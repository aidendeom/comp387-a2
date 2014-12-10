package org.soen387.app.dispatcher;

import java.io.IOException;

import javax.servlet.ServletException;

import org.dsrg.soenea.domain.command.CommandException;
import org.soen387.domain.command.LoginCommand;
import org.soen387.domain.command.exception.NoSuchUserException;

public class LogIn extends CheckersDispatcher {

	@Override
	public void execute() throws ServletException, IOException {
		try {
			new LoginCommand(myHelper).execute();
			forward("player.jsp");
		} catch (final NoSuchUserException e) {
			fail("No such username and password combination.");
		} catch (final CommandException e) {
			fail(e);
		}
	}
}