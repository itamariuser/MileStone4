package boot;

import data.Dijkstra;
import data.EightPuzzle;

public class Main {

	public static void main(String[] args) {
		char[][] initial={{'8',' ','6'},{'5','4','7'},{'2','3','1'}};
		char[][] goal={{'1','2','3'},{'4','5','6'},{'7','8',' '}};
		EightPuzzle puzzle=new EightPuzzle(initial,goal);
		Dijkstra<char[][]> s=new Dijkstra<char[][]>();
		s.search(puzzle).toString();
	}

}
