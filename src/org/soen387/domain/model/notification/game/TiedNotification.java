package org.soen387.domain.model.notification.game;

import org.soen387.domain.model.checkerboard.ICheckerBoard;
import org.soen387.domain.model.player.IPlayer;

public class TiedNotification extends GameNotification
{
    public TiedNotification(Long id, Long version, IPlayer recipient,
            boolean seen, ICheckerBoard board)
    {
        super(id, version, recipient, seen, board);
    }
}
