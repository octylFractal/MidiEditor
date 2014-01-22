package k.midieditor.gui.actions;

import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import k.core.util.gui.JMIActionListener;
import k.midieditor.gui.MidiEditorMain;

public class SAJMIActionListener extends JMIActionListener {
	public SAJMIActionListener() {
		super("Save as...", MidiEditorMain.SAVEA_JMIKEY,
				MidiEditorMain.FILE_KEY);
	}

	@Override
	public void onAction(ActionEvent e) {
		JFileChooser fd = ((OJMIActionListener) MidiEditorMain.OPEN_LISTENER)
				.getDialog();
		fd.showSaveDialog(null);
		File out = fd.getSelectedFile();
		if (out != null) {
			if (MidiEditorMain.working != null) {
				MidiEditorMain.working.save(out);
			} else if (MidiEditorMain.working == null) {
				JOptionPane.showMessageDialog(null,
						"There is not a file currently open!", "Cannot save",
						JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}

}
