package view;

import com.sun.scenario.effect.Merge;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;

import javafx.scene.input.MouseEvent;
import view.UI.*;
import view.UI.DataSet.Item;
import view.UI.DataSet.MergeCompare;

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
    private Button[]    btnFileOpen, btnFileEdit, btnFileSave;


    @FXML private TabPane tabLeft, tabRight;
    @FXML private ListView<String> listLeft, listRight;
    @FXML private TextField fieldLeftFile, fieldRightFile;
    @FXML private TextArea textAreaLeft,textAreaRight;
    private TabPane[] tabPanes;
    private TextField[] textFields;
    private ListView[] listViews;
    private ObservableList[] listModels;
    private TextArea[] textAreas;


    private HashMap<String,btnAction> button;

    private FileOpenUI[] fileOpenUI = new FileOpenUI[2];
    private EditorUI[] editorUI = new EditorUI[2];
    private FileSaveUI[] fileSaveUI = new FileSaveUI[2];


    // condition value
    private Boolean[] condLoadFile = { false, false };

    private int[] position = { Position.LEFT, Position.RIGHT };

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("init");

        // FXML value -> position array
        btnFileOpen = new Button[]  { btnLeftFileOpen, btnRightFileOpen };
        btnFileEdit = new Button[]  { btnLeftFileEdit, btnRightFileEdit };
        btnFileSave = new Button[]  { btnLeftFileSave, btnRightFileSave };
        tabPanes = new TabPane[]    { tabLeft, tabRight };
        listViews = new ListView[]  { listLeft, listRight};
        listModels = new ObservableList[] { listLeft.getItems(), listRight.getItems() };
        textFields = new TextField[]{ fieldLeftFile, fieldRightFile };
        textAreas = new TextArea[]  { textAreaLeft,textAreaRight };

        button = new HashMap();



        // init UI
        for(int pos : position){
            fileOpenUI[pos] = new FileOpenUI(pos, Main.getPrimaryStage(), textFields[pos], textAreas[pos]);
            editorUI[pos] = new EditorUI(pos, tabPanes[pos], textFields[pos], textAreas[pos], btnFileEdit[pos]);
            fileSaveUI[pos] = new FileSaveUI(pos, textFields[pos], textAreas[pos]);
            button.put(btnFileOpen[pos].getId(), fileOpenUI[pos]);
            button.put(btnFileEdit[pos].getId(), editorUI[pos]);
            button.put(btnFileSave[pos].getId(), fileSaveUI[pos]);
        }

        //DoTest();
    }

    public void eventFileOpen(ActionEvent e) {

        // Get Button
        Control target = (Control)e.getSource();
        btnAction ui = button.get(target.getId());
        int pos = ui.getPosition();

        // Call event
        Boolean status = ui.onAction((source) -> {

            System.out.println("FileOpen callback");
            // Item data = Model.load((File)source,pos));

//            return data;
            return new Item();
        });


        editorUI[pos].ShowViewMode();
        condLoadFile[pos] = status;

        // Set button disable
        btnFileEdit[pos].setDisable(!status);
        btnFileSave[pos].setDisable(!status);
        btnCompare.setDisable(!(condLoadFile[0] && condLoadFile[1]));
    }

    public void eventFileEdit(ActionEvent e) {

        // Get Button
        Control target = (Control)e.getSource();
        btnAction ui = button.get(target.getId());
        int pos = ui.getPosition();

        // Call event
        Boolean status = ui.onAction((source) -> {

            System.out.println("FileEdit callback");
            // Item data = Model.edit((String)source, pos));

            return new Item();
        });
    }

    public void eventFileSave(ActionEvent e) {

        // Get Button
        Control target = (Control)e.getSource();
        btnAction ui = button.get(target.getId());

        // Call event
        Boolean status = ui.onAction((source) -> {
            System.out.println("Click:LeftFileSave");

            // Item data = Model.save((String)source, pos));

            return new Item();
        });
    }

    public void eventCompare(ActionEvent e){
        System.out.println("Click:Compare");


//        Compare data = model.compare();
        MergeCompare data = new Item();

        for(int pos : position){
            listModels[pos].setAll(data.getListViewItem(pos));
            editorUI[pos].ShowCompareMode();
        }

    }

    public void handleMouseClick(MouseEvent arg0) {
//        System.out.println("clicked on " + listView.getSelectionModel().getSelectedItem());



    }

    public void DoTest(){
        ObservableList<String> left = listLeft.getItems();
        ObservableList<String> right = listRight.getItems();

        left.setAll("1","2","3","4","1","2","3","4","1","2","3","4","1","2","3","4","1","2","3","4");
        right.setAll("1","2","3","4","1","2","3","4","1","2","3","4","1","2","3","4");


    }
}
