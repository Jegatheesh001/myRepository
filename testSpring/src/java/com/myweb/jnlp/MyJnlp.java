package com.myweb.jnlp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;


public class MyJnlp {

	private static String uniqueId;

	public static void loadWindow() {
		JFrame f = new JFrame("Reader");
		final JTextField tf = new JTextField();
		tf.setBounds(50, 50, 150, 20);
		JButton b = new JButton("PUSH DATA");
		b.setBounds(70, 80, 110, 20);
		JLabel status = new JLabel(uniqueId);
		status.setBounds(50, 100, 500, 50);
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				status.setText(tf.getText());
				System.out.println(tf.getText());
				triggerSocket(tf.getText());
			}
		});
		f.add(tf);
		f.add(b);
		f.add(status);
		f.setSize(400, 400);
		f.setLayout(null);
		f.setVisible(true);
	}

	private static void triggerSocket(String text) {
		SocketConnect client = new SocketConnect();
		client.sendMessage(text);
	}

	public static void main(String[] args) {
		for (String arg : args)
			System.out.println(arg);
		uniqueId = System.getProperty("username");
		System.out.println(uniqueId);
		loadWindow();
	}

}
