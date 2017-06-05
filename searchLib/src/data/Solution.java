package data;

import java.util.ArrayList;

public class Solution<T> {
	ArrayList<State<T>> pathToVictory;

	public ArrayList<State<T>> getPathToVictory() {
		return pathToVictory;
	}

	public void setPathToVictory(ArrayList<State<T>> pathToVictory) {
		this.pathToVictory = pathToVictory;
	}
	
	public Solution(ArrayList<State<T>> pathToVictory) {
		this.pathToVictory=pathToVictory;
	}
	
	@Override
	public String toString() {
		StringBuilder s=new StringBuilder();
		for (State<T> state : pathToVictory) {
			s.append("\n" + state.getLayout());
		}
		return s.toString();
	}
}
