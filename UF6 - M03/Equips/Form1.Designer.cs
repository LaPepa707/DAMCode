namespace Equips
{
    partial class frmMain
    {
        /// <summary>
        ///  Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        ///  Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        ///  Required method for Designer support - do not modify
        ///  the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            menuStrip1 = new MenuStrip();
            mnuItemGetTeam = new ToolStripMenuItem();
            llistarTotsElsEquipsToolStripMenuItem = new ToolStripMenuItem();
            modificarEquipToolStripMenuItem = new ToolStripMenuItem();
            eliminarEquipToolStripMenuItem = new ToolStripMenuItem();
            crearEquiipToolStripMenuItem = new ToolStripMenuItem();
            lblAbreviatura = new Label();
            lblNom = new Label();
            lblPressupost = new Label();
            imgLog = new PictureBox();
            txtABV = new TextBox();
            txtNom = new TextBox();
            txtPressupost = new TextBox();
            dgvEquips = new DataGridView();
            label1 = new Label();
            txtURL = new TextBox();
            xmlForm = new Button();
            sqlForm = new Button();
            menuStrip1.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)imgLog).BeginInit();
            ((System.ComponentModel.ISupportInitialize)dgvEquips).BeginInit();
            SuspendLayout();
            // 
            // menuStrip1
            // 
            menuStrip1.ImageScalingSize = new Size(24, 24);
            menuStrip1.Items.AddRange(new ToolStripItem[] { mnuItemGetTeam, llistarTotsElsEquipsToolStripMenuItem, modificarEquipToolStripMenuItem, eliminarEquipToolStripMenuItem, crearEquiipToolStripMenuItem });
            menuStrip1.Location = new Point(0, 0);
            menuStrip1.Name = "menuStrip1";
            menuStrip1.Size = new Size(1577, 33);
            menuStrip1.TabIndex = 0;
            menuStrip1.Text = "menuStrip1";
            // 
            // mnuItemGetTeam
            // 
            mnuItemGetTeam.Name = "mnuItemGetTeam";
            mnuItemGetTeam.Size = new Size(163, 29);
            mnuItemGetTeam.Text = "Obtenir un equip";
            mnuItemGetTeam.Click += mnuItemGetTeam_Click;
            // 
            // llistarTotsElsEquipsToolStripMenuItem
            // 
            llistarTotsElsEquipsToolStripMenuItem.Name = "llistarTotsElsEquipsToolStripMenuItem";
            llistarTotsElsEquipsToolStripMenuItem.Size = new Size(194, 29);
            llistarTotsElsEquipsToolStripMenuItem.Text = "Llistar Tots Els Equips";
            llistarTotsElsEquipsToolStripMenuItem.Click += llistarTotsElsEquipsToolStripMenuItem_Click;
            // 
            // modificarEquipToolStripMenuItem
            // 
            modificarEquipToolStripMenuItem.Name = "modificarEquipToolStripMenuItem";
            modificarEquipToolStripMenuItem.Size = new Size(153, 29);
            modificarEquipToolStripMenuItem.Text = "Modificar Equip";
            modificarEquipToolStripMenuItem.Click += modificarEquipToolStripMenuItem_Click;
            // 
            // eliminarEquipToolStripMenuItem
            // 
            eliminarEquipToolStripMenuItem.Name = "eliminarEquipToolStripMenuItem";
            eliminarEquipToolStripMenuItem.Size = new Size(140, 29);
            eliminarEquipToolStripMenuItem.Text = "Eliminar Equip";
            eliminarEquipToolStripMenuItem.Click += eliminarEquipToolStripMenuItem_Click;
            // 
            // crearEquiipToolStripMenuItem
            // 
            crearEquiipToolStripMenuItem.Name = "crearEquiipToolStripMenuItem";
            crearEquiipToolStripMenuItem.Size = new Size(119, 29);
            crearEquiipToolStripMenuItem.Text = "Crear Equip";
            crearEquiipToolStripMenuItem.Click += crearEquiipToolStripMenuItem_Click;
            // 
            // lblAbreviatura
            // 
            lblAbreviatura.AutoSize = true;
            lblAbreviatura.Location = new Point(910, 92);
            lblAbreviatura.Name = "lblAbreviatura";
            lblAbreviatura.Size = new Size(139, 25);
            lblAbreviatura.TabIndex = 1;
            lblAbreviatura.Text = "ABREVIATURA : ";
            // 
            // lblNom
            // 
            lblNom.AutoSize = true;
            lblNom.Location = new Point(910, 147);
            lblNom.Name = "lblNom";
            lblNom.Size = new Size(59, 25);
            lblNom.TabIndex = 2;
            lblNom.Text = "NOM:";
            // 
            // lblPressupost
            // 
            lblPressupost.AutoSize = true;
            lblPressupost.Location = new Point(910, 195);
            lblPressupost.Name = "lblPressupost";
            lblPressupost.Size = new Size(121, 25);
            lblPressupost.TabIndex = 3;
            lblPressupost.Text = "PRESSUPOST:";
            // 
            // imgLog
            // 
            imgLog.Location = new Point(1092, 300);
            imgLog.Name = "imgLog";
            imgLog.Size = new Size(249, 195);
            imgLog.TabIndex = 4;
            imgLog.TabStop = false;
            imgLog.Click += imgLog_Click;
            // 
            // txtABV
            // 
            txtABV.Location = new Point(1092, 92);
            txtABV.Name = "txtABV";
            txtABV.Size = new Size(150, 31);
            txtABV.TabIndex = 5;
            txtABV.TextChanged += txtABV_TextChanged;
            txtABV.Enter += txtABV_Enter;
            // 
            // txtNom
            // 
            txtNom.Location = new Point(1092, 141);
            txtNom.Name = "txtNom";
            txtNom.Size = new Size(284, 31);
            txtNom.TabIndex = 6;
            // 
            // txtPressupost
            // 
            txtPressupost.Location = new Point(1092, 195);
            txtPressupost.Name = "txtPressupost";
            txtPressupost.Size = new Size(150, 31);
            txtPressupost.TabIndex = 7;
            // 
            // dgvEquips
            // 
            dgvEquips.ColumnHeadersHeightSizeMode = DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            dgvEquips.Location = new Point(90, 109);
            dgvEquips.Name = "dgvEquips";
            dgvEquips.RowHeadersWidth = 62;
            dgvEquips.Size = new Size(692, 611);
            dgvEquips.TabIndex = 8;
            // 
            // label1
            // 
            label1.AutoSize = true;
            label1.Location = new Point(910, 244);
            label1.Name = "label1";
            label1.Size = new Size(47, 25);
            label1.TabIndex = 9;
            label1.Text = "URL:";
            label1.Click += label1_Click;
            // 
            // txtURL
            // 
            txtURL.Location = new Point(1092, 244);
            txtURL.Name = "txtURL";
            txtURL.Size = new Size(300, 31);
            txtURL.TabIndex = 10;
            // 
            // xmlForm
            // 
            xmlForm.Location = new Point(857, 676);
            xmlForm.Name = "xmlForm";
            xmlForm.Size = new Size(252, 34);
            xmlForm.TabIndex = 11;
            xmlForm.Text = "XML";
            xmlForm.UseVisualStyleBackColor = true;
            xmlForm.Click += xmlForm_Click;
            // 
            // sqlForm
            // 
            sqlForm.Location = new Point(1173, 676);
            sqlForm.Name = "sqlForm";
            sqlForm.Size = new Size(247, 34);
            sqlForm.TabIndex = 12;
            sqlForm.Text = "MySQL";
            sqlForm.UseVisualStyleBackColor = true;
            sqlForm.Click += sqlForm_Click;
            // 
            // frmMain
            // 
            AutoScaleDimensions = new SizeF(10F, 25F);
            AutoScaleMode = AutoScaleMode.Font;
            ClientSize = new Size(1577, 937);
            Controls.Add(sqlForm);
            Controls.Add(xmlForm);
            Controls.Add(txtURL);
            Controls.Add(label1);
            Controls.Add(dgvEquips);
            Controls.Add(txtPressupost);
            Controls.Add(txtNom);
            Controls.Add(txtABV);
            Controls.Add(imgLog);
            Controls.Add(lblPressupost);
            Controls.Add(lblNom);
            Controls.Add(lblAbreviatura);
            Controls.Add(menuStrip1);
            MainMenuStrip = menuStrip1;
            Name = "frmMain";
            Text = "APP EQUIPS";
            Load += frmMain_Load;
            menuStrip1.ResumeLayout(false);
            menuStrip1.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)imgLog).EndInit();
            ((System.ComponentModel.ISupportInitialize)dgvEquips).EndInit();
            ResumeLayout(false);
            PerformLayout();
        }

        #endregion

        private MenuStrip menuStrip1;
        private ToolStripMenuItem mnuItemGetTeam;
        private Label lblAbreviatura;
        private Label lblNom;
        private Label lblPressupost;
        private PictureBox imgLog;
        private TextBox txtABV;
        private TextBox txtNom;
        private TextBox txtPressupost;
        private ToolStripMenuItem llistarTotsElsEquipsToolStripMenuItem;
        private ToolStripMenuItem modificarEquipToolStripMenuItem;
        private ToolStripMenuItem eliminarEquipToolStripMenuItem;
        private ToolStripMenuItem crearEquiipToolStripMenuItem;
        private DataGridView dgvEquips;
        private Label label1;
        private TextBox txtURL;
        private Button xmlForm;
        private Button sqlForm;
    }
}
