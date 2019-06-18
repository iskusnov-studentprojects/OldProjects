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
            this.CreateFrame = new System.Windows.Forms.Button();
            this.canvas1 = new GraphVisualization.Canvas();
            this.canvas2 = new GraphVisualization.Canvas();
            this.SuspendLayout();
            // 
            // LoadGraph
            // 
            this.LoadGraph.Location = new System.Drawing.Point(72, 290);
            this.LoadGraph.Name = "LoadGraph";
            this.LoadGraph.Size = new System.Drawing.Size(108, 25);
            this.LoadGraph.TabIndex = 2;
            this.LoadGraph.Text = "Загрузить граф";
            this.LoadGraph.UseVisualStyleBackColor = true;
            this.LoadGraph.Click += new System.EventHandler(this.LoadGraph_Click);
            // 
            // CreateFrame
            // 
            this.CreateFrame.Location = new System.Drawing.Point(341, 290);
            this.CreateFrame.Name = "CreateFrame";
            this.CreateFrame.Size = new System.Drawing.Size(123, 23);
            this.CreateFrame.TabIndex = 3;
            this.CreateFrame.Text = "Построить каркас";
            this.CreateFrame.UseVisualStyleBackColor = true;
            this.CreateFrame.Click += new System.EventHandler(this.CreateFrame_Click);
            // 
            // canvas1
            // 
            this.canvas1.Location = new System.Drawing.Point(13, 13);
            this.canvas1.Name = "canvas1";
            this.canvas1.Size = new System.Drawing.Size(264, 255);
            this.canvas1.TabIndex = 4;
            // 
            // canvas2
            // 
            this.canvas2.Location = new System.Drawing.Point(283, 13);
            this.canvas2.Name = "canvas2";
            this.canvas2.Size = new System.Drawing.Size(259, 255);
            this.canvas2.TabIndex = 5;
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(554, 327);
            this.Controls.Add(this.canvas2);
            this.Controls.Add(this.canvas1);
            this.Controls.Add(this.CreateFrame);
            this.Controls.Add(this.LoadGraph);
            this.Name = "Form1";
            this.Text = "Form1";
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.Button LoadGraph;
        private System.Windows.Forms.Button CreateFrame;
        private GraphVisualization.Canvas canvas1;
        private GraphVisualization.Canvas canvas2;
    }
}

