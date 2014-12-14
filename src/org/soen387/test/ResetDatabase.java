package org.soen387.test;

public class ResetDatabase
{
    public static void main(String[] args)
    {
        try
        {
            Teardown.main(null);
            Setup.main(null);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}
