using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ORandGTL3
{
    class Algorithm
    {
        #region Поиск минимумов и максимумов
        public static int Min(int[] mass)
        {
            int min = int.MaxValue;
            for (int i = 0; i < mass.Length; i++)
                if (mass[i] < min)
                    min = mass[i];
            return min;
        }

        public static int Min(int[][] matrix, int j)
        {
            int min = int.MaxValue;
            for (int i = 0; i < matrix.Length; i++)
                if (matrix[i][j] < min)
                    min = matrix[i][j];
            return min;
        }

        public static int Min(int[] mass, int num)
        {
            int min = int.MaxValue;
            for (int i = 0; i < mass.Length; i++)
                if (mass[i] < min && i != num)
                    min = mass[i];
            return min;
        }

        public static int Min(int[][] matrix, int j, int num)
        {
            int min = int.MaxValue;
            for (int i = 0; i < matrix.Length; i++)
                if (matrix[i][j] < min && i != num)
                    min = matrix[i][j];
            return min;
        }

        private static void Min(Tree<Pair<int[][], Point>>.Node current, ref Tree<Pair<int[][], Point>>.Node res, ref int key)
        {
            if(current.key < key && current.right == null && current.right == null)
            {
                res = current;
                key = current.key;
            }
            if (current.right != null)
                Min(current.right, ref res, ref key);
            if (current.left != null)
                Min(current.left, ref res, ref key);
        }

        public static int Max(int[] mass)
        {
            int max = int.MinValue;
            for (int i = 0; i < mass.Length; i++)
                if (mass[i] > max)
                    max = mass[i];
            return max;
        }

        public static int Max(int[][] matrix)
        {
            int max = int.MinValue;
            for (int i = 0; i < matrix.Length; i++)
            {
                int tmp = Max(matrix[i]);
                if (tmp > max)
                    max = tmp;
            }
            return max;
        }
        #endregion

        //Найти позицию элемента в матрице
        public static Point Find(int[][] matrix, int value)
        {
            for (int i = 0; i < matrix.Length; i++)
                for (int j = 0; j < matrix[0].Length; j++)
                    if (matrix[i][j] == value)
                        return new Point(i, j);
            return new Point(-1, -1);
        }

        //Найти позицию элемента в массиве
        private static int IndexOf(int[] mass, int value)
        {
            for (int i = 0; i < mass.Length; i++)
                if (mass[i] == value)
                    return i;
            return -1;
        }

        //Привести матрицу
        public static int BringMatrix(int[][] matrix)
        {
            int sum = 0;
            for (int i = 0; i < matrix.Length; i++)
            {
                int min = Min(matrix[i]);
                for (int j = 0; j < matrix[0].Length; j++)
                    if (matrix[i][j] != int.MaxValue)
                        matrix[i][j] -= min;
                sum += min;
            }
            for (int j = 0; j < matrix[0].Length; j++)
            {
                int min = Min(matrix, j);
                for (int i = 0; i < matrix.Length; i++)
                    if (matrix[i][j] != int.MaxValue)
                        matrix[i][j] -= min;
                sum += min;
            }
            return sum;
        }

        //Вычислить штрафы для матрицы
        public static int[][] CalcPenalty(int[][] matrix)
        {
            int[][] res = CreateZeroMatrix(matrix.Length, matrix[0].Length);
            for(int i = 0; i < res.Length; i++)
                for(int j = 0; j < res[i].Length; j++)
                    if (matrix[i][j] == 0)
                    {
                        res[i][j] = Min(matrix[i], j) + Min(matrix, j, i);
                        if (res[i][j] < 0)
                            res[i][j] = int.MaxValue;
                    }
            return res;
        }

        //Сформировать нулевую матрицу
        public static int[][] CreateZeroMatrix(int n, int m)
        {
            int[][] res = new int[n][];
            for(int i = 0; i < n; i++)
            {
                res[i] = new int[m];
                for (int j = 0; j < m; j++)
                    res[i][j] = 0;
            }
            return res;
        }

        //Скопировать матрицу
        public static int[][] CopyMatrix(int[][] matrix)
        {
            int[][] res = new int[matrix.Length][];
            for (int i = 0; i < matrix.Length; i++)
            {
                res[i] = new int[matrix[0].Length];
                for (int j = 0; j < matrix[0].Length; j++)
                    res[i][j] = matrix[i][j];
            }
            return res;
        }

        //Скопировать массив
        public static int[] CopyMassive(int[] mass)
        {
            int[] res = new int[mass.Length];
            for (int i = 0; i < mass.Length; i++)
                res[i] = mass[i];
            return res;
        }

        //Преобразовать матрицу
        public static int[][] ReorganizeMatrix(int[][] matrix, int exi, int exj, ref int[] reali, ref int[] realj)
        {
            int[] mass1 = new int[reali.Length - 1],
                mass2 = new int[realj.Length - 1];
            for (int i = 0; i < mass1.Length; i++)
            {
                if (i >= exi)
                    mass1[i] = reali[i + 1];
                else
                    mass1[i] = reali[i];
                if (i >= exj)
                    mass2[i] = realj[i + 1];
                else
                    mass2[i] = realj[i];
            }
            reali = mass1;
            realj = mass2;
            int[][] res = new int[matrix.Length - 1][];
            for (int i = 0; i < res.Length; i++)
            {
                res[i] = new int[matrix[0].Length - 1];
                for(int j = 0; j < res.Length; j++)
                {
                    if (i >= exi)
                    {
                        if (j >= exj)
                            res[i][j] = matrix[i + 1][j + 1];
                        else
                            res[i][j] = matrix[i + 1][j];
                    }
                    else
                    {
                        if (j >= exj)
                            res[i][j] = matrix[i][j + 1];
                        else
                            res[i][j] = matrix[i][j];
                    }
                }
            }
            return res;
        }


        public static Tree<Pair<int[][], Point>> Solve(int[][] matrix)
        {
            Tree<Pair<int[][], Point>> tree = new Tree<Pair<int[][], Point>>(new Tree<Pair<int[][], Point>>.Node(new Pair<int[][], Point>(matrix, new Point(-1, -1)), BringMatrix(CopyMatrix(matrix)), null, null, null));
            int[] mass1 = new int[matrix.Length],
                mass2 = new int[matrix.Length];
            for (int i = 0; i < matrix.Length; i++)
            {
                mass1[i] = i + 1;
                mass2[i] = i + 1;
            }
            tree.root.realx = mass1;
            tree.root.realy = mass2;
            tree.root.flag = true;
            RecSolve(tree);
            return tree;
        }

        public static int[] RestorePath(Tree<Pair<int[][], Point>> tree)
        {
            //Поиск конечного узла
            Tree<Pair<int[][], Point>>.Node begin = SearchEndTree(tree.root),
                current = begin;
            int x = begin.data.b.X,
                y = begin.data.b.Y;

            //Выбор узлов
            List<Tree<Pair<int[][], Point>>.Node> list = new List<Tree<Pair<int[][], Point>>.Node>();
            list.Add(begin);
            while (current.parent != null && x != y)
            {
                if (x == current.data.b.Y && current.flag)
                {
                    x = current.data.b.X;
                    list.Add(current);
                    current = begin;
                }
                if (y == current.data.b.X && current.flag)
                {
                    y = current.data.b.Y;
                    list.Add(current);
                    current = begin;
                }
                current = current.parent;
            }

            //Восстановление пути
            List<int> path = new List<int>();
            int i = 0;
            path.Add(list[list.Count - 1].data.b.X);
            path.Add(list[list.Count - 1].data.b.Y);
            list.RemoveAt(list.Count - 1);
            while (list.Count > 0)
            {
                if(path[path.Count-1] == list[i].data.b.X)
                {
                    path.Add(list[i].data.b.Y);
                    list.RemoveAt(i);
                    i = 0;
                    continue;
                }
                i++;
            }
            return path.ToArray();
        }

        private static Tree<Pair<int[][], Point>>.Node SearchEndTree(Tree<Pair<int[][], Point>>.Node current)
        {
            if (current.data.a == null)
                return current;
            if (current.left != null)
            {
                Tree<Pair<int[][], Point>>.Node tmp = SearchEndTree(current.left);
                if (tmp != null)
                    return tmp;
            }
            if (current.right != null)
            {
                Tree<Pair<int[][], Point>>.Node tmp = SearchEndTree(current.right);
                if (tmp != null)
                    return tmp;
            }
            return null;
        }

        private static void RecSolve(Tree<Pair<int[][], Point>> tree)
        {
            //Поиск узла для обработки
            Tree<Pair<int[][], Point>>.Node current = null;
            int min = int.MaxValue;
            Min(tree.root, ref current, ref min);

            //Обработка узла
            if (current.data.a.Length == 1)
            {
                current.left = new Tree<Pair<int[][], Point>>.Node(new Pair<int[][], Point>(null, new Point(current.realx[0], current.realy[0])), int.MaxValue, current, null, null);
                current.right = new Tree<Pair<int[][], Point>>.Node(new Pair<int[][], Point>(null, new Point(current.realx[0], current.realy[0])), current.key, current, null, null);
                current.right.flag = true;
                return;
            }
            else
            {
                int r = BringMatrix(current.data.a);
                int[] realx = CopyMassive(current.realx),
                    realy = CopyMassive(current.realy);
                int[][] penalty = CalcPenalty(current.data.a);
                int max = Max(penalty);
                Point pos = Find(penalty, max);
                Pair<int[][], Point> newData = new Pair<int[][], Point>(ReorganizeMatrix(current.data.a, pos.X, pos.Y, ref realx, ref realy), new Point(current.realx[pos.X], current.realy[pos.Y]));

                //Формирование левого потомка
                int newKey = (current.key + max) < 0 ? int.MaxValue : (current.key + max);
                current.left = new Tree<Pair<int[][], Point>>.Node(new Pair<int[][], Point>(CopyMatrix(newData.a), newData.b), newKey, current, null, null);
                current.left.realx = realx;
                current.left.realy = realy;
                current.left.flag = false;
                Point p = SetInf(current.left);
                if (!current.left.flag)
                {
                    current.left.data.a = CopyMatrix(current.data.a);
                    current.left.realx = CopyMassive(current.realx);
                    current.left.realy = CopyMassive(current.realy);
                }
                current.left.data.a[IndexOf(current.left.realx, p.Y)][IndexOf(current.left.realy, p.X)] = int.MaxValue;

                //Формирование правого потомка
                current.right = new Tree<Pair<int[][], Point>>.Node(new Pair<int[][], Point>(CopyMatrix(newData.a), newData.b), newKey, current, null, null);
                current.right.realx = CopyMassive(realx);
                current.right.realy = CopyMassive(realy);
                current.right.flag = true;
                p = SetInf(current.right);
                current.right.data.a[IndexOf(current.right.realx, p.Y)][IndexOf(current.right.realy, p.X)] = int.MaxValue;
                int t = BringMatrix(CopyMatrix(current.right.data.a));
                current.right.key = BringMatrix(CopyMatrix(current.right.data.a)) < 0 ? current.key : (current.key + BringMatrix(CopyMatrix(current.right.data.a)));
                RecSolve(tree);
            }
        }

        //Найти позицию для установки бесконечности
        private static Point SetInf(Tree<Pair<int[][], Point>>.Node current)
        {
            int x = current.data.b.X,
                y = current.data.b.Y;
            Tree<Pair<int[][], Point>>.Node cur = current;
            if(!current.flag)
            {
                x = current.data.b.Y;
                y = current.data.b.X;
            }
            else
                while (cur.parent != null)
                {
                    if (x == cur.data.b.Y && cur.flag)
                    {
                        x = cur.data.b.X;
                        cur = current;
                    }
                    if (y == cur.data.b.X && cur.flag)
                    {
                        y = cur.data.b.Y;
                        cur = current;
                    }
                    cur = cur.parent;
                }
            return new Point(x, y);
        }
    }
}
