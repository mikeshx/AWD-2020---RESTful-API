package org.unnamedgroup.restapi.resources;

import java.net.URI;
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
import org.unnamedgroup.restapi.model.Fattura;
import org.unnamedgroup.restapi.security.Logged;

/**
 *
 * @author Didattica
 */
//risponde ai path .../rest/fatture
@Path("fatture")
public class FattureResource {

//    //soluzione base
//    @GET
//    @Produces("application/json")
//    public List<String> getCollection(
//            @Context UriInfo uriinfo,
//            @QueryParam("from") Integer from,
//            @QueryParam("to") Integer to,
//            @QueryParam("pIVA") Integer partitaIVA) {
//
//        if (from == null) {
//            from = 1;
//        }
//        if (to == null) {
//            to = 7; //per esempio
//        }
//        if (from > to) { //per sicurezza
//            int swap = from;
//            from = to;
//            to = swap;
//        }
//
//        
//        List<String> l = new ArrayList();
//        for (int i = from; i <= to; ++i) {
//            URI uri = uriinfo.getBaseUriBuilder()
//                    .path(getClass())
//                    .path(getClass(), "getItem")
//                    .build(2020, i);
//            l.add(uri.toString());
//        }
//
//        return l;
//    }
    //soluzione alternativa
    @GET
    @Produces("application/json")
    public List<Map<String, Object>> getCollection(
            @Context UriInfo uriinfo,
            @QueryParam("from") Integer from,
            @QueryParam("to") Integer to,
            @QueryParam("pIVA") Integer partitaIVA) {

        if (from == null) {
            from = 1;
        }
        if (to == null) {
            to = 7; //per esempio
        }
        if (from > to) { //per sicurezza
            int swap = from;
            from = to;
            to = swap;
        }

        List<Map<String, Object>> l = new ArrayList();
        for (int i = from; i <= to; ++i) {
            Map<String, Object> e = new HashMap<>();
            e.put("numero", 1);
            e.put("totaleIVAInclusa", 100.25);

            URI uri = uriinfo.getBaseUriBuilder()
                    .path(getClass())
                    .path(getClass(), "getItem")
                    .build(2020, i);
            e.put("url", uri.toString());
            e.put("data", Calendar.getInstance());
            l.add(e);
        }

        return l;
    }
    
    //estensione del path con segmento statico "count"
    @Path("count")
    @GET
    @Produces("application/json")
    public Response getCollectionSize(@QueryParam("pIVA") Integer partitaIVA) {
        int size; //per esempio
        if (partitaIVA != null) {
            size = 3;
        } else {
            size = 12;
        }
        return Response.ok(size).build();
    }

    //estensione del path con anno (parametrico)
    @Logged //protetta da autenticazione
    @Path("{anno: [1-9][0-9][0-9][0-9]}")
    @GET
    @Produces("application/json")
    public Response getCollectionByYear(
            @Context UriInfo uriinfo,
            @PathParam("anno") int anno) {

        List<String> l = new ArrayList();
        for (int i = 1; i <= 3; ++i) {
            URI uri = uriinfo.getBaseUriBuilder()
                    .path(getClass())
                    .path(getClass(), "getItem")
                    .build(anno, i);
            l.add(uri.toString());
        }

        return Response.ok(l).build();
    }

    //estensione del path con anno/numero (parametrici)
    @Path("{anno: [1-9][0-9][0-9][0-9]}/{numero: [0-9]+}")
    public FatturaResource getItem(
            @PathParam("anno") int anno,
            @PathParam("numero") int numero
    ) {
        if (anno == 2020) {
            return new FatturaResource(anno, numero);
        } else {
            //throw new RESTWebApplicationException(404, "Fattura non trovata");
            return null; //ritornare null da un metodo che restituisce una sotto-risorsa equivale a un 404
        }
    }

    @POST
    @Consumes("application/json")
    public Response addItem(@Context UriInfo uriinfo, Fattura f) {
        //...inserire la fattura f nel sistema...
        URI uri = uriinfo.getBaseUriBuilder()
                .path(getClass())
                .path(getClass(), "getItem")
                .build(f.getData().get(Calendar.YEAR), f.getNumero());
        return Response.created(uri).build();
    }
}
