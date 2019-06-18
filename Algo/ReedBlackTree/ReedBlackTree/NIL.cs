using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ReedBlackTree
{
    class NIL<T> : Node<T>
    {
        public NIL(Node<T> _parent = null)
            : base()
        {
            parent = _parent;
        }
    }
}
