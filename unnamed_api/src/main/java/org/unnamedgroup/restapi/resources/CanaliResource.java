package org.unnamedgroup.restapi.resources;

import java.net.URI;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.unnamedgroup.restapi.security.Logged;

/**
 *
 * @author Didattica
 */
//risponde ai path .../rest/fatture
@Path("canali")
public class CanaliResource {


    //estensione del path con segmento statico "count"
    @Path("count")
    @GET
    @Produces("application/json")
    public Response getNumberOfCanali(@QueryParam("pIVA") Integer partitaIVA) throws SQLException {
        int size; //per esempio
        size = CanaliDB.getNumberOfCanali();
        return Response.ok(size).build();
    }
    }

