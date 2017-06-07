package view.UI;

import javafx.scene.control.Button;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import dataSet.FileEditSave;

/**
 * Created by yongjunkim on 2017. 5. 27..
 */
enum Status { VIEW, EDIT };

public class EditorUI implements btnAction {
    private TextField title;
    private Button btnEdit;
    private int position;
    private TextArea textArea;
    private TabPane tabPane;
    private Status status;

    public EditorUI(int pos, TabPane tabPane, TextField textField, TextArea textArea, Button btnEdit){
        this.position = pos;
        this.tabPane = tabPane;
        this.title = textField;
        this.textArea = textArea;
        this.btnEdit = btnEdit;

        ShowViewMode();
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
        title.setText("Edit: "+title.getText());
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
        FileEditSave data = cb.callbackMethod(textArea.getText());

        switch (status){
            case VIEW: // view -> edit
                ShowEditMode();
                break;
            case EDIT: // edit -> view
                ShowViewMode();

                if(data == null) return false;
                title.setText(data.getFileName());
        }

        return true;
    }
}
