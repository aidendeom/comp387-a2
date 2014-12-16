package org.soen387.domain.command;

import java.util.ArrayList;
import java.util.List;

import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.command.CommandException;
import org.dsrg.soenea.domain.command.impl.annotation.SetInRequestAttribute;
import org.dsrg.soenea.domain.command.validator.source.NotRequired;
import org.dsrg.soenea.domain.command.validator.source.Source;
import org.dsrg.soenea.domain.command.validator.source.impl.PermalinkSource;
import org.dsrg.soenea.domain.helper.Helper;
import org.soen387.domain.model.checkerboard.ICheckerBoard;
import org.soen387.domain.model.checkerboard.mapper.CheckerBoardInputMapper;

public class ListGamesCommand extends CheckersCommand {

	public ListGamesCommand(Helper helper) {
		super(helper);
	}

	@SetInRequestAttribute
	public List<ICheckerBoard> games;
	
	@NotRequired
	@Source(sources={PermalinkSource.class})
	public int p = 1;
	
	@NotRequired
	@Source(sources=PermalinkSource.class)
	public int r = 10;
	
	@Override
	public void process() throws CommandException {
		try
		{
		    System.out.println(p);
		    System.out.println(r);
		    
		    List<ICheckerBoard> l = CheckerBoardInputMapper.findAll();
		    
            int count = l.size();

            if (count > 0)
            {
                r = clamp(r, 1, l.size());
                p = clamp(p, 1, (int) Math.ceil((float) l.size() / r));

                games = new ArrayList<ICheckerBoard>(r);
                int startIdx = (p - 1) * r;
                for (int i = startIdx; i < startIdx + r; i++)
                {
                    games.add(l.get(i));
                }
            }

            helper.setRequestAttribute("page", p);
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
