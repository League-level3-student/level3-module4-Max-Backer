package _00_IntroToStacks;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Stack;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class _02_TextUndoRedo implements KeyListener {

	public static void main(String[] args) {
		new _02_TextUndoRedo().createGUI();
	}

	/*
	 * Create a JFrame with a JPanel and a JLabel.
	 * 
	 * Every time a key is pressed, add that character to the JLabel. It should look
	 * like a basic text editor.
	 * 
	 * Make it so that every time the BACKSPACE key is pressed, the last character
	 * is erased from the JLabel.
	 * 
	 * Save that deleted character onto a Stack of Characters.
	 * 
	 * Choose a key to be the Undo key. Make it so that when that key is pressed,
	 * the top Character is popped off the Stack and added back to the JLabel.
	 */
	JFrame frame = new JFrame();
	JPanel panel = new JPanel();
	JLabel label = new JLabel();

	Stack<Character> chars = new Stack<Character>();

	Stack<Character> deleted = new Stack<Character>();

	void createGUI() {
		panel.add(label);
		frame.add(panel);

		frame.addKeyListener(this);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(300, 300);
		frame.setVisible(true);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (chars.size() > 0) {
			if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
				deleted.add(chars.pop());
				label.setText(chars.toString());
				return;
			} else if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
				if (!deleted.isEmpty()) {
					chars.add(deleted.pop());
					label.setText(chars.toString());
					return;
				}

			}
		}

		char c = e.getKeyChar();
		chars.add(c);

		label.setText(chars.toString());
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
