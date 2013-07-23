package k.midieditor.gui.actions;

import java.awt.event.ActionEvent;

import k.midieditor.gui.MidiEditorMain;

/* New JMenuItem listener */
public class NJMIActionListener extends JMIActionListener {

	public NJMIActionListener() {
		super("New...", MidiEditorMain.NEW_JMIKEY, MidiEditorMain.FILE_KEY);
	}

	@Override
	public void onAction(ActionEvent e) {
	}

}
