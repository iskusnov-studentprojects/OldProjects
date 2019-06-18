namespace ORandGT
{
    partial class Form1
    {
        /// <summary>
        /// Требуется переменная конструктора.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Освободить все используемые ресурсы.
        /// </summary>
        /// <param name="disposing">истинно, если управляемый ресурс должен быть удален; иначе ложно.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Код, автоматически созданный конструктором форм Windows

        /// <summary>
        /// Обязательный метод для поддержки конструктора - не изменяйте
        /// содержимое данного метода при помощи редактора кода.
        /// </summary>
        private void InitializeComponent()
        {
            this.mission = new System.Windows.Forms.Button();
            this.operatorsNumberTextBox = new System.Windows.Forms.TextBox();
            this.operatorsNumberLabel = new System.Windows.Forms.Label();
            this.serviceTimeLabel = new System.Windows.Forms.Label();
            this.lambdaLabel = new System.Windows.Forms.Label();
            this.serviceTimeTextBox = new System.Windows.Forms.TextBox();
            this.lambdaTextBox = new System.Windows.Forms.TextBox();
            this.allFreeLabel = new System.Windows.Forms.Label();
            this.allFreeResLabel = new System.Windows.Forms.Label();
            this.units1 = new System.Windows.Forms.Label();
            this.allBusyLabel = new System.Windows.Forms.Label();
            this.units2 = new System.Windows.Forms.Label();
            this.allBusyResLabel = new System.Windows.Forms.Label();
            this.systemOptionsLabel = new System.Windows.Forms.Label();
            this.ALabel = new System.Windows.Forms.Label();
            this.tLabel = new System.Windows.Forms.Label();
            this.kLabel = new System.Windows.Forms.Label();
            this.qLabel = new System.Windows.Forms.Label();
            this.systemOptionsPanel = new System.Windows.Forms.Panel();
            this.AResLabel = new System.Windows.Forms.Label();
            this.qResLabel = new System.Windows.Forms.Label();
            this.kResLabel = new System.Windows.Forms.Label();
            this.tResLabel = new System.Windows.Forms.Label();
            this.SMOPanel = new System.Windows.Forms.Panel();
            this.panel1 = new System.Windows.Forms.Panel();
            this.label12 = new System.Windows.Forms.Label();
            this.profitLabel = new System.Windows.Forms.Label();
            this.label = new System.Windows.Forms.Label();
            this.label10 = new System.Windows.Forms.Label();
            this.label9 = new System.Windows.Forms.Label();
            this.label8 = new System.Windows.Forms.Label();
            this.profitPerCallTextBox = new System.Windows.Forms.TextBox();
            this.lossesPerCallTextBox = new System.Windows.Forms.TextBox();
            this.label7 = new System.Windows.Forms.Label();
            this.label6 = new System.Windows.Forms.Label();
            this.timeTextBox = new System.Windows.Forms.TextBox();
            this.label5 = new System.Windows.Forms.Label();
            this.label4 = new System.Windows.Forms.Label();
            this.label3 = new System.Windows.Forms.Label();
            this.runningTimeTextBox = new System.Windows.Forms.TextBox();
            this.label2 = new System.Windows.Forms.Label();
            this.streamConstTextBox = new System.Windows.Forms.TextBox();
            this.label1 = new System.Windows.Forms.Label();
            this.systemOptionsPanel.SuspendLayout();
            this.SMOPanel.SuspendLayout();
            this.panel1.SuspendLayout();
            this.SuspendLayout();
            // 
            // mission
            // 
            this.mission.Location = new System.Drawing.Point(277, 101);
            this.mission.Name = "mission";
            this.mission.Size = new System.Drawing.Size(75, 23);
            this.mission.TabIndex = 0;
            this.mission.TabStop = false;
            this.mission.Text = "Задание";
            this.mission.UseVisualStyleBackColor = true;
            this.mission.Click += new System.EventHandler(this.mission_Click);
            // 
            // operatorsNumberTextBox
            // 
            this.operatorsNumberTextBox.Location = new System.Drawing.Point(182, 9);
            this.operatorsNumberTextBox.Name = "operatorsNumberTextBox";
            this.operatorsNumberTextBox.Size = new System.Drawing.Size(100, 20);
            this.operatorsNumberTextBox.TabIndex = 0;
            this.operatorsNumberTextBox.Text = "2";
            this.operatorsNumberTextBox.TextChanged += new System.EventHandler(this.TextChanged);
            this.operatorsNumberTextBox.KeyPress += new System.Windows.Forms.KeyPressEventHandler(this.TextChanged);
            // 
            // operatorsNumberLabel
            // 
            this.operatorsNumberLabel.AutoSize = true;
            this.operatorsNumberLabel.Location = new System.Drawing.Point(3, 12);
            this.operatorsNumberLabel.Name = "operatorsNumberLabel";
            this.operatorsNumberLabel.Size = new System.Drawing.Size(128, 13);
            this.operatorsNumberLabel.TabIndex = 2;
            this.operatorsNumberLabel.Text = "Количество операторов";
            // 
            // serviceTimeLabel
            // 
            this.serviceTimeLabel.AutoSize = true;
            this.serviceTimeLabel.Location = new System.Drawing.Point(3, 64);
            this.serviceTimeLabel.Name = "serviceTimeLabel";
            this.serviceTimeLabel.Size = new System.Drawing.Size(173, 13);
            this.serviceTimeLabel.TabIndex = 3;
            this.serviceTimeLabel.Text = "Время обработки одного звонка";
            // 
            // lambdaLabel
            // 
            this.lambdaLabel.AutoSize = true;
            this.lambdaLabel.Location = new System.Drawing.Point(3, 38);
            this.lambdaLabel.Name = "lambdaLabel";
            this.lambdaLabel.Size = new System.Drawing.Size(130, 13);
            this.lambdaLabel.TabIndex = 4;
            this.lambdaLabel.Text = "Интенсивность звонков";
            // 
            // serviceTimeTextBox
            // 
            this.serviceTimeTextBox.Location = new System.Drawing.Point(182, 61);
            this.serviceTimeTextBox.Name = "serviceTimeTextBox";
            this.serviceTimeTextBox.Size = new System.Drawing.Size(100, 20);
            this.serviceTimeTextBox.TabIndex = 2;
            this.serviceTimeTextBox.Text = "0,033333333";
            this.serviceTimeTextBox.TextChanged += new System.EventHandler(this.TextChanged);
            this.serviceTimeTextBox.KeyPress += new System.Windows.Forms.KeyPressEventHandler(this.TextChanged);
            // 
            // lambdaTextBox
            // 
            this.lambdaTextBox.Location = new System.Drawing.Point(182, 35);
            this.lambdaTextBox.Name = "lambdaTextBox";
            this.lambdaTextBox.Size = new System.Drawing.Size(100, 20);
            this.lambdaTextBox.TabIndex = 1;
            this.lambdaTextBox.Text = "40";
            this.lambdaTextBox.TextChanged += new System.EventHandler(this.TextChanged);
            this.lambdaTextBox.KeyPress += new System.Windows.Forms.KeyPressEventHandler(this.TextChanged);
            // 
            // allFreeLabel
            // 
            this.allFreeLabel.AutoSize = true;
            this.allFreeLabel.Location = new System.Drawing.Point(3, 88);
            this.allFreeLabel.Name = "allFreeLabel";
            this.allFreeLabel.Size = new System.Drawing.Size(137, 13);
            this.allFreeLabel.TabIndex = 5;
            this.allFreeLabel.Text = "Все операторы свободны";
            // 
            // allFreeResLabel
            // 
            this.allFreeResLabel.AutoSize = true;
            this.allFreeResLabel.Location = new System.Drawing.Point(145, 88);
            this.allFreeResLabel.Name = "allFreeResLabel";
            this.allFreeResLabel.Size = new System.Drawing.Size(13, 13);
            this.allFreeResLabel.TabIndex = 6;
            this.allFreeResLabel.Text = "?";
            // 
            // units1
            // 
            this.units1.AutoSize = true;
            this.units1.Location = new System.Drawing.Point(186, 88);
            this.units1.Name = "units1";
            this.units1.Size = new System.Drawing.Size(62, 13);
            this.units1.TabIndex = 7;
            this.units1.Text = "% времени";
            // 
            // allBusyLabel
            // 
            this.allBusyLabel.AutoSize = true;
            this.allBusyLabel.Location = new System.Drawing.Point(3, 111);
            this.allBusyLabel.Name = "allBusyLabel";
            this.allBusyLabel.Size = new System.Drawing.Size(124, 13);
            this.allBusyLabel.TabIndex = 8;
            this.allBusyLabel.Text = "Все операторы заняты";
            // 
            // units2
            // 
            this.units2.AutoSize = true;
            this.units2.Location = new System.Drawing.Point(186, 111);
            this.units2.Name = "units2";
            this.units2.Size = new System.Drawing.Size(62, 13);
            this.units2.TabIndex = 9;
            this.units2.Text = "% времени";
            // 
            // allBusyResLabel
            // 
            this.allBusyResLabel.AutoSize = true;
            this.allBusyResLabel.Location = new System.Drawing.Point(145, 111);
            this.allBusyResLabel.Name = "allBusyResLabel";
            this.allBusyResLabel.Size = new System.Drawing.Size(13, 13);
            this.allBusyResLabel.TabIndex = 10;
            this.allBusyResLabel.Text = "?";
            // 
            // systemOptionsLabel
            // 
            this.systemOptionsLabel.AutoSize = true;
            this.systemOptionsLabel.Location = new System.Drawing.Point(3, 0);
            this.systemOptionsLabel.Name = "systemOptionsLabel";
            this.systemOptionsLabel.Size = new System.Drawing.Size(117, 13);
            this.systemOptionsLabel.TabIndex = 11;
            this.systemOptionsLabel.Text = "Параметры системы:";
            // 
            // ALabel
            // 
            this.ALabel.AutoSize = true;
            this.ALabel.Location = new System.Drawing.Point(3, 23);
            this.ALabel.Name = "ALabel";
            this.ALabel.Size = new System.Drawing.Size(279, 13);
            this.ALabel.TabIndex = 12;
            this.ALabel.Text = "Среднее число заявок в очереди r                               =";
            // 
            // tLabel
            // 
            this.tLabel.AutoSize = true;
            this.tLabel.Location = new System.Drawing.Point(3, 93);
            this.tLabel.Name = "tLabel";
            this.tLabel.Size = new System.Drawing.Size(285, 13);
            this.tLabel.TabIndex = 13;
            this.tLabel.Text = "Среднее время прибывания заявки в системе tсист = ";
            // 
            // kLabel
            // 
            this.kLabel.AutoSize = true;
            this.kLabel.Location = new System.Drawing.Point(3, 70);
            this.kLabel.Name = "kLabel";
            this.kLabel.Size = new System.Drawing.Size(280, 13);
            this.kLabel.TabIndex = 14;
            this.kLabel.Text = "Среднее время ожидания заявки в очереди tож        =";
            // 
            // qLabel
            // 
            this.qLabel.AutoSize = true;
            this.qLabel.Location = new System.Drawing.Point(3, 46);
            this.qLabel.Name = "qLabel";
            this.qLabel.Size = new System.Drawing.Size(280, 13);
            this.qLabel.TabIndex = 15;
            this.qLabel.Text = "Среднее количество заявок в системе v                    =";
            // 
            // systemOptionsPanel
            // 
            this.systemOptionsPanel.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.systemOptionsPanel.Controls.Add(this.AResLabel);
            this.systemOptionsPanel.Controls.Add(this.qResLabel);
            this.systemOptionsPanel.Controls.Add(this.kResLabel);
            this.systemOptionsPanel.Controls.Add(this.tResLabel);
            this.systemOptionsPanel.Controls.Add(this.systemOptionsLabel);
            this.systemOptionsPanel.Controls.Add(this.qLabel);
            this.systemOptionsPanel.Controls.Add(this.ALabel);
            this.systemOptionsPanel.Controls.Add(this.kLabel);
            this.systemOptionsPanel.Controls.Add(this.tLabel);
            this.systemOptionsPanel.Location = new System.Drawing.Point(6, 137);
            this.systemOptionsPanel.Name = "systemOptionsPanel";
            this.systemOptionsPanel.Size = new System.Drawing.Size(346, 115);
            this.systemOptionsPanel.TabIndex = 16;
            // 
            // AResLabel
            // 
            this.AResLabel.AutoSize = true;
            this.AResLabel.Location = new System.Drawing.Point(294, 23);
            this.AResLabel.Name = "AResLabel";
            this.AResLabel.Size = new System.Drawing.Size(13, 13);
            this.AResLabel.TabIndex = 17;
            this.AResLabel.Text = "?";
            // 
            // qResLabel
            // 
            this.qResLabel.AutoSize = true;
            this.qResLabel.Location = new System.Drawing.Point(294, 46);
            this.qResLabel.Name = "qResLabel";
            this.qResLabel.Size = new System.Drawing.Size(13, 13);
            this.qResLabel.TabIndex = 18;
            this.qResLabel.Text = "?";
            // 
            // kResLabel
            // 
            this.kResLabel.AutoSize = true;
            this.kResLabel.Location = new System.Drawing.Point(294, 70);
            this.kResLabel.Name = "kResLabel";
            this.kResLabel.Size = new System.Drawing.Size(13, 13);
            this.kResLabel.TabIndex = 19;
            this.kResLabel.Text = "?";
            // 
            // tResLabel
            // 
            this.tResLabel.AutoSize = true;
            this.tResLabel.Location = new System.Drawing.Point(294, 93);
            this.tResLabel.Name = "tResLabel";
            this.tResLabel.Size = new System.Drawing.Size(13, 13);
            this.tResLabel.TabIndex = 20;
            this.tResLabel.Text = "?";
            // 
            // SMOPanel
            // 
            this.SMOPanel.BorderStyle = System.Windows.Forms.BorderStyle.Fixed3D;
            this.SMOPanel.Controls.Add(this.operatorsNumberLabel);
            this.SMOPanel.Controls.Add(this.systemOptionsPanel);
            this.SMOPanel.Controls.Add(this.mission);
            this.SMOPanel.Controls.Add(this.allBusyResLabel);
            this.SMOPanel.Controls.Add(this.operatorsNumberTextBox);
            this.SMOPanel.Controls.Add(this.units2);
            this.SMOPanel.Controls.Add(this.serviceTimeLabel);
            this.SMOPanel.Controls.Add(this.allBusyLabel);
            this.SMOPanel.Controls.Add(this.lambdaLabel);
            this.SMOPanel.Controls.Add(this.units1);
            this.SMOPanel.Controls.Add(this.serviceTimeTextBox);
            this.SMOPanel.Controls.Add(this.allFreeResLabel);
            this.SMOPanel.Controls.Add(this.lambdaTextBox);
            this.SMOPanel.Controls.Add(this.allFreeLabel);
            this.SMOPanel.Location = new System.Drawing.Point(12, 12);
            this.SMOPanel.Name = "SMOPanel";
            this.SMOPanel.Size = new System.Drawing.Size(363, 260);
            this.SMOPanel.TabIndex = 17;
            // 
            // panel1
            // 
            this.panel1.BorderStyle = System.Windows.Forms.BorderStyle.Fixed3D;
            this.panel1.Controls.Add(this.label12);
            this.panel1.Controls.Add(this.profitLabel);
            this.panel1.Controls.Add(this.label);
            this.panel1.Controls.Add(this.label10);
            this.panel1.Controls.Add(this.label9);
            this.panel1.Controls.Add(this.label8);
            this.panel1.Controls.Add(this.profitPerCallTextBox);
            this.panel1.Controls.Add(this.lossesPerCallTextBox);
            this.panel1.Controls.Add(this.label7);
            this.panel1.Controls.Add(this.label6);
            this.panel1.Controls.Add(this.timeTextBox);
            this.panel1.Controls.Add(this.label5);
            this.panel1.Controls.Add(this.label4);
            this.panel1.Controls.Add(this.label3);
            this.panel1.Controls.Add(this.runningTimeTextBox);
            this.panel1.Controls.Add(this.label2);
            this.panel1.Controls.Add(this.streamConstTextBox);
            this.panel1.Controls.Add(this.label1);
            this.panel1.Location = new System.Drawing.Point(382, 12);
            this.panel1.Name = "panel1";
            this.panel1.Size = new System.Drawing.Size(338, 260);
            this.panel1.TabIndex = 18;
            // 
            // label12
            // 
            this.label12.AutoSize = true;
            this.label12.Location = new System.Drawing.Point(264, 138);
            this.label12.Name = "label12";
            this.label12.Size = new System.Drawing.Size(27, 13);
            this.label12.TabIndex = 30;
            this.label12.Text = "руб.";
            // 
            // profitLabel
            // 
            this.profitLabel.AutoSize = true;
            this.profitLabel.Location = new System.Drawing.Point(155, 138);
            this.profitLabel.Name = "profitLabel";
            this.profitLabel.Size = new System.Drawing.Size(13, 13);
            this.profitLabel.TabIndex = 17;
            this.profitLabel.Text = "?";
            // 
            // label
            // 
            this.label.AutoSize = true;
            this.label.Location = new System.Drawing.Point(4, 137);
            this.label.Name = "label";
            this.label.Size = new System.Drawing.Size(39, 13);
            this.label.TabIndex = 29;
            this.label.Text = "Доход";
            // 
            // label10
            // 
            this.label10.AutoSize = true;
            this.label10.Location = new System.Drawing.Point(264, 88);
            this.label10.Name = "label10";
            this.label10.Size = new System.Drawing.Size(27, 13);
            this.label10.TabIndex = 28;
            this.label10.Text = "руб.";
            // 
            // label9
            // 
            this.label9.AutoSize = true;
            this.label9.Location = new System.Drawing.Point(264, 113);
            this.label9.Name = "label9";
            this.label9.Size = new System.Drawing.Size(27, 13);
            this.label9.TabIndex = 27;
            this.label9.Text = "руб.";
            // 
            // label8
            // 
            this.label8.AutoSize = true;
            this.label8.Location = new System.Drawing.Point(4, 113);
            this.label8.Name = "label8";
            this.label8.Size = new System.Drawing.Size(91, 13);
            this.label8.TabIndex = 26;
            this.label8.Text = "Потери за отказ";
            // 
            // profitPerCallTextBox
            // 
            this.profitPerCallTextBox.Location = new System.Drawing.Point(158, 85);
            this.profitPerCallTextBox.Name = "profitPerCallTextBox";
            this.profitPerCallTextBox.Size = new System.Drawing.Size(100, 20);
            this.profitPerCallTextBox.TabIndex = 25;
            this.profitPerCallTextBox.Text = "0";
            this.profitPerCallTextBox.TextChanged += new System.EventHandler(this.TextChanged);
            // 
            // lossesPerCallTextBox
            // 
            this.lossesPerCallTextBox.Location = new System.Drawing.Point(158, 110);
            this.lossesPerCallTextBox.Name = "lossesPerCallTextBox";
            this.lossesPerCallTextBox.Size = new System.Drawing.Size(100, 20);
            this.lossesPerCallTextBox.TabIndex = 24;
            this.lossesPerCallTextBox.Text = "0";
            this.lossesPerCallTextBox.TextChanged += new System.EventHandler(this.TextChanged);
            // 
            // label7
            // 
            this.label7.AutoSize = true;
            this.label7.Location = new System.Drawing.Point(3, 90);
            this.label7.Name = "label7";
            this.label7.Size = new System.Drawing.Size(148, 13);
            this.label7.TabIndex = 23;
            this.label7.Text = "Доход от обработки звонка";
            // 
            // label6
            // 
            this.label6.AutoSize = true;
            this.label6.Location = new System.Drawing.Point(264, 61);
            this.label6.Name = "label6";
            this.label6.Size = new System.Drawing.Size(31, 13);
            this.label6.TabIndex = 22;
            this.label6.Text = "дней";
            // 
            // timeTextBox
            // 
            this.timeTextBox.Location = new System.Drawing.Point(158, 58);
            this.timeTextBox.Name = "timeTextBox";
            this.timeTextBox.Size = new System.Drawing.Size(100, 20);
            this.timeTextBox.TabIndex = 21;
            this.timeTextBox.Text = "0";
            this.timeTextBox.TextChanged += new System.EventHandler(this.TextChanged);
            // 
            // label5
            // 
            this.label5.AutoSize = true;
            this.label5.Location = new System.Drawing.Point(3, 64);
            this.label5.Name = "label5";
            this.label5.Size = new System.Drawing.Size(103, 13);
            this.label5.TabIndex = 20;
            this.label5.Text = "Временной период";
            // 
            // label4
            // 
            this.label4.AutoSize = true;
            this.label4.Location = new System.Drawing.Point(264, 35);
            this.label4.Name = "label4";
            this.label4.Size = new System.Drawing.Size(65, 13);
            this.label4.TabIndex = 19;
            this.label4.Text = "часов/день";
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Location = new System.Drawing.Point(264, 9);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(49, 13);
            this.label3.TabIndex = 18;
            this.label3.Text = "руб./час";
            // 
            // runningTimeTextBox
            // 
            this.runningTimeTextBox.Location = new System.Drawing.Point(158, 32);
            this.runningTimeTextBox.Name = "runningTimeTextBox";
            this.runningTimeTextBox.Size = new System.Drawing.Size(100, 20);
            this.runningTimeTextBox.TabIndex = 17;
            this.runningTimeTextBox.Text = "0";
            this.runningTimeTextBox.TextChanged += new System.EventHandler(this.TextChanged);
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(3, 38);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(120, 13);
            this.label2.TabIndex = 17;
            this.label2.Text = "Длительность работы";
            // 
            // streamConstTextBox
            // 
            this.streamConstTextBox.Location = new System.Drawing.Point(158, 6);
            this.streamConstTextBox.Name = "streamConstTextBox";
            this.streamConstTextBox.Size = new System.Drawing.Size(100, 20);
            this.streamConstTextBox.TabIndex = 17;
            this.streamConstTextBox.Text = "0";
            this.streamConstTextBox.TextChanged += new System.EventHandler(this.TextChanged);
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(3, 9);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(98, 13);
            this.label1.TabIndex = 17;
            this.label1.Text = "Затраты на канал";
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(738, 286);
            this.Controls.Add(this.panel1);
            this.Controls.Add(this.SMOPanel);
            this.Name = "Form1";
            this.Text = "Form1";
            this.systemOptionsPanel.ResumeLayout(false);
            this.systemOptionsPanel.PerformLayout();
            this.SMOPanel.ResumeLayout(false);
            this.SMOPanel.PerformLayout();
            this.panel1.ResumeLayout(false);
            this.panel1.PerformLayout();
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.Button mission;
        private System.Windows.Forms.TextBox operatorsNumberTextBox;
        private System.Windows.Forms.Label operatorsNumberLabel;
        private System.Windows.Forms.Label serviceTimeLabel;
        private System.Windows.Forms.Label lambdaLabel;
        private System.Windows.Forms.TextBox serviceTimeTextBox;
        private System.Windows.Forms.TextBox lambdaTextBox;
        private System.Windows.Forms.Label allFreeLabel;
        private System.Windows.Forms.Label allFreeResLabel;
        private System.Windows.Forms.Label units1;
        private System.Windows.Forms.Label allBusyLabel;
        private System.Windows.Forms.Label units2;
        private System.Windows.Forms.Label allBusyResLabel;
        private System.Windows.Forms.Label systemOptionsLabel;
        private System.Windows.Forms.Label ALabel;
        private System.Windows.Forms.Label tLabel;
        private System.Windows.Forms.Label kLabel;
        private System.Windows.Forms.Label qLabel;
        private System.Windows.Forms.Panel systemOptionsPanel;
        private System.Windows.Forms.Label AResLabel;
        private System.Windows.Forms.Label qResLabel;
        private System.Windows.Forms.Label kResLabel;
        private System.Windows.Forms.Label tResLabel;
        private System.Windows.Forms.Panel SMOPanel;
        private System.Windows.Forms.Panel panel1;
        private System.Windows.Forms.Label label12;
        private System.Windows.Forms.Label profitLabel;
        private System.Windows.Forms.Label label;
        private System.Windows.Forms.Label label10;
        private System.Windows.Forms.Label label9;
        private System.Windows.Forms.Label label8;
        private System.Windows.Forms.TextBox profitPerCallTextBox;
        private System.Windows.Forms.TextBox lossesPerCallTextBox;
        private System.Windows.Forms.Label label7;
        private System.Windows.Forms.Label label6;
        private System.Windows.Forms.TextBox timeTextBox;
        private System.Windows.Forms.Label label5;
        private System.Windows.Forms.Label label4;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.TextBox runningTimeTextBox;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.TextBox streamConstTextBox;
        private System.Windows.Forms.Label label1;
    }
}

