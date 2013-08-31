package k.midieditor;

import java.util.Arrays;
import java.util.List;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiDevice.Info;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Receiver;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.Synthesizer;

public class MidiFilePlayer {
	public static Sequencer s;
	public static long micro = -1;
	public static Synthesizer synth;

	public static void openAndPlay(Sequence se) {
		try {
			init();
			s.setSequence(se);
			play();
		} catch (InvalidMidiDataException e) {
			e.printStackTrace();
		}
	}

	public static void play() {
		if (s == null) {
			init();
		}
		if (s.getSequence() == null) {
			return;
		}
		s.setMicrosecondPosition((micro > -1 ? micro : 0));
		s.start();
	}

	public static void stop() {
		if (s == null) {
			init();
		}
		micro = -1;
		s.stop();
		s.setMicrosecondPosition(0);
	}

	public static void pause() {
		if (s == null) {
			init();
		}
		micro = s.getMicrosecondPosition();
		s.stop();
	}

	public static void loop(long start, long end, int count) {
		if (s == null) {
			init();
		}
		s.setLoopCount(count);
		s.setLoopStartPoint(start);
		s.setLoopEndPoint(end);
	}

	public static long microPos() {
		if (s == null) {
			init();
		}
		return s.getMicrosecondPosition();
	}

	public static boolean playing() {
		return microPos() > -1;
	}

	private static void init() {
		try {
			if (micro > -1 || (s != null) && playing()) {
				stop();
			}
			synth = getGervillOrDefault();
			s = MidiSystem.getSequencer(false);
			System.err.println(s.getClass());
			Receiver recv = synth.getReceiver();
			synth.open();
			s.getTransmitter().setReceiver(recv);
			s.open();
			micro = -1;
		} catch (MidiUnavailableException e) {
			e.printStackTrace();
		}
	}

	private static Synthesizer getGervillOrDefault()
			throws MidiUnavailableException {
		List<Info> li = Arrays.asList(MidiSystem.getMidiDeviceInfo());
		for (Info i : li) {
			System.err.println("Testing device with info " + i.getName() + " v"
					+ i.getVersion() + " by " + i.getVendor());
			if (i.getName().contains("Gervill")) {
				return (Synthesizer) MidiSystem.getMidiDevice(i);
			}
		}
		System.out
				.println("WARNING: Gervill not loaded! Sound quality will drop tremedously.");
		return MidiSystem.getSynthesizer();
	}

	public static Sequencer getSeq() {
		if (s == null) {
			init();
		}
		return s;
	}

}
