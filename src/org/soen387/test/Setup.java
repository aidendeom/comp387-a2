package org.soen387.test;

import java.sql.SQLException;

import org.dsrg.soenea.service.tdg.UserTDG;
import org.soen387.app.CheckersServlet;
import org.soen387.domain.model.challenge.tdg.ChallengeTDG;
import org.soen387.domain.model.checkerboard.tdg.CheckerBoardTDG;
import org.soen387.domain.model.notification.challenge.tdg.ChallengeNotificationTDG;
import org.soen387.domain.model.notification.game.tdg.GameNotificationTDG;
import org.soen387.domain.model.player.tdg.PlayerTDG;

public class Setup {

	public static void main(String[] args) throws InterruptedException {
		CheckersServlet.prepareDbRegistry();
		try {
			CheckerBoardTDG.createTable();
			PlayerTDG.createTable();
			ChallengeTDG.createTable();
			UserTDG.createTable();
			UserTDG.createUserRoleTable();
			GameNotificationTDG.createTable();
			ChallengeNotificationTDG.createTable();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
