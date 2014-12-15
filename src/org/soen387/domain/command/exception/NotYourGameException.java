package org.soen387.domain.command.exception;

import org.dsrg.soenea.domain.command.CommandException;

public class NotYourGameException extends CommandException
{

    /**
     * 
     */
    private static final long serialVersionUID = 4304545406712998799L;

    public NotYourGameException()
    {
        // TODO Auto-generated constructor stub
    }

    public NotYourGameException(String message)
    {
        super(message);
        // TODO Auto-generated constructor stub
    }

    public NotYourGameException(Throwable cause)
    {
        super(cause);
        // TODO Auto-generated constructor stub
    }

    public NotYourGameException(String message, Throwable cause)
    {
        super(message, cause);
        // TODO Auto-generated constructor stub
    }

}
