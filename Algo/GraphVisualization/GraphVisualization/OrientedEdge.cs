using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Drawing;
using System.Drawing.Drawing2D;

namespace GraphVisualization
{
    class OrientedEdge : Edge
    {
        /// <summary>
        /// Конструктор
        /// </summary>
        /// <param name="begin">
        /// Начало ребра
        /// </param>
        /// <param name="end">
        /// Конец ребра
        /// </param>
        public OrientedEdge(Vertix begin, Vertix end) : base(begin, end){}

        /// <summary>
        /// Нарисовать ребро
        /// </summary>
        /// <param name="canvas">
        /// Холст, на котором рисуется ребро
        /// </param>
        /// <param name="shift">
        /// Уровень изгиба кривой
        /// </param>
        public virtual void Draw(Graphics canvas, int shift)
        {
            Pen pen = new Pen(Color.Black,1);
            pen.EndCap = LineCap.Custom;
            var gp = new GraphicsPath();
            gp.AddLine(0, 0, 3, -10);
            gp.AddLine(0, 0, -3, -10);
            pen.CustomEndCap = new CustomLineCap(null,gp);
            
            int x = Begin.Location.X + (End.Location.X - Begin.Location.X) / 2 + shift, 
                y = Begin.Location.Y + (End.Location.Y - Begin.Location.Y) / 2 - shift;
            double arg = Math.Atan2(End.Location.Y - Begin.Location.Y, End.Location.X - Begin.Location.X);
            Point end = new Point(Convert.ToInt32(End.Location.X - 10 * Math.Cos(arg)), Convert.ToInt32(End.Location.Y - 10 * Math.Sin(arg)));
            canvas.DrawBezier(pen, Begin.Location, new Point(x, y), new Point(x, y), end);
        }
    }
}
