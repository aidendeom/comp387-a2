package org.soen387.app.dispatcher;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;

import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.command.CommandException;
import org.dsrg.soenea.uow.UoW;
import org.soen387.domain.command.ChallengePlayerCommand;
import org.soen387.domain.command.exception.CannotChallengeSelfException;
import org.soen387.domain.command.exception.NeedToBeLoggedInException;
import org.soen387.domain.command.exception.OnlyOneOngoingGameBetweenPlayersException;
import org.soen387.domain.command.exception.OnlyOneOpenChallengeBetweenPlayersException;

public class ChallengePlayer extends CheckersDispatcher {

	@Override
	public void execute() throws ServletException, IOException {
		if(isSubmission) {
			try {
				new ChallengePlayerCommand(myHelper).execute();
				UoW.getCurrent().commit();
				forward("challenge.jsp");
			} catch (final NeedToBeLoggedInException e) {
				fail("You need to be logged in to challenge a player.");
			} catch (final CannotChallengeSelfException e) {
				fail("You can't challenge yourself.");
			} catch (final OnlyOneOpenChallengeBetweenPlayersException e) {
				fail("You may only have one open challenge against another player.");
			} catch (final OnlyOneOngoingGameBetweenPlayersException e) {
				fail("You may not issue a challenge against a player with whom you have an ongoing game.");
			} catch (final CommandException e) {
				fail(e);
			} catch (InstantiationException | IllegalAccessException | MapperException | SQLException e) {
				fail(e); //UoW went to crap
			}
		} else {
			//I guess this would give an HTML form? Is there a use for GET?
			//There's something in SOENEA that filters on this, but let's hold off
		}
	}
}