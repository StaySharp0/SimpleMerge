package view.UI;

import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.File;

/**
 * Created by yongjunkim on 2017. 5. 27..
 */
public class FileOpenUI implements btnAction{
    private Finder finder;
    private TextField title;
    private ListView<String> listView;
    private TextArea textArea;

    public FileOpenUI(Stage root, TextField title, ListView listView, TextArea textArea){

        finder = new Finder(root);

        this.title = title;
        this.listView = listView;
        this.textArea = textArea;
    }

    @Override
    public void onAction(EventCallback cb) {

        File source = finder.show();
        String fileName = source.getName();

        cb.callbackMethod(source);

//        결과값 = cb.callbackMethod(source);

        title.setText(fileName);
//        listView.setItems(결과값);
//        textArea.setText(결과값);

    }
}
