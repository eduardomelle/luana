package br.com.luanasoares.javachallenge.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenUtil {

    static final String CLAIM_KEY_USERNAME = "sub";

    static final String CLAIM_KEY_ROLE = "role";

    static final String CLAIM_KEY_CREATED = "created";

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;

    public String getUsernameFromToken(String token) {
        String username = getClaimsFromToken(token).getSubject();
        return username;
    }

    public Date getExpirationDateFromToken(String token) {
        Claims claims = getClaimsFromToken(token);
        Date date = claims.getExpiration();
        return date;
    }

    public String refreshToken(String token) {
        Claims claims = getClaimsFromToken(token);
        claims.put(CLAIM_KEY_CREATED, new Date());

        String refreshedToken = generateToken(claims);
        return refreshedToken;
    }

    public boolean validToken(String token) {
        return !expiredToken(token);
    }

    public String getToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_USERNAME, userDetails.getUsername());
        claims.put(CLAIM_KEY_CREATED, new Date());

        userDetails.getAuthorities().forEach(authority -> claims.put(CLAIM_KEY_ROLE, authority.getAuthority()));
        return generateToken(claims);
    }

    public Claims getClaimsFromToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
        return claims;
    }

    private Date getExpiratonDate() {
        return new Date(System.currentTimeMillis() + this.expiration * 1000);
    }

    private boolean expiredToken(String token) {
        Date expiredDate = this.getExpirationDateFromToken(token);
        if (expiredDate == null) {
            return false;
        }

        return expiredDate.before(new Date());
    }

    private String generateToken(Map<String, Object> claims) {
        return Jwts.builder().setClaims(claims).setExpiration(getExpiratonDate()).signWith(SignatureAlgorithm.HS512, this.secret).compact();
    }

}
