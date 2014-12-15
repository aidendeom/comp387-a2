package org.soen387.app.dispatcher;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;

import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.command.CommandException;
import org.dsrg.soenea.uow.UoW;
import org.soen387.domain.command.ConcedeGameCommand;
import org.soen387.domain.command.exception.CanOnlyConcedeOngoingGameException;
import org.soen387.domain.command.exception.NeedToBeLoggedInException;
import org.soen387.domain.command.exception.NotYourGameException;

public class ConcedeGame extends CheckersDispatcher
{
    @Override
    public void execute() throws ServletException, IOException
    {
        try
        {
            new ConcedeGameCommand(myHelper).execute();
            UoW.getCurrent().commit();
            forward("checkerboard.jsp");
        }
        catch (NeedToBeLoggedInException e)
        {
            fail("You need to be logged in to concede a game!");
        }
        catch (NotYourGameException e)
        {
            fail("This is not your game to concede!");
        }
        catch (CanOnlyConcedeOngoingGameException e)
        {
            fail("You cannot concede a game that is not ongoing!");
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