package com.leapoffaith;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BackTrack_NQueen {
	
	private class Queen{
		int row, col = 0;
		public Queen(int row, int col){
			this.row = row;
			this.col = col;
		}
		public int getRow(){
			return row;
		}
		public int getCol(){
			return col;
		}
	}//inner class Queen Over..

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Enter value of n for a n-squared matrix. Ex: 2,4,8.. :");
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.valueOf(in.readLine());//get nxn square matrix.
		new BackTrack_NQueen().start(n);
	}

	public void start(int n){
		Queen [] queenPositionHolder = new Queen[n];
		queenPositionHolder[0]= new Queen(0,0);// place the first queen at 0,0 coordinate
		
		if (NQueenRecurrsiveFunctionByRow(0,n,queenPositionHolder)){
			System.out.println("X , Y coordinates Row wise Search are  :");
			for(int i=0; i<queenPositionHolder.length;i++){
				System.out.println(queenPositionHolder[i].getRow()+" ," + queenPositionHolder[i].getCol());
			}			
		}else{
			System.out.println("No possible solution exists for " + n +"-squared matrix");
		}
		
		if (NQueenRecurrsiveFunctionByCol(0,n,queenPositionHolder)){
			System.out.println("X , Y coordinates Column wise Search are  :");
			for(int i=0; i<queenPositionHolder.length;i++){
				System.out.println(queenPositionHolder[i].getRow()+" ," + queenPositionHolder[i].getCol());
			}			
		}else{
			System.out.println("No possible solution exists for " + n +"-squared matrix");
		}
	}//start() method over..
	
	public boolean NQueenRecurrsiveFunctionByRow(int startingRow, int nSquare, Queen [] queenPositionHolder )
	{
		boolean flag =false;
		if(startingRow == nSquare){
			//You have covered all the rows in the board.. stop the recursive action..
			return true;
		}
		for(int i=0;i<nSquare;i++){
			flag =true;
			for(int j=0; j<startingRow;j++){
				//Check if it is safe to place queen..
				if(		queenPositionHolder[j].getCol()== i ||
						queenPositionHolder[j].getRow()-queenPositionHolder[j].getCol() == (startingRow - i)||
						queenPositionHolder[j].getRow()+ queenPositionHolder[j].getCol() == (startingRow +i)){
					//queenPositionHolder[j].getRow()==(startingRow) || 
						flag = false;
						break;
				}									
			}//For loop to go over each Queen location..
			
			if(flag){
				queenPositionHolder[startingRow]= new Queen(startingRow,i);
				if(NQueenRecurrsiveFunctionByRow(startingRow+1,nSquare,queenPositionHolder)){
					return true;
				}
			}
			
		}//For loop to go over each column of n-Square matrix..
		
		return false;
	}//NQueenRecurrsiveFunction() method over..	
	
	public boolean NQueenRecurrsiveFunctionByCol(int startingCol,int nSquare,Queen [] queenPositionHolder){
		if(startingCol == nSquare){
			//reached to last column and should stop the execution..
			return true;
		}
		for(int row=0;row<nSquare;row++){
			boolean flag = true;
			for(int col =0; col <startingCol;col++){
				if(queenPositionHolder[col].getRow()== row ||
						queenPositionHolder[col].getRow()-queenPositionHolder[col].getCol() == (row-startingCol)||
						queenPositionHolder[col].getRow()+queenPositionHolder[col].getCol() == (row+startingCol)
						){
					flag = false;
					break;
				}
			}//For loop to iterated through each column cell for a given row over..
			
			if(flag){
				queenPositionHolder[startingCol]= new Queen(row,startingCol);
				if(NQueenRecurrsiveFunctionByCol(startingCol+1,nSquare,queenPositionHolder)){
					return true;
				}
			}
			
		}//for loop to iterate each row per cell over..
		
		return false;
	}
}
