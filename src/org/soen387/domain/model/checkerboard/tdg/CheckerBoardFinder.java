package org.soen387.domain.model.checkerboard.tdg;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.dsrg.soenea.service.threadLocal.DbRegistry;

public class CheckerBoardFinder {
	public static final String FIND_ALL = "SELECT " + CheckerBoardTDG.COLUMNS + " FROM " + CheckerBoardTDG.TABLE_NAME + ";";
	public static ResultSet findAll() throws SQLException {
    	Connection con = DbRegistry.getDbConnection();
		PreparedStatement ps = con.prepareStatement(FIND_ALL);
		return ps.executeQuery();
	}
	
	public static final String FIND_BY_PLAYER = "SELECT " + CheckerBoardTDG.COLUMNS + " FROM " + CheckerBoardTDG.TABLE_NAME + " WHERE first_player=? or second_player=?;";
	public static ResultSet findByPlayer(long player) throws SQLException {
    	Connection con = DbRegistry.getDbConnection();
		PreparedStatement ps = con.prepareStatement(FIND_BY_PLAYER);
		ps.setLong(1,player);
		ps.setLong(2,player);
		return ps.executeQuery();
	}
	
	public static final String FIND = "SELECT " + CheckerBoardTDG.COLUMNS + " FROM " + CheckerBoardTDG.TABLE_NAME + " WHERE id=?;";
	public static ResultSet find(long id) throws SQLException {
    	Connection con = DbRegistry.getDbConnection();
		PreparedStatement ps = con.prepareStatement(FIND);
		ps.setLong(1,id);
		return ps.executeQuery();
	}
}
