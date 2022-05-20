package CalculatorWindows;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import BreezySwing.GBPanel;
import Utils.StringUtils;

public class PercentCalc extends CalculatorWindow {
	private static final long serialVersionUID = -6948220304648988531L;

	private GBPanel p1 = addPanel(1, 1, 3, 1), p2 = addPanel(2, 2, 1, 1), p3 = addPanel(3, 1, 3, 1),
			p4 = addPanel(2, 1, 1, 1), p5 = addPanel(2, 3, 1, 1);

	private JButton exit = p3.addButton("Exit", 1, 1, 1, 1);

	private JTextField answer = p2.addTextField("", GridBagConstraints.CENTER, 1, 1, 1, 1),
			num = p1.addTextField("1", GridBagConstraints.CENTER, 1, 2, 1, 1),
			percent = p1.addTextField("1", GridBagConstraints.CENTER, 2, 2, 1, 1),
			numLab = p1.addTextField("Number:", GridBagConstraints.CENTER, 1, 1, 1, 1),
			percentLab = p1.addTextField("Percent:", GridBagConstraints.CENTER, 2, 1, 1, 1),
			spacer = p1.addTextField("%", GridBagConstraints.CENTER, 2, 3, 1, 1);
	private JTextField[] labs = { numLab, percentLab, spacer };
	{
		for (JTextField j : labs) {
			j.setEditable(false);
			j.setHorizontalAlignment(SwingConstants.RIGHT);
			j.setFocusable(false);
			j.setBackground(getContentPane().getBackground());
			j.setForeground(Color.white);
		}
		spacer.setHorizontalAlignment(JLabel.LEFT);
	}
	Timer t1 = new Timer(500, new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			answer.setText(getAnswer());
		}
	});

	public void buttonClicked(JButton buttonObj) {
		dispose();
	}

	private String getAnswer() {
		try {
			int num = Integer.parseInt(this.num.getText());
			int percent = Integer.parseInt(this.percent.getText());
			if (percent <= 0)
				throw new NumberFormatException("No negative percent");
			double answer = num * percent * 0.01;

			DecimalFormat dec = new DecimalFormat("#,##0.###");
			return dec.format(answer);
		} catch (NumberFormatException nfe) {
			if (nfe.getMessage().equals("No negative percent"))
				return StringUtils.capitalize(nfe.getMessage());
			return "Invalid Inputs";
		}
	}

	public PercentCalc(JFrame arg0) {
		super(arg0, "Percent Claculator");

		p1.setOpaque(false);
		p2.setOpaque(false);
		p3.setOpaque(false);
		p4.setOpaque(false);
		p5.setOpaque(false);

		KeyListener key = new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					buttonClicked(exit);
				}
			}
		};

		num.addKeyListener(key);
		percent.addKeyListener(key);
		exit.addKeyListener(key);

		answer.setEditable(false);
		answer.setHorizontalAlignment(SwingConstants.CENTER);
		answer.setFocusable(false);
		answer.setBackground(Color.GRAY);
		answer.setForeground(Color.white);

		num.setHorizontalAlignment(SwingConstants.CENTER);
		percent.setHorizontalAlignment(SwingConstants.CENTER);

		t1.setInitialDelay(0);
		t1.start();
	}
}
