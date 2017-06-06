package data;

import java.util.Comparator;

public class StateComparator<T> implements Comparator<State<T>> {

	@Override
	public int compare(State<T> o1, State<T> o2) {
		return o1.compareTo(o2);
	}	
}
