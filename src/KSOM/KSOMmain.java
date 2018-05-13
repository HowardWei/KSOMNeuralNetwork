package KSOM;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class KSOMmain {
	
	public static void main(String[] args) {
		double[][] trainingInput = {
				// shades of red
				{51,0,0},{153,0,0},{255,0,0},
				// shades of green
				{0,51,0},{0,153,0},{0,255,0},
				// shades of blue
				{0,0,51},{0,0,153},{0,0,255},
				// shades of yellow
				{153,153,0},{255,255,0},{255,255,153},
				// shades of teal
				{0,153,153},{0,255,255},{153,255,255},
				// shades of pink
				{153,0,153},{255,0,255},{255,153,255},
				// shades of grey
				{0,0,0},{128,128,128},{255,255,255},
				// other shades
				{51,0,0},{0,51,0},{0,0,51}};
		trainingInput = NormalizeInputData(trainingInput);
		// PrintArray(trainingInput);
		
		KSOM ksom = new KSOM();
		ksom.SetPrintIntervals(Arrays.asList(20, 40, 100, 1000));
		ksom.Train(1000, trainingInput);
	}
	
	// function to normalize input data to values in [0,1]
	public static double[][] NormalizeInputData(double[][] trainingInput) {
		for(int i = 0; i < trainingInput.length; i++) {
			for(int j = 0; j < trainingInput[i].length; j++) {
				trainingInput[i][j] = (double)trainingInput[i][j]/255;
			}
		}
		return trainingInput;
	}

	// prints 2D array for testing purposes
	public static void PrintArray(double[][] trainingInput) {
		for(int i = 0; i < trainingInput.length; i++) {
			for(int j = 0; j < trainingInput[i].length; j++) {
				System.out.print(trainingInput[i][j] + " ");
			}
			System.out.println();
		}
	}
} 
