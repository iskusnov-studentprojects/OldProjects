using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace IntL5
{
    public partial class Form2 : Form
    {
        public Akt akt;

        public Form2(Akt akt)
        {
            InitializeComponent();
            this.akt = akt;
            if (akt.head_position != "")
                textBox1.Text = akt.head_position;
            if (akt.head_fullName != "")
                textBox2.Text = akt.head_fullName;
            if (akt.responsiblePerson_position != "")
                textBox3.Text = akt.responsiblePerson_position;
            if (akt.responsiblePerson_fullName != "")
                textBox4.Text = akt.responsiblePerson_fullName;
            if (akt.memberCommission1_position != "")
                textBox5.Text = akt.memberCommission1_position;
            if (akt.memberCommission1_fullName != "")
                textBox6.Text = akt.memberCommission1_fullName;
            if (akt.memberCommission2_position != "")
                textBox7.Text = akt.memberCommission2_position;
            if (akt.memberCommission1_fullName != "")
                textBox8.Text = akt.memberCommission1_fullName;
            if (akt.memberCommission3_position != "")
                textBox9.Text = akt.memberCommission3_position;
            if (akt.memberCommission3_fullName != "")
                textBox10.Text = akt.memberCommission3_fullName;
        }

        private void confirm_Click(object sender, EventArgs e)
        {
            akt.head_position = textBox1.Text;
            akt.head_fullName = textBox2.Text;

            akt.responsiblePerson_position = textBox3.Text;
            akt.responsiblePerson_fullName = textBox4.Text;

            akt.memberCommission1_position = textBox5.Text;
            akt.memberCommission1_fullName = textBox6.Text;

            akt.memberCommission2_position = textBox7.Text;
            akt.memberCommission2_fullName = textBox8.Text;

            akt.memberCommission3_position = textBox9.Text;
            akt.memberCommission3_fullName = textBox10.Text;
            this.Close();
        }
    }
}
