package k.midieditor.util;

import java.util.ArrayList;
import java.util.List;

public class MicroTracker {
	public static abstract class MicroListener {
		public abstract void onMicroChange(long new_micro);

		public abstract void onStart();

		public abstract void onEnd();
	}

	private static List<MicroListener> listeners = new ArrayList<MicroListener>();

	public static void addListener(MicroListener ml) {
		listeners.add(ml);
	}
}
