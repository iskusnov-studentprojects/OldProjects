using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Drawing;

namespace JumpPointSearch
{
    class Other
    {
        public static Cell min(List<Cell> list)
        {
            if(list.Count == 0)
                return null;
            Cell m = list[0];
            for (int i = 1; i < list.Count; i++)
                if (m.weight + m.evaluationPath > list[i].weight + list[i].evaluationPath)
                    m = list[i];
            return m;
        }

        public static Stack<Point> copypath(Stack<Point> old)
        {
            List<Point> list = old.ToList();
            Stack<Point> newstack = new Stack<Point>();
            for (int i = list.Count-1; i >= 0; i--)
                newstack.Push(list[i]);
            return newstack;
        }
    }
}
