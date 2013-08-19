package k.midieditor;

import java.awt.Toolkit;
import java.lang.reflect.InvocationTargetException;

import javax.swing.SwingUtilities;

import k.midieditor.gui.MidiEditorMain;
import k.midieditor.util.Helper;
import static k.midieditor.util.Helper.CommandLine.*;

public class MidiEditor {
	public static String VERSION = "1.0 alpha";
	public static MidiEditorMain mainwin;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
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
			VERSION = getProperty("versionOverride", VERSION);
		}
		if (hasKey("version")) {
			System.out.println("MidiEditor v" + VERSION);
			MidiEditorMain.kill();
		}
		if (hasKey("versionAndLaunch")) {
			System.out.println("MidiEditor v" + VERSION);
		}
		if (hasKey("debug")) {
			System.out.println("Toolkit is of type "
					+ Toolkit.getDefaultToolkit().getClass());
		}
		try {
			SwingUtilities.invokeAndWait(new Runnable() {

				@Override
				public void run() {
					mainwin = new MidiEditorMain(VERSION);
				}
			});
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
