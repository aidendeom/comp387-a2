package org.soen387.domain.model.notification.game;

import org.soen387.domain.model.checkerboard.ICheckerBoard;
import org.soen387.domain.model.notification.Notification;
import org.soen387.domain.model.player.IPlayer;

public class GameNotification extends Notification
{
    private ICheckerBoard board;
    private GameNotificationType type;

    public GameNotification(Long id, Long version, IPlayer recipient,
            boolean seen, ICheckerBoard board, GameNotificationType type)
    {
        super(id, version, recipient, seen);
        this.board = board;
        this.type = type;
    }

    public ICheckerBoard getBoard()
    {
        return board;
    }

    public void setBoard(ICheckerBoard board)
    {
        this.board = board;
    }

    public GameNotificationType getType()
    {
        return type;
    }

    public void setType(GameNotificationType type)
    {
        this.type = type;
    }
}
