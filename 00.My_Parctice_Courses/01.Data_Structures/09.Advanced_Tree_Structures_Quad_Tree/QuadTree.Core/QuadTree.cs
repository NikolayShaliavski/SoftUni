namespace QuadTree.Core
{
    using System;
    using System.Collections.Generic;
    using System.Linq;

    public class QuadTree<T> where T : IBoundable
    {
        public const int DefaultMaxDepth = 5;

        public readonly int MaxDepth;

        private Node<T> root;

        public QuadTree(int width, int height, int maxDepth = DefaultMaxDepth)
        {
            this.root = new Node<T>(0, 0, width, height);
            this.Bounds = this.root.Bounds;
            this.MaxDepth = maxDepth;
        }

        public int Count { get; private set; }

        public Rectangle Bounds { get; private set; }

        public bool Insert(T item)
        {
            //if item is outside QuadTree bounds -> we cannot insert it
            if (!item.Bounds.IsInside(this.Bounds))
            {
                return false;
            }
            int depth = 1;
            var currentNode = this.root;
            while (currentNode.Children != null)
            {
                int quadrant = GetQuadrant(currentNode, item.Bounds);
                if (quadrant == -1)
                {
                    break;
                }
                currentNode = currentNode.Children[quadrant];
                depth++;
            }
            currentNode.Items.Add(item);
            this.Split(currentNode, depth);
            this.Count++;

            return true;
        }

        public List<T> Report(Rectangle bounds)
        {
            var collisionCandidates = new List<T>();

            GetCollisionCandidates(this.root, bounds, collisionCandidates);

            return collisionCandidates;
        }

        public void ForEachDfs(Action<List<T>, int, int> action)
        {
            this.ForEachDfs(this.root, action);
        }

        private void ForEachDfs(Node<T> node, Action<List<T>, int, int> action, int depth = 1, int quadrant = 0)
        {
            if (node == null)
            {
                return;
            }
            if (node.Items.Any())
            {
                action(node.Items, depth, quadrant);
            }
            if (node.Children != null)
            {
                for (int i = 0; i < node.Children.Length; i++)
                {
                    this.ForEachDfs(node.Children[i], action, depth + 1, i);
                }
            }
        }

        private void GetCollisionCandidates(Node<T> node, Rectangle bounds, List<T> results)
        {
            var quadrant = GetQuadrant(node, bounds);
            //"boounds" not fit in any sub-quadrant -> return all items in current subtree
            if (quadrant == -1)
            {
                GetSubtreeContent(node, bounds, results);
            }
            else
            {
                if (node.Children != null)
                {
                    var child = node.Children[quadrant];
                    //call recursion for child quadrant which contains "bounds"
                    GetCollisionCandidates(child, bounds, results);
                }
                //In post action of the recursion we add all items from parent, which are not in sub-quadrant
                results.AddRange(node.Items);
            }
        }

        //Post-Order DFS to retrieve all items from a given subtree
        private void GetSubtreeContent(Node<T> node, Rectangle bounds, List<T> results)
        {
            if (node.Children != null)
            {
                foreach (var child in node.Children)
                {
                    if (child.Bounds.Intersects(bounds))
                    {
                        GetSubtreeContent(child, bounds, results);
                    }
                }
            }
            results.AddRange(node.Items);
        }

        private int GetQuadrant(Node<T> node, Rectangle bounds)
        {
            var verticalMidpoint = node.Bounds.MidX;
            var horizontalMidpoint = node.Bounds.MidY;

            var inTopQuadrant = node.Bounds.Y1 <= bounds.Y1 && bounds.Y2 <= horizontalMidpoint;
            var inBottomQuadrant = node.Bounds.Y2 >= bounds.Y2 && bounds.Y1 >= horizontalMidpoint;
            var inLeftQuadrant = node.Bounds.X1 <= bounds.X1 && bounds.X2 <= verticalMidpoint;
            var inRightQuadrant = node.Bounds.X2 >= bounds.X2 && bounds.X1 >= verticalMidpoint;

            if (inLeftQuadrant)
            {
                if (inTopQuadrant)
                {
                    return 1;
                }
                else if (inBottomQuadrant)
                {
                    return 2;
                }
            }
            else if (inRightQuadrant)
            {
                if (inTopQuadrant)
                {
                    return 0;
                }
                else if (inBottomQuadrant)
                {
                    return 3;
                }
            }

            return -1;
        }

        private void Split(Node<T> node, int nodeDepth)
        {
            //If there is no need to split  the node or we have reached MaxDepth -> stop
            if (!(node.ShouldSplit && nodeDepth < MaxDepth))
            {
                return;
            }

            var leftWidth = node.Bounds.Width / 2;
            var rightWidth = node.Bounds.Width - leftWidth;
            var topHeight = node.Bounds.Height / 2;
            var bottomHeight = node.Bounds.Height - topHeight;

            node.Children = new Node<T>[4];
            node.Children[0] = new Node<T>(node.Bounds.MidX, node.Bounds.Y1, rightWidth, topHeight);
            node.Children[1] = new Node<T>(node.Bounds.X1, node.Bounds.Y1, leftWidth, topHeight);
            node.Children[2] = new Node<T>(node.Bounds.X1, node.Bounds.MidY, leftWidth, bottomHeight);
            node.Children[3] = new Node<T>(node.Bounds.MidX, node.Bounds.MidY, rightWidth, bottomHeight);

            List<T> itemsToRemove = new List<T>();
            //Transfer items from parent to new nodes
            for (int i = 0; i < node.Items.Count; i++)
            {
                var item = node.Items[i];
                var quadrant = GetQuadrant(node, item.Bounds);
                if (quadrant != -1)
                {
                    node.Children[quadrant].Items.Add(item);
                    //node.Items.RemoveAt(i);
                    //i--;
                    itemsToRemove.Add(item);
                }
            }
            foreach (var item in itemsToRemove)
            {
                node.Items.Remove(item);
            }
            //In case all items from parent go to the same node -> attempt to split recursively
            foreach (var child in node.Children)
            {
                this.Split(child, nodeDepth + 1);
            }
        }
    }
}
