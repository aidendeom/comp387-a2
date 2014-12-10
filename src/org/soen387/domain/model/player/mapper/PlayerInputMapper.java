package org.soen387.domain.model.player.mapper;

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
import org.dsrg.soenea.domain.user.IUser;
import org.dsrg.soenea.domain.user.UserProxy;
import org.soen387.domain.model.player.IPlayer;
import org.soen387.domain.model.player.Player;
import org.soen387.domain.model.player.PlayerFactory;
import org.soen387.domain.model.player.tdg.PlayerFinder;

public class PlayerInputMapper implements IdentityBasedProducer{

	@IdentityBasedProducerMethod
	public static Player find(long id) throws SQLException,
			DomainObjectNotFoundException {
		Player p = null;
		try {
			p = IdentityMap.get(id, Player.class);
		} catch (final ObjectRemovedException | DomainObjectNotFoundException e) {
			// Do I care if it has been removed?
		}
		if (p != null)
			return p;

		final ResultSet rs = PlayerFinder.find(id);
		if (rs.next()) {
			p = buildPlayer(rs);
			rs.close();
			return p;
		}
		throw new DomainObjectNotFoundException(
				"Could not create a Player with id \"" + id + "\"");
	}

	public static Player find(IUser u) throws SQLException,
			DomainObjectNotFoundException {
		final ResultSet rs = PlayerFinder.findByUser(u.getId());
		if (rs.next()) {
			final long id = rs.getLong("id");
			Player p = null;
			try {
				p = IdentityMap.get(id, Player.class);
			} catch (final ObjectRemovedException | DomainObjectNotFoundException e) {
				// Do I care if it has been removed?
			}
			if (p != null)
				return p;
			p = buildPlayer(rs);
			rs.close();
			return p;
		}
		throw new DomainObjectNotFoundException(
				"Could not create a Player from User with id \"" + u.getId()
						+ "\"");
	}

	public static List<IPlayer> buildCollection(ResultSet rs)
			throws SQLException, DomainObjectNotFoundException {
		final ArrayList<IPlayer> l = new ArrayList<IPlayer>();
		while (rs.next()) {
			final long id = rs.getLong("id");
			Player p = null;
			try {
				p = IdentityMap.get(id, Player.class);
			} catch (final ObjectRemovedException | DomainObjectNotFoundException e) {
				// Do I care if it has been removed?
			}
			if (p == null) {
				p = buildPlayer(rs);

			}
			l.add(p);
		}
		return l;
	}

	public static List<IPlayer> findAll() throws MapperException {
		try {
			final ResultSet rs = PlayerFinder.findAll();
			return buildCollection(rs);
		} catch (final SQLException e) {
			throw new MapperException(e);
		}
	}

	private static Player buildPlayer(ResultSet rs) throws SQLException {
		return PlayerFactory.createClean(rs.getLong("id"), rs
				.getLong("version"), rs.getString("firstname"), rs
				.getString("lastname"), rs.getString("email"),
				new UserProxy(rs.getLong("user")));
	}

}
