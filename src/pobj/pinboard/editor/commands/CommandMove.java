package pobj.pinboard.editor.commands;

import java.util.ArrayList;
import java.util.List;

import pobj.pinboard.document.Clip;
import pobj.pinboard.editor.EditorInterface;

public class CommandMove implements Command {
	private EditorInterface editor;
	private List<Clip> clips;
	private double x;
	private double y;
	
	public CommandMove(EditorInterface editor, Clip toMove, double x, double y) {
		this.editor = editor;
		clips = new ArrayList<>();
		clips.add(toMove);
		this.x = x;
		this.y = y;
	}
	
	public CommandMove(EditorInterface editor, List<Clip> toMove, double x, double y) {
		this.editor = editor;
		clips = new ArrayList<>(toMove);
		this.x = x;
		this.y = y;
	}
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		for (Clip c : clips) {
			c.move(x, y);
		}
	}

	@Override
	public void undo() {
		// TODO Auto-generated method stub
		for (Clip c : clips) {
			c.move(-x, -y);
		}
	}

}
