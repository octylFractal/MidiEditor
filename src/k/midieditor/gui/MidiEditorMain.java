package k.midieditor.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
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
			UNDO_KEY = "undo_menu", REDO_KEY = "redo_menu", NEW_JMIKEY = "new",
			OPEN_JMIKEY = "open", SAVE_JMIKEY = "save", SAVEA_JMIKEY = "savea",
			UNDO_JMIKEY = "undo", UNDOR_JMIKEY = "undor", REDO_JMIKEY = "redo",
			REDOR_JMIKEY = "redor";

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
		// Edit menu (undo menu, redo menu) //
		// Undo menu (undo, repeat undo) //
		m.addMenuToMenuByName(EDIT_KEY, UNDO_KEY, "Undo");
		m.addMenuItemToMenuByName(UNDO_KEY, UNDO_JMIKEY, "Undo");
		m.addMenuItemToMenuByName(UNDO_KEY, UNDOR_JMIKEY, "Undo...");
		// Redo menu (redo, repeat redo) //
		m.addMenuToMenuByName(EDIT_KEY, REDO_KEY, "Redo");
		m.addMenuItemToMenuByName(REDO_KEY, REDO_JMIKEY, "Redo");
		m.addMenuItemToMenuByName(REDO_KEY, REDOR_JMIKEY, "Redo...");
		// Add actions //
		m.setActionListenerAll(JMIActionListener.inst);

		// Show the menu //
		m.display(this);
	}

	public void refresh() {
		this.update(getGraphics());
	}

	public void update(Graphics g) {
		paint(g);
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
		if (working == null) {
			working = new MidiFile(null);
		}
		return working.getFile();
	}

	public void setMidiFile(File midi) {
		if (working == null) {
			working = new MidiFile(midi);
		}
		working.setFileNoReload(midi);
		refresh();
	}

	public void setMidiFileReload(File midi) {
		if (working == null) {
			working = new MidiFile(midi);
		}
		working.setFile(midi, true);
		refresh();
	}
}
