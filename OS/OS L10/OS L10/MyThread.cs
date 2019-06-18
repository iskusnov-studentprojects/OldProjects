using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading;
using System.Threading.Tasks;
using System.Drawing;
using System.Xml;
using System.Xml.Serialization;
using System.Xml.Serialization.Configuration;

namespace OS_L10
{
    public class MyThread
    {
        public MyThread()
        {
            FigureColor = Color.White;
            Location = new Point(0, 0);
            Name = "unknown";
            thread = new Thread(new ThreadStart(Draw));
            Canvas = null;
        }

        public MyThread(Color _color, Point _location, Graphics _canvas)
        {
            FigureColor = _color;
            Location = _location;
            Canvas = _canvas;
            Name = "MyThread";
            thread = new Thread(new ThreadStart(Draw));
            thread.Priority = ThreadPriority.Lowest;
            thread.Start();
        }

        private Color color;
        private Point location;
        private Graphics canvas;
        private string name;
        private Thread thread;

        [XmlIgnore]
        public Color FigureColor
        {
            get
            {
                return color;
            }
            set
            {
                color = value;
            }
        }

        [XmlElement("FigureColor")]
        public int FigureColorAsArgb
        {
            get { return FigureColor.ToArgb(); }
            set { FigureColor = Color.FromArgb(value); }
        }

        public Point Location
        {
            set
            {
                location = value;
            }
            get
            {
                return location;
            }
        }

        [XmlIgnore]
        public Graphics Canvas
        {
            set
            {
                canvas = value;
            }
            get
            {
                return canvas;
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

        public ThreadPriority Priority
        {
            get
            {
                return thread.Priority;
            }
            set
            {
                thread.Priority = value;
            }
        }

        private void Draw()
        {
            if (Canvas == null)
            {
                throw new Exception("Невозможно вызвать фунцию Draw, т. к. не указан холст.");
            }
            while (true)
            {
                canvas.FillRectangle(new SolidBrush(FigureColor), location.X, location.Y, 100, 100);
                Thread.Sleep(1000);
                canvas.FillRectangle(new SolidBrush(Color.White), location.X, location.Y, 100, 100);
                Thread.Sleep(500);
            }
        }

        public void Stop()
        {
            thread.Abort();
        }

        public void Start()
        {
            if (thread == null)
                thread = new Thread(new ThreadStart(Draw));
            thread.Start();
        }

        public override string ToString()
        {
            return Name;
        }
    }
}
