namespace ORandGTL2
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
            this.table1 = new System.Windows.Forms.ListBox();
            this.table2 = new System.Windows.Forms.ListBox();
            this.drawGraphicButton1 = new System.Windows.Forms.Button();
            this.drawGraphicButton2 = new System.Windows.Forms.Button();
            this.loadDataButton = new System.Windows.Forms.Button();
            this.label1 = new System.Windows.Forms.Label();
            this.label2 = new System.Windows.Forms.Label();
            this.answerLabel1 = new System.Windows.Forms.Label();
            this.answerLabel2 = new System.Windows.Forms.Label();
            this.comboBox1 = new System.Windows.Forms.ComboBox();
            this.opt1 = new System.Windows.Forms.RadioButton();
            this.opt2 = new System.Windows.Forms.RadioButton();
            this.SuspendLayout();
            // 
            // table1
            // 
            this.table1.Anchor = ((System.Windows.Forms.AnchorStyles)(((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Left) 
            | System.Windows.Forms.AnchorStyles.Right)));
            this.table1.Font = new System.Drawing.Font("Courier New", 8.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(204)));
            this.table1.FormattingEnabled = true;
            this.table1.ItemHeight = 14;
            this.table1.Location = new System.Drawing.Point(12, 25);
            this.table1.Name = "table1";
            this.table1.ScrollAlwaysVisible = true;
            this.table1.Size = new System.Drawing.Size(446, 186);
            this.table1.TabIndex = 0;
            // 
            // table2
            // 
            this.table2.Anchor = ((System.Windows.Forms.AnchorStyles)(((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Left) 
            | System.Windows.Forms.AnchorStyles.Right)));
            this.table2.Font = new System.Drawing.Font("Courier New", 8.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(204)));
            this.table2.FormattingEnabled = true;
            this.table2.ItemHeight = 14;
            this.table2.Location = new System.Drawing.Point(15, 260);
            this.table2.Name = "table2";
            this.table2.ScrollAlwaysVisible = true;
            this.table2.Size = new System.Drawing.Size(443, 186);
            this.table2.TabIndex = 1;
            // 
            // drawGraphicButton1
            // 
            this.drawGraphicButton1.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Right)));
            this.drawGraphicButton1.Location = new System.Drawing.Point(338, 217);
            this.drawGraphicButton1.Name = "drawGraphicButton1";
            this.drawGraphicButton1.Size = new System.Drawing.Size(120, 23);
            this.drawGraphicButton1.TabIndex = 2;
            this.drawGraphicButton1.Text = "Нарисовать график";
            this.drawGraphicButton1.UseVisualStyleBackColor = true;
            this.drawGraphicButton1.Click += new System.EventHandler(this.drawGraphicButton1_Click);
            // 
            // drawGraphicButton2
            // 
            this.drawGraphicButton2.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Right)));
            this.drawGraphicButton2.Location = new System.Drawing.Point(339, 452);
            this.drawGraphicButton2.Name = "drawGraphicButton2";
            this.drawGraphicButton2.Size = new System.Drawing.Size(119, 23);
            this.drawGraphicButton2.TabIndex = 3;
            this.drawGraphicButton2.Text = "Нарисовать график";
            this.drawGraphicButton2.UseVisualStyleBackColor = true;
            this.drawGraphicButton2.Click += new System.EventHandler(this.drawGraphicButton2_Click);
            // 
            // loadDataButton
            // 
            this.loadDataButton.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Right)));
            this.loadDataButton.Location = new System.Drawing.Point(349, -1);
            this.loadDataButton.Name = "loadDataButton";
            this.loadDataButton.Size = new System.Drawing.Size(109, 23);
            this.loadDataButton.TabIndex = 4;
            this.loadDataButton.Text = "Загрузить данные";
            this.loadDataButton.UseVisualStyleBackColor = true;
            this.loadDataButton.Click += new System.EventHandler(this.loadDataButton_Click);
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(12, 9);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(99, 13);
            this.label1.TabIndex = 5;
            this.label1.Text = "Исходные данные";
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(12, 244);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(151, 13);
            this.label2.TabIndex = 6;
            this.label2.Text = "Данные после оптимизации";
            // 
            // answerLabel1
            // 
            this.answerLabel1.AutoSize = true;
            this.answerLabel1.Location = new System.Drawing.Point(12, 214);
            this.answerLabel1.Name = "answerLabel1";
            this.answerLabel1.Size = new System.Drawing.Size(46, 13);
            this.answerLabel1.TabIndex = 7;
            this.answerLabel1.Text = "Время: ";
            // 
            // answerLabel2
            // 
            this.answerLabel2.AutoSize = true;
            this.answerLabel2.Location = new System.Drawing.Point(12, 449);
            this.answerLabel2.Name = "answerLabel2";
            this.answerLabel2.Size = new System.Drawing.Size(46, 13);
            this.answerLabel2.TabIndex = 8;
            this.answerLabel2.Text = "Время: ";
            // 
            // comboBox1
            // 
            this.comboBox1.FormattingEnabled = true;
            this.comboBox1.Items.AddRange(new object[] {
            "Вариант 1",
            "Вариант 2",
            "Вариант 3",
            "Вариант 4"});
            this.comboBox1.Location = new System.Drawing.Point(169, 236);
            this.comboBox1.Name = "comboBox1";
            this.comboBox1.Size = new System.Drawing.Size(90, 21);
            this.comboBox1.TabIndex = 9;
            this.comboBox1.SelectedIndexChanged += new System.EventHandler(this.comboBox1_SelectedIndexChanged);
            // 
            // opt1
            // 
            this.opt1.AutoSize = true;
            this.opt1.Location = new System.Drawing.Point(118, 4);
            this.opt1.Name = "opt1";
            this.opt1.Size = new System.Drawing.Size(77, 17);
            this.opt1.TabIndex = 10;
            this.opt1.TabStop = true;
            this.opt1.Text = "Таблица 1";
            this.opt1.UseVisualStyleBackColor = true;
            this.opt1.CheckedChanged += new System.EventHandler(this.opt1_CheckedChanged);
            // 
            // opt2
            // 
            this.opt2.AutoSize = true;
            this.opt2.Location = new System.Drawing.Point(201, 4);
            this.opt2.Name = "opt2";
            this.opt2.Size = new System.Drawing.Size(77, 17);
            this.opt2.TabIndex = 11;
            this.opt2.TabStop = true;
            this.opt2.Text = "Таблица 2";
            this.opt2.UseVisualStyleBackColor = true;
            this.opt2.CheckedChanged += new System.EventHandler(this.opt2_CheckedChanged);
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(471, 485);
            this.Controls.Add(this.opt2);
            this.Controls.Add(this.opt1);
            this.Controls.Add(this.comboBox1);
            this.Controls.Add(this.answerLabel2);
            this.Controls.Add(this.answerLabel1);
            this.Controls.Add(this.label2);
            this.Controls.Add(this.label1);
            this.Controls.Add(this.loadDataButton);
            this.Controls.Add(this.drawGraphicButton2);
            this.Controls.Add(this.drawGraphicButton1);
            this.Controls.Add(this.table2);
            this.Controls.Add(this.table1);
            this.Name = "Form1";
            this.Text = "Form1";
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.ListBox table1;
        private System.Windows.Forms.ListBox table2;
        private System.Windows.Forms.Button drawGraphicButton1;
        private System.Windows.Forms.Button drawGraphicButton2;
        private System.Windows.Forms.Button loadDataButton;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.Label answerLabel1;
        private System.Windows.Forms.Label answerLabel2;
        private System.Windows.Forms.ComboBox comboBox1;
        private System.Windows.Forms.RadioButton opt1;
        private System.Windows.Forms.RadioButton opt2;
    }
}

