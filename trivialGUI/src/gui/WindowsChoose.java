package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.CardLayout;
import javax.swing.JButton;
import java.awt.GridLayout;
import javax.swing.JLabel;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.BoxLayout;

public class WindowsChoose extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WindowsChoose frame = new WindowsChoose();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public WindowsChoose() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 699, 492);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel pnTitle = new JPanel();
		contentPane.add(pnTitle, BorderLayout.NORTH);
		pnTitle.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lbTrivial = new JLabel("Trivial");
		pnTitle.add(lbTrivial);
		
		JPanel pnBoard = new JPanel();
		contentPane.add(pnBoard, BorderLayout.CENTER);
		pnBoard.setLayout(new GridLayout(9, 9, 0, 0));
		
		JButton button_83 = new JButton("New button");
		pnBoard.add(button_83);
		
		JButton button_82 = new JButton("New button");
		pnBoard.add(button_82);
		
		JButton button_81 = new JButton("New button");
		pnBoard.add(button_81);
		
		JButton button_80 = new JButton("New button");
		pnBoard.add(button_80);
		
		JButton button_79 = new JButton("New button");
		pnBoard.add(button_79);
		
		JButton button_78 = new JButton("New button");
		pnBoard.add(button_78);
		
		JButton button_77 = new JButton("New button");
		pnBoard.add(button_77);
		
		JButton button_76 = new JButton("New button");
		pnBoard.add(button_76);
		
		JButton button_75 = new JButton("New button");
		pnBoard.add(button_75);
		
		JButton button_74 = new JButton("New button");
		pnBoard.add(button_74);
		
		JButton button_73 = new JButton("New button");
		button_73.setVisible(false);
		button_73.setEnabled(false);
		pnBoard.add(button_73);
		
		JButton button_72 = new JButton("New button");
		button_72.setVisible(false);
		button_72.setEnabled(false);
		pnBoard.add(button_72);
		
		JButton button_71 = new JButton("New button");
		button_71.setVisible(false);
		button_71.setEnabled(false);
		pnBoard.add(button_71);
		
		JButton button_70 = new JButton("New button");
		pnBoard.add(button_70);
		
		JButton button_69 = new JButton("New button");
		button_69.setVisible(false);
		button_69.setEnabled(false);
		pnBoard.add(button_69);
		
		JButton button_68 = new JButton("New button");
		button_68.setVisible(false);
		button_68.setEnabled(false);
		pnBoard.add(button_68);
		
		JButton button_67 = new JButton("New button");
		button_67.setVisible(false);
		button_67.setEnabled(false);
		pnBoard.add(button_67);
		
		JButton button_66 = new JButton("New button");
		pnBoard.add(button_66);
		
		JButton button_65 = new JButton("New button");
		pnBoard.add(button_65);
		
		JButton button_64 = new JButton("New button");
		button_64.setVisible(false);
		button_64.setEnabled(false);
		pnBoard.add(button_64);
		
		JButton button_63 = new JButton("New button");
		button_63.setVisible(false);
		button_63.setEnabled(false);
		pnBoard.add(button_63);
		
		JButton button_62 = new JButton("New button");
		button_62.setVisible(false);
		button_62.setEnabled(false);
		pnBoard.add(button_62);
		
		JButton button_61 = new JButton("New button");
		pnBoard.add(button_61);
		
		JButton button_60 = new JButton("New button");
		button_60.setVisible(false);
		button_60.setEnabled(false);
		pnBoard.add(button_60);
		
		JButton button_59 = new JButton("New button");
		button_59.setVisible(false);
		button_59.setEnabled(false);
		pnBoard.add(button_59);
		
		JButton button_58 = new JButton("New button");
		button_58.setVisible(false);
		button_58.setEnabled(false);
		pnBoard.add(button_58);
		
		JButton button_57 = new JButton("New button");
		pnBoard.add(button_57);
		
		JButton button_56 = new JButton("New button");
		pnBoard.add(button_56);
		
		JButton button_55 = new JButton("New button");
		button_55.setVisible(false);
		button_55.setEnabled(false);
		pnBoard.add(button_55);
		
		JButton button_54 = new JButton("New button");
		button_54.setVisible(false);
		button_54.setEnabled(false);
		pnBoard.add(button_54);
		
		JButton button_53 = new JButton("New button");
		button_53.setVisible(false);
		button_53.setEnabled(false);
		pnBoard.add(button_53);
		
		JButton button_52 = new JButton("New button");
		pnBoard.add(button_52);
		
		JButton button_51 = new JButton("New button");
		button_51.setVisible(false);
		button_51.setEnabled(false);
		pnBoard.add(button_51);
		
		JButton button_50 = new JButton("New button");
		button_50.setVisible(false);
		button_50.setEnabled(false);
		pnBoard.add(button_50);
		
		JButton button_49 = new JButton("New button");
		button_49.setVisible(false);
		button_49.setEnabled(false);
		pnBoard.add(button_49);
		
		JButton button_48 = new JButton("New button");
		pnBoard.add(button_48);
		
		JButton button_47 = new JButton("New button");
		pnBoard.add(button_47);
		
		JButton button_46 = new JButton("New button");
		pnBoard.add(button_46);
		
		JButton button_45 = new JButton("New button");
		pnBoard.add(button_45);
		
		JButton button_44 = new JButton("New button");
		pnBoard.add(button_44);
		
		JButton button_43 = new JButton("New button");
		pnBoard.add(button_43);
		
		JButton button_42 = new JButton("New button");
		pnBoard.add(button_42);
		
		JButton button_41 = new JButton("New button");
		pnBoard.add(button_41);
		
		JButton button_40 = new JButton("New button");
		pnBoard.add(button_40);
		
		JButton button_39 = new JButton("New button");
		pnBoard.add(button_39);
		
		JButton button_38 = new JButton("New button");
		pnBoard.add(button_38);
		
		JButton button_37 = new JButton("New button");
		button_37.setVisible(false);
		button_37.setEnabled(false);
		pnBoard.add(button_37);
		
		JButton button_36 = new JButton("New button");
		button_36.setVisible(false);
		button_36.setEnabled(false);
		pnBoard.add(button_36);
		
		JButton button_35 = new JButton("New button");
		button_35.setVisible(false);
		button_35.setEnabled(false);
		pnBoard.add(button_35);
		
		JButton button_34 = new JButton("New button");
		pnBoard.add(button_34);
		
		JButton button_33 = new JButton("New button");
		button_33.setVisible(false);
		button_33.setEnabled(false);
		pnBoard.add(button_33);
		
		JButton button_32 = new JButton("New button");
		button_32.setVisible(false);
		button_32.setEnabled(false);
		pnBoard.add(button_32);
		
		JButton button_31 = new JButton("New button");
		button_31.setVisible(false);
		button_31.setEnabled(false);
		pnBoard.add(button_31);
		
		JButton button_30 = new JButton("New button");
		pnBoard.add(button_30);
		
		JButton button_29 = new JButton("New button");
		pnBoard.add(button_29);
		
		JButton button_28 = new JButton("New button");
		button_28.setVisible(false);
		button_28.setEnabled(false);
		pnBoard.add(button_28);
		
		JButton button_27 = new JButton("New button");
		button_27.setVisible(false);
		button_27.setEnabled(false);
		pnBoard.add(button_27);
		
		JButton button_26 = new JButton("New button");
		button_26.setVisible(false);
		button_26.setEnabled(false);
		pnBoard.add(button_26);
		
		JButton button_25 = new JButton("New button");
		pnBoard.add(button_25);
		
		JButton button_24 = new JButton("New button");
		button_24.setVisible(false);
		button_24.setEnabled(false);
		pnBoard.add(button_24);
		
		JButton button_23 = new JButton("New button");
		button_23.setVisible(false);
		button_23.setEnabled(false);
		pnBoard.add(button_23);
		
		JButton button_22 = new JButton("New button");
		button_22.setVisible(false);
		button_22.setEnabled(false);
		pnBoard.add(button_22);
		
		JButton button_21 = new JButton("New button");
		pnBoard.add(button_21);
		
		JButton button_13 = new JButton("New button");
		pnBoard.add(button_13);
		
		JButton button_12 = new JButton("New button");
		button_12.setVisible(false);
		button_12.setEnabled(false);
		pnBoard.add(button_12);
		
		JButton button = new JButton("New button");
		button.setVisible(false);
		button.setEnabled(false);
		pnBoard.add(button);
		
		JButton button_4 = new JButton("New button");
		button_4.setVisible(false);
		button_4.setEnabled(false);
		pnBoard.add(button_4);
		
		JButton button_6 = new JButton("New button");
		pnBoard.add(button_6);
		
		JButton button_5 = new JButton("New button");
		button_5.setVisible(false);
		button_5.setEnabled(false);
		pnBoard.add(button_5);
		
		JButton button_8 = new JButton("New button");
		button_8.setVisible(false);
		button_8.setEnabled(false);
		pnBoard.add(button_8);
		
		JButton button_7 = new JButton("New button");
		button_7.setVisible(false);
		button_7.setEnabled(false);
		pnBoard.add(button_7);
		
		JButton button_10 = new JButton("New button");
		pnBoard.add(button_10);
		
		JButton button_9 = new JButton("New button");
		pnBoard.add(button_9);
		
		JButton button_15 = new JButton("New button");
		pnBoard.add(button_15);
		
		JButton button_11 = new JButton("New button");
		pnBoard.add(button_11);
		
		JButton button_18 = new JButton("New button");
		pnBoard.add(button_18);
		
		JButton button_14 = new JButton("New button");
		pnBoard.add(button_14);
		
		JButton button_16 = new JButton("New button");
		pnBoard.add(button_16);
		
		JButton button_17 = new JButton("New button");
		pnBoard.add(button_17);
		
		JButton button_19 = new JButton("New button");
		pnBoard.add(button_19);
		
		JButton button_20 = new JButton("New button");
		pnBoard.add(button_20);
		
		JPanel pnQuesitos = new JPanel();
		contentPane.add(pnQuesitos, BorderLayout.EAST);
		
		JLabel lblNewLabel = new JLabel("New label");
		pnQuesitos.add(lblNewLabel);
	}
}
