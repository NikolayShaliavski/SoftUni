namespace Sortable_Collection.Sorters
{
    using System;
    using System.Collections.Generic;

    using Contracts;

    public class MergeSorter<T> : ISorter<T> where T : IComparable<T>
    {
        public void Sort(List<T> collection)
        {
            MergeSort(collection, 0, collection.Count - 1);
        }

        private void MergeSort(List<T> collection, int start, int end)
        {
            //bottom of recursion
            if (start >= end)
            {
                return;
            }
            int middle = start + (end - start) / 2;

            MergeSort(collection, start, middle);
            MergeSort(collection, middle + 1, end);

            Merge(collection, start, middle, end);
        }

        private void Merge(List<T> collection, int start, int middle, int end)
        {
            //left is start index for left part of the collection
            int left = start;
            //end index for left collection is middle
            //int leftEndIndex = middle;
            //right is start index for right part of the colleciton == middle + 1
            int right = middle + 1;
            //end index for right collection is passed end parameter

            //array with length == lengths of two collections (left && right). They will be merged in one sorted collectio
            T[] helpCollection = new T[end - start + 1];
            int mergeIndex = 0;
            while (left <= middle && right <= end)
            {
                if (collection[left].CompareTo(collection[right]) <= 0)
                {
                    helpCollection[mergeIndex] = collection[left];
                    left++;
                }
                else
                {
                    helpCollection[mergeIndex] = collection[right];
                    right++;
                }
                mergeIndex++;
            }
            while (left <= middle)
            {
                helpCollection[mergeIndex] = collection[left];
                left++;
                mergeIndex++;
            }
            while (right <= end)
            {
                helpCollection[mergeIndex] = collection[right];
                right++;
                mergeIndex++;
            }

            CopyMergedArrays(helpCollection, collection, start, end);
        }

        private void CopyMergedArrays(T[] helpCollection, List<T> collection, int start, int end)
        {
            int indexCounter = start;
            for (int i = 0; i < helpCollection.Length; i++)
            {
                collection[indexCounter] = helpCollection[i];
                indexCounter++;
            }
        }
    }
}
