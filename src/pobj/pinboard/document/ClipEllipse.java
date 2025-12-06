package pobj.pinboard.document;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class ClipEllipse extends AbstractClip{
	
	public ClipEllipse(double left, double top, double right, double bottom, Color color) {
		super(left, top, right, bottom, color);
	}
	@Override
	public void draw(GraphicsContext ctx) {
		// TODO Auto-generated method stub
		ctx.setFill(getColor());
		ctx.fillOval(getLeft(), getTop(), getWidth(), getHeight());
	}
	
	public boolean isSelected(double x, double y) {
		double cx = (getLeft() + getRight()) / 2;
		double cy = (getTop() + getBottom()) / 2;
		double rx = (getRight() - getLeft()) / 2;
		double ry = (getBottom() - getTop()) / 2;
		
		double t1 = (x - cx)/rx;
		double t2 = (y - cy)/ry;
		double s = Math.pow(t1, 2) + Math.pow(t2,2);
		return s <= 1 ;
	}
	
	@Override
	public Clip copy() {
		// TODO Auto-generated method stub
		return new ClipEllipse(getLeft(), getTop(), getRight(), getBottom(), getColor());
	}

}
