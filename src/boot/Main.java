package boot;

import java.util.PriorityQueue;
import java.util.Queue;

import data.Dijkstra;
import data.EightPuzzle;
import data.Searchable;
import data.Searcher;
import data.Solution;
import data.State;

public class Main {

	public static void main(String[] args) {
		Searcher<char[][]> searcher=new Dijkstra<char[][]>();
		// or any Searchable
		char[][] initial={{'8',' ','6'},{'5','4','7'},{'2','3','1'}};
		
		System.out.println("     INITIAL         ");
		for (int yi = 0; yi < initial.length; yi++) {
			for (int xi = 0; xi < initial.length; xi++) {
				System.out.print(initial[yi][xi]);
			}
			System.out.println();
		}
		System.out.println();
		System.out.println();
		char[][] goal={{'1','2','3'},{'4','5','6'},{'7','8',' '}};
		Searchable<char[][]> searchable=new EightPuzzle(initial, goal);
////		Solution<char[][]> solution = searcher.search(searchable);
//		// see the actions
////		for(State<char[][]> a : solution.getPathToVictory())
////			System.out.println(a);
////		System.out.println("# of nodes evaluated " +
////		searcher.getNumberOfNodesEvaluated());
		State<char[][]> s=new State<char[][]>(initial);
		Queue<State<char[][]>> queue=searchable.getAllPossibleStates(s);
		for(int i=searchable.getAllPossibleStates(s).size();i>=0;i--)
		{
			char[][] temp=(char[][])queue.poll().getLayout();
			
			for (int yi = 0; yi < temp.length; yi++) {
				for (int xi = 0; xi < temp.length; xi++) {
					System.out.print(temp[yi][xi]);
				}
				System.out.println();
			}
			System.out.println();
			System.out.println();
		}
		/*for(State<char[][]> s1:searchable.getAllPossibleStates(s))
		{
			char[][] temp=(char[][])s1.getLayout();
			for (int yi = 0; yi < temp.length; yi++) {
				for (int xi = 0; xi < temp.length; xi++) {
					System.out.print(temp[yi][xi]);
				}
				System.out.println();
			}
			System.out.println("************");
					
		}*/
	}

}
