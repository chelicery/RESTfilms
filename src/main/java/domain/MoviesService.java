package domain;

import java.util.ArrayList;
import java.util.List;

public class MoviesService implements IService<Movie>{
	private static List<Movie> movies = new ArrayList<>();
	private static int currentId = 1;
	
	public List<Movie> getAll(){
		return movies;
	}
	
	public Movie get(int id) {
		for(Movie m : movies){
			if( m.getId()==id) {
				return m;	
			}
		}
		return null;
	}
	
	public void add(Movie m) {
		m.setId(++currentId);
		movies.add(m);
	}
	
	public void update(Movie movie) {
		for(Movie m : movies) {
			if(m.getId()==movie.getId()) {
				m.setName(movie.getName());
				m.setYear(movie.getYear());
				m.setDescription(movie.getDescription());
			}
		}
	}
	
	public void delete(Movie m) {
		movies.remove(m);
	}

}
