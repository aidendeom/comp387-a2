package org.soen387.domain.model.notification.game.mapper;

import java.sql.SQLException;

import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.mapper.GenericOutputMapper;
import org.dsrg.soenea.domain.mapper.LostUpdateException;
import org.soen387.domain.model.notification.game.GameNotification;
import org.soen387.domain.model.notification.game.tdg.GameNotificationTDG;

public class GameNotificationOutputMapper extends GenericOutputMapper<Long, GameNotification>
{
    @Override
    public void insert(GameNotification n) throws MapperException
    {
        try
        {
            GameNotificationTDG.insert(n.getId(), n.getVersion(), n.getRecipient().getId(), n.isSeen(), n.getBoard().getId(), n.getType().getNumVal());
        }
        catch (SQLException e)
        {
            throw new MapperException(e);
        }
    }

    @Override
    public void update(GameNotification n) throws MapperException, LostUpdateException
    {
        try
        {
            int count = GameNotificationTDG.update(n.getId(), n.getVersion(), n.getRecipient().getId(), n.isSeen(), n.getBoard().getId(), n.getType().getNumVal());
            if (count == 0)
                throw new LostUpdateException("Lost update editing GameNotification with id " + n.getId());
            
            n.setVersion(n.getVersion() + 1);
        }
        catch (SQLException e)
        {
            throw new MapperException(e);
        }
    }

    @Override
    public void delete(GameNotification n) throws MapperException
    {
        try
        {
            int count = GameNotificationTDG.delete(n.getId(), n.getVersion());
            if (count == 0)
                throw new LostUpdateException("Lost update deleting GameNotification with id " + n.getId());
            
            n.setVersion(n.getVersion() + 1);
        }
        catch (SQLException e)
        {
            throw new MapperException(e);
        }
    }
}
