package org.soen387.app.dispatcher;

import java.io.IOException;

import javax.servlet.ServletException;

import org.dsrg.soenea.domain.command.CommandException;
import org.soen387.domain.command.ListGamesCommand;

public class ListGames extends CheckersDispatcher {

	@Override
	public void execute() throws ServletException, IOException {
		try {
			new ListGamesCommand(myHelper).execute();
			forward("games.jsp");
		} catch (final CommandException e) {
			fail(e);
		}
	}
}