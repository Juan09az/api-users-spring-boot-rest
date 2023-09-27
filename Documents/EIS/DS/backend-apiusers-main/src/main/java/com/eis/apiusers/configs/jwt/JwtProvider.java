package com.eis.apiusers.configs.jwt;
import com.eis.apiusers.dto.JwtTokenDto;
import com.eis.apiusers.entities.UserEntity;
import com.nimbusds.jwt.JWT;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.JWTParser;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;

import org.springframework.stereotype.Component;

import java.security.Key;
import java.text.ParseException;
import java.util.Date;
import java.util.List;


@Component
public class JwtProvider {

    private static final Logger logger = LoggerFactory.getLogger(JwtProvider.class);

    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expiration}")
    private int expiration;
    public String generateToken(Authentication authentication){
        UserEntity userPrincipal = (UserEntity) authentication.getPrincipal();
        return Jwts.builder().signWith(getKey(secret)).setSubject(userPrincipal.getUsername())
                .setIssuedAt(new Date()).setExpiration(new Date(new Date().getTime() + expiration * 1000))
                .claim("en el madrid esta","vinicius junior ")
                .compact();
    }
    public String getUsernameFromToken(String token){
        return Jwts.parserBuilder().setSigningKey(getKey(secret)).build().parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(getKey(secret)).build().parseClaimsJws(token).getBody();
            return true;
        } catch (ExpiredJwtException e) {
            logger.error("Expired token");
        } catch (UnsupportedJwtException e) {
            logger.error("Unsupported token");
        } catch (MalformedJwtException e) {
            logger.error("Malformed token");
        } catch (SignatureException e) {
            logger.error("BadSignature token");
        } catch (IllegalArgumentException e) {
            logger.error("IllegalArgument token");
        } catch (Exception e){
            logger.error("Fail token");
        }
        return false;
    }
    private Key getKey(String secret){
        byte []secretBytes = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(secretBytes);
    }
    public String refreshToken(JwtTokenDto jwtDto) throws ParseException {
        try {
            Jwts.parserBuilder().setSigningKey(getKey(secret)).build().parseClaimsJws(jwtDto.getToken());
        } catch (ExpiredJwtException e) {
            JWT jwt = JWTParser.parse(jwtDto.getToken());
            JWTClaimsSet claims = jwt.getJWTClaimsSet();
            String email = claims.getSubject();
            return Jwts.builder()
                    .setSubject(email)
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(new Date().getTime() + expiration * 1000))
                    .signWith(getKey(secret))
                    .claim("en el madrid esta","vinicius junior ")
                    .compact();
        }
        return null;
    }
}
