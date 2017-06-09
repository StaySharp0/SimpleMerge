package dataSet;


/**
 * Created by yongjunkim on 2017. 6. 3..
 */
public interface MergeCompare {
    public String getFileName(int position);
    public String getTextItem(int position);
    public String[] getListViewItem(int position);
    public void setFileName(String[] data);
    public void setTextItem(String[] data);
    public void setListViewItem(String[][] data);
    public int getListActiveOrder();
    public void setListActiveOrder(boolean order);
}
