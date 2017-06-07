package view.UI;

import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import dataSet.FileOpen;

import java.io.File;

/**
 * Created by yongjunkim on 2017. 5. 27..
 */
public class FileOpenUI implements btnAction{
    private int position;
    private Finder finder;
    private TextField title;
    private TextArea textArea;

    public FileOpenUI(int pos, Stage root, TextField title, TextArea textArea){

        this.position = pos;
        this.finder = new Finder(root);
        this.title = title;
        this.textArea = textArea;
    }

    @Override
    public int getPosition() {
        return this.position;
    }

    @Override
    public Boolean onAction(EventCallback cb) {

        File source = finder.show();

        FileOpen data = cb.callbackMethod(source);

        if(data == null) return false;

        title.setText(data.getFileName());
        textArea.setText(data.getTextData());

        return true;

    }
}
