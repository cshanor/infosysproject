package com.revature.util;


import com.revature.models.User;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.log4j.Logger;

import java.util.Date;

public class JwtGenerator {

    private static Logger log = Logger.getLogger(JwtGenerator.class);

    public static String createJwt(User subject) {
        log.info("Creating new JWT for: " + subject.getUsername());

        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();

        JwtBuilder builder = Jwts.builder()
                .setId(Integer.toString(subject.getUser_id()))
                .setSubject(subject.getUsername())
                .setIssuer("eldingar_airlines")
                .claim("password", subject.getPassword())
                .setExpiration(new Date(nowMillis + JwtConfig.EXPIRATION * 1000))
                .signWith(signatureAlgorithm, JwtConfig.signingKey);

        log.info("JWT successfully created");

        return builder.compact();
    }

    private JwtGenerator() {
        super();
    }
}
