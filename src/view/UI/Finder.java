package view.UI;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

/**
 * Created by yongjunkim on 2017. 5. 23..
 * TODO: 확장자필터 추가하는 메소드(현재 생성자에 있는 형식참고)
 */

public class Finder {
    private Stage root = null;
    private FileChooser finder;

    public Finder(){
        finder = new FileChooser();
        finder.setTitle("Open Resource File");
        finder.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("All Files", "*.*"),
            new FileChooser.ExtensionFilter("Text Files", "*.txt"),
            new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"),
            new FileChooser.ExtensionFilter("Audio Files", "*.wav", "*.mp3", "*.aac")
        );
    }

    public Finder(Stage root){
        this();
        this.root = root;
    }

    public File show(){
        return finder.showOpenDialog(root);
    }

    public void setRoot(Stage root){
        this.root = root;
    }
    public Stage getRoot() { return root; }
}
