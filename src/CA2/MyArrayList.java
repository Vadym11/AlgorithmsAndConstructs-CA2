package CA2;

import java.util.ArrayList;

public class MyArrayList<ElementType> extends ArrayList<ElementType> {

    public void insertionSort() {
        int pos;
        ElementType keyElement;

        for (int i = 1; i < size(); i++) {
            keyElement = get(i);
            pos = i;
            while (pos > 0 && ((Comparable) get(pos - 1)).compareTo((Comparable) keyElement) > 0) {
                ElementType elemAtPosMinusOne = get(pos - 1);
                set(pos, elemAtPosMinusOne);
                pos = pos - 1;
            }
            set(pos, keyElement);
        }
    }

    public void bubbleSort() {
        int i, j;

        Comparable elemAtJ, elemAtJPlus;

        for (i = 0; i < size(); i++) {
            for (j = 0; j < size() - 1 - i; j++) {
                elemAtJ = (Comparable) get(j);
                elemAtJPlus = (Comparable) get(j + 1);
                if (elemAtJ.compareTo(elemAtJPlus) > 0) {
                    swap(j, j + 1);
                }
            }
        }
    }

    public void swap (int pos1, int pos2) {

        ElementType objPos1 = get(pos1);
        ElementType objPos2 = get(pos2);
        remove(pos1);
        add(pos1, objPos2);
        remove(pos2);
        add(pos2, objPos1);
    }
}
