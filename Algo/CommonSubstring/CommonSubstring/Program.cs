using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.IO;

namespace CommonSubstring
{
    class Program
    {
        class State
        {
            public int len;
            public int link;
            public Dictionary<char, int> next;
            public State()
            {
                next = new Dictionary<char, int>();
            }
        }

        static State[] sa_init(string str)
        {
            State[] st = new State[str.Length * 2];
            for (int i = 0; i < str.Length * 2; i++)
                st[i] = new State();
            int sz, last;
            sz = last = 0;
            st[0].len = 0;
            st[0].link = -1;
            ++sz;
            for (int i = 0; i < str.Length; i++)
                sa_extend(st, str[i], ref sz, ref last);
            return st;
        }

        static void sa_extend(State[] st, char c, ref int sz, ref int last)
        {
            int cur = sz++;
            st[cur].len = st[last].len + 1;
            int p;
            for (p = last; p != -1 && !st[p].next.ContainsKey(c); p = st[p].link)
                st[p].next[c] = cur;
            if (p == -1)
                st[cur].link = 0;
            else
            {
                int q = st[p].next[c];
                if (st[p].len + 1 == st[q].len)
                    st[cur].link = q;
                else
                {
                    int clone = sz++;
                    st[clone].len = st[p].len + 1;
                    st[clone].next = st[q].next;
                    st[clone].link = st[q].link;
                    for (; p != -1 && st[p].next[c] == q; p = st[p].link)
                        st[p].next[c] = clone;
                    st[q].link = st[cur].link = clone;
                }
            }
            last = cur;
        }

        static string lcs(string s, string t)
        {
            State[] st = sa_init(s);

            int v = 0, l = 0,
                best = 0, bestpos = 0;
            for (int i = 0; i < (int)t.Length; ++i)
            {
                while (v != 0 && !st[v].next.ContainsKey(t[i]))
                {
                    v = st[v].link;
                    l = st[v].len;
                }
                if (st[v].next.ContainsKey(t[i]))
                {
                    v = st[v].next[t[i]];
                    ++l;
                }
                if (l > best)
                {
                    best = l;
                    bestpos = i;
                }
            }
            return t.Substring(bestpos - best + 1, best);
        }

        static void Main(string[] args)
        {
            StreamReader reader = new StreamReader(File.Open("input.dat", FileMode.Open));
            string st1 = reader.ReadLine(), st2 = reader.ReadLine();
            Console.WriteLine(lcs(st1, st2));
            Console.Read();
        }
    }
}
