using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Drawing;

namespace GraphVisualization
{
    class OrientedWeightedGraph : OrientedGraph
    {
        public OrientedWeightedGraph(List<string> vertices, List<string[]> edges): base(vertices, edges) { }
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
                Edges.Add(new OrientedWeightedEdge(FindVertix(i[0]), FindVertix(i[1]), Convert.ToInt32(i[2])));
            }
        }
    }
}
