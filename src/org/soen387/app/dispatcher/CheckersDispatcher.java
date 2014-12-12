package org.soen387.app.dispatcher;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dsrg.soenea.application.servlet.dispatcher.Dispatcher;

public abstract class CheckersDispatcher extends Dispatcher {

	boolean isXML = false;
	boolean isSubmission=false;

	@Override
	public void init(HttpServletRequest req, HttpServletResponse res) {
		super.init(req, res);
		if (myHelper.getString("mode").equalsIgnoreCase("xml")) {
			isXML = true;
		}
		// TODO: Remove this!!!!
		//isXML = true;
		isSubmission = !myRequest.getMethod().equalsIgnoreCase("GET");
	}

	@Override
	public void forward(String jsp) throws IOException, ServletException {
	    String location = "/WEB-INF/jsp/" + (isXML ? "xml/" : "html/") + jsp;
		super.forward(location);
	}

	public void fail(Throwable e) throws IOException, ServletException {
		fail("Something bad happened", e);
	}

	public void fail(String errorMessage, Throwable e) throws IOException,
	ServletException {
		final StringBuilder error = new StringBuilder(errorMessage);
		error.append("\n" + e.getMessage());

		final StringWriter sw = new StringWriter();
		final PrintWriter pw = new PrintWriter(sw);
		e.printStackTrace(pw);
		error.append("\n" + sw.toString());

		// In case we want the exception as well
		myRequest.setAttribute("exception", e);
		fail(error.toString());
	}

	public void fail(String errorMessage) throws IOException, ServletException {
		myRequest.setAttribute("errorMessage", errorMessage);
		forward("failure.jsp");
	}
}