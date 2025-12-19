package pobj.pinboard.editor;


import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import pobj.pinboard.document.Board;
import pobj.pinboard.document.Clip;
import pobj.pinboard.document.ClipGroup;
import pobj.pinboard.document.Composite;
import pobj.pinboard.editor.commands.CommandAdd;
import pobj.pinboard.editor.commands.CommandGroup;
import pobj.pinboard.editor.commands.CommandUngroup;
import pobj.pinboard.editor.tools.Tool;
import pobj.pinboard.editor.tools.ToolEllipse;
import pobj.pinboard.editor.tools.ToolHeart;
import pobj.pinboard.editor.tools.ToolRect;
import pobj.pinboard.editor.tools.ToolSelection;

public class EditorWindow implements EditorInterface, ClipboardListener{
	private Stage stage;
	private Board board;
	private Tool outil;
	private Canvas canvas;
	private Label statut;
	private Selection selection;
	private MenuItem paste;
	private MenuItem redo;
	private MenuItem undo;
	private CommandStack pile;
	
	public EditorWindow(Stage stage) {
		this.stage = stage;
		this.board = new Board();
		this.stage.setTitle("PinBoard");
		this.outil = new ToolRect();
		this.selection = new Selection();
		this.pile = new CommandStack();
		
		VBox vbox = new VBox();
		MenuBar menu = menu();
		ToolBar bar = tool();
		
		this.canvas = new Canvas(800,600);
		setCanvas();
		
		Separator separator = new Separator();
		
		this.statut = new Label("label");
		vbox.getChildren().addAll(menu, bar, canvas, separator, statut);
		
		Scene scene = new Scene(vbox);
		this.stage.setScene(scene);
		
		Clipboard.getInstance().addListener(this);
		stage.setOnCloseRequest(e -> {
            Clipboard.getInstance().removeListener(this);
        });
		stage.show();
		draw();
	}
	
	private MenuBar menu() {
		MenuBar menubar = new MenuBar();
		
		Menu file = new Menu("File");
		MenuItem nv_file = new MenuItem("New");
		nv_file.setOnAction((e) -> new EditorWindow(new Stage()));
		
		MenuItem close_file = new MenuItem("Close");
		close_file.setOnAction((e) -> stage.close());
		file.getItems().addAll(nv_file, new SeparatorMenuItem(), close_file);
		
		Menu edit = new Menu("Edit");
		
		undo = new MenuItem("Undo");
		undo.setOnAction((e) -> {
			pile.undo();
			updateMenuState();
			draw();
		});
		undo.setDisable(true);
		
		redo = new MenuItem("Redo");
		redo.setOnAction((e) -> {
			pile.redo();
			updateMenuState();
			draw();
		});
		redo.setDisable(true);
		
		MenuItem copy = new MenuItem("Copy");
		copy.setOnAction((e) -> Clipboard.getInstance().copyToClipboard(selection.getContents()));
		
		paste = new MenuItem("Paste");
		paste.setOnAction((e) -> {
			CommandAdd cmd = new CommandAdd(this, Clipboard.getInstance().copyFromClipboard());
			cmd.execute();
			pile.addCommand(cmd);
			updateMenuState();
			draw();
		});
		paste.setDisable(Clipboard.getInstance().isEmpty());
		
		MenuItem delete = new MenuItem("Delete");
		delete.setOnAction((e) -> {
			board.removeClip(selection.getContents());
			selection.clear();
			draw();
		});
		
		MenuItem group = new MenuItem("Group");
		group.setOnAction((e) -> {
			if (selection.getContents().size() >= 2) {
				CommandGroup cmd = new CommandGroup(this, selection.getContents());
				cmd.execute();
				pile.addCommand(cmd);
				updateMenuState();
				draw();
			}
		});
		
		MenuItem ungroup = new MenuItem("Ungroup");
		ungroup.setOnAction((e)-> {
			if (!selection.getContents().isEmpty() && selection.getContents().get(0) instanceof Composite) {
                CommandUngroup cmd = new CommandUngroup(this, (Composite) selection.getContents().get(0));
                cmd.execute();
                pile.addCommand(cmd);
                updateMenuState();
                draw();
            }
		});
		
		edit.getItems().addAll(undo, redo, copy, paste, delete, group, ungroup);
		
		
		Menu tools = new Menu("Tools");
		
		MenuItem rect = new MenuItem("Rectangle");
		rect.setOnAction((e) -> outil = new ToolRect());
		MenuItem ell = new MenuItem("Ellipse");
		ell.setOnAction((e) -> outil = new ToolEllipse());
		MenuItem heart = new MenuItem("Heart");
        heart.setOnAction((e) -> outil = new ToolHeart());
        MenuItem select = new MenuItem("Select");
        select.setOnAction((e) -> outil = new ToolSelection());
		
		tools.getItems().addAll(rect, ell, heart, select);
		
		menubar.getMenus().addAll(file, edit, tools);
		
		return menubar;
	}
	
	private ToolBar tool() {
		Button rect = new Button("Rect");
		rect.setOnAction((e) -> outil = new ToolRect());
		
		Button ellipse = new Button("Ellipse");
		ellipse.setOnAction((e) -> outil = new ToolEllipse());
		
		Button heart = new Button("Heart");
		heart.setOnAction((e) -> outil = new ToolHeart());
		
		Button select = new Button("Select");
		select.setOnAction((e) -> outil = new ToolSelection());
		
		ToolBar bar = new ToolBar(rect, ellipse, heart, select); 
		
		return bar;
	}
	
	public void updateMenuState() {
		undo.setDisable(pile.isUndoEmpty());
		redo.setDisable(pile.isRedoEmpty());
	}
	
	private void setCanvas() {
		canvas.setOnMousePressed((e) -> {outil.press(this, e); draw();});
		canvas.setOnMouseDragged((e) -> {outil.drag(this, e); draw();});
		canvas.setOnMouseReleased((e) -> {outil.release(this, e); draw();});
	}
	
	private void draw() {
		GraphicsContext gc = canvas.getGraphicsContext2D();
		board.draw(gc);
		outil.drawFeedback(this, gc);
	}
	
	@Override
	public Board getBoard() {
		// TODO Auto-generated method stub
		return board;
	}

	@Override
	public Selection getSelection() {
		// TODO Auto-generated method stub
		return selection;
	}

	@Override
	public CommandStack getUndoStack() {
		// TODO Auto-generated method stub
		return pile;
	}
	
	public void clipboardChanged() {
		this.paste.setDisable(Clipboard.getInstance().isEmpty());
	}
}
