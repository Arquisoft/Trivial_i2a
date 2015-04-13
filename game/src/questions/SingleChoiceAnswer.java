package questions;

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

import model.Game;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Map;


public class SingleChoiceAnswer {

	private JFrame frmSingleChoiceAnswer;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private String question;
	private Map<String, Boolean> answers;
	private String rightAnswer;
	private Game game;
	private JLabel questionJLabel;
	private JRadioButton option1RButton;
	private JRadioButton option2RButton;
	private JRadioButton option3RButton;
	private JRadioButton option4RButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SingleChoiceAnswer window = new SingleChoiceAnswer();
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
	public SingleChoiceAnswer() {
		initialize();
	}

	public SingleChoiceAnswer(String question, Map<String, Boolean> answers,
			String rightAnswer, Game game) {
		this.question = question;
		this.answers = answers;
		this.rightAnswer = rightAnswer;
		this.game = game;
		
		initialize();
		
		option1RButton.setText((String) answers.keySet().toArray()[0]);
		option2RButton.setText((String) answers.keySet().toArray()[1]);
		option3RButton.setText((String) answers.keySet().toArray()[2]);
		option4RButton.setText((String) answers.keySet().toArray()[3]);
		
		questionJLabel.setText(question);
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
		
		questionJLabel = new JLabel("Here goes the question");
		questionJLabel.setHorizontalAlignment(SwingConstants.CENTER);
		questionJLabel.setFont(new Font("Calibri", Font.PLAIN, 16));
		panel.add(questionJLabel);
		
		option1RButton = new JRadioButton("Option 1");
		option1RButton.setFont(new Font("Calibri", Font.PLAIN, 12));
		option1RButton.setSelected(true);
		buttonGroup.add(option1RButton);
		option1RButton.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(option1RButton);
		
		option2RButton = new JRadioButton("Option 2");
		option2RButton.setFont(new Font("Calibri", Font.PLAIN, 12));
		buttonGroup.add(option2RButton);
		option2RButton.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(option2RButton);
		
		option3RButton = new JRadioButton("Option 3");
		option3RButton.setFont(new Font("Calibri", Font.PLAIN, 12));
		buttonGroup.add(option3RButton);
		option3RButton.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(option3RButton);
		
		option4RButton = new JRadioButton("Option 4");
		option4RButton.setFont(new Font("Calibri", Font.PLAIN, 12));
		buttonGroup.add(option4RButton);
		option4RButton.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(option4RButton);
		
		JButton confirmJButton = new JButton("CONFIRM");
		confirmJButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				game.nextTurn();
				System.exit(0);
			}
		});
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