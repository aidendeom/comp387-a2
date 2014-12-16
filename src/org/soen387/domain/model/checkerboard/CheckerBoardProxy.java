package org.soen387.domain.model.checkerboard;

import java.awt.Point;
import java.sql.SQLException;

import org.dsrg.soenea.domain.DomainObjectCreationException;
import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.proxy.DomainObjectProxy;
import org.soen387.domain.model.checkerboard.mapper.CheckerBoardInputMapper;
import org.soen387.domain.model.player.IPlayer;


public class CheckerBoardProxy extends DomainObjectProxy<Long, CheckerBoard>implements ICheckerBoard {
	
	public CheckerBoardProxy(long id) {
		super(id);
	}

	@Override
	public GameStatus getStatus() {
		return getInnerObject().getStatus();
	}

	@Override
	public void setStatus(GameStatus status) {
		getInnerObject().setStatus(status);
	}

	@Override
	public String getPieces() {
		return getInnerObject().getPieces();
	}

	@Override
	public void setPieces(String pieces) {
		getInnerObject().setPieces(pieces);
	}

	@Override
	public IPlayer getFirstPlayer() {
		return getInnerObject().getFirstPlayer();
	}

	@Override
	public void setFirstPlayer(IPlayer firstPlayer) {
		getInnerObject().setFirstPlayer(firstPlayer);
	}

	@Override
	public IPlayer getSecondPlayer() {
		return getInnerObject().getSecondPlayer();
	}

	@Override
	public void setSecondPlayer(IPlayer secondPlayer) {
		getInnerObject().setSecondPlayer(secondPlayer);
	}

	@Override
	public IPlayer getCurrentPlayer() {
		return getInnerObject().getCurrentPlayer();
	}

	@Override
	public void setCurrentPlayer(IPlayer currentPlayer) {
		getInnerObject().setCurrentPlayer(currentPlayer);
	}

	@Override
	public boolean move(Point source, Point target){
		return getInnerObject().move(source, target);
	}

	@Override
	public void jump(Point source, Point... targets) {
		getInnerObject().jump(source, targets);
	}
	
	@Override
	protected CheckerBoard getFromMapper(Long id) throws MapperException,
			DomainObjectCreationException {
		try {
			return CheckerBoardInputMapper.find(id);
		} catch (SQLException e) {
			throw new MapperException(e);
		}
	}

	@Override
	public boolean isWon() {

		return getInnerObject().isWon();
	}

	@Override
	public boolean isTied() {

		return getInnerObject().isTied();
	}

}
