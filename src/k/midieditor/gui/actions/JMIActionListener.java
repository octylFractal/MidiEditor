package k.midieditor.gui.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import k.midieditor.gui.Menu;
import k.midieditor.gui.MidiEditorMain;

public abstract class JMIActionListener implements ActionListener {
	protected String cmd, jmi_ref, menu;

	public static class NonAbstractJMIActionListener extends JMIActionListener {

		public NonAbstractJMIActionListener(String command,
				String jmi_ref_name, String mtitle) {
			super(command, jmi_ref_name, mtitle);
		}

		@Override
		public void onAction(ActionEvent e) {
			System.err
					.println("NonAbstract JMIListener was used! Bad idea for you!");
		}

	}

	private static HashMap<String, JMIActionListener> hm = new HashMap<String, JMIActionListener>();
	private static HashMap<String, String> reg_refs = new HashMap<String, String>();
	private static Menu cmenu = Menu.get("midieditormain");

	public JMIActionListener(String command, String jmi_ref_name, String mtitle) {
		hm.put(jmi_ref_name, this);
		reg_refs.put(command, mtitle);
		cmd = command;
		jmi_ref = jmi_ref_name;
		menu = mtitle;
		System.out.println("registerd new listener: " + hm.get(jmi_ref_name)
				+ " " + reg_refs.get(command));
	}

	public static final JMIActionListener NEW_LISTENER = new NJMIActionListener(),
			OPEN_LISTENER = new OJMIActionListener(),
			SAVE_LISTENER = new SJMIActionListener(),
			SAVEA_LISTENER = new SAJMIActionListener(),
			UNDO_LISTENER = new UJMIActionListener("Undo",
					MidiEditorMain.UNDO_JNIKEY),
			UNDOR_LISTENER = new UJMIActionListener("Undo...",
					MidiEditorMain.UNDOR_JNIKEY),
			REDO_LISTENER = new RJNIActionListener("Redo",
					MidiEditorMain.REDO_JNIKEY),
			REDOR_LISTENER = new RJNIActionListener("Redo...",
					MidiEditorMain.REDOR_JNIKEY);
	public static final JMIActionListener inst = new NonAbstractJMIActionListener(
			"3.14159", null, "35.0");

	@Override
	public void actionPerformed(ActionEvent e) {
		String translatedKey = cmenu.translateJMITitleToRef(
				reg_refs.get(e.getActionCommand()), e.getActionCommand());
		JMIActionListener listener = hm.get(translatedKey);
		if (listener == null && !(translatedKey == null)) {
			System.err.println("Warning: No registered listener for item: "
					+ translatedKey);
			return;
		} else if (listener == inst) {
			System.err.println("Null entry!");
			return;
		}
		System.err.println("Inovking class " + listener.getClass().getName());
		listener.onAction(e);
	}

	public abstract void onAction(ActionEvent e);

}
