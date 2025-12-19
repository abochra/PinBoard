package pobj.pinboard.editor.commands;

import java.util.ArrayList;
import java.util.List;

import pobj.pinboard.document.Clip;
import pobj.pinboard.document.ClipGroup;
import pobj.pinboard.editor.EditorInterface;

public class CommandGroup implements Command {
	private EditorInterface editor;
	private List<Clip> clips;
	private ClipGroup group;
	
	public CommandGroup(EditorInterface editor, List<Clip> toGroup) {
		this.editor = editor;
		clips = new ArrayList<>(toGroup);
		group = new ClipGroup();
	}
	
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		editor.getBoard().removeClip(clips);
		for (Clip c : clips) {
			group.addClip(c);
		}
		editor.getBoard().addClip(group);
		editor.getSelection().clear();
		editor.getSelection().getContents().add(group);
	}

	@Override
	public void undo() {
		// TODO Auto-generated method stub
		editor.getBoard().removeClip(group);
		editor.getBoard().addClip(clips);
		for (Clip c : clips) {
			group.removeClip(c);
		}
		editor.getSelection().clear();
		editor.getSelection().getContents().addAll(clips);
	}

}
