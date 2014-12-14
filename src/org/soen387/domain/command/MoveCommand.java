package org.soen387.domain.command;

import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.command.CommandException;
import org.dsrg.soenea.domain.command.impl.annotation.SetInRequestAttribute;
import org.dsrg.soenea.domain.command.validator.source.IdentityBasedProducer;
import org.dsrg.soenea.domain.command.validator.source.Source;
import org.dsrg.soenea.domain.command.validator.source.impl.PermalinkSource;
import org.dsrg.soenea.domain.helper.Helper;
import org.soen387.domain.command.exception.ItsNotYourMoveException;
import org.soen387.domain.command.exception.NeedToBeLoggedInException;
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
	
	@Source(sources=PermalinkSource.class)
	public int startx;
	
	@Source(sources=PermalinkSource.class)
	public int starty;
	
	
	@Override
	public void process() throws CommandException {
		try
	    {
			if (currentPlayer == null)
				throw new NeedToBeLoggedInException();
			
			if (checkerboard.getCurrentPlayer() != currentPlayer){
				throw new ItsNotYourMoveException();
			}
	    }
		catch (MapperException e)
	        {
				throw new CommandException(e);
	        }

	}
}

