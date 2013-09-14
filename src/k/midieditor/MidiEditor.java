package k.midieditor;

import static k.midieditor.util.Helper.CommandLine.acceptPair;
import static k.midieditor.util.Helper.CommandLine.getProperty;
import static k.midieditor.util.Helper.CommandLine.hasKey;
import static k.midieditor.util.Helper.CommandLine.normalizeCommandArgs;

import java.awt.Toolkit;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;

import javax.swing.SwingUtilities;

import k.core.gui.SideConsole;
import k.midieditor.gui.MidiEditorMain;
import k.midieditor.util.Helper;

public class MidiEditor {
	public static String VERSION = "1.3 alpha";
	public static MidiEditorMain mainwin;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PrintStream temp = System.out, out = SideConsole.earlyChainO(temp);
		System.setOut(out);
		PrintStream err = System.err, temp_ = System.err;
		Helper.Array.print(args);
		args = normalizeCommandArgs(args);
		for (String s : args) {
			if (s.equalsIgnoreCase("-debug")) {
				err = SideConsole.earlyChainE(temp_);
				System.setErr(err);
				break;
			}
		}
		Helper.Array.print(args);
		for (int i = 0; i < args.length; i += 2) {
			int i2 = i + 1;
			String key = args[i];
			String val = args[i2];
			acceptPair(key, val);
		}
		if (hasKey("debug")) {
			System.out.println("Toolkit is of type "
					+ Toolkit.getDefaultToolkit().getClass());
		}
		if (hasKey("file")) {
			String val = getProperty("file");
			System.out.println(val);
		}
		if (hasKey("versionOverride")) {
			VERSION = getProperty("versionOverride", VERSION);
		}
		if (hasKey("version")) {
			temp.println("MidiEditor v" + VERSION);
			try {
				Thread.sleep(10);
				return;
			} catch (InterruptedException ie) {
			}

		}
		if (hasKey("versionAndLaunch")) {
			System.out.println("MidiEditor v" + VERSION);
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
