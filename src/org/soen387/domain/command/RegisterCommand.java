package org.soen387.domain.command;

import java.util.ArrayList;

import org.dsrg.soenea.domain.command.CommandException;
import org.dsrg.soenea.domain.command.validator.source.NoLog;
import org.dsrg.soenea.domain.command.validator.source.Source;
import org.dsrg.soenea.domain.command.validator.source.impl.ParameterSource;
import org.dsrg.soenea.domain.helper.Helper;
import org.dsrg.soenea.domain.role.IRole;
import org.dsrg.soenea.domain.user.IUser;
import org.dsrg.soenea.domain.user.UserFactory;
import org.soen387.domain.model.player.PlayerFactory;


public class RegisterCommand extends CheckersCommand {
	@Source(sources = ParameterSource.class)
	public String username;

	@NoLog
	@Source(sources = ParameterSource.class)
	public String password;

	@Source(sources = ParameterSource.class)
	public String firstName;
	
	@Source(sources = ParameterSource.class)
	public String lastName;
	
	@Source(sources = ParameterSource.class)
	public String email;

	public RegisterCommand(Helper helper) {
		super(helper);
	}

	@Override
	public void process() throws CommandException {

		try {
			IUser u = UserFactory.createNew(username, password, new ArrayList<IRole>());
			PlayerFactory.createNew(firstName, lastName, email, u);
		} catch (Exception e) {
			// Something bad has happened
			throw new CommandException(e);
		}

	}

}
