package org.soen387.domain.command;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.command.CommandException;
import org.dsrg.soenea.domain.command.impl.annotation.SetInRequestAttribute;
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
            
            int page = 1;
            
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
                
                int rows = 10;
                
                
                if (helper.getAttribute("r") != null)
                    rows = helper.getInt("r");
                if (helper.getAttribute("p") != null)
                    page = helper.getInt("p");
                
                rows = clamp(rows, 1, l.size());
                page = clamp(page, 1, (int)Math.ceil((float)l.size() / rows));
                
                notifications = new ArrayList<Notification>(rows);
                int startIdx = (page - 1) * rows;
                for (int i = startIdx; i < startIdx + rows; i++)
                {
                    notifications.add(l.get(i));
                }
            }
            
            helper.setRequestAttribute("page", page);
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
