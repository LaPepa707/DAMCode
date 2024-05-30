using DAO_Pattern;
namespace Equips
{
    public partial class frmMain : Form
    {
        private DAOTeamFactory<Team> factory = new DAOTeamFactory<Team>();
        private IDAO<Team> dao;
        private DataSource tipe;
        public frmMain()
        {
            InitializeComponent();
        }

        private void Neteja()
        {
            txtABV.Text = "";
            txtNom.Text = "";
            txtPressupost.Text = "";
            imgLog.Image = null;
            txtURL.Text = "";
        }
        private void mnuItemGetTeam_Click(object sender, EventArgs e)
        {
            try
            {
                if (txtABV.Text != "" && txtABV.Text.Length == 3)
                {
                    Team t = dao.GetValue(txtABV.Text);
                    if (t != null)
                    {
                        txtNom.Text = t.Name;
                        txtPressupost.Text = Convert.ToString(t.Budget);
                        imgLog.ImageLocation = t.LogoLink;
                        imgLog.SizeMode = PictureBoxSizeMode.AutoSize;
                        txtURL.Text = t.LogoLink;

                    }
                    else
                    {
                        Neteja();
                        txtNom.Text = "NO EXISTEIX EQUIP SOL·LICITAT";
                    }
                }
            }
            catch (Exception msg)
            {
                Neteja();
                txtNom.Text = $"{msg}";
            }

        }
        private void frmMain_Load(object sender, EventArgs e)
        {

        }

        private void llistarTotsElsEquipsToolStripMenuItem_Click(object sender, EventArgs e)
        {
            try
            {
                HashSet<Team> equips = dao.GetAll();
                List<Team> teamList = equips.ToList();
                dgvEquips.DataSource = teamList;
            }
            catch (Exception msg)
            {

                Neteja();
                txtNom.Text = $"{msg}";
            }
        }

        private void crearEquiipToolStripMenuItem_Click(object sender, EventArgs e)
        {
            Team t = null;
            try
            {
                if (txtABV.Text != "" && txtABV.Text.Length == 3)
                {
                    t = new Team(txtABV.Text, txtNom.Text, Convert.ToInt32(txtPressupost.Text), txtURL.Text);
                    dao.Save(t);
                    Neteja();
                    txtNom.Text = "S'HA CREAT CORRECTAMENT";
                }
                else
                {
                    Neteja();
                    txtNom.Text = "NO EXISTEIX EQUIP SOL·LICITAT";
                }
            }
            catch (Exception msg)
            {
                Neteja();
                txtNom.Text = $"{msg}";
            }
        }

        private void imgLog_Click(object sender, EventArgs e)
        {

        }

        private void txtABV_TextChanged(object sender, EventArgs e)
        {

        }

        private void txtABV_Enter(object sender, EventArgs e)
        {
            Neteja();
        }

        private void modificarEquipToolStripMenuItem_Click(object sender, EventArgs e)
        {
            try
            {
                if (txtABV.Text != "" && txtABV.Text.Length == 3)
                {
                    Team t = dao.GetValue(txtABV.Text);
                    if (t != null)
                    {
                        Team updateTeam = new Team(txtABV.Text, txtNom.Text, Convert.ToInt32(txtPressupost.Text), txtURL.Text);
                        dao.Update(t.Avb, updateTeam);
                        Neteja();
                        txtNom.Text = "S'HA MODIFICAT L'EQUIP";
                    }
                    else
                    {
                        Neteja();
                        txtNom.Text = "NO EXISTEIX EQUIP SOL·LICITAT";
                    }
                }
            }
            catch (Exception msg)
            {
                Neteja();
                txtNom.Text = $"{msg}";
            }
        }

        private void eliminarEquipToolStripMenuItem_Click(object sender, EventArgs e)
        {
            try
            {
                if (txtABV.Text != "" && txtABV.Text.Length == 3)
                {
                    Team t = dao.GetValue(txtABV.Text);
                    if (t != null)
                    {
                        dao.Delete(txtABV.Text);
                        txtNom.Text = "S'HA ELIMINAT CORRECTAMENT";
                    }
                    else
                    {
                        Neteja();
                        txtNom.Text = "NO EXISTEIX EQUIP SOL·LICITAT";
                    }
                }
            }
            catch (Exception msg)
            {
                Neteja();
                txtNom.Text = $"{msg}";
            }
        }

        private void label1_Click(object sender, EventArgs e)
        {

        }

        private void xmlForm_Click(object sender, EventArgs e)
        {
            dao = factory.CreateDAOTeamInstance(DataSource.XML);
        }

        private void sqlForm_Click(object sender, EventArgs e)
        {
            dao = factory.CreateDAOTeamInstance(DataSource.MySQL);
        }
    }
}
