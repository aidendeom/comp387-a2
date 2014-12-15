package org.soen387.domain.command.exception;

import org.dsrg.soenea.domain.command.CommandException;

public class ChallengeHasBeenWithdrawnException extends CommandException {

	private static final long serialVersionUID = -4068306676636336221L;

	public ChallengeHasBeenWithdrawnException() {
		super();
	}

	public ChallengeHasBeenWithdrawnException(String message, Throwable cause) {
		super(message, cause);
	}

	public ChallengeHasBeenWithdrawnException(String message) {
		super(message);
	}

	public ChallengeHasBeenWithdrawnException(Throwable cause) {
		super(cause);
	}


}
