package Maze;

import java.awt.Point;

public class Path {

	Node first;
	Point Current;
	int flage=0;
	int k;
	Point P[];
	
	public Path(int n, Point s){
	P=new Point[n];
	int k=0;
	Current=s;
	}
	
	
	void goRight() {
		
		P[k++]=new Point(Current.x,Current.y+1);
        Current=P[k];
	 
	}

	void goLeft() {

		P[k++]=new Point(Current.x,Current.y-1);
		Current=P[k];
	}

	void goUp() {
		
		P[k++]=new Point(Current.x-1,Current.y);
		Current=P[k];
	}

	void goDown() {
		
		P[k++]=new Point(Current.x+1,Current.y);
		Current=P[k];
	}
	
	

}
