package resources;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;


/**
 *
 * @author Didattica
 */
//questa è un'altra root resource 
//(registrata quindi anche nella RESTApp)
@Path("prodotti")
public class ProdottiResource {

    @Path("{codice: [a-z0-9]+}/fatture")
    @GET
    @Produces("application/json")
    public Response getFattureForProdotto(
            @Context UriInfo uriinfo,
            @PathParam("codice") String codice) {

        //generiamo materiale di esempio
        List<URI> l = new ArrayList();
        for (int i = 1; i <= 3; ++i) {
            URI uri = uriinfo.getBaseUriBuilder()
                    .path(FattureResource.class)
                    .path(FattureResource.class, "getItem")
                    .build(2020, i);
            l.add(uri);
        }

        return Response.ok(l).build();
    }
}
