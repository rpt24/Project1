import java.io.*;

public class Project1Main 
{

	public static void main(String[] args) throws Exception
	{
		File file = new File("/Users/ryanthornton/eclipse-workspace/Project1/data/Scores.txt"); 
	  
		BufferedReader br = new BufferedReader(new FileReader(file)); 
		  
		String st; 
		while ((st = br.readLine()) != null) {
		  System.out.println(st); 
		}
		
		br.close();
	}

}
