package org.soen387.domain.model.notification.challenge;

import org.soen387.domain.model.challenge.IChallenge;
import org.soen387.domain.model.notification.Notification;
import org.soen387.domain.model.player.IPlayer;


public class ChallengeNotification extends Notification
{    
    private IChallenge challenge;
    private ChallengeNotificationType type;
    
    public ChallengeNotification(Long id, Long version, IPlayer recipient,
            boolean seen, IChallenge challenge, ChallengeNotificationType type)
    {
        super(id, version, recipient, seen);
        this.setChallenge(challenge);
        this.type = type;
    }
    
    @Override
    public String getTarget()
    {
        return "challenge=\"" + challenge.getId() + "\"";
    };

    public IChallenge getChallenge()
    {
        return challenge;
    }

    public void setChallenge(IChallenge challenge)
    {
        this.challenge = challenge;
    }

    public ChallengeNotificationType getType()
    {
        return type;
    }

    public void setType(ChallengeNotificationType type)
    {
        this.type = type;
    }
}
