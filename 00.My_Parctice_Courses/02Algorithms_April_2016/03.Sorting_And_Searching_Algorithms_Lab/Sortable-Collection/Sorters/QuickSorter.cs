namespace Sortable_Collection.Sorters
{
    using System;
    using System.Collections.Generic;

    using Contracts;

    public class QuickSorter<T> : ISorter<T> where T : IComparable<T>
    {
        public void Sort(List<T> collection)
        {
            QuickSort(collection, 0, collection.Count - 1);
        }    
        
        private void QuickSort(List<T> collection, int start, int end)
        {
            if (start >= end)
            {
                return;
            }

            T pivot = collection[start];
            int storeIndex = start + 1;

            for (int i = storeIndex; i <= end; i++)
            {
                if (collection[i].CompareTo(pivot) <= 0)
                {
                    Swap(collection, i, storeIndex);
                    storeIndex++;
                }
            }
            storeIndex--;
            Swap(collection, start, storeIndex);

            QuickSort(collection, start, storeIndex - 1);
            QuickSort(collection, storeIndex + 1, end);
        }

        private void Swap(List<T> collection, int firstIndex, int secondIndex)
        {
            T temporaryElement = collection[firstIndex];
            collection[firstIndex] = collection[secondIndex];
            collection[secondIndex] = temporaryElement;
            
        }
    }
}
