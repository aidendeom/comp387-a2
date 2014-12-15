package org.soen387.domain.command;

import java.sql.SQLException;
import java.util.List;

import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.command.CommandException;
import org.dsrg.soenea.domain.helper.Helper;
import org.dsrg.soenea.uow.UoW;
import org.soen387.domain.command.exception.NeedToBeLoggedInException;
import org.soen387.domain.model.challenge.ChallengeStatus;
import org.soen387.domain.model.challenge.IChallenge;
import org.soen387.domain.model.challenge.mapper.ChallengeInputMapper;
import org.soen387.domain.model.checkerboard.GameStatus;
import org.soen387.domain.model.checkerboard.ICheckerBoard;
import org.soen387.domain.model.checkerboard.mapper.CheckerBoardInputMapper;
import org.soen387.domain.model.notification.challenge.ChallengeNotification;
import org.soen387.domain.model.notification.challenge.ChallengeNotificationFactory;
import org.soen387.domain.model.notification.challenge.ChallengeNotificationType;
import org.soen387.domain.model.notification.challenge.mapper.ChallengeNotificationInputMapper;
import org.soen387.domain.model.notification.game.GameNotificationFactory;
import org.soen387.domain.model.notification.game.GameNotificationType;
import org.soen387.domain.model.player.IPlayer;

public class DeletePlayerCommand extends CheckersCommand
{
    public DeletePlayerCommand(Helper helper)
    {
        super(helper);
    }
    
    @Override
    public void process() throws CommandException
    {
        try
        {
            // Alias so I don't have to type as much
            IPlayer p = currentPlayer;
            
            if (p == null)
                throw new NeedToBeLoggedInException();
            
            List<IChallenge> challenges = ChallengeInputMapper.find(p);
            
            for (IChallenge c : challenges)
            {
                if (c.getStatus() == ChallengeStatus.Open)
                {
                    if (c.getChallenger().equals(p))
                    {
                        c.setStatus(ChallengeStatus.Withdrawn);
                        //delete any unseen challengeIssued notifications
                        List<ChallengeNotification> l = ChallengeNotificationInputMapper.findUnseen(c);
                        
                        for (int i = 0; i < l.size(); i++)
                        {
                            ChallengeNotification n = l.get(i);
                            UoW.getCurrent().registerRemoved(n);    
                        }
                    }
                    else
                    {
                        c.setStatus(ChallengeStatus.Refused);
                        ChallengeNotificationFactory.createNew(c.getChallenger(), c, ChallengeNotificationType.Refused);
                    }
                    
                    UoW.getCurrent().registerDirty(c);
                }
            }
            
            List<ICheckerBoard> games = CheckerBoardInputMapper.find(p);
            // Concede all games
            for (ICheckerBoard b : games)
            {
                if (b.getStatus() == GameStatus.Ongoing)
                {
                    IPlayer winner = b.getFirstPlayer().equals(p) ? b.getSecondPlayer() : b.getFirstPlayer();
                    b.setStatus(GameStatus.Won);
                    // The winner is the current player???
                    b.setCurrentPlayer(winner);
                    GameNotificationFactory.createNew(winner, b, GameNotificationType.Won);
                    
                    UoW.getCurrent().registerDirty(b);
                }
            }
            
            String ps = String.format("del_%d", p.getId());
            String us = String.format("del_%d", p.getUser().getId());
            p.getUser().setPassword("-");
            p.getUser().setUsername(us);
            p.setFirstName(ps);
            p.setLastName(ps);
            p.setEmail(ps + "@CheckersGame.com");
            
            UoW.getCurrent().registerDirty(p);
            UoW.getCurrent().registerDirty(p.getUser());
            
            helper.invalidateSession();
        }
        catch (MapperException | SQLException e)
        {
            throw new CommandException(e);
        }
    }
}
