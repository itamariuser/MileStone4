package data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
	public List<State<char[][]>> getAllPossibleStates(State<char[][]> s) {
		char[][] tempLayout;
		ArrayList<State<char[][]>> states=new ArrayList<State<char[][]>>();
		boolean stop=false;
		for(int yi=0;yi<sizeY && stop!=true ;yi++)		//position y
		{
			for(int xi=0;xi<sizeX && stop!=true ;xi++)	//position x
			{
				if(layout[yi][xi]==' ')
				{
					stop=true;
					if(xi+1<sizeX)	//Don't add state if it's outside of bounds
					{
						tempLayout=layout;
						//swap
						tempLayout[yi][xi]=tempLayout[yi][xi+1];
						tempLayout[yi][xi+1]=' ';
						
						states.add(new State<char[][]>(tempLayout)); //swapped with right
					}
					if(xi-1>=0)			//Don't add state if it's outside of bounds
					{
						tempLayout=layout;
						//swap
						tempLayout[yi][xi]=layout[yi][xi-1];
						tempLayout[yi][xi-1]=' ';
						
						states.add(new State<char[][]>(tempLayout));//swapped with left
					}
					
					if(yi+1<sizeY)		//Don't add state if it's outside of bounds
					{
						tempLayout=layout;
						//swap
						tempLayout[yi][xi]=layout[yi+1][xi];
						tempLayout[yi+1][xi]=' ';
						
						states.add(new State<char[][]>(tempLayout));//swapped with bottom
					}
					if(yi-1>=0)			//Don't add state if it's outside of bounds
					{
						tempLayout=layout;
						//swap
						tempLayout[yi][xi]=layout[yi-1][xi];
						tempLayout[yi-1][xi]=' ';
						
						states.add(new State<char[][]>(tempLayout));//swapped with top
					}
				}
			}
		}
		return states;
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
