package k.midieditor.gui.actions;

import java.awt.event.ActionEvent;

import k.midieditor.gui.MidiEditorMain;

public class SJMIActionListener extends JMIActionListener {

	public SJMIActionListener() {
		super("Save", MidiEditorMain.SAVE_JMIKEY, MidiEditorMain.FILE_KEY);
	}

	@Override
	public void onAction(ActionEvent e) {
		MidiEditorMain.working.save();
	}
}
