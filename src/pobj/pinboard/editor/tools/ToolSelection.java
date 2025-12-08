package pobj.pinboard.editor.tools;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import pobj.pinboard.document.Clip;
import pobj.pinboard.editor.EditorInterface;

public class ToolSelection implements Tool {
	private double x_dernier;
	private double y_dernier;
	private boolean dessine;
	
	public ToolSelection() {
		dessine = false;
	}
	@Override
	public void press(EditorInterface i, MouseEvent e) {
		// TODO Auto-generated method stub
		x_dernier = e.getX();
		y_dernier = e.getY();
		if (e.isShiftDown()) {
			i.getSelection().toggleSelect(i.getBoard(), x_dernier, y_dernier);
		}else {
			i.getSelection().select(i.getBoard(), x_dernier, y_dernier);
		}
		
		dessine = !i.getSelection().getContents().isEmpty();
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
			
			x_dernier = e.getX();
			y_dernier = e.getY();
		}
	}

	@Override
	public void release(EditorInterface i, MouseEvent e) {
		// TODO Auto-generated method stub
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
