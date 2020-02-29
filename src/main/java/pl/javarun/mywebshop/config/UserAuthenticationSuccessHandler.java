package pl.javarun.mywebshop.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import pl.javarun.mywebshop.model.User;
import pl.javarun.mywebshop.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author: Maciej Kryger  [https://github.com/maciejkryger]
 * @date : 28.02.2020 19:43
 * *
 * @className: UserAuthenticationSuccessHandler
 * *
 * *
 ******************************************************/
@Component
public class UserAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final UserService userService;
    private final RedirectStrategy redirectStrategy;

    public UserAuthenticationSuccessHandler(UserService userService) {
        this.userService = userService;
        this.redirectStrategy = new DefaultRedirectStrategy();
    }


    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        String username = authentication.getName();
        User user = userService.getUserByUsername(username);

        HttpSession httpSession = httpServletRequest.getSession();
        httpSession.setAttribute("user",user);

        redirectStrategy.sendRedirect(httpServletRequest, httpServletResponse,"/");
    }
}
