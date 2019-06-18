namespace Alg_S4_L10
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
            this.loadGraphButton = new System.Windows.Forms.Button();
            this.canvas2 = new GraphVisualization.Canvas();
            this.edgesListBox = new System.Windows.Forms.ListBox();
            this.ButtonMaxFlow = new System.Windows.Forms.Button();
            this.flowCostLabel = new System.Windows.Forms.Label();
            this.numberFlowCostLabel = new System.Windows.Forms.Label();
            this.SuspendLayout();
            // 
            // canvas1
            // 
            this.canvas1.BackColor = System.Drawing.Color.White;
            this.canvas1.Location = new System.Drawing.Point(13, 13);
            this.canvas1.MinimumSize = new System.Drawing.Size(240, 240);
            this.canvas1.Name = "canvas1";
            this.canvas1.Size = new System.Drawing.Size(278, 270);
            this.canvas1.TabIndex = 0;
            // 
            // loadGraphButton
            // 
            this.loadGraphButton.Location = new System.Drawing.Point(79, 289);
            this.loadGraphButton.Name = "loadGraphButton";
            this.loadGraphButton.Size = new System.Drawing.Size(120, 23);
            this.loadGraphButton.TabIndex = 1;
            this.loadGraphButton.Text = "Загрузить граф";
            this.loadGraphButton.UseVisualStyleBackColor = true;
            this.loadGraphButton.Click += new System.EventHandler(this.LoadGraph_Click);
            // 
            // canvas2
            // 
            this.canvas2.BackColor = System.Drawing.Color.White;
            this.canvas2.Location = new System.Drawing.Point(297, 13);
            this.canvas2.MinimumSize = new System.Drawing.Size(240, 240);
            this.canvas2.Name = "canvas2";
            this.canvas2.Size = new System.Drawing.Size(278, 270);
            this.canvas2.TabIndex = 2;
            // 
            // edgesListBox
            // 
            this.edgesListBox.FormattingEnabled = true;
            this.edgesListBox.Location = new System.Drawing.Point(581, 12);
            this.edgesListBox.Name = "edgesListBox";
            this.edgesListBox.Size = new System.Drawing.Size(124, 251);
            this.edgesListBox.TabIndex = 3;
            // 
            // ButtonMaxFlow
            // 
            this.ButtonMaxFlow.Location = new System.Drawing.Point(394, 290);
            this.ButtonMaxFlow.Name = "ButtonMaxFlow";
            this.ButtonMaxFlow.Size = new System.Drawing.Size(116, 23);
            this.ButtonMaxFlow.TabIndex = 4;
            this.ButtonMaxFlow.Text = "Вычислить поток";
            this.ButtonMaxFlow.UseVisualStyleBackColor = true;
            this.ButtonMaxFlow.Click += new System.EventHandler(this.ButtonMaxFlow_Click);
            // 
            // flowCostLabel
            // 
            this.flowCostLabel.AutoSize = true;
            this.flowCostLabel.Location = new System.Drawing.Point(582, 270);
            this.flowCostLabel.Name = "flowCostLabel";
            this.flowCostLabel.Size = new System.Drawing.Size(100, 13);
            this.flowCostLabel.TabIndex = 5;
            this.flowCostLabel.Text = "Стоимость потока";
            // 
            // numberFlowCostLabel
            // 
            this.numberFlowCostLabel.AutoSize = true;
            this.numberFlowCostLabel.Location = new System.Drawing.Point(686, 270);
            this.numberFlowCostLabel.Name = "numberFlowCostLabel";
            this.numberFlowCostLabel.Size = new System.Drawing.Size(0, 13);
            this.numberFlowCostLabel.TabIndex = 6;
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(733, 326);
            this.Controls.Add(this.numberFlowCostLabel);
            this.Controls.Add(this.flowCostLabel);
            this.Controls.Add(this.ButtonMaxFlow);
            this.Controls.Add(this.edgesListBox);
            this.Controls.Add(this.canvas2);
            this.Controls.Add(this.loadGraphButton);
            this.Controls.Add(this.canvas1);
            this.Name = "Form1";
            this.Text = "Form1";
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private GraphVisualization.Canvas canvas1;
        private System.Windows.Forms.Button loadGraphButton;
        private GraphVisualization.Canvas canvas2;
        private System.Windows.Forms.ListBox edgesListBox;
        private System.Windows.Forms.Button ButtonMaxFlow;
        private System.Windows.Forms.Label flowCostLabel;
        private System.Windows.Forms.Label numberFlowCostLabel;
    }
}

