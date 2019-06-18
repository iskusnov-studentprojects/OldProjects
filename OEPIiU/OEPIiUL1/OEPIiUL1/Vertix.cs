using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace OEPIiUL1
{
    class Vertix
    {
        public string name;
        public int t_early;
        public int t_after;
        public int r;

        public Vertix(string _name)
        {
            name = _name;
            t_early = int.MinValue;
            t_after = int.MaxValue;
            r = 0;
        }
    }
}
