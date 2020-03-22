/************************************************************************* 
  Student Name: Ryan Thornton
  File Name: Scores.java
  Project 1

  This file is the Scores class. It contains private arrays for 
 everything that needs to be calculated. It has functions to calculate
 the needed variables, and a function to print all data.
**************************************************************************/
import java.lang.Math;

public class Scores 
{
	private String[] dates = new String[5]; // private array for holding the dates from the data file
	private double[][] scores = new double[5][24]; // private 2-D array for holding the scores for each date from the data file
	private double[] means = new double [5]; // holds the mean for each day when calculateMeans() is called and run
	private double[] sd = new double[5]; // holds the standard deviation for each day when calculateSD() is called and run
	private char[][] ttest = new char[5][5]; // holds the t test results of Y or N when tTest() is called and run
	
	/*
	 * These control variables prevent from hardcoding the loop value to a single number
	 * everywhere. These values are known from the project requirements, and if the value 
	 * changed, then it would be easier for the programmer to change this value for all 
	 * loops used.
	 * 
	 * MAX_DATES - control variable for loops, 5 dates are read in from the data file
	 * MAX_SCORES - control variable for loops, 24 scores for each date from the data file
	 */
	private int MAX_DATES = 5;
	private int MAX_SCORES = 24;
	
	/*
	 * Constructor for the Scores class which sets its private arrays dates and scores
	 * and then initializes the ttest array with null values.
	 * 
	 *  @param - dateArray passes in the array of dates from the main which read the data file
	 *  @param - scoreArray passes in the array of scores from the main which read the data file
	 */
	public Scores(String[] dateArray, double[][] scoreArray) 
	{
		dates = dateArray;
		scores = scoreArray;
		
		/* fill ttest array w/ null values */
		for (int i = 0; i < MAX_DATES; i++) {
			for (int j = i + 1; j < MAX_DATES; j++) {
				ttest[i][j] = ' ';
			}
		}
	}
	
	/*
	 * This function calculates the means of each date and
	 * stores them in the private array means[]
	 */
	public void calculateMeans()
	{
		double tempSum = 0;
		
		/* Loop over each date, then in the nested loop goes over each data value for the date */
		for (int i = 0; i < MAX_DATES; i ++) {
			for (int j = 0; j < MAX_SCORES; j++) {
				tempSum += scores[i][j]; // sums up the 24 data points into a temporary var of sum
			}
			means[i] = tempSum / MAX_SCORES; // divides sum by the known amount of scores for each date
			tempSum = 0; // zero out the sum for reuse
		}
	}
	
	/*
	 * This function calculates the standard deviation for
	 * each date and stores the SD in the privary array sd[]
	 */
	public void calculateSD()
	{
		double sum = 0;
		double var1 = 0;
		
		for(int i = 0; i < MAX_DATES; i++) {
			for (int j = 0; j < MAX_SCORES; j++) {
				var1 = (scores[i][j] - means[i]); // subtract the mean from the current data point
				var1 = var1*var1; // square the data - mean
				sum += var1; // add (data-mean)^2 to the total sum
			}
			sum = sum / MAX_SCORES; // divide the sum by amount of data (max scores = 24)
			sd[i] = Math.sqrt(sum); // square root the sum / 24 and add to sd array
			sum = 0; // zero out the sum for reuse
		}
	}
	
	/*
	 * This function computes the t test of a date
	 * against all previous dates before it.
	 */
	public void tTest()
	{
		// declare variables for calculation, init to zero
		double sum1 = 0;
		double sum2 = 0;
		double Ssq = 0;
		double denom = 0;
		double t = 0;
		
		for (int i = 0; i < MAX_DATES; i++) {
			for (int j = i + 1; j < MAX_DATES; j++) {
				sum1 = (sd[i] * sd[i]) * MAX_SCORES; // use the previously calculated standard deviation to get sum of (xi - mu)^2
				sum2 = (sd[j] * sd[j]) * MAX_SCORES; // ^same as before for sum2
				
				Ssq = (sum1 + sum2) / 46; // 46 = (24 + 24 - 2) - gets the common variance between the two dates (sums/46)
				denom = 2 * (Ssq / MAX_SCORES); // denominator for the ttest calculated 
				
				t = (means[i] - means[j]) / (Math.sqrt(denom)); // calculate final t for t test
				t = Math.abs(t); // take abs value of t
				
				/* if t > 2.25 set the ttest between these two dates to Y for yes sig. difference, else it's N for no */
				if (t > 2.25) {
					ttest[i][j] = 'Y';
				} else {
					ttest[i][j] = 'N';
				}
			}
		}
	}
	
	/*
	 * This function prints the data and should be called
	 * after all calculations are complete.
	 */
	public void printData()
	{
		/* print the headings */
		System.out.printf("%-15s","Student");
		for(int i = 0; i < MAX_DATES; i++) {
			System.out.printf("%-15s", dates[i]); // date for each heading
		}
		System.out.print("\n"); // new line after headings
		
		/* print the scores for each Student */
		int studentNum = 1; // separate var for student number instead of using for loop counter
		for(int i = 0; i < MAX_SCORES; i++) {
			System.out.format("%-15d%-15.2f%-15.2f%-15.2f%-15.2f%-15.2f\n", studentNum, scores[0][i], scores[1][i], 
					scores[2][i], scores[3][i], scores[4][i]);
			studentNum++;
		}
		
		/* print the separator for the calculated values */
		for(int star = 0; star < 80; star++) {
			System.out.print("*");
		}
		System.out.print("\n");
		
		/* print the means */
		System.out.printf("%-15s", "Mean");
		for(int i = 0; i < MAX_DATES; i++) {
			System.out.printf("%-15.2f", means[i]);
		}
		System.out.print("\n");
		
		/* print the standard deviations */
		System.out.printf("%-15s", "SD");
		for(int i = 0; i < MAX_DATES; i++) {
			System.out.printf("%-15.2f", sd[i]);
		}
		System.out.print("\n");
		
		/* Print the t test results in a formatted table */
		System.out.print("\nSignificant Differences in Mean Scores\n"); // heading
		System.out.printf("%-15s", ""); // buffer for y axis of dates
		
		for(int i = 1; i < MAX_DATES; i++) {
			System.out.printf("%-15s", dates[i]); // print date headings
		}
		System.out.print("\n"); // new line after headings
		
		for (int i = 0; i < MAX_DATES; i++) {
			System.out.printf("%-15s", dates[i]); // print current date
			for (int j = 1; j < MAX_DATES; j++) {
				System.out.format("%-15c", ttest[i][j]); // print current date's t test results
			}
			System.out.print("\n"); // new line after row
		}
	}
	
}
