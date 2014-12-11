package org.soen387.domain.model.notification.game.tdg;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.dsrg.soenea.service.threadLocal.DbRegistry;

public class ChallengeNotificationFinder
{
    public static final String FIND = "SELECT " + GameNotificationTDG.COLUMNS
            + " FROM " + GameNotificationTDG.TABLE_NAME + " WHERE id=?;";
    public static ResultSet find(long id) throws SQLException
    {
        Connection con = DbRegistry.getDbConnection();
        PreparedStatement ps = con.prepareStatement(FIND);
        ps.setLong(1, id);
        
        return ps.executeQuery();
    }

    public static final String FIND_ALL = "SELECT "
            + GameNotificationTDG.COLUMNS + " FROM "
            + GameNotificationTDG.TABLE_NAME + ";";

    public static ResultSet findAll() throws SQLException
    {
        Connection con = DbRegistry.getDbConnection();
        PreparedStatement ps = con.prepareStatement(FIND_ALL);
        
        return ps.executeQuery();
    }

    public static final String FIND_BY_PLAYER = "SELECT "
            + GameNotificationTDG.COLUMNS + " FROM "
            + GameNotificationTDG.TABLE_NAME
            + " WHERE recipient=?;";

    public static ResultSet findByPlayer(long player) throws SQLException
    {
        Connection con = DbRegistry.getDbConnection();
        PreparedStatement ps = con.prepareStatement(FIND_BY_PLAYER);
        ps.setLong(1, player);
        
        return ps.executeQuery();
    }
}
