package dataSet;


/**
 * Created by yongjunkim on 2017. 6. 3..
 */
public interface MergeCompare {
    public String[] getListViewItem(int position);
    public String getTextItem(int position);
    public void setTextItem(String[] data);
    public void setListViewItem(String[][] data);
    public int getListActiveOrder();
    public void setListActiveOrder(boolean order);
}
