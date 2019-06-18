namespace Alg_S4_L9
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
            this.numberRecordsTextBox = new System.Windows.Forms.TextBox();
            this.generateButton = new System.Windows.Forms.Button();
            this.priority1TextBox = new System.Windows.Forms.TextBox();
            this.priority2TextBox = new System.Windows.Forms.TextBox();
            this.priority3TextBox = new System.Windows.Forms.TextBox();
            this.sortButton = new System.Windows.Forms.Button();
            this.SuspendLayout();
            // 
            // numberRecordsTextBox
            // 
            this.numberRecordsTextBox.Location = new System.Drawing.Point(13, 13);
            this.numberRecordsTextBox.Name = "numberRecordsTextBox";
            this.numberRecordsTextBox.Size = new System.Drawing.Size(149, 20);
            this.numberRecordsTextBox.TabIndex = 0;
            // 
            // generateButton
            // 
            this.generateButton.Location = new System.Drawing.Point(168, 13);
            this.generateButton.Name = "generateButton";
            this.generateButton.Size = new System.Drawing.Size(145, 23);
            this.generateButton.TabIndex = 1;
            this.generateButton.Text = "Создать исходный файл";
            this.generateButton.UseVisualStyleBackColor = true;
            this.generateButton.Click += new System.EventHandler(this.generateButton_Click);
            // 
            // priority1TextBox
            // 
            this.priority1TextBox.Location = new System.Drawing.Point(13, 40);
            this.priority1TextBox.Name = "priority1TextBox";
            this.priority1TextBox.Size = new System.Drawing.Size(20, 20);
            this.priority1TextBox.TabIndex = 2;
            // 
            // priority2TextBox
            // 
            this.priority2TextBox.Location = new System.Drawing.Point(39, 40);
            this.priority2TextBox.Name = "priority2TextBox";
            this.priority2TextBox.Size = new System.Drawing.Size(20, 20);
            this.priority2TextBox.TabIndex = 3;
            // 
            // priority3TextBox
            // 
            this.priority3TextBox.Location = new System.Drawing.Point(65, 40);
            this.priority3TextBox.Name = "priority3TextBox";
            this.priority3TextBox.Size = new System.Drawing.Size(20, 20);
            this.priority3TextBox.TabIndex = 4;
            // 
            // sortButton
            // 
            this.sortButton.Location = new System.Drawing.Point(92, 36);
            this.sortButton.Name = "sortButton";
            this.sortButton.Size = new System.Drawing.Size(88, 23);
            this.sortButton.TabIndex = 5;
            this.sortButton.Text = "Сортировать";
            this.sortButton.UseVisualStyleBackColor = true;
            this.sortButton.Click += new System.EventHandler(this.sortButton_Click);
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(392, 265);
            this.Controls.Add(this.sortButton);
            this.Controls.Add(this.priority3TextBox);
            this.Controls.Add(this.priority2TextBox);
            this.Controls.Add(this.priority1TextBox);
            this.Controls.Add(this.generateButton);
            this.Controls.Add(this.numberRecordsTextBox);
            this.Name = "Form1";
            this.Text = "Form1";
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.TextBox numberRecordsTextBox;
        private System.Windows.Forms.Button generateButton;
        private System.Windows.Forms.TextBox priority1TextBox;
        private System.Windows.Forms.TextBox priority2TextBox;
        private System.Windows.Forms.TextBox priority3TextBox;
        private System.Windows.Forms.Button sortButton;
    }
}

