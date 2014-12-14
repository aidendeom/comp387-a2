package org.soen387.domain.model.notification.challenge.mapper;

import java.sql.SQLException;

import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.mapper.GenericOutputMapper;
import org.dsrg.soenea.domain.mapper.LostUpdateException;
import org.soen387.domain.model.notification.challenge.ChallengeNotification;
import org.soen387.domain.model.notification.challenge.tdg.ChallengeNotificationTDG;

public class ChallengeNotificationOutputMapper extends GenericOutputMapper<Long, ChallengeNotification>
{

    @Override
    public void insert(ChallengeNotification c) throws MapperException
    {
        try
        {
            ChallengeNotificationTDG.insert(c.getId(), c.getVersion(), c.getRecipient().getId(), c.isSeen(), c.getChallenge().getId(), c.getType().getNumVal());
        }
        catch (SQLException e)
        {
            throw new MapperException(e);
        }
    }

    @Override
    public void update(ChallengeNotification c) throws MapperException, LostUpdateException
    {
        try
        {
            int count = ChallengeNotificationTDG.update(c.getId(), c.getVersion(), c.getRecipient().getId(), c.isSeen(), c.getChallenge().getId(), c.getType().getNumVal());
            if (count == 0)
                throw new LostUpdateException("Lost update editing ChallengeNotification with id " + c.getId());
            
            c.setVersion(c.getVersion() + 1);
        }
        catch (SQLException e)
        {
            throw new MapperException(e);
        }
    }

    @Override
    public void delete(ChallengeNotification c) throws MapperException, LostUpdateException
    {
        try
        {
            int count = ChallengeNotificationTDG.delete(c.getId(), c.getVersion());
            if (count == 0)
                throw new LostUpdateException("Lost update deleting ChallengeNotification with id " + c.getId());
            
            c.setVersion(c.getVersion() + 1);
        }
        catch (SQLException e)
        {
            throw new MapperException(e);
        }
    }

}
