package org.unnamedgroup.restapi.resources;

import org.unnamedgroup.restapi.model.Palinsesto;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
@Path("palinsesto")
public class PalinsestoResource {
    @Path("{data: [a-z0-9]+}")
    @GET
    @Produces("application/json")
    public Response getPalinsesto(@PathParam("data") String data) throws SQLException, ParseException {
        ArrayList<Palinsesto> pal; //per esempio
        pal = PalinsestoDB.getPalinsestoFromDate(data);
        return Response.ok(pal).build();
    }
}
