package problem_09.collections;

import problem_09.interfaces.AddRemoveCollection;

import java.util.ArrayList;
import java.util.List;

public class AddRemoveCollectionImpl extends AddCollectionImpl implements AddRemoveCollection {

    private List<String> collection;

    public AddRemoveCollectionImpl() {
        this.collection = new ArrayList<>();
    }

    @Override
    public int add(String element) {
        this.collection.add(0, element);
        return 0;
    }

    @Override
    public String remove() {
        return this.collection.remove(this.collection.size() - 1);
    }
}
