using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace JumpPointSearch
{
    public partial class SizeDialog : Form
    {
        Rectangle size;

        public SizeDialog(Rectangle _size)
        {
            InitializeComponent();
            size = _size;
        }

        private void SizeDialog_FormClosing(object sender, FormClosingEventArgs e)
        {
            int width, height;
            try
            {
                width = Convert.ToInt32(widthTextBox.Text);
                height = Convert.ToInt32(heightTextBox.Text);
                if (width <= 0 || height <= 0)
                    e.Cancel = true;
                else
                {
                    size.Width = width;
                    size.Height = height;
                }
            }
            catch (Exception ex)
            {
                e.Cancel = true;
            }
        }
    }
}
