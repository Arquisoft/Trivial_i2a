package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
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
import model.Box;
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
	private JLabel lblPlayer;
	private JLabel lblScoreplayer;
	private JLabel lblNewLabel;
	private JLabel lblScorePlayer;
	private JLabel lblPlayer_1;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JMenuBar menuBar;
	private JMenu mnNewMenu;
	private JMenu mnNewMenu_1;
	private JMenuItem mntmNewMenuItem;
	private JMenuItem mntmExit;
	private JMenuItem mntmAboutTrivialPursuit;
	private JLabel lblAcoreDado;
	private JButton btnNewButton_3;
	
	public Principal vP = null;

	

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BoardSmall frame = new BoardSmall(padre);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
*/
	
	
	
	/**
	 * Create the frame.
	 * @param i 
	 * @param game 
	 */
	public BoardGui(Principal padre, int i, Game game) {
		this.vP = padre;
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
			pnLabelScore.add(getBtnNewButton_3());
			pnLabelScore.add(getLblAcoreDado());
		}
		return pnLabelScore;
	}
	private JPanel getPnPlayers() {
		if (pnPlayers == null) {
			pnPlayers = new JPanel();
			pnPlayers.setBorder(new TitledBorder(null, "Scores", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			pnPlayers.setLayout(new GridLayout(4, 5, 0, 0));
			pnPlayers.add(getLblPlayer_1());
			pnPlayers.add(getLblScorePlayer());
			pnPlayers.add(getLblNewLabel());
			//pnPlayers.setBorder(new EmptyBorder(5, 5, 0, 0));
			pnPlayers.add(getLblPlayer());
			pnPlayers.add(getLblNewLabel_3());
			pnPlayers.add(getLblNewLabel_2());
			pnPlayers.add(getLblScoreplayer());
			pnPlayers.add(getLblNewLabel_1());
		}
		return pnPlayers;
	}
	private JLabel getLblPlayer() {
		if (lblPlayer == null) {
			lblPlayer = new JLabel("Score player 2");
		}
		return lblPlayer;
	}
	private JLabel getLblScoreplayer() {
		if (lblScoreplayer == null) {
			lblScoreplayer = new JLabel("Player 4");
		}
		return lblScoreplayer;
	}
	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("Player 2");
		}
		return lblNewLabel;
	}
	private JLabel getLblScorePlayer() {
		if (lblScorePlayer == null) {
			lblScorePlayer = new JLabel("Score Player 1");
		}
		return lblScorePlayer;
	}
	private JLabel getLblPlayer_1() {
		if (lblPlayer_1 == null) {
			lblPlayer_1 = new JLabel("Player 1:");
		}
		return lblPlayer_1;
	}
	private JLabel getLblNewLabel_1() {
		if (lblNewLabel_1 == null) {
			lblNewLabel_1 = new JLabel("Score player 4");
		}
		return lblNewLabel_1;
	}
	private JLabel getLblNewLabel_2() {
		if (lblNewLabel_2 == null) {
			lblNewLabel_2 = new JLabel("Score player 3");
		}
		return lblNewLabel_2;
	}
	private JLabel getLblNewLabel_3() {
		if (lblNewLabel_3 == null) {
			lblNewLabel_3 = new JLabel("Player 3");
		}
		return lblNewLabel_3;
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
			mnNewMenu.add(getMntmNewMenuItem());
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
	private JMenuItem getMntmNewMenuItem() {
		if (mntmNewMenuItem == null) {
			mntmNewMenuItem = new JMenuItem("Initialize");
		}
		return mntmNewMenuItem;
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
					JOptionPane.showMessageDialog(null, "Hello!");
				}
			});
		}
		return mntmAboutTrivialPursuit;
	}
	private JLabel getLblAcoreDado() {
		if (lblAcoreDado == null) {
			lblAcoreDado = new JLabel("Score dado");
			
		}
		return lblAcoreDado;
	}
	private JButton getBtnNewButton_3() {
		if (btnNewButton_3 == null) {
			btnNewButton_3 = new JButton("");
			
			btnNewButton_3.setBackground(Color.WHITE);
			btnNewButton_3.setIcon(new ImageIcon(BoardGui.class.getResource("/img/dado.png")));
			
		}
		return btnNewButton_3;
	}
}
