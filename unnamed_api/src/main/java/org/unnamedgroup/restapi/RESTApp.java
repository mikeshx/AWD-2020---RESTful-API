package org.unnamedgroup.restapi;


import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.unnamedgroup.restapi.resources.CanaliResource;
import org.unnamedgroup.restapi.resources.PalinsestoResource;
import org.unnamedgroup.restapi.resources.ProgrammiResource;
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
        c.add(CanaliResource.class);

        c.add(AutenticazioneResource.class);
        c.add(PalinsestoResource.class);
        c.add(ProgrammiResource.class);


        //aggiungiamo il provider Jackson per poter
        //usare i suoi servizi di serializzazione e 
        //deserializzazione JSON
        c.add(JacksonJsonProvider.class);

        //necessario se vogliamo una (de)serializzazione custom di qualche classe    

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

