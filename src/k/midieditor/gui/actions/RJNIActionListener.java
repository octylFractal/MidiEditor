package k.midieditor.gui.actions;

import java.awt.event.ActionEvent;

import k.midieditor.gui.MidiEditorMain;

public class RJNIActionListener extends JMIActionListener {

	public RJNIActionListener(String command, String jmi_ref_name) {
		super(command, jmi_ref_name, MidiEditorMain.EDIT_KEY);
	}

	@Override
	public void onAction(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}
