package org.soen387.domain.model.challenge.tdg;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.dsrg.soenea.service.threadLocal.DbRegistry;

public class ChallengeFinder {

	public static final String FIND = "SELECT " + ChallengeTDG.COLUMNS + " FROM " + ChallengeTDG.TABLE_NAME + " WHERE id=? AND status NOT IN (3);";
	public static ResultSet find(long id) throws SQLException {
    	Connection con = DbRegistry.getDbConnection();
		PreparedStatement ps = con.prepareStatement(FIND);
		ps.setLong(1,id);
		return ps.executeQuery();
	}
	
	public static final String FIND_ALL = "SELECT " + ChallengeTDG.COLUMNS + " FROM " + ChallengeTDG.TABLE_NAME + " WHERE status NOT IN (3);";
	public static ResultSet findAll() throws SQLException {
    	Connection con = DbRegistry.getDbConnection();
		PreparedStatement ps = con.prepareStatement(FIND_ALL);
		return ps.executeQuery();
	}
	
	public static final String FIND_BY_PLAYER = "SELECT " + ChallengeTDG.COLUMNS + " FROM " + ChallengeTDG.TABLE_NAME + " WHERE challenger=? or challengee=? AND status NOT IN (3);";
	public static ResultSet findByPlayer(long player) throws SQLException {
    	Connection con = DbRegistry.getDbConnection();
		PreparedStatement ps = con.prepareStatement(FIND_BY_PLAYER);
		ps.setLong(1,player);
		ps.setLong(2,player);
		return ps.executeQuery();
	}

}
