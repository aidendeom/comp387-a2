package org.soen387.domain.command;

import java.util.List;

import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.command.CommandException;
import org.dsrg.soenea.domain.command.impl.annotation.SetInRequestAttribute;
import org.dsrg.soenea.domain.helper.Helper;
import org.soen387.domain.model.challenge.IChallenge;
import org.soen387.domain.model.challenge.mapper.ChallengeInputMapper;

public class ListChallengesCommand extends CheckersCommand {

	public ListChallengesCommand(Helper helper) {
		super(helper);
	}

	@SetInRequestAttribute
	public List<IChallenge> challenges;
	
	@Override
	public void process() throws CommandException {
		try {
			challenges = ChallengeInputMapper.findAll();
		} catch (MapperException e) {
			throw new CommandException(e);
		}
	}

}
