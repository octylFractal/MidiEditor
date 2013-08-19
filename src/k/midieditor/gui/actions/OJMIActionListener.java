package k.midieditor.gui.actions;

import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import k.midieditor.MidiEditor;
import k.midieditor.gui.MidiEditorMain;

public class OJMIActionListener extends JMIActionListener {
	private File workingDir = new File(System.getProperty("user.home", "/"));
	private File oldDir = null;
	private JFileChooser dialog = new JFileChooser(workingDir);

	public OJMIActionListener() {
		super("Open", MidiEditorMain.OPEN_JMIKEY, MidiEditorMain.FILE_KEY);
	}

	@Override
	public void onAction(ActionEvent e) {
		JFileChooser fd = getDialog();
		fd.showOpenDialog(null);
		File out = fd.getSelectedFile();
		if (out != null) {
			MidiEditor.mainwin.setMidiFileReload(out);
		}
	}

	public JFileChooser getDialog() {
		if (dialog.getCurrentDirectory() == null && workingDir != null) {
			dialog.setCurrentDirectory(workingDir);
		}
		if (oldDir != null
				&& workingDir != null
				&& dialog.getCurrentDirectory()
						.equals(oldDir.getAbsolutePath())
				|| !dialog.getCurrentDirectory().equals(
						workingDir.getAbsolutePath())) {
			dialog.setCurrentDirectory(workingDir);
			dialog.setFileFilter(new FileNameExtensionFilter("MIDI Files",
					"mid", "midi"));
		}
		return dialog;
	}

	public File getWorkingDir() {
		return workingDir;
	}

	public void setWorkingDir(File f) {
		oldDir = workingDir;
		workingDir = f;
	}

	/**
	 * @return -1 if check is not the old dir or the current dir, 0 if it is the
	 *         current dir, and 1 if it is the old dir
	 */
	public int isFileWorkingDir(File check) {
		return check.equals(workingDir) ? 0 : check.equals(oldDir) ? 1 : -1;
	}
}
