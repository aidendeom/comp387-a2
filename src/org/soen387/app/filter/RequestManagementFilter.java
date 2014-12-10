package org.soen387.app.filter;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import org.dsrg.soenea.domain.mapper.DomainObjectNotFoundException;
import org.dsrg.soenea.service.threadLocal.DbRegistry;
import org.soen387.domain.command.CheckersCommand;
import org.soen387.domain.model.player.mapper.PlayerInputMapper;

/**
 * Servlet Filter implementation class SessionManagementFilter
 */
@WebFilter("/Game/*")
public class RequestManagementFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// Experimenting with using filters for pre and post-process requests
		// Which should happen first? Not sure I know!
		try { // Make sure we've started a transaction
			DbRegistry.getDbConnection().setAutoCommit(false);
		} catch (final SQLException e) {
			// eat the sqlexception, but throw a stacktrace to console
			e.printStackTrace();
		}
		try {
			final Long pid = (Long) ((HttpServletRequest) request).getSession(
					true).getAttribute("playerid");
			System.out.println("You are playing with player: " + pid);
			if (pid != null) {
				request.setAttribute(CheckersCommand.CURRENT_PLAYER_ATTR,
						PlayerInputMapper.find(pid));
			}
		} catch (DomainObjectNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		chain.doFilter(request, response);

	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	@Override
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
