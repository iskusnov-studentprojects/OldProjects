using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Timetable
{
    class Algorithm
    {
        public static void CountDowntimes(int[] predData, int[] predDowntime, int[] data, int[] downtime)
        {
            downtime[0] = predData[0] + predDowntime[0];
            for (int i = 1; i < data.Length; i++)
            {
                downtime[i] = max(summ(predData, i) + summ(predDowntime, i) - summ(data, i - 1) - summ(downtime, i - 1), 0);
            }
        }

        public static void CountDowntimes(int[][] datas, int[][] downtimes)
        {
            for (int i = 1; i < datas.Length; i++)
                CountDowntimes(datas[i - 1], downtimes[i - 1], datas[i], downtimes[i]);
        }

        public static int summ(int[] mass, int pos)
        {
            if(pos == 0)
                return mass[0];
            return mass[pos] + summ(mass, pos - 1);
        }

        public static int summ(int[] mass)
        {
            return summ(mass, mass.Length - 1);
        }

        public static int max(int a, int b)
        {
            if (a > b)
                return a;
            else
                return b;
        }

        public static int max(int[] mass)
        {
            int n = mass[0];
            for (int i = 1; i < mass.Length; i++)
                if (mass[i] > n)
                    n = mass[i];
            return n;
        }

        private static int[] reorganizeTable(int[] column1, int[] column2)
        {
            int[] newPos = new int[column1.Length],
                tmpCol1 = copyMassive(column1),
                tmpCol2 = copyMassive(column2);
            int beg = 0, end = column1.Length - 1;
            while(beg <= end)
            {
                if (tmpCol1[minPos(tmpCol1)] == tmpCol2[minPos(tmpCol2)])
                {
                    if (tmpCol1[minPos(tmpCol2)] < tmpCol2[minPos(tmpCol1)])
                    {
                        newPos[beg] = minPos(tmpCol1);
                        tmpCol1[newPos[beg]] = int.MaxValue;
                        tmpCol2[newPos[beg]] = int.MaxValue;
                        beg++;
                    }
                    else
                    {
                        newPos[end] = minPos(tmpCol2);
                        tmpCol1[newPos[end]] = int.MaxValue;
                        tmpCol2[newPos[end]] = int.MaxValue;
                        end--;
                    }
                }
                else
                {
                    if (tmpCol1[minPos(tmpCol1)] < tmpCol2[minPos(tmpCol2)])
                    {
                        newPos[beg] = minPos(tmpCol1);
                        tmpCol1[newPos[beg]] = int.MaxValue;
                        tmpCol2[newPos[beg]] = int.MaxValue;
                        beg++;
                    }
                    else
                    {
                        newPos[end] = minPos(tmpCol2);
                        tmpCol1[newPos[end]] = int.MaxValue;
                        tmpCol2[newPos[end]] = int.MaxValue;
                        end--;
                    }
                }
            }
            return newPos;
        }

        public static int minPos(int[] mass)
        {
            int n, pos;
            n = mass[0];
            pos = 0;
            for (int i = 1; i < mass.Length; i++)
                if (mass[i] < n)
                {
                    n = mass[i];
                    pos = i;
                }
            return pos;
        }

        public static int min(int[] mass)
        {
            int n;
            n = mass[0];
            for (int i = 1; i < mass.Length; i++)
                if (mass[i] > n)
                {
                    n = mass[i];
                }
            return n;
        }

        public static int[] copyMassive(int[] mass)
        {
            int[] newMass = new int[mass.Length];
            for (int i = 0; i < newMass.Length; i++)
                newMass[i] = mass[i];
            return newMass;
        }

        private static void swapLines(int[] column1, int[] column2, int[] newPositions)
        {
            int[] newCol1 = copyMassive(column1),
                    newCol2 = copyMassive(column2);
            for (int i = 0; i < column1.Length; i++)
            {
                newCol1[i] = column1[newPositions[i]];
                newCol2[i] = column2[newPositions[i]];
            }
            for (int i = 0; i < column1.Length; i++)
            {
                column1[i] = newCol1[i];
                column2[i] = newCol2[i];
            }
        }

        private static void swapLines(int[] column1, int[] column2, int[] column3, int[] newPositions)
        {
            int[] newCol1 = copyMassive(column1),
                    newCol2 = copyMassive(column2),
                    newCol3 = copyMassive(column3);
            for (int i = 0; i < column1.Length; i++)
            {
                newCol1[i] = column1[newPositions[i]];
                newCol2[i] = column2[newPositions[i]];
                newCol3[i] = column3[newPositions[i]];
            }
            for(int i = 0; i < column1.Length; i++)
            {
                column1[i] = newCol1[i];
                column2[i] = newCol2[i];
                column3[i] = newCol3[i];
            }
        }

        public static bool JohnsonsAlgorithm(int[] column1, int[] column2)
        {
            swapLines(column1, column2, reorganizeTable(column1, column2));
            return true;
        }

        public static bool JohnsonsAlgorithm(int[] column1, int[] column2, int[] column3)
        {
            if (min(column1) >= max(column2) || min(column3) >= max(column2))
            {
                int[] mass1 = new int[column1.Length],
                    mass2 = new int[column2.Length];
                for (int i = 0; i < column1.Length; i++)
                {
                    mass1[i] = column1[i] + column2[i];
                    mass2[i] = column2[i] + column3[i];
                }
                swapLines(column1, column2, column3, reorganizeTable(mass1, mass2));
                return true;
            }
            else
                return false;
        }
    }
}
