package org.soen387.domain.command.exception;

import org.dsrg.soenea.domain.command.CommandException;

public class NoSuchUserException extends CommandException {
	private static final long serialVersionUID = -246856619581328417L;

	public NoSuchUserException() {
		super();
	}

	public NoSuchUserException(String message, Throwable cause) {
		super(message, cause);
	}

	public NoSuchUserException(String message) {
		super(message);
	}

	public NoSuchUserException(Throwable cause) {
		super(cause);
	}

}
