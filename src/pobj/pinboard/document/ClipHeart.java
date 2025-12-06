package pobj.pinboard.document;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class ClipHeart extends AbstractClip{
	
	public ClipHeart(double left, double top, double right, double bottom, Color color) {
		super(left, top, right, bottom, color);
	}
	
	@Override
	public void draw(GraphicsContext ctx) {
		// TODO Auto-generated method stub
		ctx.setFill(getColor());
		drawHeart(ctx, getLeft(), getTop(), getWidth(), getHeight());
	}
	
	private void drawHeart(GraphicsContext ctx, double x, double y, double width, double height) {
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
        ctx.fill();
	}
	
	@Override
	public boolean isSelected(double x, double y) {
	    if (!super.isSelected(x, y)) {
	        return false;
	    }
	    double relX = (x - getLeft()) / getWidth();
	    double relY = (y - getTop()) / getHeight();
	    if (relY < 0.3) {
	        double distFromLeft = Math.abs(relX - 0.25);
	        double distFromRight = Math.abs(relX - 0.75);
	        return (distFromLeft < 0.25 || distFromRight < 0.25);
	    }
	    double maxDistFromCenter = 0.5 * (1.2 - relY);
	    double distFromCenter = Math.abs(relX - 0.5);
	    return distFromCenter <= maxDistFromCenter;
	}
	
	@Override
	public Clip copy() {
		// TODO Auto-generated method stub
		return new ClipHeart(getLeft(), getTop(), getRight(), getBottom(), getColor());
	}

}
