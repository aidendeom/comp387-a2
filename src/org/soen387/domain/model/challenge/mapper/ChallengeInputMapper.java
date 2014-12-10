package org.soen387.domain.model.challenge.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.ObjectRemovedException;
import org.dsrg.soenea.domain.mapper.DomainObjectNotFoundException;
import org.dsrg.soenea.domain.mapper.IdentityMap;
import org.dsrg.soenea.domain.producer.IdentityBasedProducer;
import org.dsrg.soenea.domain.producer.IdentityBasedProducerMethod;
import org.soen387.domain.model.challenge.Challenge;
import org.soen387.domain.model.challenge.ChallengeFactory;
import org.soen387.domain.model.challenge.ChallengeStatus;
import org.soen387.domain.model.challenge.IChallenge;
import org.soen387.domain.model.challenge.tdg.ChallengeFinder;
import org.soen387.domain.model.player.IPlayer;
import org.soen387.domain.model.player.PlayerProxy;

public class ChallengeInputMapper implements IdentityBasedProducer {


	@IdentityBasedProducerMethod
	public static Challenge find(long id) throws SQLException, DomainObjectNotFoundException {
		Challenge c = null;
		try {
			c = IdentityMap.get(id, Challenge.class);
		} catch (final ObjectRemovedException | DomainObjectNotFoundException e) {
			// Do I care if it has been removed?
		}
		if(c!=null) return c;
		
		ResultSet rs = ChallengeFinder.find(id);
		if(rs.next()) {
			c = buildChallenge(rs);
			rs.close();
			return c;
		}
		throw new DomainObjectNotFoundException("Could not create a Challenge with id \""+id+"\"");
	}

	public static List<IChallenge> buildCollection(ResultSet rs)
		    throws SQLException, DomainObjectNotFoundException {
		    ArrayList<IChallenge> l = new ArrayList<IChallenge>();
		    while(rs.next()) {
		    	long id = rs.getLong("id");
				Challenge c = null;
				try {
					c = IdentityMap.get(id, Challenge.class);
				} catch (final ObjectRemovedException | DomainObjectNotFoundException e) {
					// Do I care if it has been removed?
				}
		    	if(c == null) {
		    		c = buildChallenge(rs);
		    	}
		    	l.add(c);
		    }
		    return l;
		}

	public static List<IChallenge> find(IPlayer p) throws MapperException {
        try {
            ResultSet rs = ChallengeFinder.findByPlayer(p.getId());
            return buildCollection(rs);
        } catch (SQLException e) {
            throw new MapperException(e);
        }
	}
	
	public static List<IChallenge> findAll() throws MapperException {
        try {
            ResultSet rs = ChallengeFinder.findAll();
            return buildCollection(rs);
        } catch (SQLException e) {
            throw new MapperException(e);
        }
	}
	

	private static Challenge buildChallenge(ResultSet rs) throws SQLException  {
		// TODO Auto-generated method stub
		return ChallengeFactory.createClean(rs.getLong("id"),
				rs.getInt("version"),
				new PlayerProxy(rs.getLong("challenger")),
				new PlayerProxy(rs.getLong("challengee")),
				ChallengeStatus.values()[rs.getInt("status")]
				);
	}
	
}
