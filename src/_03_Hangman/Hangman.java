package _03_Hangman;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Stack;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Hangman implements KeyListener {
	public static void main(String[] args) {
		Hangman hangman = new Hangman();
		hangman.createGUI();
	}

	JFrame frame = new JFrame();
	JPanel panel = new JPanel();
	JLabel label = new JLabel();

	String randWord;
	int lettersPresent;
	int lives;
	StringBuilder builder = new StringBuilder();

	int numOfWords = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter the number of words to guess (1-100)"));
	int count = numOfWords;

	public void createGUI() {
		panel.add(label);
		frame.add(panel);
		frame.addKeyListener(this);

		frame.setSize(400, 100);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		nextWord();
	}

	public void nextWord() {
		if (numOfWords >= 1 && numOfWords <= 100) {
			count--;
			randWord = Utilities.readRandomLineFromFile("dictionary.txt");
			lettersPresent = 0;
			lives = 10;
			builder.setLength(0);

			for (int i = 0; i < randWord.length(); i++) {
				builder.append('_');
			}
			label.setText(builder.toString() + " Lives: " + lives);

			frame.setVisible(true);
		} else {
			JOptionPane.showMessageDialog(null, "Your number was not between 1-100 D:");
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		String letter = "" + e.getKeyChar();
		for (int i = 0; i < randWord.length(); i++) {
			if ((randWord.charAt(i) + "").equals(letter)) {
				builder.replace(i, i + 1, letter);
				lettersPresent++;
			}
		}
		if (lettersPresent == 0) {
			lives--;
		} else {
			lettersPresent = 0;
		}
		
		label.setText(builder.toString() + " Lives: " + lives);

		int remaining = 0;
		for (int i = 0; i < builder.length(); i++) {
			if (builder.charAt(i) == '_') {
				remaining++;
			}
		}

		if (remaining == 0) {
			if (count == 0) {
				JOptionPane.showMessageDialog(null, "-the end-");
				return;
			} else {
				JOptionPane.showMessageDialog(null, "gj brudda");
				nextWord();
			}
		}

		if (lives == 0) {
			if (count == 0) {
				JOptionPane.showMessageDialog(null, "Correct word was: " + randWord);
				JOptionPane.showMessageDialog(null, "adios amigo");
				return;
			} else {
				JOptionPane.showMessageDialog(null, "u lose )^:  Correct word was: " + randWord);
				nextWord();
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}
}
