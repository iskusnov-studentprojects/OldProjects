using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Drawing;

namespace ReedBlackTree
{
    class Vertix
    {
        public string key;
        public Color backColor;
        public Point location;
        public Vertix(string _key, NodeColor color, Point _location)
        {
            key = _key;
            location = _location;
            if (color == NodeColor.Black)
                backColor = Color.Gray;
            else
                backColor = Color.Red;
        }
        public virtual void Draw(Graphics canvas)
        {
            canvas.FillEllipse(new SolidBrush(backColor), location.X - 15, location.Y - 15, 30, 30);
            canvas.DrawEllipse(new Pen(Color.Black), location.X - 15, location.Y - 15, 30, 30);
            canvas.DrawString(key, new Font("Areal", 9), new SolidBrush(Color.Black), location.X - 15, location.Y - 9);
        }
    }
}
