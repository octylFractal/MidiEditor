package k.midieditor.gui.actions;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import k.midieditor.gui.MidiEditorMain;

public class PLJMIActionListener extends JMIActionListener {

	public PLJMIActionListener() {
		super("Play", MidiEditorMain.PLAY_JMIKEY, MidiEditorMain.PLAYBACK_KEY);
	}

	@Override
	public void onAction(ActionEvent e) {
		if (MidiEditorMain.working != null) {
			MidiEditorMain.working.play();
		} else {
			JOptionPane.showMessageDialog(null, "No file open!", "Cannot play",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}

}
