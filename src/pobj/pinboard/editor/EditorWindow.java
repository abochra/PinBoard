package pobj.pinboard.editor;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import pobj.pinboard.document.Board;
import pobj.pinboard.editor.tools.Tool;
import pobj.pinboard.editor.tools.ToolEllipse;
import pobj.pinboard.editor.tools.ToolHeart;
import pobj.pinboard.editor.tools.ToolRect;

public class EditorWindow implements EditorInterface{
	private Stage stage;
	private Board board;
	private Tool outil;
	private Canvas canvas;
	private Label statut;
	
	public EditorWindow(Stage stage) {
		this.stage = stage;
		this.board = new Board();
		this.stage.setTitle("PinBoard");
		this.outil = new ToolRect();
		
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
		
		Menu tools = new Menu("Tools");
		
		MenuItem rect = new MenuItem("Rectangle");
		rect.setOnAction((e) -> outil = new ToolRect());
		MenuItem ell = new MenuItem("Ellipse");
		ell.setOnAction((e) -> outil = new ToolEllipse());
		tools.getItems().addAll(rect, ell);
		
		menubar.getMenus().addAll(file, edit, tools);
		
		return menubar;
	}
	
	private ToolBar tool() {
		Button rect = new Button("Rect");
		rect.setOnAction((e) -> outil = new ToolRect());
		
		Button ellipse = new Button("Ellipse");
		ellipse.setOnAction((e) -> outil = new ToolEllipse());
		
		Button coeur = new Button("Coeur");
		coeur.setOnAction((e) -> outil = new ToolHeart());
		
		ToolBar bar = new ToolBar(rect, ellipse, coeur); 
		
		return bar;
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
		return null;
	}

	@Override
	public CommandStack getUndoStack() {
		// TODO Auto-generated method stub
		return null;
	}
}
