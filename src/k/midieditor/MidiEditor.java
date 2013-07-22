package k.midieditor;

import k.midieditor.gui.MidiEditorMain;
import k.midieditor.util.Helper;
import static k.midieditor.util.Helper.CommandLine.*;

public class MidiEditor {
	private static String VERSION = "1.0 alpha";

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MidiEditorMain mainwin = new MidiEditorMain(VERSION);
		args = normalizeCommandArgs(args);
		Helper.Array.print(args);
		for (int i = 0; i < args.length; i += 2) {
			int i2 = i + 1;
			String key = args[i];
			String val = args[i2];
			acceptPair(key, val);
		}
		if (hasKey("file")) {
			String val = getProperty("file");
			System.out.println(val);
		}
		if (hasKey("versionOverride")) {
			Helper.Window.kill(mainwin);
			VERSION = getProperty("versionOverride", VERSION);
			mainwin = new MidiEditorMain(VERSION);
		}
		if (hasKey("version")) {
			System.out.println("MidiEditor v" + VERSION);
			Helper.Window.kill(mainwin);
		}
	}
}
