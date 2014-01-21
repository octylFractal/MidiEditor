package k.midieditor.gui.actions;

import java.awt.event.ActionEvent;

import k.core.util.gui.JMIActionListener;
import k.midieditor.gui.MidiEditorMain;

public class UJMIActionListener extends JMIActionListener {

	public UJMIActionListener(String command, String jmi_ref_name) {
		super(command, jmi_ref_name, MidiEditorMain.UNDO_KEY);
	}

	@Override
	public void onAction(ActionEvent e) {

	}

}
