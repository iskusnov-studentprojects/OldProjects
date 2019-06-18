using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Drawing;

namespace GraphVisualization
{
    class OrientedGraph : Graph
    {
        public OrientedGraph(List<string> vertices, List<string[]> edges): base(vertices,edges){}

        protected override void CreateGraph(List<string> vertices, List<string[]> edges)
        {
            int x = 120, y = 120, r = 100;
            double arg = 0;
            foreach (string i in vertices)
            {
                Vertices.Add(new Vertix(i, new Point(Convert.ToInt32(x + Math.Cos(arg) * r), Convert.ToInt32(y + Math.Sin(arg) * r))));
                arg += Math.PI * 2 / vertices.Count;
            }
            foreach (string[] i in edges)
            {
                Edges.Add(new OrientedEdge(FindVertix(i[0]), FindVertix(i[1])));
            }
        }

        public override void Draw(Graphics canvas)
        {
            foreach (OrientedEdge i in Edges)
            {
                if (!ExistsEdge(i.End, i.Begin))
                    i.Draw(canvas, 0);
                else
                {
                    if (Edges.IndexOf(FindEdge(i.Begin, i.End)) > Edges.IndexOf(FindEdge(i.End, i.Begin)))
                        i.Draw(canvas, 15);
                    else
                        i.Draw(canvas, -15);
                }
            }
            foreach (var i in Vertices)
                i.Draw(canvas);
        }

        protected bool ExistsEdge(Vertix begin, Vertix end)
        {
            foreach (var i in Edges)
                if (i.Begin == begin && i.End == end)
                    return true;
            return false;
        }

        protected Edge FindEdge(Vertix begin, Vertix end)
        {
            foreach (var i in Edges)
                if (i.Begin == begin && i.End == end)
                    return i;
            return null;
        }
    }
}
