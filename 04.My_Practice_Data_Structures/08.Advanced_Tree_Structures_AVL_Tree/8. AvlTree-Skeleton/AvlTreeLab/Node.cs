using System;

namespace AvlTreeLab
{
    public class Node<T> where T : IComparable<T>
    {
        private Node<T> leftChild;
        private Node<T> rightChild;

        public Node(T value)
        {
            this.Value = value;
        }

        public T Value { get; set; }

        public Node<T> Parent { get; set; }

        public int BalanceFactor { get; set; }

        public Node<T> Left
        {
            get { return this.leftChild; }
            set
            {
                if (value != null)
                {
                    value.Parent = this;
                }
                this.leftChild = value;
            }
        }

        public Node<T> Right
        {
            get { return this.rightChild; }
            set
            {
                if (value != null)
                {
                    value.Parent = this;
                }
                this.rightChild = value;
            }
        }

        public bool IsLeftChild
        {
            get
            {
                return this.Parent != null &&
                        this.Parent.Left != null &&
                        this.Parent.Left.Value.Equals(this.Value);
            }
        }
        public bool IsRightChild
        {
            get
            {
                return this.Parent != null &&
                        this.Parent.Right != null &&
                        this.Parent.Right.Value.Equals(this.Value);
            }
        }
        public int ChildrenCount
        {
            get
            {
                if (this.Left != null && this.Right != null)
                {
                    return 2;
                }
                if (this.Left != null || this.Right != null)
                {
                    return 1;
                }
                return 0;
            }
        }

        public void PrintInOrder(int indent, Node<T> node)
        {
            if (node.rightChild != null)
            {
                this.PrintInOrder(indent + 1, node.rightChild);
            }
            Console.WriteLine(new string(' ', indent * 2) + node.Value);
            if (node.leftChild != null)
            {
                this.PrintInOrder(indent + 1, node.leftChild);
            }
            
            
        }

        public override string ToString()
        {
            return this.Value.ToString();
        }
    }
}

