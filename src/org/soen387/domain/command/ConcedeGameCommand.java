package org.soen387.domain.command;

import java.sql.SQLException;

import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.command.CommandException;
import org.dsrg.soenea.domain.command.impl.annotation.SetInRequestAttribute;
import org.dsrg.soenea.domain.command.validator.source.IdentityBasedProducer;
import org.dsrg.soenea.domain.command.validator.source.Source;
import org.dsrg.soenea.domain.command.validator.source.impl.PermalinkSource;
import org.dsrg.soenea.domain.helper.Helper;
import org.dsrg.soenea.uow.UoW;
import org.soen387.domain.command.exception.CanOnlyConcedeOngoingGameException;
import org.soen387.domain.command.exception.NeedToBeLoggedInException;
import org.soen387.domain.command.exception.NotYourGameException;
import org.soen387.domain.model.checkerboard.GameStatus;
import org.soen387.domain.model.checkerboard.ICheckerBoard;
import org.soen387.domain.model.checkerboard.mapper.CheckerBoardInputMapper;
import org.soen387.domain.model.notification.game.GameNotificationFactory;
import org.soen387.domain.model.notification.game.GameNotificationType;
import org.soen387.domain.model.player.IPlayer;

public class ConcedeGameCommand extends CheckersCommand
{
    public ConcedeGameCommand(Helper helper)
    {
        super(helper);
    }

    @SetInRequestAttribute
    @Source(sources = PermalinkSource.class)
    @IdentityBasedProducer(mapper = CheckerBoardInputMapper.class)
    public ICheckerBoard checkerboard;

    @Override
    public void process() throws CommandException
    {
        try
        {
            if (currentPlayer == null)
                throw new NeedToBeLoggedInException();

            if (!(checkerboard.getFirstPlayer().equals(currentPlayer)
                    || checkerboard.getSecondPlayer().equals(currentPlayer)))
                throw new NotYourGameException();

            if (checkerboard.getStatus() != GameStatus.Ongoing)
                throw new CanOnlyConcedeOngoingGameException();

            IPlayer winner = checkerboard.getFirstPlayer().equals(currentPlayer)
                    ? checkerboard.getSecondPlayer()
                    : checkerboard.getFirstPlayer();
            
            checkerboard.setStatus(GameStatus.Won);
            checkerboard.setCurrentPlayer(winner);
            UoW.getCurrent().registerDirty(checkerboard);
            
            GameNotificationFactory.createNew(winner, checkerboard, GameNotificationType.Conceded);
            GameNotificationFactory.createNew(winner, checkerboard, GameNotificationType.Won);
        }
        catch (MapperException | SQLException e)
        {
            throw new CommandException(e);
        }
    }
}
