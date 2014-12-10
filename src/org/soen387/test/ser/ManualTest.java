package org.soen387.test.ser;

import java.lang.annotation.Annotation;

import org.junit.runner.Computer;
import org.junit.runner.Description;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;
import org.soen387.test.Setup;
import org.soen387.test.Teardown;

public class ManualTest {

	/**
	 * 
	 * This has all the default values I use for my application. Fill in FieldMap properly for your application.
	 * If you did it right, all the tests should pass. There's a FieldMap option at the bottom to enable/disable
	 * debug mode, which shows request parameters being sent, as well as responses.
	 * 
	 * All requests default to post, even for read requests. While this is somewhat questionable, adjust your
	 * approach to accomodate it.
	 * 
	 * If you use a front-controller approach and take a parameter for the particular command, include it in the 
	 * url... I think it will work :D If you use some sort of permalink system, email me and we'll sort something out.
	 * 
	 */
	public static void main(String[] args) {

		
		
		/*
		 * 
		 * If you've set up a way to wipe out your database and start fresh, add it here.
		 * By default, I call mine Teardown and Setup, and so I've used those here... but you'll
		 * want to change this to whatever you used. At worst you can comment this out and manually
		 * restart your database before each run of this file, but it's easier to do it programatically.
		 * 
		 */
		try {
		Teardown.main(null);
		Setup.main(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		/*
		 * 
		 * If you want debugging, use debug = true. If you don't set it false.
		 * ...but you need to set it to something so it doesn't crap out on you.
		 * 
		 * The debugging consists of showing you the URL passed, along with the parameters
		 * and then showing you the response. Note that the order of tests may not be as expected for you, 
		 * so look for the stacktrace that is not part of the debugging (but the stacktrace that I print
		 * for the failed tests)
		 * 
		 * All this junk will appear in the console... hopefully you're in eclipse ;)
		 * 
		 */
		
		//FieldMap.current.get().put("DEBUG", "false");
		FieldMap.current.get().put("DEBUG", "true");
		
		
		Computer computer = new Computer();

		JUnitCore jUnitCore = new JUnitCore();
		jUnitCore.addListener(new RunListener(){
			int score = 0;
			int total = 0;
		    @Override public void testFailure(Failure failure){
		    	System.out.println("******TestStatus");
		    	System.out.println("\t"+failure.getTestHeader());
		    	System.out.println("\t"+failure.getMessage());
		    	failure.getException().printStackTrace(System.err);
		    	for(Annotation a: failure.getDescription().getAnnotations()) {
		    		if(a instanceof ScoreAnnotation) {
		    			score -=((ScoreAnnotation)a).value();
		    		}
		    	}
		    }
		    
		    @Override
		    public void testFinished(Description description) throws Exception {
		    	// TODO Auto-generated method stub
		    	super.testFinished(description);

		    	for(Annotation a: description.getAnnotations()) {
		    		
		    		System.out.println(a);
		    		if(a instanceof ScoreAnnotation) {
		    			score +=((ScoreAnnotation)a).value();
		    			total +=((ScoreAnnotation)a).value();
		    		}
		    	}
		    }
		    
		    @Override
		    public void testRunFinished(Result result) throws Exception {
		    	// TODO Auto-generated method stub
		    	super.testRunFinished(result);
		    	System.out.println();
		    	System.out.println("******Final Results");
		    	System.out.println("You scored: " + score + " on " + total);
		    }
		  });
		
		Result r = jUnitCore.run(computer, MarkedTestCheckers.class);
		
		/*
		 * 
		 * Aside from the standard error output, this should list all the tests that failed by name, but the stacktrace above
		 * and possibly the debug code is what's imporant. If you see
		 * Failures:
		 * []
		 * 
		 * Then you're in good shape!
		 * 
		 */
		
		System.out.println();
		System.out.println();
		System.out.println("Failures:");
		System.out.println(r.getFailures());

		
	}

}
