package org.soen387.domain.model.notification.game;

import org.soen387.domain.model.checkerboard.ICheckerBoard;
import org.soen387.domain.model.notification.Notification;
import org.soen387.domain.model.player.IPlayer;

public abstract class GameNotification extends Notification
{
    private ICheckerBoard board;

    public GameNotification(Long id, Long version, IPlayer recipient,
            boolean seen, ICheckerBoard board)
    {
        super(id, version, recipient, seen);
        this.board = board;
    }

    public ICheckerBoard getBoard()
    {
        return board;
    }

    public void setBoard(ICheckerBoard board)
    {
        this.board = board;
    }
}
