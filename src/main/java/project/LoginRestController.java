package project;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	public ResponseEntity<String> handleAuthirizationRequest(@RequestBody LoginRequestPayload loginRequestPayload, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse){
		
		if(authenticationProcessBuilder.process(loginRequestPayload, httpServletRequest, httpServletResponse))
			return new ResponseEntity<String>("OK", HttpStatus.OK);
		
		return new ResponseEntity<String>("UNAUTHORIZED", HttpStatus.UNAUTHORIZED);
		
	}

}
