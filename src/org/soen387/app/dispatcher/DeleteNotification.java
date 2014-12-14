package org.soen387.app.dispatcher;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;

import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.command.CommandException;
import org.dsrg.soenea.uow.UoW;
import org.soen387.domain.command.DeleteNotificationCommand;
import org.soen387.domain.command.exception.NeedToBeLoggedInException;

public class DeleteNotification extends CheckersDispatcher
{
    @Override
    public void execute() throws ServletException, IOException
    {
        try
        {
            new DeleteNotificationCommand(myHelper).execute();
            UoW.getCurrent().commit();
            forward("viewnotification.jsp");
        }
        catch (NeedToBeLoggedInException e)
        {
            fail("You need to be logged in to delete a notification");
        }
        catch (CommandException e)
        {
            fail(e);
        }
        catch (InstantiationException | IllegalAccessException | MapperException | SQLException e)
        {
            fail(e);
        }
    }
}
