package app.wangzc.challenge.views;

import app.wangzc.challenge.models.AwardModel;

import javax.swing.*;
import java.awt.event.*;

/**
 * The JFrame for viewing awards and operating for occupied
 *
 * @author WangZC
 */
public class ViewAwardJFrame extends javax.swing.JFrame {

	public static ViewAwardJFrame viewAwardJFrame = new ViewAwardJFrame();

	public static ViewAwardJFrame getInstance() {
		return viewAwardJFrame;
	}

	/**
	 * Creates new form ViewAwardJFrame
	 */
	public ViewAwardJFrame() {
		initComponents();
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		setTitle("查看奖励");

		popupMenu = new javax.swing.JPopupMenu();
		JMenuItem doneItem = new JMenuItem("用完");
		doneItem.setFont(new java.awt.Font("华文行楷", 0, 24));
		popupMenu.add(doneItem);

		doneItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AwardModel.deleteUsing(objectsUsing[usingTable.getSelectedRow()][0].toString());
				refresh();
			}
		});

		label = new javax.swing.JLabel();
		jScrollPane1 = new javax.swing.JScrollPane();
		awardTable = new javax.swing.JTable();
		jScrollPane2 = new javax.swing.JScrollPane();
		usingTable = new javax.swing.JTable();
		usingLabel = new javax.swing.JLabel();
		totalLabel = new javax.swing.JLabel();
		add = new javax.swing.JButton();

		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

		label.setFont(new java.awt.Font("华文行楷", 0, 36)); // NOI18N
		label.setText("拥有奖励");

		awardTable.setFont(new java.awt.Font("宋体", 0, 18)); // NOI18N

		awardTable.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mousePressed(java.awt.event.MouseEvent evt) {
				awardTableMousePressed(evt);
			}
		});
		jScrollPane1.setViewportView(awardTable);

		usingTable.setFont(new java.awt.Font("宋体", 0, 18)); // NOI18N
		refresh();
		usingTable.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mousePressed(java.awt.event.MouseEvent evt) {
				usingTableMousePressed(evt);
			}
		});
		jScrollPane2.setViewportView(usingTable);

		usingLabel.setFont(new java.awt.Font("华文行楷", 0, 36)); // NOI18N
		usingLabel.setText("使用中");

		totalLabel.setFont(new java.awt.Font("华文行楷", 0, 18)); // NOI18N
		totalLabel.setText("库存总量：" + AwardModel.getStorage());

		add.setFont(new java.awt.Font("Consolas", 0, 24)); // NOI18N
		add.setText(">>");
		add.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				addActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup()
								.addGap(43, 43, 43)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
										.addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 680, javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGroup(layout.createSequentialGroup()
												.addComponent(label)
												.addGap(127, 127, 127)
												.addComponent(totalLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(add, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
								.addGap(46, 46, 46)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addComponent(usingLabel)
										.addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 680, javax.swing.GroupLayout.PREFERRED_SIZE))
								.addContainerGap(51, Short.MAX_VALUE))
		);
		layout.setVerticalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup()
								.addGap(51, 51, 51)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(label)
										.addComponent(usingLabel)
										.addComponent(totalLabel)
										.addComponent(add, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGap(34, 34, 34)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 508, Short.MAX_VALUE)
										.addComponent(jScrollPane1))
								.addGap(51, 51, 51))
		);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				super.windowActivated(e);
				refresh();
			}
		});

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				super.windowClosing(e);
				MenuJFrame.getInstance().setVisible(true);
			}
		});

		pack();

		setLocationRelativeTo(null);
		setResizable(false);
	}// </editor-fold>

	private void showPopup(java.awt.event.MouseEvent evt) {
		if (usingTable.getSelectedRow() != -1)
			popupMenu.show(evt.getComponent(), evt.getX(), evt.getY());
	}

	public void refresh() {
		this.setEnabled(false);
		objectsAward = AwardModel.queryHaveAward();
		awardTable.setModel(new javax.swing.table.DefaultTableModel(
				objectsAward,
				new String[]{
						"价值", "项目", "已拥有"
				}
		) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});
		awardTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		awardTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		awardTable.setRowHeight(30);
		awardTable.getColumnModel().getColumn(0).setPreferredWidth(80);
		awardTable.getColumnModel().getColumn(1).setPreferredWidth(528);
		awardTable.getColumnModel().getColumn(2).setPreferredWidth(70);

		objectsUsing = AwardModel.queryUsing();
		usingTable.setModel(new javax.swing.table.DefaultTableModel(
				objectsUsing,
				new String[]{
						"领取时间", "项目"
				}
		) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});
		usingTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		usingTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		usingTable.setRowHeight(30);
		usingTable.getColumnModel().getColumn(0).setPreferredWidth(200);
		usingTable.getColumnModel().getColumn(1).setPreferredWidth(478);

		totalLabel.setText("库存总量：" + AwardModel.getStorage());

		try {
			if (indexAward != -1)
				awardTable.setRowSelectionInterval(indexAward, indexAward);
		} catch (IllegalArgumentException ex) {
			indexAward = -1;
		} finally {
			try {
				if (indexUsing != -1)
					usingTable.setRowSelectionInterval(indexUsing, indexUsing);
			} catch (IllegalArgumentException ex) {
				indexUsing = -1;
			}
		}
		this.setEnabled(true);
	}

	private void awardTableMousePressed(java.awt.event.MouseEvent evt) {
		// TODO add your handling code here:
		indexAward = awardTable.getSelectedRow();
	}

	private void usingTableMousePressed(java.awt.event.MouseEvent evt) {
		// TODO add your handling code here:
		indexUsing = usingTable.getSelectedRow();
		if ((evt.getModifiers() & InputEvent.BUTTON3_MASK) != 0) {
			showPopup(evt);
		}
	}

	private void addActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
		if (awardTable.getSelectedRow() != -1) {
			AwardModel.insertUsing(objectsAward[awardTable.getSelectedRow()][1].toString(), Integer.parseInt(objectsAward[awardTable.getSelectedRow()][2].toString()));
		}
		refresh();
	}

	/**
	 * @param args the command line arguments
	 */
	public static void main(String args[]) {
	    /* Set the Nimbus look and feel */
		//<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
		/* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
		 * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Windows".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(ViewAwardJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(ViewAwardJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(ViewAwardJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(ViewAwardJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		//</editor-fold>

        /* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new ViewAwardJFrame().setVisible(true);
			}
		});
	}

	// Variables declaration - do not modify
	private javax.swing.JButton add;
	private javax.swing.JTable awardTable;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JScrollPane jScrollPane2;
	private javax.swing.JLabel label;
	private javax.swing.JPopupMenu popupMenu;
	private javax.swing.JLabel totalLabel;
	private javax.swing.JLabel usingLabel;
	private javax.swing.JTable usingTable;
	// End of variables declaration
	private Object[][] objectsAward;
	private Object[][] objectsUsing;
	private int indexAward = -1;
	private int indexUsing = -1;
}

