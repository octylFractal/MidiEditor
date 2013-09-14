package k.midieditor.gui.actions;

import java.awt.event.ActionEvent;

import k.core.gui.JMIActionListener;
import k.midieditor.MidiFilePlayer;
import k.midieditor.gui.MidiEditorMain;

public class PAJMIActionListener extends JMIActionListener {

	public PAJMIActionListener() {
		super("Pause", MidiEditorMain.PAUSE_JMIKEY, MidiEditorMain.PLAYBACK_KEY);
	}

	@Override
	public void onAction(ActionEvent e) {
		if (MidiFilePlayer.playing()) {
			MidiFilePlayer.pause();
		}
	}

}
