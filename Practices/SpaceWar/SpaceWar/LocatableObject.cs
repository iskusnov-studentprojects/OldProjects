using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Drawing;

namespace SpaceWar
{
    abstract class LocatableObject
    {
        private Location position;

        public LocatableObject()
        {
            position = new Location();
        }

        public LocatableObject(int x, int y)
        {
            position = new Location(x, y);
        }

        public virtual Location Position
        {
            set
            {
                position.X = value.X;
                position.Y = value.Y;
            }
            get
            {
                return position;
            }
        }

        public virtual void SetPosition(int x, int y)
        {
            position.X = x;
            position.Y = y;
        }

        public abstract void Draw(Graphics canvas, Rectangle canvasSize, Rectangle cursor);
    }
}
