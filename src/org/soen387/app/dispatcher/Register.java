package org.soen387.app.dispatcher;

import java.io.IOException;

import javax.servlet.ServletException;

import org.dsrg.soenea.uow.UoW;
import org.soen387.domain.command.LoginCommand;
import org.soen387.domain.command.RegisterCommand;
import org.soen387.domain.command.exception.NoSuchUserException;

public class Register extends CheckersDispatcher {

	@Override
	public void execute() throws ServletException, IOException {
		try {
			new RegisterCommand(myHelper).execute();
			
			//Normally I tend to do this in the command, but let's test what it 
			//feels like to commit in the Dispatcher
			UoW.getCurrent().commit();
			
			new LoginCommand(myHelper).execute();
			
			forward("player.jsp");
		} catch (final NoSuchUserException e) {
			fail("No such username and password combination.");
		} catch (final Exception e) {
			e.printStackTrace();
			fail(e);
		} 
	}
}