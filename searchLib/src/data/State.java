package data;

public class State<T> {
	private T layout;
	private State<T> cameFromState;
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
}
