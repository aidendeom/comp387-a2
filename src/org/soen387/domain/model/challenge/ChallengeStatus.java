package org.soen387.domain.model.challenge;

import org.dsrg.soenea.domain.producer.IdentityBasedProducer;
import org.dsrg.soenea.domain.producer.IdentityBasedProducerMethod;

public enum ChallengeStatus {
	Open,
	Accepted,
	Refused,
	Withdrawn;
	
	//Let's make it play nice with Javabeans
	public int getId() { return this.ordinal(); }
	
	public static class ChallengeStatusProducer implements IdentityBasedProducer {
		@IdentityBasedProducerMethod
		public static ChallengeStatus get(int ordinal) {
			return ChallengeStatus.values()[ordinal];
		}
	}
}

