package problem_07.interfaces;

import problem_07.collections.CustomListImpl;

public interface Sort<T extends Comparable<T>> {

    CustomListImpl<T> sort(CustomListImpl<T> collection);
}
