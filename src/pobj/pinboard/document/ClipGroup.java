package pobj.pinboard.document;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class ClipGroup extends AbstractClip implements Composite {
	private List<Clip> group;
	
	public ClipGroup() {
		super(0,0,0,0,Color.BLACK);
		group = new ArrayList<>();
		updateBounds();
	}
	
	@Override
	public void draw(GraphicsContext ctx) {
		// TODO Auto-generated method stub
		for (Clip c : group) {
			c.draw(ctx);
		}
	}

	@Override
	public Clip copy() {
		// TODO Auto-generated method stub
		ClipGroup nv_group = new ClipGroup();
		for (Clip c : group) {
			nv_group.addClip(c.copy());;
		}
		return nv_group;
	}

	@Override
	public List<Clip> getClips() {
		// TODO Auto-generated method stub
		return group;
	}

	@Override
	public void addClip(Clip toAdd) {
		// TODO Auto-generated method stub
		group.add(toAdd);
		updateBounds();
	}

	@Override
	public void removeClip(Clip toRemove) {
		// TODO Auto-generated method stub
		group.remove(toRemove);
		updateBounds();
	}
	
	public void move(double x, double y) {
		for (Clip c : group) {
			c.move(x, y);
		}
		super.move(x, y);
	}
	
	public void setGeometry(double left, double top, double right, double bottom) {
		double diff_x = left - getLeft();
		double diff_y = top - getTop();
		move(diff_x, diff_y);
	}
	
	private void updateBounds() {
        if (group.isEmpty()) {
            setGeometry(0, 0, 0, 0);
            return;
        }
        
        double minLeft = Double.MAX_VALUE;
        double minTop = Double.MAX_VALUE;
        double maxRight = Double.MIN_VALUE;
        double maxBottom = Double.MIN_VALUE;
        
        for (Clip clip : group) {
            minLeft = Math.min(minLeft, clip.getLeft());
            minTop = Math.min(minTop, clip.getTop());
            maxRight = Math.max(maxRight, clip.getRight());
            maxBottom = Math.max(maxBottom, clip.getBottom());
        }
        
        super.setGeometry(minLeft, minTop, maxRight, maxBottom);
    }
	
	public boolean isSelected(double x, double y) {
        for (Clip c : group) {
            if (c.isSelected(x, y)) {
                return true;
            }
        }
        return false;
    }

}
