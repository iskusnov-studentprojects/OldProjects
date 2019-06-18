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

namespace JumpPointSearch
{
    public partial class Form1 : Form
    {
        RectangleF size;
        private int width;
        private int height;
        private double stepw;
        private double steph;
        private int[][] map;
        private Point start;
        private Point finish;
        private string pathToFile;
        private bool pathFound;
        private Stack<Point> path;
        private bool bSetStart;
        private bool bSetFinish;

        public Form1()
        {
            InitializeComponent();
            start = new Point();
            finish = new Point();
            size = new RectangleF(0, 0, (float)canvas.Size.Width, (float)canvas.Size.Height);
            pathFound = false;
            bSetStart = false;
            bSetFinish = false;
        }

        private void SetGrid(Graphics graphics)
        {
            Pen pen = new Pen(Color.Black);
            for(double curw = 0; curw < size.Width; curw += stepw)
                graphics.DrawLine(pen, new PointF((float)curw,0), new PointF((float)curw,size.Height));
            for (double curh = 0; curh < size.Height; curh += steph)
                graphics.DrawLine(pen, new PointF(0, (float)curh), new PointF(size.Width, (float)curh));
        }

        private void DrawMap(Graphics graphics)
        {
            if (map == null)
                return;
            graphics.Clear(Color.White);
            Brush brush = new SolidBrush(Color.Gray);
            for (int i = 1; i <= height; i++)
                for (int j = 1; j <= width; j++)
                    if (map[i][j] == 1)
                        graphics.FillRectangle(brush, (float)stepw * (j - 1), (float)steph * (i - 1), (float)stepw, (float)steph);
            SetGrid(graphics);
        }

        private void SetStart(Graphics graphics, int x, int y)
        {
            if (map == null)
                return;
            if (map[x][y] == 1 || (finish.X == x && finish.Y == y))
            {
                MessageBox.Show("Клетка занята!");
                return;
            }
            bSetStart = false;
            setStartButton.Enabled = true;
            if (pathFound)
            {
                pathFound = false;
                DrawMap(canvas.CreateGraphics());
                FillFinish(canvas.CreateGraphics());
            }
            SetCellColor(graphics, start.X - 1, start.Y - 1, Color.White);
            start.X = x;
            start.Y = y;
            FillStart(graphics);
            SetGrid(graphics);
        }

        private void FillStart(Graphics graphics)
        {
            SetCellColor(graphics, start.X - 1, start.Y - 1, Color.Blue);
        }

        private void SetFinish(Graphics graphics, int x, int y)
        {
            if (map == null)
                return;
            if (map[x][y] == 1 || (start.X == x && start.Y == y))
            {
                MessageBox.Show("Клетка занята!");
                return;
            }
            bSetFinish = false;
            setFinishButton.Enabled = true;
            if (pathFound)
            {
                pathFound = false;
                DrawMap(canvas.CreateGraphics());
                FillStart(canvas.CreateGraphics());
            }
            SetCellColor(graphics, finish.X - 1, finish.Y - 1, Color.White);
            finish.X = x;
            finish.Y = y;
            FillFinish(graphics);
            SetGrid(graphics);
        }

        private void FillFinish(Graphics graphics)
        {
            SetCellColor(graphics, finish.X - 1, finish.Y - 1, Color.Green);
        }

        private void LoadMap()
        {
            if (!File.Exists(pathToFile))
                return;
            StreamReader reader = new StreamReader(File.Open(pathToFile, FileMode.Open));
            List<int[]> list = new List<int[]>();
            do
            {
                string line = reader.ReadLine();
                width = line.Length;
                list.Add(new int[width+2]);
                list[list.Count - 1][0] = 1;
                list[list.Count - 1][width+1] = 1;
                if(list.Count > 1 && list[list.Count-2].Length != width + 2)
                    throw new Exception("Поле не прямоугольное.");
                for (int i = 1; i <= width; i++)
                    list[list.Count - 1][i] = Convert.ToInt32(line[i - 1].ToString());
            } while (!reader.EndOfStream);
            list.Insert(0, new int[width + 2]);
            for (int i = 0; i < width + 2; i++)
                list[0][i] = 1;
            list.Add(new int[width + 2]);
            for (int i = 0; i < width + 2; i++)
                list[list.Count-1][i] = 1;
            map = list.ToArray();
            height = map.Length - 2;
            stepw = size.Width / (double)width;
            steph = size.Height / (double)height;
            DrawMap(canvas.CreateGraphics());
        }

        private void loadButton_Click(object sender, EventArgs e)
        {
            OpenFileDialog dialog = new OpenFileDialog();
            pathToFile = dialog.Filter = "Data files (*.dat)|*.dat|All files (*.*)|*.*";
            dialog.Multiselect = false;
            dialog.ShowDialog();
            pathToFile = dialog.FileName;
            LoadMap();
            DrawMap(canvas.CreateGraphics());
        }

        private void SetCellColor(Graphics graphics,int x, int y, Color color)
        {
            Brush brush = new SolidBrush(color);
            float wp = (float)stepw * y,
                hp = (float)steph * x;
            graphics.FillRectangle(brush, wp, hp, (float)stepw, (float)steph);
        }

        private void canvas_MouseClick(object sender, MouseEventArgs e)
        {
            int i = (int)(e.Y / steph) + 1,
                j = (int)(e.X / stepw) + 1;
            if (ModifierKeys == Keys.Shift || bSetStart)
                SetStart(canvas.CreateGraphics(), i, j);
            if (ModifierKeys == Keys.Control || bSetFinish)
                SetFinish(canvas.CreateGraphics(), i, j);
        }

        private void DrawLine(int x0, int y0, int x, int y, Graphics graphics)
        {
            graphics.DrawLine(new Pen(Color.Black), x0 * (float)stepw + (float)stepw / 2, y0 * (float)steph + (float)steph / 2, x * (float)stepw + (float)stepw / 2, y * (float)steph + (float)steph / 2);
        }

        private void DrawPath(Graphics graphics)
        {
            Stack<Point> stack = Other.copypath(path);
            Point p = stack.Pop();
            DrawMap(canvas.CreateGraphics());
            FillStart(canvas.CreateGraphics());
            FillFinish(canvas.CreateGraphics());
            while (stack.Count > 0)
            {
                Pen pen = new Pen(Color.Black, 4);
                pen.StartCap = System.Drawing.Drawing2D.LineCap.RoundAnchor;
                pen.EndCap = System.Drawing.Drawing2D.LineCap.ArrowAnchor;
                graphics.DrawLine(pen, (float)((p.Y - 1) * stepw + stepw / 2), (float)((p.X - 1) * steph + steph / 2), (float)((stack.Peek().Y - 1) * stepw + stepw / 2), (float)((stack.Peek().X - 1) * steph + steph / 2));
                p = stack.Pop();
            }
        }

        private void findPathButton_Click(object sender, EventArgs e)
        {
            if (!pathFound)
            {
                JPS jps = new JPS(map, start, finish, 1);
                path = JPS.SearchPath(start, finish, map, 1);
                pathFound = true;
            }
            if(path.Count == 0)
            {
                MessageBox.Show("Пути не существует.");
                return;
            }
            DrawPath(canvas.CreateGraphics());
        }

        private void canvas_Paint(object sender, PaintEventArgs e)
        {
            DrawMap(e.Graphics);
            FillStart(e.Graphics);
            FillFinish(e.Graphics);
            if (pathFound)
            {
                DrawPath(e.Graphics);
            }
        }

        private void canvas_Resize(object sender, EventArgs e)
        {
            size = new RectangleF(0, 0, (float)canvas.Size.Width, (float)canvas.Size.Height);
            stepw = size.Width / (double)width;
            steph = size.Height / (double)height;
            canvas.Invalidate();
        }

        private void setStartButton_Click(object sender, EventArgs e)
        {
            bSetStart = true;
            setStartButton.Enabled = false;
        }

        private void button2_Click(object sender, EventArgs e)
        {
            bSetFinish = true;
            setFinishButton.Enabled = false;
        }

        private void выходToolStripMenuItem_Click(object sender, EventArgs e)
        {
            this.Close();
        }

        private void создатьКартуToolStripMenuItem_Click(object sender, EventArgs e)
        {
            Rectangle rect = new Rectangle();
            (new SizeDialog(rect)).ShowDialog();
            width = rect.Width;
            height = rect.Height;
            stepw = size.Width / (double)width;
            steph = size.Height / (double)height;
            map = new int[height + 2][];
            for (int i = 0; i < height + 2; i++)
            {
                map[i] = new int[rect.Width + 2];
                map[i][0] = 1;
                map[i][width + 1] = 1;
            }
            for(int i = 0; i < width + 2; i++)
            {
                map[0][i] = 1;
                map[height + 1][i] = 1;
            }
            DrawMap(canvas.CreateGraphics());
        }
    }
}