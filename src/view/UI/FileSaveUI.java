package view.UI;

import javafx.scene.control.TextField;

/**
 * Created by yongjunkim on 2017. 5. 27..
 */
public class FileSaveUI implements btnAction {
    TextField title;

    public FileSaveUI(TextField title){
        this.title = title;
    }

    @Override
    public void onAction(EventCallback cb) {
        String fileName = title.getText();
        String[] splitTmp = fileName.split("/* ");

        if(splitTmp.length == 0) {
            fileName = splitTmp[0];
            title.setText(fileName);
        }

        cb.callbackMethod();
    }
}
