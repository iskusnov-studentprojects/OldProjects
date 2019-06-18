using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Drawing;

namespace JumpPointSearch
{
    class JPS
    {
        List<Cell> openList;
        List<Cell> closeList;
        Cell start;
        Cell finish;
        int[][] field;
        int stopValue;

        public JPS(int[][] _field, Cell _start, Cell _finish, int _stopValue)
        {
            openList = new List<Cell>();
            closeList = new List<Cell>();
            start = _start;
            finish = _finish;
            field = _field;
            stopValue = _stopValue;
        }

        public Stack<Point> SearchPath()
        {
            openList.Add(new Cell(start.x, start.y, 0, HeuristicEvaluationPath(start.x, start.y), null));
            Cell current = null;
            do
            {
                current = Other.min(openList);
                closeList.Add(current);
                JumpUp(current.x, current.y, current.weight - HeuristicEvaluationPath(current.x, current.y), current);
                JumpUpRight(current.x, current.y, current.weight - HeuristicEvaluationPath(current.x, current.y), current);
                JumpRight(current.x, current.y, current.weight - HeuristicEvaluationPath(current.x, current.y), current);
                JumpDownRight(current.x, current.y, current.weight - HeuristicEvaluationPath(current.x, current.y), current);
                JumpDown(current.x, current.y, current.weight - HeuristicEvaluationPath(current.x, current.y), current);
                JumpDownLeft(current.x, current.y, current.weight - HeuristicEvaluationPath(current.x, current.y), current);
                JumpLeft(current.x, current.y, current.weight - HeuristicEvaluationPath(current.x, current.y), current);
                JumpUpLeft(current.x, current.y, current.weight - HeuristicEvaluationPath(current.x, current.y), current);

            } while ((current.x != finish.x || current.y != finish.y) && openList.Count != 0);
            return RestorePath();
        }

        private void JumpUp(int x, int y, int weight, Cell pred)
        {
            while (field[x][y] != stopValue)
            {
                weight += 10 + 10 * field[x][y];
                y++;
                if (field[x + 1][y] == stopValue && field[x + 1][y + 1] != stopValue || field[x - 1][y] == stopValue && field[x - 1][y + 1] != stopValue)
                    openList.Add(new Cell(x, y, weight, HeuristicEvaluationPath(x, y), pred));
            }
        }

        private void JumpRight(int x, int y, int weight, Cell pred)
        {
            while (field[x][y] != stopValue)
            {
                weight += 10 + 10 * field[x][y];
                x++;
                if (field[x][y + 1] == stopValue && field[x + 1][y + 1] != stopValue || field[x][y - 1] == stopValue && field[x + 1][y - 1] != stopValue)
                    openList.Add(new Cell(x, y, weight, HeuristicEvaluationPath(x, y), pred));
            }
        }

        private void JumpDown(int x, int y, int weight, Cell pred)
        {
            while (field[x][y] != stopValue)
            {
                weight += 10 + weight * field[x][y];
                y--;
                if (field[x + 1][y] == stopValue && field[x + 1][y - 1] != stopValue || field[x - 1][y] == stopValue && field[x - 1][y - 1] != stopValue)
                    openList.Add(new Cell(x, y, weight, HeuristicEvaluationPath(x, y), pred));
            }
        }

        private void JumpLeft(int x, int y, int weight, Cell pred)
        {
            while (field[x][y] != stopValue)
            {
                weight += 10 + 10 * field[x][y];
                x--;
                if (field[x][y + 1] == stopValue && field[x - 1][y + 1] != stopValue || field[x][y - 1] == stopValue && field[x - 1][y - 1] != stopValue)
                    openList.Add(new Cell(x, y, weight, HeuristicEvaluationPath(x, y), pred));
            }
        }

        private bool BJumpUp(int x, int y)
        {
            while (field[x][y] != stopValue)
            {
                if (field[x + 1][y] == stopValue && field[x + 1][y + 1] != stopValue || field[x - 1][y] == stopValue && field[x - 1][y + 1] != stopValue)
                    return true;
                y++;
            }
            return false;
        }

        private bool BJumpRight(int x, int y)
        {
            while (field[x][y] != stopValue)
            {
                if (field[x][y + 1] == stopValue && field[x + 1][y + 1] != stopValue || field[x][y - 1] == stopValue && field[x + 1][y - 1] != stopValue)
                    return true;
                x++;
            }
            return false;
        }

        private bool BJumpDown(int x, int y)
        {
            while (field[x][y] != stopValue)
            {
                if (field[x + 1][y] == stopValue && field[x + 1][y - 1] != stopValue || field[x - 1][y] == stopValue && field[x - 1][y - 1] != stopValue)
                    return true;
                y--;
            }
            return false;
        }

        private bool BJumpLeft(int x, int y)
        {
            while (field[x][y] != stopValue)
            {
                if (field[x][y + 1] == stopValue && field[x - 1][y + 1] != stopValue || field[x][y - 1] == stopValue && field[x - 1][y - 1] != stopValue)
                    return true;
                x--;
            }
            return false;
        }

        private void JumpUpRight(int x, int y, int weight, Cell pred)
        {
            while (field[x][y] != stopValue)
            {
                if (field[x - 1][y] == stopValue && field[x - 1][y + 1] != stopValue || field[x][y - 1] == stopValue && field[x + 1][y - 1] != stopValue || BJumpUp(x, y) || BJumpRight(x, y))
                    openList.Add(new Cell(x, y, weight, HeuristicEvaluationPath(x, y), pred));
                weight += 14 + 14 * field[x][y];
                x++;
                y++;
            }
        }

        private void JumpDownRight(int x, int y, int weight, Cell pred)
        {
            while (field[x][y] != stopValue)
            {
                if (field[x - 1][y] == stopValue && field[x - 1][y - 1] != stopValue || field[x][y + 1] == stopValue && field[x + 1][y + 1] != stopValue || BJumpRight(x, y) || BJumpDown(x, y))
                    openList.Add(new Cell(x, y, weight, HeuristicEvaluationPath(x, y), pred));
                weight += 14 + 14 * field[x][y];
                x++;
                y--;
            }
        }

        private void JumpDownLeft(int x, int y, int weight, Cell pred)
        {
            while (field[x][y] != stopValue)
            {
                if (field[x + 1][y] == stopValue && field[x + 1][y - 1] != stopValue || field[x][y + 1] == stopValue && field[x - 1][y + 1] != stopValue || BJumpDown(x, y) || BJumpLeft(x, y))
                    openList.Add(new Cell(x, y, weight, HeuristicEvaluationPath(x, y), pred));
                weight += 14 + 14 * field[x][y];
                x--;
                y--;
            }
        }

        private void JumpUpLeft(int x, int y, int weight, Cell pred)
        {
            while (field[x][y] != stopValue)
            {
                if (field[x + 1][y] == stopValue && field[x + 1][y + 1] != stopValue || field[x][y - 1] == stopValue && field[x - 1][y - 1] != stopValue || BJumpUp(x, y) || BJumpLeft(x, y))
                    openList.Add(new Cell(x, y, weight, HeuristicEvaluationPath(x, y), pred));
                weight += 14 + 14 * field[x][y];
                x--;
                y++;
            }
        }

        private Stack<Point> RestorePath()
        {
            Stack<Point> stack = new Stack<Point>();
            Cell current = closeList[closeList.Count-1]; 
            return stack;
        }

        private int HeuristicEvaluationPath(int x, int y)
        {
            double dist = Math.Sqrt((x - finish.x) * (x - finish.x) + (y - finish.y) * (y - finish.y));
            return (int)(10 * dist);
        }
    }
}
