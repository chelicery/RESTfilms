package domain;

import java.util.List;

public interface IService<T> {
	    T get(int id);
	    void add(T t);
	    void update(T t);
	    void delete(T t);
	    List<T> getAll();
	}

