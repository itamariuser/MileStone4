package data;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

public abstract class CommonSearcher<T> implements Searcher<T> {
	protected Stack<State<T>> openList;//Maybe priority queue?
	private int evaluatedNodes;

	public CommonSearcher() {
		openList = new Stack<State<T>>();
		evaluatedNodes = 0;
	}

	protected State<T> popOpenList() {
	evaluatedNodes++;
	return openList.pop();
	}

	@Override
	public int getNumberOfNodesEvaluated() {
	return evaluatedNodes;
	}

	public Solution search(Searchable<T> s) {
		return null;
	}

}
