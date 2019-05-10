package com.revature.filter;

import com.revature.models.Principal;
import com.revature.util.JwtConfig;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.apache.log4j.Logger;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/")
public class JwtFilter extends HttpFilter {

    private static final long serialVersionUID = 1L;
    private static Logger log = Logger.getLogger(JwtFilter.class);

    @Override
    public void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain) throws IOException, ServletException {
        log.info("Inside of JwtAuthFilter.doFilter");

        setAccessControlHeaders(resp);

        String header = req.getHeader(JwtConfig.HEADER);

        if (header == null || !header.startsWith(JwtConfig.PREFIX)) {
            log.info("Request originates from unauthenticated origin");
            chain.doFilter(req, resp);
            return;
        }

        String token = header.replaceAll(JwtConfig.PREFIX, "");

        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(JwtConfig.signingKey)
                    .parseClaimsJws(token)
                    .getBody();

            Principal principal = new Principal();
            principal.setUser_id(claims.getId());
            principal.setPassword(claims.get("password", String.class));

            req.setAttribute("principal", principal);
        } catch (Exception e) {
            log.error(e.getMessage());
        }

        chain.doFilter(req, resp);
    }

    private void setAccessControlHeaders(HttpServletResponse resp) {
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, PATCH, DELETE");
        resp.setHeader("Access-Control-Allow-Headers", "Content-type, Authorization, Username, User_ID, Profile_ID");
        resp.setHeader("Access-Control-Expose-Headers", "Authorization, Username, User_ID, Profile_ID, first_name, last_name, alias, slogan, desc, icon_id");
    }
}
