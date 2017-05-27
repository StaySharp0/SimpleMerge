package view.UI.DataSet;

import javafx.collections.ObservableList;

/**
 * Created by yongjunkim on 2017. 5. 27..
 */
public class Item implements FileOpen, FileSave{
    private String fileName = "tmp";
    private String textData = "tmp";
    private ObservableList<String> listViewModel = null;

    @Override
    public String getFileName() {
        return null;
    }

    @Override
    public ObservableList<String> getListViewModel() {
        return null;
    }

    @Override
    public String getTextData() {
        return null;
    }
}
