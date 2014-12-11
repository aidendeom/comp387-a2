package org.soen387.domain.model.notification.game;

public enum GameNotificationType
{
    Started(0),
    Turn(1),
    Won(2),
    Loss(3),
    Tied(4),
    Conceded(5);
    
    int numVal;
    
    private GameNotificationType(int numVal)
    {
        this.numVal = numVal;
    }
    
    public int getNumVal()
    {
        return numVal;
    }
    
    public static GameNotificationType fromNumVal(int numVal)
    {
        return GameNotificationType.values()[numVal];
    }
}
