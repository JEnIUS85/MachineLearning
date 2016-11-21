package sample;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;
import java.util.Set;
import java.util.Vector;

public class AI {
	
	public Map<Integer,String> map_grid = new HashMap<Integer,String>();
	public Map<Integer,Vector> map_winnerSet = new HashMap<Integer,Vector>();
	public Map<Integer,Vector> map_drawSet = new HashMap<Integer,Vector>();
	public Map<Integer,Vector> map_LossSet = new HashMap<Integer,Vector>();
	
	public String str_winningcombination = "winningcombination.properties";
	public String str_drawcombination = "drawcombination.properties";
	public String str_losscombination = "losscombination.properties";
	
	public Vector vec = new Vector();
	
	public void Metamorphosis(int i_Tainingiterations){
		
		Player obj_player = new Player();
		//obj_player.initializer(0); //set the Preferrence weight for each cell in board to 100..
		MC obj_mc = new MC();
		obj_mc.initializer();
		PaintFame obj_paintFame = new PaintFame();
		
		
	try{			 
		
		while(i_Tainingiterations > 0){
			//obj_player.clearTempPreferrenceList();//No need of it..
			initializer();//read the winning and draw set from properties file.. remove any duplicate values..
			String cellLocator = "";
			Vector vector = new Vector();
			
			//Initialize HashMap Grid...
			for(int i=1; i<=9;i++){
				map_grid.put(i,Integer.toString(i));		
			}
			i_Tainingiterations--;	
			
			//*********** Make First Mover... *************
			cellLocator = botNextMove_adv(map_grid,"O");
			vector.addElement(cellLocator);
			cellLocator = PerfBasedNextMove_adv(map_grid,"X",1,vector);
			vector.addElement(cellLocator);
			//obj_paintFame.PaintGrid(map_grid);
			//Thread.sleep(5000);
			
			//*********** Make Second Mover... *************
			cellLocator = botNextMove_adv(map_grid,"O");
			vector.addElement(cellLocator);
			cellLocator = PerfBasedNextMove_adv(map_grid,"X",3,vector);
			vector.addElement(cellLocator);
			//obj_paintFame.PaintGrid(map_grid);
			//Thread.sleep(5000);
			
			//*********** Make Third Mover... *************
			cellLocator = botNextMove_adv(map_grid,"O");
			vector.addElement(cellLocator);
			if(obj_mc.checkWinner(map_grid,"O")){
				//obj_paintFame.PaintGrid(map_grid);
				updatePreference_adv(vector,0);//0 means 'first player - O,i.e'  the winner. so print vector seq as it is..
				continue;
			}
			cellLocator = PerfBasedNextMove_adv(map_grid,"X",5,vector);
			vector.addElement(cellLocator);
			if(obj_mc.checkWinner(map_grid,"X")){
				//obj_paintFame.PaintGrid(map_grid);
				updatePreference_adv(vector,1);//1 means 'Second player - X' the winner. so print vector seq after reordering..
				continue;
			}
			//obj_paintFame.PaintGrid(map_grid);
			//Thread.sleep(5000);
			
			//*********** Make Fourth Mover... *************
			cellLocator = botNextMove_adv(map_grid,"O");
			vector.addElement(cellLocator);
			if(obj_mc.checkWinner(map_grid,"O")){
				//obj_paintFame.PaintGrid(map_grid);
				updatePreference_adv(vector,0);//0 means 'first player - O'  the winner. so print vector seq as it is..
				continue;
			}
			cellLocator = PerfBasedNextMove_adv(map_grid,"X",7,vector);
			vector.addElement(cellLocator);
			if(obj_mc.checkWinner(map_grid,"X")){
				//obj_paintFame.PaintGrid(map_grid);
				updatePreference_adv(vector,1);//1 means 'Second player - X' the winner. so print vector seq after reordering..
				continue;
			}
			//obj_paintFame.PaintGrid(map_grid);
			//Thread.sleep(5000);
			
			//*********** Make Fifth Mover... *************
			cellLocator = botNextMove_adv(map_grid,"O");
			vector.addElement(cellLocator);
			//obj_paintFame.PaintGrid(map_grid);
			if(obj_mc.checkWinner(map_grid,"O")){
				updatePreference_adv(vector,0);//0 means 'first player - O'  the winner. so print vector seq as it is..
				continue;
			}else{			
				updatePreference_adv(vector,2);//2 means draw. so print vector seq as it is to draw.properties file.
				continue;
			}			
		}//While loop over..
      }catch(Exception e){
			e.printStackTrace();
	  }	
	}//Metamorphosis() method over..

	public void playSingleUser(){
		Player obj_player = new Player();
		MC obj_mc = new MC();
		obj_mc.initializer();
		PaintFame obj_paintFame = new PaintFame();
		
		try{
			for(int i=1; i<=9;i++){
				map_grid.put(i,Integer.toString(i));		
			}
			initializer();//read the winning and draw set from properties file.. remove any duplicate values..
			String cellLocator = "";
			Vector vector = new Vector();
			
			//*********** Make First Mover... *************	
			
			//cellLocator = makeNextMove(map_grid,"X");
			//vector.addElement(cellLocator);
			
			cellLocator = botNextMove_adv(map_grid,"X");
			vector.addElement(cellLocator);		
			obj_paintFame.PaintGrid(map_grid);
			System.out.println("Make your 1st Move...");
			cellLocator = makeNextMove(map_grid,"O");
			vector.addElement(cellLocator);
			obj_paintFame.PaintGrid(map_grid);
			
			//*********** Make Second Mover... *************			
			cellLocator = PerfBasedNextMove_adv(map_grid,"X",2,vector);//botNextMove_adv(map_grid,"O");
			vector.addElement(cellLocator);	
			obj_paintFame.PaintGrid(map_grid);
			System.out.println("Make your 2nd Move...");
			cellLocator = makeNextMove(map_grid,"O");
			vector.addElement(cellLocator);					
			obj_paintFame.PaintGrid(map_grid);	
			
			//*********** Make Third Mover... *************
			
			cellLocator = PerfBasedNextMove_adv(map_grid,"X",4,vector);//botNextMove_adv(map_grid,"O");
			vector.addElement(cellLocator);			
			if(obj_mc.checkWinner(map_grid,"X")){
				obj_paintFame.PaintGrid(map_grid);
				updatePreference_adv(vector,1);//0 means 'first player - O'  the winner. so print vector seq as it is..
				new ExitGameClass().exitGame();
			}			
			obj_paintFame.PaintGrid(map_grid);
			System.out.println("Make your 3rd Move...");
			cellLocator = makeNextMove(map_grid,"O");
			vector.addElement(cellLocator);
			if(obj_mc.checkWinner(map_grid,"O")){
				obj_paintFame.PaintGrid(map_grid);
				updatePreference_adv(vector,0);//1 means 'Second player - X' the winner. so print vector seq after reordering..
				new ExitGameClass().exitGame();
			}			
			obj_paintFame.PaintGrid(map_grid);
			
			//*********** Make Fourth Mover... *************
			
			cellLocator = PerfBasedNextMove_adv(map_grid,"X",6,vector);//botNextMove_adv(map_grid,"O");
			vector.addElement(cellLocator);
			if(obj_mc.checkWinner(map_grid,"X")){
				obj_paintFame.PaintGrid(map_grid);
				updatePreference_adv(vector,1);//0 means 'first player - O'  the winner. so print vector seq as it is..
				new ExitGameClass().exitGame();
			}
			obj_paintFame.PaintGrid(map_grid);
			System.out.println("Make your 4th and final Move...");
			cellLocator = makeNextMove(map_grid,"O");
			vector.addElement(cellLocator);
			if(obj_mc.checkWinner(map_grid,"O")){
				obj_paintFame.PaintGrid(map_grid);
				updatePreference_adv(vector,0);//1 means 'Second player - X' the winner. so print vector seq after reordering..
				new ExitGameClass().exitGame();
			}			
			obj_paintFame.PaintGrid(map_grid);
			
			//*********** Make Fifth Mover... *************
			//System.out.println("Make your 5th and final Move...");
			/*cellLocator = makeNextMove(map_grid,"X");
			vector.addElement(cellLocator);
			if(obj_mc.checkWinner(map_grid,"X")){
				obj_paintFame.PaintGrid(map_grid);
				updatePreference_adv(vector,1);//0 means 'first player - O'  the winner. so print vector seq as it is..
				new ExitGameClass().exitGame();
			}*/
			cellLocator = PerfBasedNextMove_adv(map_grid,"X",8,vector);//botNextMove_adv(map_grid,"O");
			vector.addElement(cellLocator);
			if(obj_mc.checkWinner(map_grid,"X")){
				obj_paintFame.PaintGrid(map_grid);
				updatePreference_adv(vector,1);//0 means 'first player - O'  the winner. so print vector seq as it is..
				new ExitGameClass().exitGame();
			}
			else{	
				System.out.println("IT's a DRAW !!!...PLAY HARD NEXT TIME.. ");
				updatePreference_adv(vector,2);//2 means draw. so print vector seq as it is to draw.properties file.				
			}
			obj_paintFame.PaintGrid(map_grid);
			
		}catch(Exception e ){
			e.printStackTrace();
		}
		
	}//playSingleUser() method over..
	
	public void initializer(){
		try{
			map_winnerSet.clear();
			map_drawSet.clear();
			map_LossSet.clear();
			
			Set<String> hs_winner = new HashSet<String>();
			Set<String> hs_draw = new HashSet<String>();
			Set<String> hs_loss = new HashSet<String>();
			
		   //Create properties file for Winner Draw and Loss Data Set..
		   File file_winner = new File(".//"+ str_winningcombination);		  
		   if (!file_winner.exists()) {
			   System.out.println("WinningSet file not found.. creating it new..");
				file_winner.createNewFile();				
			}else{
				Properties prop = new Properties();
				//Scanner scan = new Scanner(file_winner);
				prop.load(new FileInputStream(file_winner));
				//while(scan.hasNextLine()) {
				for (String key : prop.stringPropertyNames()) {					
		              hs_winner.add(prop.get(key).toString());
		              //System.out.println(prop.get(key).toString());		            	   
				}//while loop over..
				//add unique value of Hashset to HashMap map_winnerSet
				int i =1;
				for (String s : hs_winner) {
	                //System.out.println(s);
					Vector <String>v = new Vector<String>();
					String split[] =s.split(",");
					for(int j=0;j<split.length;j++){
						//System.out.println(split[j]);
						v.add(split[j]);
					}					
					map_winnerSet.put(i,v);
					prop.put(String.valueOf(i), s);
					i++;
	            }//for loop over..
				FileOutputStream fop_winners = new FileOutputStream(file_winner);//to read and write back after removing duplicate values..
				prop.store(fop_winners, null);//write back  to winner property file with unique data..
				fop_winners.close();
			}
		  File file_draw = new File(".//"+ str_drawcombination);
		  if (!file_draw.exists()) {
			  System.out.println("DrawSet file not found.. creating it new..");
					file_draw.createNewFile();
			}else{
				Properties prop = new Properties();
				//Scanner scan = new Scanner(file_draw);
				prop.load(new FileInputStream(file_draw));
				//while(scan.hasNextLine()) {
				for (String key : prop.stringPropertyNames()) {			
					hs_draw.add(prop.get(key).toString());
					//System.out.println(prop.get(key).toString());			            	   
				}//while loop over..
				//add unique value of Hashset to HashMap map_winnerSet
				int i =1;
				for (String s : hs_draw) {
	               // System.out.println(s);
					Vector v = new Vector();
					String split[] =s.split(",");
					for(int j=0;j<split.length;j++){v.add(split[j]);}
					map_drawSet.put(i,v);
					prop.put(String.valueOf(i), s);
					i++;
	            }//for loop over..
				FileOutputStream fop_draw = new FileOutputStream(file_draw);//to read and write back after removing duplicate values..
				prop.store(fop_draw, null);//write back  to winner property file with unique data..
				fop_draw.close();
			}
		  
		  File file_loss = new File(".//"+ str_losscombination);		  
		   if (!file_loss.exists()) {
			   System.out.println("LossSet file not found.. creating it new..");
				file_loss.createNewFile();				
			}else{
				Properties prop = new Properties();
				//Scanner scan = new Scanner(file_winner);
				prop.load(new FileInputStream(file_loss));
				//while(scan.hasNextLine()) {
				for (String key : prop.stringPropertyNames()) {					
		              hs_loss.add(prop.get(key).toString());
		              //System.out.println(prop.get(key).toString());		            	   
				}//while loop over..
				//add unique value of Hashset to HashMap map_winnerSet
				int i =1;
				for (String s : hs_loss) {
	                //System.out.println(s);
					Vector <String>v = new Vector<String>();
					String split[] =s.split(",");
					for(int j=0;j<split.length;j++){
						//System.out.println(split[j]);
						v.add(split[j]);
					}					
					map_LossSet.put(i,v);
					prop.put(String.valueOf(i), s);
					i++;
	            }//for loop over..
				FileOutputStream fop_loss = new FileOutputStream(file_loss);//to read and write back after removing duplicate values..
				prop.store(fop_loss, null);//write back  to winner property file with unique data..
				fop_loss.close();
			}
		 //======================================================== 			
		}catch(Exception e){
			e.printStackTrace();
		}		
	}//initializer() over..
	
	public String makeNextMove(Map<Integer,String> map_grid,String str_XorR){
		
		System.out.print("You can select any position from avilable slots  : ");
		Iterator it = map_grid.entrySet().iterator();
		int i = 1;
		while (it.hasNext()) {			 
		        Map.Entry pair = (Map.Entry)it.next();
		        if(!(pair.getValue().equals("X") || pair.getValue().equals("O"))){
		        	System.out.print(pair.getValue()+" ");		       	
		        }	        
		    }//While method over..
		 System.out.print(" => ");
		 
		Scanner reader = new Scanner(System.in);  // Reading from System.in
		int n = reader.nextInt();
		if((1<=n) && (n<=9)){
			while(map_grid.get(n).equals("X") || map_grid.get(n).equals("O"))
			{
				System.err.println("Entered slot is already picked.. enter new slot ..");
				n = reader.nextInt();
				if(!((1<=n) && (n<=9))){
					System.err.println("Entered slot is not valid.. Restart the game..");
					new ExitGameClass().exitGame();
				}//inner if loop over..
			}//while loop over..	
			map_grid.put(n, str_XorR);	
			
		}else{
			System.err.println("You have entered a wrong input.. Game will exit..");
			new ExitGameClass().exitGame();
		}
		return String.valueOf(n);
		
	}//makeNextMove() method over..	
	
	public String botNextMove_adv(Map<Integer,String> map_grid, String str_XorO){		
		Iterator it = map_grid.entrySet().iterator();
		Map<Integer,String> map_grid_tmp = new HashMap<Integer,String> ();
		int i = 1;
		 while (it.hasNext()) {			 
		        Map.Entry pair = (Map.Entry)it.next();
		       // System.out.print(pair.getKey() + " : " + pair.getValue());
		        if(!(pair.getValue().equals("X") || pair.getValue().equals("O"))){
		        	map_grid_tmp.put(i, pair.getKey().toString());
		        	//System.out.println("ADDED");
		        	i++;
		        }		        
		        //System.out.println("---");
		    }//While method over..
		 
		 int random_key = 1+ (int)(Math.random() * ((map_grid_tmp.size())));
		 //System.out.println("Priting random_key  =  " + random_key);
		 /*******print entire tmp map ***********/
		 /*  it = map_grid_tmp.entrySet().iterator();
		     while (it.hasNext()) {			 
		        Map.Entry pair = (Map.Entry)it.next();
		       // System.out.println(pair.getKey() + " : " + pair.getValue());		      
		    }//While method over..
		 */
		 /************************************/
		 //System.out.println("Priting tmp key  =  " +map_grid_tmp.get(random_key));
		 map_grid.put(Integer.valueOf(map_grid_tmp.get(random_key)),str_XorO);	//"O"	 
		 
		 return (map_grid_tmp.get(random_key));
		 
	}//botNextMove method over..
	
	public String PerfBasedNextMove_adv(Map<Integer,String> map_grid, String str_XorO,int moveCount, Vector vector){
		   
		//Check if map_winnerSet has any mapping set..
		if(map_winnerSet.size()>0){
			int flag = 0;
			//Check if pattern is a match..each element in this hashmap is a vector.
			Iterator it = map_winnerSet.entrySet().iterator();
			 while (it.hasNext()) {	
				 flag=0;
				 Map.Entry pair = (Map.Entry)it.next();
				 Vector vect_tmp = (Vector) pair.getValue();
				 //System.out.println("===================");
				 for(int i=0; i< moveCount;i++){
					 //System.out.println(vect_tmp.elementAt(i));
					 if(i< vect_tmp.size()&& (vect_tmp.elementAt(i).equals(vector.elementAt(i)))){flag=flag+0;}
					else{flag=1;}
				 }//For loop over..
				 //System.out.println("===================");
				 if(flag ==0 && moveCount < vect_tmp.size()){
					   map_grid.put(Integer.valueOf((String)vect_tmp.elementAt(moveCount)),str_XorO);//"X"
					   return (String) vect_tmp.elementAt(moveCount);
				 }
			 }//While loop over..
		}
		if (map_drawSet.size()>0){
			//you will come here only if there are no vector in map_winnerSet or your current board stage is not matching with any winning combination.
			int flag = 0;
			//Check if pattern is a match..each element in this hashmap is a vector.
			Iterator it = map_drawSet.entrySet().iterator();
			 while (it.hasNext()) {	
				 flag=0;
				 Map.Entry pair = (Map.Entry)it.next();
				 Vector vect_tmp = (Vector) pair.getValue();
				 //System.out.println("======DRAW=============");
				 for(int i=0; i< moveCount;i++){
					 //System.out.println(vect_tmp.elementAt(i));
					if(i< vect_tmp.size() && vect_tmp.elementAt(i).equals(vector.elementAt(i))){flag=flag+0;}
					else{flag=1;}
				 }//For loop over..
				 //System.out.println("===================");
				 if(flag ==0 & moveCount < vect_tmp.size() ){
					   map_grid.put(Integer.valueOf((String)vect_tmp.elementAt(moveCount)),str_XorO);//"X"
					  return (String) vect_tmp.elementAt(moveCount);
				 }
			 }//While loop over..
		}
		if (map_LossSet.size()>0){
			//you will come here only if there are no vector in map_winnerSet and map_drawSet or your current board stage is not matching with any winning combination.
			int flag = 0;
			//Check if pattern is a match..each element in this hashmap is a vector.
			Iterator it = map_LossSet.entrySet().iterator();
			while (it.hasNext()) {	
				 flag=0;
				 Map.Entry pair = (Map.Entry)it.next();
				 Vector vect_tmp = (Vector) pair.getValue();
				 //System.out.println("======LOSS=============");
				 for(int i=0; (i< moveCount && flag==0);i++){
					 //System.out.println(vect_tmp.elementAt(i));
					if(i< vect_tmp.size() && vect_tmp.elementAt(i).equals(vector.elementAt(i))){flag=flag+0;}
					else{flag=1;}
				 }//For loop over..
				 //System.out.println("======DRAW=============");
				 if(flag ==0 && (moveCount+1 < vect_tmp.size() )){ 
					 	//moveCount+1 is looked because we should pick opponent move from loosing set.picking moveCount will give you the faulty selection. 
					   map_grid.put(Integer.valueOf((String)vect_tmp.elementAt(moveCount+1)),str_XorO);//"X"
					  return (String) vect_tmp.elementAt(moveCount+1);
				 }
			 }//While loop over..
		}
		//if you are here then better call for random selection as there is no matching pattern to be found in winning or draw set..
			return (botNextMove_adv(map_grid,str_XorO));//"X");				
	}//PerfBasedNextMove () method over..

	public void updatePreference_adv(Vector vector, int seqOrder){	
		Properties prop = new Properties();
		Properties prop_loss = new Properties();
		String seqlist="";
		String seqlist_loss="";
		
		if(seqOrder ==0 || seqOrder ==1 ){
			try{
				//Create properties file for Winner and Draw Data Set..
				   File file_winner = new File(".//"+ str_winningcombination);
				   File file_loss = new File(".//"+ str_losscombination);
				   FileOutputStream fop_winners = new FileOutputStream(file_winner,true);//to append to existing file.
				   if (!file_winner.exists()) {
						file_winner.createNewFile();
					}
				   FileOutputStream fop_loss = new FileOutputStream(file_loss,true);//to append to existing file.
				   if (!file_loss.exists()) {
						file_loss.createNewFile();
					}
				   
				   if(seqOrder==1){//0
					   //System.out.println("Vector size winner = "+vector.size());
					   for(int i=0;i<vector.size();i++)
						   {//System.out.println(vector.elementAt(i));
						   seqlist=seqlist+(String)vector.elementAt(i)+",";}
					   	   seqlist = seqlist.replaceAll(",$", "");//remove the trailing ,
				   }else{
					   int i=0;
					   //System.out.println("Vector size lost = "+vector.size());
					   while(i<vector.size())
					   { //System.out.println(vector.elementAt(i));
						   //for(int i=1;i<=vector.size();i++)
					   		seqlist_loss=seqlist_loss+(String)vector.elementAt(i)+",";
						    if(i+1 <vector.size())
						   	  {
						    	seqlist=seqlist+(String)vector.elementAt(i+1)+",";
						   	    seqlist_loss=seqlist_loss+(String)vector.elementAt(i+1)+",";
						   	  }
						      seqlist=seqlist+(String)vector.elementAt(i)+",";
						      i=i+2;
						}//while loop over..	
					   seqlist = seqlist.replaceAll(",$", "");//remove the trailing ,
					   seqlist_loss = seqlist_loss.replaceAll(",$", "");//remove the trailing ,
					   prop_loss.put(String.valueOf(map_LossSet.size()+1),seqlist_loss);
					   prop_loss.store(fop_loss, null);//write the combination to file.
					  }				   
				//================================================= 				   
				   //System.out.println("Seqlist to be added = " +seqlist);
				   prop.put(String.valueOf(map_winnerSet.size()+1),seqlist);
				   prop.store(fop_winners, null);//write the combination to file.
				   
				   
			}catch(Exception e){
				e.printStackTrace();
			}
		}else{
			//Create properties file for Draw Data Set..
			try{
				File file_draw = new File(".//"+ str_drawcombination);
				FileOutputStream fop_draw = new FileOutputStream(file_draw,true);//to append to existing file.);
				if (!file_draw.exists()) {
						file_draw.createNewFile();
					}
				//write the sequence as it is in the draw properties file..
				//System.out.println("Vector size Draw = "+vector.size());
				for(int i=0;i<vector.size();i++)
				   {seqlist=seqlist+(String)vector.elementAt(i)+",";}
			   	   seqlist = seqlist.replaceAll(",$", "");//remove the trailing ,
			   	//System.out.println("Seqlist to be added = " +seqlist);
				prop.put(String.valueOf(map_drawSet.size()+1),seqlist);
				prop.store(fop_draw, null);//write the combination to file.
			   	   
			}catch(Exception e)	{
				e.printStackTrace();
			}
		}//if-else outter condition over..		 
	}//updatePreference_adv() method over..
}
