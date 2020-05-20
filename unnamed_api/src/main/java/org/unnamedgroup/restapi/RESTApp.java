package org.unnamedgroup.restapi;


import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import org.unnamedgroup.restapi.jackson.ObjectMapperContextResolver;
import org.unnamedgroup.restapi.resources.FattureResource;
import org.unnamedgroup.restapi.resources.ProdottiResource;
import org.unnamedgroup.restapi.security.AutenticazioneResource;
import org.unnamedgroup.restapi.security.LoggedFilter;

/**
 *
 * @author didattica
 */
@ApplicationPath("rest")
public class RESTApp extends Application {

    private final Set<Class<?>> classes;

    public RESTApp() {
        HashSet<Class<?>> c = new HashSet<>();
        //aggiungiamo tutte le *root resurces* (cioè quelle
        //con l'annotazione Path) che vogliamo pubblicare
        c.add(FattureResource.class);
        c.add(ProdottiResource.class);
        c.add(AutenticazioneResource.class);

        //aggiungiamo il provider Jackson per poter
        //usare i suoi servizi di serializzazione e 
        //deserializzazione JSON
        c.add(JacksonJsonProvider.class);

        //necessario se vogliamo una (de)serializzazione custom di qualche classe    
        c.add(ObjectMapperContextResolver.class);
        //esempio di autoenticazione
        c.add(LoggedFilter.class);
        classes = Collections.unmodifiableSet(c);
    }

    //l'override di questo metodo deve restituire il set
    //di classi che Jersey utilizzerà per pubblicare il
    //servizio. Tutte le altre, anche se annotate, verranno
    //IGNORATE
    @Override
    public Set<Class<?>> getClasses() {
        return classes;
    }
}

