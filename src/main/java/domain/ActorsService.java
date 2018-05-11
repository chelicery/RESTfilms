package domain;
import java.util.ArrayList;
import java.util.List;

public class ActorsService implements IService<Actor>{
		
		private static List<Actor> actors = new ArrayList<>();
		private static int currentId = 1;
		
		public List<Actor> getAll(){
			return actors;
		}
		
		public Actor get(int id) {
			for(Actor a : actors){
				if( a.getId()==id) {
					return a;	
				}
			}
			return null;
		}
		
		public void add(Actor a) {
			a.setId(++currentId);
			actors.add(a);
		}
		
		public void update(Actor actor) {
			for(Actor a : actors) {
				if(a.getId()==actor.getId()) {
					a.setName(actor.getName());
					a.setSurname(actor.getSurname());

				}
			}
		}
		
		public void delete(Actor a) {
			actors.remove(a);
		}
	}

