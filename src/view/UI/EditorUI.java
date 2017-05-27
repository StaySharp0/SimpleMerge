package view.UI;

import javafx.scene.control.TabPane;

/**
 * Created by yongjunkim on 2017. 5. 27..
 */
enum Status { VIEW, EDIT };

public class EditorUI implements btnAction {
    private int position;
    private TabPane tabPane;
    private Status status;

    public EditorUI(int pos, TabPane tabPane){
        this.position = pos;
        this.tabPane = tabPane;
        this.status = status.VIEW;
    }

    public void ShowList(){
        tabPane.getSelectionModel().select(0);
    }

    public void ShowTextArea(){
        tabPane.getSelectionModel().select(1);
    }


    @Override
    public int getPosition() {
        return this.position;
    }

    @Override
    public Boolean onAction(EventCallback cb) {
        switch (status){
            case VIEW: // view -> edit
                this.ShowTextArea();
                this.status = status.EDIT;

                break;
            case EDIT: // edit -> view
                this.ShowList();
                this.status = status.VIEW;
        }

        return true;
    }
}
