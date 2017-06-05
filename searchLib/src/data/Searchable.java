package data;

import java.util.ArrayList;
import java.util.List;

public interface Searchable<T> {
	State<T> getInitialState();
	State<T> getGoalState();
	List<State<T>> getAllPossibleStates(State<T> s);
	int getCostBetween(State<T> src, State<T> dest);
}
