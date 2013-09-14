package k.midieditor.gui.actions;

import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.JFileChooser;

import k.core.gui.JMIActionListener;
import k.midieditor.MidiEditor;
import k.midieditor.gui.MidiEditorMain;

/* New JMenuItem listener */
public class NJMIActionListener extends JMIActionListener {

	public NJMIActionListener() {
		super("New...", MidiEditorMain.NEW_JMIKEY, MidiEditorMain.FILE_KEY);
	}

	@Override
	public void onAction(ActionEvent e) {
		JFileChooser fd = ((OJMIActionListener) MidiEditorMain.OPEN_LISTENER)
				.getDialog();
		fd.showDialog(null, "Create");
		File out = fd.getSelectedFile();
		if (out != null) {
			MidiEditor.mainwin.setMidiFileReload(out);
		}
	}

}
