package k.midieditor.gui.actions;

import java.awt.event.ActionEvent;

import k.midieditor.gui.MidiEditorMain;

public class SAJMIActionListener extends JMIActionListener {
	public SAJMIActionListener() {
		super("Save as...", MidiEditorMain.SAVEA_JMIKEY,
				MidiEditorMain.FILE_KEY);
	}

	@Override
	public void onAction(ActionEvent e) {

	}

}
