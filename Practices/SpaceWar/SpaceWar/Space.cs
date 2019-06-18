using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace SpaceWar
{
    class Space: BaseClass
    {
        public class Empty: BaseClass
        {
            public Empty()
            {
                link = null;
                visited = false;
            }
            private LocatableObject link;
            public bool visited;

            public LocatableObject Link
            {
                set
                {
                    link = value;
                }
                get
                {
                    return link;
                }
            }

            public bool IsFreely
            {
                get
                {
                    if (link == null)
                        return true;
                    else
                        return false;
                }
            }
        }
        private Empty[,] field;
        private int width;
        private int height;

        public Space(int _width, int _height)
        {
            field = new Empty[_width, _height];
            for (int i = 0; i < _width; i++)
                for (int j = 0; j < _height; j++)
                    field[i, j] = new Empty();
            width = _width;
            height = _height;
        }

        public int Width
        {
            get
            {
                return width;
            }
        }

        public int Height
        {
            get
            {
                return height;
            }
        }

        virtual public LocatableObject this[int i, int j]
        {
            set
            {
                if (field[i, j] == null)
                    field[i, j] = new Empty();
                field[i, j].Link = value;
                if (value != null && value.Position.X != i && value.Position.Y != j)
                {
                    field[value.Position.X, value.Position.Y] = null;
                    value.SetPosition(i, j);
                }
            }
            get
            {
                if (field[i, j].Link == null)
                    return null;
                else
                    return field[i, j].Link;
            }
        }

        public void SetToZero()
        {
            for (int i = 0; i < width; i++)
                for (int j = 0; j < height; j++)
                {
                    if (field[i, j] == null)
                        field[i, j] = new Empty();
                    field[i, j].visited = false;
                }
        }

        public void SetAsVisited(int i, int j)
        {
            field[i, j].visited = true;
        }

        public bool GetStateOfVisits(int i, int j)
        {
            return field[i, j].visited;
        }

        public Empty GetEmpty(int i, int j)
        {
            return field[i,j];
        }
    }
}
