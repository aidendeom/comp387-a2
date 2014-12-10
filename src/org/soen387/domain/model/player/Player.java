package org.soen387.domain.model.player;

import org.dsrg.soenea.domain.DomainObject;
import org.dsrg.soenea.domain.user.IUser;

public class Player extends DomainObject<Long> implements IPlayer {

	String firstName;
	String lastName;
	String email;
	IUser user;

	@Override
	public String getFirstName() {
		return firstName;
	}

	@Override
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Override
	public String getLastName() {
		return lastName;
	}

	@Override
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String getEmail() {
		return email;
	}

	@Override
	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public IUser getUser() {
		return user;
	}

	@Override
	public void setUser(IUser user) {
		this.user = user;
	}

	public Player(long id, long version, String firstName, String lastName,
			String email, IUser user) {
		super(id, version);
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.user = user;
	}
}
