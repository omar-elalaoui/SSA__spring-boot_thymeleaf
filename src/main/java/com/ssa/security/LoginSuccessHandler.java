package com.ssa.security;

import com.ssa.entity.LogConnexion;
import com.ssa.entity.User;
import com.ssa.service.UserService;
import com.ssa.service.impl.LogServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

public final class LoginSuccessHandler implements AuthenticationSuccessHandler {
    @Autowired
    private LogServiceImpl logService;
    @Autowired
    private UserService userService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        User user= userService.findById(userService.getcurrentUsername());
        String userNN= "Admin";
        if(!user.getUsername().equals("admin")) userNN= user.getProfile().getPrenom()+" "+user.getProfile().getNom();
        LogConnexion logConnexion= new LogConnexion(0, new Date(), userNN);
        logService.saveLogConnexion(logConnexion);
        response.sendRedirect("/");
    }
}
