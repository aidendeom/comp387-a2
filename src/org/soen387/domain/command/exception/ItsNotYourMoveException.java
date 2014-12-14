package org.soen387.domain.command.exception;

import org.dsrg.soenea.domain.command.CommandException;

public class ItsNotYourMoveException extends CommandException {
	private static final long serialVersionUID = -4696062455077810127L;

	public ItsNotYourMoveException() {
		super();
	}

	public ItsNotYourMoveException(String message, Throwable cause) {
		super(message, cause);
	}

	public ItsNotYourMoveException(String message) {
		super(message);
	}

	public ItsNotYourMoveException(Throwable cause) {
		super(cause);
	}

}
