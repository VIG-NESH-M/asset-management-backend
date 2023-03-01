package com.staunch.tech.utils;

import com.staunch.tech.entity.Employee;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Base64;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class JwtUtils {
	private static final String secretKey = "7909cee8-9ccb-11ed-a8fc-0242ac120002";
	Logger logger = LoggerFactory.getLogger(JwtUtils.class);

	/**
	 *
	 * @param employee
	 * @return
	 * @throws SignatureException
	 * @throws MalformedJwtException
	 * @throws ClaimJwtException
	 */
	public static String generateToken(Employee employee) {
		final String authorities = employee.getRoles().stream().collect(Collectors.joining(","));
		;

		return Jwts.builder().setId("AUTH SERVER").setSubject(employee.getName()).setIssuer("Asset Management Server")
				.claim("roles", authorities).claim("taskname", authorities).claim("userId", employee.getId())
				.claim("username", employee.getUsername()).claim("designation", employee.getDesignation())
				.claim("department", employee.getDepartment()).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(30)))
				.signWith(SignatureAlgorithm.HS256, Base64.getEncoder().encode(secretKey.getBytes())).compact();
	}

	/**
	 *
	 * @param token
	 * @return
	 * @throws SignatureException
	 * @throws MalformedJwtException
	 * @throws ClaimJwtException
	 */
	public static Claims getAllClaimsFromToken(String token)
			throws SignatureException, MalformedJwtException, ClaimJwtException {
		Claims claims = Jwts.parser().setSigningKey(Base64.getEncoder().encode(secretKey.getBytes()))
				.parseClaimsJws(token).getBody();
		return claims;
	}

	/**
	 *
	 * @param token
	 * @return
	 * @throws ExpiredJwtException
	 * @throws SignatureException
	 * @throws MalformedJwtException
	 * @throws ClaimJwtException
	 */
	private boolean isTokenExpired(String token)
			throws ExpiredJwtException, SignatureException, MalformedJwtException, ClaimJwtException {
		return this.getAllClaimsFromToken(token).getExpiration().before(new Date());
	}

	/**
	 *
	 * @param token
	 * @return
	 */
	public boolean isInvalid(String token) {
		try {
			boolean checkExpired = this.isTokenExpired(token);
			return checkExpired;
		} catch (SignatureException e) {
			logger.info("Invalid JWT signature.");
			logger.trace("Invalid JWT signature trace: {}", e);
		} catch (MalformedJwtException e) {
			logger.info("Invalid JWT token.");
			logger.trace("Invalid JWT token trace: {}", e);
		} catch (ExpiredJwtException e) {
			logger.info("Expired JWT token.");
			logger.trace("Expired JWT token trace: {}", e);
		} catch (ClaimJwtException e) {
			logger.info("Claiming the JWT token is Failed");
			logger.trace("Claiming JWT token trace: {}", e);
		}
		return true;
	}

}
