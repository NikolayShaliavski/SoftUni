using System;

public class BinaryTree<T>
{
    public T Value { get; set; }

    public BinaryTree<T> LeftChid { get; set; }

    public BinaryTree<T> RightChild { get; set; }

    public BinaryTree(T value, BinaryTree<T> leftChild = null, BinaryTree<T> rightChild = null)
    {
        this.Value = value;
        this.LeftChid = leftChild;
        this.RightChild = rightChild;
    }

    public void PrintIndentedPreOrder(int indent = 0)
    {
        //PreOrder -> (root -> leftChild -> rightChild)
        Console.Write(new string(' ', 2 * indent));
        Console.WriteLine(this.Value);
        if (this.LeftChid != null)
        {
            this.LeftChid.PrintIndentedPreOrder(indent + 1);
        }
        if (this.RightChild != null)
        {
            this.RightChild.PrintIndentedPreOrder(indent + 1);
        }
    }

    public void EachInOrder(Action<T> action)
    {
        //InOrder -> (leftChild -> root -> rightChild)
        if (this.LeftChid != null)
        {
            this.LeftChid.EachInOrder(action);
        }
        action(this.Value);
        if (this.RightChild != null)
        {
            this.RightChild.EachInOrder(action);
        }
    }

    public void EachPostOrder(Action<T> action)
    {
        //PostOrder -> (leftChild -> rightChild -> root)
        if (this.LeftChid != null)
        {
            this.LeftChid.EachPostOrder(action);
        }
        if (this.RightChild != null)
        {
            this.RightChild.EachPostOrder(action);
        }
        action(this.Value);
    }
}
