/************************************************************************* 
  Student Name: Ryan Thornton
  File Name: Project1Main.java
  Project 1

  This file is the main file that drives the program. It reads in the
 data file Scores.txt and passes the data into a constructor for the 
 Scores class. It then drives the new Scores object to call its
 calculate functions and lastly print the data
**************************************************************************/
import java.io.*;

public class Project1Main 
{
	
	/*
	 * This is the main function. It drives the program.
	 * It reads in the data from a data file. Creates a new
	 * Scores class object. Then calls thew new Scores
	 * object's calculations and prints its data
	 */
	public static void main(String[] args) throws Exception
	{
		/* Uncomment if developing in Windows */
		File file = new File("C:\\Users\\rptho\\git\\Project1\\data\\Scores.txt");
		/* Uncomment if developing in MacOS */
		//File file = new File("/Users/ryanthornton/eclipse-workspace/Project1/data/Scores.txt");
		BufferedReader br = new BufferedReader(new FileReader(file)); 
		
		String[] dates = new String[5]; // make array to get dates
		double[][] scores = new double[5][24]; // make array to get scores for each date
		
		String st; // stores value of read line
		int dateCount = 0;  // control var date
		int scoreCount = 0; // control var amount of scores
		int scoreDay = 0;   // control var for day of scores
		
		/* while the line isn't null, parse the file and place data in respective arrays */
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
		br.close(); // close file
		
		Scores scoreData = new Scores(dates, scores); // make new Scores class object
		scoreData.calculateMeans(); // calculate the means
		scoreData.calculateSD(); // calculate the standard deviations
		scoreData.tTest(); // compute the t test
		/* after calculations, the data is printed */
		scoreData.printData();
	}

}
