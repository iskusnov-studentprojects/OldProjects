using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace Timetable
{
    public partial class Graphic : Form
    {
        private int[][] columns;
        private int[][] downtimes;

        public Graphic(int[][] _columns, int[][] _downtimes)
        {
            columns = _columns;
            downtimes = _downtimes;
            InitializeComponent();
            
        }

        private void canvas_Paint(object sender, PaintEventArgs e)
        {
            Pen pen = new Pen(Color.Black);
            Pen dottedPen = new Pen(Color.Black);
            float[] dash = { 5, 5 };
            dottedPen.DashPattern = dash;
            float stepw = ((float)canvas.Size.Width - 50) / (Algorithm.summ(columns[columns.Length - 1]) + Algorithm.summ(downtimes[downtimes.Length - 1])),
                steph = ((float)canvas.Size.Height - 20) / (columns.Length + 1);
            e.Graphics.DrawLine(pen, 30, 10, 30, canvas.Size.Height - 10);

            float curw, curh = 10 + steph;
            Char S = 'S', x = 'x', s = 's';
            int Si = 0, xi = 0, si = 0;
            for (int i = 0; i < columns.Length; i++)
            {
                curw = 30;
                e.Graphics.DrawString(S.ToString() + Si.ToString(), new Font("Arial", 12), new SolidBrush(Color.Black), new PointF(0, curh - 8), StringFormat.GenericDefault);
                Si++;
                e.Graphics.DrawLine(pen, 30, curh, (float)canvas.Size.Width - 10, curh);
                for (int j = 0; j < columns[0].Length; j++)
                {
                    if (downtimes[i][j] > 0)
                    {
                        curw += downtimes[i][j] * stepw;
                        e.Graphics.DrawLine(pen, curw, curh - 5, curw, curh + 5);
                        e.Graphics.DrawString(x.ToString() + xi.ToString() + j.ToString(), new Font("Arial", 9), new SolidBrush(Color.Black), new PointF(curw - ((stepw * downtimes[i][j]) / 2) - 10, curh - 15), StringFormat.GenericDefault);
                        e.Graphics.DrawString(downtimes[i][j].ToString(), new Font("Arial", 9), new SolidBrush(Color.Black), new PointF(curw - ((stepw * downtimes[i][j]) / 2) - 10, curh + 2), StringFormat.GenericDefault);
                    }
                    curw += columns[i][j] * stepw;
                    e.Graphics.DrawLine(pen, curw, curh - 5, curw, curh + 5);
                    e.Graphics.DrawString(s.ToString() + si + j.ToString(), new Font("Arial", 9), new SolidBrush(Color.Black), new PointF(curw - ((stepw * columns[i][j]) / 2) - 10, curh - 15), StringFormat.GenericDefault);
                    e.Graphics.DrawString(columns[i][j].ToString(), new Font("Arial", 9), new SolidBrush(Color.Black), new PointF(curw - ((stepw * columns[i][j]) / 2) - 10, curh + 2), StringFormat.GenericDefault);
                    if (i < columns.Length - 1 && downtimes[i + 1][j] != 0) e.Graphics.DrawLine(dottedPen, curw, curh, curw, curh + steph);
                }
                xi++;
                si++;
                curh += steph;
            }
        }

        private void canvas_Resize(object sender, EventArgs e)
        {
            canvas.Invalidate();
        }
    }
}
