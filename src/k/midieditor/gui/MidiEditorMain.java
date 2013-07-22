package k.midieditor.gui;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;

import k.midieditor.util.Helper;

public class MidiEditorMain extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MidiEditorMain(String ver) {
		super("MidiEditor v" + ver);
		Helper.Window.setBackground(new Color(100, 0, 0), this);
		this.setPreferredSize(new Dimension(800, 600));
		this.setSize(getPreferredSize());
		Helper.Window.drop(this);
		this.setVisible(true);
	}
}
