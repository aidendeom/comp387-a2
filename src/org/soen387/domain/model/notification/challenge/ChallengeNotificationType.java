package org.soen387.domain.model.notification.challenge;

public enum ChallengeNotificationType
{
    Issued(0),
    Accepted(1),
    Refused(2);
    
    private static ChallengeNotificationType[] values = ChallengeNotificationType.values();
    
    private int numVal;
    
    private ChallengeNotificationType(int numVal)
    {
        this.numVal = numVal;
    }
    
    public int getNumVal()
    {
        return numVal;
    }
    
    public String getStringVal()
    {
        return this.toString().toLowerCase();
    }
    
    public static ChallengeNotificationType fromNumVal(int numVal)
    {
        return values[numVal];
    }
}
