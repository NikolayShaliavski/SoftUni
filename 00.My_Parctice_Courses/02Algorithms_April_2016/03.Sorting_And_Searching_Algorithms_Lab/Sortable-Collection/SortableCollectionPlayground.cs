namespace Sortable_Collection
{
    using System;

    using Sorters;
    using System.Collections.Generic;

    public class SortableCollectionPlayground
    {
        private static Random random = new Random();

        public static void Main(string[] args)
        {
            //const int NumberOfElementsToSort = 10;
            //const int MaxValue = 100;

            //var array = new int[NumberOfElementsToSort];

            //for (int i = 0; i < NumberOfElementsToSort; i++)
            //{
            //    array[i] = random.Next(MaxValue);
            //}

            //var collectionToSort = new SortableCollection<int>(array);
            //collectionToSort.Sort(new BucketSorter { Max = MaxValue });

            //Console.WriteLine(collectionToSort);

            //var collection = new SortableCollection<int>(3, 2, 6, 9, -1, 1, 3, 1, 0, 0);
            //Console.WriteLine(collection);
            //
            //collection.Sort(new MergeSorter<int>());
            //Console.WriteLine(collection);

            var arr = new List<int>(new int[] {4, 4, 3, 2, 2});

            var sorter = new QuickSorter<int>();
            sorter.Sort(arr);

            for (int i = 0; i < arr.Count; i++)
            {
                Console.WriteLine(arr[i]);
            }
        }
    }
}
