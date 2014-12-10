package org.soen387.domain.model.player;

import java.sql.SQLException;

import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.user.IUser;
import org.dsrg.soenea.uow.UoW;
import org.soen387.domain.model.player.tdg.PlayerTDG;

public class PlayerFactory {

	public static Player createNew(String firstName, String lastName, String email, IUser u) throws SQLException, MapperException{
		return createNew(PlayerTDG.getMaxId(), 1, firstName, lastName, email, u);
	}

	public static Player createNew(long id, long version, String firstName, String lastName, String email, IUser u) throws SQLException, MapperException{
		final Player p = new Player(id, version, firstName, lastName, email, u);
		UoW.getCurrent().registerNew(p);
		return p;
	}
	
	public static Player createClean(long id, long version, String firstName, String lastName, String email, IUser u) {
		Player p = new Player(id, version, firstName, lastName, email, u);
		UoW.getCurrent().registerClean(p);
		return p;
	}
}