package asynchronous;

import entities.Creature;
import org.apache.deltaspike.core.api.future.Futureable;

import javax.ejb.AsyncResult;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.concurrent.Future;

//https://klevas.mif.vu.lt/~vaidasj/tp/skaidres/15-DeltaSpike.pdf

@ApplicationScoped
public class AsyncComponent implements Serializable {

    @PersistenceContext
    @RescueOrAsync // Asinchroninis komponentas negali naudoti @RequestScoped konteksto
    private EntityManager em;

    @Futureable
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    // be šios anotacijos negalėsime gauti @RescueOrAsync EntityManager
    public Future<String> asyncGetCreatureCount() {
        String result;

        System.out.println("AsyncComponent starts counting up creatures...");
        try {
            result = em.createNamedQuery("Creature.count").getSingleResult().toString();
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            result = "An error has occurred while trying to count creatures: " + e.toString();
        }
        System.out.println("AsyncComponent: creature counting completed.");
        return new AsyncResult<>(result);
    }

}
