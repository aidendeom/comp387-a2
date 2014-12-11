package org.soen387.domain.model.notification.challenge.tdg;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.dsrg.soenea.service.threadLocal.DbRegistry;

public class ChallengeNotificationTDG
{
    public static final String TABLE_NAME = "ChallengeNotification";
    public static final String COLUMNS = "id, version, recipient, seen, challenge, type";

    public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS "
            + TABLE_NAME + " (" + "id BIGINT, " + "version BIGINT, "
            + "recipient BIGINT, " + "seen BIT(1), " + "challenge BIGINT, "
            + "type INT, " + "PRIMARY KEY(id), " + "INDEX(recipient), "
            + "INDEX(challenge) " + ");";

    public static void createTable() throws SQLException
    {
        Connection con = DbRegistry.getDbConnection();
        Statement update = con.createStatement();
        update.execute(CREATE_TABLE);
    }

    public static final String TRUNCATE_TABLE = "TRUNCATE TABLE  " + TABLE_NAME+ ";";
    public static final String DROP_TABLE = "DROP TABLE  " + TABLE_NAME + ";";

    public static void dropTable() throws SQLException
    {
        Connection con = DbRegistry.getDbConnection();
        Statement update = con.createStatement();
        update.execute(TRUNCATE_TABLE);
        update = con.createStatement();
        update.execute(DROP_TABLE);
    }

    public static final String INSERT = "INSERT INTO " + TABLE_NAME + " ("
            + COLUMNS + ") " + "VALUES(?,?,?,?,?,?);";

    public static int insert(long id,
                             long version,
                             long recipientID,
                             boolean seen,
                             long challengeID,
                             int type) throws SQLException
    {
        Connection con = DbRegistry.getDbConnection();
        PreparedStatement ps = con.prepareStatement(INSERT);
        ps.setLong(1, id);
        ps.setLong(2, version);
        ps.setLong(3, recipientID);
        ps.setBoolean(4, seen);
        ps.setLong(5, challengeID);
        ps.setInt(6, type);

        return ps.executeUpdate();
    }

    public static final String UPDATE = "UPDATE " + TABLE_NAME
            + " set version = version+1,"
            + "recipient=?, seen=?, challenge=?, type=?"
            + " WHERE id=? AND version=?;";

    public static int update(long id,
                             long version,
                             long recipientID,
                             boolean seen,
                             long challengeID,
                             int type) throws SQLException
    {
        Connection con = DbRegistry.getDbConnection();
        PreparedStatement ps = con.prepareStatement(UPDATE);
        ps.setLong(1, recipientID);
        ps.setBoolean(2, seen);
        ps.setLong(3, challengeID);
        ps.setInt(4, type);
        ps.setLong(5, id);
        ps.setLong(6, version);

        return ps.executeUpdate();
    }

    public static final String DELETE = "DELETE FROM " + TABLE_NAME + " "
            + "WHERE id=? AND version=?;";

    public static int delete(long id, long version) throws SQLException
    {
        Connection con = DbRegistry.getDbConnection();
        PreparedStatement ps = con.prepareStatement(DELETE);
        ps.setLong(1, id);
        ps.setLong(2, version);

        return ps.executeUpdate();
    }

    private static long maxId = 0;
    public static final String GET_MAX_ID = "SELECT max(id) AS max FROM "
            + TABLE_NAME + ";";

    public static synchronized long getMaxId() throws SQLException
    {
        if (maxId == 0)
        {
            Connection con = DbRegistry.getDbConnection();
            PreparedStatement ps = con.prepareStatement(GET_MAX_ID);
            ResultSet rs = ps.executeQuery();
            if (rs.next())
            {
                maxId = rs.getLong("max");
            }
        }
        return ++maxId;
    }
}
