package org.soen387.domain.command;

import org.dsrg.soenea.domain.command.CommandException;
import org.dsrg.soenea.domain.command.impl.annotation.SetInRequestAttribute;
import org.dsrg.soenea.domain.command.validator.source.IdentityBasedProducer;
import org.dsrg.soenea.domain.command.validator.source.Source;
import org.dsrg.soenea.domain.command.validator.source.impl.PermalinkSource;
import org.dsrg.soenea.domain.helper.Helper;
import org.soen387.domain.model.checkerboard.ICheckerBoard;
import org.soen387.domain.model.checkerboard.mapper.CheckerBoardInputMapper;

public class MoveCommand extends CheckersCommand {

	public MoveCommand(Helper myHelper) {
		super(myHelper);
	}
	
	@SetInRequestAttribute
	@Source(sources=PermalinkSource.class)
	@IdentityBasedProducer(mapper=CheckerBoardInputMapper.class)
	public ICheckerBoard checkerboard;
	
	@Override
	public void process() throws CommandException {
		//All of it done with SoenEA's annotations. How's them beans?
	}

}
