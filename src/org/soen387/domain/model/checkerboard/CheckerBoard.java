package org.soen387.domain.model.checkerboard;

import java.awt.Point;
import java.sql.SQLException;

import org.dsrg.soenea.domain.DomainObject;
import org.dsrg.soenea.domain.MapperException;
import org.soen387.domain.model.notification.game.GameNotificationFactory;
import org.soen387.domain.model.notification.game.GameNotificationType;
import org.soen387.domain.model.player.IPlayer;

public class CheckerBoard extends DomainObject<Long> implements ICheckerBoard {
	
	public CheckerBoard(long id, int version, GameStatus status,
			String pieces, IPlayer firstPlayer, IPlayer secondPlayer,
			IPlayer currentPlayer) {
		super(id, version);
		this.status = status;
		this.pieces = pieces;
		this.firstPlayer = firstPlayer;
		this.secondPlayer = secondPlayer;
		this.currentPlayer = currentPlayer;
	}

	@Override
	public GameStatus getStatus() {
		return status;
	}

	@Override
	public void setStatus(GameStatus status) {
		this.status = status;
	}

	@Override
	public String getPieces() {
		return pieces;
	}

	@Override
	public void setPieces(String pieces) {
		this.pieces = pieces;
	}

	@Override
	public IPlayer getFirstPlayer() {
		return firstPlayer;
	}

	@Override
	public void setFirstPlayer(IPlayer firstPlayer) {
		this.firstPlayer = firstPlayer;
	}

	@Override
	public IPlayer getSecondPlayer() {
		return secondPlayer;
	}

	@Override
	public void setSecondPlayer(IPlayer secondPlayer) {
		this.secondPlayer = secondPlayer;
	}

	@Override
	public IPlayer getCurrentPlayer() {
		return currentPlayer;
	}

	@Override
	public void setCurrentPlayer(IPlayer currentPlayer) {
		this.currentPlayer = currentPlayer;
	}

	GameStatus status;
	String pieces;
	IPlayer firstPlayer;
	IPlayer secondPlayer;
	IPlayer currentPlayer;


	@Override
	public boolean move(Point source, Point target){
		//we don't have the logic, assume the move is successful 
		return true;
	}
	
	@Override
	public void jump(Point source, Point... targets) {
		
	}

	@Override
	public boolean isWon() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isTied() {
		// TODO Auto-generated method stub
		return false;
	}
}
