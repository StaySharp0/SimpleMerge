package view.UI.DataSet;

import java.util.ArrayList;

/**
 * Created by yongjunkim on 2017. 5. 27..
 */
public class Item implements FileOpen, FileEditSave, MergeCompare{
    private String fileName = "tmp";
    private String textData = "tmp";
    private ArrayList<String>[] listViewItem;

    @Override
    public String getFileName() {
        return null;
    }


    @Override
    public String getTextData() {
        return null;
    }

    @Override
    public String[] getListViewItem(int positon) {
        return new String[positon];
    }
}
