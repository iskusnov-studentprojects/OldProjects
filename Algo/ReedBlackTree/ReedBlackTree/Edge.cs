using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Drawing;

namespace ReedBlackTree
{
    class Edge
    {
        public Vertix begin;
        public Vertix end;

        public Edge(Vertix _begin, Vertix _end)
        {
            begin = _begin;
            end = _end;
        }

        public void Draw(Graphics canvas)
        {
            canvas.DrawLine(new Pen(Color.Black), begin.location, end.location);
        }
    }
}
