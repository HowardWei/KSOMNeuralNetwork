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
		Shell shell = new Shell();
		shell.setSize(400, 474);
		shell.setText("SWT Application");
		shell.setLayout(null);
		
		trainingInput = new ArrayList<double[]>();
		
		Label lblKsomDimensions = new Label(shell, SWT.NONE);
		lblKsomDimensions.setBounds(5, 5, 113, 21);
		lblKsomDimensions.setText("KSOM Properties");
		
		Label lblRows = new Label(shell, SWT.NONE);
		lblRows.setBounds(5, 32, 129, 21);
		lblRows.setAlignment(SWT.RIGHT);
		lblRows.setText("Rows:");
		
		rowsInput = new Text(shell, SWT.BORDER);
		rowsInput.setBounds(140, 30, 127, 24);
		
		Label lblNeighborhoodSize = new Label(shell, SWT.NONE);
		lblNeighborhoodSize.setBounds(5, 89, 131, 21);
		lblNeighborhoodSize.setText("Neighborhood size:");
		
		neighborhoodSizeInput = new Text(shell, SWT.BORDER);
		neighborhoodSizeInput.setBounds(140, 87, 127, 24);
		
		Label lblColumns = new Label(shell, SWT.NONE);
		lblColumns.setBounds(5, 62, 131, 21);
		lblColumns.setAlignment(SWT.RIGHT);
		lblColumns.setText("Columns:");
		
		colsInput = new Text(shell, SWT.BORDER);
		colsInput.setBounds(140, 59, 127, 24);

		Button btnGenerate = new Button(shell, SWT.NONE);
		btnGenerate.setBounds(140, 117, 127, 26);
		btnGenerate.setText("Generate");
		
		Label lblTrainingData = new Label(shell, SWT.NONE);
		lblTrainingData.setBounds(5, 149, 87, 21);
		lblTrainingData.setText("Training Data");
		
		Button btnSelectColor = new Button(shell, SWT.NONE);
		btnSelectColor.setBounds(140, 176, 127, 26);
		btnSelectColor.setText("Select Color");
		
		Label lblR = new Label(shell, SWT.NONE);
		lblR.setAlignment(SWT.RIGHT);
		lblR.setBounds(107, 208, 27, 21);
		lblR.setText("R:");
		
		Glevels = new Text(shell, SWT.BORDER);
		Glevels.setBounds(140, 260, 127, 24);
		
		Label lblG = new Label(shell, SWT.NONE);
		lblG.setAlignment(SWT.RIGHT);
		lblG.setBounds(107, 262, 27, 21);
		lblG.setText("G:");
		
		Blevels = new Text(shell, SWT.BORDER);
		Blevels.setBounds(140, 233, 127, 24);
		
		Label lblB = new Label(shell, SWT.NONE);
		lblB.setAlignment(SWT.RIGHT);
		lblB.setBounds(107, 235, 27, 21);
		lblB.setText("B:");
		
		Rlevels = new Text(shell, SWT.BORDER);
		Rlevels.setBounds(140, 206, 127, 24);
		
		Button btnAddToTraining = new Button(shell, SWT.NONE);
		btnAddToTraining.setBounds(140, 290, 127, 26);
		btnAddToTraining.setText("Add Training Input");
		
		Button btnTrainButton = new Button(shell, SWT.NONE);
		btnTrainButton.setBounds(182, 394, 60, 26);
		btnTrainButton.setText("Train");

		Button btnReset = new Button(shell, SWT.NONE);
		btnReset.setEnabled(false);
		btnReset.setBounds(313, 394, 61, 26);
		btnReset.setText("Reset");
		
		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setAlignment(SWT.RIGHT);
		lblNewLabel.setBounds(65, 324, 69, 21);
		lblNewLabel.setText("Epochs:");
		
		epochInput = new Text(shell, SWT.BORDER);
		epochInput.setBounds(140, 322, 127, 24);
		
		Button btnResults = new Button(shell, SWT.NONE);
		btnResults.setEnabled(false);
		btnResults.setBounds(248, 394, 59, 26);
		btnResults.setText("Results");
		
		// handlers
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
				JColorChooser colorChooser = new JColorChooser();
				Color chosenColor = colorChooser.showDialog(null, "Color Picker", Color.RED);
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
					btnResults.setEnabled(false);
				}
			}
		});
		
		btnAddToTraining.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				int Rlevel, Blevel, Glevel;
				if(Rlevels.getText().equals("")) {
					Rlevel = 0;
				} else {
					Rlevel = Integer.parseInt(Rlevels.getText());
				}
				if(Glevels.getText().equals("")) {
					Glevel = 0;
				} else {
					Glevel = Integer.parseInt(Blevels.getText());
				}
				if(Blevels.getText().equals("")) {
					Blevel = 0;
				} else {
					Blevel = Integer.parseInt(Glevels.getText());
				}
				
				trainingInput.add(new double[]{Rlevel, Glevel, Blevel});
			}
		});
		
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
}
