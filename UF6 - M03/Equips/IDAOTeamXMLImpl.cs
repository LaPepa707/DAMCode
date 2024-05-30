using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Xml;
using System.Xml.Linq;
using System.Xml.Schema;
using System.Xml.XPath;

namespace DAO_Pattern
{
    public class IDAOTeamXMLImpl : IDAO<Team>
    {
        string xmlFile = "EQUIPS.XML";
        public void Delete(string id)
        {
            XDocument doc = XDocument.Load(xmlFile);
            XElement equipDel = null;
            equipDel = doc.XPathSelectElement($"/EQUIPS/EQUIP[ABREVIACIO='{id}']");

            if (equipDel is null)
                throw new ArgumentNullException("NO EXISTEIX L'EQUIP QUE ES VOL ELIMINAR");
            else
            {
                equipDel.Remove();
                doc.Save(xmlFile);
            }
        }

        public HashSet<Team> GetAll()
        {
            HashSet<Team> teamList = new HashSet<Team>();
            XDocument doc = XDocument.Load(xmlFile);
            Team team = null;
            IEnumerable<XElement> teams = doc.XPathSelectElements("/EQUIPS/EQUIP");
            foreach (XElement t in teams)
            {
                string abv = (string)t.Element("ABREVIACIO");
                team = GetValue(abv);
                teamList.Add(team);
            }
            return teamList;
        }

        public Team GetValue(string abv)
        {
            Team eq = null;
            XDocument docEquips = XDocument.Load(xmlFile);

            XElement e = docEquips.XPathSelectElement($"/EQUIPS/EQUIP[ABREVIACIO='{abv}']");
           
            if (e!=null) 
                eq = new Team(e.Element("ABREVIACIO").Value, e.Element("NOM").Value, Convert.ToInt32(e.Element("PRESSUPOST").Value), e.Element("LOGO").Value);
            return eq;
        }

        public void Save(Team value)
        {
            if (value is not null)
            {
                HashSet<Team> equips = GetAll();
                if (equips.Contains(value)) throw new Exception("L'EQUIP QUE ES VOL CREAR JA EXISTEIX");
                else
                {
                    XmlDocument doc = new XmlDocument();
                    doc.Load(xmlFile);
                    XmlNode EQUIPS = doc.SelectSingleNode("/EQUIPS");
                    XmlNode EQUIP = doc.CreateElement("EQUIP");
                    XmlNode ABREVIACIO = doc.CreateElement("ABREVIACIO");
                    XmlNode NOM = doc.CreateElement("NOM");
                    XmlNode PRESSUPOST = doc.CreateElement("PRESSUPOST");
                    XmlNode LOGO = doc.CreateElement("LOGO");
                    ABREVIACIO.InnerText = value.Avb;
                    NOM.InnerText = value.Name;
                    PRESSUPOST.InnerText = Convert.ToString(value.Budget);
                    LOGO.InnerText = value.LogoLink;
                    EQUIP.AppendChild(ABREVIACIO);
                    EQUIP.AppendChild(NOM);
                    EQUIP.AppendChild(PRESSUPOST);
                    EQUIP.AppendChild(LOGO);
                    EQUIPS.AppendChild(EQUIP);
                    doc.Save(xmlFile);
                }
            }
            else throw new ArgumentNullException("EQUIP NULL");

        }

        public void Update(string abreviacio, Team updatedTeam)
        {
            XDocument doc = XDocument.Load(xmlFile);
            XElement equipModificar = doc.XPathSelectElement($"/EQUIPS/EQUIP[ABREVIACIO='{abreviacio}']");

            if (equipModificar is not null)
            {
                equipModificar.SetElementValue("NOM", updatedTeam.Name);
                equipModificar.SetElementValue("PRESSUPOST", updatedTeam.Budget);
                equipModificar.SetElementValue("LOGO", updatedTeam.LogoLink);

                doc.Save(xmlFile);
            }
            else throw new ArgumentNullException("NO ES POT MODIFICAR UN EQUIP INEXISTENT");

        }
    }
}
