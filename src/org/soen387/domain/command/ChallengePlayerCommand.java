package org.soen387.domain.command;

import java.sql.SQLException;
import java.util.List;

import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.command.CommandException;
import org.dsrg.soenea.domain.command.impl.annotation.SetInRequestAttribute;
import org.dsrg.soenea.domain.command.validator.source.IdentityBasedProducer;
import org.dsrg.soenea.domain.command.validator.source.Source;
import org.dsrg.soenea.domain.command.validator.source.impl.PermalinkSource;
import org.dsrg.soenea.domain.helper.Helper;
import org.soen387.domain.command.exception.CannotChallengeSelfException;
import org.soen387.domain.command.exception.NeedToBeLoggedInException;
import org.soen387.domain.command.exception.OnlyOneOngoingGameBetweenPlayersException;
import org.soen387.domain.command.exception.OnlyOneOpenChallengeBetweenPlayersException;
import org.soen387.domain.model.challenge.ChallengeFactory;
import org.soen387.domain.model.challenge.ChallengeStatus;
import org.soen387.domain.model.challenge.IChallenge;
import org.soen387.domain.model.challenge.mapper.ChallengeInputMapper;
import org.soen387.domain.model.checkerboard.GameStatus;
import org.soen387.domain.model.checkerboard.ICheckerBoard;
import org.soen387.domain.model.checkerboard.mapper.CheckerBoardInputMapper;
import org.soen387.domain.model.player.IPlayer;
import org.soen387.domain.model.player.mapper.PlayerInputMapper;

public class ChallengePlayerCommand extends CheckersCommand {

	public ChallengePlayerCommand(Helper helper) {
		super(helper);
	}

	@SetInRequestAttribute
	@Source(sources=PermalinkSource.class)
	@IdentityBasedProducer(mapper=PlayerInputMapper.class)
	public IPlayer player;
	
	@SetInRequestAttribute
	public IChallenge challenge;
	
	@Override
	public void process() throws CommandException {
		try {
			if(currentPlayer==null) {
				throw new NeedToBeLoggedInException();
			}
			
			if(player.equals(currentPlayer)) throw new CannotChallengeSelfException();
			
			//Alternately, make a more complex sql, but I hate making more SELECT statements, so I
			//went this route... would fix if this caused performance problems, but is cleaner otherwise
			List<IChallenge> challengerChallenges = ChallengeInputMapper.find(currentPlayer);
			challengerChallenges.retainAll( ChallengeInputMapper.find(player));
			for(IChallenge c: challengerChallenges) {
				if(c.getStatus().equals(ChallengeStatus.Open)) {
					throw new OnlyOneOpenChallengeBetweenPlayersException();
				}
			}
			
			//similar to above
			List<ICheckerBoard> challengeGames = CheckerBoardInputMapper.find(currentPlayer);
			challengeGames.retainAll(CheckerBoardInputMapper.find(player));
			for(ICheckerBoard c: challengeGames) {
				if(c.getStatus().equals(GameStatus.Ongoing)) {
					throw new OnlyOneOngoingGameBetweenPlayersException();
				}
			}
			
			//With both of the above, I'm seriously questioning why I haven't implemented the two finds needed... that's looking kinda dirty to me...
			
			challenge = ChallengeFactory.createNew(currentPlayer, player);

		} catch (MapperException | SQLException e) {
			throw new CommandException(e);
		}
	}

}
