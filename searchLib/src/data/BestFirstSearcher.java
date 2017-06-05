//package data;
//
//import java.util.ArrayList;
//import java.util.HashSet;
//
//public class BestFirstSearcher<T> extends CommonSearcher<T> {
//	
//	HashSet<State<T>> closed;
//
//	public BestFirstSearcher() {
//		closed = new HashSet<State<T>>();
//	}
//
//	@Override
//	public Solution<T> search(Searchable<T> s) {
//		while (!openList.isEmpty()) {
//			State<T> state = openList.poll();
//			closed.add(state);
//			if (state.equals(s.getGoalState())) {
//				return backtrace(s.getGoalState(), s.getInitialState());
//			} else {
//				ArrayList<State<T>> nextStates = s.getAllPossibleStates(state);
//				for (State<T> tempState : nextStates) {
//					if (!closed.contains(tempState) && !openList.contains(tempState)) 
//					{
//						tempState.cameFromState=state;
//						openList.add(tempState);
//					}
//					/*b. Otherwise, if this new path is better than previous one
//					i. If it is not in OPEN add it to OPEN.
//					ii. Otherwise, adjust its priority in OPEN*/
//				}
//			}
//		}
//		return null;
//
//	}
//
//	@Override
//	public Solution<T> backtrace(State<T> goalState, State<T> initialState) {
//		ArrayList<State<T>> pathToVictory = new ArrayList<State<T>>();
//		State<T> tempState = goalState;
//		do {
//			pathToVictory.add(tempState);
//			tempState = tempState.cameFromState;
//		} while (tempState != initialState);
//		return new Solution<>(pathToVictory);
//	}
//}
