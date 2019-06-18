using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Drawing;

namespace GraphVisualization
{
    public class Vertix
    {
        public Vertix(string name, Point location)
        {
            Name = name;
            Location = location;
            backcolor = Color.Aqua;
        }

        private Point location;
        private string name;
        private Color backcolor;
        
        public Point Location
        {
            get
            {
                return location;
            }
            set
            {
                location = value;
            }
        }

        public string Name
        {
            get
            {
                return name;
            }
            set
            {
                name = value;
            }
        }

        public Color BackColor
        {
            get
            {
                return backcolor;
            }
            set
            {
                backcolor = value;
            }
        }

        public virtual void Draw(Graphics canvas)
        {
            canvas.FillEllipse(new SolidBrush(BackColor), Location.X - 10, Location.Y - 10, 20, 20);
            canvas.DrawEllipse(new Pen(Color.Black), Location.X - 10, Location.Y - 10, 20, 20);
            canvas.DrawString(Name, new Font("Areal", 9), new SolidBrush(Color.Black), Location.X - 5, Location.Y - 6);
        }
    }
}
