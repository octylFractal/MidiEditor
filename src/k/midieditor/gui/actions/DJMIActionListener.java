package k.midieditor.gui.actions;

import java.awt.event.ActionEvent;

import javax.swing.JCheckBoxMenuItem;

import k.midieditor.gui.MidiEditorMain;
import k.midieditor.gui.SideConsole;

public class DJMIActionListener extends JMIActionListener {

	public DJMIActionListener() {
		super("Debug?", SideConsole.DEBUG_JMIKEY, SideConsole.OPTION_MENU);
	}

	@Override
	public void onAction(ActionEvent e) {
		MidiEditorMain.console.error(((JCheckBoxMenuItem) e.getSource())
				.isSelected());
	}

}
