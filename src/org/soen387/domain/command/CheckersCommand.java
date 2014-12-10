package org.soen387.domain.command;

import org.dsrg.soenea.domain.command.impl.ValidatorCommand;
import org.dsrg.soenea.domain.command.impl.annotation.SetInRequestAttribute;
import org.dsrg.soenea.domain.command.validator.source.NotRequired;
import org.dsrg.soenea.domain.command.validator.source.Source;
import org.dsrg.soenea.domain.command.validator.source.impl.AttributeSource;
import org.dsrg.soenea.domain.helper.Helper;
import org.soen387.domain.model.player.IPlayer;

public abstract class CheckersCommand extends ValidatorCommand {

	public CheckersCommand(Helper helper) {
		super(helper);
	}

	public static final String CURRENT_PLAYER_ATTR = "currentPlayer";
	
	@Source(sources=AttributeSource.class, keys=CURRENT_PLAYER_ATTR)
	@SetInRequestAttribute
	@NotRequired
	public IPlayer currentPlayer;
	
}
