package org.soen387.domain.model.checkerboard;

import java.sql.SQLException;

import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.uow.UoW;
import org.soen387.domain.model.checkerboard.tdg.CheckerBoardTDG;
import org.soen387.domain.model.player.IPlayer;

public class CheckerBoardFactory {
	public static final String initialState = "b b b b  b b b bb b b b                  r r r rr r r r  r r r r";  
	
	public static CheckerBoard createNew(IPlayer firstPlayer, IPlayer secondPlayer) throws SQLException, MapperException{
		return createNew(CheckerBoardTDG.getMaxId(), 1, GameStatus.Ongoing, initialState, firstPlayer, secondPlayer, firstPlayer);
	}

	public static CheckerBoard createNew(long id, int version, GameStatus status,
			String pieces, IPlayer firstPlayer, IPlayer secondPlayer,
			IPlayer currentPlayer) throws SQLException, MapperException{
		CheckerBoard cb = new CheckerBoard(id, version, status, pieces, firstPlayer, secondPlayer, currentPlayer);
		UoW.getCurrent().registerNew(cb);
		return cb;
	}
	
	public static CheckerBoard createClean(long id, int version, GameStatus status,
			String pieces, IPlayer firstPlayer, IPlayer secondPlayer,
			IPlayer currentPlayer) {
		CheckerBoard cb = new CheckerBoard(id, version, status, pieces, firstPlayer, secondPlayer, currentPlayer);
		UoW.getCurrent().registerClean(cb);
		return cb;
	}
}