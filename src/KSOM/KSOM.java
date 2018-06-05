package KSOM;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;

public class KSOM {
	private int neighborhoodWidth;
	private List<Integer> printIntervals = new ArrayList<Integer>();
	private int totalEpochs;
	@SuppressWarnings("rawtypes")
	private ArrayList[][] neurons; 
	
	private enum NeuronTypes { TYPE_A, TYPE_B, TYPE_C, TYPE_D, TYPE_E, TYPE_F }
	
	public KSOM() {
		neighborhoodWidth = 20;
		neurons = new ArrayList[100][100];
		neurons = InitializeNeurons(neurons);
	}
	
	public KSOM(int neighborhoodWidth, int neuronGridX, int neuronGridY) {
		this.neighborhoodWidth = neighborhoodWidth;
		neurons = new ArrayList[neuronGridX][neuronGridY];
		neurons = InitializeNeurons(neurons);
	}

	public void SetPrintIntervals(List<Integer> intervals) {
		printIntervals.addAll(intervals);
	}
	
	// training algorithm for the neural network
	public void Train(int epochs, double[][] trainingInput) {
		Random rand = new Random();
		this.totalEpochs = epochs;
		boolean decreaseNeighborhood = false;
		double winningDistance = Math.sqrt(3);
		double tempDistance;
		int originalNeighborhoodWidth = this.neighborhoodWidth;
		int winningX = 0;
		int winningY = 0;

		PrintNeuronMap(0);
		// each epoch
		for(int i = 0; i < totalEpochs; i++) {
			// each training input
			for(int j = 0; j < trainingInput.length; j++) {	
				winningDistance = Math.sqrt(3);
				// find the winning neuron
				for(int x = 0; x < 100; x++) {
					for(int y = 0; y < 100; y++) {
						tempDistance = CalculateDistance(x, y, trainingInput[j]);
						if (GetNeuronType(trainingInput[j]) == GetNeuronType(x, y)) {
							if(tempDistance < winningDistance) {
								winningDistance = tempDistance;
								winningX = x;
								winningY = y;
							} else if (tempDistance == winningDistance) {
								if(rand.nextFloat() > 0.5) {
									winningX = x;
									winningY = y;
								}
							}
						}
					}
				}
				UpdateNeighborhood(i + 1, winningX, winningY, trainingInput[j]);
			}
			
			if(neighborhoodWidth > 0) {
				if(decreaseNeighborhood == true) {
					neighborhoodWidth -= 1;
					decreaseNeighborhood = false;
				} else {
					decreaseNeighborhood = true;
				}
			}
			
			PrintNeuronMap(i + 1);
		}
	}
	
	// find and update the weights of the winning neighborhood
	private void UpdateNeighborhood(int epoch, int neuronX, int neuronY, double[] input) {		
		int Xmax = neuronX + neighborhoodWidth;
		if(Xmax > 99) {
			Xmax = 99;
		}
		int Xmin = neuronX - neighborhoodWidth;
		if(Xmin < 0) {
			Xmin = 0;
		}
		int Ymax = neuronY + neighborhoodWidth;
		if(Ymax > 99) {
			Ymax = 99;
		}
		int Ymin = neuronY - neighborhoodWidth;
		if(Ymin < 0) {
			Ymin = 0;
		}
		
		for(int x = Xmin; x <= Xmax; x++) {
			for(int y = Ymin; y <= Ymax; y++) {
				double learningRate = GetLearningRate(epoch, neuronX, neuronY, x, y);
				UpdateWeight(learningRate, x, y, input);
			}
		}
	}
	
	// update weights for winning neuron
	@SuppressWarnings("unchecked")
	private void UpdateWeight(double learningRate, int neuronX, int neuronY, double[] input) {		
		// R
		double oldWeight = (double)neurons[neuronX][neuronY].get(0);
		double newWeight = oldWeight + learningRate*(input[0] - oldWeight);
		neurons[neuronX][neuronY].set(0, (double)newWeight);
		
		// G
		oldWeight = (double)neurons[neuronX][neuronY].get(1);
		newWeight = oldWeight + learningRate*(input[1] - oldWeight);
		neurons[neuronX][neuronY].set(1, (double)newWeight);
		
		// B
		oldWeight = (double)neurons[neuronX][neuronY].get(2);
		newWeight = oldWeight + learningRate*(input[2] - oldWeight);
		neurons[neuronX][neuronY].set(2, (double)newWeight);
	}
	
	// calculates Euclidean distance
	private double CalculateDistance(int neuronX, int neuronY, double[] input) {
		double Rdistance = Math.pow((double)neurons[neuronX][neuronY].get(0) - input[0], 2);
		double Gdistance = Math.pow((double)neurons[neuronX][neuronY].get(1) - input[1], 2);
		double Bdistance = Math.pow((double)neurons[neuronX][neuronY].get(2) - input[2], 2);
		return Math.sqrt(Rdistance + Gdistance + Bdistance);
	}
	
	// returns learning rate a(k) with a(0) = 0.8, constant after 90th epoch
	private double GetLearningRate(int epoch, int winningX, int winningY, int neuronX, int neuronY) {
		// adjustment to account for epoch, higher epochs have less effect
		double timeAdjustment = 1 - epoch/(totalEpochs + 1);
		// adjustment to account for distance from winning neuron, further neurons have less effect
		double adjustment = neighborhoodWidth - (double)epoch/100;
		if (adjustment <= 0)
			adjustment = 0.1;
		double distanceAdjustment = Math.exp(-GetDistance(winningX, winningY, neuronX, neuronX) / adjustment);
		return timeAdjustment*distanceAdjustment;
	}
	
	// initializes the weights of each neuron of the 2D neuron array
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private ArrayList[][] InitializeNeurons(ArrayList[][] neurons) {
		Random rand = new Random();
		for(int i = 0; i < neurons.length; i++) {
			for(int j = 0; j < neurons[i].length; j++) {
				// R:0, G:1, B:2
				neurons[i][j] = new ArrayList<Double>();
				neurons[i][j].add(0, (double) rand.nextFloat());
				neurons[i][j].add(1, (double) rand.nextFloat());
				neurons[i][j].add(2, (double) rand.nextFloat());
			}	
		}
		return neurons;
	}
	
	// prints 2D colored neuron array to file
	private void PrintNeuronMap(int epoch) {
		try {
			if(printIntervals.contains(epoch)) {
				BufferedImage coloredMap = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
				
				for(int i = 0; i < neurons.length; i++) {
					for(int j = 0; j < neurons[i].length; j++) {
						int Rvalue = (int) Math.rint((double)neurons[i][j].get(0)*255);
						int Gvalue = (int) Math.rint((double)neurons[i][j].get(1)*255);
						int Bvalue = (int) Math.rint((double)neurons[i][j].get(2)*255);
						
						Color neuronColor = new Color(Rvalue, Gvalue, Bvalue);
						coloredMap.setRGB(i, j, neuronColor.getRGB());
					}
				}
				ImageIO.write(coloredMap, "jpg", new File("neuronMapColoredEpoch" + epoch + ".jpg"));
			}
		} catch (IOException e) {
			System.out.println(e.toString());
		}
	}
	
	private NeuronTypes GetNeuronType(int neuronX, int neuronY) {
		double[] tempArray = new double[3];
		tempArray[0] = (double)neurons[neuronX][neuronY].get(0);
		tempArray[1] = (double)neurons[neuronX][neuronY].get(1);
		tempArray[2] = (double)neurons[neuronX][neuronY].get(2);
		return GetNeuronType(tempArray);
	}
	
	private NeuronTypes GetNeuronType(double[] neuron) {

		// this is somewhat lazy copy-paste coding... need to fix
		if(neuron[0] >= neuron[1] && neuron[0] >= neuron[2]) {
			if(neuron[1] >= neuron[2]) {
				// R >= G >= B
				return NeuronTypes.TYPE_A;
			} else {
				// R >= B >= G
				return NeuronTypes.TYPE_B;
			}
		} else if (neuron[1] >= neuron[0] && neuron[1] >= neuron[2]) {
			if(neuron[0] >= neuron[2]) {
				// G >= R >= B
				return NeuronTypes.TYPE_C;
			} else {
				// G >= B >= R
				return NeuronTypes.TYPE_D;
			}
		} else if (neuron[2] >= neuron[0] && neuron[2] >= neuron[1]) {
			if(neuron[0] >= neuron[1]) {
				// B >= R >= B
				return NeuronTypes.TYPE_E;
			} else {
				// B >= G >= R
				return NeuronTypes.TYPE_F;
			}
		}
		
		return null;
	}
	
	private double GetDistance(int x1, int y1, int x2, int y2) {
		return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
	}
}
