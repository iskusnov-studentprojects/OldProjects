using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace SpaceWar
{
    class RadarShip : SpaceShip, DisableStealth
    {
        public RadarShip(int x, int y, PlayerColor color, Space _space, string[][] parameters)
            : base(x, x, color, _space, parameters)
        { }

        public int Vision()
        {
            return visibility;
        }

        public override void Attack(LocatableObject obj)
        {
            Retreat(obj);
        }
    }
}
