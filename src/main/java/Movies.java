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
import domain.Comment;
import domain.Movie;
import domain.MoviesService;
import domain.Rate;
//lista wszystkich filmow w jsonie - link: localhost:8080/RESTfilms/rest/movies
@Path("/movies")
public class Movies {
	private MoviesService db = new MoviesService();
	private static int currentCommentId = 1;
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Movie> getAll(){
		return db.getAll();
	}
	//wyszukiwanie filmu po id - link: localhost:8080/RESTfilms/rest/movies/id
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response get(@PathParam("id") int id){
		Movie result = db.get(id);
		if(result==null) {
			return Response.status(404).build();
		}
		return Response.ok(result).build();
	}
	//dodawanie filmu - link localhost:8008/RESTfilms/rest/movies
	//metoda post, header: content-type :  value: application/json
	// format json
	/*
	 * {
	 * 	"name":"nazwa",
	 * "year" : "rok prod",
	 * "description" : "opis"
	 * }
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response Add(Movie movie) {
		db.add(movie);
		return Response.ok(movie.getId()).build();
	}
	//
	@PUT
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response update(@PathParam("id") int id, Movie m) {
		Movie result = db.get(id);
		if(result==null) {
			return Response.status(404).build();
		}
		m.setId(id);
		db.update(m);
		return Response.ok().build();
	}
	//metoda post: link /rest/movies/id filmu do usuniecia
	// brak headera i formatu
	@DELETE
	@Path("/{id}")
	public Response delete(@PathParam("id") int id) {
		Movie result = db.get(id);
		if(result==null) {
			return Response.status(404).build();
		}
		db.delete(result);
		return Response.ok("Removed " + " move").build();
	}
	
	@GET
	@Path("/{movieId}/comments")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Comment> getMovies(@PathParam("movieId") int movieId){
		Movie result = db.get(movieId);
		if(result==null) {
			return null;
		}
		if(result.getComments()==null) {
			result.setComments(new ArrayList<Comment>());
			
		}
		return result.getComments();
	}
	//wyswietlanie komentarzy dla filmu o określonym id, jezeli nie ma zadnego komentarza, 
	//tworzona i wyswietlana jest pusta lista
	@POST
	@Path("/{id}/comments")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addComment(@PathParam("id") int movieId, Comment comment) {
		Movie result = db.get(movieId);
		if(result==null) {
			return Response.status(404).build();
		}
		if(result.getComments()==null) {
			result.setComments(new ArrayList<Comment>());			
		}
		comment.setId(++currentCommentId);
		result.getComments().add(comment);
		return Response.ok().build();
		
	}
	//usuwanie komentarza dla filmu o okreslonym id, nalezy podac id komentarza
	@DELETE
	@Path("/{id}/comments/{commentId}")
	public Response deleteComment(@PathParam("id") int id,@PathParam("commentId") int commentId) {
		Movie result = db.get(id);
		for(Comment c : result.getComments()){
			if(c.getId()==commentId) {
				result.getComments().remove(c);
				return Response.ok("Removed " + " comment").build();
			}
		}
		if(result==null) {
			return Response.status(404).build();
		}
		
		return Response.ok("Removed " + " comment").build();
	}
	//ocenianie filmu o okreslonym id
	@POST
	@Path("/{id}/rate")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addRate(@PathParam("id") int id,Rate rate) {
		try {
			Movie result = db.get(id);
			if(result==null) {
				return Response.status(404).build();
			}
			
			result.setRate(rate);
		} catch (NullPointerException e ) {
			e.printStackTrace();
			return Response.status(401).build();
		}
		
		return Response.ok().build();
		
	}
	//wyswietlanie oceny filmu i ilosci oddanych glosow, 
	// TO DO - naprawic system oceniania, aktualnie ocena jest zanizana
	@GET
	@Path("/{movieId}/rate")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getRate(@PathParam("movieId") int movieId){
		Movie result = db.get(movieId);
		if(result==null) {
			return null;
		}	
		return Response.ok("oddano glosów"+result.getRateCounter()).build();
	}
	@GET
	@Path("/{movieId}/actors")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Actor> getMovieActors(@PathParam("movieId") int movieId){
		Movie result = db.get(movieId);
		if(result==null) {
			return null;
		}	
		
		return result.getActors();
	}

	
}
