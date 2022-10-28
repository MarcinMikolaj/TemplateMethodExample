package project;

// The class represents the payload of the query from the client during the authorization attempt.
public final class LoginRequestPayload {
	
	private final String email;
	private final String password;
	
	public LoginRequestPayload(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	@Override
	public String toString() {
		return "LoginRequestPayload [email=" + email + ", password=" + password + "]";
	}
	
}
