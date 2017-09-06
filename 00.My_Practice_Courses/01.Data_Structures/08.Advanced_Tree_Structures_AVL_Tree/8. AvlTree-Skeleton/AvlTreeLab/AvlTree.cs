using System;
using System.Collections.Generic;

namespace AvlTreeLab
{   
    public class AvlTree<T> where T : IComparable<T>
    {
        private Node<T> root;

        public int Count { get; private set; }
        
        public void Add(T item)
        {
            var inserted = true;
            if (this.root == null)
            {
                this.root = new Node<T>(item);
            }
            else 
            {
                inserted = this.InsertInternal(this.root, item);
            }
            if (inserted)
            {
                this.Count++;
            }
        }

        private bool InsertInternal(Node<T> node, T item)
        {
            var currentNode = node;
            var newNode = new Node<T>(item);
            var inserted = true;
            var sholdRetrace = false;

            while (true)
            {
                //item's value is less than currentNode's value -> go left
                if (currentNode.Value.CompareTo(item) > 0)
                {
                    if (currentNode.Left == null)
                    {
                        currentNode.Left = newNode;
                        currentNode.BalanceFactor++;
                        sholdRetrace = currentNode.BalanceFactor != 0;
                        break;
                    }
                    currentNode = currentNode.Left;
                }
                //item's value is greater than currentNode's value -> go right
                else if (currentNode.Value.CompareTo(item) < 0)
                {
                    if (currentNode.Right == null)
                    {
                        currentNode.Right = newNode;
                        currentNode.BalanceFactor--;
                        sholdRetrace = currentNode.BalanceFactor != 0;
                        break;
                    }
                    currentNode = currentNode.Right;
                }
                else
                {
                    inserted = false;
                    break;
                }
            }
            
            if (sholdRetrace)
            {
                this.RetraceInsert(currentNode);
            }
            return inserted;
        }

        private void RetraceInsert(Node<T> node)
        {
            var parent = node.Parent;
            while (parent != null)
            {            
                if (node.IsLeftChild)
                {
                    //Parent's left branch has grown and BF will become 2 -> rebalance
                    if (parent.BalanceFactor == 1)
                    {
                        parent.BalanceFactor++;//increase parent's BF because we come from left
                        if (node.BalanceFactor == -1)
                        {
                            //Make branch straight so we can rotate (left-right case)
                            this.RotateLeft(node);
                        }
                        //Rotate parent right to balance (left-left case)
                        this.RotateRight(parent);
                        break;
                    }
                    else if (parent.BalanceFactor == -1)//parent will be balanced - no need to go up
                    {
                        parent.BalanceFactor = 0;//balance parent -> parent.BalanceFactor--
                        break;
                    }
                    else
                    {
                        parent.BalanceFactor = 1;
                        //parent BF is 1 -> parent already has one left child. 
                        //When we insert again new Node here, we should already rebalance
                    }
                }
                else//the same operations on the right banch
                {
                    if (parent.BalanceFactor == -1)
                    {
                        parent.BalanceFactor--;
                        if (node.BalanceFactor == 1)
                        {
                            this.RotateRight(node);
                        }
                        this.RotateLeft(parent);
                        break;
                    }
                    else if (parent.BalanceFactor == 1)
                    {
                        parent.BalanceFactor = 0;
                        break;
                    }
                    else
                    {
                        parent.BalanceFactor = -1;
                    }
                }
                node = parent;
                parent = node.Parent;
            }
        }

        private void RotateRight(Node<T> node)
        {
            var parent = node.Parent;
            var child = node.Left;
            if (parent != null)
            {
                if (node.IsRightChild)
                {
                    parent.Right = child;
                }
                else
                {
                    parent.Left = child;
                }
            }
            else
            {
                this.root = child;
                this.root.Parent = null;
            }
            node.Left = child.Right;
            child.Right = node;

            node.BalanceFactor -= 1 + Math.Max(child.BalanceFactor, 0);
            child.BalanceFactor -= 1 - Math.Min(node.BalanceFactor, 0);
        }

        private void RotateLeft(Node<T> node)
        {
            var parent = node.Parent;
            var child = node.Right;
            if (parent != null)
            {
                if (node.IsLeftChild)
                {
                    parent.Left = child;
                }
                else
                {
                    parent.Right = child;
                }
            }
            else
            {
                this.root = child;
                this.root.Parent = null;
            }
            node.Right = child.Left;
            child.Left = node;

            node.BalanceFactor += 1 - Math.Min(child.BalanceFactor, 0);
            child.BalanceFactor += 1 + Math.Max(node.BalanceFactor, 0);
        }

        public bool Contains(T item)
        {
            var node = this.root;
            while (node != null)
            {
                if (item.CompareTo(node.Value) < 0)//go left
                {
                    node = node.Left;
                }
                else if (item.CompareTo(node.Value) > 0)//go right
                {
                    node = node.Right;
                }
                else
                {
                    return true;
                }
            }
            return false;
        }

        public void ForeachDfs(Action<int, T> action)
        {
            if (this.Count == 0)
            {
                return;
            }
            this.InOrderDfs(this.root, 1, action);
        }

        public void Print()
        {
            if (this.root != null)
            {
                this.root.PrintInOrder(0, this.root);
            }
        }

        public List<T> Range(T firstValue, T secondValue)
        {
            T from = (firstValue.CompareTo(secondValue) < 0) ? firstValue : secondValue;
            T to = (secondValue.CompareTo(firstValue) > 0) ? secondValue : firstValue;

            List<T> range = new List<T>();
            this.InOrderDfsRange(this.root, range, from, to);
            return range;
        }

        private void InOrderDfsRange(Node<T> node, List<T> range, T from, T to)
        {
            if (node.Left != null)
            {
                if (node.Left.Value.CompareTo(from) >= 0)
                {
                    this.InOrderDfsRange(node.Left, range, from, to);
                }              
            }
            if (node.Value.CompareTo(from) >= 0 && node.Value.CompareTo(to) <= 0)
            {
                range.Add(node.Value);
            }           
            if (node.Right != null)
            {
                if (node.Right.Value.CompareTo(to) <= 0)
                {
                    this.InOrderDfsRange(node.Right, range, from, to);
                }
            }
        }

        private void InOrderDfs(Node<T> node, int depth, Action<int, T> action)
        {
            if (node.Left != null)
            {
                this.InOrderDfs(node.Left, depth + 1, action);
            }
            action(depth, node.Value);
            if (node.Right != null)
            {
                this.InOrderDfs(node.Right, depth + 1, action);
            }
        }
    }
}
