package org.soen387.domain.command.exception;

import org.dsrg.soenea.domain.command.CommandException;

public class CanOnlyConcedeOngoingGameException extends CommandException
{

    /**
     * 
     */
    private static final long serialVersionUID = -8093610921889794675L;

    public CanOnlyConcedeOngoingGameException()
    {
        // TODO Auto-generated constructor stub
    }

    public CanOnlyConcedeOngoingGameException(String message)
    {
        super(message);
        // TODO Auto-generated constructor stub
    }

    public CanOnlyConcedeOngoingGameException(Throwable cause)
    {
        super(cause);
        // TODO Auto-generated constructor stub
    }

    public CanOnlyConcedeOngoingGameException(String message, Throwable cause)
    {
        super(message, cause);
        // TODO Auto-generated constructor stub
    }

}
