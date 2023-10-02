package com.stackroute.AuthenticationService.util;


import com.stackroute.AuthenticationService.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtUtils {

    private String secret = "testkey";

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, username);
    }

    private String createToken(Map<String, Object> claims, String subject) {

        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(SignatureAlgorithm.HS256, secret).compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    public Map<String, String> newgenerateToken(User user) {
        System.out.println(user);//creates a new HashMap called result, which will be
        // used to store the generated token
        Map<String, String> result = new HashMap<>();//creates a new HashMap called userdata, which will be used to store
        // the user data that will be included in the token.
        Map<String, Object> userdata = new HashMap<>();
        //add properties into userdata
        userdata.put("userEmail", user.getUserEmail());
        userdata.put("userRole", user.getUserRole());



        //The Jwts.builder() method starts building the token
        String myToken = Jwts.builder().setClaims(userdata)
                //setIssuedAt sets the issue time of the token to the current time.
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS512, "secure")
                // used to generate the final token as a compact string.
                .compact();
        result.put("Token", myToken);
        result.put("userEmail", user.getUserEmail());
        result.put("Message", "login successful");
        result.put("userRole", user.getUserRole());


        return result;
    }

}

