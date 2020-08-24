package org.unnamedgroup.restapi.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import sun.java2d.pipe.SpanShapeRenderer;

import java.io.IOException;
import java.security.Key;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author didattica
 */
@Provider
@Logged
@Priority(Priorities.AUTHENTICATION)
public class LoggedFilter implements ContainerRequestFilter {

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        String token = null;
        //come esempio, proviamo a cercare il token in vari punti, in ordine di priorità
        //in un'applicazione reale, potremmo scegliere una sola modalità

        boolean test = requestContext.getCookies().containsKey("token");
        System.out.println(test);

        String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
                token = authorizationHeader.substring("Bearer".length()).trim();

                /** fuori dai coglioni il controllo sul cookie (di merda)
        } else if (requestContext.getCookies().containsKey("token")) {
            token = requestContext.getCookies().get("token").getValue(); */

        } else if (requestContext.getUriInfo().getQueryParameters().containsKey("token")) {
            token = requestContext.getUriInfo().getQueryParameters().getFirst("token");
        }
        if (token != null && !token.isEmpty()) {
            try {
                //validiamo il token (REAL)
                //String user = validateToken(token);

                // fake
                String user = validateToken();

                if (user != null) {
                    //inseriamo nel contesto i risultati dell'autenticazione
                    //per farli usare dai nostri metodi restful
                    requestContext.setProperty("token", token);
                    requestContext.setProperty("user", user);
                } else {
                    requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
                }
            } catch (Exception e) {
                requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());

            }
        } else {
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
        }
    }

    private String validateToken() {
        return "admin";
    }

    // Verify the token expiration by checking if its date is still valid. Untested.
    private String validateToken_old(String token) throws ParseException {

        Key key = JWTHelpers.getInstance().getJwtKey();
        Jws<Claims> jwsc = Jwts.parser().setSigningKey(key).parseClaimsJws(token);

     // Get the expiration date and check if the token is valid
     Date date = jwsc.getBody().getExpiration();

     SimpleDateFormat formatter = new SimpleDateFormat();
     String strDate = formatter.format(date);

        if (new SimpleDateFormat("MM/yyyy").parse(strDate).before(new Date())) {
            return jwsc.getBody().getIssuer();
        }
        return null;
    }

}

//TODO: ADD DB
