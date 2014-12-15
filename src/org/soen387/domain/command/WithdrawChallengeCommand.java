package org.soen387.domain.command;

import java.util.List;

import org.dsrg.soenea.domain.MapperException;
import org.dsrg.soenea.domain.command.CommandException;
import org.dsrg.soenea.domain.command.impl.annotation.SetInRequestAttribute;
import org.dsrg.soenea.domain.command.validator.source.IdentityBasedProducer;
import org.dsrg.soenea.domain.command.validator.source.Source;
import org.dsrg.soenea.domain.command.validator.source.impl.AttributeSource;
import org.dsrg.soenea.domain.command.validator.source.impl.ParameterSource;
import org.dsrg.soenea.domain.command.validator.source.impl.PermalinkSource;
import org.dsrg.soenea.domain.helper.Helper;
import org.dsrg.soenea.uow.UoW;
import org.soen387.domain.command.exception.CanOnlyRespondToOpenChallengesException;
import org.soen387.domain.command.exception.CanOnlyWithdrawChallengeIssuedByYouException;
import org.soen387.domain.command.exception.NeedToBeLoggedInException;
import org.soen387.domain.model.challenge.ChallengeStatus;
import org.soen387.domain.model.challenge.IChallenge;
import org.soen387.domain.model.challenge.mapper.ChallengeInputMapper;
import org.soen387.domain.model.notification.challenge.ChallengeNotification;
import org.soen387.domain.model.notification.challenge.mapper.ChallengeNotificationInputMapper;

public class WithdrawChallengeCommand extends CheckersCommand {

	public WithdrawChallengeCommand(Helper helper) {
		super(helper);
	}
	
	@SetInRequestAttribute
	@Source(sources=PermalinkSource.class)
	@IdentityBasedProducer(mapper=ChallengeInputMapper.class)
	public IChallenge challenge;
	
	@Source(sources = {AttributeSource.class, ParameterSource.class})
	public long version;
	
	@Override
	public void process() throws CommandException {
		try {
			//Validation
			if(currentPlayer==null) {
				throw new NeedToBeLoggedInException();
			}
			
			challenge.setVersion(version);
			
			if(!challenge.getChallenger().equals(currentPlayer)) {
				throw new CanOnlyWithdrawChallengeIssuedByYouException();
			}

			if(!challenge.getStatus().equals(ChallengeStatus.Open)) {
				throw new CanOnlyRespondToOpenChallengesException();
			}
			
			//Do it
			List<ChallengeNotification> challengeNotifications = null;
			
			//set status to "withdrawn" from open?
			challenge.setStatus(ChallengeStatus.Withdrawn);
			
			//delete any unseen challengeIssued notifications
			challengeNotifications = ChallengeNotificationInputMapper.findUnseen(challenge);
			
			for (int i = 0; i < challengeNotifications.size(); i++)
			{
			    ChallengeNotification n = challengeNotifications.get(i);
				UoW.getCurrent().registerRemoved(n);	
			}
			
			UoW.getCurrent().registerDirty(challenge);
			
		} catch (MapperException e) {
			throw new CommandException(e);
		}
	}

}
