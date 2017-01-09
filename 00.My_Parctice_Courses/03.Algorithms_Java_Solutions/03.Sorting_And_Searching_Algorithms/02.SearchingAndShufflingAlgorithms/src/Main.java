import shuffling.FisherYatesShuffler;

public class Main {

    public static void main(String[] args) {
        int[] testArr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};

        //Binary search test
//        BinarySearcher binarySearcher = new BinarySearcher();
//        int index = binarySearcher.searchIndex(testArr, 5);
//        boolean contains = binarySearcher.contains(testArr, 5);
//
//        System.out.println(contains);
//        System.out.println(index);

        //Shuffle test
        FisherYatesShuffler shuffler = new FisherYatesShuffler();
        shuffler.shuffle(testArr);

        for (int element : testArr) {
            System.out.print(element + " ");
        }
        System.out.println();
    }
}
