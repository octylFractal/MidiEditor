package k.midieditor.gui.actions;

import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.io.File;

import k.midieditor.gui.MidiEditorMain;

public class SAJMIActionListener extends JMIActionListener {
	public SAJMIActionListener() {
		super("Save as...", MidiEditorMain.SAVEA_JMIKEY,
				MidiEditorMain.FILE_KEY);
	}

	@Override
	public void onAction(ActionEvent e) {
		FileDialog fd = ((OJMIActionListener) JMIActionListener.OPEN_LISTENER)
				.getDialog();
		fd.setVisible(true);
		String out = fd.getFile();
		if (out != null && out.endsWith(".mid")) {
			MidiEditorMain.working.save(new File(out));
		}
	}

}
