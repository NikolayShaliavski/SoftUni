using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace _04.OrderedSet
{
    class TreeNode<T> : IComparable<T> where T : IComparable
    {
        public T Value { get; set; }
        public TreeNode<T> LeftChild { get; set; }
        public TreeNode<T> RightChild { get; set; }
        public TreeNode<T> Parent { get; set; }

        public TreeNode(T value, TreeNode<T> leftChild = null, TreeNode<T> rightChild = null)
        {
            this.Value = value;
            this.LeftChild = leftChild;
            this.RightChild = rightChild;
        }

        
        public void PrintIndentedInOrder(int indent = 0)
        {
            if (this.LeftChild != null)
            {
                this.LeftChild.PrintIndentedInOrder(indent + 2);
            }
            Console.Write(new string(' ', indent * 2));
            Console.WriteLine(this.Value);
            if (this.RightChild != null)
            {
                this.RightChild.PrintIndentedInOrder(indent + 2);
            }
        }

        public T EachInOrder()
        {
            if (this.LeftChild != null)
            {
                this.LeftChild.EachInOrder();
            }
            if (this.RightChild != null)
            {
                this.RightChild.EachInOrder();
            }
            return this.Value;
        }

        public int CompareTo(T value)
        {
            return this.Value.CompareTo(value);
        }
    }
}
