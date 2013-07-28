package k.midieditor.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.io.File;

import javax.swing.JFrame;

import k.midieditor.file.MidiFile;
import k.midieditor.gui.actions.JMIActionListener;
import k.midieditor.util.Helper;

public class MidiEditorMain extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/* Menu creation constants */
	public static final String FILE_KEY = "f", EDIT_KEY = "e",
			NEW_JMIKEY = "new", OPEN_JMIKEY = "open", SAVE_JMIKEY = "save",
			SAVEA_JMIKEY = "savea", UNDO_JNIKEY = "undo",
			UNDOR_JNIKEY = "undor", REDO_JNIKEY = "redo",
			REDOR_JNIKEY = "redor";

	private static JFrame inst = null;

	public static MidiFile working = null;

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
		refresh();
	}

	public void addAllComponents() {
		addMenu();
	}

	private void addMenu() {
		// Create menu creator //
		Menu m = Menu.create("midieditormain");

		// Menu items visible on bar //
		m.addMenuByName(FILE_KEY, "File");
		m.addMenuByName(EDIT_KEY, "Edit");

		// Menu Items inside bar-visible ones //
		// File menu (new, open, save, save as)
		m.addMenuItemToMenuByName(FILE_KEY, NEW_JMIKEY, "New...");
		m.addMenuItemToMenuByName(FILE_KEY, OPEN_JMIKEY, "Open");
		m.addMenuItemToMenuByName(FILE_KEY, SAVE_JMIKEY, "Save");
		m.addMenuItemToMenuByName(FILE_KEY, SAVEA_JMIKEY, "Save as...");
		// Edit menu (undo, repeat undo, redo, repeat redo)
		m.addMenuItemToMenuByName(EDIT_KEY, UNDO_JNIKEY, "Undo");
		m.addMenuItemToMenuByName(EDIT_KEY, UNDOR_JNIKEY, "Undo...");
		m.addMenuItemToMenuByName(EDIT_KEY, REDO_JNIKEY, "Redo");
		m.addMenuItemToMenuByName(EDIT_KEY, REDOR_JNIKEY, "Redo...");
		// Add actions //
		m.setActionListenerAll(JMIActionListener.inst);

		// Show the menu //
		m.display(this);
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
		return working.getFile();
	}

	public void setMidiFile(File midi) {
		working.setFileNoReload(midi);
		refresh();
	}

	public void setMidiFileReload(File midi) {
		working.setFile(midi, true);
		refresh();
	}
}
