package k.midieditor;

import java.util.Arrays;
import java.util.List;

import javax.sound.midi.MidiDevice.Info;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Receiver;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;

public class MidiFilePlayer {
	private static Sequencer s;
	private static Receiver recv = null;
	static {
		try {
			s = getGervillOrDefault();
			recv = s.getReceiver();
		} catch (MidiUnavailableException e) {
			e.printStackTrace();
		}
	}

	public static void openAndPlay(Sequence s) {

	}

	private static Sequencer getGervillOrDefault()
			throws MidiUnavailableException {
		List<Info> li = Arrays.asList(MidiSystem.getMidiDeviceInfo());
		for (Info i : li) {
			if (i.getName().contains("Gervill")) {
				return (Sequencer) MidiSystem.getMidiDevice(i);
			}
		}
		System.out
				.println("WARNING: Gervill not loaded! Sound quality will drop tremedously.");
		return MidiSystem.getSequencer();
	}

}
