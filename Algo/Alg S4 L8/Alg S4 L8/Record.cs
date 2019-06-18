using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Runtime.Serialization;

namespace Alg_S4_L8
{
    [Serializable]
    class Record: ISerializable
    {
        int num1;
        int num2;
        int num3;
        string str1;
        string str2;

        static uint[] priority;

        public Record(int _num1, int _num2, int _num3, string _str1, string _str2)
        {
            num1 = _num1;
            num2 = _num2;
            num3 = _num3;
            str1 = _str1;
            str2 = _str2;
        }

        protected Record(SerializationInfo info, StreamingContext context)
        {
            if (info == null)
                throw new System.ArgumentNullException("info");
            num1 = (int)info.GetValue("Number1", typeof(int));
            num2 = (int)info.GetValue("Number2", typeof(int));
            num3 = (int)info.GetValue("Number3", typeof(int));
            str1 = (string)info.GetValue("String1", typeof(string));
            str2 = (string)info.GetValue("String2", typeof(string));
        }

        public void SetPriority(uint first, uint second, uint third)
        {
            if (first == second || first == third || second == third || first > 5 || second > 5 || third > 5 || first < 1 || second < 1 || third < 1)
                throw new Exception("Приоритеты некорректны.");
            priority = new uint[3];
            priority[0] = first;
            priority[1] = second;
            priority[2] = third;
        }

        public void SetPriority(uint[] _priority)
        {
            if (_priority[0] == _priority[1] || _priority[0] == _priority[2] || _priority[1] == _priority[2] || _priority[0] > 4 || _priority[1] > 4 || _priority[2] > 4)
                throw new Exception("Приоритеты некорректны.");
            priority = _priority;
        }

        #region Compare
        public static bool operator ==(Record obj1, Record obj2)
        {
            return obj1.num1 == obj2.num1 && obj1.num2 == obj2.num2 && obj1.num3 == obj2.num3 && obj1.str1 == obj2.str1 && obj1.str2 == obj2.str2;
        }

        public static bool operator !=(Record obj1, Record obj2)
        {
            return !(obj1 == obj2);
        }

        public static bool operator <(Record obj1, Record obj2)
        {
            if (priority == null)
                throw new Exception("Сравнение невозможно. Не указаны приоритеты сравнения.");
            //Сравнение по первому приоритету
            switch (priority[0])
            {
                case 0: if (obj1.num1 == obj2.num1) break;
                    if (obj1.num1 < obj2.num1)
                        return true;
                    else
                        return false;
                case 1: if (obj1.num2 == obj2.num2) break;
                    if (obj1.num2 < obj2.num2)
                        return true;
                    else
                        return false;
                case 2: if (obj1.num3 == obj2.num3) break;
                    if (obj1.num3 < obj2.num3)
                        return true;
                    else
                        return false;
                case 3: if (obj1.str1 == obj2.str1) break;
                    if (string.Compare(obj1.str1, obj2.str1) < 0)
                        return true;
                    else
                        return false;
                case 4: if (obj1.str2 == obj2.str2) break;
                    if (string.Compare(obj1.str2, obj2.str2) < 0)
                        return true;
                    else
                        return false;
            }
            //Сравнение по второму приоритету
            switch (priority[1])
            {
                case 0: if (obj1.num1 == obj2.num1) break;
                    if (obj1.num1 < obj2.num1)
                        return true;
                    else
                        return false;
                case 1: if (obj1.num2 == obj2.num2) break;
                    if (obj1.num2 < obj2.num2)
                        return true;
                    else
                        return false;
                case 2: if (obj1.num3 == obj2.num3) break;
                    if (obj1.num3 < obj2.num3)
                        return true;
                    else
                        return false;
                case 3: if (obj1.str1 == obj2.str1) break;
                    if (string.Compare(obj1.str1, obj2.str1) < 0)
                        return true;
                    else
                        return false;
                case 4: if (obj1.str2 == obj2.str2) break;
                    if (string.Compare(obj1.str2, obj2.str2) < 0)
                        return true;
                    else
                        return false;
            }
            //Сравнение по третьему приоритету
            switch (priority[2])
            {
                case 0: if (obj1.num1 < obj2.num1)
                        return true;
                    else
                        return false;
                case 1: if (obj1.num2 < obj2.num2)
                        return true;
                    else
                        return false;
                case 2: if (obj1.num3 < obj2.num3)
                        return true;
                    else
                        return false;
                case 3: if (string.Compare(obj1.str1,obj2.str1) < 0)
                        return true;
                    else
                        return false;
                case 4: if (string.Compare(obj1.str2, obj2.str2) < 0)
                        return true;
                    else
                        return false;
            }
            return false;
        }

        public static bool operator >(Record obj1, Record obj2)
        {
            return !(obj1 < obj2) && obj1 != obj2;
        }

        public static bool operator <=(Record obj1, Record obj2)
        {
            return obj1 < obj2 || obj1 == obj2;
        }

        public static bool operator >=(Record obj1, Record obj2)
        {
            return obj1 > obj2 || obj1 == obj2;
        }

        public static int Compare(Record obj1, Record obj2)
        {
            if (obj1 > obj2) return 1;
            if (obj1 == obj2) return 0;
            return -1;
        }

        public static int Compare(Record obj1, Record obj2, uint[] _priority)
        {
            obj1.SetPriority(_priority);
            obj2.SetPriority(_priority);
            return Compare(obj1, obj2);
        }
        #endregion

        public virtual void GetObjectData(SerializationInfo info, StreamingContext context)
        {
            if(info == null)
                throw new System.ArgumentNullException("info");
            info.AddValue("Number1", num1);
            info.AddValue("Number2", num2);
            info.AddValue("Number3", num3);
            info.AddValue("String1", str1);
            info.AddValue("String2", str2);
        }

        public override string ToString()
        {
            return num1.ToString() + '-' + num2.ToString() + '-' + num3.ToString() + '-' + str1 + '-' + str2;
        }
    }
}
