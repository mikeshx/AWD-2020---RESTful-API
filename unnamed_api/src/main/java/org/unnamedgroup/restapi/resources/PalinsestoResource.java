package org.unnamedgroup.restapi.resources;

import org.unnamedgroup.restapi.model.Palinsesto;
import org.unnamedgroup.restapi.model.PalinsestoCanaliProgrammi;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.sql.Array;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
@Path("palinsesto")
public class PalinsestoResource {
    @Path("{data: [a-z0-9]+}")
    @GET
    @Produces("application/json")
    public Response getPalinsesto(@PathParam("data") String data) throws SQLException, ParseException {
        ArrayList<PalinsestoCanaliProgrammi> pal; //per esempio
        pal = PalinsestoDB.getPalinsestoFromDate(data);
        return Response.ok(pal).build();
    }

    @Path("{canale: [a-z0-9]+}/{data: [a-z0-9]+}")
    @GET
    @Produces("application/json")
    public Response getPalinsestoOfCanale(@PathParam("canale") int canale, @PathParam("data") String data) throws SQLException, ParseException {
        ArrayList<PalinsestoCanaliProgrammi> pal; //per esempio
        pal = PalinsestoDB.getPalinsestoFromDateCanale(canale, data);
        return Response.ok(pal).build();
    }

    @Path("filter/{filter: [a-z0-9]+}/{search: [a-z0-9]+}")
    @GET
    @Produces("application/json")
    public Response getPalinsestoFiltered(@PathParam("filter") String filter, @PathParam("search") String search) throws SQLException, ParseException {
        ArrayList<PalinsestoCanaliProgrammi> pal; //per esempio
        pal = PalinsestoDB.getPalinsestoFiltered(filter, search);
        return Response.ok(pal).build();
    }
}
