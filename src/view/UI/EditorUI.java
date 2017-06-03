package view.UI;

import javafx.scene.control.Button;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;

/**
 * Created by yongjunkim on 2017. 5. 27..
 */
enum Status { VIEW, EDIT };

public class EditorUI implements btnAction {
    private Button btnEdit;
    private int position;
    private TextArea textArea;
    private TabPane tabPane;
    private Status status;

    public EditorUI(int pos, TabPane tabPane, TextArea textArea, Button btnEdit){
        this.position = pos;
        this.tabPane = tabPane;
        this.textArea = textArea;
        this.btnEdit = btnEdit;
        this.status = status.VIEW;
    }

    public void ShowViewMode(){
        status = status.VIEW;
        textArea.setEditable(false);
        tabPane.getSelectionModel().select(1);

        btnEdit.getStyleClass().remove("edit");
        btnEdit.getStyleClass().add("view");

    }
    public void ShowEditMode(){
        status = status.EDIT;
        textArea.setEditable(true);
        tabPane.getSelectionModel().select(1);

        btnEdit.getStyleClass().remove("view");
        btnEdit.getStyleClass().add("edit");
    }

    public void ShowCompareMode(){
        tabPane.getSelectionModel().select(0);
    }


    @Override
    public int getPosition() {
        return this.position;
    }

    @Override
    public Boolean onAction(EventCallback cb) {
        switch (status){
            case VIEW: // view -> edit
                ShowEditMode();
                break;
            case EDIT: // edit -> view
                ShowViewMode();
        }

        cb.callbackMethod(textArea.getText());

        return true;
    }
}
