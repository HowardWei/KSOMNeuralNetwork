package KSOM;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class KSOMresults extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public KSOMresults() {
		setAlwaysOnTop(true);
		setTitle("KSOM Results");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1196, 494);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblEpoch0 = new JLabel("");
		lblEpoch0.setBounds(26, 89, 200, 200);
		ImageIcon image = new ImageIcon("neuronMapColoredEpoch0.jpg");  
		lblEpoch0.setIcon(image);   
		contentPane.add(lblEpoch0);
		
		JLabel lblEpoch20 = new JLabel("");
		lblEpoch20.setBounds(252, 89, 200, 200);
		image = new ImageIcon("neuronMapColoredEpoch20.jpg");   
		lblEpoch20.setIcon(image);  
		contentPane.add(lblEpoch20);
		
		JLabel lblEpoch40 = new JLabel("");
		lblEpoch40.setBounds(485, 89, 200, 200);
		image = new ImageIcon("neuronMapColoredEpoch40.jpg");  
		lblEpoch40.setIcon(image);   
		contentPane.add(lblEpoch40);
		
		JLabel lblEpoch100 = new JLabel("");
		lblEpoch100.setBounds(711, 89, 200, 200);
		image = new ImageIcon("neuronMapColoredEpoch100.jpg");   
		lblEpoch100.setIcon(image);  
		contentPane.add(lblEpoch100);

		
		JLabel lblEpoch1000 = new JLabel("");
		lblEpoch1000.setBounds(938, 89, 200, 200);
		image = new ImageIcon("neuronMapColoredEpoch1000.jpg");    
		lblEpoch1000.setIcon(image); 
		contentPane.add(lblEpoch1000);
		
		JLabel lblEpoch = new JLabel("Epoch 0");
		lblEpoch.setBounds(69, 28, 115, 33);
		contentPane.add(lblEpoch);
		
		JLabel lblEpoch_1 = new JLabel("Epoch 20");
		lblEpoch_1.setBounds(293, 28, 115, 33);
		contentPane.add(lblEpoch_1);
		
		JLabel lblEpoch_2 = new JLabel("Epoch 40");
		lblEpoch_2.setBounds(527, 28, 115, 33);
		contentPane.add(lblEpoch_2);
		
		JLabel lblEpoch_3 = new JLabel("Epoch 100");
		lblEpoch_3.setBounds(743, 28, 130, 33);
		contentPane.add(lblEpoch_3);
		
		JLabel lblEpoch_4 = new JLabel("Epoch 1000");
		lblEpoch_4.setBounds(970, 28, 140, 33);
		contentPane.add(lblEpoch_4);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				ImageIcon image = new ImageIcon("neuronMapColoredEpoch0.jpg");  
				lblEpoch0.setIcon(image);   
				contentPane.add(lblEpoch0);

				image = new ImageIcon("neuronMapColoredEpoch20.jpg");   
				lblEpoch20.setIcon(image);  
				contentPane.add(lblEpoch20);

				image = new ImageIcon("neuronMapColoredEpoch40.jpg");  
				lblEpoch40.setIcon(image);   
				contentPane.add(lblEpoch40);

				image = new ImageIcon("neuronMapColoredEpoch100.jpg");   
				lblEpoch100.setIcon(image);  
				contentPane.add(lblEpoch100);

				image = new ImageIcon("neuronMapColoredEpoch1000.jpg");    
				lblEpoch1000.setIcon(image); 
				contentPane.add(lblEpoch1000);
			}
		});
		btnNewButton.setBounds(505, 337, 171, 41);
		contentPane.add(btnNewButton);
	}
}
