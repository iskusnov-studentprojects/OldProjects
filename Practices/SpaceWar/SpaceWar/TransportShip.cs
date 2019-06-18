using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace SpaceWar
{
    class TransportShip : SpaceShip, ICapture
    {
        public TransportShip(int x, int y, PlayerColor color, Space _space, string[][] parameters)
            : base(x, y, color, _space, parameters)
        { }

        public override void Attack(LocatableObject obj)
        {
            Retreat(obj);
        }

        public void Capture(Planet planet)
        {
            path = null;
        	for (int i = 0; i < speed; i++)
        		if(Math.Sqrt(Math.Pow(planet.Position.X - Position.X,2) + Math.Pow(planet.Position.Y - Position.Y,2)) <= visibility*0.1){
        			planet.Captured(playerColor);
                    Armor = -1;
        			return;
        		}
	        	else{
			        //Движение к планете
                    if (path != null && path.Count > 0 && space[path.Peek().X, path.Peek().Y] == null)
                    {
                        space[path.Peek().X, path.Peek().Y] = this;
                        path.Pop();
                    }
                    else
                        path = BFS(Position, planet.Position);
	        	}
        }
    }
}
