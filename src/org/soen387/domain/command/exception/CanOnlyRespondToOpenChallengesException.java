package org.soen387.domain.command.exception;

import org.dsrg.soenea.domain.command.CommandException;

public class CanOnlyRespondToOpenChallengesException extends CommandException {
	private static final long serialVersionUID = -5376483941426670356L;

	public CanOnlyRespondToOpenChallengesException() {
		super();
	}

	public CanOnlyRespondToOpenChallengesException(String message, Throwable cause) {
		super(message, cause);
	}

	public CanOnlyRespondToOpenChallengesException(String message) {
		super(message);
	}

	public CanOnlyRespondToOpenChallengesException(Throwable cause) {
		super(cause);
	}

}
