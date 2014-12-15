package org.soen387.app.dispatcher;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;

import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.command.CommandException;
import org.dsrg.soenea.uow.UoW;
import org.soen387.domain.command.RespondToChallengeCommand;
import org.soen387.domain.command.exception.CanOnlyRespondToChallengesIssuedAgainstYouException;
import org.soen387.domain.command.exception.CanOnlyRespondToOpenChallengesException;
import org.soen387.domain.command.exception.ChallengeHasBeenWithdrawnException;
import org.soen387.domain.command.exception.NeedToBeLoggedInException;

public class RespondToChallenge extends CheckersDispatcher {

	@Override
	public void execute() throws ServletException, IOException {
		if(isSubmission) {
			try {
				new RespondToChallengeCommand(myHelper).execute();
				UoW.getCurrent().commit();
				forward("challenge.jsp");
			} catch (final NeedToBeLoggedInException e) {
				fail("You need to be logged in to challenge a player.");
			} catch (final CanOnlyRespondToChallengesIssuedAgainstYouException e) {
				fail("You aren't the recipient of the challenge you are trying to respond to.");
			} catch (final CanOnlyRespondToOpenChallengesException e) {
				fail("The challenge you are trying to respond to either does not exist or is no longer open.");
			} catch (final ChallengeHasBeenWithdrawnException e){
				fail("The Challenge no longer appears to exist, perhaps it was withdrawn");
			} catch (final CommandException e) {
				fail(e);
			} catch (InstantiationException | IllegalAccessException | MapperException | SQLException e) {
				fail(e); //UoW went to crap 
			}
		} else {
			//I guess this would give an HTML form? Is there a use for GET? It might be more plausible if looking
			//at an individual challenge?
			//There's something in SOENEA that filters on this, but let's hold off
		}
	}
}