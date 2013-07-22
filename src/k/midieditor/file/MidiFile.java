package k.midieditor.file;

import java.io.File;
import java.io.IOException;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.Sequence;
import javax.sound.midi.Track;

public class MidiFile {
	private boolean invalid = false;
	private File midi = null;
	private Sequence internal = null;

	public MidiFile(File path) {
		if (path.isDirectory()
				|| (!path.getAbsolutePath().endsWith(".mid") && !path
						.getAbsolutePath().endsWith(".midi"))) {
			System.err.println("File '" + path.getAbsolutePath()
					+ "' is not a " + (path.isDirectory() ? "file!" : "midi!"));
			invalid = true;
			return;
		}
		if (!path.exists()) {
			try {
				path.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("WARNING: MidiFile was unable to load '"
						+ path.getAbsolutePath() + "'!");
				return;
			}
		}
		midi = path.getAbsoluteFile();
		try {
			internal = MidiSystem.getSequence(midi);
		} catch (InvalidMidiDataException e) {
			e.printStackTrace();
			System.err.println("Bad MIDI data!");
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("Couldn't read data? Access might be denied!");
		}
	}

	public Sequence getSequence() {
		return internal;
	}

	public void setSequnce(Sequence s) {
		internal = s;
	}

	public Track getTrackAt(int index) {
		return getSequence().getTracks()[index];
	}

	public void setTrackAt(int index, Track t) {
		getSequence().getTracks()[index] = t;
	}
}
