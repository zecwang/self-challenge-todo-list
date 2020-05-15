package app.wangzc.challenge.views;

import app.wangzc.challenge.models.AwardModel;

import javax.swing.*;
import java.awt.event.*;

/**
 * The JFrame for adding new awards
 *
 * @author WangZC
 */
public class ExchangeJFrame extends javax.swing.JFrame {

	public static ExchangeJFrame exchangeJFrame = new ExchangeJFrame();

	public static ExchangeJFrame getInstance() {
		return exchangeJFrame;
	}

	/**
	 * Creates new form ExchangeJFrame
	 */
	public ExchangeJFrame() {
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

		setTitle("兑换项目");

		popupMenu = new javax.swing.JPopupMenu();
		JMenuItem dropItem = new JMenuItem("删除");
		dropItem.setFont(new java.awt.Font("华文行楷", 0, 24));
		popupMenu.add(dropItem);

		dropItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AwardModel.deleteItem(objects[list.getSelectedRow()][1].toString());
				refresh();
			}
		});

		label = new javax.swing.JLabel();
		jScrollPane1 = new javax.swing.JScrollPane();
		list = new javax.swing.JTable();
		valueLabel = new javax.swing.JLabel();
		value = new javax.swing.JTextField();
		itemLabel = new javax.swing.JLabel();
		jScrollPane2 = new javax.swing.JScrollPane();
		item = new javax.swing.JTextArea();
		add = new javax.swing.JButton();

		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

		label.setFont(new java.awt.Font("华文行楷", 0, 36)); // NOI18N
		label.setText("兑换项目");

		list.setFont(new java.awt.Font("宋体", 0, 18)); // NOI18N
		refresh();
		list.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mousePressed(java.awt.event.MouseEvent evt) {
				listMousePressed(evt);
			}
		});
		jScrollPane1.setViewportView(list);

		valueLabel.setFont(new java.awt.Font("华文行楷", 0, 24)); // NOI18N
		valueLabel.setText("价 值 ：");

		value.setFont(new java.awt.Font("Consolas", 0, 24)); // NOI18N

		itemLabel.setFont(new java.awt.Font("华文行楷", 0, 24)); // NOI18N
		itemLabel.setText("项目 ↓");

		item.setColumns(20);
		item.setFont(new java.awt.Font("宋体", 0, 18)); // NOI18N
		item.setRows(5);
		jScrollPane2.setViewportView(item);

		add.setFont(new java.awt.Font("华文行楷", 0, 24)); // NOI18N
		add.setText("添加");
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
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addGroup(layout.createSequentialGroup()
												.addGap(73, 73, 73)
												.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(itemLabel)
														.addGroup(layout.createSequentialGroup()
																.addComponent(valueLabel)
																.addGap(49, 49, 49)
																.addComponent(value, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
														.addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 592, javax.swing.GroupLayout.PREFERRED_SIZE)))
										.addGroup(layout.createSequentialGroup()
												.addGap(40, 40, 40)
												.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
														.addComponent(add, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
														.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
																.addComponent(label)
																.addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 665, javax.swing.GroupLayout.PREFERRED_SIZE)))))
								.addContainerGap(45, Short.MAX_VALUE))
		);
		layout.setVerticalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup()
								.addGap(51, 51, 51)
								.addComponent(label)
								.addGap(32, 32, 32)
								.addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(29, 29, 29)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(valueLabel)
										.addComponent(value, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGap(18, 18, 18)
								.addComponent(itemLabel)
								.addGap(18, 18, 18)
								.addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(18, 18, 18)
								.addComponent(add, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addContainerGap(27, Short.MAX_VALUE))
		);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				super.windowActivated(e);
				refresh();
			}
		});

		pack();

		setLocationRelativeTo(null);
		setResizable(false);
	}// </editor-fold>

	private void showPopup(java.awt.event.MouseEvent evt) {
		if (list.getSelectedRow() != -1)
			popupMenu.show(evt.getComponent(), evt.getX(), evt.getY());
	}

	public void refresh() {
		this.setEnabled(false);
		objects = AwardModel.getAward();
		list.setModel(new javax.swing.table.DefaultTableModel(
				objects,
				new String[]{
						"价值", "项目"
				}
		) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});
		list.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setRowHeight(30);
		list.getColumnModel().getColumn(0).setPreferredWidth(80);
		list.getColumnModel().getColumn(1).setPreferredWidth(560);
		this.setEnabled(true);
	}

	private void listMousePressed(java.awt.event.MouseEvent evt) {
		// TODO add your handling code here:
		if ((evt.getModifiers() & InputEvent.BUTTON3_MASK) != 0) {
			showPopup(evt);
		}
	}

	private void addActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
		try {
			if (item.getText().replace(" ", "").equals(""))
				JOptionPane.showMessageDialog(null, " 项目不可为`NULL`", "操作失败", JOptionPane.WARNING_MESSAGE);
			else
				AwardModel.insertItem(Integer.parseInt(value.getText()), item.getText());
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, " 价值不可为`NULL`", "操作失败", JOptionPane.WARNING_MESSAGE);
		} finally {
			refresh();
			value.setText("");
			item.setText("");
		}
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
			java.util.logging.Logger.getLogger(ExchangeJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(ExchangeJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(ExchangeJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(ExchangeJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		//</editor-fold>

        /* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new ExchangeJFrame().setVisible(true);
			}
		});
	}

	// Variables declaration - do not modify
	private javax.swing.JButton add;
	private javax.swing.JTextArea item;
	private javax.swing.JLabel itemLabel;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JScrollPane jScrollPane2;
	private javax.swing.JLabel label;
	private javax.swing.JTable list;
	private javax.swing.JPopupMenu popupMenu;
	private javax.swing.JTextField value;
	private javax.swing.JLabel valueLabel;
	// End of variables declaration
	private Object[][] objects;
}
