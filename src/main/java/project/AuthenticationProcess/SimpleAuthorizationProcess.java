package project.AuthenticationProcess;

import org.springframework.stereotype.Component;

import project.LoginRequestPayload;

@Component
public class SimpleAuthorizationProcess extends AuthenticationProcessBuilder {

	@Override
	public boolean checkProvidedData(LoginRequestPayload loginRequestPayload) {
		return false;
	}

	@Override
	protected void callBeforeStartProcess() {
		System.out.println("Start simple autentification process");
		
	}

	@Override
	protected void callBeforeEndProcess() {
		System.out.println("End simple autentification process");
		
	}

}
