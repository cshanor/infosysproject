package com.revature.util;

import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;

public class JwtConfig {

    public static final String URI = "/**";
    public static final String HEADER = "Authorization";
    public static final String PREFIX = "Bearer ";
    public static final int EXPIRATION = 24 * 60 * 60;
    public static final String SECRET = "JwtSecretKey";
    public static final Key signingKey;

    static {
        // Create signing key using the JWT secret key
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        byte[] secretBytes = DatatypeConverter.parseBase64Binary(SECRET);
        signingKey = new SecretKeySpec(secretBytes, signatureAlgorithm.getJcaName());
    }

    private JwtConfig() {}
}
