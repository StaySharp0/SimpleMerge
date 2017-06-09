package view.UI;

import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import dataSet.FileEditSave;

/**
 * Created by yongjunkim on 2017. 5. 27..
 */
public class FileSaveUI implements btnAction {
    private int position;
    private TextField title;
    private TextArea textArea;

    public FileSaveUI(int pos, TextField title, TextArea textArea){
        this.position = pos;
        this.title = title;
        this.textArea = textArea;
    }

    @Override
    public int getPosition() {
        return position;
    }

    @Override
    public Boolean onAction(EventCallback cb) {

        FileEditSave data = cb.callbackMethod(textArea.getText());
        if(data == null) return false;

        title.setText(data.getFileName());

        return true;
    }
}
