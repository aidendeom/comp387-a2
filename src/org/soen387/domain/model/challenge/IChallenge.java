package org.soen387.domain.model.challenge;

import org.dsrg.soenea.domain.interf.IDomainObject;
import org.soen387.domain.model.player.IPlayer;

public interface IChallenge extends IDomainObject<Long> {

	public abstract IPlayer getChallenger();

	public abstract void setChallenger(IPlayer challenger);

	public abstract IPlayer getChallengee();

	public abstract void setChallengee(IPlayer challengee);

	public abstract ChallengeStatus getStatus();

	public abstract void setStatus(ChallengeStatus status);
}