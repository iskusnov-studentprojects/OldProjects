using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Drawing;

namespace GraphVisualization
{
    class OrientedWeightedEdge: OrientedEdge
    {
        private int weight;

        public int Weight
        {
            get
            {
                return weight;
            }
            set
            {
                weight = value;
            }
        }

        public OrientedWeightedEdge(Vertix begin, Vertix end, int weight)
            : base(begin, end)
        {
            Weight = weight;
        }

        public override void Draw(Graphics canvas, int shift)
        {
            base.Draw(canvas, shift);
            int x = Begin.Location.X + (End.Location.X - Begin.Location.X) / 2 + shift,
                y = Begin.Location.Y + (End.Location.Y - Begin.Location.Y) / 2 - shift;
            canvas.DrawString(Weight.ToString(), new Font("Areal", 9), new SolidBrush(Color.Black), x, y);
        }
    }
}
