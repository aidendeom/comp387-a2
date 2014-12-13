package org.soen387.domain.model.notification;

import org.dsrg.soenea.domain.DomainObject;
import org.soen387.domain.model.player.IPlayer;

public abstract class Notification extends DomainObject<Long>
{
    private IPlayer recipient;
    private boolean seen;
    // This is to allow the JSPs to correctly display whether or not it was seen before displaying
    private boolean wasSeen;

    public Notification(Long id, Long version, IPlayer recipient, boolean seen)
    {
        super(id, version);
        this.recipient = recipient;
        this.seen = seen;
        this.wasSeen = seen;
    }
    
    public abstract String getTarget();

    public IPlayer getRecipient()
    {
        return recipient;
    }

    public void setRecipient(IPlayer recipient)
    {
        this.recipient = recipient;
    }

    public boolean isSeen()
    {
        return seen;
    }

    public void setSeen(boolean seen)
    {
        this.seen = seen;
    }

    public boolean isWasSeen()
    {
        return wasSeen;
    }

    public void setWasSeen(boolean wasSeen)
    {
        this.wasSeen = wasSeen;
    }
}
