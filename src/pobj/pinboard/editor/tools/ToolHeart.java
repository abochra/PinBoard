package pobj.pinboard.editor.tools;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import pobj.pinboard.document.ClipEllipse;
import pobj.pinboard.document.ClipHeart;
import pobj.pinboard.editor.EditorInterface;
import pobj.pinboard.editor.commands.CommandAdd;

public class ToolHeart implements Tool {
	private double x_debut;
	private double y_debut;
	private double x_fin;
	private double y_fin;
	private boolean dessine;
	
	public ToolHeart() {
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
				ClipHeart new_hrt = new ClipHeart(left, top, right, bottom, Color.RED);
				CommandAdd cmd = new CommandAdd(i, new_hrt);
				cmd.execute();
				if (i.getUndoStack() != null) {
					i.getUndoStack().addCommand(cmd);
				}
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
			
			gc.setStroke(Color.RED);
			gc.setLineWidth(2);
			drawHeartOutline(gc, left, top, right - left, bottom - top);
		}
	}
	
	private void drawHeartOutline(GraphicsContext ctx, double x, double y, double width, double height) {
		double pX = x + width / 2.0;
        double pY = y + (height / 100.0) * 33.33;
        
        ctx.beginPath();
        ctx.moveTo(pX, pY);
        
        double x1 = x + (width / 100.0) * 50;
        double y1 = y + (height / 100.0) * 5;
        double x2 = x + (width / 100.0) * 90;
        double y2 = y + (height / 100.0) * 10;
        double x3 = x + (width / 100.0) * 90;
        double y3 = y + (height / 100.0) * 33.33;
        ctx.bezierCurveTo(x1, y1, x2, y2, x3, y3);
        
        x1 = x + (width / 100.0) * 90;
        y1 = y + (height / 100.0) * 55;
        x2 = x + (width / 100.0) * 65;
        y2 = y + (height / 100.0) * 60;
        x3 = x + (width / 100.0) * 50;
        y3 = y + (height / 100.0) * 90;
        ctx.bezierCurveTo(x1, y1, x2, y2, x3, y3);
        
        ctx.lineTo(pX, pY);
        
        x1 = x + (width / 100.0) * 50;
        y1 = y + (height / 100.0) * 5;
        x2 = x + (width / 100.0) * 10;
        y2 = y + (height / 100.0) * 10;
        x3 = x + (width / 100.0) * 10;
        y3 = y + (height / 100.0) * 33.33;
        ctx.bezierCurveTo(x1, y1, x2, y2, x3, y3);
        
        x1 = x + (width / 100.0) * 10;
        y1 = y + (height / 100.0) * 55;
        x2 = x + (width / 100.0) * 35;
        y2 = y + (height / 100.0) * 60;
        x3 = x + (width / 100.0) * 50;
        y3 = y + (height / 100.0) * 90;
        ctx.bezierCurveTo(x1, y1, x2, y2, x3, y3);
        
        ctx.closePath();
        ctx.stroke();
	}

	@Override
	public String getName(EditorInterface editor) {
		// TODO Auto-generated method stub
		return "Heart Tool";
	}

}
