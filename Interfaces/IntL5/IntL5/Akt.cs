using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace IntL5
{
    public class Akt
    {
        public class Line
        {
            public string number;
            public string dishesCode;
            public string dishesName;
            public int cost;
            public int numberBreake;
            public int summBreake;
            public int numberLost;
            public int summLost;
            public string circs;
            public string guilty;
            public string comment;
        }

        public string number;
        public string date;
        public string sinceTime;
        public string toTime;
        public string organisation;
        public string part;
        public string operation;
        public string OKUD;
        public string OKPO;
        public string partCode;
        public string OKDP;
        public string totalBreakeNum;
        public string totalBreakeSumm;
        public string totalLostNum;
        public string totalLostSumm;
        public string totalBreakeNumText;
        public string answer;
        public List<Line> table;

        public string head_position;
        public string responsiblePerson_position;
        public string memberCommission1_position;
        public string memberCommission2_position;
        public string memberCommission3_position;

        public string head_fullName;
        public string responsiblePerson_fullName;
        public string memberCommission1_fullName;
        public string memberCommission2_fullName;
        public string memberCommission3_fullName;

        public Akt()
        {
            table = new List<Line>();
        }

        public int AllLost()
        {
            int summ = 0;
            for (int i = 0; i < table.Count; i++ )
            {
                summ += table[i].numberLost;
            }
            return summ;
        }

        public int AllLostCost()
        {
            int summ = 0;
            for (int i = 0; i < table.Count; i++)
            {
                summ += table[i].numberLost*table[i].cost;
            }
            return summ;
        }

        public int AllBreakeCost()
        {
            int summ = 0;
            for (int i = 0; i < table.Count; i++)
            {
                summ += table[i].numberBreake * table[i].cost;
            }
            return summ;
        }

        public int AllBreake()
        {
            int summ = 0;
            for (int i = 0; i < table.Count; i++ )
            {
                summ += table[i].numberBreake;
            }
            return summ;
        }

        public int First10Lost()
        {
            int summ = 0;
            for (int i = 0; i < table.Count && i < 10; i++)
            {
                summ += table[i].numberLost;
            }
            return summ;
        }

        public int First10LostCost()
        {
            int summ = 0;
            for (int i = 0; i < table.Count && i < 10; i++)
            {
                summ += table[i].numberLost * table[i].cost;
            }
            return summ;
        }

        public int First10BreakeCost()
        {
            int summ = 0;
            for (int i = 0; i < table.Count && i < 10; i++)
            {
                summ += table[i].numberBreake * table[i].cost;
            }
            return summ;
        }

        public int First10Breake()
        {
            int summ = 0;
            for (int i = 0; i < table.Count && i < 10; i++)
            {
                summ += table[i].numberBreake;
            }
            return summ;
        }
    }
}
