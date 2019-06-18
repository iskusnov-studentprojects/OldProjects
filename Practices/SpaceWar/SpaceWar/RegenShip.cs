using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace SpaceWar
{
    class RegenShip : WarShip, IRegeneration
    {
        public RegenShip(int x, int y, PlayerColor color, Space _space, string[][] parameters)
            : base(x, y, color, _space, parameters)
        { }

        public void Regeneration()
        {
            armor++;
        }
    }
}
