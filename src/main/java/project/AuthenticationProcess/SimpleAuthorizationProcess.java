package project.AuthenticationProcess;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import project.LoginRequestPayload;

@Component
public class SimpleAuthorizationProcess extends AuthenticationProcessBuilder {

	@Override
	protected void callBeforeStartProcess() {
		System.out.println("Start simple autentification process");
		
	}

	@Override
	protected void callBeforeEndProcess() {
		System.out.println("End simple autentification process");
		
	}
	
	@Override
	protected boolean checkProvidedData(LoginRequestPayload loginRequestPayload, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		return false;
	}
}
