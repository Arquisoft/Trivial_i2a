package main;

import gui.BoardGui;
import gui.SelectPlayers;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Board;
import model.Game;

public class Principal extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4741437948991199474L;
	private JPanel contentPane;
	private JPanel pnTitle;
	private JLabel label;
	private JPanel pnOption;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;
	private JPanel panel;
	private JButton btnNewButton_3;
	private static Game game;
	static Principal frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new Principal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	

	public void showShowBoardSmall(int numberOfPlayers) {
		Board board = new Board(9, numberOfPlayers);
		Game game = new Game(board);
		Principal.game = game;
		BoardGui small = new BoardGui(this,9,game, numberOfPlayers);
		small.setLocationRelativeTo(this);
		small.setDefaultCloseOperation(EXIT_ON_CLOSE);
		small.setVisible(true);
	}
	
	public void showShowBoardBig(int numberOfPlayers) {
		Board board = new Board(17, numberOfPlayers);
		Game game = new Game(board);
		Principal.game = game;
		BoardGui big = new BoardGui(this,17,game, numberOfPlayers);
		big.setLocationRelativeTo(this);
		big.setDefaultCloseOperation(EXIT_ON_CLOSE);
		big.setVisible(true);
	}
	
	public void showShowBoardMid(int numberOfPlayers) {
		Board board = new Board(13, numberOfPlayers);
		Game game = new Game(board);
		Principal.game = game;
		BoardGui mid = new BoardGui(this,13,game, numberOfPlayers);
		mid.setLocationRelativeTo(this);
		mid.setDefaultCloseOperation(EXIT_ON_CLOSE);
		mid.setVisible(true);
	}
	
	
	
	
	/**
	 * Create the frame.
	 */
	public Principal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getPnTitle(), BorderLayout.NORTH);
		contentPane.add(getPnOption(), BorderLayout.CENTER);
		contentPane.add(getPanel(), BorderLayout.SOUTH);
	}
	private JPanel getPnTitle() {
		if (pnTitle == null) {
			pnTitle = new JPanel();
			pnTitle.add(getLabel());
		}
		return pnTitle;
	}
	private JLabel getLabel() {
		if (label == null) {
			label = new JLabel("");
			label.setIcon(new ImageIcon(Principal.class.getResource("/img/5030934069243.jpg")));
		}
		return label;
	}
	private JPanel getPnOption() {
		if (pnOption == null) {
			pnOption = new JPanel();
			pnOption.setLayout(new GridLayout(0, 3, 0, 0));
			pnOption.add(getBtnNewButton_2());
			pnOption.add(getBtnNewButton_1());
			pnOption.add(getBtnNewButton());
		}
		return pnOption;
	}
	private JButton getBtnNewButton() {
		if (btnNewButton == null) {
			btnNewButton = new JButton("BIG size");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					showSelectPlayers("big");
				}
			});
		}
		return btnNewButton;
	}
	private JButton getBtnNewButton_1() {
		if (btnNewButton_1 == null) {
			btnNewButton_1 = new JButton("MEDIUM size");
			btnNewButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					showSelectPlayers("mid");
				}
			});
		}
		return btnNewButton_1;
	}
	private JButton getBtnNewButton_2() {
		if (btnNewButton_2 == null) {
			btnNewButton_2 = new JButton("SMALL size");
			btnNewButton_2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					showSelectPlayers("small");
				}
			});
		}
		return btnNewButton_2;
	}
	
	protected void showSelectPlayers(String boardSize) 
	{
		SelectPlayers sp = new SelectPlayers(this, boardSize);
		sp.setVisible(true);
	}

	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.add(getButton_1());
		}
		return panel;
	}
	private JButton getButton_1() {
		if (btnNewButton_3 == null) {
			btnNewButton_3 = new JButton("Exit");
			btnNewButton_3.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.exit(0);
				}
			});
		}
		return btnNewButton_3;
	}




	public static Game getGame() {
		return game;
	}
}
