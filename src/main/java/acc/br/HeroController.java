package acc.br;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import acc.br.model.Hero;
import acc.br.service.HeroService;

@Path("/hero")

public class HeroController {
    @Inject HeroService service;
    
    @GET
    public List<Hero> list() {
        return service.getHeros();
    }
    
    @POST
    public Response create(Hero hero) {        
        service.add(hero);
        return Response.ok(hero).status(201).build();
    }
}