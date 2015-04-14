package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import main.Principal;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class SelectPlayers extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Create the dialog.
	 * @param principal 
	 * @param boardSize 
	 */
	public SelectPlayers(final Principal principal, final String boardSize) {
		setResizable(false);
		setTitle("Select players");
		setModal(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 275, 134);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNumberOfPlayers = new JLabel("Number of players (2 - 4):");
		lblNumberOfPlayers.setBounds(46, 11, 177, 25);
		contentPanel.add(lblNumberOfPlayers);
		
		JButton btn2Players = new JButton("2");
		btn2Players.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				showBoard(principal, boardSize, 2);
			}
		});
		btn2Players.setBounds(10, 47, 50, 23);
		contentPanel.add(btn2Players);
		
		JButton btn3Players = new JButton("3");
		btn3Players.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				showBoard(principal, boardSize, 3);
			}
		});
		btn3Players.setBounds(107, 47, 50, 23);
		contentPanel.add(btn3Players);
		
		JButton btn4Players = new JButton("4");
		btn4Players.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				showBoard(principal, boardSize, 4);
			}
		});
		btn4Players.setBounds(199, 47, 50, 23);
		contentPanel.add(btn4Players);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
		}
	}

	protected void showBoard(Principal principal, String boardSize, int numberOfPlayers) 
	{
		switch(boardSize)
		{
		case "big":
			principal.showShowBoardBig(numberOfPlayers);
			principal.dispose();
			dispose();
			break;
		case "small":
			principal.showShowBoardSmall(numberOfPlayers);
			principal.dispose();
			dispose();
			break;
		case "mid":
			principal.showShowBoardMid(numberOfPlayers);
			principal.dispose();
			dispose();
			break;
		}
	}
}
