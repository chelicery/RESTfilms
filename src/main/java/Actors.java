import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import domain.Actor;
import domain.ActorsService;
import domain.Comment;
import domain.Movie;
import domain.MoviesService;

@Path("/actors")
public class Actors {
	private ActorsService actorsDb = new ActorsService();
	private MoviesService moviesDb = new MoviesService();
	
	//pobranie listy aktorow
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Actor> getAll(){
		return actorsDb.getAll();
	}
	//wyszukiwanie aktora po id - link: localhost:8080/RESTfilms/rest/actors/id
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response get(@PathParam("id") int id){
		Actor result = actorsDb.get(id);
		if(result==null) {
			return Response.status(404).build();
		}
		return Response.ok(result).build();
	}
	//dodawanie aktora - link localhost:8008/RESTfilms/rest/movies
	//metoda post, header: content-type :  value: application/json
	// format json
	/*
	 * {
	 * 	"name":"nazwa",
	 * "surname" : "nazwisko",
	 *
	 * }
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response Add(Actor actor) {
		actorsDb.add(actor);
		return Response.ok(actor.getId()).build();
	}
	//
	
	@PUT
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response update(@PathParam("id") int id, Actor a) {
		Actor result = actorsDb.get(id);
		if(result==null) {
			return Response.status(404).build();
		}
		a.setId(id);
		actorsDb.update(a);
		return Response.ok().build();
	}

	@DELETE
	@Path("/{id}")
	public Response delete(@PathParam("id") int id) {
		Actor result = actorsDb.get(id);
		if(result==null) {
			return Response.status(404).build();
		}
		actorsDb.delete(result);
		return Response.ok("Removed " + " move").build();
	}
	@GET
	@Path("/{id}/movies")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Movie> getMovieActors(@PathParam("actorId") int actorId){
		Actor result = actorsDb.get(actorId);
		if(result==null) {
			return null;
		}	
		
		return result.getActorMovies();
	}
	//metoda post z id aktora i id filmu przypisuje film do aktora
	@POST
	@Path("/{actorId}/{movieId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response setMovie(@PathParam("actorId") int actorId, 
			@PathParam("movieId") int movieId){
		Movie movie = moviesDb.get(movieId);
		
		actorsDb.get(actorId).getActorMovies().add(movie);

		return Response.ok("added " + " actor").build();
	}
	
}
