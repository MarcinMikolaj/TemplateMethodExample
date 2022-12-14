package project.utils.jwt;

import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.NoSuchElementException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import java.util.Date;
import java.util.HashMap;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.jsonwebtoken.*;

//This class allows you to generate new JSON Web Token, get Token information like
//expire time or subject and validate the token
@Service
public class JwtTokenProviderImpl implements JwtTokenProvider {
	
	private final String jwtSecretKey = "co3js8cjwh33Su3nx927dns92mvheoskwhdwhwndh3946dhb2w8ck30wh2cbxHh2oShwhsGjrHwoB83w6dhIdbwh3gdw83H63h";
	private final Long expireTime = (long) 1800000; //30min
	private static final Logger logger = LoggerFactory.getLogger(JwtTokenProviderImpl.class);
	
	
	public String createJwtToken(String subject, Date issuedAt, Date expiration) {
		
        Long currentTimeInMili = System.currentTimeMillis();
		
		if(issuedAt == null) {
			issuedAt = new Date(currentTimeInMili);
		}
		
		if(expiration == null) {
			expiration = new Date(currentTimeInMili + expireTime);
		}
		
		// Create token header		
		Map<String, Object> headersForJwtToken = new HashMap<String, Object>();
		headersForJwtToken.put("alg", "HS512");
		headersForJwtToken.put("typ", "JWT");
		
		String token = Jwts.builder()
				.setHeader(headersForJwtToken)
				.setSubject(subject)
				.setIssuedAt(issuedAt)
				.setExpiration(expiration)
				.signWith(SignatureAlgorithm.HS512, jwtSecretKey)
		        .compact();
		
		return token;
		
	}
	
	public Date getTokentIssuedAt(String jwtToken) {
		
		if(validateToken(jwtToken)) {
			return Jwts.parser().setSigningKey(jwtSecretKey).parseClaimsJws(jwtToken).getBody().getIssuedAt();
		}
		
		return null;
	}
	
    public Date getTokentExpiration(String jwtToken) {
		
		if(validateToken(jwtToken)) {
			return Jwts.parser().setSigningKey(jwtSecretKey).parseClaimsJws(jwtToken).getBody().getExpiration();
		}
		
		return null;
	}
	
	
	public String getTokenSubject(String jwtToken) {
		if(validateToken(jwtToken)) {
			return Jwts.parser().setSigningKey(jwtSecretKey).parseClaimsJws(jwtToken).getBody().getSubject();
		}
		
		return null;
	}
	
	public boolean validateToken(String jwtToken) {
		try {
			Jwts.parser().setSigningKey(jwtSecretKey).parseClaimsJws(jwtToken);
			return true;
		} catch (SignatureException ex) {
            logger.error("JwtTokenProvider.validateToken: Invalid JWT signature");
        } catch (MalformedJwtException ex) {
            logger.error("JwtTokenProvider.validateToken: Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            logger.error("JwtTokenProvider.validateToken: Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            logger.error("JwtTokenProvider.validateToken: Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            logger.error("JwtTokenProvider.validateToken: JWT claims string is empty.");
        }
		
		return false;
	}
	
	// Return JSON Web Token from cookie as string using HttpServletRequest.
	// Returns null in the absence of an cooki 'jwt' attribute.
	public String getJwtTokenFromCookie(HttpServletRequest request) {
				
		Cookie[] cookies = request.getCookies();
		String jwtToken = null;
			
		for(Cookie cookie: cookies) {
			
			if(cookie.getName().toString().equals("jwt")) {
				jwtToken = cookie.getValue().toString();
				return jwtToken;
			}
		}
		
		  throw new NoSuchElementException("JwtTokenProviderImpl.getJwtTokenFromCookie: No authorization token in client cooki request.");
	}
		
}
