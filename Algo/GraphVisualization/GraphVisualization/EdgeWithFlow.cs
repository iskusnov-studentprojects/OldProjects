using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Drawing;
using System.Drawing.Drawing2D;

namespace GraphVisualization
{
    class EdgeWithFlow: OrientedEdge
    {
        private int flow;
        private int weight;

        public int Flow
        {
            get
            {
                return flow;
            }
            set
            {
                flow = value;
            }
        }

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

        public EdgeWithFlow(Vertix begin, Vertix end, int weight, int flow)
            : base(begin, end)
        {
            Flow = flow;
            Weight = weight;
        }

        public override void Draw(System.Drawing.Graphics canvas, int shift)
        {
            base.Draw(canvas, shift);
            int x = Begin.Location.X + (End.Location.X - Begin.Location.X) / 2 + shift,
                y = Begin.Location.Y + (End.Location.Y - Begin.Location.Y) / 2 - shift;
            canvas.DrawString(Flow.ToString() + '/' + Weight.ToString(), new Font("Areal", 9), new SolidBrush(Color.Black), x, y);
        }
    }
}
