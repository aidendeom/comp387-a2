package org.soen387.domain.command.exception;

import org.dsrg.soenea.domain.command.CommandException;

public class OnlyOneOpenChallengeBetweenPlayersException extends CommandException {
	private static final long serialVersionUID = -4696062455077810697L;

	public OnlyOneOpenChallengeBetweenPlayersException() {
		super();
	}

	public OnlyOneOpenChallengeBetweenPlayersException(String message, Throwable cause) {
		super(message, cause);
	}

	public OnlyOneOpenChallengeBetweenPlayersException(String message) {
		super(message);
	}

	public OnlyOneOpenChallengeBetweenPlayersException(Throwable cause) {
		super(cause);
	}

}
