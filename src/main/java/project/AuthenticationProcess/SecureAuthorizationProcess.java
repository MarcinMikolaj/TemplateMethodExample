package project.AuthenticationProcess;

import org.springframework.stereotype.Component;

import project.LoginRequestPayload;

@Component
public class SecureAuthorizationProcess extends AuthenticationProcessBuilder {

	@Override
	public boolean checkProvidedData(LoginRequestPayload loginRequestPayload) {
		return false;
	}

	@Override
	protected void callBeforeStartProcess() {
		System.out.println("End secure autentification process");
	}

	@Override
	protected void callBeforeEndProcess() {
		System.out.println("End secure autentification process");
	}
}
