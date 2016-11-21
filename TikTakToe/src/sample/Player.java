package sample;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;

public class Player {
		
	public static Map<Integer, Integer > PreferrenceList = new HashMap<Integer, Integer >();
	public static Map<Integer, Integer > tmp_PreferrenceList ;//= new HashMap<Integer, Integer >();
	public String str_preferrencefile = "prefRating.properties";
	public int key_counter =1;
	
	public void initializer(int flag){
		// Check if Preferrence list to be set to 100 or read for a file..
		if(flag ==0){
			for(int i=1; i<=9;i++)		
			{  //initialize all the 9 grids to 100.. with each game the values of each cell should increase or decrease..
				PreferrenceList.put(i, 1000);
			}
		}else{			
			//read from Preferrence List
			try{
				File file = new File(".//"+ str_preferrencefile);
				Properties properties = new Properties();
				properties.load(new FileInputStream(file));
				for (String key : properties.stringPropertyNames()) {
					PreferrenceList.put(Integer.valueOf(key), Integer.valueOf(properties.get(key).toString()));
					//System.out.println(key+" : " + properties.get(key));
				}
			}catch(Exception e){
				e.printStackTrace();
			}			
		}
	}//initializer() method  over..
	
	public void clearTempPreferrenceList(){		
		tmp_PreferrenceList = new HashMap<Integer, Integer >();
		key_counter=1;
	}
	public Map<Integer,String> makeNextMove(Map<Integer,String> map_grid){
		
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
			map_grid.put(n, "X");
			tmp_PreferrenceList.put(n,key_counter);
			key_counter++;
		}else{
			System.err.println("You have entered a wrong input.. Game will exit..");
			new ExitGameClass().exitGame();
		}
		return map_grid;
	}//makeNextMove() method over..	
	
	public Map<Integer,String> botNextMove(Map<Integer,String> map_grid){
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
		 map_grid.put(Integer.valueOf(map_grid_tmp.get(random_key)),"O");
		 tmp_PreferrenceList.put(Integer.valueOf(map_grid_tmp.get(random_key)),key_counter);
		 key_counter++;
		
		 return map_grid;
	}//botNextMove method over..
	
   public Map<Integer,String> PerfBasedNextMove(Map<Integer,String> map_grid, String str_XorO){
	   Iterator it = map_grid.entrySet().iterator();
	   Map<Integer,Integer> map_grid_tmp = new HashMap<Integer,Integer> ();		
	   int tmp_maxvalue=-1000000;//hope that weightage of any cell wont go beyond this number.
	   int i =1;
	   //System.out.println("Listing down grid that has higher preferrence value");
		 while (it.hasNext()) {			 
		        Map.Entry pair = (Map.Entry)it.next();
		        
		        if(!(pair.getValue().equals("X") || pair.getValue().equals("O"))){
		        	//System.out.println(" Key :" +pair.getKey()+ " : Value = " +pair.getValue() );
		        	//if it is the first cell, then add it..		        	
		        	if (tmp_maxvalue < PreferrenceList.get(pair.getKey())){//Integer.valueOf(pair.getValue().toString())
		        		i=1;tmp_maxvalue = PreferrenceList.get(pair.getKey());
		        		//System.out.println(" Key :" +i );
		        		map_grid_tmp =  new HashMap<Integer,Integer> ();
		        		map_grid_tmp.put(i,Integer.valueOf(pair.getKey().toString()));
		        		i++;
		        	}
		        	else if (tmp_maxvalue == Integer.valueOf(pair.getValue().toString())){
		        		//tmp_maxvalue = PreferrenceList.get(pair.getKey());//Integer.valueOf(pair.getValue().toString());
		        		//int i_tmp = Integer.valueOf(pair.getKey().toString());
			        	map_grid_tmp.put(i, i);//PreferrenceList.get(Integer.valueOf(pair.getKey().toString()))
			        	i++;
		        	}//inner if-else over..	
		        	/*if (i==1){
		        		//tmp_maxvalue = PreferrenceList.get(pair.getKey());
		        		//tmp_maxvalue = Integer.valueOf(pair.getValue().toString());
		        		map_grid_tmp.put(i, i);//PreferrenceList.get(Integer.valueOf(pair.getKey().toString()))
		        		i++;
		        	}*/
		        	//System.out.println("ADDED");
		        }//Outer if condition over..		        
		        //System.out.println("---");
		    }//While method over..
	   
		 int random_key = 1+ (int)(Math.random() * ((map_grid_tmp.size())));
		// System.out.println("randomKey = "+random_key + " key_counter :"+ key_counter);
		 
		 /*it = map_grid_tmp.entrySet().iterator();
		   System.out.println("Perferrence list ...");
			 while (it.hasNext()) {				 
			        Map.Entry pair = (Map.Entry)it.next();
			        System.out.println(pair.getKey() + " : " + pair.getValue());		      
			    }//While method over..
		 */
		 //System.out.println("Grid selected based upon prefrrence = "+Integer.valueOf(map_grid_tmp.get(random_key)));
		 map_grid.put(Integer.valueOf(map_grid_tmp.get(random_key)),str_XorO);//"X"
		 tmp_PreferrenceList.put(Integer.valueOf(map_grid_tmp.get(random_key)),key_counter);
		 key_counter++;
		return map_grid;
	}//PerfBasedNextMove () method over..
   
   public void updatePreference(Map<Integer,String> map_grid, String str_XorOorD){
	   String absolutePath = getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
       absolutePath = absolutePath.substring(0, absolutePath.lastIndexOf("/"));
       //System.out.println("str_XorOorD = " + str_XorOorD);
       FileOutputStream fop = null;
	   File file;
	   FileWriter fstream;
	   BufferedWriter out;
	   
	   Properties prop = new Properties();
	   
	   try{
		   file = new File(".//"+ str_preferrencefile);
		   fop = new FileOutputStream(file);
		   if (!file.exists()) {
				file.createNewFile();
			}
		   fstream = new FileWriter(file);
		   out = new BufferedWriter(fstream);
		   
		   //System.out.println("Updating Perferrence list ...");
	       int i_X=0;
	       int i_O=0;
	       int i_D=0;
	       if(str_XorOorD.equals("X")){i_X = 9;i_O=0;}
		   else if (str_XorOorD.equals("O")){i_X = 0;i_O=10;}
		   else {i_D = 1;}
	       Iterator it;
	       
	       
	      /* it = PreferrenceList.entrySet().iterator();
		   System.out.println("Perferrence list ...");
			 while (it.hasNext()) {				 
			        Map.Entry pair = (Map.Entry)it.next();
			        System.out.println(pair.getKey() + " : " + pair.getValue());		      
			    }//While method over..	
			
			 it = tmp_PreferrenceList.entrySet().iterator();
			   System.out.println("tmp_PreferrenceList list ...");
				 while (it.hasNext()) {				 
				        Map.Entry pair = (Map.Entry)it.next();
				        System.out.println(pair.getKey() + " : " + pair.getValue());		      
				    }//While method over..	
			*/	 
		   it= map_grid.entrySet().iterator(); 
		   while (it.hasNext()) {			   
			   Map.Entry pair = (Map.Entry)it.next();
			   //System.out.println(pair.getKey() + " : " + pair.getValue());
			   if(str_XorOorD.equals("D")){
				   if((pair.getValue().equals("X") || pair.getValue().equals("O"))){		        	
						PreferrenceList.put(Integer.valueOf(pair.getKey().toString()), PreferrenceList.get(pair.getKey())+ i_D);//Integer.valueOf(pair.getKey().toString())
					   
				   }
			  }else{				   
				   if((pair.getValue().equals("X"))){		        	
						     PreferrenceList.put(Integer.valueOf(pair.getKey().toString()), (PreferrenceList.get(pair.getKey())+ i_X-tmp_PreferrenceList.get(pair.getKey())));//Integer.valueOf(pair.getKey().toString())
				   }else if((pair.getValue().equals("O"))){
						     PreferrenceList.put(Integer.valueOf(pair.getKey().toString()), (PreferrenceList.get(pair.getKey())+ i_O -tmp_PreferrenceList.get(pair.getKey())));//Integer.valueOf(pair.getKey().toString())
				     }
			   }//if-else over..
			   //out.write(pair.getKey()+" : " + PreferrenceList.get(pair.getKey()) + "\n");
			   prop.put(pair.getKey().toString(), String.valueOf(PreferrenceList.get(pair.getKey())));
		   }//While Loop over..
		   //out.write("===========================================");
		  // out.close();
		   prop.store(fop, null);
		   it = PreferrenceList.entrySet().iterator();
		   //System.out.println("Perferrence list ...");
			 while (it.hasNext()) {				 
			        Map.Entry pair = (Map.Entry)it.next();
			        //System.out.println(pair.getKey() + " : " + pair.getValue());		      
			    }//While method over..			 
	   }catch(Exception e){
		   e.printStackTrace();
	   }
   }//updatePreference() method over..  
   
}
