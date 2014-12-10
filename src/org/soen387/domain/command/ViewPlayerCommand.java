package org.soen387.domain.command;

import java.util.List;

import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.command.CommandException;
import org.dsrg.soenea.domain.command.impl.annotation.SetInRequestAttribute;
import org.dsrg.soenea.domain.command.validator.source.IdentityBasedProducer;
import org.dsrg.soenea.domain.command.validator.source.Source;
import org.dsrg.soenea.domain.command.validator.source.impl.PermalinkSource;
import org.dsrg.soenea.domain.helper.Helper;
import org.soen387.domain.model.checkerboard.ICheckerBoard;
import org.soen387.domain.model.checkerboard.mapper.CheckerBoardInputMapper;
import org.soen387.domain.model.player.IPlayer;
import org.soen387.domain.model.player.mapper.PlayerInputMapper;

public class ViewPlayerCommand extends CheckersCommand {

	public ViewPlayerCommand(Helper helper) {
		super(helper);
	}

	@SetInRequestAttribute
	@Source(sources=PermalinkSource.class)
	@IdentityBasedProducer(mapper=PlayerInputMapper.class)
	public IPlayer player;
	
	@SetInRequestAttribute
	public List<ICheckerBoard> games;
	
	@Override
	public void process() throws CommandException {
		try {
			games = CheckerBoardInputMapper.find(player);
		} catch (MapperException e) {
			throw new CommandException(e);
		}
	}

}
