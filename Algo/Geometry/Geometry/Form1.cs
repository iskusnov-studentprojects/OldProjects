using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using System.IO;

namespace Geometry
{
    public partial class Form1 : Form
    {
        Point[] points;

        public Form1()
        {
            InitializeComponent();
        }

        void LoadPoints()
        {
            StreamReader reader = new StreamReader(File.Open("input.dat", FileMode.Open));
            string[] massStr = reader.ReadLine().Split(' ');
            reader.Close();
            points = new Point[massStr.Length];
            for (int i = 0; i < massStr.Length; i++)
            {
                points[i] = new Point(Convert.ToInt32(massStr[i].Split(',')[0]), Convert.ToInt32(massStr[i].Split(',')[1]));
            }
        }

        /*
        Point GetStartPoint()
        {
            Point point = points[0];
            for (int i = 1; i < points.Length; i++)
                if (point.X > points[i].X)
                    point = points[i];
            return point;
        }
        */

        int GetStartPoint()
        {
            int ret = 0;
            for (int i = 1; i < points.Length; i++)
            {
                if (points[i].Y < points[ret].Y)
                    ret = i;
                else
                    if (points[i].Y == points[ret].Y &&
                        points[i].X < points[ret].X)
                        ret = i;
            }
            return ret;
        }

        List<Point> ConstructConvexHull()
        {
            List<Point> hull = new List<Point>();
            int beg = GetStartPoint();
            hull.Add(points[beg]);
            int first = beg;
            int cur = beg;
            do
            {
                int next = (cur + 1) % points.Length;
                for (int i = 0; i < points.Length; i++)
                {
                    int sign = Rotate(points[cur], points[next], points[i]);
                    // точка mas[i] находится левее прямой ( mas[cur], mas[next] )
                    if (sign < 0) // обход выпуклой оболочки против часовой стрелки
                        next = i;
                    // точка лежит на прямой, образованной точками  mas[cur], mas[next]
                    else if (sign == 0)
                    {
                        // точка mas[i] находится дальше, чем mas[next] от точки mas[cur]
                        if (isInside(points[cur], points[next], points[i]))
                            next = i;
                    }
                }
                cur = next;
                hull.Add(points[next]);
            }
            while (cur != first);
            return hull;
        }

        int Rotate(Point a, Point b, Point c)
        {
            return a.X * (b.Y - c.Y) + b.X * (c.Y - a.Y) + c.X * (a.Y - b.Y);
        }

        bool isInside(Point p1, Point p, Point p2)
        {
            return (p1.X <= p.X && p.X <= p2.X &&
                     p1.Y <= p.Y && p.Y <= p2.Y);
        }

        double Distance(Point beg, Point end)
        {
            return Math.Sqrt((beg.X - end.X) * (beg.X - end.X) + (beg.Y - end.Y) * (beg.Y - end.Y));
        }

        void FindMaxDistance(List<Point> hull)
        {
            int v = 0,
            a = 1,
            b = 2;
            double dist = Distance(hull[v], new Point((hull[a].X + hull[b].X)/2,(hull[a].Y + hull[b].Y)/2));
            for (int i = 2, j = 3; j < hull.Count; i++, j++)
                if (Distance(hull[v], new Point((hull[i].X + hull[j].X) / 2, (hull[i].Y + hull[j].Y) / 2)) > dist)
                {
                    a = i; b = j; dist = Distance(hull[v], new Point((hull[a].X + hull[b].X) / 2, (hull[a].Y + hull[b].Y) / 2));
                }

            bool f = true;
            int t1 = 0, t2 = 0, t3 = 0;
            while (a < hull.Count - 1)
            {
                if (f)
                {
                    b = v + 1;
                    a++;
                    f = false;
                    if (Distance(hull[a], new Point((hull[v].X + hull[b].X) / 2, (hull[v].Y + hull[b].Y) / 2)) > dist)
                    {
                        dist = Distance(hull[a], new Point((hull[v].X + hull[b].X) / 2, (hull[v].Y + hull[b].Y) / 2));
                        t1 = v;
                        t2 = a;
                        t3 = b;
                    }
                }
                else
                {
                    b = a + 1;
                    v++;
                    f = true;
                    if (Distance(hull[v], new Point((hull[a].X + hull[b].X) / 2, (hull[a].Y + hull[b].Y) / 2)) > dist)
                    {
                        dist = Distance(hull[v], new Point((hull[a].X + hull[b].X) / 2, (hull[a].Y + hull[b].Y) / 2));
                        t1 = v;
                        t2 = a;
                        t3 = b;
                    }
                }
            }

            Point p1 = hull[t1], p2 = hull[t2], p3 = hull[t3], ap1,ap2;
            if (Distance(p1, p2) > Distance(p1, p3))
            {
                ap1 = p1;
                ap2 = p2;
            }
            else{
                if (Distance(p2, p3) > Distance(p1, p3))
                {
                    ap1 = p2;
                    ap2 = p3;
                }
                else
                {
                    ap1 = p1;
                    ap2 = p3;
                }
            }
            int h = field.Height / 2;
            int w = field.Width / 2;
            field.CreateGraphics().FillEllipse(new SolidBrush(Color.Red), (ap1.X + w - 5), (h - ap1.Y - 5), 10, 10);
            field.CreateGraphics().FillEllipse(new SolidBrush(Color.Red), (ap2.X + w - 5), (h - ap2.Y - 5), 10, 10);
        }

        void DrawPoints(Graphics canvas)
        {
            int h = field.Height / 2;
            int w = field.Width / 2;
            canvas.Clear(Color.White);
            canvas.DrawLine(new Pen(Color.Black, 2), new Point(0, h), new Point(field.Height, h));
            canvas.DrawLine(new Pen(Color.Black, 2), new Point(w, 0), new Point(w, field.Width));
            foreach (var i in points)
            {
                canvas.FillEllipse(new SolidBrush(Color.Black), (i.X + w - 5), (h - i.Y - 5), 10, 10);
            }
        }

        void DrawHull(Graphics canvas, List<Point> hull)
        {
            int h = field.Height / 2;
            int w = field.Width / 2;
            for (int i = 1; i < hull.Count; i++)
            {
                canvas.DrawLine(new Pen(Color.Black), new Point(hull[i - 1].X + w, h - hull[i - 1].Y), new Point(hull[i].X + w, h - hull[i].Y));
            }
        }

        private void button1_Click(object sender, EventArgs e)
        {
            LoadPoints();
            DrawPoints(field.CreateGraphics());
        }

        private void button2_Click(object sender, EventArgs e)
        {
            FindMaxDistance(ConstructConvexHull());
        }
    }
}
