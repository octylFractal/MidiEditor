package k.midieditor.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.io.File;

import javax.swing.JFrame;

import k.midieditor.util.Helper;

public class MidiEditorMain extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static JFrame inst = null;

	public File working = null;

	public MidiEditorMain(String ver) {
		super("MidiEditor v" + ver);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		Helper.Window.setBackground(new Color(100, 0, 0), this);
		this.setPreferredSize(new Dimension(800, 600));
		this.setSize(getPreferredSize());
		Helper.Window.drop(this);
		addAllComponents();
		if (inst != null) {
			Helper.Window.kill(this);
			return;
		}
		this.setVisible(true);
		inst = this;
	}

	public void addAllComponents() {

	}

	public void refresh() {
		this.update(getGraphics());
	}

	public static MidiEditorMain reboot(String version) {
		Helper.Window.kill(inst);
		inst = null;
		return new MidiEditorMain(version);
	}

	public static void kill() {
		Helper.Window.kill(inst);
		inst = null;
	}

	public File getMidiFile() {
		return working;
	}

	public void setMidiFile(File midi) {
		working = midi;
	}
}
