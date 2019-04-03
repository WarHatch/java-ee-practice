package rest;

import entities.Creature;
import lombok.val;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@ApplicationScoped
@Path("/creatures")
@Produces(MediaType.APPLICATION_JSON)
public class CreaturesRestService {
    @PersistenceContext
    private EntityManager em;

    @GET
    @Path("/{creatureId}")
    public Creature find(@PathParam("creatureId") int id) {
        return em.find(Creature.class, id);
    }

    @POST
    @Consumes("application/json")
    @Transactional
    @Path("/")
    public String add(final Creature input) {
        System.out.println(input);
        try {
            em.persist(input);
        } catch (Exception e) {
            return e.toString();
        }
        return input.toString();
    }

    @PUT
    @Consumes("application/json")
    @Transactional
    @Path("/update/{id}")
    public String add(@PathParam("id") int id, final Creature input) {
        val dbCreature = em.find(Creature.class, id);
        if (dbCreature == null) {
            throw new IllegalArgumentException("creature id " + id + "not found");
        }
        dbCreature.setName(input.getName());
        em.merge(dbCreature);
        return dbCreature.toString();
    }
}
