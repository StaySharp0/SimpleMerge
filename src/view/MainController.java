package view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import view.UI.Position;
import view.UI.DataSet.Item;
import view.UI.EditorUI;
import view.UI.FileOpenUI;

import view.UI.btnAction;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class MainController implements Initializable {


    // init FXML Objects
    @FXML private Button    btnLeftFileOpen, btnRightFileOpen,
                            btnLeftFileEdit, btnRightFileEdit,
                            btnLeftFileSave ,btnRightFileSave,
                            btnCompare,
                            btnMtoLeft, btnMtoRight;

    @FXML private TextField fieldLeftFile, fieldRightFile;
    @FXML private TabPane tabLeft, tabRight;

    @FXML private ListView<String> listLeft, listRight;
    @FXML private TextArea textAreaLeft,textAreaRight;


    private HashMap<String,btnAction> button;

    private FileOpenUI[] fileOpenUI = new FileOpenUI[2];
    private EditorUI[] editorUI = new EditorUI[2];


    private Button[] btnFileOpen = new Button[2];
    private Button[] btnFileEdit = new Button[2];
    private Button[] btnFileSave = new Button[2];


    // condition value
    private Boolean[] condLoadFile = { false, false };
    private Boolean condCompare = false;




    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("init");

        // FXML value -> position array
        btnFileOpen[Position.LEFT] = btnLeftFileOpen;
        btnFileOpen[Position.RIGHT] = btnRightFileOpen;
        btnFileEdit[Position.LEFT] = btnLeftFileEdit;
        btnFileEdit[Position.RIGHT] = btnRightFileEdit;
        btnFileSave[Position.LEFT] = btnLeftFileSave;
        btnFileSave[Position.RIGHT] = btnRightFileSave;


        button = new HashMap();

        fileOpenUI[Position.LEFT] = new FileOpenUI(Position.LEFT, Main.getPrimaryStage(), fieldLeftFile, listLeft, textAreaLeft);
        fileOpenUI[Position.RIGHT] = new FileOpenUI(Position.RIGHT, Main.getPrimaryStage(), fieldRightFile, listRight, textAreaRight);
        editorUI[Position.LEFT] = new EditorUI(Position.LEFT, tabLeft);
        editorUI[Position.RIGHT] = new EditorUI(Position.RIGHT, tabRight);


        button.put(btnLeftFileOpen.getId(), fileOpenUI[Position.LEFT]);
        button.put(btnRightFileOpen.getId(), fileOpenUI[Position.RIGHT]);
//        button.put(btnLeftFileEdit.getId(), editorUI[Position.LEFT]);
//        button.put(btnRightFileEdit.getId(), editorUI[Position.RIGHT]);

    }

    public void eventFileOpen(ActionEvent e) {

        // Get Button
        Control target = (Control)e.getSource();
        btnAction ui = button.get(target.getId());

        // Call event
        Boolean status = ui.onAction((source) -> {

            System.out.println("FileOpen callback");
            // FileOpen data = Model.Update((File)source));

            return new Item();
        });


        int pos = ui.getPosition();
        editorUI[pos].ShowList();
        condLoadFile[pos] = status;

        // Set button disable
        btnFileEdit[pos].setDisable(!status);
        btnFileSave[pos].setDisable(!status);
        btnCompare.setDisable(!(condLoadFile[0] && condLoadFile[1]));
    }

    public void eventFileEdit(ActionEvent e) {

        Control target = (Control)e.getSource();
//        button.get(target.getId()).onAction(()->{
//
//            return new Item();
//        });
    }

    public void eventFileSave(ActionEvent e) {
        System.out.println("Click:LeftFileSave");
    }

}
