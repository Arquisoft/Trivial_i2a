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


public class trueOrFalseAnswer {

	private JFrame frmTrueOrFalseAnswer;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					trueOrFalseAnswer window = new trueOrFalseAnswer();
					window.frmTrueOrFalseAnswer.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public trueOrFalseAnswer() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmTrueOrFalseAnswer = new JFrame();
		frmTrueOrFalseAnswer.setTitle("True or False Answer");
		frmTrueOrFalseAnswer.setBounds(100, 100, 623, 398);
		frmTrueOrFalseAnswer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmTrueOrFalseAnswer.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		frmTrueOrFalseAnswer.getContentPane().add(panel);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel questionJLabel = new JLabel("Here goes the question");
		questionJLabel.setHorizontalAlignment(SwingConstants.CENTER);
		questionJLabel.setFont(new Font("Calibri", Font.PLAIN, 16));
		panel.add(questionJLabel);
		
		JRadioButton option1RButton = new JRadioButton("T R U E");
		option1RButton.setFont(new Font("Calibri", Font.PLAIN, 12));
		option1RButton.setSelected(true);
		buttonGroup.add(option1RButton);
		option1RButton.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(option1RButton);
		
		JRadioButton option2RButton = new JRadioButton("F A L S E");
		option2RButton.setFont(new Font("Calibri", Font.PLAIN, 12));
		buttonGroup.add(option2RButton);
		option2RButton.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(option2RButton);
		
		
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
