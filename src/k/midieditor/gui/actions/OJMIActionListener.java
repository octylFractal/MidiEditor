package k.midieditor.gui.actions;

import java.awt.Dialog;
import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FilenameFilter;

import k.midieditor.MidiEditor;
import k.midieditor.gui.MidiEditorMain;

public class OJMIActionListener extends JMIActionListener {
	private File workingDir = new File(System.getProperty("user.home", "/"));
	private File oldDir = null;
	private FileDialog dialog = new FileDialog((Dialog) null,
			"Choose a file: ", FileDialog.LOAD);

	public OJMIActionListener() {
		super("Open", MidiEditorMain.OPEN_JMIKEY, MidiEditorMain.FILE_KEY);
	}

	@Override
	public void onAction(ActionEvent e) {
		FileDialog fd = getDialog();
		fd.setVisible(true);
		String out = fd.getFile();
		if (out != null && out.endsWith(".mid")) {
			MidiEditor.mainwin.setMidiFileReload(new File(out));
		}
	}

	public FileDialog getDialog() {
		if (dialog.getDirectory() == null && workingDir != null) {
			dialog.setDirectory(workingDir.getAbsolutePath());
		}
		if (oldDir != null && workingDir != null
				&& dialog.getDirectory().equals(oldDir.getAbsolutePath())
				|| !dialog.getDirectory().equals(workingDir.getAbsolutePath())) {
			dialog.setDirectory(workingDir.getAbsolutePath());
			dialog.setFilenameFilter(new FilenameFilter() {

				@Override
				public boolean accept(File dir, String name) {
					return dir.getAbsolutePath().endsWith(".mid");
				}
			});
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
