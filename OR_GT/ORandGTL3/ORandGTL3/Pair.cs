using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ORandGTL3
{
    class Pair<T, K>
    {
        public T a;
        public K b;

        public Pair(T _a, K _b)
        {
            a = _a;
            b = _b;
        }
    }
}
