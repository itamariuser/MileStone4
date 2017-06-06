package data;

public class State<T> implements Comparable<State<T>> {
	private T layout;
	private State<T> cameFromState;
	private int  cost;
	public T getLayout() {
		return layout;
	}

	public void setLayout(T layout) {
		this.layout = layout;
	}

	public State<T> getCameFromState() {
		return cameFromState;
	}

	public void setCameFromState(State<T> cameFromState) {
		this.cameFromState = cameFromState;
	}
	
	public State(T layout) {
		this();
		this.layout=layout;
	}
	public State() {
		this.cameFromState=null;
	}
	
	public boolean equals(State<T> s) {
		return layout.equals(s.layout);
	}
	@Override
	public String toString() {
		return layout.toString();
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	@Override
	public int compareTo(State<T> o) {
		return this.getCost()-o.getCost();
	}
}
