package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import view.UI.EditorUI;
import view.UI.FileOpenUI;
import view.UI.btnAction;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML private Button btnLeftFileOpen, btnRightFileOpen, btnLeftFileEdit, btnRightFileEdit, btnLeftFileSave ,btnRightFileSave;
    @FXML private TextField fieldLeftFile, fieldRightFile;
    @FXML private TabPane tabLeft, tabRight;

    @FXML private ListView<String> listLeft, listRight;
    @FXML private TextArea textAreaLeft;


    private HashMap<String,btnAction> button;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("init");

        button = new HashMap();
        button.put(btnLeftFileOpen.getId(),
                new FileOpenUI(Main.getPrimaryStage(), fieldLeftFile, listLeft, textAreaLeft));


        button.put(btnLeftFileEdit.getId(),new EditorUI(tabLeft));

//        button.put(btnLeftFileSave.getId(),new FileUI());
//        button.put(btnRightFileSave.getId(),new FileUI());


    }

    public void eventFileOpen(ActionEvent e) {
        System.out.println("Click:fileOpen");

        Control target = (Control)e.getSource();
        button.get(target.getId()).onAction((source) -> {
            System.out.println("FileOpen callback");
            //Model.Update((File)source));

        });

    }

    public void eventFileEdit(ActionEvent e) {

        Control target = (Control)e.getSource();
//        button.get(target.getId()).onAction();
    }

    public void eventFileSave(ActionEvent e) {
        System.out.println("Click:LeftFileSave");
    }

}
