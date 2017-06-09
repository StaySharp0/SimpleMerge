package view.UI;

import javafx.scene.control.Button;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import dataSet.FileEditSave;

/**
 * Created by yongjunkim on 2017. 5. 27..
 */
enum Status { VIEW, EDIT, COMPARE };

public class EditorUI implements btnAction, ScreenMode {
    private TextField title;
    private Button btnEdit;
    private int position;
    private TextArea textArea;
    private TabPane tabPane;
    private Status status;
    private final String prefix = "Edit: ";


    public EditorUI(int pos, TabPane tabPane, TextField textField, TextArea textArea, Button btnEdit){
        this.position = pos;
        this.tabPane = tabPane;
        this.title = textField;
        this.textArea = textArea;
        this.btnEdit = btnEdit;

        ShowViewMode();
    }

    @Override
    public void ShowViewMode(){
        status = status.VIEW;
        tabPane.getSelectionModel().select(1);

        textArea.setEditable(false);

        btnEdit.getStyleClass().remove("view");
        btnEdit.getStyleClass().add("edit");

        SetFileName();
    }

    @Override
    public void ShowEditMode(){
        status = status.EDIT;
        tabPane.getSelectionModel().select(1);

        textArea.setEditable(true);

        btnEdit.getStyleClass().remove("edit");
        btnEdit.getStyleClass().add("view");

        SetFileName();
    }

    @Override
    public void ShowCompareMode(){
        status = status.COMPARE;
        tabPane.getSelectionModel().select(0);

        btnEdit.getStyleClass().remove("view");
        btnEdit.getStyleClass().add("edit");

        SetFileName();
    }

    private void SetFileName(){

        String fileName = title.getText();
        String[] tmp = fileName.split(prefix);

        switch (status){
            case EDIT:
                if(tmp.length == 1) title.setText(prefix+fileName);
                break;
            default:
                if(tmp.length == 2) title.setText(tmp[1]);
        }

    }

    @Override
    public int getPosition() {
        return this.position;
    }

    @Override
    public Boolean onAction(EventCallback cb) {
        FileEditSave data = cb.callbackMethod(textArea.getText());

        switch (status){
            case COMPARE:   // compare -> edit
            case VIEW:      // view -> edit
                ShowEditMode();
                break;
            case EDIT:      // edit -> view
                ShowViewMode();

                if(data == null) return false;

                title.setText(data.getFileName());
        }

        return true;
    }
}
