package k.midieditor.gui.actions;

import java.awt.event.ActionEvent;

import javax.sound.midi.Sequencer;
import javax.swing.JOptionPane;

import k.core.gui.JMIActionListener;
import k.midieditor.MidiFilePlayer;
import k.midieditor.gui.MidiEditorMain;

public class LJIMIActionListener extends JMIActionListener {

	public LJIMIActionListener() {
		super("Loop...", MidiEditorMain.LOOP_JMIKEY,
				MidiEditorMain.PLAYBACK_KEY);
	}

	@Override
	public void onAction(ActionEvent e) {
		if (MidiFilePlayer.getSeq().getSequence() == null) {
			JOptionPane.showMessageDialog(null, "There is no file open",
					"Cannot add a loop", JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		String strt = JOptionPane.showInputDialog("Loop start point?", "0");
		String end = JOptionPane
				.showInputDialog("Loop end point?",
						microToUserText(MidiFilePlayer.getSeq()
								.getMicrosecondLength()));
		String times = JOptionPane
				.showInputDialog(
						"Loop count (put 'infinite' for infinite looping)?",
						"infinite");
		int start = 0;
		long endl = -1;
		int loops = Sequencer.LOOP_CONTINUOUSLY;
		try {
			start = Integer.parseInt(strt);
			endl = Integer.parseInt(end);
			loops = times.equalsIgnoreCase("infinite") ? Sequencer.LOOP_CONTINUOUSLY
					: Integer.parseInt(times);
		} catch (Exception e1) {
		}
		MidiFilePlayer.loop(start, endl, loops);
	}

	private String microToUserText(float microsecondLength) {
		float millis = (float) Math.floor(microsecondLength / 1000f);
		float sec = (float) Math.floor(millis / 1000f);
		millis -= sec * 1000;
		float min = (float) Math.floor(sec / 60f);
		sec -= min * 60;
		return userFriendlyMin(min) + ":" + userFriendlySec(sec) + "."
				+ userFriendlyMillis(millis);
	}

	private String userFriendlyMillis(float millis) {
		String s = String.valueOf((int) millis);
		while (s.length() < 3) {
			s = "0" + s;
		}
		return s;
	}

	private String userFriendlySec(float sec) {
		String s = String.valueOf((int) sec);
		while (s.length() < 2) {
			s = "0" + s;
		}
		return s;
	}

	private String userFriendlyMin(float min) {
		String s = String.valueOf((int) min);
		while (s.length() < 2) {
			s = "0" + s;
		}
		return s;
	}

}
