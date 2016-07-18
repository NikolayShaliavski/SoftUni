package problem_09.collections;

import problem_09.interfaces.AddCollection;

import java.util.LinkedList;
import java.util.List;

public class AddCollectionImpl implements AddCollection {

    private List<String> collection;

    public AddCollectionImpl() {
        this.collection = new LinkedList<>();
    }

    @Override
    public int add(String element) {
        this.collection.add(element);
        return this.collection.size() - 1;
    }
}
