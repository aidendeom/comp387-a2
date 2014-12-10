package org.soen387.domain.model.challenge;

import java.sql.SQLException;

import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.uow.UoW;
import org.soen387.domain.model.challenge.tdg.ChallengeTDG;
import org.soen387.domain.model.player.IPlayer;

public class ChallengeFactory {
	public static Challenge createNew(IPlayer challenger,
			IPlayer challengee) throws SQLException, MapperException{
		return createNew(ChallengeTDG.getMaxId(), 1, challenger, challengee, ChallengeStatus.Open);
	}

	public static  Challenge createNew(long id, long version, IPlayer challenger,
			IPlayer challengee, ChallengeStatus status) throws SQLException, MapperException{
		Challenge c = new Challenge(id, version, challenger, challengee, status);
		UoW.getCurrent().registerNew(c);
		return c;
	}
	
	public static  Challenge createClean(long id, int version, IPlayer challenger,
			IPlayer challengee, ChallengeStatus status) {
		Challenge c = new Challenge(id, version, challenger, challengee, status);
		UoW.getCurrent().registerClean(c);
		return c;
	}
}