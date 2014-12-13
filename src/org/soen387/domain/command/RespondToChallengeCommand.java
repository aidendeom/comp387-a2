package org.soen387.domain.command;

import java.sql.SQLException;

import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.command.CommandException;
import org.dsrg.soenea.domain.command.impl.annotation.SetInRequestAttribute;
import org.dsrg.soenea.domain.command.validator.source.IdentityBasedProducer;
import org.dsrg.soenea.domain.command.validator.source.Source;
import org.dsrg.soenea.domain.command.validator.source.impl.ParameterSource;
import org.dsrg.soenea.domain.command.validator.source.impl.PermalinkSource;
import org.dsrg.soenea.domain.helper.Helper;
import org.dsrg.soenea.uow.UoW;
import org.soen387.domain.command.exception.CanOnlyRespondToChallengesIssuedAgainstYouException;
import org.soen387.domain.command.exception.CanOnlyRespondToOpenChallengesException;
import org.soen387.domain.command.exception.NeedToBeLoggedInException;
import org.soen387.domain.model.challenge.ChallengeStatus;
import org.soen387.domain.model.challenge.IChallenge;
import org.soen387.domain.model.challenge.mapper.ChallengeInputMapper;
import org.soen387.domain.model.checkerboard.CheckerBoardFactory;
import org.soen387.domain.model.notification.challenge.ChallengeNotificationFactory;
import org.soen387.domain.model.notification.challenge.ChallengeNotificationType;

public class RespondToChallengeCommand extends CheckersCommand {

	public RespondToChallengeCommand(Helper helper) {
		super(helper);
	}

	@SetInRequestAttribute
	@Source(sources=PermalinkSource.class)
	@IdentityBasedProducer(mapper=ChallengeInputMapper.class)
	public IChallenge challenge;
	
	@Source(sources=ParameterSource.class)
	public long version;
	
	@Source(sources=ParameterSource.class)
	@IdentityBasedProducer(mapper=ChallengeStatus.ChallengeStatusProducer.class)
	public ChallengeStatus status;
	
	@Override
	public void process() throws CommandException {
		try {
			//Validation
			if(currentPlayer==null) {
				throw new NeedToBeLoggedInException();
			}
			
			challenge.setVersion(version);
			
			if(!challenge.getChallengee().equals(currentPlayer)) {
				throw new CanOnlyRespondToChallengesIssuedAgainstYouException();
			}

			if(!challenge.getStatus().equals(ChallengeStatus.Open)) {
				throw new CanOnlyRespondToOpenChallengesException();
			}
			
			//Do it
			challenge.setStatus(status);
			if(status.equals(ChallengeStatus.Accepted)) {
			    ChallengeNotificationFactory.createNew(challenge.getChallenger(), challenge, ChallengeNotificationType.Accepted);
				CheckerBoardFactory.createNew(currentPlayer, challenge.getChallenger());
			}
			else
			{
			    ChallengeNotificationFactory.createNew(challenge.getChallenger(), challenge, ChallengeNotificationType.Refused);
			}
			UoW.getCurrent().registerDirty(challenge);
			
		} catch (MapperException | SQLException e) {
			throw new CommandException(e);
		}
	}

}
