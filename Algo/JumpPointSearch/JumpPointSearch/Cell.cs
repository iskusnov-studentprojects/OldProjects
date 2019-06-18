using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace JumpPointSearch
{
    class Cell
    {
        public int x;
        public int y;
        public int weight;
        public int evaluationPath;
        public Cell pred;

        public Cell(int _x, int _y, int _weight, int _evaluationPath, Cell _pred)
        {
            x = _x;
            y = _y;
            weight = _weight;
            evaluationPath = _evaluationPath;
            pred = _pred;
        }

        public override bool Equals(object obj)
        {
            if (obj == null || GetType() != obj.GetType())
                return false;
            Cell a = (Cell)obj;
            return (x == a.x) && (y == a.y);
        }
    }
}
