package k.midieditor.gui.actions;

import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.io.File;

import k.midieditor.MidiEditor;
import k.midieditor.gui.MidiEditorMain;

/* New JMenuItem listener */
public class NJMIActionListener extends JMIActionListener {

	public NJMIActionListener() {
		super("New...", MidiEditorMain.NEW_JMIKEY, MidiEditorMain.FILE_KEY);
	}

	@Override
	public void onAction(ActionEvent e) {
		FileDialog fd = ((OJMIActionListener) JMIActionListener.OPEN_LISTENER)
				.getDialog();
		fd.setVisible(true);
		String out = fd.getFile();
		if (out != null && out.endsWith(".mid")) {
			MidiEditor.mainwin.setMidiFileReload(new File(out));
		}
	}

}
