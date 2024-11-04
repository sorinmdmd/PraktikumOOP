package gui;

import business.TeppichenModel;
import javafx.stage.Stage;

public class TeppichControl {
	private Stage stage;
	private TeppichenModel model;
	private TeppichenView view;

	public TeppichControl(Stage stage) {
		super();
		this.stage = stage;
		this.model = new TeppichenModel();
		this.view = new TeppichenView(stage, model, this);
	}

	
	
}
