package pobj.pinboard.editor;

import pobj.pinboard.document.Clip;
import java.util.ArrayList;
import java.util.List;

public class Clipboard {
	private static Clipboard instance = new Clipboard();
	private List<Clip> copies;
	private List<ClipboardListener> listeners;
	
	private Clipboard() {
		copies = new ArrayList<>();
		listeners = new ArrayList<>();
	}
	
	public void copyToClipboard(List<Clip> clips) {
		for (Clip c : clips) {
			copies.add(c.copy());
		}
		notifyListeners();
	}
	
	public List<Clip> copyFromClipboard(){
		List<Clip> copy = new ArrayList<>();
		for (Clip c : copies) {
			copy.add(c.copy());
		}
		return copy;
	}
	
	public void clear() {
		copies.clear();
		notifyListeners();
	}
	
	public boolean isEmpty() {
		return copies.isEmpty();
	}
	
	public static Clipboard getInstance() {
		return instance;
	}
	
	public void addListener(ClipboardListener listener) {
		if (!listeners.contains(listener)) {
			listeners.add(listener);
		}
		
	}
	
	public void removeListener(ClipboardListener listener) {
		listeners.remove(listener);
	}
	
	private void notifyListeners() {
		for (ClipboardListener l : listeners) {
			l.clipboardChanged();
		}
	}
}
