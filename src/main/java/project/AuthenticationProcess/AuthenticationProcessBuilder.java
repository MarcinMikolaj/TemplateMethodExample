package project.AuthenticationProcess;

import java.io.IOException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import project.LoginRequestPayload;
import project.utils.cookie.*;
import project.utils.jwt.*;

public abstract class AuthenticationProcessBuilder {
	
	@Autowired
	private JwtTokenProvider jwtTokenProvider;
	
	public boolean process(LoginRequestPayload loginRequestPayload, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		
	
		callBeforeStartProcess();
		
		if(checkProvidedData(loginRequestPayload, httpServletRequest, httpServletResponse)) {
			callBeforeEndProcess();
			return grantAccess(loginRequestPayload, httpServletRequest, httpServletResponse);
		}	
		else {
			callBeforeEndProcess();
			return denyAccess(loginRequestPayload, httpServletRequest, httpServletResponse);
		}
			
	}
	
	protected abstract void callBeforeStartProcess();
	protected abstract boolean checkProvidedData(LoginRequestPayload loginRequestPayload, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse);
	
	private final boolean grantAccess(LoginRequestPayload loginRequestPayload, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		
		String token = jwtTokenProvider.createJwtToken(loginRequestPayload.getEmail(), null, null);
		CookieUtils.addCookie(httpServletResponse, "jwt", token, 600000);
		
		return true;
	};
	
	
	private final boolean denyAccess(LoginRequestPayload loginRequestPayload, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		
		for(Cookie cookie : httpServletRequest.getCookies()) {
            cookie.setMaxAge(0);
        }

		try {
			httpServletResponse.sendRedirect("/");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return false;
	};
	
	protected abstract void callBeforeEndProcess();
	
}
