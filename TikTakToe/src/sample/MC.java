package sample;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MC {
	
	public static final int[] winningcombination_1 = {1,2,3};
	public static final int[] winningcombination_2 = {4,5,6};
	public static final int[] winningcombination_3 = {7,8,9};
	public static final int[] winningcombination_4 = {1,4,7};
	public static final int[] winningcombination_5 = {2,5,8};
	public static final int[] winningcombination_6 = {3,6,9};
	public static final int[] winningcombination_7 = {1,5,9};
	public static final int[] winningcombination_8 = {3,5,7};
	public static Map<Integer, int[] > winningList = new HashMap<Integer, int[] >();
	
	
	public void initializer(){
		winningList.put(1,winningcombination_1);
		winningList.put(2,winningcombination_2);
		winningList.put(3,winningcombination_3);
		winningList.put(4,winningcombination_4);
		winningList.put(5,winningcombination_5);
		winningList.put(6,winningcombination_6);
		winningList.put(7,winningcombination_7);
		winningList.put(8,winningcombination_8);	
	}//initializer() method over..
	
	public boolean checkWinner(Map<Integer, String>  map_grid, String str_XorO){
		boolean result = false;	
		Iterator it = map_grid.entrySet().iterator();
		Map<Integer,String> map_grid_tmp_X = new HashMap<Integer,String> ();
		Map<Integer,String> map_grid_tmp_O = new HashMap<Integer,String> ();
		int i = 1;
		 while (it.hasNext()) {			 
		        Map.Entry pair = (Map.Entry)it.next();		        
		        if(pair.getValue().equals(str_XorO) ){
		        	map_grid_tmp_X.put(Integer.valueOf(pair.getKey().toString()), pair.getKey().toString());
		        	//System.out.println("ADDED to winning set key = " + pair.getKey());	        	
		        }		       
		        //System.out.println("---");
		    }//While method over..	
		  result = matchPattern(map_grid_tmp_X,str_XorO );
		  
		 return result;
	}//checkWinner method over..
	
	public boolean matchPattern(Map<Integer, String>  map_grid, String str_XorO){
		boolean result = false;
		int counter = 0;
		Iterator it = winningList.entrySet().iterator();
		 while (it.hasNext()) {			 
		        Map.Entry pair = (Map.Entry)it.next();		        
		        int[] tmp = (int[]) pair.getValue();
		         for(int i=0; i < tmp.length;i++)  {
		        	 if(map_grid.containsKey(tmp[i])){
		        		 counter++;
		        	 }
		        }//for loop over.. 	
		        if(counter ==3)	 {
		        	if (str_XorO.equals("O"))
		        	{	System.out.println("YOU HAVE WON...CONGRATULATION.. ");
		        	    System.out.print("Winning combination was : ");
			        	for(int i=0; i < tmp.length;i++)  {
			        		System.out.print(tmp[i] + "  ");
			        	}
		        		return true;
		        	}
		        	else {
		        		System.out.println("YOU LOOSE :( ...BETTER LUCK NEXT TIME... ");
		        		System.out.print("Winning combination was : ");
		        		for(int i=0; i < tmp.length;i++)  {
			        		System.out.print(tmp[i] + "  ");
			        	}
		        		return true;
		        	}
		        }     
		      counter =0;
		    }//While method over..	
		
		return result;		
	}
}
