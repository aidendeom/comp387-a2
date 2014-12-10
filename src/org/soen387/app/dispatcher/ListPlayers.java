package org.soen387.app.dispatcher;

import java.io.IOException;

import javax.servlet.ServletException;

import org.dsrg.soenea.domain.command.CommandException;
import org.soen387.domain.command.ListPlayersCommand;

public class ListPlayers extends CheckersDispatcher {

	@Override
	public void execute() throws ServletException, IOException {
		try {
			new ListPlayersCommand(myHelper).execute();
			forward("players.jsp");
		} catch (final CommandException e) {
			fail(e);
		}
	}
}