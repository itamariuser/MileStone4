package data;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

public abstract class CommonSearcher<T> implements Searcher<T> {
	protected PriorityQueue<State<T>> openList;//Maybe priority queue?
	private int evaluatedNodes;

	public CommonSearcher() {
		openList = new PriorityQueue<State<T>>(new StateComparator<T>());
		evaluatedNodes = 0;
	}

	protected State<T> pollOpenList() {
	evaluatedNodes++;
	return openList.poll();
	}

	@Override
	public int getNumberOfNodesEvaluated() {
	return evaluatedNodes;
	}

}
