package view.UI;

import javafx.scene.control.TextField;
import view.UI.DataSet.FileSave;

/**
 * Created by yongjunkim on 2017. 5. 27..
 */
public class FileSaveUI implements btnAction {
    TextField title;

    public FileSaveUI(TextField title){
        this.title = title;
    }

    @Override
    public int getPosition() {
        return 0;
    }

    @Override
    public Boolean onAction(EventCallback cb) {
        String fileName = title.getText();
        String[] splitTmp = fileName.split("/* ");

        if(splitTmp.length == 0) {
            fileName = splitTmp[0];
            title.setText(fileName);
        }

        FileSave data = cb.callbackMethod(null);

        return true;
    }
}
