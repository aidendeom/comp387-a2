package org.soen387.app.dispatcher;

import java.io.IOException;

import javax.servlet.ServletException;

public class LogOut extends CheckersDispatcher {

	@Override
	public void execute() throws ServletException, IOException {
		// I can't convince myself that I need a command here
		myHelper.invalidateSession();
		forward("success.jsp");
	}
}