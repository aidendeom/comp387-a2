package org.soen387.domain.command.exception;

import org.dsrg.soenea.domain.command.CommandException;

public class CanOnlyWithdrawChallengeIssuedByYouException extends CommandException {
	private static final long serialVersionUID = -4068306236636336491L;

	public CanOnlyWithdrawChallengeIssuedByYouException() {
		super();
	}

	public CanOnlyWithdrawChallengeIssuedByYouException(String message, Throwable cause) {
		super(message, cause);
	}

	public CanOnlyWithdrawChallengeIssuedByYouException(String message) {
		super(message);
	}

	public CanOnlyWithdrawChallengeIssuedByYouException(Throwable cause) {
		super(cause);
	}

}
