package org.soen387.domain.command;

import java.util.List;

import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.command.CommandException;
import org.dsrg.soenea.domain.command.impl.annotation.SetInRequestAttribute;
import org.dsrg.soenea.domain.helper.Helper;
import org.dsrg.soenea.uow.UoW;
import org.soen387.domain.command.exception.NeedToBeLoggedInException;
import org.soen387.domain.model.notification.challenge.ChallengeNotification;
import org.soen387.domain.model.notification.challenge.mapper.ChallengeNotificationInputMapper;
import org.soen387.domain.model.notification.game.GameNotification;
import org.soen387.domain.model.notification.game.mapper.GameNotificationInputMapper;

public class ViewUnseenNotificationsCommand extends CheckersCommand
{
    public ViewUnseenNotificationsCommand(Helper helper)
    {
        super(helper);
    }
    
    @SetInRequestAttribute
    public List<GameNotification> gameNotifications;
    
    @SetInRequestAttribute
    public List<ChallengeNotification> challengeNotifications;
    
    @Override
    public void process() throws CommandException
    {
        try
        {
            if (currentPlayer == null)
                throw new NeedToBeLoggedInException();
            
            gameNotifications = GameNotificationInputMapper.findUnseen(currentPlayer);
            challengeNotifications = ChallengeNotificationInputMapper.findUnseen(currentPlayer);
            
            for (GameNotification g : gameNotifications)
            {
                g.setSeen(true);
                UoW.getCurrent().registerDirty(g);
            }
            for (ChallengeNotification c : challengeNotifications)
            {
                c.setSeen(true);
                UoW.getCurrent().registerDirty(c);
            }
        }
        catch (MapperException e)
        {
            throw new CommandException(e);
        }
    }
}
