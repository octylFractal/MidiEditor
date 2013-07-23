package k.midieditor.gui.actions;

import java.awt.event.ActionEvent;

import k.midieditor.gui.MidiEditorMain;

public class OJMIActionListener extends JMIActionListener {

	public OJMIActionListener() {
		super("Open", MidiEditorMain.OPEN_JMIKEY, MidiEditorMain.FILE_KEY);
	}

	@Override
	public void onAction(ActionEvent e) {
	}

}
