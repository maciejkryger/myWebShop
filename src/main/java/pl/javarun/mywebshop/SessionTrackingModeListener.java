package pl.javarun.mywebshop;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.SessionTrackingMode;
import java.util.EnumSet;

public class SessionTrackingModeListener implements ServletContextListener {


    @Override
    public void contextDestroyed(ServletContextEvent event) {
        // Do nothing
    }

    @Override
    public void contextInitialized(ServletContextEvent event) {
        ServletContext context = event.getServletContext();
        EnumSet<SessionTrackingMode> modes =
                EnumSet.of(SessionTrackingMode.SSL);

        context.setSessionTrackingModes(modes);
    }

}
