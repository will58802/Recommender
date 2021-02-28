package sol;

import src.IAttributeDataset;
import src.IAttributeDatum;

import javax.sound.sampled.Line;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;

/*
 * Class for a specific representation of rows in a data table. This uses a list
 * of objects (one object per row).
 * The type T is the object that forms the "rows" of the data table
 */
public class ListObjsData<T extends IAttributeDatum> implements IAttributeDataset<T> {

    public LinkedList<String> attributes;
    public LinkedList<T> rows;

    public ListObjsData(LinkedList<String> attributes, LinkedList<T> rows) {
        this.attributes = attributes;
        this.rows = rows;
    }

    @Override
    public LinkedList<String> getAttributes() {
        return attributes;
    }

    @Override
    public boolean allSameValue(String ofAttribute) {
        T previous = null;
        for (T row : this.rows) {
            if (previous == null) {
                previous = row;
                continue;
            }
            if (!previous.getValueOf(ofAttribute).equals(row.getValueOf(ofAttribute))) {
                return false;
            }
//            System.out.println(row.getValueOf(ofAttribute));
        }
        return true;
    }

    @Override
    public int size() {
        return rows.size();
    }

    @Override
    public LinkedList<IAttributeDataset<T>> partition(String onAttribute) {

        ArrayList<LinkedList<T>> subsets = new ArrayList<LinkedList<T>>();

        for (T row : this.rows) {

            boolean subsetExists = false;
            int index = 0;

            for (int i = 0; i < subsets.size(); i++) {

                if (row.getValueOf(onAttribute).equals(subsets.get(i).getFirst().getValueOf(onAttribute))) {
                    subsetExists = true;
                    index = i;
                    break;
                }
            }

            if (subsetExists) {
                LinkedList<T> temp = subsets.get(index);
                temp.add(row);
                subsets.set(index, temp);
            } else {
                LinkedList<T> newSubset = new LinkedList<T>();
                newSubset.add(row);
                subsets.add(newSubset);
            }

        }

        LinkedList<IAttributeDataset<T>> results = new LinkedList<IAttributeDataset<T>>();
        for (LinkedList<T> subset : subsets) {
            ListObjsData<T> formatted = new ListObjsData<T>(this.attributes, subset);
            results.add(formatted);
        }

        return results;
    }

    @Override
    public Object getSharedValue(String ofAttribute) {
        return this.rows.getFirst().getValueOf(ofAttribute);
    }

    @Override
    public Object mostCommonValue(String ofAttribute) {

        LinkedList<IAttributeDataset<T>> results = this.partition(ofAttribute);

//        System.out.println(results.getFirst().getSharedValue(ofAttribute));

        int mostCommon = 0;
        Object value = null;

        for (IAttributeDataset<T> result : results) {
            if (result.size() > mostCommon) {
                mostCommon = result.size();
                value = result.getSharedValue(ofAttribute);
            }
        }

        return value;

    }
}
