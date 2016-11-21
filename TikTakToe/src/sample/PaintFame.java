package sample;

import java.util.Map;

public class PaintFame {

	public void PaintGrid(Map<Integer,String> grid){
		try{
			System.out.println(" ");
			 System.out.println("	!	!	");
			// System.out.println("		|		|		");
			System.out.println("     "+grid.get(1)+"  !   "+grid.get(2)+"   !  "+grid.get(3));
			 System.out.println("    =================");
			 System.out.println("	!	!	");
			 //System.out.println("	|	|	");
			 System.out.println("     "+grid.get(4)+"  !   "+grid.get(5)+"   !  "+grid.get(6));
			 System.out.println("    =================");
			 System.out.println("	!	!	");
			 //System.out.println("	|	|	");
			 System.out.println("     "+grid.get(7)+"  !   "+grid.get(8)+"   !  "+grid.get(9));
			 System.out.println(" ");
			/* System.out.println("	|	|	");
				// System.out.println("		|		|		");
				System.out.println("     "+grid[0]+"  |   "+grid[1]+"   |  "+grid[2]);
				 System.out.println("    =================");
				 System.out.println("	|	|	");
				 //System.out.println("	|	|	");
				 System.out.println("     "+grid[3]+"  |   "+grid[4]+"   |  "+grid[5]);
				 System.out.println("    =================");
				 System.out.println("	|	|	");
				 //System.out.println("	|	|	");
				 System.out.println("     "+grid[6]+"  |   "+grid[7]+"   |  "+grid[8]);*/
			
		}catch(Exception e){
			e.printStackTrace();
		}		
	}//PaintGrid method over..
}
