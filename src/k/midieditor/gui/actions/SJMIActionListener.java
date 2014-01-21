package k.midieditor.gui.actions;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import k.core.util.gui.JMIActionListener;
import k.midieditor.gui.MidiEditorMain;

public class SJMIActionListener extends JMIActionListener {

	public SJMIActionListener() {
		super("Save", MidiEditorMain.SAVE_JMIKEY, MidiEditorMain.FILE_KEY);
	}

	@Override
	public void onAction(ActionEvent e) {
		if (MidiEditorMain.working != null) {
			MidiEditorMain.working.save();
		} else if (MidiEditorMain.working == null) {
			JOptionPane.showMessageDialog(null,
					"There is not a file currently open!", "Cannot save",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}
}
