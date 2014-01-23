package k.midieditor.gui.actions;

import java.awt.event.ActionEvent;

import k.core.util.gui.JMIActionListener;
import k.midieditor.MidiFilePlayer;
import k.midieditor.gui.MidiEditorMain;

public class STJMIActionListener extends JMIActionListener {

    public STJMIActionListener() {
        super("Stop", MidiEditorMain.STOP_JMIKEY, MidiEditorMain.PLAYBACK_KEY);
    }

    @Override
    public void onAction(ActionEvent e) {
        if (MidiFilePlayer.playing()) {
            MidiFilePlayer.stop();
        }
    }

}
