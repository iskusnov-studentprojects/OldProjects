using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace ORandGT
{
    public partial class Form1 : Form
    {
        #region Задание
        private string textMission = "Справочная служба одной из торговых сетей отвечает на вопросы своих клиентов по двухканальной телефонной линии, обрабатываемые двумя операторами. Те из клиентов, которые услышали сигнал \"занято\", пытаются перезвонить. Распределение заявок - пуассоновское со средней скоростью прибытия звонков 40 звонков в час; каждый диспетчер может обработать 30 звонков в час. Требуется определить характеристики системы и рассчитать:"
                                + "\n1. В течение какого времени (в %) оба оператора свободны?"
                                + "\n2. В течение какого времени (в %) оба оператора работают?"
                                + "\n3. Какова вероятность получить сигнал \"занято\", если в службе работают 2,3,4 оператора?"
                                + "\n4. Компания не хочет, чтобы более 12 % звонящих получали сигнал \"занято\"."
                                + "\n5. Сколько для этого необходимо иметь операторов?";
        #endregion

        #region Параметры системы
        int n; //Количество операторов
        double lambda; //Интенсивность потока событий
        double servtime; //Среднее время обслуживания
        double mu; //Интенсивность обратного потока событий
        double ro; //ро
        double A; //Обсолютная пропускная способность
        double q; //Относительная пропускная способность
        double k; //Среднее число занятых каналов
        double t; //Среднее время ожидания
        double wt;
        double r; //Среднее число заявок в очереди
        double gamma;
        double v;
        double wp;
        double[] p; //Вероятности событий
        #endregion

        public Form1()
        {
            InitializeComponent();
            CalculateParameters();
        }

        private void mission_Click(object sender, EventArgs e)
        {
            MessageBox.Show(textMission);
        }

        private void ReadData()
        {
            try
            {
                lambda = Convert.ToDouble(lambdaTextBox.Text);
                servtime = Convert.ToDouble(serviceTimeTextBox.Text);
                n = Convert.ToInt32(operatorsNumberTextBox.Text);
                p = new double[pow(2, n)];
            }
            catch (Exception exc)
            {
                lambda = 0;
                mu = 0;
                n = 1;
                p = new double[2];
            }
        }

        private void CalculateParameters()
        {
            ReadData();
            /*
            //Система с отказами 
            mu = 1 / servtime;
            ro = lambda / mu;
            for (int i = 0; i <= n; i++)
                p[0] += Math.Pow(ro, i) / factorial(i);
            p[0] = 1 / p[0];
            for (int i = 1; i <= n + 1; i++)
                p[i] = (Math.Pow(ro, i) * p[0]) / factorial(i);
            q = 1 - p[p.Length - 1];
            A = lambda * q;
            k = A / mu;
            wp = p[n];
            */

            //Система с бесконечной очередью
            mu = 1 / servtime;
            ro = lambda / mu;
            A = lambda;
            q = 1;
            k = ro;
            gamma = ro / n;
            for (int i = 0; i <= n + 1; i++)
            {
                if (i <= n)
                    p[0] += Math.Pow(ro, i) / factorial(i);
                else
                    p[0] += Math.Pow(ro, i) / (factorial(n) * (n - ro));
            }
            p[0] = 1 / p[0];
            for (int i = 1; i <= n + 1; i++)
            {
                if (i <= n)
                    p[i] = (Math.Pow(ro, i) * p[0]) / factorial(i);
                else
                    p[i] = (Math.Pow(ro, i) * p[0]) / (factorial(n) * pow(n, i - n));
            }
            wp = 0;
            for (int i = 0; i < n; i++)
                wp += p[i];
            wp = 1 - wp;
            r = (Math.Pow(ro, n + 1) * p[0]) / (n * factorial(n) * (1 - gamma) * (1 - gamma));
            wt = (Math.Pow(ro, n) * p[0]) / (n * mu * factorial(n) * (1 - gamma) * (1 - gamma));
            t = wt + servtime;
            v = k + r;
            OutData();
        }

        private void CalculateProfit()
        {
            double costStream = Convert.ToInt32(streamConstTextBox.Text),
                timePerDay = Convert.ToInt32(runningTimeTextBox.Text),
                time = Convert.ToInt32(timeTextBox.Text),
                profitPerCall = Convert.ToInt32(profitPerCallTextBox.Text),
                lossesPerCall = Convert.ToInt32(lossesPerCallTextBox.Text),
                allCalls = lambda * timePerDay * time,
                effectiveTime = 0, lossesTime = 0;
            for (int i = 1; i <= n; i++)
                effectiveTime += p[i];
            lossesTime = 1 - p[0] - effectiveTime + p[n];
            double profit = allCalls * (effectiveTime * profitPerCall - lossesTime * lossesPerCall) - costStream * timePerDay * time;
            profitLabel.Text = ((int)profit).ToString();
        }

        private int pow(int a, int n)
        {
            int res = 1;
            for(int i = 0; i < n; i++)
                res *= a;
            return res;
        }

        private int factorial(int n)
        {
            if (n == 0)
                return 1;
            int res = 1;
            for (int i = 1; i <= n; i++)
                res *= i;
            return res;
        }

        private void TextChanged(object sender, EventArgs e)
        {
            try
            {
                if (lambdaTextBox.Text != "" && serviceTimeTextBox.Text != "" && operatorsNumberTextBox.Text != "")
                    CalculateParameters();
                CalculateProfit();
            }
            catch(Exception exc)
            {
                return;
            }
        }

        private void OutData()
        {
            try
            {
                /*
                allFreeResLabel.Text = (p[0] * 100).ToString().Substring(0, (p[0] * 100).ToString().Length > 4 ? 4 : (p[0] * 100).ToString().Length);
                allBusyResLabel.Text = (wp * 100).ToString().Substring(0, (wp * 100).ToString().Length > 4 ? 4 : (wp * 100).ToString().Length);
                AResLabel.Text = A.ToString().Substring(0, (A.ToString().Length < 7) ? A.ToString().Length : ((int)A).ToString().Length + 5);
                qResLabel.Text = q.ToString().Substring(0, (q.ToString().Length < 7) ? A.ToString().Length : ((int)q).ToString().Length + 5);
                kResLabel.Text = k.ToString().Substring(0, (k.ToString().Length < 7) ? A.ToString().Length : ((int)k).ToString().Length + 5);
                tResLabel.Text = t.ToString().Substring(0, (t.ToString().Length < 7) ? A.ToString().Length : ((int)t).ToString().Length + 5);
                */
                allFreeResLabel.Text = (p[0] * 100).ToString().Substring(0, (p[0] * 100).ToString().Length > 4 ? 4 : (p[0] * 100).ToString().Length);
                allBusyResLabel.Text = (wp * 100).ToString().Substring(0, (wp * 100).ToString().Length > 4 ? 4 : (wp * 100).ToString().Length);
                AResLabel.Text = r.ToString().Substring(0, (r.ToString().Length < 7) ? r.ToString().Length : ((int)r).ToString().Length + 5);
                qResLabel.Text = v.ToString().Substring(0, (v.ToString().Length < 7) ? v.ToString().Length : ((int)v).ToString().Length + 5);
                kResLabel.Text = wt.ToString().Substring(0, (wt.ToString().Length < 7) ? A.ToString().Length : ((int)wt).ToString().Length + 5);
                tResLabel.Text = t.ToString().Substring(0, (t.ToString().Length < 7) ? A.ToString().Length : ((int)t).ToString().Length + 5);
            }
            catch (Exception exc)
            {
                return;
            }
        }
    }
}
