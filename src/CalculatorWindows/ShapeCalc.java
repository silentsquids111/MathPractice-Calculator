package CalculatorWindows;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import BreezySwing.GBPanel;

public abstract class ShapeCalc extends CalculatorWindow {
	private static final long serialVersionUID = -3897111547231323877L;

	private GBPanel p1 = addPanel(1, 1, 3, 1), p2 = addPanel(2, 2, 1, 1), p3 = addPanel(3, 1, 3, 1),
			p4 = addPanel(2, 1, 1, 1), p5 = addPanel(2, 3, 1, 1);
	private JButton exit = p3.addButton("Exit", GridBagConstraints.CENTER, 1, 1, 1, 1);

	private JTextField perAnswer = p2.addTextField("", GridBagConstraints.CENTER, 1, 1, 1, 1),
			areaAnswer = p2.addTextField("", GridBagConstraints.CENTER, 2, 1, 1, 1);
	private JTextField[] answers = { perAnswer, areaAnswer };
	{
		for (JTextField answer : answers) {
			answer.setEditable(false);
			answer.setHorizontalAlignment(SwingConstants.CENTER);
			answer.setFocusable(false);
			answer.setBackground(Color.GRAY);
			answer.setForeground(Color.white);
		}
	}

	private JTextField[] labs;
	private Timer t1 = new Timer(500, new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				perAnswer.setText(getPerAnswer());
				areaAnswer.setText(getAreaAnswer());
			} catch (ArithmeticException ae) {
				if (ae.getMessage().equals("Not a triangle")) {
					perAnswer.setText(ae.getMessage());
					areaAnswer.setText(ae.getMessage());
				}
			}

		}
	});
	{
		t1.setInitialDelay(0);
	}
	private KeyListener key = new KeyAdapter() {
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
				dispose();
		}
	};

	public void buttonClicked(JButton buttonObj) {
		dispose();
	}

	abstract String getPerAnswer();

	abstract String getAreaAnswer();

	public JTextField[] getLabs() {
		return labs;
	}

	public void setLabs(JTextField[] labs) {
		this.labs = labs;
		for (JTextField j : labs) {
			j.setHorizontalAlignment(JLabel.RIGHT);
			j.setEditable(false);
			j.setFocusable(false);
			j.setBackground(getContentPane().getBackground());
			j.setForeground(Color.white);
		}
	}

	public ShapeCalc(JFrame arg0, String title) {
		super(arg0, title);
		p1.setOpaque(false);
		p2.setOpaque(false);
		p3.setOpaque(false);
		p4.setOpaque(false);
		p5.setOpaque(false);

		exit.addKeyListener(key);

		t1.start();
	}

	public GBPanel getP1() {
		return p1;
	}

	public GBPanel getP4() {
		return p4;
	}

	public KeyListener getKey() {
		return key;
	}
}