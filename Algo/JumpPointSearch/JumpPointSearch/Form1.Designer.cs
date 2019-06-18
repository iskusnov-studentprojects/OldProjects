namespace JumpPointSearch
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
            this.canvas = new System.Windows.Forms.PictureBox();
            this.loadButton = new System.Windows.Forms.Button();
            this.findPathButton = new System.Windows.Forms.Button();
            this.setStartButton = new System.Windows.Forms.Button();
            this.setFinishButton = new System.Windows.Forms.Button();
            this.menuStrip1 = new System.Windows.Forms.MenuStrip();
            this.файлToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.загрузитьИзФайлаToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.создатьКартуToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.изменитьКартуToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.сохранитьКартуToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.точкиToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.установитьСтартToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.установитьФинишToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.справкаToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.toolStripSeparator1 = new System.Windows.Forms.ToolStripSeparator();
            this.выходToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.toolStripMenuItem1 = new System.Windows.Forms.ToolStripMenuItem();
            ((System.ComponentModel.ISupportInitialize)(this.canvas)).BeginInit();
            this.menuStrip1.SuspendLayout();
            this.SuspendLayout();
            // 
            // canvas
            // 
            this.canvas.Anchor = ((System.Windows.Forms.AnchorStyles)((((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Bottom) 
            | System.Windows.Forms.AnchorStyles.Left) 
            | System.Windows.Forms.AnchorStyles.Right)));
            this.canvas.BackColor = System.Drawing.Color.White;
            this.canvas.Location = new System.Drawing.Point(12, 27);
            this.canvas.Name = "canvas";
            this.canvas.Size = new System.Drawing.Size(367, 316);
            this.canvas.TabIndex = 0;
            this.canvas.TabStop = false;
            this.canvas.Paint += new System.Windows.Forms.PaintEventHandler(this.canvas_Paint);
            this.canvas.MouseClick += new System.Windows.Forms.MouseEventHandler(this.canvas_MouseClick);
            this.canvas.Resize += new System.EventHandler(this.canvas_Resize);
            // 
            // loadButton
            // 
            this.loadButton.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Right)));
            this.loadButton.Location = new System.Drawing.Point(385, 27);
            this.loadButton.Name = "loadButton";
            this.loadButton.Size = new System.Drawing.Size(120, 40);
            this.loadButton.TabIndex = 1;
            this.loadButton.Text = "Загрузить из файла";
            this.loadButton.UseVisualStyleBackColor = true;
            this.loadButton.Click += new System.EventHandler(this.loadButton_Click);
            // 
            // findPathButton
            // 
            this.findPathButton.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Right)));
            this.findPathButton.Location = new System.Drawing.Point(385, 73);
            this.findPathButton.Name = "findPathButton";
            this.findPathButton.Size = new System.Drawing.Size(120, 40);
            this.findPathButton.TabIndex = 2;
            this.findPathButton.Text = "Найти путь";
            this.findPathButton.UseVisualStyleBackColor = true;
            this.findPathButton.Click += new System.EventHandler(this.findPathButton_Click);
            // 
            // setStartButton
            // 
            this.setStartButton.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Right)));
            this.setStartButton.ForeColor = System.Drawing.Color.Blue;
            this.setStartButton.Location = new System.Drawing.Point(386, 120);
            this.setStartButton.Name = "setStartButton";
            this.setStartButton.Size = new System.Drawing.Size(119, 23);
            this.setStartButton.TabIndex = 3;
            this.setStartButton.Text = "Установть старт";
            this.setStartButton.UseVisualStyleBackColor = true;
            this.setStartButton.Click += new System.EventHandler(this.setStartButton_Click);
            // 
            // setFinishButton
            // 
            this.setFinishButton.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Right)));
            this.setFinishButton.ForeColor = System.Drawing.Color.Green;
            this.setFinishButton.Location = new System.Drawing.Point(386, 149);
            this.setFinishButton.Name = "setFinishButton";
            this.setFinishButton.Size = new System.Drawing.Size(119, 23);
            this.setFinishButton.TabIndex = 4;
            this.setFinishButton.Text = "Установить финиш";
            this.setFinishButton.UseVisualStyleBackColor = true;
            this.setFinishButton.Click += new System.EventHandler(this.button2_Click);
            // 
            // menuStrip1
            // 
            this.menuStrip1.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.файлToolStripMenuItem,
            this.точкиToolStripMenuItem,
            this.справкаToolStripMenuItem});
            this.menuStrip1.Location = new System.Drawing.Point(0, 0);
            this.menuStrip1.Name = "menuStrip1";
            this.menuStrip1.Size = new System.Drawing.Size(517, 24);
            this.menuStrip1.TabIndex = 5;
            this.menuStrip1.Text = "menuStrip1";
            // 
            // файлToolStripMenuItem
            // 
            this.файлToolStripMenuItem.DropDownItems.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.загрузитьИзФайлаToolStripMenuItem,
            this.создатьКартуToolStripMenuItem,
            this.изменитьКартуToolStripMenuItem,
            this.сохранитьКартуToolStripMenuItem,
            this.toolStripMenuItem1,
            this.toolStripSeparator1,
            this.выходToolStripMenuItem});
            this.файлToolStripMenuItem.Name = "файлToolStripMenuItem";
            this.файлToolStripMenuItem.Size = new System.Drawing.Size(48, 20);
            this.файлToolStripMenuItem.Text = "Файл";
            // 
            // загрузитьИзФайлаToolStripMenuItem
            // 
            this.загрузитьИзФайлаToolStripMenuItem.Name = "загрузитьИзФайлаToolStripMenuItem";
            this.загрузитьИзФайлаToolStripMenuItem.Size = new System.Drawing.Size(181, 22);
            this.загрузитьИзФайлаToolStripMenuItem.Text = "Загрузить из файла";
            this.загрузитьИзФайлаToolStripMenuItem.Click += new System.EventHandler(this.loadButton_Click);
            // 
            // создатьКартуToolStripMenuItem
            // 
            this.создатьКартуToolStripMenuItem.Name = "создатьКартуToolStripMenuItem";
            this.создатьКартуToolStripMenuItem.Size = new System.Drawing.Size(195, 22);
            this.создатьКартуToolStripMenuItem.Text = "Создать карту";
            this.создатьКартуToolStripMenuItem.Click += new System.EventHandler(this.создатьКартуToolStripMenuItem_Click);
            // 
            // изменитьКартуToolStripMenuItem
            // 
            this.изменитьКартуToolStripMenuItem.Name = "изменитьКартуToolStripMenuItem";
            this.изменитьКартуToolStripMenuItem.Size = new System.Drawing.Size(181, 22);
            this.изменитьКартуToolStripMenuItem.Text = "Изменить карту";
            // 
            // сохранитьКартуToolStripMenuItem
            // 
            this.сохранитьКартуToolStripMenuItem.Name = "сохранитьКартуToolStripMenuItem";
            this.сохранитьКартуToolStripMenuItem.Size = new System.Drawing.Size(181, 22);
            this.сохранитьКартуToolStripMenuItem.Text = "Сохранить карту";
            // 
            // точкиToolStripMenuItem
            // 
            this.точкиToolStripMenuItem.DropDownItems.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.установитьСтартToolStripMenuItem,
            this.установитьФинишToolStripMenuItem});
            this.точкиToolStripMenuItem.Name = "точкиToolStripMenuItem";
            this.точкиToolStripMenuItem.Size = new System.Drawing.Size(53, 20);
            this.точкиToolStripMenuItem.Text = "Точки";
            // 
            // установитьСтартToolStripMenuItem
            // 
            this.установитьСтартToolStripMenuItem.Name = "установитьСтартToolStripMenuItem";
            this.установитьСтартToolStripMenuItem.Size = new System.Drawing.Size(180, 22);
            this.установитьСтартToolStripMenuItem.Text = "Установить старт";
            // 
            // установитьФинишToolStripMenuItem
            // 
            this.установитьФинишToolStripMenuItem.Name = "установитьФинишToolStripMenuItem";
            this.установитьФинишToolStripMenuItem.Size = new System.Drawing.Size(180, 22);
            this.установитьФинишToolStripMenuItem.Text = "Установить финиш";
            // 
            // справкаToolStripMenuItem
            // 
            this.справкаToolStripMenuItem.Name = "справкаToolStripMenuItem";
            this.справкаToolStripMenuItem.Size = new System.Drawing.Size(65, 20);
            this.справкаToolStripMenuItem.Text = "Справка";
            // 
            // toolStripSeparator1
            // 
            this.toolStripSeparator1.Name = "toolStripSeparator1";
            this.toolStripSeparator1.Size = new System.Drawing.Size(178, 6);
            // 
            // выходToolStripMenuItem
            // 
            this.выходToolStripMenuItem.Name = "выходToolStripMenuItem";
            this.выходToolStripMenuItem.Size = new System.Drawing.Size(181, 22);
            this.выходToolStripMenuItem.Text = "Выход";
            this.выходToolStripMenuItem.Click += new System.EventHandler(this.выходToolStripMenuItem_Click);
            // 
            // toolStripMenuItem1
            // 
            this.toolStripMenuItem1.Name = "toolStripMenuItem1";
            this.toolStripMenuItem1.Size = new System.Drawing.Size(195, 22);
            this.toolStripMenuItem1.Text = "Сохранить карту как...";
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(517, 355);
            this.Controls.Add(this.setFinishButton);
            this.Controls.Add(this.setStartButton);
            this.Controls.Add(this.findPathButton);
            this.Controls.Add(this.loadButton);
            this.Controls.Add(this.canvas);
            this.Controls.Add(this.menuStrip1);
            this.MainMenuStrip = this.menuStrip1;
            this.Name = "Form1";
            this.Text = "Form1";
            ((System.ComponentModel.ISupportInitialize)(this.canvas)).EndInit();
            this.menuStrip1.ResumeLayout(false);
            this.menuStrip1.PerformLayout();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.PictureBox canvas;
        private System.Windows.Forms.Button loadButton;
        private System.Windows.Forms.Button findPathButton;
        private System.Windows.Forms.Button setStartButton;
        private System.Windows.Forms.Button setFinishButton;
        private System.Windows.Forms.MenuStrip menuStrip1;
        private System.Windows.Forms.ToolStripMenuItem файлToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem загрузитьИзФайлаToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem создатьКартуToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem изменитьКартуToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem сохранитьКартуToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem точкиToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem установитьСтартToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem установитьФинишToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem справкаToolStripMenuItem;
        private System.Windows.Forms.ToolStripSeparator toolStripSeparator1;
        private System.Windows.Forms.ToolStripMenuItem выходToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem toolStripMenuItem1;

    }
}

