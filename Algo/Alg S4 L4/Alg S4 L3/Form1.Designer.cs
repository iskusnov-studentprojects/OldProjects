namespace Alg_S4_L3
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
            this.LoadGraph = new System.Windows.Forms.Button();
            this.canvas1 = new GraphVisualization.Canvas();
            this.ListCycles = new System.Windows.Forms.ListBox();
            this.InputLengthCycle = new System.Windows.Forms.TextBox();
            this.FindCycles = new System.Windows.Forms.Button();
            this.SuspendLayout();
            // 
            // LoadGraph
            // 
            this.LoadGraph.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Bottom | System.Windows.Forms.AnchorStyles.Left)));
            this.LoadGraph.Location = new System.Drawing.Point(85, 224);
            this.LoadGraph.Name = "LoadGraph";
            this.LoadGraph.Size = new System.Drawing.Size(83, 36);
            this.LoadGraph.TabIndex = 0;
            this.LoadGraph.Text = "Загрузить граф";
            this.LoadGraph.UseVisualStyleBackColor = true;
            this.LoadGraph.Click += new System.EventHandler(this.button1_Click);
            // 
            // canvas1
            // 
            this.canvas1.Anchor = ((System.Windows.Forms.AnchorStyles)((((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Bottom) 
            | System.Windows.Forms.AnchorStyles.Left) 
            | System.Windows.Forms.AnchorStyles.Right)));
            this.canvas1.Location = new System.Drawing.Point(13, 13);
            this.canvas1.Name = "canvas1";
            this.canvas1.Size = new System.Drawing.Size(233, 205);
            this.canvas1.TabIndex = 1;
            // 
            // ListCycles
            // 
            this.ListCycles.Anchor = ((System.Windows.Forms.AnchorStyles)(((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Bottom) 
            | System.Windows.Forms.AnchorStyles.Right)));
            this.ListCycles.FormattingEnabled = true;
            this.ListCycles.Location = new System.Drawing.Point(252, 13);
            this.ListCycles.Name = "ListCycles";
            this.ListCycles.Size = new System.Drawing.Size(219, 199);
            this.ListCycles.TabIndex = 2;
            // 
            // InputLengthCycle
            // 
            this.InputLengthCycle.Anchor = ((System.Windows.Forms.AnchorStyles)(((System.Windows.Forms.AnchorStyles.Bottom | System.Windows.Forms.AnchorStyles.Left) 
            | System.Windows.Forms.AnchorStyles.Right)));
            this.InputLengthCycle.Location = new System.Drawing.Point(252, 224);
            this.InputLengthCycle.Name = "InputLengthCycle";
            this.InputLengthCycle.Size = new System.Drawing.Size(48, 20);
            this.InputLengthCycle.TabIndex = 3;
            // 
            // FindCycles
            // 
            this.FindCycles.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Bottom | System.Windows.Forms.AnchorStyles.Right)));
            this.FindCycles.Location = new System.Drawing.Point(306, 224);
            this.FindCycles.Name = "FindCycles";
            this.FindCycles.Size = new System.Drawing.Size(101, 23);
            this.FindCycles.TabIndex = 4;
            this.FindCycles.Text = "Найти циклы";
            this.FindCycles.UseVisualStyleBackColor = true;
            this.FindCycles.Click += new System.EventHandler(this.FindCycles_Click);
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(483, 265);
            this.Controls.Add(this.FindCycles);
            this.Controls.Add(this.InputLengthCycle);
            this.Controls.Add(this.ListCycles);
            this.Controls.Add(this.canvas1);
            this.Controls.Add(this.LoadGraph);
            this.Name = "Form1";
            this.Text = "Form1";
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Button LoadGraph;
        private GraphVisualization.Canvas canvas1;
        private System.Windows.Forms.ListBox ListCycles;
        private System.Windows.Forms.TextBox InputLengthCycle;
        private System.Windows.Forms.Button FindCycles;
    }
}

