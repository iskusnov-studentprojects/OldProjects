namespace Alg_S4_L8
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
            this.numberLines = new System.Windows.Forms.TextBox();
            this.generateFileButton = new System.Windows.Forms.Button();
            this.sourceFileLabel = new System.Windows.Forms.Label();
            this.sourceFileListBox = new System.Windows.Forms.ListBox();
            this.outputFileListBox = new System.Windows.Forms.ListBox();
            this.outputFileLabel = new System.Windows.Forms.Label();
            this.sourceFileLoadButton = new System.Windows.Forms.Button();
            this.outputFileLoadButton = new System.Windows.Forms.Button();
            this.Sort = new System.Windows.Forms.Button();
            this.priority1TextBox = new System.Windows.Forms.TextBox();
            this.priority2TextBox = new System.Windows.Forms.TextBox();
            this.priority3TextBox = new System.Windows.Forms.TextBox();
            this.SuspendLayout();
            // 
            // numberLines
            // 
            this.numberLines.Location = new System.Drawing.Point(13, 13);
            this.numberLines.Name = "numberLines";
            this.numberLines.Size = new System.Drawing.Size(165, 20);
            this.numberLines.TabIndex = 0;
            this.numberLines.Text = "Введите колличество записей";
            this.numberLines.Click += new System.EventHandler(this.numberLines_Click);
            // 
            // generateFileButton
            // 
            this.generateFileButton.Location = new System.Drawing.Point(184, 12);
            this.generateFileButton.Name = "generateFileButton";
            this.generateFileButton.Size = new System.Drawing.Size(173, 23);
            this.generateFileButton.TabIndex = 1;
            this.generateFileButton.Text = "Сгенерировать исходный файл";
            this.generateFileButton.UseVisualStyleBackColor = true;
            this.generateFileButton.Click += new System.EventHandler(this.generateFileButton_Click);
            // 
            // sourceFileLabel
            // 
            this.sourceFileLabel.AutoSize = true;
            this.sourceFileLabel.Location = new System.Drawing.Point(13, 40);
            this.sourceFileLabel.Name = "sourceFileLabel";
            this.sourceFileLabel.Size = new System.Drawing.Size(168, 13);
            this.sourceFileLabel.TabIndex = 2;
            this.sourceFileLabel.Text = "Содержимое исходного файла: ";
            // 
            // sourceFileListBox
            // 
            this.sourceFileListBox.FormattingEnabled = true;
            this.sourceFileListBox.Location = new System.Drawing.Point(13, 57);
            this.sourceFileListBox.Name = "sourceFileListBox";
            this.sourceFileListBox.Size = new System.Drawing.Size(280, 186);
            this.sourceFileListBox.TabIndex = 3;
            // 
            // outputFileListBox
            // 
            this.outputFileListBox.FormattingEnabled = true;
            this.outputFileListBox.Location = new System.Drawing.Point(299, 57);
            this.outputFileListBox.Name = "outputFileListBox";
            this.outputFileListBox.Size = new System.Drawing.Size(291, 186);
            this.outputFileListBox.TabIndex = 4;
            // 
            // outputFileLabel
            // 
            this.outputFileLabel.AutoSize = true;
            this.outputFileLabel.Location = new System.Drawing.Point(296, 40);
            this.outputFileLabel.Name = "outputFileLabel";
            this.outputFileLabel.Size = new System.Drawing.Size(104, 13);
            this.outputFileLabel.TabIndex = 5;
            this.outputFileLabel.Text = "После сортировки:";
            // 
            // sourceFileLoadButton
            // 
            this.sourceFileLoadButton.Location = new System.Drawing.Point(13, 250);
            this.sourceFileLoadButton.Name = "sourceFileLoadButton";
            this.sourceFileLoadButton.Size = new System.Drawing.Size(165, 23);
            this.sourceFileLoadButton.TabIndex = 6;
            this.sourceFileLoadButton.Text = "Загрузить исходный файл";
            this.sourceFileLoadButton.UseVisualStyleBackColor = true;
            this.sourceFileLoadButton.Click += new System.EventHandler(this.sourceFileLoadButton_Click);
            // 
            // outputFileLoadButton
            // 
            this.outputFileLoadButton.Location = new System.Drawing.Point(299, 250);
            this.outputFileLoadButton.Name = "outputFileLoadButton";
            this.outputFileLoadButton.Size = new System.Drawing.Size(162, 23);
            this.outputFileLoadButton.TabIndex = 7;
            this.outputFileLoadButton.Text = "Загрузить выходной файл";
            this.outputFileLoadButton.UseVisualStyleBackColor = true;
            this.outputFileLoadButton.Click += new System.EventHandler(this.outputFileLoadButton_Click);
            // 
            // Sort
            // 
            this.Sort.Location = new System.Drawing.Point(99, 279);
            this.Sort.Name = "Sort";
            this.Sort.Size = new System.Drawing.Size(82, 23);
            this.Sort.TabIndex = 8;
            this.Sort.Text = "Сортировать";
            this.Sort.UseVisualStyleBackColor = true;
            this.Sort.Click += new System.EventHandler(this.Sort_Click);
            // 
            // priority1TextBox
            // 
            this.priority1TextBox.Location = new System.Drawing.Point(16, 279);
            this.priority1TextBox.Name = "priority1TextBox";
            this.priority1TextBox.Size = new System.Drawing.Size(21, 20);
            this.priority1TextBox.TabIndex = 9;
            // 
            // priority2TextBox
            // 
            this.priority2TextBox.Location = new System.Drawing.Point(43, 279);
            this.priority2TextBox.Name = "priority2TextBox";
            this.priority2TextBox.Size = new System.Drawing.Size(21, 20);
            this.priority2TextBox.TabIndex = 10;
            // 
            // priority3TextBox
            // 
            this.priority3TextBox.Location = new System.Drawing.Point(70, 279);
            this.priority3TextBox.Name = "priority3TextBox";
            this.priority3TextBox.Size = new System.Drawing.Size(21, 20);
            this.priority3TextBox.TabIndex = 11;
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(619, 314);
            this.Controls.Add(this.priority3TextBox);
            this.Controls.Add(this.priority2TextBox);
            this.Controls.Add(this.priority1TextBox);
            this.Controls.Add(this.Sort);
            this.Controls.Add(this.outputFileLoadButton);
            this.Controls.Add(this.sourceFileLoadButton);
            this.Controls.Add(this.outputFileLabel);
            this.Controls.Add(this.outputFileListBox);
            this.Controls.Add(this.sourceFileListBox);
            this.Controls.Add(this.sourceFileLabel);
            this.Controls.Add(this.generateFileButton);
            this.Controls.Add(this.numberLines);
            this.Name = "Form1";
            this.Text = "Form1";
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.TextBox numberLines;
        private System.Windows.Forms.Button generateFileButton;
        private System.Windows.Forms.Label sourceFileLabel;
        private System.Windows.Forms.ListBox sourceFileListBox;
        private System.Windows.Forms.ListBox outputFileListBox;
        private System.Windows.Forms.Label outputFileLabel;
        private System.Windows.Forms.Button sourceFileLoadButton;
        private System.Windows.Forms.Button outputFileLoadButton;
        private System.Windows.Forms.Button Sort;
        private System.Windows.Forms.TextBox priority1TextBox;
        private System.Windows.Forms.TextBox priority2TextBox;
        private System.Windows.Forms.TextBox priority3TextBox;
    }
}

