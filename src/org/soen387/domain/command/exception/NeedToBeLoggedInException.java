package org.soen387.domain.command.exception;

import org.dsrg.soenea.domain.command.CommandException;

public class NeedToBeLoggedInException extends CommandException {
	private static final long serialVersionUID = -1395648564202426032L;

	public NeedToBeLoggedInException() {
		super();
	}

	public NeedToBeLoggedInException(String message, Throwable cause) {
		super(message, cause);
	}

	public NeedToBeLoggedInException(String message) {
		super(message);
	}

	public NeedToBeLoggedInException(Throwable cause) {
		super(cause);
	}

}
