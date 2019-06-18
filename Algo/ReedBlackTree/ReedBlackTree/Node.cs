using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ReedBlackTree
{
    enum NodeColor
    {
        Red,
        Black
    }

    class Node<T>
    {
        public int key;
        public T data;
        public Node<T> left;
        public Node<T> right;
        public Node<T> parent;
        public NodeColor color;

        protected Node()
        {
            key = int.MinValue;
            color = NodeColor.Black;
            parent = null;
            left = null;
            right = null;
        }

        public Node(int _key, T _data, NodeColor _color = NodeColor.Red, Node<T> _parent = null)
        {
            key = _key;
            data = _data;
            color = _color;
            parent = _parent;
            left = null;
            right = null;
        }
    }
}
