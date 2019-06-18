using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ORandGTL3
{
    delegate bool Comparator(Object a, Object b);

    class Tree<T>
    {
        public Node root;

        public Tree(Node _root)
        {
            root = _root;
        }

        public class Node
        {
            public T data;
            public bool flag;
            public int[] realx;
            public int[] realy;
            public int key;
            public Node parent;
            public Node left;
            public Node right;
            public Node(T _data, int _key, Node _parent, Node _left, Node _right)
            {
                data = _data;
                key = _key;
                parent = _parent;
                left = _left;
                right = _right;
            }
        }
    }
}
