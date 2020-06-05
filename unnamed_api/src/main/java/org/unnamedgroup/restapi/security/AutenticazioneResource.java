package org.unnamedgroup.restapi.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.dbutils.DbUtils;
import org.mindrot.jbcrypt.BCrypt;


import java.security.Key;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import javax.crypto.SecretKey;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;

import javax.ws.rs.core.MediaType;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Path("auth")
public class AutenticazioneResource {

    // POST ==> /rest/auth/login?username=admin&password=admin

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)

    public Response doLogin(@Context UriInfo uriinfo,
            //un altro modo per ricevere e iniettare i parametri con JAX-RS...
            @FormParam("username") String username,
            @FormParam("password") String password) {

        try {
            if (authenticate(username, password)) {

                System.out.println("true");
                String authToken = issueToken(uriinfo, username);

                // return the token on response
                return Response.ok(authToken).build();

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

    /* User authentication */
    public static boolean authenticate(String username, String password) throws SQLException {

        Connection dbConnection = DBManager.getDBConenction();
        PreparedStatement pst = null;
        boolean result = false;

        try {

            System.out.println(username);

            pst = dbConnection.prepareStatement("SELECT password FROM login WHERE username = ?");
            pst.setString(1, username);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                //Check if the provided plain text password and the hashed one are equal
                if (BCrypt.checkpw(password, rs.getString("password")))
                    result = true;
            }
        } catch (Exception e) {
                e.printStackTrace();
        } finally {
            DbUtils.close(dbConnection);
        }

        return result;
    }

    // register the token and link it to the user
    private String issueToken(UriInfo context, String username) {

        SecretKey key = JWTHelpers.getInstance().getJwtKey();
        String token = Jwts.builder()
               .setSubject(username)
               .setIssuer(context.getAbsolutePath().toString())
                .setIssuedAt(new Date())
               .setExpiration(Date.from(LocalDateTime.now().plusMinutes(15L).atZone(ZoneId.systemDefault()).toInstant()))
                .signWith(SignatureAlgorithm.HS256, key)
                .compact();
        return token;
    }

    private void revokeToken(String token) {
        /* invalidate il token */
    }
}
