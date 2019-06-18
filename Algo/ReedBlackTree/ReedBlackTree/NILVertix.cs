using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Drawing;

namespace ReedBlackTree
{
    class NILVertix : Vertix
    {
        public NILVertix(Point _location)
            : base("NIL", NodeColor.Black, _location)
        {
            backColor = Color.Black;
        }

        public override void Draw(Graphics canvas)
        {
            canvas.FillEllipse(new SolidBrush(backColor), location.X - 10, location.Y - 10, 20, 20);
        }
    }
}
