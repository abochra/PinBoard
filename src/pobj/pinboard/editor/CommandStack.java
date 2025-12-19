package pobj.pinboard.editor;

import java.util.Stack;

import pobj.pinboard.editor.commands.Command;

public class CommandStack {
	private Stack<Command> undo;
	private Stack<Command> redo;
	
	public CommandStack() {
		this.redo = new Stack<>();
		this.undo = new Stack<>();
	}
	
	public void addCommand(Command cmd) {
		undo.push(cmd);
		redo.clear();
	}
	
	public void undo() {
		if (!undo.isEmpty()) {
			Command derniere = undo.pop();
			derniere.undo();
			redo.push(derniere);
		}
	}
	
	public void redo() {
		if (!redo.isEmpty()) {
			Command derniere = redo.pop();
			derniere.execute();
			undo.push(derniere);
		}
	}
	
	public boolean isUndoEmpty() {
		return undo.isEmpty();
	}
	
	public boolean isRedoEmpty() {
		return redo.isEmpty();
	}
}
