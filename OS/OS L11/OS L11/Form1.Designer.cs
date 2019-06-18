namespace OS_L11
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
            this.processesList = new System.Windows.Forms.ListBox();
            this.labelProcesses = new System.Windows.Forms.Label();
            this.labelModules = new System.Windows.Forms.Label();
            this.modulesList = new System.Windows.Forms.ListBox();
            this.refreshProcessListButton = new System.Windows.Forms.Button();
            this.comboBoxPriority = new System.Windows.Forms.ComboBox();
            this.SuspendLayout();
            // 
            // processesList
            // 
            this.processesList.FormattingEnabled = true;
            this.processesList.Location = new System.Drawing.Point(15, 25);
            this.processesList.Name = "processesList";
            this.processesList.Size = new System.Drawing.Size(158, 147);
            this.processesList.TabIndex = 0;
            this.processesList.SelectedIndexChanged += new System.EventHandler(this.processesList_SelectedIndexChanged);
            // 
            // labelProcesses
            // 
            this.labelProcesses.AutoSize = true;
            this.labelProcesses.Location = new System.Drawing.Point(12, 9);
            this.labelProcesses.Name = "labelProcesses";
            this.labelProcesses.Size = new System.Drawing.Size(62, 13);
            this.labelProcesses.TabIndex = 1;
            this.labelProcesses.Text = "Процессы:";
            // 
            // labelModules
            // 
            this.labelModules.AutoSize = true;
            this.labelModules.Location = new System.Drawing.Point(202, 8);
            this.labelModules.Name = "labelModules";
            this.labelModules.Size = new System.Drawing.Size(48, 13);
            this.labelModules.TabIndex = 2;
            this.labelModules.Text = "Модули:";
            // 
            // modulesList
            // 
            this.modulesList.FormattingEnabled = true;
            this.modulesList.Location = new System.Drawing.Point(205, 25);
            this.modulesList.Name = "modulesList";
            this.modulesList.Size = new System.Drawing.Size(151, 147);
            this.modulesList.TabIndex = 3;
            // 
            // refreshProcessListButton
            // 
            this.refreshProcessListButton.Location = new System.Drawing.Point(12, 238);
            this.refreshProcessListButton.Name = "refreshProcessListButton";
            this.refreshProcessListButton.Size = new System.Drawing.Size(102, 39);
            this.refreshProcessListButton.TabIndex = 4;
            this.refreshProcessListButton.Text = "Обновить спиок процессов";
            this.refreshProcessListButton.UseVisualStyleBackColor = true;
            this.refreshProcessListButton.Click += new System.EventHandler(this.refreshProcessListButton_Click);
            // 
            // comboBoxPriority
            // 
            this.comboBoxPriority.FormattingEnabled = true;
            this.comboBoxPriority.Items.AddRange(new object[] {
            "Idle",
            "BelowNormal",
            "Normal",
            "AboveNormal",
            "High",
            "RealTime"});
            this.comboBoxPriority.Location = new System.Drawing.Point(15, 193);
            this.comboBoxPriority.Name = "comboBoxPriority";
            this.comboBoxPriority.Size = new System.Drawing.Size(158, 21);
            this.comboBoxPriority.TabIndex = 5;
            this.comboBoxPriority.SelectedIndexChanged += new System.EventHandler(this.comboBoxPriority_SelectedIndexChanged);
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(404, 289);
            this.Controls.Add(this.comboBoxPriority);
            this.Controls.Add(this.refreshProcessListButton);
            this.Controls.Add(this.modulesList);
            this.Controls.Add(this.labelModules);
            this.Controls.Add(this.labelProcesses);
            this.Controls.Add(this.processesList);
            this.Name = "Form1";
            this.Text = "Form1";
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.ListBox processesList;
        private System.Windows.Forms.Label labelProcesses;
        private System.Windows.Forms.Label labelModules;
        private System.Windows.Forms.ListBox modulesList;
        private System.Windows.Forms.Button refreshProcessListButton;
        private System.Windows.Forms.ComboBox comboBoxPriority;
    }
}

