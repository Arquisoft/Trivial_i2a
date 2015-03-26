import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.FlowLayout;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.BoxLayout;
import javax.swing.JLayeredPane;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JRadioButton;
import java.awt.GridLayout;
import java.awt.CardLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Insets;
import javax.swing.ButtonGroup;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JDesktopPane;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class singleChoiceAnswer {

	private JFrame frmSingleChoiceAnswer;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					singleChoiceAnswer window = new singleChoiceAnswer();
					window.frmSingleChoiceAnswer.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public singleChoiceAnswer() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmSingleChoiceAnswer = new JFrame();
		frmSingleChoiceAnswer.setTitle("Single Choice Answer");
		frmSingleChoiceAnswer.setBounds(100, 100, 623, 398);
		frmSingleChoiceAnswer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSingleChoiceAnswer.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		frmSingleChoiceAnswer.getContentPane().add(panel);
		panel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel questionJLabel = new JLabel("Here goes the question");
		questionJLabel.setHorizontalAlignment(SwingConstants.CENTER);
		questionJLabel.setFont(new Font("Calibri", Font.PLAIN, 16));
		panel.add(questionJLabel);
		
		JRadioButton option1RButton = new JRadioButton("Option 1");
		option1RButton.setFont(new Font("Calibri", Font.PLAIN, 12));
		option1RButton.setSelected(true);
		buttonGroup.add(option1RButton);
		option1RButton.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(option1RButton);
		
		JRadioButton option2RButton = new JRadioButton("Option 2");
		option2RButton.setFont(new Font("Calibri", Font.PLAIN, 12));
		buttonGroup.add(option2RButton);
		option2RButton.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(option2RButton);
		
		JRadioButton option3RButton = new JRadioButton("Option 3");
		option3RButton.setFont(new Font("Calibri", Font.PLAIN, 12));
		buttonGroup.add(option3RButton);
		option3RButton.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(option3RButton);
		
		JRadioButton option4RButton = new JRadioButton("Option 4");
		option4RButton.setFont(new Font("Calibri", Font.PLAIN, 12));
		buttonGroup.add(option4RButton);
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
