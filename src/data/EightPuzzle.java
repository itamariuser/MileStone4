package data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class EightPuzzle implements Searchable<char[][]> {
	char[][] layout;//layout[1][0]=second row, first column
	int sizeX; 	//width of layout(3 for array that ends with [2])
	int sizeY;	//length of layout(3 for array that ends with [2])
	char[][] goalLayout;
	
	public EightPuzzle(char[][] layout,char[][] goalLayout) {
		this.layout=layout;
		this.goalLayout=goalLayout;
		this.sizeY=layout.length;
		this.sizeX=layout[0].length;
	}
	@Override
	public State<char[][]> getInitialState() {
		return new State<char[][]>(layout);
	}

	@Override
	public State<char[][]> getGoalState() {
		return new State<char[][]>(goalLayout);
	}

	@Override
	public PriorityQueue<State<char[][]>> getAllPossibleStates(State<char[][]> s) {
		char[][] tempLayout;
		PriorityQueue<State<char[][]>> states = new PriorityQueue<State<char[][]>>(new Comparator<State<char[][]>>() {
			@Override
			public int compare(State<char[][]> o1, State<char[][]> o2) {
				return getCostBetween(s, o1) - getCostBetween(s, o2);
			}
		});
		boolean stop=false;
		for(int yi=0;yi<sizeY && stop!=true ;yi++)		//position y
		{
			for(int xi=0;xi<sizeX && stop!=true ;xi++)	//position x
			{
				if(layout[yi][xi]==' ')
				{
					
//					if(xi+1<sizeX)	//Don't add state if it's outside of bounds
//					{
//						tempLayout=layout.clone();
//						states.add(new State<char[][]>(generateSwappedArr(yi, xi+1, yi, xi, tempLayout))); //swapped with right
//						System.out.println();
//					}
					if(xi-1>=0)			//Don't add state if it's outside of bounds
					{
						tempLayout=layout.clone();
						states.add(new State<char[][]>(generateSwappedArr(yi, xi-1, yi, xi, tempLayout)));//swapped with left
						System.out.println();
					}
					
					if(yi+1<sizeY)		//Don't add state if it's outside of bounds
					{
						tempLayout=layout;
						tempLayout=generateSwappedArr(yi+1, xi, yi, xi, tempLayout);
						
						states.add(new State<char[][]>(tempLayout));//swapped with bottom
						System.out.println();
					}
//					if(yi-1>=0)			//Don't add state if it's outside of bounds
//					{
//						tempLayout=layout;
//						tempLayout=generateSwappedArr(yi-1, xi, yi, xi, tempLayout);
//						
//						states.add(new State<char[][]>(tempLayout));//swapped with top
//					}
					stop=true;
				}
			}
		}
		return states;
	}
	
	private char[][] generateSwappedArr(int yi, int xi, int nyi,int nxi, char[][] layout)
	{
		char[][] tempL=layout;
		char temp=tempL[yi][xi];
		tempL[yi][xi]=tempL[nyi][nxi];
		tempL[nyi][nxi]=temp;
		
		return tempL;
	}

	@Override
	public int getCostBetween(State<char[][]> src, State<char[][]> dest) {
		//convert dest layout to character array
		char[][] destLayout=dest.getLayout();
		Character[][] temp = new Character[sizeY][sizeX];
		for (int yi = 0; yi < destLayout.length; yi++) {//height
			for (int xi = 0; xi < destLayout[0].length; xi++) {//width
				temp[yi][xi]=layout[yi][xi];
			}
		}
		//convert dest layout to ArrayList
		ArrayList<ArrayList<Character>> list = new ArrayList<ArrayList<Character>>();
	    for (Character[] array : temp) {
	    	list.add(new ArrayList<Character>(Arrays.asList(array)));
	    }
	    //search characters in src
	    char[][] srcLayout=src.getLayout();
	    int cost=0;
		for (int yi = 0; yi < srcLayout.length; yi++) {//height
			for (int xi = 0; xi < srcLayout[0].length; xi++) {//width
				cost+=findIndex(list,srcLayout[yi][xi],xi,yi);
			}
		}
		return cost;
	}

	private int findIndex(ArrayList<ArrayList<Character>> list,char c,int x,int y)
	{
		for (ArrayList<Character> arrayList : list) {
			int index=arrayList.indexOf(new Character(c));
			if(index!=-1)
			{
				return distanceBetween(index, x, list.indexOf(arrayList), y);
			}
		}
		return 0;
	}
	
	private int distanceBetween(int x1,int x2,int y1,int y2)
	{
		return (int) Math.sqrt(Math.pow((x1-x2),2)+Math.pow(y1-y2,2));
	}
}
