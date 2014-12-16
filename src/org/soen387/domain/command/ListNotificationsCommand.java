package org.soen387.domain.command;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.command.CommandException;
import org.dsrg.soenea.domain.command.impl.annotation.SetInRequestAttribute;
import org.dsrg.soenea.domain.command.validator.source.NotRequired;
import org.dsrg.soenea.domain.command.validator.source.Source;
import org.dsrg.soenea.domain.command.validator.source.impl.PermalinkSource;
import org.dsrg.soenea.domain.helper.Helper;
import org.soen387.domain.command.exception.NeedToBeLoggedInException;
import org.soen387.domain.model.notification.Notification;
import org.soen387.domain.model.notification.challenge.ChallengeNotification;
import org.soen387.domain.model.notification.challenge.mapper.ChallengeNotificationInputMapper;
import org.soen387.domain.model.notification.game.GameNotification;
import org.soen387.domain.model.notification.game.mapper.GameNotificationInputMapper;

public class ListNotificationsCommand extends CheckersCommand
{
    public ListNotificationsCommand(Helper helper)
    {
        super(helper);
    }
    
    @SetInRequestAttribute
    public List<Notification> notifications;
    
    @NotRequired
    @Source(sources={PermalinkSource.class})
    public int p = 1;
    
    @NotRequired
    @Source(sources=PermalinkSource.class)
    public int r = 10;
    
    public boolean onlyUnseen = false;
    
    @Override
    public void process() throws CommandException
    {
        try
        {         
            if (currentPlayer == null)
                throw new NeedToBeLoggedInException();
            
            
            List<GameNotification> gameNotifications = null;
            List<ChallengeNotification> challengeNotifications = null;
            
            if (onlyUnseen)
            {
                gameNotifications = GameNotificationInputMapper.findUnseen(currentPlayer);
                challengeNotifications = ChallengeNotificationInputMapper.findUnseen(currentPlayer);
            }
            else
            {
                gameNotifications = GameNotificationInputMapper.find(currentPlayer);
                challengeNotifications = ChallengeNotificationInputMapper.find(currentPlayer);
            }
            
            int numNotifications = gameNotifications.size() + challengeNotifications.size();
            
            if (numNotifications > 0)
            {
                ArrayList<Notification> l = new ArrayList<Notification>(numNotifications);
                
                for (GameNotification g : gameNotifications)
                {
                    l.add(g);
                }
                for (ChallengeNotification c : challengeNotifications)
                {
                    l.add(c);
                }
                
                l.sort(Comparator.comparing(Notification::getId));
                
                r = clamp(r, 1, l.size());
                p = clamp(p, 1, (int)Math.ceil((float)l.size() / r));
                
                notifications = new ArrayList<Notification>(r);
                int startIdx = (p - 1) * r;
                for (int i = startIdx; i < startIdx + r; i++)
                {
                    notifications.add(l.get(i));
                }
            }
            
            helper.setRequestAttribute("page", p);
            helper.setRequestAttribute("count", numNotifications);
        }
        catch (MapperException e)
        {
            throw new CommandException(e);
        }
    }
    
    private int clamp(int n, int lower, int upper)
    {
        return Math.max(Math.min(n, upper), lower);
    }
}
