import java.io.*;

public class Project1Main 
{

	public static void main(String[] args) throws Exception
	{
		/* Uncomment if developing in Windows */
		File file = new File("C:\\Users\\rptho\\git\\Project1\\data\\Scores.txt");
		/* Uncomment if developing in MacOS */
		//File file = new File("/Users/ryanthornton/eclipse-workspace/Project1/data/Scores.txt");
		BufferedReader br = new BufferedReader(new FileReader(file)); 
		
		String[] dates = new String[5];
		double[][] scores = new double[5][24];
		
		String st;
		int dateCount = 0;
		int scoreCount = 0;
		int scoreDay = 0;
		
		while ((st = br.readLine()) != null) 
		{
		  if (dateCount < 5) {
			  dates[dateCount] = st;
			  dateCount++;
		  } else if (scoreCount < 24) {
			  scores[scoreDay][scoreCount] = Double.valueOf(st);
			  scoreCount++;
			  if (scoreCount > 23) {
				  scoreDay++;
				  scoreCount = 0;
			  }
		  }
		}
		br.close();
		
		Scores scoreData = new Scores(dates, scores);
		scoreData.calculateMeans();
		
		scoreData.printData();
	}

}
