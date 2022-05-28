package ch.m1m.jukebox;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;
import org.jboss.logging.Logger;

@ApplicationScoped
public class AppLifecycleBean {

    @Inject
    DbSongService dbSongService;

    private static final Logger LOG = Logger.getLogger("ListenerBean");

    void onStart(@Observes StartupEvent ev) {
        LOG.info("The application is starting...");

        //songService.insert(new Song("10", "mc marc", "do it"));
    }

    void onStop(@Observes ShutdownEvent ev) {
        LOG.info("The application is stopping...");
    }
}
