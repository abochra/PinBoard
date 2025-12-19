package pobj.pinboard.editor.commands;

import java.util.ArrayList;
import java.util.List;

import pobj.pinboard.document.Clip;
import pobj.pinboard.editor.EditorInterface;

public class CommandAdd implements Command {
	private EditorInterface editor;
	private List<Clip> clips;
	
	
	public CommandAdd(EditorInterface editor, Clip toAdd) {
		this.editor = editor;
		clips = new ArrayList<>();
		clips.add(toAdd);
	}
	
	public CommandAdd(EditorInterface editor, List<Clip> toAdd) {
		this.editor = editor;
		clips = new ArrayList<>(toAdd);
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		editor.getBoard().addClip(clips);
	}

	@Override
	public void undo() {
		// TODO Auto-generated method stub
		editor.getBoard().removeClip(clips);
	}

}
