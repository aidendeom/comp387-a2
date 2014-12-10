package org.soen387.app.dispatcher;

import java.io.IOException;

import javax.servlet.ServletException;

import org.dsrg.soenea.domain.command.CommandException;
import org.soen387.domain.command.ListChallengesCommand;

public class ListChallenges extends CheckersDispatcher {

	@Override
	public void execute() throws ServletException, IOException {
		try {
			new ListChallengesCommand(myHelper).execute();
			forward("challenges.jsp");
		} catch (final CommandException e) {
			fail(e);
		}
	}
}