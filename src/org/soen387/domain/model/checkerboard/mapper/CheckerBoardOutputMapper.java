package org.soen387.domain.model.checkerboard.mapper;

import java.sql.SQLException;

import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.mapper.GenericOutputMapper;
import org.dsrg.soenea.domain.mapper.LostUpdateException;
import org.soen387.domain.model.checkerboard.CheckerBoard;
import org.soen387.domain.model.checkerboard.tdg.CheckerBoardTDG;

public class CheckerBoardOutputMapper extends GenericOutputMapper<Long, CheckerBoard>{

	
	@Override
	public void insert(CheckerBoard cb) throws MapperException {
		try {
			CheckerBoardTDG.insert(cb.getId(), cb.getVersion(), cb.getStatus().getId(), cb.getPieces(), cb.getFirstPlayer().getId(), cb.getSecondPlayer().getId(), cb.getCurrentPlayer().getId());
		} catch (SQLException e) {
			throw new MapperException(e);
		}
	}

	@Override
	public void update(CheckerBoard cb) throws MapperException {
		try {
			int count = CheckerBoardTDG.update(cb.getId(), cb.getVersion(), cb.getStatus().getId(), cb.getPieces(), cb.getFirstPlayer().getId(), cb.getSecondPlayer().getId(), cb.getCurrentPlayer().getId());
			if(count==0) throw new LostUpdateException("Lost Update editing CheckerBoard with id " + cb.getId());
			cb.setVersion(cb.getVersion()+1);
		} catch (SQLException e) {
			throw new MapperException(e);
		}
	}

	@Override
	public void delete(CheckerBoard cb) throws MapperException {
		try {
			int count = CheckerBoardTDG.delete(cb.getId(), cb.getVersion());
			if(count==0) throw new LostUpdateException("Lost Update deleting CheckerBoard with id " + cb.getId());
		} catch (SQLException e) {
			throw new MapperException(e);
		}
		//
		// What's the process for deleting a CheckerBoard... do we need to delete them?
		// More on that when we discuss referential integrity.
		//
	}
}
