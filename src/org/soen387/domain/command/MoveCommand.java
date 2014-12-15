package org.soen387.domain.command;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import org.dsrg.soenea.domain.command.CommandException;
import org.dsrg.soenea.domain.command.impl.annotation.SetInRequestAttribute;
import org.dsrg.soenea.domain.command.validator.source.IdentityBasedProducer;
import org.dsrg.soenea.domain.command.validator.source.Source;
import org.dsrg.soenea.domain.command.validator.source.impl.AttributeSource;
import org.dsrg.soenea.domain.command.validator.source.impl.ParameterSource;
import org.dsrg.soenea.domain.command.validator.source.impl.PermalinkSource;
import org.dsrg.soenea.domain.helper.Helper;
import org.soen387.domain.command.exception.InvalidMovesException;
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
	
	@Source(sources = {AttributeSource.class, ParameterSource.class})
	public long version;
	
	@Source(sources = {AttributeSource.class, ParameterSource.class})
	public int[] x;
	
	@Source(sources = {AttributeSource.class, ParameterSource.class})
	public int[] y;
	
	@Override
	public void process() throws CommandException {
		try
	    {
			for (Integer xval: x){
				System.out.print(xval + " ");
			}
			System.out.println();
			for (Integer yval: y){
				System.out.print(yval+ " " );
			}
			System.out.println();
			
			System.out.println("Version: " + version);
		
			if (currentPlayer == null){
				throw new NeedToBeLoggedInException();
			}
			
			if (checkerboard.getCurrentPlayer().equals(currentPlayer)){
				throw new ItsNotYourMoveException();
			}
			
			if (x.length != y.length){
				throw new InvalidMovesException();
			}
			
			//get start value out on array [0]
			Point source = new Point(x[0], y[0]);
			List<Point> moves = new ArrayList<Point>();
			moves.add(source);
			
			for (Point point: moves){
				System.out.println(point);
			}
			//get all target positions
			for(int i = 1; i < x.length; i++){
				moves.add(new Point(x[i], y[i]));
			}
			//now call the moves method
			boolean success = false;
			for (int i = 0; i < moves.size()-1; i++){
				success = checkerboard.move(moves.get(i), moves.get(i+1));
			}
			
			if (!success){
				throw new InvalidMovesException();
			}
			
			
			
	    }
		catch (Exception e)
	        {
				throw new CommandException(e);
	        }

	}
}

