package org.soen387.domain.model.notification.challenge;

import org.soen387.domain.model.challenge.IChallenge;
import org.soen387.domain.model.player.IPlayer;

public class IssuedNotification extends ChallengeNotification
{
    public IssuedNotification(Long id, Long version, IPlayer recipient,
            boolean seen, IChallenge challenge)
    {
        super(id, version, recipient, seen, challenge);
    }
}
