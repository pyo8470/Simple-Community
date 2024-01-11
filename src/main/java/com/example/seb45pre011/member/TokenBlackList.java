package com.example.seb45pre011.member;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.*;
@Component
public class TokenBlackList {
    @Value("${jwt.password}")
    private String secretKey;

    @PostConstruct
    protected void init(){
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes(StandardCharsets.UTF_8));
    }


    private Set<String> blacklist = new HashSet<>();
    public void addBlackList(String token){
        blacklist.add(token);
    }

    public boolean isTokenBlacklisted(String token){
        return blacklist.contains(token);
    }

    public void removeExpiredTokens() {
        Set<String> tokensToRemove = new HashSet<>();

        Instant now = Instant.now();

        for (String token : blacklist) {

            String expirationTimeString = decodeTokenAndGetExpirationTime(token);

            if (expirationTimeString != null) {
                Instant expirationTime = Instant.parse(expirationTimeString);

                // 현재 시간과 비교하여 만료된 토큰을 제거합니다.
                if (now.isAfter(expirationTime)) {
                    tokensToRemove.add(token);
                }
            }
        }
        blacklist.removeAll(tokensToRemove);
    }

    public String decodeTokenAndGetExpirationTime(String token) {
        try {
            Jws<Claims> claimsJws = Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token);

            Claims claims = claimsJws.getBody();
            Date expirationTime = claims.getExpiration();
            return expirationTime.toString();
        } catch (Exception e) {

            return null;
        }
    }
}