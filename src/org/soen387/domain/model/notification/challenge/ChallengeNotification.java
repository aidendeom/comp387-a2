package org.soen387.domain.model.notification.challenge;

import org.soen387.domain.model.challenge.IChallenge;
import org.soen387.domain.model.notification.Notification;
import org.soen387.domain.model.player.IPlayer;


public class ChallengeNotification extends Notification
{    
    private IChallenge challenge;
    private ChallengeNotifcationType type;
    
    public ChallengeNotification(Long id, Long version, IPlayer recipient,
            boolean seen, IChallenge challenge, ChallengeNotifcationType type)
    {
        super(id, version, recipient, seen);
        this.setChallenge(challenge);
        this.type = type;
    }

    public IChallenge getChallenge()
    {
        return challenge;
    }

    public void setChallenge(IChallenge challenge)
    {
        this.challenge = challenge;
    }

    public ChallengeNotifcationType getType()
    {
        return type;
    }

    public void setType(ChallengeNotifcationType type)
    {
        this.type = type;
    }
}
