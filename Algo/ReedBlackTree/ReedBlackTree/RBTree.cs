using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace ReedBlackTree
{
    class RBTree<T>
    {
        private Node<T> root;

        public RBTree()
        {
            root = null;
        }

        public Node<T> GetRoot
        {
            get
            {
                return root;
            }
        }

        #region Searching
        private Node<T> GrandParent(Node<T> current)
        {
            if (current != null && current.parent != null)
                return current.parent.parent;
            else
                return null;
        }

        private Node<T> Uncle(Node<T> current)
        {
            Node<T> grandParent = GrandParent(current);
            if (current.parent == grandParent.left)
                return grandParent.right;
            else
                return grandParent.left;
        }

        private Node<T> Sibling(Node<T> current)
        {
            return current.parent.right;
        }

        private Node<T> Predecessor(Node<T> current)
        {
            Node<T> predecessor = current.left;

            if(predecessor != null)
                while (predecessor.right != null)
                {
                    predecessor = predecessor.right;
                }

            return predecessor;
        }

        private Node<T> Follower(Node<T> current)
        {
            Node<T> follower = current.right;

            while (follower.left != null)
            {
                follower = follower.left;
            }

            return follower;
        }
        #endregion

        public Node<T> Find(int key)
        {
            return RecFind(key, root);
        }

        public void Insert(int key, T data)
        {
            if (Find(key) != null)
            {
                MessageBox.Show("Ключ уже существует в дереве.");
                return;
            }

            Node<T> newNode = new Node<T>(key, data);
            if (root == null)
            {
                root = newNode;
            }
            else
                RecInsert(root, newNode);
            InsertCase1(newNode);
        }

        public void Delete(int key)
        {
            Node<T> x = Find(key);
            if (x != null)
                DeleteNode(x);
        }

        private void ReplaceNode(Node<T> x, Node<T> y)
        {
            if (x.parent != null)
            {
                if (x.parent.left == x)
                    x.parent.left = y;
                else
                    x.parent.right = y;
            }
            if (y != null)
            {
                y.parent = x.parent;
            }
        }

        #region Rotations
        private void LeftRotate(Node<T> x)
        {
            Node<T> y = x.right;
            x.right = y.left;
            if (y.left != null)
                y.left.parent = x;
            y.left = x;
            if (x.parent != null)
            {
                if (x.parent.left == x)
                    x.parent.left = y;
                else
                    x.parent.right = y;
            }
            else
            {
                root = y;
            }
            y.parent = x.parent;
            x.parent = y;
        }

        private void RightRotate(Node<T> x)
        {
            Node<T> y = x.left;
            x.left = y.right;
            if (y.right != null)
                y.right.parent = x;
            y.right = x;
            if (x.parent != null)
            {
                if (x.parent.left == x)
                    x.parent.left = y;
                else
                    x.parent.right = y;
            }
            else
            {
                root = y;
            }
            y.parent = x.parent;
            x.parent = y;
        }
        #endregion
        #region Insert Cases
        private void InsertCase1(Node<T> current)
        {
            if (current.parent == null)
                current.color = NodeColor.Black;
            else
                InsertCase2(current);
        }

        private void InsertCase2(Node<T> current)
        {
            if (current.parent.color == NodeColor.Black)
                return;
            else
                InsertCase3(current);
        }

        private void InsertCase3(Node<T> current)
        {
            Node<T> uncle = Uncle(current), grandparent;

            if (uncle != null && uncle.color == NodeColor.Red && current.parent.color == NodeColor.Red)
            {
                current.parent.color = NodeColor.Black;
                uncle.color = NodeColor.Black;
                grandparent = GrandParent(current);
                grandparent.color = NodeColor.Red;
                InsertCase1(grandparent);
            }
            else
            {
                InsertCase4(current);
            }
        }

        private void InsertCase4(Node<T> current)
        {
            Node<T> grandparent = GrandParent(current);

            if (current == current.parent.right && current.parent == grandparent.left)
            {
                LeftRotate(current.parent);
                current = current.left;
            }
            else
            {
                if (current == current.parent.left && current.parent == grandparent.right)
                {
                    RightRotate(current.parent);
                    current = current.right;
                }
            }

            InsertCase5(current);
        }

        private void InsertCase5(Node<T> current)
        {
            Node<T> grandparent = GrandParent(current);

            current.parent.color = NodeColor.Black;
            grandparent.color = NodeColor.Red;

            if (current == current.parent.left && current.parent == grandparent.left)
            {
                RightRotate(grandparent);
            }
            else
            {
                LeftRotate(grandparent);
            }
        }
        #endregion
        //Algolist's code, delete
        void DeleteFixup(Node<T> x)
        {

            /*************************************
             *  maintain Red-Black tree balance  *
             *  after deleting node x            *
             *************************************/

            while (x != root && x.color == NodeColor.Black)
            {
                if (x == x.parent.left)
                {
                    Node<T> w = x.parent.right;
                    if (w != null)
                        return;
                    if (w.color == NodeColor.Red)
                    {
                        w.color = NodeColor.Black;
                        x.parent.color = NodeColor.Red;
                        LeftRotate(x.parent);
                        w = x.parent.right;
                    }
                    if (w.left.color == NodeColor.Black && w.right.color == NodeColor.Black)
                    {
                        w.color = NodeColor.Red;
                        x = x.parent;
                    }
                    else
                    {
                        if (w.right.color == NodeColor.Black)
                        {
                            w.left.color = NodeColor.Black;
                            w.color = NodeColor.Red;
                            RightRotate(w);
                            w = x.parent.right;
                        }
                        w.color = x.parent.color;
                        x.parent.color = NodeColor.Black;
                        w.right.color = NodeColor.Black;
                        LeftRotate(x.parent);
                        x = root;
                    }
                }
                else
                {
                    Node<T> w = x.parent.left;
                    if (w == null)
                        return;
                    if (w.color == NodeColor.Red)
                    {
                        w.color = NodeColor.Black;
                        x.parent.color = NodeColor.Red;
                        RightRotate(x.parent);
                        w = x.parent.left;
                    }
                    if (w.right.color == NodeColor.Black && w.left.color == NodeColor.Black)
                    {
                        w.color = NodeColor.Red;
                        x = x.parent;
                    }
                    else
                    {
                        if (w.left.color == NodeColor.Black)
                        {
                            w.right.color = NodeColor.Black;
                            w.color = NodeColor.Red;
                            LeftRotate(w);
                            w = x.parent.left;
                        }
                        w.color = x.parent.color;
                        x.parent.color = NodeColor.Black;
                        w.left.color = NodeColor.Black;
                        RightRotate(x.parent);
                        x = root;
                    }
                }
            }
            x.color = NodeColor.Black;
        }

        void DeleteNode(Node<T> z)
        {
            Node<T> x, y;

            /*****************************
             *  delete node z from tree  *
             *****************************/

            if (z == null) return;


            if (z.left == null || z.right == null)
            {
                /* y has a null node as a child */
                y = z;
            }
            else
            {
                /* find tree successor with a null node as a child */
                y = z.right;
                while (y.left != null) y = y.left;
            }

            /* x is y's only child */
            if (y.left != null)
                x = y.left;
            else
                x = y.right;

            /* remove y from the parent chain */
            if(x != null)
                x.parent = y.parent;
            if (y.parent != null)
                if (y == y.parent.left)
                    y.parent.left = x;
                else
                    y.parent.right = x;
            else
                root = x;

            if (y != z) z.key = y.key;

            if (y.color == NodeColor.Black)
            {
                if (x != null)
                    DeleteFixup(x);
                else
                    DeleteFixup(y);
            }
        }
        #region Recursions
        private void RecInsert(Node<T> current, Node<T> newNode)
        {
            if (current.key > newNode.key)
            {
                if (current.left == null)
                {
                    newNode.parent = current;
                    current.left = newNode;
                }
                else
                    RecInsert(current.left, newNode);
            }
            else
            {
                if (current.right == null)
                {
                    newNode.parent = current;
                    current.right = newNode;
                }
                else
                    RecInsert(current.right, newNode);
            }
        }

        private Node<T> RecFind(int _key, Node<T> current)
        {
            if (current == null)
                return null;
            if (current.key == _key)
                return current;
            else
            {
                if (_key < current.key)
                    return RecFind(_key, current.left);
                else
                    return RecFind(_key, current.right);
            }
        }
        #endregion
    }
}
