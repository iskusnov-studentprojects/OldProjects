using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Drawing;

namespace GraphVisualization
{
    class WeightedEdge:  Edge
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
        public WeightedEdge(Vertix begin, Vertix end, int weight)
            : base(begin, end)
        {
            Weight = weight;
        }
        public override void Draw(Graphics canvas)
        {
            base.Draw(canvas);
            canvas.DrawString(Weight.ToString(), new Font("Areal", 9), new SolidBrush(Color.Black), Begin.Location.X + (End.Location.X - Begin.Location.X )/ 2, Begin.Location.Y + (End.Location.Y - Begin.Location.Y) / 2);
        }
    }
}
