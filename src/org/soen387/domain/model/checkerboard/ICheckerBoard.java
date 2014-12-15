package org.soen387.domain.model.checkerboard;

import java.awt.Point;

import org.dsrg.soenea.domain.interf.IDomainObject;
import org.soen387.domain.model.player.IPlayer;

public interface ICheckerBoard extends IDomainObject<Long> {

	public abstract GameStatus getStatus();

	public abstract void setStatus(GameStatus status);

	public abstract String getPieces();

	public abstract void setPieces(String pieces);

	public abstract IPlayer getFirstPlayer();

	public abstract void setFirstPlayer(IPlayer firstPlayer);

	public abstract IPlayer getSecondPlayer();

	public abstract void setSecondPlayer(IPlayer secondPlayer);

	public abstract IPlayer getCurrentPlayer();

	public abstract void setCurrentPlayer(IPlayer currentPlayer);

	public abstract boolean move(Point source, Point target);

	public abstract void jump(Point source, Point... targets);

}