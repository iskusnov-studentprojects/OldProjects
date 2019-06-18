namespace OS_L10
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
            this.Picture = new System.Windows.Forms.PictureBox();
            this.ThreadsList = new System.Windows.Forms.ListBox();
            this.AddThread = new System.Windows.Forms.Button();
            this.PriorityUp = new System.Windows.Forms.Button();
            this.PriorityDown = new System.Windows.Forms.Button();
            this.SaveThreads = new System.Windows.Forms.Button();
            this.LoadThreads = new System.Windows.Forms.Button();
            this.RemoveThreads = new System.Windows.Forms.Button();
            this.LabelColor = new System.Windows.Forms.Label();
            this.LabelColorValue = new System.Windows.Forms.Label();
            this.LabelPosition = new System.Windows.Forms.Label();
            this.LabelPositionValue = new System.Windows.Forms.Label();
            this.LabelPriority = new System.Windows.Forms.Label();
            this.LabelPriorityValue = new System.Windows.Forms.Label();
            this.LabelPatamets = new System.Windows.Forms.Label();
            this.LabelThreads = new System.Windows.Forms.Label();
            ((System.ComponentModel.ISupportInitialize)(this.Picture)).BeginInit();
            this.SuspendLayout();
            // 
            // Picture
            // 
            this.Picture.Anchor = ((System.Windows.Forms.AnchorStyles)((((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Bottom) 
            | System.Windows.Forms.AnchorStyles.Left) 
            | System.Windows.Forms.AnchorStyles.Right)));
            this.Picture.BackColor = System.Drawing.Color.White;
            this.Picture.Location = new System.Drawing.Point(13, 13);
            this.Picture.Name = "Picture";
            this.Picture.Size = new System.Drawing.Size(355, 301);
            this.Picture.TabIndex = 0;
            this.Picture.TabStop = false;
            // 
            // ThreadsList
            // 
            this.ThreadsList.Anchor = ((System.Windows.Forms.AnchorStyles)(((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Bottom) 
            | System.Windows.Forms.AnchorStyles.Right)));
            this.ThreadsList.FormattingEnabled = true;
            this.ThreadsList.Location = new System.Drawing.Point(373, 29);
            this.ThreadsList.Name = "ThreadsList";
            this.ThreadsList.Size = new System.Drawing.Size(190, 186);
            this.ThreadsList.TabIndex = 1;
            this.ThreadsList.SelectedIndexChanged += new System.EventHandler(this.ThreadsList_SelectedIndexChanged);
            // 
            // AddThread
            // 
            this.AddThread.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Bottom | System.Windows.Forms.AnchorStyles.Left)));
            this.AddThread.Location = new System.Drawing.Point(13, 319);
            this.AddThread.Name = "AddThread";
            this.AddThread.Size = new System.Drawing.Size(94, 36);
            this.AddThread.TabIndex = 2;
            this.AddThread.Text = "Добавить поток";
            this.AddThread.UseVisualStyleBackColor = true;
            this.AddThread.Click += new System.EventHandler(this.AddThread_Click);
            // 
            // PriorityUp
            // 
            this.PriorityUp.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Bottom | System.Windows.Forms.AnchorStyles.Right)));
            this.PriorityUp.Location = new System.Drawing.Point(374, 278);
            this.PriorityUp.Name = "PriorityUp";
            this.PriorityUp.Size = new System.Drawing.Size(94, 36);
            this.PriorityUp.TabIndex = 3;
            this.PriorityUp.Text = "Повысить приоритет";
            this.PriorityUp.UseVisualStyleBackColor = true;
            this.PriorityUp.Click += new System.EventHandler(this.PriorityUp_Click);
            // 
            // PriorityDown
            // 
            this.PriorityDown.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Bottom | System.Windows.Forms.AnchorStyles.Right)));
            this.PriorityDown.Location = new System.Drawing.Point(474, 278);
            this.PriorityDown.Name = "PriorityDown";
            this.PriorityDown.Size = new System.Drawing.Size(89, 36);
            this.PriorityDown.TabIndex = 4;
            this.PriorityDown.Text = "Понизить приоритет";
            this.PriorityDown.UseVisualStyleBackColor = true;
            this.PriorityDown.Click += new System.EventHandler(this.PriorityDown_Click);
            // 
            // SaveThreads
            // 
            this.SaveThreads.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Bottom | System.Windows.Forms.AnchorStyles.Left)));
            this.SaveThreads.Location = new System.Drawing.Point(206, 319);
            this.SaveThreads.Name = "SaveThreads";
            this.SaveThreads.Size = new System.Drawing.Size(94, 37);
            this.SaveThreads.TabIndex = 5;
            this.SaveThreads.Text = "Сохранить потоки";
            this.SaveThreads.UseVisualStyleBackColor = true;
            this.SaveThreads.Click += new System.EventHandler(this.SaveThreads_Click);
            // 
            // LoadThreads
            // 
            this.LoadThreads.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Bottom | System.Windows.Forms.AnchorStyles.Left)));
            this.LoadThreads.Location = new System.Drawing.Point(308, 319);
            this.LoadThreads.Name = "LoadThreads";
            this.LoadThreads.Size = new System.Drawing.Size(89, 37);
            this.LoadThreads.TabIndex = 6;
            this.LoadThreads.Text = "Загрузить потоки";
            this.LoadThreads.UseVisualStyleBackColor = true;
            this.LoadThreads.Click += new System.EventHandler(this.LoadThreads_Click);
            // 
            // RemoveThreads
            // 
            this.RemoveThreads.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Bottom | System.Windows.Forms.AnchorStyles.Left)));
            this.RemoveThreads.Location = new System.Drawing.Point(113, 320);
            this.RemoveThreads.Name = "RemoveThreads";
            this.RemoveThreads.Size = new System.Drawing.Size(87, 36);
            this.RemoveThreads.TabIndex = 7;
            this.RemoveThreads.Text = "Удалить потоки";
            this.RemoveThreads.UseVisualStyleBackColor = true;
            this.RemoveThreads.Click += new System.EventHandler(this.RemoveThreads_Click);
            // 
            // LabelColor
            // 
            this.LabelColor.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Bottom | System.Windows.Forms.AnchorStyles.Right)));
            this.LabelColor.AutoSize = true;
            this.LabelColor.Location = new System.Drawing.Point(374, 236);
            this.LabelColor.Name = "LabelColor";
            this.LabelColor.Size = new System.Drawing.Size(35, 13);
            this.LabelColor.TabIndex = 8;
            this.LabelColor.Text = "Цвет:";
            // 
            // LabelColorValue
            // 
            this.LabelColorValue.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Bottom | System.Windows.Forms.AnchorStyles.Right)));
            this.LabelColorValue.AutoSize = true;
            this.LabelColorValue.Location = new System.Drawing.Point(415, 236);
            this.LabelColorValue.Name = "LabelColorValue";
            this.LabelColorValue.Size = new System.Drawing.Size(33, 13);
            this.LabelColorValue.TabIndex = 9;
            this.LabelColorValue.Text = "None";
            // 
            // LabelPosition
            // 
            this.LabelPosition.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Bottom | System.Windows.Forms.AnchorStyles.Right)));
            this.LabelPosition.AutoSize = true;
            this.LabelPosition.Location = new System.Drawing.Point(374, 249);
            this.LabelPosition.Name = "LabelPosition";
            this.LabelPosition.Size = new System.Drawing.Size(54, 13);
            this.LabelPosition.TabIndex = 10;
            this.LabelPosition.Text = "Позиция:";
            // 
            // LabelPositionValue
            // 
            this.LabelPositionValue.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Bottom | System.Windows.Forms.AnchorStyles.Right)));
            this.LabelPositionValue.AutoSize = true;
            this.LabelPositionValue.Location = new System.Drawing.Point(434, 249);
            this.LabelPositionValue.Name = "LabelPositionValue";
            this.LabelPositionValue.Size = new System.Drawing.Size(33, 13);
            this.LabelPositionValue.TabIndex = 11;
            this.LabelPositionValue.Text = "None";
            // 
            // LabelPriority
            // 
            this.LabelPriority.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Bottom | System.Windows.Forms.AnchorStyles.Right)));
            this.LabelPriority.AutoSize = true;
            this.LabelPriority.Location = new System.Drawing.Point(374, 262);
            this.LabelPriority.Name = "LabelPriority";
            this.LabelPriority.Size = new System.Drawing.Size(64, 13);
            this.LabelPriority.TabIndex = 12;
            this.LabelPriority.Text = "Приоритет:";
            // 
            // LabelPriorityValue
            // 
            this.LabelPriorityValue.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Bottom | System.Windows.Forms.AnchorStyles.Right)));
            this.LabelPriorityValue.AutoSize = true;
            this.LabelPriorityValue.Location = new System.Drawing.Point(440, 262);
            this.LabelPriorityValue.Name = "LabelPriorityValue";
            this.LabelPriorityValue.Size = new System.Drawing.Size(33, 13);
            this.LabelPriorityValue.TabIndex = 13;
            this.LabelPriorityValue.Text = "None";
            // 
            // LabelPatamets
            // 
            this.LabelPatamets.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Bottom | System.Windows.Forms.AnchorStyles.Right)));
            this.LabelPatamets.AutoSize = true;
            this.LabelPatamets.Location = new System.Drawing.Point(374, 223);
            this.LabelPatamets.Name = "LabelPatamets";
            this.LabelPatamets.Size = new System.Drawing.Size(104, 13);
            this.LabelPatamets.TabIndex = 14;
            this.LabelPatamets.Text = "Параметры потока";
            // 
            // LabelThreads
            // 
            this.LabelThreads.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Right)));
            this.LabelThreads.AutoSize = true;
            this.LabelThreads.Location = new System.Drawing.Point(375, 13);
            this.LabelThreads.Name = "LabelThreads";
            this.LabelThreads.Size = new System.Drawing.Size(44, 13);
            this.LabelThreads.TabIndex = 15;
            this.LabelThreads.Text = "Потоки";
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(584, 372);
            this.Controls.Add(this.LabelThreads);
            this.Controls.Add(this.LabelPatamets);
            this.Controls.Add(this.LabelPriorityValue);
            this.Controls.Add(this.LabelPriority);
            this.Controls.Add(this.LabelPositionValue);
            this.Controls.Add(this.LabelPosition);
            this.Controls.Add(this.LabelColorValue);
            this.Controls.Add(this.LabelColor);
            this.Controls.Add(this.RemoveThreads);
            this.Controls.Add(this.LoadThreads);
            this.Controls.Add(this.SaveThreads);
            this.Controls.Add(this.PriorityDown);
            this.Controls.Add(this.PriorityUp);
            this.Controls.Add(this.AddThread);
            this.Controls.Add(this.ThreadsList);
            this.Controls.Add(this.Picture);
            this.MinimumSize = new System.Drawing.Size(600, 400);
            this.Name = "Form1";
            this.Text = "Form1";
            this.FormClosing += new System.Windows.Forms.FormClosingEventHandler(this.Form1_FormClosing);
            ((System.ComponentModel.ISupportInitialize)(this.Picture)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.PictureBox Picture;
        private System.Windows.Forms.ListBox ThreadsList;
        private System.Windows.Forms.Button AddThread;
        private System.Windows.Forms.Button PriorityUp;
        private System.Windows.Forms.Button PriorityDown;
        private System.Windows.Forms.Button SaveThreads;
        private System.Windows.Forms.Button LoadThreads;
        private System.Windows.Forms.Button RemoveThreads;
        private System.Windows.Forms.Label LabelColor;
        private System.Windows.Forms.Label LabelColorValue;
        private System.Windows.Forms.Label LabelPosition;
        private System.Windows.Forms.Label LabelPositionValue;
        private System.Windows.Forms.Label LabelPriority;
        private System.Windows.Forms.Label LabelPriorityValue;
        private System.Windows.Forms.Label LabelPatamets;
        private System.Windows.Forms.Label LabelThreads;
    }
}

