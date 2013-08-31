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

import k.midieditor.util.MicroTracker;
import k.midieditor.util.MicroTracker.MicroListener;

public class MidiFilePlayer {
	public static Sequencer s;
	public static long micro = -1;
	public static Synthesizer synth;
	private static int loops;
	private static long l_start;
	private static long l_end;
	private static MicroListener loopListen = new MicroListener() {

		@Override
		public void onStart() {
			if (loops != 0) {

			}
		}

		@Override
		public void onMicroChange(long new_micro) {
			if (new_micro < l_start) {
				s.setMicrosecondPosition(l_start);
			}
			if (new_micro > l_end) {
				s.setMicrosecondPosition(l_start);
				if (loops > 0) {
					loops--;
				} else if (loops != 0) {
					loops = -1;
				}
			}
		}

		@Override
		public void onEnd() {

		}
	};

	@Deprecated
	public static void openAndPlay(Sequence se) {
		open(se);
		play();
	}

	public static void open(Sequence se) {
		init(true);
		try {
			s.setSequence(se);
		} catch (InvalidMidiDataException e) {
			e.printStackTrace();
		}
	}

	public static void play() {
		if (s == null) {
			init(false);
		}
		if (s.getSequence() == null) {
			return;
		}
		s.setMicrosecondPosition((micro > -1 ? micro : 0));
		s.start();
	}

	public static void stop() {
		if (s == null) {
			init(false);
		}
		micro = -1;
		s.stop();
		s.setMicrosecondPosition(0);
	}

	public static void pause() {
		if (s == null) {
			init(false);
		}
		micro = s.getMicrosecondPosition();
		s.stop();
	}

	public static void loop(long start, long end, int count) {
		if (s != null && s.getSequence() == null) {
			return;
		}
		loopSequenceFix(start, end, count);
	}

	private static void loopSequenceFix(long start, long end, int count) {
		loops = count;
		l_start = start;
		l_end = end;
		MicroTracker.addListener(loopListen);
	}

	public static long microPos() {
		if (s == null) {
			init(false);
		}
		return s.getMicrosecondPosition();
	}

	public static boolean playing() {
		return microPos() > 0;
	}

	private static void init(boolean refresh) {
		if (s != null && !refresh) {
			return;
		}
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
			init(false);
		}
		return s;
	}

}
