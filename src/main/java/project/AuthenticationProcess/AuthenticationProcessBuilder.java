package project.AuthenticationProcess;

import project.LoginRequestPayload;
import project.LoginResponsePayload;

public abstract class AuthenticationProcessBuilder {
	
	public LoginResponsePayload process(LoginRequestPayload loginRequestPayload) {
		
		
		callBeforeStartProcess();
		
		if(checkProvidedData(loginRequestPayload)) {
			callBeforeEndProcess();
			return grantAccess();
		}	
		else {
			callBeforeEndProcess();
			return denyAccess();
		}
			
	}
	
	protected abstract void callBeforeStartProcess();
	protected abstract boolean checkProvidedData(LoginRequestPayload loginRequestPayload);
	
	private final LoginResponsePayload grantAccess() {
		System.out.println("Grant Access");
		return null;
	};
	
	
	private final LoginResponsePayload denyAccess() {
		System.out.println("Deny Access");
		return null;
	};
	
	protected abstract void callBeforeEndProcess();
	
}
