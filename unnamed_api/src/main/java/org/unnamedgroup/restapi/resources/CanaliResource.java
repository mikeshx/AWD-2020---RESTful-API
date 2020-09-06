package org.unnamedgroup.restapi.resources;

import java.net.URI;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;
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

import org.unnamedgroup.restapi.model.PalinsestoCanaliProgrammi;
import org.unnamedgroup.restapi.security.Logged;

/**
 *
 * @author Didattica
 */
//risponde ai path .../rest/fatture
@Path("canali")
public class CanaliResource {


    //estensione del path con segmento statico "count"
    @Path("")
    @GET
    @Produces("application/json")
    public Response getCanali() throws SQLException, ParseException  {
        ArrayList<String[]> canali = new ArrayList<>();
        canali = CanaliDB.getCanali();
        return Response.ok(canali).build();
    }
    @Path("{canale: [a-z0-9]+}/palinsesto/{data: [a-z0-9]+}")
    @GET
    @Produces("application/json")
    public Response getPalinsestoOfCanale(@PathParam("canale") int canale, @PathParam("data") String data) throws SQLException, ParseException {
        ArrayList<PalinsestoCanaliProgrammi> pal; //per esempio
        pal = PalinsestoDB.getPalinsestoFromDateCanale(canale, data);
        return Response.ok(pal).build();
    }

    @Path("{canale: [a-z0-9]+}/palinsesto")
    @GET
    @Produces("application/json")
    public Response getPalinsestoOfCanale(@PathParam("canale") int canale) throws SQLException, ParseException {
        ArrayList<PalinsestoCanaliProgrammi> pal; //per esempio
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String strDate = dateFormat.format(date);

        pal = PalinsestoDB.getPalinsestoFromDateCanale(canale, strDate);
        return Response.ok(pal).build();
    }

    }

