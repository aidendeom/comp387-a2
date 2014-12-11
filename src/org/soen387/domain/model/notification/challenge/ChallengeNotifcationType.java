package org.soen387.domain.model.notification.challenge;

public enum ChallengeNotifcationType
{
    Issued(0),
    Accepted(1),
    Refused(2);
    
    private int numVal;
    
    private ChallengeNotifcationType(int numVal)
    {
        this.numVal = numVal;
    }
    
    public int getNumVal()
    {
        return numVal;
    }
}
