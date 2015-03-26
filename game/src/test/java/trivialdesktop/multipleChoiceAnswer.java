import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.JCheckBox;


public class multipleChoiceAnswer {

	private JFrame frmMultipleChoiceAnswer;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					multipleChoiceAnswer window = new multipleChoiceAnswer();
					window.frmMultipleChoiceAnswer.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public multipleChoiceAnswer() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmMultipleChoiceAnswer = new JFrame();
		frmMultipleChoiceAnswer.setTitle("Multiple Choice Answer");
		frmMultipleChoiceAnswer.setBounds(100, 100, 623, 398);
		frmMultipleChoiceAnswer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMultipleChoiceAnswer.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		frmMultipleChoiceAnswer.getContentPane().add(panel);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel questionJLabel = new JLabel("Here goes the question");
		questionJLabel.setHorizontalAlignment(SwingConstants.CENTER);
		questionJLabel.setFont(new Font("Calibri", Font.PLAIN, 16));
		panel.add(questionJLabel);
		
		JCheckBox option1RButton = new JCheckBox("Option 1");
		option1RButton.setFont(new Font("Calibri", Font.PLAIN, 12));
		option1RButton.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(option1RButton);
		
		JCheckBox option2RButton = new JCheckBox("Option 2");
		option2RButton.setFont(new Font("Calibri", Font.PLAIN, 12));
		option2RButton.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(option2RButton);
		
		JCheckBox option3RButton = new JCheckBox("Option 3");
		option3RButton.setFont(new Font("Calibri", Font.PLAIN, 12));
		option3RButton.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(option3RButton);
		
		JCheckBox option4RButton = new JCheckBox("Option 4");
		option4RButton.setFont(new Font("Calibri", Font.PLAIN, 12));
		option4RButton.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(option4RButton);
		
		JButton confirmJButton = new JButton("CONFIRM");
		confirmJButton.setFont(new Font("Calibri", Font.BOLD, 20));
		confirmJButton.setForeground(new Color(0, 128, 0));
		panel.add(confirmJButton);
		
		JButton cancelJButton = new JButton("CANCEL");
		cancelJButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		cancelJButton.setForeground(new Color(255, 0, 0));
		cancelJButton.setFont(new Font("Calibri", Font.BOLD, 20));
		panel.add(cancelJButton);
	}

}
