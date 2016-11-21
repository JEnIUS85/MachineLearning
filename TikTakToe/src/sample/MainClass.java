package sample;

import java.util.Map;
import java.util.Scanner;
import java.util.HashMap;

public class MainClass {

	/**
	 * @param args
	 */
	//public String [] grid = {"O","X","O","X","O","X","O","X","O"};
	public String [] grid = {"1","2","3","4","5","6","7","8","9"};
	public Map<Integer,String> map_grid = new HashMap<Integer,String>();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
			new MainClass();
	}
	
	public MainClass(){
		//Initialize HashMap Grid...
		for(int i=1; i<=9;i++){
			map_grid.put(i,Integer.toString(i));		
		}
		
		Scanner reader = new Scanner(System.in);  // Reading from System.in
		System.out.println("Welcome to Game of Tic-Tac-Toe");
		System.out.print("Enter (1) for Single Player ... (0) for program to learn and evolve.. : ");//(2) for Multi-Player ... 
		int n = reader.nextInt(); // Scans the next token of the input as an int.
		if (n ==1){
			System.out.println("Your opponent is this computer and it will make the first move..You will play next.. ");
			System.out.println("**************************************");
			//SinglePlayer();
			new AI().playSingleUser();
		}
		else if (n == 2)
		{
			System.out.println("You are Player #1 playing with X .. Your opponent will play with 0");	
		}
		else if (n ==0){
			System.out.println("How many self iteration i should run to train myself. minimum 10K please : ");			
			System.out.println("Wait for a while. I am getting trained through self learning..");
			System.out.println("**************************************");
			//Metamorphosis(reader.nextInt());
			new AI().Metamorphosis(reader.nextInt());
		}
		else{
			 System.out.println("You have added a wrong input.. Program will exit ...");
			 new ExitGameClass().exitGame();			 
		}		
	}//Public constructor over...
	
	
	public void Metamorphosis(int i_Tainingiterations){
		Player obj_player = new Player();
		obj_player.initializer(0); //set the Preferrence weight for each cell in board to 100..
		MC obj_mc = new MC();
		obj_mc.initializer();
		PaintFame obj_paintFame = new PaintFame();
		
		while(i_Tainingiterations > 0){
			obj_player.clearTempPreferrenceList();
			
			//Initialize HashMap Grid...
			for(int i=1; i<=9;i++){
				map_grid.put(i,Integer.toString(i));		
			}
			i_Tainingiterations--;
			//*********** Make First Mover... *************
			map_grid = obj_player.botNextMove(map_grid);				
			map_grid = obj_player.PerfBasedNextMove(map_grid,"X");
			//obj_paintFame.PaintGrid(map_grid);	
			//*********** Make Second Mover... *************
			map_grid = obj_player.botNextMove(map_grid);
			map_grid = obj_player.PerfBasedNextMove(map_grid,"X");
			//obj_paintFame.PaintGrid(map_grid);	
			//*********** Make Third Mover... *************
			map_grid = obj_player.botNextMove(map_grid);			
			if(obj_mc.checkWinner(map_grid,"O")){
				obj_player.updatePreference(map_grid,"O");
				continue;
			}
			
			map_grid = obj_player.PerfBasedNextMove(map_grid,"X");
			if(obj_mc.checkWinner(map_grid,"X")){				
				obj_player.updatePreference(map_grid,"X");
				continue;
		    }
			//obj_paintFame.PaintGrid(map_grid);	
			//*********** Make Fourth Mover... *************
			map_grid = obj_player.botNextMove(map_grid);			
			if(obj_mc.checkWinner(map_grid,"O")){
				obj_player.updatePreference(map_grid,"O");
				continue;
			}
			
			map_grid = obj_player.PerfBasedNextMove(map_grid,"X");
			if(obj_mc.checkWinner(map_grid,"X")){				
				obj_player.updatePreference(map_grid,"X");
				continue;
		    }
			//obj_paintFame.PaintGrid(map_grid);	
			//*********** Make Fifth Mover... *************
			map_grid = obj_player.botNextMove(map_grid);			
			if(obj_mc.checkWinner(map_grid,"O")){
				obj_player.updatePreference(map_grid,"O");				
			}
			else{
				obj_player.updatePreference(map_grid,"D");
			}		
			//obj_paintFame.PaintGrid(map_grid);	
		}//while loop over.. 
		
	}// Metamorphosis() method over..
	
	public void SinglePlayer(){		
		PaintFame obj_paintFame = new PaintFame();
		Player obj_player = new Player();
		obj_player.initializer(1);//read the preferrence weight from file and assign to PreferrenceList hashmap..
		obj_player.clearTempPreferrenceList();
		obj_paintFame.PaintGrid(map_grid);		
		//*********** Make First Mover... *************
		System.out.println("Make your First Move...");
		map_grid = obj_player.makeNextMove(map_grid);
		//map_grid = obj_player.botNextMove(map_grid);
		map_grid = obj_player.PerfBasedNextMove(map_grid,"O");
		obj_paintFame.PaintGrid(map_grid);		
		
		//*********** Make Second Mover... *************
		System.out.println("Make your Second Move...");		
		map_grid = obj_player.makeNextMove(map_grid);
		//map_grid = obj_player.botNextMove(map_grid);
		map_grid = obj_player.PerfBasedNextMove(map_grid,"O");
		obj_paintFame.PaintGrid(map_grid);		
		
		//*********** Make Third Mover... *************
		MC obj_mc = new MC();
		obj_mc.initializer();
		System.out.println("Make your 3rd Move...");		
		map_grid = obj_player.makeNextMove(map_grid);
		
		if(obj_mc.checkWinner(map_grid,"X")){
			obj_player.updatePreference(map_grid,"X");
			obj_paintFame.PaintGrid(map_grid);
			new ExitGameClass().exitGame();
		}
		//map_grid = obj_player.botNextMove(map_grid);
		map_grid = obj_player.PerfBasedNextMove(map_grid,"O");
		if(obj_mc.checkWinner(map_grid,"O")){
			obj_player.updatePreference(map_grid,"O");
			obj_paintFame.PaintGrid(map_grid);
			new ExitGameClass().exitGame();
	    }
		obj_paintFame.PaintGrid(map_grid);
		
		//*********** Make Fourth Mover... *************
		System.out.println("Make your 4th Move...");		
		map_grid = obj_player.makeNextMove(map_grid);
		
		if(obj_mc.checkWinner(map_grid,"X")){
			obj_player.updatePreference(map_grid,"X");
			obj_paintFame.PaintGrid(map_grid);
			new ExitGameClass().exitGame();
		}
		//map_grid = obj_player.botNextMove(map_grid);
		map_grid = obj_player.PerfBasedNextMove(map_grid,"O");
		if(obj_mc.checkWinner(map_grid,"O")){
			obj_player.updatePreference(map_grid,"X");			
			obj_paintFame.PaintGrid(map_grid);
			new ExitGameClass().exitGame();
	    }
		obj_paintFame.PaintGrid(map_grid);
		
		//*********** Make Fifth Mover... *************
		System.out.println("Make your final Move...");		
		map_grid = obj_player.makeNextMove(map_grid);
		
		if(obj_mc.checkWinner(map_grid,"X")){			
			obj_player.updatePreference(map_grid,"X");
			obj_paintFame.PaintGrid(map_grid);
			new ExitGameClass().exitGame();
		}else{
			obj_player.updatePreference(map_grid,"D");
			obj_paintFame.PaintGrid(map_grid);
			System.out.println("IT's a DRAW !!!...PLAY HARD NEXT TIME.. ");
		}
	}//SinglePlayer() method over..	
}
