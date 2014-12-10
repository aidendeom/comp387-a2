package org.soen387.domain.command.exception;

import org.dsrg.soenea.domain.command.CommandException;

public class OnlyOneOngoingGameBetweenPlayersException extends CommandException {
	private static final long serialVersionUID = -9114439013335560899L;

	public OnlyOneOngoingGameBetweenPlayersException() {
		super();
	}

	public OnlyOneOngoingGameBetweenPlayersException(String message, Throwable cause) {
		super(message, cause);
	}

	public OnlyOneOngoingGameBetweenPlayersException(String message) {
		super(message);
	}

	public OnlyOneOngoingGameBetweenPlayersException(Throwable cause) {
		super(cause);
	}

}
