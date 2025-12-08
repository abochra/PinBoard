package pobj.pinboard.editor;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import pobj.pinboard.document.*;

public class Selection {
	private List<Clip> selections;
	
	public Selection() {
		selections = new ArrayList<>();
	}
	
	public void select(Board board, double x, double y) {
		this.clear();
		for (Clip c : board.getContents()) {
			if (c.isSelected(x, y)) {
				selections.add(c);
				return;
			}
		}
	}
	
	public void toggleSelect(Board board, double x, double y) {
		for (Clip c : board.getContents()) {
			if (c.isSelected(x, y)) {
				if (selections.contains(c)) {
					selections.remove(c);
				}else {
					selections.add(c);
				}
				return;
			}
		}
	}
	
	public void clear() {
		selections.clear();
	}
	
	public List<Clip> getContents(){
		return selections;
	}
	
	public void drawFeedback(GraphicsContext gc) {
		gc.setStroke(Color.BLUE);
		gc.setLineWidth(2);
		for (Clip c : selections) {
			gc.strokeRect(c.getLeft(), c.getTop(), c.getRight() - c.getLeft(), c.getBottom() - c.getTop());
		}
	}
}
