package com.leapoffaith;

public class BackTrack_Maps {

	private class map_datastructure{
		int [] as_neighbouringState;
		String s_stateColor = null;// "R", "G" or "B"..
		public map_datastructure(int[] args){
			setNeighbouringState(args);
		}
		public void setNeighbouringState(int [] as_neighbouringState){
			this.as_neighbouringState = as_neighbouringState;
		}
		public int [] getNeighbouringState(){
			return as_neighbouringState;
		}
		public void setStateColor(String s_stateColor){
			this.s_stateColor = s_stateColor;
		}
		public String getStateColor(){
			return s_stateColor;
		}
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new BackTrack_Maps().start();
	}
	
	public void start(){
		//WA, NT, SA, Q, NSW,V ,T ==> 0,1,2,3,4,5,
		map_datastructure [] oa_map = new map_datastructure[7];
		oa_map[0] = new map_datastructure(new int[] {1,2});//"NT","SA"
		oa_map[1] = new map_datastructure(new int[] {0,2,3});//"WA","SA","Q"
		oa_map[2] = new map_datastructure(new int[] {0,1,3,4,5});//"NT","WA","Q","NSW","V
		oa_map[3] = new map_datastructure(new int[] {1,2,4});//"NT","SA","NSW"
		oa_map[4] = new map_datastructure(new int[] {2,3,5});//"SA","Q","V"
		oa_map[5] = new map_datastructure(new int[]{2,4});//"SA","NSW"
		oa_map[6] = new map_datastructure(null);//"NONE"
		
		if(colormap(oa_map,0)){
			System.out.println("It is possible to color all states without overlap");
			for(int i=0;i<oa_map.length;i++){
				System.out.print(oa_map[i].getStateColor()+" ");
			}
		}else{
			System.out.println("It is not possible to color all states without overlap");
		}		
	}//Start() over..
	
	public boolean colormap(map_datastructure [] oa_map, int i_Counter){
		//System.out.println("iCounter = "+ i_Counter);
		if(i_Counter ==oa_map.length ){
			return true;
		}
		String[] as_rbg= new String[]{"R","G","B"};
		
		int [] ai_neighboringState = oa_map[i_Counter].getNeighbouringState();
		//for(int x=0;x<ai_neighboringState.length;x++){System.out.print(ai_neighboringState[x]+ " ");}
		
		//System.out.println("");
		for(int i=0;i<3; i++){//3
			boolean flag = true;			
			//iterate over all 3 colors..
			if(ai_neighboringState !=null){
				for(int j=0; j<ai_neighboringState.length;j++){
					//iterate over all neighbor states..
					if(oa_map[ai_neighboringState[j]].getStateColor()!=null &&
							oa_map[ai_neighboringState[j]].getStateColor().equals(as_rbg[i])
					){
						flag = false;
						break;
					}
				}//for loop to iterate over all neighboring states over
			}
			if(flag){
				oa_map[i_Counter].setStateColor(as_rbg[i]);
				if(colormap(oa_map,i_Counter+1)){
					return true;
				}//go recursive..
			}//
			
		}//for loop to iterate over all 3 colors over..
		
		return false;
	}//colormap() function over.
	
}
