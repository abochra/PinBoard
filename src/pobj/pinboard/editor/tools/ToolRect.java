package pobj.pinboard.editor.tools;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import pobj.pinboard.document.ClipRect;
import pobj.pinboard.editor.EditorInterface;

public class ToolRect implements Tool {
	private double x_debut;
	private double y_debut;
	private double x_fin;
	private double y_fin;
	private boolean dessine;
	
	public ToolRect() {
		this.dessine = false;
	}
	
	@Override
	public void press(EditorInterface i, MouseEvent e) {
		// TODO Auto-generated method stub
		x_debut = e.getX();
		y_debut = e.getY();
		x_fin = x_debut;
		y_fin = y_debut;
		dessine = true;
	}

	@Override
	public void drag(EditorInterface i, MouseEvent e) {
		// TODO Auto-generated method stub
		if (dessine) {
			x_fin = e.getX();
			y_fin = e.getY();
		}
	}

	@Override
	public void release(EditorInterface i, MouseEvent e) {
		// TODO Auto-generated method stub
		if (dessine) {
			x_fin = e.getX();
			y_fin = e.getY();
			
			double left = Math.min(x_debut, x_fin);
			double right = Math.max(x_debut, x_fin);
			double top = Math.min(y_debut, y_fin);
			double bottom = Math.max(y_debut, y_fin);
			
			if (right - left > 1 && bottom - top > 1) {
				ClipRect new_rect = new ClipRect(left, top, right, bottom, Color.PINK);
				i.getBoard().addClip(new_rect);
			}
			
			dessine = false;
		}
	}

	@Override
	public void drawFeedback(EditorInterface i, GraphicsContext gc) {
		// TODO Auto-generated method stub
		if (dessine) {
			double left = Math.min(x_debut, x_fin);
			double right = Math.max(x_debut, x_fin);
			double top = Math.min(y_debut, y_fin);
			double bottom = Math.max(y_debut, y_fin);
			
			gc.setStroke(Color.PINK);
			gc.setLineWidth(2);
			gc.strokeRect(left, top, right - left, bottom - top);
		}
	}

	@Override
	public String getName(EditorInterface editor) {
		// TODO Auto-generated method stub
		return "Rectangle Tool";
	}

}
