package org.soen387.domain.model.notification.challenge;

import org.soen387.domain.model.challenge.IChallenge;
import org.soen387.domain.model.notification.Notification;
import org.soen387.domain.model.player.IPlayer;

public abstract class ChallengeNotification extends Notification
{
    private IChallenge challenge;
    
    public ChallengeNotification(Long id, Long version, IPlayer recipient,
            boolean seen, IChallenge challenge)
    {
        super(id, version, recipient, seen);
        this.setChallenge(challenge);
    }

    public IChallenge getChallenge()
    {
        return challenge;
    }

    public void setChallenge(IChallenge challenge)
    {
        this.challenge = challenge;
    }
}
