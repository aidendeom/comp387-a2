package org.soen387.domain.command;

import java.util.ArrayList;
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
		try
		{
		    List<ICheckerBoard> l = CheckerBoardInputMapper.findAll();
		    
            int count = l.size();
            int page = 1;

            if (count > 0)
            {
                int rows = 10;

                if (helper.getAttribute("r") != null)
                    rows = helper.getInt("r");
                if (helper.getAttribute("p") != null)
                    page = helper.getInt("p");

                rows = clamp(rows, 1, l.size());
                page = clamp(page, 1, (int) Math.ceil((float) l.size() / rows));

                games = new ArrayList<ICheckerBoard>(rows);
                int startIdx = (page - 1) * rows;
                for (int i = startIdx; i < startIdx + rows; i++)
                {
                    games.add(l.get(i));
                }
            }

            helper.setRequestAttribute("page", page);
            helper.setRequestAttribute("count", count);
		}
		catch (MapperException e)
		{
			throw new CommandException(e);
		}
	}

    private int clamp(int n, int lower, int upper)
    {
        return Math.max(Math.min(n, upper), lower);
    }
}
