package net.hb.day28;

import java.awt.FlowLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

public class Test extends JFrame implements ActionListener, Runnable {

	JTabbedPane jp = new JTabbedPane();
	String[] listData = { "Friend1", "Friend2", "Friend3" };
	TextField tf = new TextField(30);
	JButton btn = new JButton("전송");
	JPanel pan = new JPanel();
	JPanel jpan = new JPanel();
	JTextArea jta = new JTextArea(7, 20);
	Vector<JTextArea> v = new Vector<JTextArea>();
	int count = 1;

	public Test() {

		setLayout(new FlowLayout());
		JList li = new JList(listData);
		add(li);
		v.add(new JTextArea());
		this.jp.addTab("All", jpan.add((JTextArea) v.get(0)));
		add(jp);
		pan.add(tf);
		pan.add(btn);
		add(pan);

		setBounds(200, 200, 500, 500);
		setVisible(true);

		MouseListener mouseListener = new MouseAdapter() {
			public void mouseClicked(MouseEvent ae) {
				String name = li.getSelectedValue().toString();
				if (ae.getClickCount() == 2) {

					v.add(new JTextArea(7, 20));
					jp.addTab(name, new JPanel().add(v.get(count)));
					count++;
					add(jp);
				}
			}
		};
		li.addMouseListener(mouseListener);
		btn.addActionListener(this);
		tf.addActionListener(this);

	}

	public void run() {

	}

	public static void main(String[] args) {
		new Test();
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == tf || e.getSource() == btn) {
			String str = tf.getText();
			int i = jp.getSelectedIndex();
			v.get(i).append(tf.getText());

			tf.setText("");
			tf.requestFocus();
			System.out.println("111");
		}

	}

}
