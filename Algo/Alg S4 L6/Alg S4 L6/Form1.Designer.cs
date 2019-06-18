namespace Alg_S4_L6
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
            this.canvas1 = new GraphVisualization.Canvas();
            this.LoadGraph = new System.Windows.Forms.Button();
            this.ColorsList = new System.Windows.Forms.ListBox();
            this.Colors = new System.Windows.Forms.Label();
            this.Colorize = new System.Windows.Forms.Button();
            this.SuspendLayout();
            // 
            // canvas1
            // 
            this.canvas1.Location = new System.Drawing.Point(12, 12);
            this.canvas1.MinimumSize = new System.Drawing.Size(240, 240);
            this.canvas1.Name = "canvas1";
            this.canvas1.Size = new System.Drawing.Size(240, 240);
            this.canvas1.TabIndex = 0;
            // 
            // LoadGraph
            // 
            this.LoadGraph.Location = new System.Drawing.Point(82, 258);
            this.LoadGraph.Name = "LoadGraph";
            this.LoadGraph.Size = new System.Drawing.Size(97, 23);
            this.LoadGraph.TabIndex = 1;
            this.LoadGraph.Text = "Загрузить граф";
            this.LoadGraph.UseVisualStyleBackColor = true;
            this.LoadGraph.Click += new System.EventHandler(this.LoadGraph_Click);
            // 
            // ColorsList
            // 
            this.ColorsList.FormattingEnabled = true;
            this.ColorsList.Location = new System.Drawing.Point(258, 28);
            this.ColorsList.Name = "ColorsList";
            this.ColorsList.Size = new System.Drawing.Size(148, 147);
            this.ColorsList.TabIndex = 2;
            // 
            // Colors
            // 
            this.Colors.AutoSize = true;
            this.Colors.Location = new System.Drawing.Point(258, 12);
            this.Colors.Name = "Colors";
            this.Colors.Size = new System.Drawing.Size(47, 13);
            this.Colors.TabIndex = 3;
            this.Colors.Text = "Группы:";
            // 
            // Colorize
            // 
            this.Colorize.Location = new System.Drawing.Point(258, 182);
            this.Colorize.Name = "Colorize";
            this.Colorize.Size = new System.Drawing.Size(148, 23);
            this.Colorize.TabIndex = 4;
            this.Colorize.Text = "Раскрасить граф";
            this.Colorize.UseVisualStyleBackColor = true;
            this.Colorize.Click += new System.EventHandler(this.Colorize_Click);
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(418, 287);
            this.Controls.Add(this.Colorize);
            this.Controls.Add(this.Colors);
            this.Controls.Add(this.ColorsList);
            this.Controls.Add(this.LoadGraph);
            this.Controls.Add(this.canvas1);
            this.Name = "Form1";
            this.Text = "Form1";
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private GraphVisualization.Canvas canvas1;
        private System.Windows.Forms.Button LoadGraph;
        private System.Windows.Forms.ListBox ColorsList;
        private System.Windows.Forms.Label Colors;
        private System.Windows.Forms.Button Colorize;
    }
}

