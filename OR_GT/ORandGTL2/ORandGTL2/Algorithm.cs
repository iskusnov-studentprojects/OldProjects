using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ORandGTL2
{
    class Algorithm
    {
        public static int Sort(int[][] table)
        {
            int[][] sequences = GetSequences(table);
            int[][][] tables = { matrixMethod(formTable(table, sequences[0])), matrixMethod(formTable(table, sequences[1])), matrixMethod(formTable(table, sequences[2])), matrixMethod(formTable(table, sequences[3])) };
            int num = 0;
            for (int i = 1; i < tables.Length; i++)
            {
                if (tables[i][tables[i].Length - 2][tables[i][0].Length - 2] < tables[num][tables[num].Length - 2][tables[num][0].Length - 2])
                    num = i;
                else
                    if (tables[i][tables[i].Length - 2][tables[i][0].Length - 2] == tables[num][tables[num].Length - 2][tables[num][0].Length - 2])
                    {
                        int summ1 = 0,
                            summ2 = 0;
                        for (int j = 0; j < tables[i].Length - 1; j++)
                        {
                            summ1 += tables[i][j][tables[i][0].Length - 1];
                            summ2 += tables[num][j][tables[num][0].Length - 1];
                        }
                        if (summ1 < summ2)
                            num = i;
                        else
                            if (summ1 == summ2)
                            {
                                summ1 = 0;
                                summ2 = 0;
                                for (int j = 0; j < tables[i].Length - 1; j++)
                                {
                                    summ1 += tables[i][tables[i].Length - 1][j];
                                    summ2 += tables[num][tables[num].Length - 1][j];
                                }
                                if (summ1 < summ2)
                                    num = i;
                            }
                    }
            }
            return num;
        }

        public static int[][][] allTables(int[][] table)
        {
            int[] P1 = countP1(table),
                P2 = countP2(table),
                lambda = countLambda(P1, P2);
            int[][] D1 = countD1(table, lambda),
                D0 = countD0(table, lambda),
                D2 = countD2(table, lambda);
            int[][] sequences = { rule1(P1, P2, merge(D0, D1), D2, table), rule2(lambda), rule3(P1, P2, D0, D1, D2, table), rule4(P1, P2, lambda, D0, D1, D2, table) };
            int[][][] res = {formTable(table, sequences[0]), formTable(table, sequences[1]),formTable(table, sequences[2]),formTable(table, sequences[3])};
            return res;
        }

        public static int[][] dataTable(int[][] table)
        {
            List<int[]> list = new List<int[]>();
            int[] P1 = countP1(table),
                P2 = countP2(table),
                lambda = countLambda(P1, P2);
            int[][] D1 = countD1(table, lambda),
                D0 = countD0(table, lambda),
                D2 = countD2(table, lambda);
            list.Add(countP1(table));
            list.Add(countP2(table));
            list.Add(countLambda(countP1(table), countP2(table)));
            int[][] sequences = { rule1(P1, P2, merge(D0, D1), D2, table), rule2(lambda), rule3(P1, P2, D0, D1, D2, table), rule4(P1, P2, lambda, D0, D1, D2, table) };
            list.Add(sequences[0]);
            list.Add(sequences[1]);
            list.Add(sequences[2]);
            list.Add(sequences[3]);
            return list.ToArray();
        }

        #region Формирование последовательностей
        public static int countPi1(int[] mass)
        {
            int summ = 0;
            for (int i = 0; i < ((mass.Length % 2 == 0) ? mass.Length / 2 : (mass.Length + 1) / 2); i++)
                summ += mass[i];
            return summ;
        }

        public static int countPi2(int[] mass)
        {
            int summ = 0;
            for (int i = ((mass.Length % 2 == 0) ? mass.Length / 2 + 1 : (mass.Length + 1) / 2); i <= mass.Length; i++)
                summ += mass[i - 1];
            return summ;
        }

        public static int[] countP1(int[][] table)
        {
            int[] res = new int[table.Length];
            for (int i = 0; i < table.Length; i++)
                res[i] = countPi1(table[i]);
            return res;
        }

        public static int[] countP2(int[][] table)
        {
            int[] res = new int[table.Length];
            for (int i = 0; i < table.Length; i++)
                res[i] = countPi2(table[i]);
            return res;
        }

        public static int[] countLambda(int[] P1, int[] P2)
        {
            int[] res = new int[P1.Length];
            for (int i = 0; i < res.Length; i++)
                res[i] = P2[i] - P1[i];
            return res;
        }

        public static int[][] countD0(int[][] table, int[] lambda)
        {
            List<int[]> res = new List<int[]>();
            for (int i = 0; i < lambda.Length; i++)
                if (lambda[i] == 0)
                    res.Add(table[i]);
            return res.ToArray();
        }

        public static int[][] countD1(int[][] table, int[] lambda)
        {
            List<int[]> res = new List<int[]>();
            for (int i = 0; i < lambda.Length; i++)
                if (lambda[i] > 0)
                    res.Add(table[i]);
            return res.ToArray();
        }

        public static int[][] countD2(int[][] table, int[] lambda)
        {
            List<int[]> res = new List<int[]>();
            for (int i = 0; i < lambda.Length; i++)
                if (lambda[i] < 0)
                    res.Add(table[i]);
            return res.ToArray();
        }

        public static int[][] merge(int[][] D0, int[][] D1)
        {
            List<int[]> res = new List<int[]>();
            foreach (var i in D0)
                res.Add(i);
            foreach (var i in D1)
                res.Add(i);
            return res.ToArray();
        }

        public static int[] rule1(int[] P1, int[] P2, int[][] D10, int[][] D2, int[][] table)
        {
            int[] res = new int[P1.Length],
                mass1 = new int[D10.Length],
                mass2 = new int[D2.Length];
            for (int i = 0; i < mass1.Length; i++)
                mass1[i] = P1[Array.IndexOf(table, D10[i])];
            Array.Sort<int>(mass1);
            for (int i = 0; i < mass2.Length; i++)
                mass2[i] = P2[Array.IndexOf(table, D2[i])];
            Array.Sort<int>(mass2);
            Array.Reverse(mass2);
            for (int i = 0; i < res.Length; i++)
            {
                if (i < mass1.Length)
                    res[i] = Array.IndexOf(P1, mass1[i]);
                else
                    res[i] = Array.IndexOf(P2, mass2[i - mass1.Length]);
            }
            return res;
        }

        public static int[] rule2(int[] lambda)
        {
            int[] res = new int[lambda.Length],
                mass = new int[lambda.Length];
            Array.Copy(lambda, mass, lambda.Length);
            Array.Sort<int>(mass);
            Array.Reverse(mass);
            for (int i = 0; i < res.Length; i++)
                res[i] = Array.IndexOf(lambda, mass[i]);
            return res;
        }

        public static int[] rule3(int[] P1, int[] P2, int[][] D0, int[][] D1, int[][] D2, int[][] table)
        {
            int[] res = new int[P1.Length],
                mass1 = new int[D1.Length],
                mass2 = new int[D0.Length],
                mass3 = new int[D2.Length];
            for (int i = 0; i < mass1.Length; i++)
                mass1[i] = P1[Array.IndexOf(table, D1[i])];
            Array.Sort<int>(mass1);
            for (int i = 0; i < mass2.Length; i++)
                mass2[i] = P1[Array.IndexOf(table, D0[i])];
            Array.Sort<int>(mass2);
            for (int i = 0; i < mass3.Length; i++)
                mass3[i] = P2[Array.IndexOf(table, D2[i])];
            Array.Sort<int>(mass3);
            Array.Reverse(mass3);
            for (int i = 0; i < res.Length; i++)
            {
                if (i < mass1.Length)
                    res[i] = Array.IndexOf(P1, mass1[i]);
                else
                {
                    if (i >= mass1.Length && i < mass1.Length + mass2.Length)
                        res[i] = Array.IndexOf(P1, mass2[i - mass1.Length]);
                    else
                        res[i] = Array.IndexOf(P2, mass3[i - mass1.Length - mass2.Length]);
                }
            }
            return res;
        }

        public static int[] rule4(int[] P1, int[] P2, int[] lambda, int[][] D0, int[][] D1, int[][] D2, int[][] table)
        {
            int[] res = new int[P1.Length];
            int i = 0;

            for (int j = 0; j < res.Length; j++)
                res[j] = -1;

            while (i < D1.Length)
            {
                int max = int.MinValue;
                for (int j = 0; j < D1.Length; j++)
                    if (P2[Array.IndexOf(table, D1[j])] > max && !exists(res, Array.IndexOf(table, D1[j])))
                        max = P2[Array.IndexOf(table, D1[j])];
                res[i] = Array.IndexOf(P2, max);
                i++;

                if (i + 1 >= D1.Length)
                    continue;
                int min = int.MaxValue;
                for (int j = 0; j < D1.Length; j++)
                    if (P1[Array.IndexOf(table, D1[j])] < min && !exists(res, Array.IndexOf(table, D1[j])))
                        min = P1[Array.IndexOf(table, D1[j])];
                res[i] = Array.IndexOf(P1, min);
                i++;
            }

            if (D0.Length != 0)
            {
                if (D1.Length % 2 == 1)
                {
                    int min = int.MaxValue;
                    for (int j = 0; j < D0.Length; j++)
                        if (P1[Array.IndexOf(table, D0[j])] < min)
                            min = P1[Array.IndexOf(table, D0[j])];
                    res[i] = Array.IndexOf(P1, min);
                    i++;
                }

                while (i < D1.Length + D0.Length)
                {
                    int max = int.MinValue;
                    for (int j = 0; j < D0.Length; j++)
                        if (P2[Array.IndexOf(table, D0[j])] > max && !exists(res, Array.IndexOf(table, D0[j])))
                            max = P2[Array.IndexOf(table, D0[j])];
                    res[i] = Array.IndexOf(P2, max);
                    i++;

                    if (i + 1 >= D1.Length + D0.Length)
                        continue;
                    int min = int.MaxValue;
                    for (int j = 0; j < D0.Length; j++)
                        if (P1[Array.IndexOf(table, D0[j])] < min && !exists(res, Array.IndexOf(table, D0[j])))
                            min = P1[Array.IndexOf(table, D0[j])];
                    res[i] = Array.IndexOf(P1, min);
                    i++;
                }
            }

            if (D2.Length != 0)
            {
                if ((D1.Length + D0.Length) % 2 == 1)
                {
                    int min = int.MaxValue;
                    for (int j = 0; j < D2.Length; j++)
                        if (P1[Array.IndexOf(table, D2[j])] < min)
                            min = P1[Array.IndexOf(table, D2[j])];
                    res[i] = Array.IndexOf(P1, min);
                    i++;
                }

                while (i < D1.Length + D0.Length + D2.Length)
                {
                    int max = int.MinValue;
                    for (int j = 0; j < D2.Length; j++)
                        if (P2[Array.IndexOf(table, D2[j])] > max && !exists(res, Array.IndexOf(table, D2[j])))
                            max = P2[Array.IndexOf(table, D2[j])];
                    res[i] = Array.IndexOf(P2, max);
                    i++;

                    if (i + 1 >= D1.Length + D0.Length + D2.Length)
                        continue;
                    int min = int.MaxValue;
                    for (int j = 0; j < D2.Length; j++)
                        if (P1[Array.IndexOf(table, D2[j])] < min && !exists(res, Array.IndexOf(table, D2[j])))
                            min = P1[Array.IndexOf(table, D2[j])];
                    res[i] = Array.IndexOf(P1, min);
                    i++;
                }
            }

            if ((D1.Length + D0.Length + D2.Length) % 2 == 1)
            {
                int tmp = res[res.Length - 1];
                for (i = res.Length - 1; i >= 0; i -= 2)
                {
                    res[i] = res[i - 1];
                    res[i - 1] = res[i - 2];
                    if (Math.Max(lambda[res[i - 4]], lambda[res[i - 3]]) > lambda[tmp] && Math.Min(lambda[res[i]], lambda[res[i - 1]]) < lambda[tmp])
                    {
                        res[i - 2] = tmp;
                        break;
                    }
                }
            }
            return res;
        }

        private static bool exists(int[] mass, int key)
        {
            foreach (var i in mass)
                if (i == key)
                    return true;
            return false;
        }
        #endregion

        private static int[][] formTable(int[][] table, int[] sequence)
        {
            int[][] res = new int[table.Length][];
            for (int i = 0; i < res.Length; i++)
                res[i] = table[sequence[i]];
            return res;
        }

        public static int[][] matrixMethod(int[][] table)
        {
            int[][] res = new int[table.Length + 1][];
            for (int i = 0; i < res.Length - 1; i++)
            {
                res[i] = new int[table[i].Length + 1];
                for (int j = 0; j < res[0].Length - 1; j++)
                {
                    res[i][j] = 0;
                    while (table[i][j] == 0) j++;
                    int max = 0;
                    for (int k = i - 1; k >= 0; k--)
                        if (res[k][j] > max)
                            max = res[k][j];
                    for (int k = j - 1; k >= 0; k--)
                        if (res[i][k] > max)
                            max = res[i][k];
                    res[i][j] = table[i][j] + max;
                }
            }
            res[res.Length - 1] = new int[res[0].Length];
            for (int i = 0; i < res.Length - 1; i++)
            {
                int summ = 0;
                for (int j = 0; j < res[i].Length - 1; j++)
                    summ += table[i][j];
                res[i][res[i].Length - 1] = res[i][res[i].Length - 2] - summ;
            }

            for (int j = 0; j < res[0].Length - 1; j++)
            {
                int summ = 0;
                for (int i = 0; i < res.Length - 1; i++)
                {
                    summ += table[i][j];
                }
                int t;
                for (t = res.Length - 2; res[t][j] == 0; t--) ;
                res[res.Length - 1][j] = res[t][j] - summ;
            }
            return res;
        }

        public static int[][] GetSequences(int[][] table)
        {
            int[] P1 = countP1(table),
                P2 = countP2(table),
                lambda = countLambda(P1, P2);
            int[][] D1 = countD1(table, lambda),
                D0 = countD0(table, lambda),
                D2 = countD2(table, lambda);
            int[][] sequences = { rule1(P1, P2, merge(D0, D1), D2, table), rule2(lambda), rule3(P1, P2, D0, D1, D2, table), rule4(P1, P2, lambda, D0, D1, D2, table) };
            return sequences;
        }
    }
}
