package org.soen387.domain.command.exception;

import org.dsrg.soenea.domain.command.CommandException;

public class InvalidMovesException extends CommandException {

	private static final long serialVersionUID = -4696062455022810127L;

	public InvalidMovesException() {
		super();
	}

	public InvalidMovesException(String message, Throwable cause) {
		super(message, cause);
	}

	public InvalidMovesException(String message) {
		super(message);
	}

	public InvalidMovesException(Throwable cause) {
		super(cause);
	}
}
