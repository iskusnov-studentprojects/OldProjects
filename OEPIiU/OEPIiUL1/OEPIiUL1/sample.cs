using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace OEPIiUL1
{
    class sample
    {
        public class sample2: sample
        {

        }

        private sample2 tmp;

        public sample()
        {
            tmp = new sample2();
        }
    }
}
