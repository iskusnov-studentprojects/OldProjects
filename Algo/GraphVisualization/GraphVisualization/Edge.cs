using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Drawing;

namespace GraphVisualization
{
    /// <summary>
    /// Ребро графа
    /// </summary>
    class Edge
    {
        /// <summary>
        /// Конструктор
        /// </summary>
        /// <param name="begin">
        /// Первая вершина
        /// </param>
        /// <param name="end">
        /// Вторая вершина
        /// </param>
        public Edge(Vertix begin, Vertix end)
        {
            Begin = begin;
            End = end;
        }

        /// <summary>
        /// Первая вершина
        /// </summary>
        public Vertix Begin;
        /// <summary>
        /// Вторая вершина
        /// </summary>
        public Vertix End;

        /// <summary>
        /// Нарисовать ребро
        /// </summary>
        /// <param name="canvas">
        /// Холст, на котором рисуется ребро
        /// </param>
        public virtual void Draw(Graphics canvas)
        {
            canvas.DrawLine(new Pen(Color.Black),Begin.Location,End.Location);
        }
    }
}
