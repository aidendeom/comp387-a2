package org.soen387.app.dispatcher;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;

import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.command.CommandException;
import org.dsrg.soenea.uow.UoW;
import org.soen387.domain.command.ListNotificationsCommand;
import org.soen387.domain.command.exception.NeedToBeLoggedInException;

public class ListUnseenNotifications extends CheckersDispatcher
{
    @Override
    public void execute() throws ServletException, IOException
    {
        try
        {
            ListNotificationsCommand c = new ListNotificationsCommand(myHelper);
            c.onlyUnseen = true;
            c.execute();
            
            UoW.getCurrent().commit();
            forward("notifications.jsp");
        }
        catch (NeedToBeLoggedInException e)
        {
            fail("You need to be logged in to see your notifications");
        }
        catch (CommandException e)
        {
            fail(e);
        }
        catch (InstantiationException | IllegalAccessException | MapperException | SQLException e) {
            fail(e); //UoW went to crap 
        }
    }
}
