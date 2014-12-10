package org.soen387.domain.command;

import java.util.List;

import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.command.CommandException;
import org.dsrg.soenea.domain.command.impl.annotation.SetInRequestAttribute;
import org.dsrg.soenea.domain.helper.Helper;
import org.soen387.domain.model.player.IPlayer;
import org.soen387.domain.model.player.mapper.PlayerInputMapper;

public class ListPlayersCommand extends CheckersCommand {

	public ListPlayersCommand(Helper helper) {
		super(helper);
	}

	@SetInRequestAttribute
	public List<IPlayer> players;
	
	@Override
	public void process() throws CommandException {
		try {
			players = PlayerInputMapper.findAll();
		} catch (MapperException e) {
			throw new CommandException(e);
		}
	}

}
