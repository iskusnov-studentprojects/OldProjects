namespace Alg_S4_L7
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
            this.ButtonMaxFlow = new System.Windows.Forms.Button();
            this.canvas2 = new GraphVisualization.Canvas();
            this.canvas1 = new GraphVisualization.Canvas();
            this.SuspendLayout();
            // 
            // LoadGraph
            // 
            this.LoadGraph.Location = new System.Drawing.Point(78, 259);
            this.LoadGraph.Name = "LoadGraph";
            this.LoadGraph.Size = new System.Drawing.Size(105, 23);
            this.LoadGraph.TabIndex = 1;
            this.LoadGraph.Text = "Загрузить граф";
            this.LoadGraph.UseVisualStyleBackColor = true;
            this.LoadGraph.Click += new System.EventHandler(this.LoadGraph_Click);
            // 
            // ButtonMaxFlow
            // 
            this.ButtonMaxFlow.Location = new System.Drawing.Point(311, 259);
            this.ButtonMaxFlow.Name = "ButtonMaxFlow";
            this.ButtonMaxFlow.Size = new System.Drawing.Size(151, 38);
            this.ButtonMaxFlow.TabIndex = 3;
            this.ButtonMaxFlow.Text = "Вычислить максимальный поток";
            this.ButtonMaxFlow.UseVisualStyleBackColor = true;
            this.ButtonMaxFlow.Click += new System.EventHandler(this.ButtonMaxFlow_Click);
            // 
            // canvas2
            // 
            this.canvas2.Location = new System.Drawing.Point(259, 12);
            this.canvas2.MinimumSize = new System.Drawing.Size(240, 240);
            this.canvas2.Name = "canvas2";
            this.canvas2.Size = new System.Drawing.Size(240, 240);
            this.canvas2.TabIndex = 4;
            // 
            // canvas1
            // 
            this.canvas1.Location = new System.Drawing.Point(13, 13);
            this.canvas1.MinimumSize = new System.Drawing.Size(240, 240);
            this.canvas1.Name = "canvas1";
            this.canvas1.Size = new System.Drawing.Size(240, 240);
            this.canvas1.TabIndex = 5;
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(521, 308);
            this.Controls.Add(this.canvas1);
            this.Controls.Add(this.canvas2);
            this.Controls.Add(this.ButtonMaxFlow);
            this.Controls.Add(this.LoadGraph);
            this.Name = "Form1";
            this.Text = "Form1";
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.Button LoadGraph;
        private System.Windows.Forms.Button ButtonMaxFlow;
        private GraphVisualization.Canvas canvas2;
        private GraphVisualization.Canvas canvas1;
    }
}

