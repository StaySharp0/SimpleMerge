package view.UI;

import javafx.scene.control.TabPane;

/**
 * Created by yongjunkim on 2017. 5. 27..
 */
enum Status { VIEW, EDIT };

public class EditorUI implements btnAction {
    private TabPane tabPane;
    private Status status;


    public EditorUI(TabPane tabPane){
        this.tabPane = tabPane;
        this.status = status.VIEW;

    }

    @Override
    public void onAction() {

    }

    @Override
    public void onAction(EventCallback cb) {
        switch (status){
            case VIEW:
                tabPane.getSelectionModel().select(1);
                this.status = status.EDIT;
                break;
            case EDIT:
                tabPane.getSelectionModel().select(0);
                this.status = status.VIEW;
        }

    }
}
