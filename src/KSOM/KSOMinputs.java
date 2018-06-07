package KSOM;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import java.awt.GridLayout;
import java.util.ArrayList;

public class KSOMinputs extends JFrame {

	private JPanel contentPane;
	/**
	 * Create the frame.
	 */
	public KSOMinputs(ArrayList<double[]> trainingInputs) {
		setTitle("KSOM Training Inputs");
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 393, 552);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 1, 0, 0));
		
		JTextArea txtTrainingInput = new JTextArea();
		txtTrainingInput.setEditable(false);
		contentPane.add(txtTrainingInput);
		
		for(int i = 0; i < trainingInputs.size(); i++) {
			int rValue = (int)trainingInputs.get(i)[0];
			int gValue = (int)trainingInputs.get(i)[1];
			int bValue = (int)trainingInputs.get(i)[2];
			
			txtTrainingInput.append("R: " + Integer.toString(rValue) + " G: " + Integer.toString(gValue)  + " B: " + Integer.toString(bValue) + "\n");
		}
	}
}
