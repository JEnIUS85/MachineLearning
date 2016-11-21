package sample;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.Vector;

public class dummy {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		int i = 10;
		double j = 0.9;
			while(true){				
				if (i <=5)
				{	System.out.println("Value of i="+i);
					break;
				}
				else{
					i--;
					int k = (int) (1 + (0.1)*i);
					System.out.println("K = "+ k);
					System.out.println("Value of i="+i);
					
					String str = "kushalhs, mayurvm, narendrabz,";
					str = str.replaceAll(",$", "");
					System.out.println(str);
					
					continue;
				}				
			}//while over..
			ArrayList k = new ArrayList();
	        ArrayList v = new ArrayList();
	        Set<String> hs = new HashSet<String>();
	        Scanner scan = new Scanner(new File("C:\\abc.properties"));
	        while(scan.hasNextLine()) {
	            //System.out.println(scan.nextLine());
	            String split[] = scan.nextLine().trim().split("=");
	            if(split.length == 2) {
	            k.add(split[0]);
	            v.add(split[1]);
	            hs.add(split[1]);
	              System.out.println("pair " + split[0] + ":" + split[1]);
	            }	            
	        }//while loop over.
	        System.out.println(k);
            System.out.println(v);
            v.clear();
            v.addAll(hs);
            System.out.println(v);
            for (String s : hs) {
                System.out.println(s);
            }//for loop over..
            Vector v1 = new Vector();
            v1.add(10);
            v1.add(11);
            v1.add(12);
            v1.add(13);
            System.out.println("Vector size = " + v1.size());              
            for(int ii=0;ii<v1.size();ii++){
            	System.out.println("Vector element= " +v1.elementAt(ii));
            }           
	}

}
