package org.soen387.domain.model.player;

import java.sql.SQLException;

import org.dsrg.soenea.domain.DomainObjectCreationException;
import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.proxy.DomainObjectProxy;
import org.dsrg.soenea.domain.user.IUser;
import org.soen387.domain.model.player.mapper.PlayerInputMapper;

public class PlayerProxy extends DomainObjectProxy<Long, Player>implements IPlayer {
	public PlayerProxy(long id) {
		super(id);
	}

	@Override
	public String getFirstName() {
		return getInnerObject().getFirstName();
	}

	@Override
	public void setFirstName(String firstName) {
		getInnerObject().setFirstName(firstName);
	}

	@Override
	public String getLastName() {
		return getInnerObject().getLastName();
	}

	@Override
	public void setLastName(String lastName) {
		getInnerObject().setLastName(lastName);
	}

	@Override
	public String getEmail() {
		return getInnerObject().getEmail();
	}

	@Override
	public void setEmail(String email) {
		getInnerObject().setEmail(email);
	}

	@Override
	public IUser getUser() {
		return getInnerObject().getUser();
	}

	@Override
	public void setUser(IUser user) {
		getInnerObject().setUser(user);
	}

	@Override
	protected Player getFromMapper(Long id) throws MapperException,
			DomainObjectCreationException {
		try {
			return PlayerInputMapper.find(id);
		} catch (SQLException e) {
			throw new MapperException(e);
		}
	}
	
}
