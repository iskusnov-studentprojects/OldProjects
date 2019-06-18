using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace SpaceWar
{
    interface IStealth
    {
        bool StealthOn();
    }

    interface DisableStealth
    {
        int Vision();
    }
}
