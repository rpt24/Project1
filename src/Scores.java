import java.lang.Math;

public class Scores 
{
	private String[] dates = new String[5];
	private double[][] scores = new double[5][24];
	private double[] means = new double [5];
	private double[] sd = new double[5];
	
	public Scores(String[] dateArray, double[][] scoreArray) 
	{
		dates = dateArray;
		scores = scoreArray;
	}
	
	public void calculateMeans()
	{
		double tempSum = 0;
		
		for (int i = 0; i < 5; i ++) {
			for (int j = 0; j < 24; j++) {
				tempSum += scores[i][j];
			}
			means[i] = tempSum / 24;
			tempSum = 0;
		}
	}
	
	public void calculateSD()
	{
		double sum = 0;
		double var1 = 0;
		
		for(int i = 0; i < 5; i++) {
			for (int j = 0; j < 24; j++) {
				var1 = (scores[i][j] - means[i]);
				var1 = var1*var1;
				sum += var1;
			}
			sum = sum /24;
			sd[i] = Math.sqrt(sum);
			sum = 0;
		}
	}
	
	public void printData()
	{
		/* print the headings */
		System.out.printf("%-15s","Student");
		for(int i = 0; i < 5; i++) {
			System.out.printf("%-15s", dates[i]);
		}
		System.out.print("\n"); // new line after headings
		
		/* print the scores for each Student */
		int studentNum = 1; // separate var for student number instead of using for loop counter
		for(int i = 0; i < 24; i++) {
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
		for(int i = 0; i < 5; i++) {
			System.out.printf("%-15.2f", means[i]);
		}
		System.out.print("\n");
		
		/* print the standard deviations */
		System.out.printf("%-15s", "SD");
		for(int i = 0; i < 5; i++) {
			System.out.printf("%-15.2f", sd[i]);
		}
		System.out.print("\n");
	}
	
}
