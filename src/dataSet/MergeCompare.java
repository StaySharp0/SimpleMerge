package dataSet;


/**
 * Created by yongjunkim on 2017. 6. 3..
 */
public interface MergeCompare {
    public String[] getListViewItem(int positon);
    public void setListViewItem(String[][] data);
    public int getListActiveOrder();
    public void setListActiveOrder(boolean order);
}
