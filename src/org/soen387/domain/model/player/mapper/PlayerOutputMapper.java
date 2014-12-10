package org.soen387.domain.model.player.mapper;

import java.sql.SQLException;

import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.mapper.GenericOutputMapper;
import org.dsrg.soenea.domain.mapper.LostUpdateException;
import org.soen387.domain.model.player.Player;
import org.soen387.domain.model.player.tdg.PlayerTDG;

public class PlayerOutputMapper extends GenericOutputMapper<Long, Player> {

	
	public void insert(Player p) throws MapperException {
		try {
			PlayerTDG.insert(p.getId(), p.getVersion(), p.getFirstName(), p.getLastName(), p.getEmail(), p.getUser().getId());
		} catch (SQLException e) {
			throw new MapperException(e);
		}
	}

	public void update(Player p) throws MapperException, LostUpdateException {
		try {
			int count = PlayerTDG.update(p.getId(), p.getVersion(), p.getFirstName(), p.getLastName(), p.getEmail(), p.getUser().getId());
			if(count==0) throw new LostUpdateException("Lost Update editing player with id " + p.getId());
			p.setVersion(p.getVersion()+1);
		} catch (SQLException e) {
			throw new MapperException(e);
		}
	}
	
	public void delete(Player p) throws MapperException, LostUpdateException {
		try {
			int count = PlayerTDG.delete(p.getId(), p.getVersion());
			if(count==0) throw new LostUpdateException("Lost Update deleting player with id " + p.getId());
		} catch (SQLException e) {
			throw new MapperException(e);
		}
	}
}
