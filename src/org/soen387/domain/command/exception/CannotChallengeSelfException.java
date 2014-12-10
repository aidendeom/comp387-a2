package org.soen387.domain.command.exception;

import org.dsrg.soenea.domain.command.CommandException;

public class CannotChallengeSelfException extends CommandException {
	private static final long serialVersionUID = -4696062455077810697L;

	public CannotChallengeSelfException() {
		super();
	}

	public CannotChallengeSelfException(String message, Throwable cause) {
		super(message, cause);
	}

	public CannotChallengeSelfException(String message) {
		super(message);
	}

	public CannotChallengeSelfException(Throwable cause) {
		super(cause);
	}

}
