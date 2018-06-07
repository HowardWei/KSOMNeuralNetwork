package KSOM;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JColorChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import java.util.function.Consumer;

public class KSOMgui {
	private static Text rowsInput;
	private static Text colsInput;
	private static Text neighborhoodSizeInput;
	private static Text Glevels;
	private static Text Blevels;
	private static Text Rlevels;
	private static KSOM ksom;
	private static Text epochInput;
	private static ArrayList<double[]> trainingInput;
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		Display display = Display.getDefault();
		Shell shlKsomNeuralNetwork = new Shell();
		shlKsomNeuralNetwork.setSize(400, 474);
		shlKsomNeuralNetwork.setText("KSOM Neural Network");
		shlKsomNeuralNetwork.setLayout(null);
		
		trainingInput = new ArrayList<double[]>();
		
		Label lblKsomDimensions = new Label(shlKsomNeuralNetwork, SWT.NONE);
		lblKsomDimensions.setBounds(5, 5, 113, 21);
		lblKsomDimensions.setText("KSOM Properties");
		
		Label lblRows = new Label(shlKsomNeuralNetwork, SWT.NONE);
		lblRows.setBounds(5, 32, 129, 21);
		lblRows.setAlignment(SWT.RIGHT);
		lblRows.setText("Rows:");
		
		rowsInput = new Text(shlKsomNeuralNetwork, SWT.BORDER);
		rowsInput.setBounds(140, 30, 127, 24);
		
		Label lblNeighborhoodSize = new Label(shlKsomNeuralNetwork, SWT.NONE);
		lblNeighborhoodSize.setBounds(5, 89, 131, 21);
		lblNeighborhoodSize.setText("Neighborhood size:");
		
		neighborhoodSizeInput = new Text(shlKsomNeuralNetwork, SWT.BORDER);
		neighborhoodSizeInput.setBounds(140, 87, 127, 24);
		
		Label lblColumns = new Label(shlKsomNeuralNetwork, SWT.NONE);
		lblColumns.setBounds(5, 62, 131, 21);
		lblColumns.setAlignment(SWT.RIGHT);
		lblColumns.setText("Columns:");
		
		colsInput = new Text(shlKsomNeuralNetwork, SWT.BORDER);
		colsInput.setBounds(140, 59, 127, 24);

		Button btnGenerate = new Button(shlKsomNeuralNetwork, SWT.NONE);
		btnGenerate.setBounds(140, 117, 127, 26);
		btnGenerate.setText("Generate");
		
		Label lblTrainingData = new Label(shlKsomNeuralNetwork, SWT.NONE);
		lblTrainingData.setBounds(5, 149, 87, 21);
		lblTrainingData.setText("Training Data");
		
		Button btnSelectColor = new Button(shlKsomNeuralNetwork, SWT.NONE);
		btnSelectColor.setBounds(140, 176, 127, 26);
		btnSelectColor.setText("Select Color");
		
		Label lblR = new Label(shlKsomNeuralNetwork, SWT.NONE);
		lblR.setAlignment(SWT.RIGHT);
		lblR.setBounds(107, 208, 27, 21);
		lblR.setText("R:");
		
		Glevels = new Text(shlKsomNeuralNetwork, SWT.BORDER);
		Glevels.setBounds(140, 260, 127, 24);
		
		Label lblG = new Label(shlKsomNeuralNetwork, SWT.NONE);
		lblG.setAlignment(SWT.RIGHT);
		lblG.setBounds(107, 262, 27, 21);
		lblG.setText("G:");
		
		Blevels = new Text(shlKsomNeuralNetwork, SWT.BORDER);
		Blevels.setBounds(140, 233, 127, 24);
		
		Label lblB = new Label(shlKsomNeuralNetwork, SWT.NONE);
		lblB.setAlignment(SWT.RIGHT);
		lblB.setBounds(107, 235, 27, 21);
		lblB.setText("B:");
		
		Rlevels = new Text(shlKsomNeuralNetwork, SWT.BORDER);
		Rlevels.setBounds(140, 206, 127, 24);
		
		Button btnAddToTraining = new Button(shlKsomNeuralNetwork, SWT.NONE);
		btnAddToTraining.setBounds(140, 290, 127, 26);
		btnAddToTraining.setText("Add Input");
		
		Button btnTrainButton = new Button(shlKsomNeuralNetwork, SWT.NONE);
		btnTrainButton.setBounds(10, 394, 144, 26);
		btnTrainButton.setText("Train");

		Button btnReset = new Button(shlKsomNeuralNetwork, SWT.NONE);
		btnReset.setEnabled(false);
		btnReset.setBounds(303, 394, 69, 26);
		btnReset.setText("Reset");
		
		Label lblNewLabel = new Label(shlKsomNeuralNetwork, SWT.NONE);
		lblNewLabel.setAlignment(SWT.RIGHT);
		lblNewLabel.setBounds(65, 324, 69, 21);
		lblNewLabel.setText("Epochs:");
		
		epochInput = new Text(shlKsomNeuralNetwork, SWT.BORDER);
		epochInput.setBounds(140, 322, 127, 24);
		
		Button btnResults = new Button(shlKsomNeuralNetwork, SWT.NONE);
		btnResults.setEnabled(false);
		btnResults.setBounds(160, 394, 137, 26);
		btnResults.setText("Results");
		
		Button btnNewButton = new Button(shlKsomNeuralNetwork, SWT.NONE);
		btnNewButton.setBounds(272, 290, 102, 26);
		btnNewButton.setText("View Inputs");
		
		Button btnUndo = new Button(shlKsomNeuralNetwork, SWT.NONE);
		btnUndo.setBounds(49, 290, 85, 26);
		btnUndo.setText("Undo");
		
		Button btnNewButton_1 = new Button(shlKsomNeuralNetwork, SWT.NONE);
		btnNewButton_1.setBounds(273, 176, 99, 26);
		btnNewButton_1.setText("Load Inputs");
		
		// handlers ---------------------------------------------------------------------------------------------

		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
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
			}
		});
		
		btnUndo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				trainingInput.remove(trainingInput.size() - 1);
			}
		});
		
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				KSOMinputs ksomInputs = new KSOMinputs(trainingInput);
				ksomInputs.setVisible(true);
			}
		});
		
		btnResults.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				KSOMresults ksomResults = new KSOMresults();
				ksomResults.setVisible(true);
			}
		});		
		
		btnSelectColor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				Color chosenColor = JColorChooser.showDialog(null, "Color Picker", Color.RED);
				Rlevels.setText(String.valueOf(chosenColor.getRed()));
				Glevels.setText(String.valueOf(chosenColor.getGreen()));
				Blevels.setText(String.valueOf(chosenColor.getBlue()));
			}
		});

		btnGenerate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				if(rowsInput.getText().equals("") || colsInput.getText().equals("") || neighborhoodSizeInput.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Please fill out all fields (rows, columns, neighborhood size)", "Invalid Field", JOptionPane.ERROR_MESSAGE);
				} else {
					int rows = Integer.parseInt(rowsInput.getText());
					int cols = Integer.parseInt(colsInput.getText());
					int nSize = Integer.parseInt(neighborhoodSizeInput.getText());
					ksom = new KSOM(nSize, rows, cols);
					ksom.SetPrintIntervals(Arrays.asList(0, 20, 40, 100, 1000));
					btnReset.setEnabled(true);
					btnResults.setEnabled(false);
				}
			}
		});
		
		btnReset.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				ksom.ResetKSOM();
				trainingInput = new ArrayList<double[]>();
			}
		});
		
		btnTrainButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				if(epochInput.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Please enter the number of training epochs", "Invalid Field", JOptionPane.ERROR_MESSAGE);
				} else {
					int epochs = Integer.parseInt(epochInput.getText());
					trainingInput = KSOM.NormalizeInputData(trainingInput);
					ksom.Train(epochs, trainingInput);
					btnResults.setEnabled(true);
				}
			}
		});
		
		btnAddToTraining.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				int Rlevel, Blevel, Glevel;
				if(Rlevels.getText().equals("") || Glevels.getText().equals("") || Blevels.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Please enter a value for R,G,B", "Invalid Field", JOptionPane.ERROR_MESSAGE);
				} else {
					Rlevel = Integer.parseInt(Rlevels.getText());
					Glevel = Integer.parseInt(Blevels.getText());
					Blevel = Integer.parseInt(Glevels.getText());
					trainingInput.add(new double[]{Rlevel, Glevel, Blevel});
					Rlevels.setText("");
					Glevels.setText("");
					Blevels.setText("");
				}
				
			}
		});
		
		shlKsomNeuralNetwork.open();
		shlKsomNeuralNetwork.layout();
		while (!shlKsomNeuralNetwork.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
}
