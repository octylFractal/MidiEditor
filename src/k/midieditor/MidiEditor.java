package k.midieditor;

import java.awt.Toolkit;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;

import javax.swing.SwingUtilities;

import k.midieditor.gui.MidiEditorMain;
import k.midieditor.gui.SideConsole;
import k.midieditor.util.Helper;
import static k.midieditor.util.Helper.CommandLine.*;

public class MidiEditor {
	public static String VERSION = "1.2 alpha";
	public static MidiEditorMain mainwin;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PrintStream out = SideConsole.earlyChainO(), temp = System.out;
		System.setOut(out);
		PrintStream err = System.err, temp_ = System.err;
		Helper.Array.print(args);
		args = normalizeCommandArgs(args);
		for (String s : args) {
			if (s.equalsIgnoreCase("-debug")) {
				err = SideConsole.earlyChainE();
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
			System.out.println("MidiEditor v" + VERSION);
			MidiEditorMain.kill();
		}
		if (hasKey("versionAndLaunch")) {
			System.out.println("MidiEditor v" + VERSION);
		}
		try {
			System.setOut(temp);
			System.setErr(temp_);
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
