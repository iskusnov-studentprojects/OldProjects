using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace SpaceWar
{
    class StealthShip : WarShip, IStealth
    {
        public StealthShip(int x, int y, PlayerColor color, Space _space, string[][] parameters)
            : base(x, y, color, _space, parameters)
        { }

        public bool StealthOn()
        {
            int range = visibility * 2;
            for (int i = Position.X - range >= 0 ? Position.X - range : 0; i < (Position.X + range <= space.Width ? Position.X + range : space.Width); i++)
                for (int j = Position.Y - range >= 0 ? Position.Y - range : 0; i < (Position.Y + range <= space.Width ? Position.Y + range : space.Width); j++)
                    if(space[i,j] is DisableStealth)
                        if (Math.Sqrt(Math.Pow(i - Position.X, 2) + Math.Pow(j - Position.Y, 2)) <= (space[i,j] as DisableStealth).Vision())
                        {
                            return false;
                        }
            return true;
        }
    }
}
