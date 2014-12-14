package org.soen387.domain.command;

import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.command.CommandException;
import org.dsrg.soenea.domain.command.impl.annotation.SetInRequestAttribute;
import org.dsrg.soenea.domain.command.validator.source.Source;
import org.dsrg.soenea.domain.command.validator.source.impl.PermalinkSource;
import org.dsrg.soenea.domain.helper.Helper;
import org.dsrg.soenea.domain.mapper.DomainObjectNotFoundException;
import org.dsrg.soenea.uow.UoW;
import org.soen387.domain.command.exception.CanOnlySeeYourNotificationsException;
import org.soen387.domain.command.exception.NeedToBeLoggedInException;
import org.soen387.domain.model.notification.Notification;
import org.soen387.domain.model.notification.challenge.mapper.ChallengeNotificationInputMapper;
import org.soen387.domain.model.notification.game.mapper.GameNotificationInputMapper;

public class SeeNotificationCommand extends CheckersCommand
{
    public SeeNotificationCommand(Helper helper)
    {
        super(helper);
    }

    @Source(sources=PermalinkSource.class)
    public int notificationID;
    
    @SetInRequestAttribute
    public Notification notification;
    
    @Override
    public void process() throws CommandException
    {
        try
        {
            if (currentPlayer == null)
                throw new NeedToBeLoggedInException();
            
            try
            {
                notification = GameNotificationInputMapper.find(notificationID);
            }
            catch (DomainObjectNotFoundException e)
            {
                notification = ChallengeNotificationInputMapper.find(notificationID);
            }
            
            if (!notification.getRecipient().equals(currentPlayer))
                throw new CanOnlySeeYourNotificationsException();
            
            notification.setSeen(true);
            UoW.getCurrent().registerDirty(notification);
        }
        catch (MapperException e)
        {
            throw new CommandException(e);
        }
    }
}
