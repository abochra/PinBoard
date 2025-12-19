package pobj.pinboard.editor.commands;

import java.util.ArrayList;
import java.util.List;

import pobj.pinboard.document.Clip;
import pobj.pinboard.document.ClipGroup;
import pobj.pinboard.document.Composite;
import pobj.pinboard.editor.EditorInterface;

public class CommandUngroup implements Command {
	private EditorInterface editor;
	private List<Clip> clips;
	private Composite group;
	
	public CommandUngroup(EditorInterface editor, Composite group) {
		this.editor = editor;
		clips = new ArrayList<>(group.getClips());
		this.group = group;
	}
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		editor.getBoard().removeClip((Clip)group);
		editor.getBoard().addClip(clips);
		editor.getSelection().clear();
		editor.getSelection().getContents().addAll(clips);
	}

	@Override
	public void undo() {
		// TODO Auto-generated method stub
		editor.getBoard().removeClip(clips);
		editor.getBoard().addClip((Clip)group);
		editor.getSelection().clear();
		editor.getSelection().getContents().add((Clip)group);
	}

}
