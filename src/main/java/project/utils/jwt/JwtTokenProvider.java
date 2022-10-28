package project.utils.jwt;

import java.util.Date;
import javax.servlet.http.HttpServletRequest;

public interface JwtTokenProvider {
	
	public String createJwtToken(String subject, Date issuedAt, Date expiration);
	public Date getTokentIssuedAt(String jwtToken);
	public Date getTokentExpiration(String jwtToken);
	public String getTokenSubject(String jwtToken);
	public boolean validateToken(String jwtToken);
	public String getJwtTokenFromCookie(HttpServletRequest request);

}
