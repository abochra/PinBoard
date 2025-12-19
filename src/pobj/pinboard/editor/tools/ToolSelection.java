package pobj.pinboard.editor.tools;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import pobj.pinboard.document.Clip;
import pobj.pinboard.editor.EditorInterface;
import pobj.pinboard.editor.commands.CommandMove;

public class ToolSelection implements Tool {
	private double x_dernier;
	private double y_dernier;
	private boolean dessine;
	private double total_deltax;
	private double total_deltay;
	private List<Clip> selectionnes;
	
	public ToolSelection() {
		dessine = false;
	}
	
	@Override
	public void press(EditorInterface i, MouseEvent e) {
		// TODO Auto-generated method stub
		x_dernier = e.getX();
		y_dernier = e.getY();
		total_deltax = 0;
		total_deltay = 0;
		if (e.isShiftDown()) {
			i.getSelection().toggleSelect(i.getBoard(), x_dernier, y_dernier);
		}else {
			i.getSelection().select(i.getBoard(), x_dernier, y_dernier);
		}
		
		if(!i.getSelection().getContents().isEmpty()) {
			dessine = true;
			selectionnes = new ArrayList<>(i.getSelection().getContents());
		}
	}

	@Override
	public void drag(EditorInterface i, MouseEvent e) {
		// TODO Auto-generated method stub
		if (dessine) {
			double nv_x = e.getX() - x_dernier;
			double nv_y = e.getY() - y_dernier;
			
			for (Clip c : i.getSelection().getContents()) {
				c.move(nv_x, nv_y);
			}
			total_deltax += nv_x;
			total_deltay += nv_y;
			
			x_dernier = e.getX();
			y_dernier = e.getY();
		}
	}

	@Override
	public void release(EditorInterface i, MouseEvent e) {
		// TODO Auto-generated method stub
		if (dessine && (total_deltax != 0 || total_deltay != 0)) {
			CommandMove cmd = new CommandMove(i, selectionnes, total_deltax, total_deltay);
			if (i.getUndoStack() != null) {
				i.getUndoStack().addCommand(cmd);
			}
		}
		dessine = false;
	}

	@Override
	public void drawFeedback(EditorInterface i, GraphicsContext gc) {
		// TODO Auto-generated method stub
		i.getSelection().drawFeedback(gc);
	}

	@Override
	public String getName(EditorInterface editor) {
		// TODO Auto-generated method stub
		return "Selection Tool";
	}

}
