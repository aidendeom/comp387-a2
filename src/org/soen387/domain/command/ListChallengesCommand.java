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
import org.soen387.domain.model.challenge.IChallenge;
import org.soen387.domain.model.challenge.mapper.ChallengeInputMapper;

public class ListChallengesCommand extends CheckersCommand {

	public ListChallengesCommand(Helper helper) {
		super(helper);
	}

	@SetInRequestAttribute
	public List<IChallenge> challenges;

    @NotRequired
    @Source(sources = { PermalinkSource.class })
    public int p = 1;

    @NotRequired
    @Source(sources = PermalinkSource.class)
    public int r = 10;
	
	@Override
	public void process() throws CommandException {
		try
		{
			List<IChallenge> l = ChallengeInputMapper.findAll();
			
            int count = l.size();

            if (count > 0)
            {
                r = clamp(r, 1, l.size());
                p = clamp(p, 1, (int) Math.ceil((float) l.size() / r));

                challenges = new ArrayList<IChallenge>(r);
                int startIdx = (p - 1) * r;
                for (int i = startIdx; i < startIdx + r; i++)
                {
                    challenges.add(l.get(i));
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
