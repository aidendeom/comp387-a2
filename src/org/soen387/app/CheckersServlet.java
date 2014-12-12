package org.soen387.app;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dsrg.soenea.application.servlet.DispatcherServlet;
import org.dsrg.soenea.application.servlet.Servlet;
import org.dsrg.soenea.domain.user.User;
import org.dsrg.soenea.domain.user.mapper.UserOutputMapper;
import org.dsrg.soenea.service.MySQLConnectionFactory;
import org.dsrg.soenea.service.registry.Registry;
import org.dsrg.soenea.service.threadLocal.DbRegistry;
import org.dsrg.soenea.uow.MapperFactory;
import org.dsrg.soenea.uow.UoW;
import org.soen387.domain.model.challenge.Challenge;
import org.soen387.domain.model.challenge.mapper.ChallengeOutputMapper;
import org.soen387.domain.model.checkerboard.CheckerBoard;
import org.soen387.domain.model.checkerboard.mapper.CheckerBoardOutputMapper;
import org.soen387.domain.model.notification.challenge.ChallengeNotification;
import org.soen387.domain.model.notification.challenge.mapper.ChallengeNotificationOutputMapper;
import org.soen387.domain.model.notification.game.GameNotification;
import org.soen387.domain.model.notification.game.mapper.GameNotificationOutputMapper;
import org.soen387.domain.model.player.Player;
import org.soen387.domain.model.player.mapper.PlayerOutputMapper;

/**
 * Servlet implementation class CherckersServlet
 */
@WebServlet(name="CheckersServlet",
urlPatterns="/Game/*"
		)
public class CheckersServlet extends DispatcherServlet {
	private static final long serialVersionUID = 1L;
    /**
     * @see Servlet#Servlet()
     */
    public CheckersServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	public static void prepareDbRegistry() {
		MySQLConnectionFactory f = new MySQLConnectionFactory(null, null, null, null);
		try {
			f.defaultInitialization();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		DbRegistry.setConFactory(f);
		String tablePrefix;
		try {
			tablePrefix = Registry.getProperty("mySqlTablePrefix");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			tablePrefix = "";
		}
		if(tablePrefix == null) {
			tablePrefix = "";
		}
		DbRegistry.setTablePrefix(tablePrefix);
	}
	
	
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		prepareDbRegistry();
		setupUoW();
	}
	
	public static void setupUoW() {
		MapperFactory myDomain2MapperMapper = new MapperFactory();
		myDomain2MapperMapper.addMapping(Player.class,PlayerOutputMapper.class);
		myDomain2MapperMapper.addMapping(User.class,UserOutputMapper.class);
		myDomain2MapperMapper.addMapping(CheckerBoard.class,CheckerBoardOutputMapper.class);
		myDomain2MapperMapper.addMapping(Challenge.class,ChallengeOutputMapper.class);
		myDomain2MapperMapper.addMapping(GameNotification.class, GameNotificationOutputMapper.class);
		myDomain2MapperMapper.addMapping(ChallengeNotification.class, ChallengeNotificationOutputMapper.class);
		UoW.initMapperFactory(myDomain2MapperMapper);
	}
	
	@Override
	protected void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.processRequest(request, response);
		//You can't actually put this in post-processrequest... one more reason I should consider explicit filters, if I could order them...
		try {
			DbRegistry.getDbConnection().rollback();
		} catch (SQLException e) {
			//Die silently?
			System.out.println("Was just trying to rollback, this should be a silent death?");
			e.printStackTrace();
		}
	}

}
