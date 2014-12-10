package org.soen387.domain.model.checkerboard.mapper;

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
import org.soen387.domain.model.checkerboard.CheckerBoard;
import org.soen387.domain.model.checkerboard.CheckerBoardFactory;
import org.soen387.domain.model.checkerboard.GameStatus;
import org.soen387.domain.model.checkerboard.ICheckerBoard;
import org.soen387.domain.model.checkerboard.tdg.CheckerBoardFinder;
import org.soen387.domain.model.player.IPlayer;
import org.soen387.domain.model.player.PlayerProxy;

public class CheckerBoardInputMapper implements IdentityBasedProducer{
	
	public static List<ICheckerBoard> buildCollection(ResultSet rs)
		    throws SQLException, DomainObjectNotFoundException {
		    ArrayList<ICheckerBoard> l = new ArrayList<ICheckerBoard>();
		    while(rs.next()) {
		    	long id = rs.getLong("id");
				CheckerBoard cb = null;
				try {
					cb = IdentityMap.get(id, CheckerBoard.class);
				} catch (final ObjectRemovedException | DomainObjectNotFoundException e) {
					// Do I care if it has been removed?
				}
		    	if(cb == null) {
		    		cb = buildCheckerBoard(rs);
		    	}
		    	l.add(cb);
		    }
		    return l;
		}

	public static List<ICheckerBoard> findAll() throws MapperException {
        try {
            ResultSet rs = CheckerBoardFinder.findAll();
            return buildCollection(rs);
        } catch (SQLException e) {
            throw new MapperException(e);
        }
	}
	
	public static List<ICheckerBoard> find(IPlayer p) throws MapperException {
        try {
            ResultSet rs = CheckerBoardFinder.findByPlayer(p.getId());
            return buildCollection(rs);
        } catch (SQLException e) {
            throw new MapperException(e);
        }
	}
	
	@IdentityBasedProducerMethod
	public static CheckerBoard find(long id) throws SQLException, DomainObjectNotFoundException {
		CheckerBoard cb = null;
		try {
			cb = IdentityMap.get(id, CheckerBoard.class);
		} catch (final ObjectRemovedException | DomainObjectNotFoundException e) {
			// Do I care if it has been removed?
		}
		if(cb!=null) return cb;
		
		ResultSet rs = CheckerBoardFinder.find(id);
		if(rs.next()) {
			cb = buildCheckerBoard(rs);
			rs.close();
			return cb;
		}
		throw new DomainObjectNotFoundException("Could not create a CheckerBoard with id \""+id+"\"");
	}

	private static CheckerBoard buildCheckerBoard(ResultSet rs) throws SQLException  {
		return CheckerBoardFactory.createClean(rs.getLong("id"),
        		rs.getInt("version"),
        		GameStatus.values()[rs.getInt("status")],
        		rs.getString("pieces"),
        		new PlayerProxy(rs.getLong("first_player")),
        		new PlayerProxy(rs.getLong("second_player")),
        		new PlayerProxy(rs.getLong("current_player"))
        		);
	}
}
