package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;



public class BoardSmall extends JFrame {

	private JPanel contentPane;
	private JPanel pnTitle;
	private JPanel pnScore;
	private JPanel pnBoard;
	private JLabel lbTitle;
	private JLabel lbScore;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;
	private JButton button;
	private JButton btnQuesito_2;
	private JButton button_2;
	private JButton button_3;
	private JButton button_4;
	private JButton btnTiraDeNuevo_1;
	private JButton button_6;
	private JButton button_7;
	private JButton button_8;
	private JButton button_9;
	private JButton button_10;
	private JButton button_11;
	private JButton button_12;
	private JButton button_13;
	private JButton button_14;
	private JButton button_15;
	private JButton button_16;
	private JButton button_17;
	private JButton button_18;
	private JButton button_19;
	private JButton button_20;
	private JButton button_21;
	private JButton button_22;
	private JButton button_23;
	private JButton button_24;
	private JButton button_25;
	private JButton button_26;
	private JButton button_27;
	private JButton button_28;
	private JButton button_29;
	private JButton button_30;
	private JButton button_31;
	private JButton button_32;
	private JButton btnQuesito_3;
	private JButton button_34;
	private JButton button_35;
	private JButton button_36;
	private JButton btnMultiQ;
	private JButton button_38;
	private JButton button_39;
	private JButton btnQuesito_1;
	private JButton button_41;
	private JButton button_42;
	private JButton button_43;
	private JButton button_44;
	private JButton button_45;
	private JButton button_46;
	private JButton button_47;
	private JButton button_48;
	private JButton button_49;
	private JButton button_50;
	private JButton button_51;
	private JButton button_52;
	private JButton button_53;
	private JButton button_54;
	private JButton button_55;
	private JButton button_56;
	private JButton button_57;
	private JButton button_58;
	private JButton button_59;
	private JButton button_60;
	private JButton button_61;
	private JButton button_62;
	private JButton button_63;
	private JButton button_64;
	private JButton button_65;
	private JButton button_66;
	private JButton button_67;
	private JButton btnQuesito;
	private JButton button_69;
	private JButton button_70;
	private JButton button_71;
	private JButton btnTiraDeNuevo_2;
	private JButton button_73;
	private JButton button_74;
	private JButton button_75;
	private JButton button_76;
	private JButton btnTiraDeNuevo;
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
	private JMenuBar menuBar_1;
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
	 */
	public BoardSmall(Principal padre) {
		this.vP = padre;
		setBounds(100, 100, 772, 485);
		setJMenuBar(getMenuBar_1());
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getPnTitle(), BorderLayout.NORTH);
		contentPane.add(getPnScore(), BorderLayout.EAST);
		contentPane.add(getPnBoard(), BorderLayout.CENTER);
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
	private JPanel getPnBoard() {
		if (pnBoard == null) {
			pnBoard = new JPanel();
			pnBoard.setLayout(new GridLayout(9, 9, 0, 0));
			pnBoard.add(getBtnTiraDeNuevo());
			pnBoard.add(getButton_76());
			pnBoard.add(getButton_75());
			pnBoard.add(getButton_74());
			pnBoard.add(getBtnQuesito());
			pnBoard.add(getButton_69());
			pnBoard.add(getButton_70());
			pnBoard.add(getButton_71());
			pnBoard.add(getBtnTiraDeNuevo_2());
			pnBoard.add(getButton_73());
			pnBoard.add(getButton_62());
			pnBoard.add(getButton_63());
			pnBoard.add(getButton_64());
			pnBoard.add(getButton_65());
			pnBoard.add(getButton_66());
			pnBoard.add(getButton_67());
			pnBoard.add(getButton_56());
			pnBoard.add(getButton_57());
			pnBoard.add(getButton_58());
			pnBoard.add(getButton_59());
			pnBoard.add(getButton_60());
			pnBoard.add(getButton_61());
			pnBoard.add(getButton_50());
			pnBoard.add(getButton_51());
			pnBoard.add(getButton_52());
			pnBoard.add(getButton_53());
			pnBoard.add(getButton_54());
			pnBoard.add(getButton_55());
			pnBoard.add(getButton_44());
			pnBoard.add(getButton_45());
			pnBoard.add(getButton_46());
			pnBoard.add(getButton_47());
			pnBoard.add(getButton_48());
			pnBoard.add(getButton_49());
			pnBoard.add(getButton_38());
			pnBoard.add(getButton_39());
			pnBoard.add(getBtnQuesito_1());
			pnBoard.add(getButton_41());
			pnBoard.add(getButton_42());
			pnBoard.add(getButton_43());
			pnBoard.add(getBtnMultiQ());
			pnBoard.add(getButton_36());
			pnBoard.add(getButton_35());
			pnBoard.add(getButton_34());
			pnBoard.add(getBtnQuesito_3());
			pnBoard.add(getButton_32());
			pnBoard.add(getButton_31());
			pnBoard.add(getButton_30());
			pnBoard.add(getButton_29());
			pnBoard.add(getButton_28());
			pnBoard.add(getButton_27());
			pnBoard.add(getButton_26());
			pnBoard.add(getButton_25());
			pnBoard.add(getButton_24());
			pnBoard.add(getButton_23());
			pnBoard.add(getButton_22());
			pnBoard.add(getButton_21());
			pnBoard.add(getButton_20());
			pnBoard.add(getButton_19());
			pnBoard.add(getButton_18());
			pnBoard.add(getButton_17());
			pnBoard.add(getButton_16());
			pnBoard.add(getButton_15());
			pnBoard.add(getButton_14());
			pnBoard.add(getButton_13());
			pnBoard.add(getButton_12());
			pnBoard.add(getButton_11());
			pnBoard.add(getButton_10());
			pnBoard.add(getButton_8());
			pnBoard.add(getButton_9());
			pnBoard.add(getButton_7());
			pnBoard.add(getButton_6());
			pnBoard.add(getBtnTiraDeNuevo_1());
			pnBoard.add(getButton_4());
			pnBoard.add(getButton_3());
			pnBoard.add(getButton_2());
			pnBoard.add(getBtnQuesito_2());
			pnBoard.add(getButton());
			pnBoard.add(getBtnNewButton_2());
			pnBoard.add(getBtnNewButton_1());
			pnBoard.add(getBtnNewButton());
		}
		return pnBoard;
	}
	private JLabel getLbTitle() {
		if (lbTitle == null) {
			lbTitle = new JLabel("");
			lbTitle.setIcon(new ImageIcon(BoardSmall.class.getResource("/img/5030934069243.jpg")));
		}
		return lbTitle;
	}
	private JLabel getLbScore() {
		if (lbScore == null) {
			lbScore = new JLabel("score");
		}
		return lbScore;
	}
	private JButton getBtnNewButton() {
		if (btnNewButton == null) {
			btnNewButton = new JButton("Tira de nuevo");
			btnNewButton.setBackground(Color.WHITE);
		}
		return btnNewButton;
	}
	private JButton getBtnNewButton_1() {
		if (btnNewButton_1 == null) {
			btnNewButton_1 = new JButton("");
			btnNewButton_1.setBackground(Color.YELLOW);
		}
		return btnNewButton_1;
	}
	private JButton getBtnNewButton_2() {
		if (btnNewButton_2 == null) {
			btnNewButton_2 = new JButton("");
			btnNewButton_2.setBackground(Color.PINK);
		}
		return btnNewButton_2;
	}
	private JButton getButton() {
		if (button == null) {
			button = new JButton("");
			button.setForeground(Color.RED);
			button.setBackground(Color.RED);
		}
		return button;
	}
	private JButton getBtnQuesito_2() {
		if (btnQuesito_2 == null) {
			btnQuesito_2 = new JButton("Quesito");
			btnQuesito_2.setBackground(Color.WHITE);
		}
		return btnQuesito_2;
	}
	private JButton getButton_2() {
		if (button_2 == null) {
			button_2 = new JButton("");
			button_2.setBackground(Color.YELLOW);
		}
		return button_2;
	}
	private JButton getButton_3() {
		if (button_3 == null) {
			button_3 = new JButton("");
			button_3.setBackground(Color.PINK);
		}
		return button_3;
	}
	private JButton getButton_4() {
		if (button_4 == null) {
			button_4 = new JButton("");
			button_4.setBackground(Color.BLUE);
		}
		return button_4;
	}
	private JButton getBtnTiraDeNuevo_1() {
		if (btnTiraDeNuevo_1 == null) {
			btnTiraDeNuevo_1 = new JButton("Tira de nuevo");
			btnTiraDeNuevo_1.setBackground(Color.WHITE);
		}
		return btnTiraDeNuevo_1;
	}
	private JButton getButton_6() {
		if (button_6 == null) {
			button_6 = new JButton("");
			button_6.setBackground(Color.BLUE);
		}
		return button_6;
	}
	private JButton getButton_7() {
		if (button_7 == null) {
			button_7 = new JButton("");
			button_7.setVisible(false);
			button_7.setEnabled(false);
		}
		return button_7;
	}
	private JButton getButton_8() {
		if (button_8 == null) {
			button_8 = new JButton("");
			button_8.setVisible(false);
			button_8.setEnabled(false);
		}
		return button_8;
	}
	private JButton getButton_9() {
		if (button_9 == null) {
			button_9 = new JButton("");
			button_9.setVisible(false);
			button_9.setEnabled(false);
		}
		return button_9;
	}
	private JButton getButton_10() {
		if (button_10 == null) {
			button_10 = new JButton("");
			button_10.setBackground(Color.YELLOW);
		}
		return button_10;
	}
	private JButton getButton_11() {
		if (button_11 == null) {
			button_11 = new JButton("");
			button_11.setVisible(false);
			button_11.setEnabled(false);
		}
		return button_11;
	}
	private JButton getButton_12() {
		if (button_12 == null) {
			button_12 = new JButton("");
			button_12.setVisible(false);
			button_12.setEnabled(false);
		}
		return button_12;
	}
	private JButton getButton_13() {
		if (button_13 == null) {
			button_13 = new JButton("");
			button_13.setVisible(false);
			button_13.setEnabled(false);
		}
		return button_13;
	}
	private JButton getButton_14() {
		if (button_14 == null) {
			button_14 = new JButton("");
			button_14.setForeground(Color.RED);
			button_14.setBackground(Color.RED);
		}
		return button_14;
	}
	private JButton getButton_15() {
		if (button_15 == null) {
			button_15 = new JButton("");
			button_15.setForeground(Color.RED);
			button_15.setBackground(Color.RED);
		}
		return button_15;
	}
	private JButton getButton_16() {
		if (button_16 == null) {
			button_16 = new JButton("");
			button_16.setVisible(false);
			button_16.setEnabled(false);
		}
		return button_16;
	}
	private JButton getButton_17() {
		if (button_17 == null) {
			button_17 = new JButton("");
			button_17.setVisible(false);
			button_17.setEnabled(false);
		}
		return button_17;
	}
	private JButton getButton_18() {
		if (button_18 == null) {
			button_18 = new JButton("");
			button_18.setVisible(false);
			button_18.setEnabled(false);
		}
		return button_18;
	}
	private JButton getButton_19() {
		if (button_19 == null) {
			button_19 = new JButton("");
			button_19.setBackground(Color.PINK);
		}
		return button_19;
	}
	private JButton getButton_20() {
		if (button_20 == null) {
			button_20 = new JButton("");
			button_20.setVisible(false);
			button_20.setEnabled(false);
		}
		return button_20;
	}
	private JButton getButton_21() {
		if (button_21 == null) {
			button_21 = new JButton("");
			button_21.setVisible(false);
			button_21.setEnabled(false);
		}
		return button_21;
	}
	private JButton getButton_22() {
		if (button_22 == null) {
			button_22 = new JButton("");
			button_22.setVisible(false);
			button_22.setEnabled(false);
		}
		return button_22;
	}
	private JButton getButton_23() {
		if (button_23 == null) {
			button_23 = new JButton("");
			button_23.setBackground(Color.BLUE);
		}
		return button_23;
	}
	private JButton getButton_24() {
		if (button_24 == null) {
			button_24 = new JButton("");
			button_24.setBackground(Color.PINK);
		}
		return button_24;
	}
	private JButton getButton_25() {
		if (button_25 == null) {
			button_25 = new JButton("");
			button_25.setVisible(false);
			button_25.setEnabled(false);
		}
		return button_25;
	}
	private JButton getButton_26() {
		if (button_26 == null) {
			button_26 = new JButton("");
			button_26.setVisible(false);
			button_26.setEnabled(false);
		}
		return button_26;
	}
	private JButton getButton_27() {
		if (button_27 == null) {
			button_27 = new JButton("");
			button_27.setVisible(false);
			button_27.setEnabled(false);
		}
		return button_27;
	}
	private JButton getButton_28() {
		if (button_28 == null) {
			button_28 = new JButton("");
			button_28.setBackground(Color.BLUE);
		}
		return button_28;
	}
	private JButton getButton_29() {
		if (button_29 == null) {
			button_29 = new JButton("");
			button_29.setVisible(false);
			button_29.setEnabled(false);
		}
		return button_29;
	}
	private JButton getButton_30() {
		if (button_30 == null) {
			button_30 = new JButton("");
			button_30.setVisible(false);
			button_30.setEnabled(false);
		}
		return button_30;
	}
	private JButton getButton_31() {
		if (button_31 == null) {
			button_31 = new JButton("");
			button_31.setVisible(false);
			button_31.setEnabled(false);
		}
		return button_31;
	}
	private JButton getButton_32() {
		if (button_32 == null) {
			button_32 = new JButton("");
			button_32.setBackground(Color.YELLOW);
		}
		return button_32;
	}
	private JButton getBtnQuesito_3() {
		if (btnQuesito_3 == null) {
			btnQuesito_3 = new JButton("Quesito");
			btnQuesito_3.setBackground(Color.WHITE);
		}
		return btnQuesito_3;
	}
	private JButton getButton_34() {
		if (button_34 == null) {
			button_34 = new JButton("");
			button_34.setBackground(Color.BLUE);
		}
		return button_34;
	}
	private JButton getButton_35() {
		if (button_35 == null) {
			button_35 = new JButton("");
			button_35.setBackground(Color.RED);
		}
		return button_35;
	}
	private JButton getButton_36() {
		if (button_36 == null) {
			button_36 = new JButton("");
			button_36.setBackground(Color.YELLOW);
		}
		return button_36;
	}
	private JButton getBtnMultiQ() {
		if (btnMultiQ == null) {
			btnMultiQ = new JButton("multi Q");
		}
		return btnMultiQ;
	}
	private JButton getButton_38() {
		if (button_38 == null) {
			button_38 = new JButton("");
			button_38.setVisible(false);
			button_38.setEnabled(false);
		}
		return button_38;
	}
	private JButton getButton_39() {
		if (button_39 == null) {
			button_39 = new JButton("");
			button_39.setBackground(Color.YELLOW);
		}
		return button_39;
	}
	private JButton getBtnQuesito_1() {
		if (btnQuesito_1 == null) {
			btnQuesito_1 = new JButton("Quesito");
			btnQuesito_1.setBackground(Color.WHITE);
		}
		return btnQuesito_1;
	}
	private JButton getButton_41() {
		if (button_41 == null) {
			button_41 = new JButton("");
			button_41.setBackground(Color.RED);
		}
		return button_41;
	}
	private JButton getButton_42() {
		if (button_42 == null) {
			button_42 = new JButton("");
			button_42.setBackground(Color.BLUE);
		}
		return button_42;
	}
	private JButton getButton_43() {
		if (button_43 == null) {
			button_43 = new JButton("");
			button_43.setBackground(Color.PINK);
		}
		return button_43;
	}
	private JButton getButton_44() {
		if (button_44 == null) {
			button_44 = new JButton("");
			button_44.setVisible(false);
			button_44.setEnabled(false);
		}
		return button_44;
	}
	private JButton getButton_45() {
		if (button_45 == null) {
			button_45 = new JButton("");
			button_45.setVisible(false);
			button_45.setEnabled(false);
		}
		return button_45;
	}
	private JButton getButton_46() {
		if (button_46 == null) {
			button_46 = new JButton("");
			button_46.setVisible(false);
			button_46.setEnabled(false);
		}
		return button_46;
	}
	private JButton getButton_47() {
		if (button_47 == null) {
			button_47 = new JButton("");
			button_47.setBackground(Color.RED);
		}
		return button_47;
	}
	private JButton getButton_48() {
		if (button_48 == null) {
			button_48 = new JButton("");
			button_48.setVisible(false);
			button_48.setEnabled(false);
		}
		return button_48;
	}
	private JButton getButton_49() {
		if (button_49 == null) {
			button_49 = new JButton("");
			button_49.setVisible(false);
			button_49.setEnabled(false);
		}
		return button_49;
	}
	private JButton getButton_50() {
		if (button_50 == null) {
			button_50 = new JButton("");
			button_50.setBackground(Color.YELLOW);
		}
		return button_50;
	}
	private JButton getButton_51() {
		if (button_51 == null) {
			button_51 = new JButton("");
			button_51.setVisible(false);
			button_51.setEnabled(false);
		}
		return button_51;
	}
	private JButton getButton_52() {
		if (button_52 == null) {
			button_52 = new JButton("");
			button_52.setVisible(false);
			button_52.setEnabled(false);
		}
		return button_52;
	}
	private JButton getButton_53() {
		if (button_53 == null) {
			button_53 = new JButton("");
			button_53.setVisible(false);
			button_53.setEnabled(false);
		}
		return button_53;
	}
	private JButton getButton_54() {
		if (button_54 == null) {
			button_54 = new JButton("");
			button_54.setBackground(Color.BLUE);
		}
		return button_54;
	}
	private JButton getButton_55() {
		if (button_55 == null) {
			button_55 = new JButton("");
			button_55.setBackground(Color.PINK);
		}
		return button_55;
	}
	private JButton getButton_56() {
		if (button_56 == null) {
			button_56 = new JButton("");
			button_56.setVisible(false);
			button_56.setEnabled(false);
		}
		return button_56;
	}
	private JButton getButton_57() {
		if (button_57 == null) {
			button_57 = new JButton("");
			button_57.setForeground(Color.RED);
			button_57.setBackground(Color.RED);
		}
		return button_57;
	}
	private JButton getButton_58() {
		if (button_58 == null) {
			button_58 = new JButton("");
			button_58.setForeground(Color.RED);
			button_58.setBackground(Color.RED);
		}
		return button_58;
	}
	private JButton getButton_59() {
		if (button_59 == null) {
			button_59 = new JButton("");
			button_59.setVisible(false);
			button_59.setEnabled(false);
		}
		return button_59;
	}
	private JButton getButton_60() {
		if (button_60 == null) {
			button_60 = new JButton("");
			button_60.setVisible(false);
			button_60.setEnabled(false);
		}
		return button_60;
	}
	private JButton getButton_61() {
		if (button_61 == null) {
			button_61 = new JButton("");
			button_61.setVisible(false);
			button_61.setEnabled(false);
		}
		return button_61;
	}
	private JButton getButton_62() {
		if (button_62 == null) {
			button_62 = new JButton("");
			button_62.setVisible(false);
			button_62.setEnabled(false);
		}
		return button_62;
	}
	private JButton getButton_63() {
		if (button_63 == null) {
			button_63 = new JButton("");
			button_63.setVisible(false);
			button_63.setEnabled(false);
		}
		return button_63;
	}
	private JButton getButton_64() {
		if (button_64 == null) {
			button_64 = new JButton("");
			button_64.setVisible(false);
			button_64.setEnabled(false);
		}
		return button_64;
	}
	private JButton getButton_65() {
		if (button_65 == null) {
			button_65 = new JButton("");
			button_65.setBackground(Color.PINK);
		}
		return button_65;
	}
	private JButton getButton_66() {
		if (button_66 == null) {
			button_66 = new JButton("");
			button_66.setVisible(false);
			button_66.setEnabled(false);
		}
		return button_66;
	}
	private JButton getButton_67() {
		if (button_67 == null) {
			button_67 = new JButton("");
			button_67.setVisible(false);
			button_67.setEnabled(false);
		}
		return button_67;
	}
	private JButton getBtnQuesito() {
		if (btnQuesito == null) {
			btnQuesito = new JButton("Quesito");
			btnQuesito.setForeground(Color.RED);
			btnQuesito.setBackground(Color.WHITE);
		}
		return btnQuesito;
	}
	private JButton getButton_69() {
		if (button_69 == null) {
			button_69 = new JButton("");
			button_69.setBackground(Color.BLUE);
		}
		return button_69;
	}
	private JButton getButton_70() {
		if (button_70 == null) {
			button_70 = new JButton("");
			button_70.setBackground(Color.YELLOW);
		}
		return button_70;
	}
	private JButton getButton_71() {
		if (button_71 == null) {
			button_71 = new JButton("");
			button_71.setBackground(Color.PINK);
		}
		return button_71;
	}
	private JButton getBtnTiraDeNuevo_2() {
		if (btnTiraDeNuevo_2 == null) {
			btnTiraDeNuevo_2 = new JButton("Tira de nuevo");
			btnTiraDeNuevo_2.setBackground(Color.WHITE);
		}
		return btnTiraDeNuevo_2;
	}
	private JButton getButton_73() {
		if (button_73 == null) {
			button_73 = new JButton("");
			button_73.setBackground(Color.BLUE);
		}
		return button_73;
	}
	private JButton getButton_74() {
		if (button_74 == null) {
			button_74 = new JButton("");
			button_74.setBackground(Color.RED);
		}
		return button_74;
	}
	private JButton getButton_75() {
		if (button_75 == null) {
			button_75 = new JButton("");
			button_75.setBackground(Color.PINK);
		}
		return button_75;
	}
	private JButton getButton_76() {
		if (button_76 == null) {
			button_76 = new JButton("");
			button_76.setBackground(Color.YELLOW);
		}
		return button_76;
	}
	private JButton getBtnTiraDeNuevo() {
		if (btnTiraDeNuevo == null) {
			btnTiraDeNuevo = new JButton("Tira de nuevo");
			btnTiraDeNuevo.setBackground(Color.WHITE);
		}
		return btnTiraDeNuevo;
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
			btnNewButton_3.setIcon(new ImageIcon(BoardSmall.class.getResource("/img/dado.png")));
			
		}
		return btnNewButton_3;
	}
}
