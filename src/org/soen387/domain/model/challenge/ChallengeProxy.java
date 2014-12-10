package org.soen387.domain.model.challenge;

import java.sql.SQLException;

import org.dsrg.soenea.domain.DomainObjectCreationException;
import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.proxy.DomainObjectProxy;
import org.soen387.domain.model.challenge.mapper.ChallengeInputMapper;
import org.soen387.domain.model.player.IPlayer;

public class ChallengeProxy extends DomainObjectProxy<Long, Challenge> implements IChallenge{

	
	public ChallengeProxy(long id) {
		super(id);
	}

	@Override
	public IPlayer getChallenger() {
		return getInnerObject().getChallenger();
	}

	@Override
	public void setChallenger(IPlayer challenger) {
		getInnerObject().setChallenger(challenger);
		
	}

	@Override
	public IPlayer getChallengee() {
		return getInnerObject().getChallengee();
	}

	@Override
	public void setChallengee(IPlayer challengee) {
		getInnerObject().setChallengee(challengee);
		
	}

	@Override
	public ChallengeStatus getStatus() {
		return getInnerObject().getStatus();
	}

	@Override
	public void setStatus(ChallengeStatus status) {
		getInnerObject().setStatus(status);
	}

	@Override
	protected Challenge getFromMapper(Long id) throws MapperException,
			DomainObjectCreationException {
		try {
			return ChallengeInputMapper.find(id);
		} catch (SQLException e) {
			throw new MapperException(e);
		}
	}

}
