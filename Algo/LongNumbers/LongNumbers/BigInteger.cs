using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace LongNumbers
{
    class BigInteger
    {
        private List<int> number;
        const int BASE = 10;
        public BigInteger()
        {
            number = new List<int>();
            Set(0);
        }

        public BigInteger(int num)
        {
            number = new List<int>();
            Set(num);
        }

        public List<int> Number
        {
            set
            {
                if (number == value) return;
                number.Clear();
                foreach (var i in value)
                    number.Add(i);
            }
            get
            {
                return number;
            }
        }

        public void Set(int num)
        {
            number.Clear();
            int t = num;
            do
            {
                number.Add(t % BASE);
                t /= BASE;
            } while (t != 0);
        }

        public static BigInteger operator +(BigInteger num1, BigInteger num2)
        {
            BigInteger res = new BigInteger();
            res.Number = num1.Number;
            bool cf = false;
            int size = res.Number.Count > num2.Number.Count ? res.Number.Count:num2.Number.Count;
            for (int i = 0; i < size || cf; i++)
            {
                if(i >= res.Number.Count) res.Number.Add(0);
                res.Number[i] += (i < num2.Number.Count ? num2.Number[i] : 0) + (cf ? 1 : 0);
                if (res.Number[i] > 9)
                {
                    cf = true;
                    res.Number[i] %= BASE;
                }
                else
                    cf = false;
            }
            return res;
        }

        public static BigInteger operator +(BigInteger num1, int shortnum)
        {
            BigInteger num2 = new BigInteger();
            num2.Set(shortnum);
            BigInteger res = new BigInteger();
            res.Number = num1.Number;
            bool cf = false;
            int size = res.Number.Count > num2.Number.Count ? res.Number.Count : num2.Number.Count;
            for (int i = 0; i < size || cf; i++)
            {
                if (i >= res.Number.Count) res.Number.Add(0);
                res.Number[i] += (i < num2.Number.Count ? num2.Number[i] : 0) + (cf ? 1 : 0);
                if (res.Number[i] > 9)
                {
                    cf = true;
                    res.Number[i] %= BASE;
                }
                else
                    cf = false;
            }
            return res;
        }
        
        public static BigInteger operator -(BigInteger num1, BigInteger num2)
        {
            if (num1 < num2) throw new Exception("Невозможно выполнить операцию");
            BigInteger res = new BigInteger();
            res.Number = num1.Number;
            bool cf = false;
            int size = num2.Number.Count;
            for (int i = 0; i < size || cf; i++)
            {
                res.Number[i] -= num2.Number[i] + (cf ? 1 : 0);
                if (res.Number[i] < 0)
                {
                    res.Number[i] += BASE;
                    cf = true;
                }
                else
                    cf = false;
            }
            for (int i = res.Number.Count - 1; i >= 0; i++)
            {
                if (res.Number[i] != 0)
                    break;
                res.Number.RemoveAt(i);
            }
            return res;
        }

        public static BigInteger operator -(BigInteger num1, int shortnum)
        {
            BigInteger num2 = new BigInteger();
            num2.Set(shortnum);
            if (num1 < num2) throw new Exception("Невозможно выполнить операцию");
            BigInteger res = new BigInteger();
            res.Number = num1.Number;
            bool cf = false;
            int size = num2.Number.Count;
            for (int i = 0; i < size || cf; i++)
            {
                res.Number[i] -= num2.Number[i] + (cf ? 1 : 0);
                if (res.Number[i] < 0)
                {
                    res.Number[i] += BASE;
                    cf = true;
                }
                else
                    cf = false;
            }
            for (int i = res.Number.Count - 1; i >= 0; i++)
            {
                if (res.Number[i] != 0)
                    break;
                res.Number.RemoveAt(i);
            }
            return res;
        }

        public static BigInteger operator *(BigInteger num1, BigInteger num2)
        {
            BigInteger res = new BigInteger();
            res.Number.Clear();
            for (int i = 0; i < num1.Number.Count + num2.Number.Count; i++)
                res.Number.Add(0);
            int carry = 0;
            for (int i = 0; i < num1.Number.Count; i++)
                for (int j = 0; j < num2.Number.Count || carry != 0; j++)
                {
                    res.Number[i + j] += num1.Number[i] * (j < num2.Number.Count ? num2.Number[j] : 0) + carry;
                    carry = res.Number[i + j] / BASE;
                    res.Number[i + j] %= BASE;
                }
            for (int i = res.Number.Count - 1; i >= 0; i--)
            {
                if (res.Number[i] != 0)
                    break;
                res.Number.RemoveAt(i);
            }
            return res;
        }

        public static BigInteger operator *(BigInteger num1, int shortnum)
        {
            BigInteger num2 = new BigInteger();
            num2.Set(shortnum);
            BigInteger res = new BigInteger();
            res.Number.Clear();
            for (int i = 0; i < num1.Number.Count + num2.Number.Count; i++)
                res.Number.Add(0);
            int carry = 0;
            for (int i = 0; i < num1.Number.Count; i++)
                for (int j = 0; j < num2.Number.Count || carry != 0; j++)
                {
                    res.Number[i + j] += num1.Number[i] * (j < num2.Number.Count ? num2.Number[j] : 0) + carry;
                    carry = res.Number[i + j] / BASE;
                    res.Number[i + j] %= BASE;
                }
            for (int i = res.Number.Count - 1; i >= 0; i--)
            {
                if (res.Number[i] != 0)
                    break;
                res.Number.RemoveAt(i);
            }
            return res;
        }

        public static bool operator ==(BigInteger num1, BigInteger num2)
        {
            if (num1.Number.Count != num2.Number.Count)
                return false;
            bool f = true;
            for (int i = 0; i < num1.Number.Count; i++)
                if (num1.Number[i] != num2.Number[i])
                {
                    f = false;
                    break;
                }
            return f;
        }

        public static bool operator !=(BigInteger num1, BigInteger num2)
        {
            return !(num1 == num2);
        }

        public static bool operator >(BigInteger num1, BigInteger num2)
        {
            if (num1.Number.Count > num2.Number.Count)
                return true;
            if (num1.Number.Count < num2.Number.Count)
                return false;
            bool f = true;
            for (int i = num1.Number.Count - 1; i >= 0; i--)
                if (num1.Number[i] < num2.Number[i])
                {
                    f = false;
                    break;
                }
            return f;
        }

        public static bool operator <(BigInteger num1, BigInteger num2)
        {
            if (num1.Number.Count > num2.Number.Count)
                return false;
            if (num1.Number.Count < num2.Number.Count)
                return true;
            bool f = true;
            for (int i = num1.Number.Count - 1; i >= 0; i--)
                if (num1.Number[i] > num2.Number[i])
                {
                    f = false;
                    break;
                }
            return f;
        }

        public static bool operator >=(BigInteger num1, BigInteger num2)
        {
            return num1 == num2 || num1 > num2;
        }

        public static bool operator <=(BigInteger num1, BigInteger num2)
        {
            return num1 == num2 || num1 < num2;
        }

        public override string ToString()
        {
            string str = "";
            for (int i = number.Count - 1; i >= 0; i--)
            {
                str += number[i].ToString();
            }
            return str;
        }
    }
}
