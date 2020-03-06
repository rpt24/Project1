import java.io.*;

public class Project1Main 
{

	public static void main(String[] args) throws Exception
	{
		File file = new File("/Users/ryanthornton/eclipse-workspace/Project1/data/Scores.txt"); 
		BufferedReader br = new BufferedReader(new FileReader(file)); 
		
		String[] dates = new String[5];
		double[][] scores = new double[5][24];
		
		String st;
		int dateCount = 0;
		int scoreCount = 0;
		int scoreMarker = 0;
		
		while ((st = br.readLine()) != null) 
		{
		  if (dateCount < 5) {
			  dates[dateCount] = st;
			  dateCount++;
		  } else if (scoreCount < 24) {
			  scores[scoreMarker][scoreCount] = Double.valueOf(st);
			  scoreCount++;
			  if (scoreCount > 23) {
				  scoreMarker++;
				  scoreCount = 0;
			  }
		  }
		}

		for (int i = 0; i < 5; i++) {
			System.out.println(dates[i]);
			for (int j = 0; j < 24; j++) {
				System.out.println(scores[i][j]);
			}
		}
		
		br.close();
	}

}
