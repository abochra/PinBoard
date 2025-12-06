package pobj.pinboard.editor.tools.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.List;
import javafx.scene.input.MouseEvent;
import pobj.pinboard.document.Clip;
import pobj.pinboard.document.ClipHeart;
import pobj.pinboard.editor.EditorInterface;
import pobj.pinboard.editor.tools.Tool;
import pobj.pinboard.editor.tools.ToolHeart;

public class ToolHeartTest extends ToolTest {
    private EditorInterface editor = new MockEditor();
    private Tool tool = new ToolHeart();
    
    @Test
    public void testCreate() {
        assertTrue(editor.getBoard().getContents().isEmpty());
        tool.press(editor, makeMouseEvent(MouseEvent.MOUSE_PRESSED, 100, 200, false));
        assertTrue(editor.getBoard().getContents().isEmpty());
        tool.drag(editor, makeMouseEvent(MouseEvent.MOUSE_DRAGGED, 300, 400, false));
        assertTrue(editor.getBoard().getContents().isEmpty());
        tool.release(editor, makeMouseEvent(MouseEvent.MOUSE_RELEASED, 300, 400, false));
        List<Clip> list = editor.getBoard().getContents();
        assertEquals(1, list.size());
        assertTrue(list.get(0) instanceof ClipHeart);
        ClipHeart h = (ClipHeart) list.get(0);
        assertEquals(100., h.getLeft(), 0.);
        assertEquals(200., h.getTop(), 0.);
        assertEquals(300., h.getRight(), 0.);
        assertEquals(400., h.getBottom(), 0.);
    }
    
    @Test
    public void testCreate2() {
        tool.press(editor, makeMouseEvent(MouseEvent.MOUSE_PRESSED, 100, 200, false));
        tool.drag(editor, makeMouseEvent(MouseEvent.MOUSE_DRAGGED, 400, 500, false));
        tool.drag(editor, makeMouseEvent(MouseEvent.MOUSE_DRAGGED, 50, 50, false));
        tool.drag(editor, makeMouseEvent(MouseEvent.MOUSE_DRAGGED, 200, 50, false));
        tool.drag(editor, makeMouseEvent(MouseEvent.MOUSE_DRAGGED, 50, 600, false));
        tool.drag(editor, makeMouseEvent(MouseEvent.MOUSE_DRAGGED, 300, 400, false));
        tool.release(editor, makeMouseEvent(MouseEvent.MOUSE_RELEASED, 300, 400, false));
        List<Clip> list = editor.getBoard().getContents();
        assertEquals(1, list.size());
        assertTrue(list.get(0) instanceof ClipHeart);
        ClipHeart h = (ClipHeart) list.get(0);
        assertEquals(100., h.getLeft(), 0.);
        assertEquals(200., h.getTop(), 0.);
        assertEquals(300., h.getRight(), 0.);
        assertEquals(400., h.getBottom(), 0.);
    }
    
    @Test
    public void testCreate3() {
        tool.press(editor, makeMouseEvent(MouseEvent.MOUSE_PRESSED, 300, 400, false));
        tool.drag(editor, makeMouseEvent(MouseEvent.MOUSE_DRAGGED, 100, 200, false));
        tool.release(editor, makeMouseEvent(MouseEvent.MOUSE_RELEASED, 100, 200, false));
        List<Clip> list = editor.getBoard().getContents();
        assertEquals(1, list.size());
        assertTrue(list.get(0) instanceof ClipHeart);
        ClipHeart h = (ClipHeart) list.get(0);
        assertEquals(100., h.getLeft(), 0.);
        assertEquals(200., h.getTop(), 0.);
        assertEquals(300., h.getRight(), 0.);
        assertEquals(400., h.getBottom(), 0.);
    }
}