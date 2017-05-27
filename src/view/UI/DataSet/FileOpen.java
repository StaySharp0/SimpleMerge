package view.UI.DataSet;

import javafx.collections.ObservableList;

/**
 * Created by yongjunkim on 2017. 5. 27..
 */
public interface FileOpen {
    public String getFileName();
    public ObservableList<String> getListViewModel();
    public String getTextData();
}
