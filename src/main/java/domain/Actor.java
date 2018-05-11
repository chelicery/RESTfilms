package domain;

import java.util.ArrayList;

public class Actor {
		private int id;
		private String name;
		private String surname;
		private ArrayList<Movie> actorMovies = new ArrayList<>();
		
		
		
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getSurname() {
			return surname;
		}
		public void setSurname(String surname) {
			this.surname = surname;
		}
		public ArrayList<Movie> getActorMovies() {
			return actorMovies;
		}
		public void setActorMovies(ArrayList<Movie> actorMovies) {
			this.actorMovies = actorMovies;
		}
		
		
		
		
}
