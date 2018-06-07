package KSOM;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class KSOMmain {
	
	public static void main(String[] args) {
		ArrayList<double[]> trainingInput = new ArrayList<double[]>();
		// shades of red
		trainingInput.add(new double[]{51,0,0});
		trainingInput.add(new double[]{153,0,0});
		trainingInput.add(new double[]{255,0,0});
		// shades of green
		trainingInput.add(new double[]{0,51,0});
		trainingInput.add(new double[]{0,153,0});
		trainingInput.add(new double[]{0,255,0});
		// shades of blue
		trainingInput.add(new double[]{0,0,51});
		trainingInput.add(new double[]{0,0,153});
		trainingInput.add(new double[]{0,0,255});
		// shades of yellow
		trainingInput.add(new double[]{153,153,0});
		trainingInput.add(new double[]{255,255,0});
		trainingInput.add(new double[]{255,255,153});
		// shades of teal
		trainingInput.add(new double[]{0,153,153});
		trainingInput.add(new double[]{0,255,255});
		trainingInput.add(new double[]{153,255,255});
		// shades of pink
		trainingInput.add(new double[]{153,0,153});
		trainingInput.add(new double[]{255,0,255});
		trainingInput.add(new double[]{255,153,255});
		// shades of grey
		trainingInput.add(new double[]{0,0,0});
		trainingInput.add(new double[]{128,128,128});
		trainingInput.add(new double[]{255,255,255});
		// other shades
		trainingInput.add(new double[]{51,0,0});
		trainingInput.add(new double[]{0,51,0});
		trainingInput.add(new double[]{0,0,51});
		
		KSOM ksom = new KSOM(30, 100, 100);
		trainingInput = KSOM.NormalizeInputData(trainingInput);
		ksom.SetPrintIntervals(Arrays.asList(0, 20, 40, 100, 1000));
		ksom.Train(1000, trainingInput);
	}
} 
