package org.soen387.domain.model.notification.game.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.ObjectRemovedException;
import org.dsrg.soenea.domain.mapper.DomainObjectNotFoundException;
import org.dsrg.soenea.domain.mapper.IdentityMap;
import org.dsrg.soenea.domain.producer.IdentityBasedProducer;
import org.soen387.domain.model.checkerboard.CheckerBoardProxy;
import org.soen387.domain.model.checkerboard.ICheckerBoard;
import org.soen387.domain.model.notification.game.GameNotification;
import org.soen387.domain.model.notification.game.GameNotificationFactory;
import org.soen387.domain.model.notification.game.GameNotificationType;
import org.soen387.domain.model.notification.game.tdg.GameNotificationFinder;
import org.soen387.domain.model.player.IPlayer;
import org.soen387.domain.model.player.PlayerProxy;

public class GameNotificationInputMapper implements IdentityBasedProducer
{
    public static GameNotification find(long id) throws MapperException, DomainObjectNotFoundException
    {
        GameNotification g = null;
        try
        {
            g = IdentityMap.get(id, GameNotification.class);
        }
        catch (final ObjectRemovedException | DomainObjectNotFoundException e)
        {
        }
        
        if (g != null)
            return g;

        try
        {
            ResultSet rs = GameNotificationFinder.find(id);
            if (rs.next())
            {
                g = buildNotification(rs);
                rs.close();
                return g;
            }
        }
        catch(SQLException e)
        {
            throw new MapperException(e);
        }
        
        throw new DomainObjectNotFoundException("Could not create a GameNotification with id \""+id+"\"");
    }
    
    public static List<GameNotification> find(IPlayer p) throws MapperException
    {
        try
        {
            ResultSet rs = GameNotificationFinder.findByPlayer(p.getId());
            return buildCollection(rs);
        }
        catch (SQLException e)
        {
            throw new MapperException(e);
        }
    }
    
    public static List<GameNotification> findUnseen(IPlayer p) throws MapperException
    {
        try
        {
            ResultSet rs = GameNotificationFinder.findByPlayerUnseen(p.getId());
            return buildCollection(rs);
        }
        catch (SQLException e)
        {
            throw new MapperException(e);
        }
    }
    
    public static List<GameNotification> findAll() throws MapperException
    {
        try
        {
            ResultSet rs = GameNotificationFinder.findAll();
            return buildCollection(rs);
        }
        catch (SQLException e)
        {
            throw new MapperException(e);
        }
    }
    
    private static List<GameNotification> buildCollection(ResultSet rs) throws SQLException, MapperException 
    {
        ArrayList<GameNotification> l = new ArrayList<GameNotification>();
        while(rs.next())
        {
            long id = rs.getLong("id");
            GameNotification c = null;
            try
            {
                c = IdentityMap.get(id, GameNotification.class);
            }
            catch (final ObjectRemovedException | DomainObjectNotFoundException e)
            {
                // Do I care if it has been removed?
            }
            
            if(c == null)
            {
                c = buildNotification(rs);
            }
            
            l.add(c);
        }
        
        return l;
    }
    
    private static GameNotification buildNotification(ResultSet rs) throws SQLException, MapperException
    {
        long id = rs.getLong("id");
        long version = rs.getLong("version");
        IPlayer recipient = new PlayerProxy(rs.getLong("recipient"));
        boolean seen = rs.getBoolean("seen");
        ICheckerBoard board = new CheckerBoardProxy(rs.getLong("board"));
        GameNotificationType type = GameNotificationType.fromNumVal(rs.getInt("type"));
        
        return GameNotificationFactory.createClean(id, version, recipient, seen, board, type);
    }
}
