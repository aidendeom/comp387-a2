package org.soen387.domain.model.challenge;

import org.dsrg.soenea.domain.DomainObject;
import org.soen387.domain.model.player.IPlayer;

public class Challenge extends DomainObject<Long> implements IChallenge {

	IPlayer challenger;
	IPlayer challengee;
	ChallengeStatus status;

	@Override
	public IPlayer getChallenger() {
		return challenger;
	}

	@Override
	public void setChallenger(IPlayer challenger) {
		this.challenger = challenger;
	}

	@Override
	public IPlayer getChallengee() {
		return challengee;
	}

	@Override
	public void setChallengee(IPlayer challengee) {
		this.challengee = challengee;
	}

	@Override
	public ChallengeStatus getStatus() {
		return status;
	}

	@Override
	public void setStatus(ChallengeStatus status) {
		this.status = status;
	}

	public Challenge(long id, long version, IPlayer challenger,
			IPlayer challengee, ChallengeStatus status) {
		super(id, version);

		this.challenger = challenger;
		this.challengee = challengee;
		this.status = status;
	}
	
	
}
