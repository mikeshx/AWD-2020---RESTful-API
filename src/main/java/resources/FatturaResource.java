package resources;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;

import model.Fattura;
import model.Prodotto;

/**
 *
 * @author Didattica
 */
//essendo solo una sotto-risorsa, non ha un'annotazione @Path e non
//viene registrata nella RESTApp. I path da cui potrà essere attivata
//dipendono quindi solo dalle risorse che la restituiranno 
//(in questo caso solo FatturaRespource)
public class FatturaResource {

    private final Fattura f;

    FatturaResource(int anno, int numero) {
        //...prelevare la fattura f dal sistema...
        f = new Fattura(); //per esempio
        f.setNumero(numero);
        Calendar data = Calendar.getInstance();
        data.set(Calendar.YEAR, anno);
        f.setData(data);
        f.getIntestatario().setRagioneSociale("Pippo");
        f.getIntestatario().setPartitaIVA("123456789");
        Prodotto p = new Prodotto();
        p.setCodice("XYZ");
        p.setIva((byte) 4);
        f.getElementi().add(p);
    }

    @GET
    @Produces("application/json")
    public Response getItem() {
        try {
            return Response.ok(f)
                    //possiamo aggiungere alla Response vari elementi, ad esempio header...
                    .header("X-fattura-app-version", "1.0")
                    .build();
        } catch (Exception e) {
            //gestione delle eccezioni (business):
            //Modalità 1: creazione response di errore
//            return Response.serverError()
//                    .entity(e.getMessage()) //mai in produzione
//                    .build();
            //Modalità 2: incapsulamento in eccezione JAXRS compatibile
            throw new RESTWebApplicationException(e);
        }
    }

    @Path("pIVA")
    @GET
    @Produces("application/json")
    public Response getItemPIva() {
        String pIVA = f.getIntestatario().getPartitaIVA();
        return Response.ok(pIVA).build();
    }

    @PUT
    @Consumes("application/json")
    public Response updateItem(Fattura f_new) {
        //...aggiornamento della fattura f con f_new...      
        return Response.noContent().build();
    }

    @DELETE
    public Response deleteItem() {
        //...rimozione della fattura f...      
        return Response.noContent().build();
    }

    @Path("elementi")
    public ElementiResource getElementi() {
        //sotto-sotto-risorsa!
        return new ElementiResource(f);
    }

    //esempio di download binario in streaming
    @Path("attachment")
    @GET
    @Produces("application/pdf")
    public Response getAttachment() {
        final byte[] attachment = "Ciao a tutti".getBytes(); //per esempio
        StreamingOutput out = new StreamingOutput() {
            @Override
            public void write(OutputStream output) throws IOException, WebApplicationException {
                output.write(attachment); //esempio banale
                //ad esempio, potrei copiare su output un altro file
            }
        };
        return Response
                .ok(out)
                .header("content-disposition", "attachment; filename=fattura.pdf")
                .build();
    }
}
