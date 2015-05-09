package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import main.Principal;
import model.Board;
import model.Game;



public class BoardGui extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8575172728564397634L;
	private JPanel contentPane;
	private JPanel pnTitle;
	private JPanel pnScore;
	public static JPanel pnBoard;
	private JLabel lbTitle;
	private JLabel lbScore;
	private JPanel pnLabelScore;
	private JPanel pnPlayers;
	private static JLabel lblScorePlayer_2;
	private JLabel lblPlayer_4;
	private JLabel lblPlayer_2;
	private static JLabel lblScorePlayer_1;
	private JLabel lblPlayer_1;
	private static JLabel lblScorePlayer_4;
	private static JLabel lblScorePlayer_3;
	private JLabel lblPlayer_3;
	private JMenuBar menuBar;
	private JMenu mnNewMenu;
	private JMenu mnNewMenu_1;
	private JMenuItem mntmExit;
	private JMenuItem mntmAboutTrivialPursuit;
	private static JLabel lblAcoreDado;
	private static JButton btnDado;
	
	public Principal vP = null;
	private int numberOfPlayers;
	private static JLabel lblTurnOfPlayer;

	
	/**
	 * Create the frame.
	 * @param i 
	 * @param game 
	 */
	public BoardGui(Principal padre, int i, Game game, int numberOfPlayers) {
		this.vP = padre;
		this.numberOfPlayers = numberOfPlayers;
		setBounds(100, 100, 772, 485);
		setJMenuBar(getMenuBar_1());
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getPnTitle(), BorderLayout.NORTH);
		contentPane.add(getPnScore(), BorderLayout.EAST);
		contentPane.add(getPnBoard(i,game.getBoard()), BorderLayout.CENTER);
	}
	
	public Principal getvP() {
		return vP;
	}

	public void setvP(Principal vP) {
		this.vP = vP;
	}

	private JPanel getPnTitle() {
		if (pnTitle == null) {
			pnTitle = new JPanel();
			pnTitle.add(getLbTitle());
			pnTitle.add(getLblTurnOfPlayer());
		}
		return pnTitle;
	}
	private JPanel getPnScore() {
		if (pnScore == null) {
			pnScore = new JPanel();
			pnScore.setLayout(new BorderLayout(0, 0));
			pnScore.add(getLbScore());
			pnScore.add(getPnLabelScore(), BorderLayout.NORTH);
			pnScore.add(getPnPlayers(), BorderLayout.CENTER);
		}
		return pnScore;
	}
	public JPanel getPnBoard(int number,Board board) {
		if (pnBoard == null) {
			pnBoard = new JPanel();
			pnBoard.setLayout(new GridLayout(number, number, 0, 0));
			for(int i=0; i<board.getBoard().length; i++)
				for(int j=0; j<board.getBoard().length; j++)
					pnBoard.add(board.getBoard()[i][j]);
		}
		return pnBoard;
	}

	private JLabel getLbTitle() {
		if (lbTitle == null) {
			lbTitle = new JLabel("");
			lbTitle.setIcon(new ImageIcon(BoardGui.class.getResource("/img/5030934069243.jpg")));
		}
		return lbTitle;
	}
	private JLabel getLbScore() {
		if (lbScore == null) {
			lbScore = new JLabel("score");
		}
		return lbScore;
	}
	
	private JPanel getPnLabelScore() {
		if (pnLabelScore == null) {
			pnLabelScore = new JPanel();
			pnLabelScore.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			pnLabelScore.add(getBtnDado());
			pnLabelScore.add(getLblScoreDado());
		}
		return pnLabelScore;
	}
	private JPanel getPnPlayers() {
		if (pnPlayers == null) {
			pnPlayers = new JPanel();
			pnPlayers.setBorder(new TitledBorder(null, "Scores", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			pnPlayers.setLayout(new GridLayout(4, 5, 0, 0));
			if(numberOfPlayers >= 2)
			{
				pnPlayers.add(getLblPlayer_1());
				pnPlayers.add(getLblScorePlayer_1());
				pnPlayers.add(getLblPlayer_2());
				pnPlayers.add(getLblScorePlayer_2());
				if(numberOfPlayers >= 3)
				{
					pnPlayers.add(getLblPlayer_3());
					pnPlayers.add(getLblScorePlayer_3());
				}
				if(numberOfPlayers == 4)
				{
					pnPlayers.add(getLblPlayer_4());
					pnPlayers.add(getLblScorePlayer_4());
				}
			}
		}
		return pnPlayers;
	}
	public static JLabel getLblScorePlayer_2() {
		if (lblScorePlayer_2 == null) {
			lblScorePlayer_2 = new JLabel("0");
		}
		return lblScorePlayer_2;
	}
	private JLabel getLblPlayer_4() {
		if (lblPlayer_4 == null) {
			lblPlayer_4 = new JLabel("Player 4:");
		}
		return lblPlayer_4;
	}
	private  JLabel getLblPlayer_2() {
		if (lblPlayer_2 == null) {
			lblPlayer_2 = new JLabel("Player 2:");
		}
		return lblPlayer_2;
	}
	public static JLabel getLblScorePlayer_1() {
		if (lblScorePlayer_1 == null) {
			lblScorePlayer_1 = new JLabel("0");
		}
		return lblScorePlayer_1;
	}
	private JLabel getLblPlayer_1() {
		if (lblPlayer_1 == null) {
			lblPlayer_1 = new JLabel("Player 1:");
		}
		return lblPlayer_1;
	}
	public static JLabel getLblScorePlayer_4() {
		if (lblScorePlayer_4 == null) {
			lblScorePlayer_4 = new JLabel("0");
		}
		return lblScorePlayer_4;
	}
	public static JLabel getLblScorePlayer_3() {
		if (lblScorePlayer_3 == null) {
			lblScorePlayer_3 = new JLabel("0");
		}
		return lblScorePlayer_3;
	}
	private JLabel getLblPlayer_3() {
		if (lblPlayer_3 == null) {
			lblPlayer_3 = new JLabel("Player 3:");
		}
		return lblPlayer_3;
	}
	private JMenuBar getMenuBar_1() {
		if (menuBar == null) {
			menuBar = new JMenuBar();
			menuBar.add(getMnNewMenu());
			menuBar.add(getMnNewMenu_1());
			
		}
		return menuBar;
	}
	
	private JMenu getMnNewMenu() {
		if (mnNewMenu == null) {
			mnNewMenu = new JMenu("Play");
			mnNewMenu.add(getMntmExit());
		}
		return mnNewMenu;
	}
	private JMenu getMnNewMenu_1() {
		if (mnNewMenu_1 == null) {
			mnNewMenu_1 = new JMenu("Help");
			mnNewMenu_1.add(getMntmAboutTrivialPursuit());
		}
		return mnNewMenu_1;
	}
	private JMenuItem getMntmExit() {
		if (mntmExit == null) {
			mntmExit = new JMenuItem("Exit");
			mntmExit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					System.exit(0);
				}
			});
		}
		return mntmExit;
	}
	private JMenuItem getMntmAboutTrivialPursuit() {
		if (mntmAboutTrivialPursuit == null) {
			mntmAboutTrivialPursuit = new JMenuItem("About Trivial pursuit...");
			mntmAboutTrivialPursuit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JOptionPane.showMessageDialog(null, "Arquisoft/Trivial_i2a did this");
				}
			});
		}
		return mntmAboutTrivialPursuit;
	}
	public static JLabel getLblScoreDado() {
		if (lblAcoreDado == null) {
			lblAcoreDado = new JLabel("Press");
		}
		return lblAcoreDado;
	}
	public static JButton getBtnDado() {
		if (btnDado == null) {
			btnDado = new JButton("");
			btnDado.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					getLblScoreDado().setText("1");
				}
			});
			
			btnDado.setBackground(Color.WHITE);
			btnDado.setIcon(new ImageIcon(BoardGui.class.getResource("/img/dado.png")));
			
		}
		return btnDado;
	}
	public static JLabel getLblTurnOfPlayer() {
		if (lblTurnOfPlayer == null) {
			lblTurnOfPlayer = new JLabel("");
			lblTurnOfPlayer.setFont(new Font("Consolas", Font.BOLD, 16));
		}
		return lblTurnOfPlayer;
	}
}
