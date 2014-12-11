package org.soen387.domain.model.notification.game;

import java.sql.SQLException;

import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.uow.UoW;
import org.soen387.domain.model.checkerboard.ICheckerBoard;
import org.soen387.domain.model.notification.game.tdg.GameNotificationTDG;
import org.soen387.domain.model.player.IPlayer;

public class GameNotificationFactory
{
    public static GameNotification createNew(IPlayer recipient, ICheckerBoard board, GameNotificationType type) throws SQLException, MapperException
    {
        GameNotification g = new GameNotification(GameNotificationTDG.getMaxId(), 1L, recipient, false, board, type);
        UoW.getCurrent().registerNew(g);
        return g;
    }
    
    public static GameNotification createClean(long id, long version, IPlayer recipient, boolean seen, ICheckerBoard board, GameNotificationType type)
    {
        GameNotification g = new GameNotification(id, version, recipient, seen, board, type);
        UoW.getCurrent().registerClean(g);
        return g;
    }
}
