package data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;

public class Dijkstra<T> extends CommonSearcher<T> {
	int numOfEvaluatedNodes;
	PriorityQueue<State<T>> closed;
	HashMap<State<T>,Integer> distances;
	
	public Dijkstra() {
		numOfEvaluatedNodes=0;
		closed= new PriorityQueue<State<T>>();
		distances = new HashMap<State<T>,Integer>();
	}
	@Override
	public Solution<T> search(Searchable<T> s)
	{
		State<T> initialState=s.getInitialState();
		distances.put(initialState, 0);
		openList.add(initialState);
		while(!openList.isEmpty())
		{
			ArrayList<State<T>> neighbors=(ArrayList<State<T>>)s.getAllPossibleStates(openList.pop());
			discoverAndPutInfinity(neighbors);//if visited, what is cost?
			State<T> minState=chooseCheapestState(neighbors,s);
			openList.remove(minState);
			for (State<T> neighborState : neighbors) {
				if(distances.containsKey(minState))
				{
					int alt=distances.get(minState)+ s.getCostBetween(minState, neighborState);
					if(alt<distances.get(neighborState))
					{
						neighborState.setCameFromState(minState);
						distances.put(neighborState, alt);
						neighbors.add(neighborState);
					}
				}
				else
				{
					int alt=s.getCostBetween(minState, neighborState);
					neighborState.setCameFromState(minState);
					distances.put(neighborState, alt);
					neighbors.add(neighborState);
				}
			}
			closed.add(minState);
		}
		return backtrace(s.getGoalState(), s.getInitialState());
	}
	private State<T> chooseCheapestState(List<State<T>> s,Searchable<T> searchable)
	{
		State<T> stateMin=new State<T>();
		for (State<T> state : s) 
		{
			if(distances.containsKey(stateMin))
			{
				if(distances.get(state)<=distances.get(stateMin))
				{
					stateMin=state;
				}
			}
			else 
			{
				stateMin=
			}
		}
		return stateMin;
	}
	public int getNumberOfNodesEvaluated()
	{
		return numOfEvaluatedNodes;
	}
	private void discoverAndPutInfinity(ArrayList<State<T>> neighbors)
	{
		numOfEvaluatedNodes++;
		for (State<T> state : neighbors) {
			System.out.println(state);
			if(!openList.contains(state) && closed.contains(state))
			{
				distances.put(state, Integer.MAX_VALUE);
				openList.add(state);
			}
		}
	}
	@Override
	public Solution<T> backtrace(State<T> goalState, State<T> initialState) {
		ArrayList<State<T>> pathToVictory = new ArrayList<State<T>>();
		State<T> tempState = goalState;
		while( tempState!=initialState) {
			pathToVictory.add(tempState);
			tempState = tempState.getCameFromState();
		}
		return new Solution<>(pathToVictory);
	}
}