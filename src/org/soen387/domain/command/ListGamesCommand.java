package org.soen387.domain.command;

import java.util.List;

import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.command.CommandException;
import org.dsrg.soenea.domain.command.impl.annotation.SetInRequestAttribute;
import org.dsrg.soenea.domain.helper.Helper;
import org.soen387.domain.model.checkerboard.ICheckerBoard;
import org.soen387.domain.model.checkerboard.mapper.CheckerBoardInputMapper;

public class ListGamesCommand extends CheckersCommand {

	public ListGamesCommand(Helper helper) {
		super(helper);
	}

	@SetInRequestAttribute
	public List<ICheckerBoard> games;
	
	@Override
	public void process() throws CommandException {
		try {
			games = CheckerBoardInputMapper.findAll();
		} catch (MapperException e) {
			throw new CommandException(e);
		}
	}

}
