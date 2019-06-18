using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using System.IO;
using Application = Microsoft.Office.Interop.Excel.Application;
using Workbook = Microsoft.Office.Interop.Excel.Workbook;
using Worksheet = Microsoft.Office.Interop.Excel.Worksheet;


namespace IntL5
{
    public partial class Form1 : Form
    {
        public Akt akt;
        string[] elementsCost = {"80,00", "80,00", "150,00", "200,00"};
        
        public Form1()
        {
            InitializeComponent();
            akt = new Akt();
        }

        private void bOut_Click(object sender, EventArgs e)
        {
            string template = Directory.GetCurrentDirectory() + "\\template.xls";
            if (!File.Exists(template))
            {
                MessageBox.Show("Отсутствует шаблонный файл!");
                return;
            }

            akt.number = tbNum.Text;
            akt.date = dtDate.Text;
            akt.sinceTime = dtSince.Text;
            akt.toTime = dtTo.Text;
            akt.organisation = cbOrganisation.Text;
            akt.operation = tbOperation.Text;
            akt.OKDP = cbOKDP.Text;
            akt.OKUD = tbOKUD.Text;
            akt.OKPO = cbOKPO.Text;
            akt.operation = tbOperation.Text;
            akt.part = cbPart.Text;
            akt.partCode = cbCodePart.Text;
            akt.totalBreakeNum = tbTotalBreakNum.Text;
            akt.totalBreakeSumm = tbTotalBreakSumm.Text;
            akt.totalLostNum = tbTotalLostNum.Text;
            akt.totalLostSumm = tbTotalLostSumm.Text;
            akt.totalBreakeNumText = tbTotalBreakNumText.Text;
            akt.answer = tbAnswer.Text;


            for (int i = 0; i < table.Rows.Count - 1; i++)
            {
                try
                {
                    Akt.Line line = new Akt.Line();
                    line.number = table.Rows[i].Cells[0].Value != null? table.Rows[i].Cells[0].Value.ToString(): "";
                    line.dishesCode = table.Rows[i].Cells[1].Value != null? table.Rows[i].Cells[1].Value.ToString(): "";
                    line.dishesName = table.Rows[i].Cells[2].Value != null? table.Rows[i].Cells[2].Value.ToString(): "";
                    line.cost = table.Rows[i].Cells[3].Value != null? StringToCost(table.Rows[i].Cells[3].Value.ToString()): 0;
                    line.numberBreake = table.Rows[i].Cells[4].Value != null? Convert.ToInt32(table.Rows[i].Cells[4].Value.ToString()): 0;
                    line.summBreake = table.Rows[i].Cells[5].Value != null? StringToCost(table.Rows[i].Cells[5].Value.ToString()): 0;
                    line.numberLost = table.Rows[i].Cells[6].Value != null? Convert.ToInt32(table.Rows[i].Cells[6].Value.ToString()): 0;
                    line.summLost = table.Rows[i].Cells[7].Value != null? StringToCost(table.Rows[i].Cells[7].Value.ToString()): 0;
                    line.circs = table.Rows[i].Cells[8].Value != null? table.Rows[i].Cells[8].Value.ToString(): "";
                    line.guilty = table.Rows[i].Cells[9].Value != null? table.Rows[i].Cells[9].Value.ToString(): "";
                    line.comment = table.Rows[i].Cells[10].Value != null? table.Rows[i].Cells[10].Value.ToString(): "";
                    akt.table.Add(line);
                }
                catch (Exception exc)
                {
                    MessageBox.Show(exc.Message);
                }
            }

            SaveFileDialog sfd = new SaveFileDialog();
            sfd.DefaultExt = ".xls";
            sfd.Filter = "Excel|*.xls";
            if (sfd.ShowDialog() == DialogResult.OK)
            {
                try
                {
                    if (sfd.FileName == template)
                        throw new Exception("Нельзя пересохранять шаблонный файл, веберите другой!");
                    if (File.Exists(sfd.FileName))
                        File.Delete(sfd.FileName);
                    File.Copy(template, sfd.FileName);
                }
                catch (Exception exc)
                {
                    MessageBox.Show(exc.Message);
                }

                Application application = new Application();
                application.DisplayAlerts = false;
                Workbook workbook = application.Workbooks.Open(sfd.FileName);
                Worksheet worksheet = workbook.ActiveSheet as Worksheet;

                worksheet.Range["A6"].Value = akt.organisation;
                worksheet.Range["BQ6"].Value = akt.OKPO;
                worksheet.Range["A8"].Value = akt.part;
                worksheet.Range["BQ7"].Value = akt.partCode;
                worksheet.Range["BQ9"].Value = akt.OKDP;
                worksheet.Range["BQ10"].Value = akt.operation;
                worksheet.Range["AK14"].Value = akt.number;
                worksheet.Range["AS14"].Value = akt.date;
                worksheet.Range["BA14"].Value = akt.sinceTime;
                worksheet.Range["BF14"].Value = akt.toTime;
                worksheet.Range["BM13"].Value = akt.head_position;
                worksheet.Range["BQ15"].Value = akt.head_fullName;
                worksheet.Range["U18"].Value = akt.responsiblePerson_position;
                worksheet.Range["AM18"].Value = akt.responsiblePerson_fullName;
                worksheet.Range["Z38"].Value = akt.First10Breake();
                worksheet.Range["AD38"].Value = CostToString(akt.First10BreakeCost());
                worksheet.Range["AI38"].Value = akt.First10Lost();
                worksheet.Range["AM38"].Value = CostToString(akt.First10LostCost());
                worksheet.Range["AR38"].Value = akt.First10Breake() + akt.First10Lost(); 
                worksheet.Range["AV38"].Value = CostToString(akt.First10BreakeCost() + akt.First10LostCost());
                worksheet.Range["Z59"].Value = akt.AllBreake();
                worksheet.Range["AD59"].Value =  CostToString(akt.AllBreakeCost());
                worksheet.Range["AI59"].Value = akt.AllLost();
                worksheet.Range["AM59"].Value =  CostToString(akt.AllLostCost());
                worksheet.Range["AR59"].Value = akt.AllBreake() + akt.AllLost();
                worksheet.Range["AV59"].Value = CostToString(akt.AllBreakeCost() + akt.AllLostCost());
                worksheet.Range["N65"].Value = akt.memberCommission1_position;
                worksheet.Range["AQ65"].Value = akt.memberCommission1_fullName;
                worksheet.Range["N67"].Value = akt.memberCommission2_position;
                worksheet.Range["AQ67"].Value = akt.memberCommission2_fullName;
                worksheet.Range["N69"].Value = akt.memberCommission3_position;
                worksheet.Range["AQ69"].Value = akt.memberCommission3_fullName;
                worksheet.Range["AI61"].Value = akt.totalBreakeNumText;
                worksheet.Range["N72"].Value = akt.answer;

                int line = 28;
                for (int i = 0; i < 20; i++)
                {
                    if (i < akt.table.Count)
                    {
                        worksheet.Range["A" + line.ToString()].Value = akt.table[i].number;
                        worksheet.Range["E" + line.ToString()].Value = akt.table[i].dishesName;
                        worksheet.Range["O" + line.ToString()].Value = akt.table[i].dishesCode;
                        worksheet.Range["T" + line.ToString()].Value = CostToString(akt.table[i].cost);
                        worksheet.Range["Z" + line.ToString()].Value = akt.table[i].numberBreake;
                        worksheet.Range["AD" + line.ToString()].Value = CostToString(akt.table[i].summBreake);
                        worksheet.Range["AI" + line.ToString()].Value = akt.table[i].numberLost;
                        worksheet.Range["AM" + line.ToString()].Value = CostToString(akt.table[i].summLost);
                        worksheet.Range["AR" + line.ToString()].Value = akt.table[i].numberBreake + akt.table[i].numberLost;
                        worksheet.Range["AV" + line.ToString()].Value = CostToString(akt.table[i].summBreake + akt.table[i].summLost);
                        worksheet.Range["BA" + line.ToString()].Value = akt.table[i].circs + " " + akt.table[i].guilty;
                        worksheet.Range["BR" + line.ToString()].Value = akt.table[i].comment;
                    }
                    else
                    {
                        worksheet.Range["A" + line.ToString()].Value = "-";
                        worksheet.Range["E" + line.ToString()].Value = "-";
                        worksheet.Range["O" + line.ToString()].Value = "-";
                        worksheet.Range["T" + line.ToString()].Value = "-";
                        worksheet.Range["Z" + line.ToString()].Value = "-";
                        worksheet.Range["AD" + line.ToString()].Value = "-";
                        worksheet.Range["AI" + line.ToString()].Value = "-";
                        worksheet.Range["AM" + line.ToString()].Value = "-";
                        worksheet.Range["AR" + line.ToString()].Value = "-";
                        worksheet.Range["AV" + line.ToString()].Value = "-";
                        worksheet.Range["BA" + line.ToString()].Value = "-";
                        worksheet.Range["BR" + line.ToString()].Value = "-";
                    }
                    line++;
                    if (line == 38) line = 48;
                }

                if (akt.AllBreake() - akt.First10Breake() != 0)
                {
                    worksheet.Range["Z58"].Value = akt.AllBreake() - akt.First10Breake();
                    worksheet.Range["AD58"].Value = CostToString(akt.AllBreakeCost() - akt.First10BreakeCost());
                    worksheet.Range["AI58"].Value = akt.AllLost() - akt.First10Lost();
                    worksheet.Range["AM58"].Value = CostToString(akt.AllLostCost() - akt.First10LostCost());
                    worksheet.Range["AR58"].Value = (akt.AllBreake() + akt.AllLost()) - (akt.First10Breake() + akt.First10Lost());
                    worksheet.Range["AV58"].Value = CostToString((akt.AllBreakeCost() + akt.AllLostCost()) - (akt.First10BreakeCost() + akt.First10LostCost()));
                }
                else
                {
                    worksheet.Range["Z58"].Value = "-";
                    worksheet.Range["AD58"].Value = "-";
                    worksheet.Range["AI58"].Value = "-";
                    worksheet.Range["AM58"].Value = "-";
                    worksheet.Range["AR58"].Value = "-";
                    worksheet.Range["AV58"].Value = "-";
                }
                workbook.Save();
            }


        }

        private void bExit_Click(object sender, EventArgs e)
        {
            this.Close();
        }

        private void table_RowsAdded(object sender, DataGridViewRowsAddedEventArgs e)
        {
            DataGridViewTextBoxCell number = (DataGridViewTextBoxCell)table.Rows[table.Rows.Count - 2].Cells[0];
            DataGridViewButtonCell button = (DataGridViewButtonCell)table.Rows[table.Rows.Count - 2].Cells[11];
            number.Value = (table.Rows.Count - 1).ToString();
        }

        private void bDelete_Click()
        {
            if (table.CurrentRow.Index != table.Rows.Count - 1)
            {
                table.Rows.RemoveAt(table.CurrentRow.Index);
                for (int i = 0; i < table.Rows.Count - 1;i++ )
                {
                    DataGridViewTextBoxCell number = (DataGridViewTextBoxCell)table.Rows[table.Rows.Count - 2].Cells[0];
                    number.Value = (i + 1).ToString();
                }

                TotalSummNum(tbTotalBreakNum, tbTotalBreakSumm, 4, 5);
                TotalSummNum(tbTotalLostNum, tbTotalLostSumm, 6, 7);
            }
        }

        private void table_CellContentClick(object sender, DataGridViewCellEventArgs e)
        {
            var senderGrid = (DataGridView)sender;

            if (senderGrid.Columns[e.ColumnIndex] is DataGridViewButtonColumn &&
                e.RowIndex >= 0)
            {
                bDelete_Click();
            }
        }

        private void table_CellValueChanged(object sender, DataGridViewCellEventArgs e)
        {
            if (table.Rows.Count <= 1)
                return;

            if (e.ColumnIndex == 1)
            {
                DataGridViewComboBoxCell code = (DataGridViewComboBoxCell)table.Rows[e.RowIndex].Cells[1],
                    name = (DataGridViewComboBoxCell)table.Rows[e.RowIndex].Cells[2];
                DataGridViewTextBoxCell cost = (DataGridViewTextBoxCell)table.Rows[e.RowIndex].Cells[3];
                int index = GetIndexDataGridViewComboBoxCell(code, code.Value);
                if (index == -1)
                    return;
                name.Value = name.Items[index];
                cost.Value = elementsCost[index];
            }


            if (e.ColumnIndex == 2)
            {
                DataGridViewComboBoxCell code = (DataGridViewComboBoxCell)table.Rows[e.RowIndex].Cells[1],
                    name = (DataGridViewComboBoxCell)table.Rows[e.RowIndex].Cells[2];
                DataGridViewTextBoxCell cost = (DataGridViewTextBoxCell)table.Rows[e.RowIndex].Cells[3];
                int index = GetIndexDataGridViewComboBoxCell(name, name.Value);
                if (index == -1)
                    return;
                code.Value = code.Items[index];
                cost.Value = elementsCost[index];
            }


            if (e.ColumnIndex == 3 || e.ColumnIndex == 4)
            {
                try{
                    if (table.Rows[e.RowIndex].Cells[3].Value == null ||
                        table.Rows[e.RowIndex].Cells[4].Value == null)
                        return;
                    int cost = StringToCost(table.Rows[e.RowIndex].Cells[3].Value.ToString()),
                        number = Convert.ToInt32(table.Rows[e.RowIndex].Cells[4].Value.ToString()),
                        summ = cost * number;
                    DataGridViewTextBoxCell summText = (DataGridViewTextBoxCell)table.Rows[e.RowIndex].Cells[5];
                    summText.Value = CostToString(summ);

                    TotalSummNum(tbTotalBreakNum, tbTotalBreakSumm, 4, 5);
                }
                catch(Exception exc)
                {
                    MessageBox.Show(exc.Message);
                }
            }
            if (e.ColumnIndex == 3 || e.ColumnIndex == 6)
            {
                try
                {
                    if (table.Rows[e.RowIndex].Cells[3].Value == null ||
                        table.Rows[e.RowIndex].Cells[6].Value == null)
                        return;
                    int cost = StringToCost(table.Rows[e.RowIndex].Cells[3].Value.ToString()),
                        number = Convert.ToInt32(table.Rows[e.RowIndex].Cells[6].Value.ToString()),
                        summ = cost * number;
                    DataGridViewTextBoxCell summText = (DataGridViewTextBoxCell)table.Rows[e.RowIndex].Cells[7];
                    summText.Value = CostToString(summ);

                    TotalSummNum(tbTotalLostNum, tbTotalLostSumm, 6, 7);
                }
                catch (Exception exc)
                {
                    MessageBox.Show(exc.Message);
                }
            }
        }

        private int GetIndexDataGridViewComboBoxCell(DataGridViewComboBoxCell cb, object value)
        {
            for (int i = 0; i < cb.Items.Count; i++ )
            {
                if (cb.Items[i].Equals(value))
                    return i;
            }
            return -1;
        }

        private void persons_Click(object sender, EventArgs e)
        {
            (new Form2(akt)).ShowDialog();
        }

        private void OrganisationChange(object sender, EventArgs e)
        {
            ComboBox cb = (ComboBox)sender;
            cbOrganisation.SelectedIndex = cb.SelectedIndex;
            cbOKPO.SelectedIndex = cb.SelectedIndex;
        }

        private void PartChange(object sender, EventArgs e)
        {
            ComboBox cb = (ComboBox)sender;
            cbPart.SelectedIndex = cb.SelectedIndex;
            cbCodePart.SelectedIndex = cb.SelectedIndex;
            cbOKDP.SelectedIndex = 6;
        }

        private string CostToString(int cost)
        {
            return (cost / 100).ToString() + "," + (cost % 100).ToString();
        }

        private int StringToCost(string str)
        {
            return Convert.ToInt32(str.Split(',')[0])*100 + Convert.ToInt32(str.Split(',')[1]);
        }

        private void TotalSummNum(TextBox tbNum, TextBox tbSumm, int colNum, int colSumm)
        {
            int totalNum = 0,
                totalSumm = 0,
                number,
                summ;
            for (int i = 0; i < table.Rows.Count - 1; i++)
            {
                if (table.Rows[i].Cells[colNum].Value == null ||
                    table.Rows[i].Cells[colSumm].Value == null)
                    continue;
                number = Convert.ToInt32(table.Rows[i].Cells[colNum].Value.ToString());
                summ = StringToCost(table.Rows[i].Cells[colSumm].Value.ToString());
                totalNum += number;
                totalSumm += summ;
            }
            tbNum.Text = totalNum.ToString();
            tbSumm.Text = CostToString(totalSumm);
        }
    }
}
