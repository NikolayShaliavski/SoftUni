using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace _04.OrderedSet
{
    class OrderedSet<T> : IEnumerable<T> where T : IComparable
    {
        public TreeNode<T> Root { get; set; }
        public int Count { get; set; }
        public List<T> AllNodes { get; set; }

        public OrderedSet()
        {
            this.AllNodes = new List<T>();
        }

        public void Add(T element)
        {
            if (this.Root == null)
            {
                this.Root = new TreeNode<T>(element);
                this.Count++;
                return;
            }
            var node = this.Root;
            this.InsertNode(node, element);
        }

        public bool Contains(T element)
        {
            if (this.Root == null)
            {
                return false;
            }
            return this.FindElement(this.Root, element, 1);
        }

        public bool Remove(T element)
        {
            TreeNode<T> nodeToRemove = this.FindNodeToRemove(this.Root, element);
            if (nodeToRemove == null)
            {
                return false;
            }

            var parent = nodeToRemove.Parent;
            var leftChild = nodeToRemove.LeftChild;
            var rightChild = nodeToRemove.RightChild;
            if (parent == null)
            {
                if (rightChild != null)
                {
                    this.Root = rightChild;
                    rightChild.Parent = null;
                    if (leftChild != null)
                    {
                        var leftLeaf = rightChild;
                        while (leftLeaf.LeftChild != null)
                        {
                            leftLeaf = leftLeaf.LeftChild;
                        }
                        if (leftLeaf != null)
                        {
                            leftChild.Parent = leftLeaf;
                            leftLeaf.LeftChild = leftChild;
                        }
                    }
                }
                else if (leftChild != null)
                {
                    this.Root = leftChild;
                    leftChild.Parent = null;
                }
                else
                {
                    this.Root = null;
                }
                this.Count--;
                return true;
            }
            if (parent != null && parent.LeftChild != null && nodeToRemove.Equals(parent.LeftChild))
            {
                if (rightChild != null)
                {
                    parent.LeftChild = rightChild;
                    rightChild.Parent = parent;
                    if (leftChild != null)
                    {
                        var leftLeaf = rightChild;
                        while (leftLeaf.LeftChild != null)
                        {
                            leftLeaf = leftLeaf.LeftChild;
                        }
                        if (leftLeaf != null)
                        {
                            leftChild.Parent = leftLeaf;
                            leftLeaf.LeftChild = leftChild;
                        }
                    }
                }
                else if (leftChild != null)
                {
                    parent.LeftChild = leftChild;
                    leftChild.Parent = parent;
                }
                else
                {
                    parent.LeftChild = null;
                }
            }
            else if (parent != null && parent.RightChild != null && nodeToRemove.Equals(parent.RightChild))
            {
                if (rightChild != null)
                {
                    parent.RightChild = rightChild;
                    rightChild.Parent = parent;
                    if (leftChild != null)
                    {
                        var leftLeaf = rightChild;
                        while (leftLeaf.LeftChild != null)
                        {
                            leftLeaf = leftLeaf.LeftChild;
                        }
                        if (leftLeaf != null)
                        {
                            leftChild.Parent = leftLeaf;
                            leftLeaf.LeftChild = leftChild;
                        }
                    }
                }
                else if (leftChild != null)
                {
                    parent.RightChild = leftChild;
                    leftChild.Parent = parent;
                }
                else
                {
                    parent.RightChild = null;
                }
            }
            this.Count--;
            return true;
        }

        private TreeNode<T> FindNodeToRemove(TreeNode<T> node, T element)
        {
            if (element.CompareTo(node.Value) == 0)
            {
                return node;
            }
            else
            {
                if (element.CompareTo(node.Value) < 0 && node.LeftChild != null)
                {
                    return this.FindNodeToRemove(node.LeftChild, element);
                }
                else if (element.CompareTo(node.Value) > 0 && node.RightChild != null)
                {
                    return this.FindNodeToRemove(node.RightChild, element);
                }
            }
            return null;
        }

        private bool FindElement(TreeNode<T> node, T element, int step)
        {
            Console.WriteLine("Step " + step);
            if (node.Value.Equals(element))
            {
                return true;
            }
            if (element.CompareTo(node.Value) < 0)
            {
                return this.FindElement(node.LeftChild, element, step + 1);
            }
            else
            {
                return this.FindElement(node.RightChild, element, step + 1);
            }
        }

        private void InsertNode(TreeNode<T> node, T element)
        {
            if (node.Value.Equals(element))
            {
                return;
            }
            if (element.CompareTo(node.Value) < 0)
            {
                if (node.LeftChild == null)
                {
                    var newNode = new TreeNode<T>(element);
                    node.LeftChild = newNode;
                    newNode.Parent = node;
                    this.Count++;
                    return;
                }
                else
                {
                    this.InsertNode(node.LeftChild, element);
                }
            }
            else if (element.CompareTo(node.Value) > 0)
            {
                if (node.RightChild == null)
                {
                    var newNode = new TreeNode<T>(element);
                    node.RightChild = newNode;
                    newNode.Parent = node;
                    this.Count++;
                    return;
                }
                else
                {
                    this.InsertNode(node.RightChild, element);
                }
            }
        }

        public void Print()
        {
            if (this.Root == null)
            {
                return;
            }
            this.Root.PrintIndentedInOrder(0);
        }

        public IEnumerator<T> GetEnumerator()
        {
            this.TraverseTree(this.Root);
            foreach (var node in this.AllNodes)
            {
                yield return node;
            }
        }

        private void TraverseTree(TreeNode<T> node)
        {
            if (node == null)
            {
                return;
            }
            if (node.LeftChild != null)
            {
                this.TraverseTree(node.LeftChild);
            }
            AllNodes.Add(node.Value);
            if (node.RightChild != null)
            {
                this.TraverseTree(node.RightChild);
            }
        }

        IEnumerator IEnumerable.GetEnumerator()
        {
            return this.GetEnumerator();
        }
    }
}
