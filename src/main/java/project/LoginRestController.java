package project;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import project.AuthenticationProcess.AuthenticationProcessBuilder;
import project.AuthenticationProcess.SimpleAuthorizationProcess;

@RestController
public class LoginRestController {
	
	private AuthenticationProcessBuilder authenticationProcessBuilder;
	
	public LoginRestController(SimpleAuthorizationProcess simpleAuthorizationProcess) {
		authenticationProcessBuilder = simpleAuthorizationProcess;
	}
	
	@RequestMapping(path = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<String> handleAuthirizationRequest(@RequestBody LoginRequestPayload loginRequestPayload){
		
		authenticationProcessBuilder.process(loginRequestPayload);
	
		return new ResponseEntity<String>("", HttpStatus.OK);
		
	}

}
