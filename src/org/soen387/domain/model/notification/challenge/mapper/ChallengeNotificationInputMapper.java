package org.soen387.domain.model.notification.challenge.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.ObjectRemovedException;
import org.dsrg.soenea.domain.mapper.DomainObjectNotFoundException;
import org.dsrg.soenea.domain.mapper.IdentityMap;
import org.soen387.domain.model.challenge.ChallengeProxy;
import org.soen387.domain.model.challenge.IChallenge;
import org.soen387.domain.model.notification.challenge.ChallengeNotificationType;
import org.soen387.domain.model.notification.challenge.ChallengeNotification;
import org.soen387.domain.model.notification.challenge.ChallengeNotificationFactory;
import org.soen387.domain.model.notification.challenge.tdg.ChallengeNotificationFinder;
import org.soen387.domain.model.player.IPlayer;
import org.soen387.domain.model.player.PlayerProxy;

public class ChallengeNotificationInputMapper
{
    public static ChallengeNotification find(long id) throws MapperException, DomainObjectNotFoundException
    {
        ChallengeNotification c = null;
        try
        {
            c = IdentityMap.get(id, ChallengeNotification.class);
        }
        catch (final ObjectRemovedException | DomainObjectNotFoundException e)
        {
        }
        
        if (c != null)
            return c;

        try
        {
            ResultSet rs = ChallengeNotificationFinder.find(id);
            if (rs.next())
            {
                c = buildNotification(rs);
                rs.close();
                return c;
            }
        }
        catch(SQLException e)
        {
            throw new MapperException(e);
        }
        
        throw new DomainObjectNotFoundException("Could not create a ChallengeNotification with id \""+id+"\"");
    }
    
    public static List<ChallengeNotification> find(IPlayer p) throws MapperException
    {
        try
        {
            ResultSet rs = ChallengeNotificationFinder.findByPlayer(p.getId());
            return buildCollection(rs);
        }
        catch (SQLException e)
        {
            throw new MapperException(e);
        }
    }
    
    public static List<ChallengeNotification> findUnseen(IPlayer p) throws MapperException
    {
        try
        {
            ResultSet rs = ChallengeNotificationFinder.findByPlayerUnseen(p.getId());
            return buildCollection(rs);
        }
        catch (SQLException e)
        {
            throw new MapperException(e);
        }
    }
    
    public static List<ChallengeNotification> findAll() throws MapperException
    {
        try
        {
            ResultSet rs = ChallengeNotificationFinder.findAll();
            return buildCollection(rs);
        }
        catch (SQLException e)
        {
            throw new MapperException(e);
        }
    }
    
    private static List<ChallengeNotification> buildCollection(ResultSet rs) throws SQLException, MapperException 
    {
        ArrayList<ChallengeNotification> l = new ArrayList<ChallengeNotification>();
        while(rs.next())
        {
            long id = rs.getLong("id");
            ChallengeNotification c = null;
            try
            {
                c = IdentityMap.get(id, ChallengeNotification.class);
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
    
    private static ChallengeNotification buildNotification(ResultSet rs) throws SQLException, MapperException
    {
        long id = rs.getLong("id");
        long version = rs.getLong("version");
        IPlayer recipient = new PlayerProxy(rs.getLong("recipient"));
        boolean seen = rs.getBoolean("seen");
        IChallenge challenge = new ChallengeProxy(rs.getLong("challenge"));
        ChallengeNotificationType type = ChallengeNotificationType.fromNumVal(rs.getInt("type"));
        
        return ChallengeNotificationFactory.createClean(id, version, recipient, seen, challenge, type);
    }
}
