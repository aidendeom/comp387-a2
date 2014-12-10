package org.soen387.domain.command;

import java.sql.SQLException;

import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.command.CommandException;
import org.dsrg.soenea.domain.command.impl.annotation.SetInRequestAttribute;
import org.dsrg.soenea.domain.command.validator.source.NoLog;
import org.dsrg.soenea.domain.command.validator.source.Source;
import org.dsrg.soenea.domain.command.validator.source.impl.AttributeSource;
import org.dsrg.soenea.domain.command.validator.source.impl.ParameterSource;
import org.dsrg.soenea.domain.helper.Helper;
import org.dsrg.soenea.domain.mapper.DomainObjectNotFoundException;
import org.dsrg.soenea.domain.user.User;
import org.dsrg.soenea.domain.user.mapper.UserInputMapper;
import org.soen387.domain.command.exception.NoSuchUserException;
import org.soen387.domain.model.player.IPlayer;
import org.soen387.domain.model.player.mapper.PlayerInputMapper;

public class LoginCommand extends CheckersCommand {

	private static final String PLAYER_ID_ATTR = "playerid";

	@Source(sources = {AttributeSource.class, ParameterSource.class})
	public String username;

	@NoLog
	@Source(sources = {AttributeSource.class, ParameterSource.class})
	public String password;

	@SetInRequestAttribute
	public IPlayer player;

	public LoginCommand(Helper helper) {
		super(helper);
	}

	@Override
	public void process() throws CommandException {

		try {
			User u;
			try {
				u = UserInputMapper.find(username, password);
			} catch (final DomainObjectNotFoundException e) {
				throw new NoSuchUserException();
			}
			player = currentPlayer = PlayerInputMapper.find(u);
		} catch (MapperException | SQLException e) {
			// Something bad has happened
			throw new CommandException(e);
		}

		// Security Check!
		helper.invalidateSession();

		// Store PlayerId in sesssion
		helper.setSessionAttribute(PLAYER_ID_ATTR, currentPlayer.getId());
	}

}
