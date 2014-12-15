package org.soen387.app.dispatcher;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;

import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.command.CommandException;
import org.dsrg.soenea.uow.UoW;
import org.soen387.domain.command.MoveCommand;
import org.soen387.domain.command.ViewGameCommand;
import org.soen387.domain.command.exception.ItsNotYourMoveException;
import org.soen387.domain.command.exception.NeedToBeLoggedInException;

public class Game extends CheckersDispatcher {

	@Override
	public void execute() throws ServletException, IOException {
		if(isSubmission) {
			//Make Move
			try {
				new MoveCommand(myHelper).execute();
				UoW.getCurrent().commit(); //we need UoW to save our move to game changes
				forward("checkerboard.jsp"); 
				//do we also need to catch an exception on "current player in game?"
			} catch (final NeedToBeLoggedInException e) {
				fail("You need to be logged in to make a move.");
			} catch (final ItsNotYourMoveException e){
				fail("This is not your move to make.");
			} catch (final CommandException e) {
				fail(e);
			} catch (InstantiationException | IllegalAccessException | MapperException | SQLException e) {
				fail(e); //UoW went to crap
			}
		} else {
			try {
				new ViewGameCommand(myHelper).execute();
				forward("checkerboard.jsp");
			} catch (final CommandException e) {
				fail(e);
			}
		}
	}
}