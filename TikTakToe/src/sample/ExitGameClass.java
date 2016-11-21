package sample;

public class ExitGameClass {
	
	public void exitGame(){
		try{
			 Thread.sleep(3000);
			 System.exit(0);
		 }catch(Exception e){
			 e.printStackTrace();
		 }
	}// exitGame() method over..
}
