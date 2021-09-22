package com.example.springrestvotingsystem.services;

import com.example.springrestvotingsystem.dto.VoterDTO;
import com.example.springrestvotingsystem.entities.Voter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtService {

    @Value("${security.jwt.secret}")
    private byte[] secret;

    @Value("${security.jwt.validity-min}")
    private long validityMin;

    private final ObjectMapper objectMapper;

    public JwtService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public String createToken(Voter voter) {
        Date now = new Date();

        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setIssuer("voting-api")
                .setAudience("voting-ui")
                .setSubject(voter.getUsername())
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + validityMin * 60000))
                .claim("voter", new VoterDTO(voter))
                .signWith(Keys.hmacShaKeyFor(secret), SignatureAlgorithm.HS512)
                .compact();
    }

    public Authentication parseToken(String jwt) throws JsonProcessingException {
        Claims parsedJwtBody;

        try {
            parsedJwtBody = Jwts.parserBuilder()
                    .setSigningKey(secret)
                    .build()
                    .parseClaimsJws(jwt)
                    .getBody();
        } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | SignatureException | IllegalArgumentException e) {
            return null;
        }

        VoterDTO voterDTO = objectMapper.readValue(objectMapper.writeValueAsString(parsedJwtBody.get("voter")), VoterDTO.class);
        Voter voter = new Voter(voterDTO);

        return new UsernamePasswordAuthenticationToken(voter, null, voter.getRoles());
    }
}
