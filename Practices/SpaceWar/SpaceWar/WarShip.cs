using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Drawing;

namespace SpaceWar
{
    class WarShip : SpaceShip
    {
        #region Attributes
        private int damage;
        #endregion

        public WarShip(int x, int y, PlayerColor color, Space _space, string[][] parameters)
            : base(x, y, color, _space, parameters)
        {
            for (int i = 0; i < parameters.Length; i++)
            {
                switch (parameters[i][0])
                {
                    case "Damage": damage = Convert.ToInt32(parameters[i][1]); break;
                }
            }
        }

        public void fire(SpaceShip ship)
        {
            ship.GetDamage(damage);
            animationState = ShipAnimationState.Attack;
        }

        public override void Attack(LocatableObject obj)
        {
            animationState = ShipAnimationState.None;
            path = null;
            SpaceShip ship = (SpaceShip)obj;
            for (int i = 0; i < speed; i++)
            {
                if (Math.Sqrt(Math.Pow(ship.Position.X - Position.X, 2) + Math.Pow(ship.Position.Y - Position.Y, 2)) <= visibility * 0.8)
                {
                    fire(ship);
                    break;
                }
                else
                    MoveTo(ship.Position);
            }
        }
    }
}
