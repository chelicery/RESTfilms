package domain;

import java.util.ArrayList;
import java.util.Iterator;

public class Movie {
		private String name;
		private String year;
		private Rate rate;
		private static int rateCounter = 1;
		private int id;
		private String description;
		private static double sumOfRates;

		private static ArrayList<Comment> comments = new ArrayList<>();
		private static ArrayList<Actor> actors = new ArrayList<>();
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getYear() {
			return year;
		}
		public void setYear(String year) {
			this.year = year;
		}
		public Rate getRate() {
			return rate;
		}

		public static ArrayList<Comment> getComments() {
			return comments;
		}
		public static void setComments(ArrayList<Comment> comments) {
			Movie.comments = comments;
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}

		public void setRate(Rate rate2) {
			rateCounter++;
			sumOfRates += rate2.getRate();
			this.rate = new Rate(sumOfRates / rateCounter);
				
		}
		
		public int getRateCounter() {
			
			return rateCounter;
		}
		public static ArrayList<Actor> getActors() {
			return actors;
		}
		public static void setActors(ArrayList<Actor> actors) {
			Movie.actors = actors;
		}
		
		

		
}
