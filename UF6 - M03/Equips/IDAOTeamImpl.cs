using MySql.Data.MySqlClient;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace DAO_Pattern
{
    public class IDAOTeamMySqlImpl : IDAO <Team>
    {
        MySql.Data.MySqlClient.MySqlConnection cN;
        
        public IDAOTeamMySqlImpl()
        {
            string strCn = "datasource=127.0.0.1;port=3306;username=root;password=;database=equips;";
            cN = new MySqlConnection(strCn);
            cN.Open();

        }
        public void Delete(string  id)
        {
            Team equipDel = GetValue(id);

            using var cmd = new MySqlCommand();
            cmd.Connection = cN;

            if (equipDel is not null)
            {
                cmd.CommandText = "DELETE FROM equips WHERE ABREVIACIO = @ABV";
                cmd.Parameters.AddWithValue("@ABV", id);
                cmd.ExecuteNonQuery();
            }
            else throw new ArgumentNullException("NO EXISTEIX L'EQUIP");
        }

        public HashSet<Team> GetAll()
        {
            HashSet<Team> llistaEquipas = new HashSet<Team>();
            Team equipsAfegir = null;
            var sSql = "SELECT * FROM EQUIPS";
            MySqlCommand cmd = new MySqlCommand(sSql, cN);

            cmd.Prepare();

            using (MySqlDataReader cursor = cmd.ExecuteReader())
            {
                while (cursor.Read())
                {
                    equipsAfegir = new(cursor.GetString("ABREVIACIO"), cursor.GetString("NOM"), cursor.GetInt32("PRESSUPOST"), cursor.GetString("LOGOLINK"));
                    llistaEquipas.Add(equipsAfegir);
                }
            }

            return llistaEquipas;
            
        }
        public Team GetValue(string abv)
        {
            Team equip = null;
            var sSql = "SELECT * FROM EQUIPS WHERE ABREVIACIO = @ABV";
            MySqlCommand cmd = new MySqlCommand(sSql, cN);

            cmd.Parameters.AddWithValue("@ABV", abv);

            cmd.Prepare();

            using (MySqlDataReader cursor = cmd.ExecuteReader()) 
            {
                if (cursor.Read())
                {
                    equip = new(cursor.GetString("ABREVIACIO"), cursor.GetString("NOM"), cursor.GetInt32("PRESSUPOST"), cursor.GetString("LOGOLINK"));
                }
            }
                

            return equip;
        }

        public void Save(Team value)
        {
            HashSet<Team> equips = GetAll();

            if (equips.Contains(value)) throw new Exception("L'EQUIP JA EXISTEIX");
            else
            {
                using var cmd = new MySqlCommand();
                cmd.Connection = cN;

                cmd.CommandText = "INSERT INTO equips VALUES (@ABV, @NOM, @BUDGET, @LOGO )";
                cmd.Parameters.AddWithValue("@ABV", value.Avb);
                cmd.Parameters.AddWithValue("@NOM", value.Name);
                cmd.Parameters.AddWithValue("@BUDGET", value.Budget);
                cmd.Parameters.AddWithValue("@LOGO", value.LogoLink);

                cmd.ExecuteNonQuery();

            }
        }


        public void Update(string abreviacio, Team updatedTeam)
        {
            Team equipModificar = null;
            equipModificar = GetValue(abreviacio);

            if (equipModificar is not null)
            {
                var sSql = "UPDATE equips SET NOM = @NOM, PRESSUPOST = @BUDGET, LOGOLINK = @LOGO WHERE ABREVIACIO = @ABV";

                using (MySqlCommand cmd = new MySqlCommand(sSql, cN))
                {
                    cmd.Parameters.AddWithValue("@ABV", abreviacio);
                    cmd.Parameters.AddWithValue("@NOM", updatedTeam.Name);
                    cmd.Parameters.AddWithValue("@BUDGET", updatedTeam.Budget);
                    cmd.Parameters.AddWithValue("@LOGO", updatedTeam.LogoLink);

                    cmd.ExecuteNonQuery();
                }
            }

            else throw new ArgumentNullException("L'EQUIP NO EXISTEIX");
        }
    }
}
