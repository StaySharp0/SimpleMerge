package view;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import model.Model;
import model.ModelInterface;
import view.UI.*;
import dataSet.MergeCompare;

import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    ModelInterface model;

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

    private btnAction[] fileOpenUI = new FileOpenUI[2];
    private ScreenMode[] editorUI = new EditorUI[2];
    private btnAction[] fileSaveUI = new FileSaveUI[2];


    // condition value
    private Boolean[] condLoadFile = { false, false };
    private Boolean condCompare = false;
    private int condActiveOrder;
    private int selectedIndex;

    private int[] position = { Position.LEFT, Position.RIGHT };

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("init");

        model = new Model();

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
            button.put(btnFileEdit[pos].getId(), (btnAction) editorUI[pos]);
            button.put(btnFileSave[pos].getId(), fileSaveUI[pos]);
        }


    }

    public void SyncScrollBar() {
        ScrollBar left = (ScrollBar) listLeft.lookup(".scroll-bar:vertical");
        ScrollBar right = (ScrollBar) listRight.lookup(".scroll-bar:vertical");

        left.valueProperty().bindBidirectional(right.valueProperty());
    }

    public void eventFileOpen(ActionEvent e) {

        // Get Button
        Control target = (Control)e.getSource();
        btnAction ui = button.get(target.getId());
        int pos = ui.getPosition();

        // Call event
        Boolean status = ui.onAction((source) -> {
            System.out.println("FileOpen callback");

            return model.load((File) source, pos);
        });

        if(condLoadFile[pos]) return; //현재 불러온 파일이 있지만 다시 불러오기를 실패한 경우

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

            return model.edit((String) source, pos);
        });

        condCompare = false;
        btnMtoRight.setDisable(!condCompare);
        btnMtoLeft.setDisable(!condCompare);
    }

    public void eventFileSave(ActionEvent e) {

        // Get Button
        Control target = (Control)e.getSource();
        btnAction ui = button.get(target.getId());
        int pos = ui.getPosition();

        // Call event
        Boolean status = ui.onAction((source) -> {
            System.out.println("Click:LeftFileSave");

            return model.save((String) source, pos);
        });
    }

    public void eventCompare(ActionEvent e){
        System.out.println("Click:Compare");

        // model Data update
        for(int pos : position) model.edit(textAreas[pos].getText(),pos);
        MergeCompare data = model.compare();

        updateListView(data);
    }

    public void evnetMergeToLeft(ActionEvent e){
        System.out.println("Click:MergeToLeft");

        if(!condCompare) return;

        MergeCompare data = model.merge(selectedIndex,Position.LEFT);

        updateListView(data);
    }

    public void evnetMergeToRight(ActionEvent e){
        System.out.println("Click:MergeToRight");

        if(!condCompare) return;

        MergeCompare data = model.merge(selectedIndex,Position.RIGHT);

        updateListView(data);
    }

    public void SyncSelectItem(Event e) {

        ListView target = (ListView)e.getSource();
        selectedIndex = target.getSelectionModel().getSelectedIndex();

        for(int pos : position) listViews[pos].getSelectionModel().select(selectedIndex);

        // Active Merge Button
        boolean flag = !(selectedIndex % 2 == condActiveOrder); // 틀린 Index가 맞는 경우 true(setDisable 때문에 역을 함)
        btnMtoLeft.setDisable(flag);
        btnMtoRight.setDisable(flag);
    }

    private void updateListView(MergeCompare data) {

        if( data == null ) {
            System.out.println("err: Compare");

            condCompare = false;
            return;
        }

        condCompare = true;
        condActiveOrder = data.getListActiveOrder();

        for (int pos : position) {
            listModels[pos].setAll(data.getListViewItem(pos));
            editorUI[pos].ShowCompareMode();

            listViews[pos].getStyleClass().removeAll("odd","even");
            if (condActiveOrder == 1) {           // 홀수번째가 틀린경우
                listViews[pos].getStyleClass().add("odd");
            } else if (condActiveOrder == 0) {    //짝수번째가 틀린경우
                listViews[pos].getStyleClass().add("even");
            }
            System.out.println(listViews[pos].getStyleClass().getClass());
        }
    }
}
