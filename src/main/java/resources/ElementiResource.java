package resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import model.Fattura;

/**
 *
 * @author Didattica
 */
//essendo solo una sotto-risorsa, non ha un'annotazione @Path e non
//viene registrata nella RESTApp. I path da cui potrà essere attivata
//dipendono quindi solo dalle risorse che la restituiranno 
public class ElementiResource {

    private final Fattura f;

    ElementiResource(Fattura f) {
        this.f = f;
    }

    @GET
    @Produces("application/json")
    public Response getCollection() {
        return Response.ok(f.getElementi()).build();
    }

    @GET
    @Path("{riga: [0-9]+}")
    @Produces("application/json")
    public Response getItem(@PathParam("riga") int riga) {
        if (riga >= 1 && riga <= f.getElementi().size()) {
            return Response.ok(f.getElementi().get(riga-1)).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("La riga non esiste").build();
        }
    }

}
