namespace Timetable
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
            this.SuspendLayout();
            // 
            // table1
            // 
            this.table1.Font = new System.Drawing.Font("Courier New", 8.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(204)));
            this.table1.FormattingEnabled = true;
            this.table1.ItemHeight = 14;
            this.table1.Location = new System.Drawing.Point(12, 42);
            this.table1.Name = "table1";
            this.table1.Size = new System.Drawing.Size(250, 200);
            this.table1.TabIndex = 0;
            // 
            // table2
            // 
            this.table2.Font = new System.Drawing.Font("Courier New", 8.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(204)));
            this.table2.FormattingEnabled = true;
            this.table2.ItemHeight = 14;
            this.table2.Location = new System.Drawing.Point(268, 42);
            this.table2.Name = "table2";
            this.table2.Size = new System.Drawing.Size(250, 200);
            this.table2.TabIndex = 1;
            // 
            // drawGraphicButton1
            // 
            this.drawGraphicButton1.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Bottom | System.Windows.Forms.AnchorStyles.Right)));
            this.drawGraphicButton1.Location = new System.Drawing.Point(142, 247);
            this.drawGraphicButton1.Name = "drawGraphicButton1";
            this.drawGraphicButton1.Size = new System.Drawing.Size(120, 23);
            this.drawGraphicButton1.TabIndex = 2;
            this.drawGraphicButton1.Text = "Нарисовать график";
            this.drawGraphicButton1.UseVisualStyleBackColor = true;
            this.drawGraphicButton1.Click += new System.EventHandler(this.drawGraphicButton1_Click);
            // 
            // drawGraphicButton2
            // 
            this.drawGraphicButton2.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Bottom | System.Windows.Forms.AnchorStyles.Right)));
            this.drawGraphicButton2.Location = new System.Drawing.Point(399, 247);
            this.drawGraphicButton2.Name = "drawGraphicButton2";
            this.drawGraphicButton2.Size = new System.Drawing.Size(119, 23);
            this.drawGraphicButton2.TabIndex = 3;
            this.drawGraphicButton2.Text = "Нарисовать график";
            this.drawGraphicButton2.UseVisualStyleBackColor = true;
            this.drawGraphicButton2.Click += new System.EventHandler(this.drawGraphicButton2_Click);
            // 
            // loadDataButton
            // 
            this.loadDataButton.Location = new System.Drawing.Point(12, 12);
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
            this.label1.Location = new System.Drawing.Point(163, 22);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(99, 13);
            this.label1.TabIndex = 5;
            this.label1.Text = "Исходные данные";
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(367, 26);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(151, 13);
            this.label2.TabIndex = 6;
            this.label2.Text = "Данные после оптимизации";
            // 
            // answerLabel1
            // 
            this.answerLabel1.AutoSize = true;
            this.answerLabel1.Location = new System.Drawing.Point(12, 247);
            this.answerLabel1.Name = "answerLabel1";
            this.answerLabel1.Size = new System.Drawing.Size(46, 13);
            this.answerLabel1.TabIndex = 7;
            this.answerLabel1.Text = "Время: ";
            // 
            // answerLabel2
            // 
            this.answerLabel2.AutoSize = true;
            this.answerLabel2.Location = new System.Drawing.Point(268, 245);
            this.answerLabel2.Name = "answerLabel2";
            this.answerLabel2.Size = new System.Drawing.Size(46, 13);
            this.answerLabel2.TabIndex = 8;
            this.answerLabel2.Text = "Время: ";
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(532, 286);
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
    }
}

