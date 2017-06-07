package dataSet;

import java.util.ArrayList;

/**
 * Created by yongjunkim on 2017. 5. 27..
 */
public class Item implements FileOpen, FileEditSave, MergeCompare{
    private String fileName;
    private String textData;
    private String[][] listViewItem;
    private boolean listActiveOrder;



    @Override
    public String getFileName() {
        return fileName;
    }

    @Override
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }


    @Override
    public String getTextData() {
        return textData;
    }

    @Override
    public void setTextData(String textData) {
        this.textData = textData;
    }

    @Override
    public String[] getListViewItem(int position) {
        return listViewItem[position];
    }

    @Override
    public void setListViewItem(String[][] data) {
        this.listViewItem = data;
    }

    @Override
    public int getListActiveOrder() {
        // true(홀수) -> return 1
        // false(짝수) -> return 0

        return listActiveOrder ? 1:0;
    }

    @Override
    public void setListActiveOrder(boolean order) {
        this.listActiveOrder = order;
    }

}
