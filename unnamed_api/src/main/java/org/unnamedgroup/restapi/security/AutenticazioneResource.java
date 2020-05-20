package org.unnamedgroup.restapi.security;

import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author didattica /rest/auth
 *
 */
@Path("auth")
public class AutenticazioneResource {

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response doLogin(@Context UriInfo uriinfo,
            //un altro modo per ricevere e iniettare i parametri con JAX-RS...
            @FormParam("username") String username,
            @FormParam("password") String password) {
        try {
            if (authenticate(username, password)) {
                /* per esempio */
                String authToken = issueToken(uriinfo, username);

                //return Response.ok(authToken).build();
                //return Response.ok().cookie(new NewCookie("token", authToken)).build();
                //return Response.ok().header(HttpHeaders.AUTHORIZATION, "Bearer " + authToken).build();
                //Restituiamolo in tutte le modalità, giusto per fare un esempio..
                return Response.ok(authToken)
                        .cookie(new NewCookie("token", authToken))
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + authToken).build();
            } else {
                return Response.status(Response.Status.UNAUTHORIZED).build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }

    @Logged
    @DELETE
    @Path("/logout")
    public Response doLogout(@Context HttpServletRequest request) {
        try {
            //estraiamo i dati inseriti dal nostro LoggedFilter...
            String token = (String) request.getAttribute("token");
            if (token != null) {
                revokeToken(token);
            }
            return Response.noContent().build();
        } catch (Exception e) {
            return Response.serverError().build();
        }
    }

    private boolean authenticate(String username, String password) {
        /* autenticare! */
        return true;
    }

    private String issueToken(UriInfo context, String username) {
        /* registrare il token e associarlo all'utenza */
        String token = username + UUID.randomUUID().toString();
        /* per esempio */

//        JWT        
//        Key key = JWTHelpers.getInstance().getJwtKey();
//        String token = Jwts.builder()
//                .setSubject(username)
//                .setIssuer(context.getAbsolutePath().toString())
//                .setIssuedAt(new Date())
//                .setExpiration(Date.from(LocalDateTime.now().plusMinutes(15L).atZone(ZoneId.systemDefault()).toInstant()))
//                .signWith(key)
//                .compact();
        return token;
    }

    private void revokeToken(String token) {
        /* invalidate il token */
    }
}
