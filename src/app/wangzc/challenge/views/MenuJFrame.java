package app.wangzc.challenge.views;

import app.wangzc.challenge.controllers.conf.Constant;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * 菜单界面
 *
 * @author WangZC
 */
public class MenuJFrame extends javax.swing.JFrame {

	public static MenuJFrame menuJFrame = new MenuJFrame();

	public static MenuJFrame getInstance() {
		return menuJFrame;
	}

	/**
	 * Creates new form MenuJFrame
	 */
	public MenuJFrame() {
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

		setTitle("菜单页");

		usualLabel = new javax.swing.JLabel();
		jSeparator1 = new javax.swing.JSeparator();
		bt01 = new javax.swing.JButton();
		bt02 = new javax.swing.JButton();
		bt03 = new javax.swing.JButton();
		bt04 = new javax.swing.JButton();
		manageLabel = new javax.swing.JLabel();
		jSeparator2 = new javax.swing.JSeparator();
		bt11 = new javax.swing.JButton();
		bt12 = new javax.swing.JButton();
		bt13 = new javax.swing.JButton();
		bt14 = new javax.swing.JButton();
		functionLabel = new javax.swing.JLabel();
		jSeparator3 = new javax.swing.JSeparator();
		bt21 = new javax.swing.JButton();
		bt22 = new javax.swing.JButton();
		bt23 = new javax.swing.JButton();
		bt24 = new javax.swing.JButton();
		othersLabel = new javax.swing.JLabel();
		jSeparator4 = new javax.swing.JSeparator();
		bt31 = new javax.swing.JButton();
		bt32 = new javax.swing.JButton();
		bt33 = new javax.swing.JButton();
		bt34 = new javax.swing.JButton();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		usualLabel.setFont(new java.awt.Font("华文行楷", 0, 36)); // NOI18N
		usualLabel.setText("常用");

		bt01.setFont(new java.awt.Font("华文行楷", 2, 18)); // NOI18N
		bt01.setText("查看任务");
		bt01.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				bt01ActionPerformed(evt);
			}
		});

		bt02.setFont(new java.awt.Font("华文行楷", 2, 18)); // NOI18N
		bt02.setText("查看奖励");
		bt02.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				bt02ActionPerformed(evt);
			}
		});

		bt03.setFont(new java.awt.Font("华文行楷", 2, 18)); // NOI18N
		bt03.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				bt03ActionPerformed(evt);
			}
		});

		bt04.setFont(new java.awt.Font("华文行楷", 2, 18)); // NOI18N
		bt04.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				bt04ActionPerformed(evt);
			}
		});

		manageLabel.setFont(new java.awt.Font("华文行楷", 0, 36)); // NOI18N
		manageLabel.setText("管理");

		bt11.setFont(new java.awt.Font("华文行楷", 2, 18)); // NOI18N
		bt11.setText("管理日常");
		bt11.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				bt11ActionPerformed(evt);
			}
		});

		bt12.setFont(new java.awt.Font("华文行楷", 2, 18)); // NOI18N
		bt12.setText("兑换项目");
		bt12.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				bt12ActionPerformed(evt);
			}
		});

		bt13.setFont(new java.awt.Font("华文行楷", 2, 18)); // NOI18N
		bt13.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				bt13ActionPerformed(evt);
			}
		});

		bt14.setFont(new java.awt.Font("华文行楷", 2, 18)); // NOI18N
		bt14.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				bt14ActionPerformed(evt);
			}
		});

		functionLabel.setFont(new java.awt.Font("华文行楷", 0, 36)); // NOI18N
		functionLabel.setText("功能");

		bt21.setFont(new java.awt.Font("华文行楷", 2, 18)); // NOI18N
		bt21.setText("积分兑换");
		bt21.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				bt21ActionPerformed(evt);
			}
		});

		bt22.setFont(new java.awt.Font("华文行楷", 2, 18)); // NOI18N
		bt22.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				bt22ActionPerformed(evt);
			}
		});

		bt23.setFont(new java.awt.Font("华文行楷", 2, 18)); // NOI18N
		bt23.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				bt23ActionPerformed(evt);
			}
		});

		bt24.setFont(new java.awt.Font("华文行楷", 2, 18)); // NOI18N
		bt24.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				bt24ActionPerformed(evt);
			}
		});

		othersLabel.setFont(new java.awt.Font("华文行楷", 0, 36)); // NOI18N
		othersLabel.setText("其他");

		bt31.setFont(new java.awt.Font("华文行楷", 2, 18)); // NOI18N
		bt31.setText("关于作者");
		bt31.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				bt31ActionPerformed(evt);
			}
		});

		bt32.setFont(new java.awt.Font("华文行楷", 2, 18)); // NOI18N
		bt32.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				bt32ActionPerformed(evt);
			}
		});

		bt33.setFont(new java.awt.Font("华文行楷", 2, 18)); // NOI18N
		bt33.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				bt33ActionPerformed(evt);
			}
		});

		bt34.setFont(new java.awt.Font("华文行楷", 2, 18)); // NOI18N
		bt34.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				bt34ActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup()
								.addGap(31, 31, 31)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addComponent(othersLabel)
										.addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 624, javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGroup(layout.createSequentialGroup()
												.addComponent(bt31, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
												.addGap(18, 18, 18)
												.addComponent(bt32, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
												.addGap(18, 18, 18)
												.addComponent(bt33, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
												.addGap(18, 18, 18)
												.addComponent(bt34, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
										.addComponent(functionLabel)
										.addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 624, javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGroup(layout.createSequentialGroup()
												.addComponent(bt21, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
												.addGap(18, 18, 18)
												.addComponent(bt22, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
												.addGap(18, 18, 18)
												.addComponent(bt23, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
												.addGap(18, 18, 18)
												.addComponent(bt24, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
										.addComponent(manageLabel)
										.addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 624, javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGroup(layout.createSequentialGroup()
												.addComponent(bt11, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
												.addGap(18, 18, 18)
												.addComponent(bt12, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
												.addGap(18, 18, 18)
												.addComponent(bt13, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
												.addGap(18, 18, 18)
												.addComponent(bt14, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
										.addComponent(usualLabel)
										.addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 624, javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGroup(layout.createSequentialGroup()
												.addComponent(bt01, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
												.addGap(18, 18, 18)
												.addComponent(bt02, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
												.addGap(18, 18, 18)
												.addComponent(bt03, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
												.addGap(18, 18, 18)
												.addComponent(bt04, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)))
								.addContainerGap(45, Short.MAX_VALUE))
		);
		layout.setVerticalGroup(
				layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup()
								.addGap(64, 64, 64)
								.addComponent(usualLabel)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(bt01, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(bt02, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(bt03, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(bt04, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGap(18, 18, 18)
								.addComponent(manageLabel)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(bt11, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(bt12, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(bt13, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(bt14, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGap(18, 18, 18)
								.addComponent(functionLabel)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(bt21, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(bt22, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(bt23, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(bt24, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGap(18, 18, 18)
								.addComponent(othersLabel)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(bt31, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(bt32, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(bt33, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(bt34, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
								.addContainerGap(90, Short.MAX_VALUE))
		);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowIconified(WindowEvent e) {
				super.windowIconified(e);
				dispose();
			}
		});

		pack();

		setLocationRelativeTo(null);
		setResizable(false);
	}// </editor-fold>

	private void bt01ActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
		SelfChallengeJFrame.getInstance().setVisible(true);
		dispose();
	}

	private void bt02ActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
		ViewAwardJFrame.getInstance().setVisible(true);
		dispose();
	}

	private void bt03ActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
	}

	private void bt04ActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
	}

	private void bt11ActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:]
		ManageDailyJFrame.getInstance().setVisible(true);
	}

	private void bt12ActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
		ExchangeJFrame.getInstance().setVisible(true);
	}

	private void bt13ActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
	}

	private void bt14ActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
	}

	private void bt21ActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
		ItemJFrame.getInstance().setVisible(true);
	}

	private void bt22ActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
	}

	private void bt23ActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
	}

	private void bt24ActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
	}

	private void bt31ActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
		JOptionPane.showMessageDialog(null, new JLabel(Constant.AboutAuthor), "关于作者", JOptionPane.INFORMATION_MESSAGE);
	}

	private void bt32ActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
	}

	private void bt33ActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
	}

	private void bt34ActionPerformed(java.awt.event.ActionEvent evt) {
		// TODO add your handling code here:
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
			java.util.logging.Logger.getLogger(MenuJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(MenuJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(MenuJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(MenuJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		//</editor-fold>

        /* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new MenuJFrame().setVisible(true);
			}
		});
	}

	// Variables declaration - do not modify
	private javax.swing.JButton bt01;
	private javax.swing.JButton bt02;
	private javax.swing.JButton bt03;
	private javax.swing.JButton bt04;
	private javax.swing.JButton bt11;
	private javax.swing.JButton bt12;
	private javax.swing.JButton bt13;
	private javax.swing.JButton bt14;
	private javax.swing.JButton bt21;
	private javax.swing.JButton bt22;
	private javax.swing.JButton bt23;
	private javax.swing.JButton bt24;
	private javax.swing.JButton bt31;
	private javax.swing.JButton bt32;
	private javax.swing.JButton bt33;
	private javax.swing.JButton bt34;
	private javax.swing.JLabel functionLabel;
	private javax.swing.JSeparator jSeparator1;
	private javax.swing.JSeparator jSeparator2;
	private javax.swing.JSeparator jSeparator3;
	private javax.swing.JSeparator jSeparator4;
	private javax.swing.JLabel manageLabel;
	private javax.swing.JLabel othersLabel;
	private javax.swing.JLabel usualLabel;
	// End of variables declaration
}
