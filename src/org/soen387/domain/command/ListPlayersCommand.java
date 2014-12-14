package org.soen387.domain.command;

import java.util.ArrayList;
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
		try
		{		    
			List<IPlayer> l = PlayerInputMapper.findAll();
			
			int count = l.size();
			int page = 1;
			
			if (count > 0)
			{
			    int rows = 10;
			    
                if (helper.getInt("r") != null)
                    rows = helper.getInt("r");
                if (helper.getInt("p") != null)
                    page = helper.getInt("p");
                
                rows = clamp(rows, 1, l.size());
                page = clamp(page, 1, (int)Math.ceil((float)l.size() / rows));
                
                players = new ArrayList<IPlayer>(rows);
                int startIdx = (page - 1) * rows;
                for (int i = startIdx; i < startIdx + rows; i++)
                {
                    players.add(l.get(i));
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
