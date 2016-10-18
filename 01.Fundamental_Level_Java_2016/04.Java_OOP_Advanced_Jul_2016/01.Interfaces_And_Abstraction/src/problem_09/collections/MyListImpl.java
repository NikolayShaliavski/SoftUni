package problem_09.collections;

import problem_09.interfaces.MyList;

import java.util.LinkedList;
import java.util.List;

public class MyListImpl extends AddRemoveCollectionImpl implements MyList{

    private List<String> collection;

    public MyListImpl() {
        this.collection = new LinkedList<>();
    }

    @Override
    public int add(String element) {
        this.collection.add(0, element);
        return 0;
    }

    @Override
    public String remove() {
        return this.collection.remove(0);
    }
}
