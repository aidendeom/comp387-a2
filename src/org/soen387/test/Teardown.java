package org.soen387.test;

import java.sql.SQLException;

import org.dsrg.soenea.service.tdg.UserTDG;
import org.soen387.app.CheckersServlet;
import org.soen387.domain.model.challenge.tdg.ChallengeTDG;
import org.soen387.domain.model.checkerboard.tdg.CheckerBoardTDG;
import org.soen387.domain.model.notification.challenge.tdg.ChallengeNotificationTDG;
import org.soen387.domain.model.notification.game.tdg.GameNotificationTDG;
import org.soen387.domain.model.player.tdg.PlayerTDG;

public class Teardown {

	public static void main(String[] args) throws InterruptedException {
		CheckersServlet.prepareDbRegistry();
		try {
			CheckerBoardTDG.dropTable();
			PlayerTDG.dropTable();
			ChallengeTDG.dropTable();
			UserTDG.dropTable();
			GameNotificationTDG.dropTable();
			ChallengeNotificationTDG.dropTable();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
