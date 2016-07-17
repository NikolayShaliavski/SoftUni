package problem_07.sorter;

import problem_07.collections.CustomListImpl;
import problem_07.interfaces.Sort;

public class Sorter<T extends Comparable<T>> implements Sort<T> {

    @Override
    public CustomListImpl<T> sort(CustomListImpl<T> collection) {
        for (int i = 0; i < collection.size(); i++) {
            for (int j = i + 1; j < collection.size(); j++) {
                if (collection.get(i).compareTo(collection.get(j)) > 0) {
                    T temp = collection.get(i);
                    collection.set(i, collection.get(j));
                    collection.set(j, temp);
                }
            }
        }
        return collection;
    }
}
