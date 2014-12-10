package org.soen387.domain.model.challenge.mapper;

import java.sql.SQLException;

import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.mapper.GenericOutputMapper;
import org.dsrg.soenea.domain.mapper.LostUpdateException;
import org.soen387.domain.model.challenge.Challenge;
import org.soen387.domain.model.challenge.tdg.ChallengeTDG;

public class ChallengeOutputMapper extends GenericOutputMapper<Long, Challenge> {

	@Override
	public void insert(Challenge c) throws MapperException {
		try {
			ChallengeTDG.insert(c.getId(), c.getVersion(), c.getChallenger().getId(), c.getChallengee().getId(), c.getStatus().getId());
		} catch (SQLException e) {
			throw new MapperException(e);
		}
	}

	@Override
	public void update(Challenge c) throws MapperException {
		try {
			int count = ChallengeTDG.update(c.getId(), c.getVersion(), c.getChallenger().getId(), c.getChallengee().getId(), c.getStatus().getId());
			if(count==0) throw new LostUpdateException("Lost Update editing Challenge with id " + c.getId());
			c.setVersion(c.getVersion()+1);
		} catch (SQLException e) {
			throw new MapperException(e);
		}
	}

	@Override
	public void delete(Challenge c) throws MapperException {
		try {
			int count = ChallengeTDG.delete(c.getId(), c.getVersion());
			if(count==0) throw new LostUpdateException("Lost Update deleting Challenge with id " + c.getId());
		} catch (SQLException e) {
			throw new MapperException(e);
		}
	}
	
	

	
}
