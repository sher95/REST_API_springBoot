package com.example.expenseTracker.filters;

import com.example.expenseTracker.constants;
import io.jsonwebtoken.Claims;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.http.HttpStatus;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import io.jsonwebtoken.Jwts;

public class AuthFilter extends GenericFilter {


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpreq = (HttpServletRequest)request;
        HttpServletResponse httpres = (HttpServletResponse) response;
        String authHeader = httpreq.getHeader("Authorization");
        if(authHeader != null){
            String[] authorizationArr = authHeader.split("Bearer");
            if(authorizationArr.length > 1 && authorizationArr[1] != null){
                String token = authorizationArr[1];
                try{
                    Claims claims = OAuth2ResourceServerProperties.Jwts.parser().setSigningKey(constants.API_SECRET_KEY)
                            .parseClaimsJws(token).getBody();
                    httpreq.setAttribute("userId", Integer.parseInt(claims.get("userId").toString()));

                }catch (Exception e){
                    httpres.sendError(HttpStatus.FORBIDDEN.value(), "invalid/expired token");

                }
            }else{
                httpres.sendError(HttpStatus.FORBIDDEN.value(), "Authorization token must be Bearer [token]");
            }

        }else{
            httpres.sendError(HttpStatus.FORBIDDEN.value(), "Authorization token must be provided");
        }
        doFilter(request, response);



    }
}
