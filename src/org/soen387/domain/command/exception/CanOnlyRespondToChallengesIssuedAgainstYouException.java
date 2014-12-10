package org.soen387.domain.command.exception;

import org.dsrg.soenea.domain.command.CommandException;

public class CanOnlyRespondToChallengesIssuedAgainstYouException extends CommandException {
	private static final long serialVersionUID = -4068306676636336491L;

	public CanOnlyRespondToChallengesIssuedAgainstYouException() {
		super();
	}

	public CanOnlyRespondToChallengesIssuedAgainstYouException(String message, Throwable cause) {
		super(message, cause);
	}

	public CanOnlyRespondToChallengesIssuedAgainstYouException(String message) {
		super(message);
	}

	public CanOnlyRespondToChallengesIssuedAgainstYouException(Throwable cause) {
		super(cause);
	}

}
