package org.soen387.domain.model.notification.challenge;

import java.sql.SQLException;

import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.uow.UoW;
import org.soen387.domain.model.challenge.IChallenge;
import org.soen387.domain.model.notification.challenge.tdg.ChallengeNotificationTDG;
import org.soen387.domain.model.player.IPlayer;

public class ChallengeNotificationFactory
{
    public static ChallengeNotification createNew(IPlayer recipient, IChallenge challenge, ChallengeNotifcationType type) throws SQLException, MapperException
    {
        ChallengeNotification n = new ChallengeNotification(ChallengeNotificationTDG.getMaxId(), 1L, recipient, false, challenge, type);
        UoW.getCurrent().registerNew(n);
        return n;
    }
    
    public static ChallengeNotification createClean(long id, long version, IPlayer recipient, boolean seen, IChallenge challenge, ChallengeNotifcationType type)
    {
        ChallengeNotification n = new ChallengeNotification(id, version, recipient, seen, challenge, type);
        UoW.getCurrent().registerClean(n);
        return n;
    }
}
