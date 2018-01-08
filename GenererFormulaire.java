import org.jdom.*;
import org.jdom.input.*;
import java.util.List;
import java.io.File;
import java.io.PrintWriter;

public class GenererFormulaire
{
	private static Document document;
	private static Element racine;

	public GenererFormulaire(String fichier)
	{
		SAXBuilder sxb = new SAXBuilder();
		try
		{
			document = sxb.build(new File(fichier));
		}
		catch (Exception e)
		{
			System.out.println("Erreur dans la lecture du fichier.");
		}
		racine = document.getRootElement();
	}

	public String genererEntete(String variables)
	{
		String s = "";
		s+="import java.awt.*;\n";
		s+="import javax.swing.*;\n";
		s+="import java.util.*;\n\n";
		s+="public class Formulaire extends JFrame\n{\n";
		s+="\tArrayList<Object> listeObjet;\n";
		s+= variables;

		return s;
	}

	public String genererCorps(String variables)
	{
		String s = "";
		s+="\tpublic Formulaire()\n\t{\n";
		s+="\t\tsetSize(500,500);\n";
		s+="\t\tsetLocation(200,200);\n";
		s+="\t\tsetLayout(new GridBagLayout());\n";
		s+=variables;
		s+="\t\tsetVisible(true);\n";
		s+="\t}";

		return s;
	}

	public String genererPied()
	{
		String s = "";
		s+="\n\tpublic static void main(String[] args)\n\t{\n";
		s+="\t\tnew Formulaire();\n";
		s+="\t}\n}";

		return s;
	}

	public String[] genererVariables()
	{
		List<Element> listeObjet = racine.getChildren("label");
		String declarationVariable = "";
		String instanciationVariable = "";

		int compteurJtf = 0,compteurLabel = 0,compteurCheckbox = 0;

		instanciationVariable+="\t\tlisteObjet = new ArrayList<Object>();\n";
		instanciationVariable+="\t\tGridBagConstraints gbc = new GridBagConstraints();\n\n";

		for (Element e : listeObjet )
		{
			int id = Integer.parseInt(e.getAttributeValue("id"));
			int posX = Integer.parseInt(e.getAttributeValue("x"));
			int posY = Integer.parseInt(e.getAttributeValue("y"));
			int largeur = Integer.parseInt(e.getAttributeValue("largeur"));
			String type = e.getAttributeValue("type");

			switch (type)
			{
				case "JTextField" :
				{
					String nomVariable = "jtextfield"+ ++compteurJtf;
					declarationVariable+="\tprivate "+type+" "+nomVariable+";\n";
					instanciationVariable+="\t\t"+nomVariable+" = new "+type+"();\n";
					instanciationVariable+="\t\t"+nomVariable+".setPreferredSize(new Dimension("+largeur+",25));\n";
					instanciationVariable+="\t\tgbc.insets = new Insets(5, 10, 0, 5);\n";
					instanciationVariable+="\t\tgbc.gridx = 1;\n";
					instanciationVariable+="\t\tgbc.gridy = "+id+";\n";
					instanciationVariable+="\t\tadd (new JLabel(\""+e.getText()+"\"),gbc);\n";
					instanciationVariable+="\t\tgbc.gridx = 2;\n";
					instanciationVariable+="\t\tadd("+nomVariable+",gbc);\n";
					instanciationVariable+="\t\tlisteObjet.add("+nomVariable+");\n\n";
				}
				break;
				case "JLabel" :
				{
					String nomVariable = "jlabel"+ ++compteurLabel;
					String valeur = e.getAttributeValue("value");
					declarationVariable+="\tprivate "+type+" "+nomVariable+";\n";
					if(!valeur.equals(null))
					{
						instanciationVariable+="\t\t"+nomVariable+" = new "+type+"(\""+valeur+"\");\n";
					}
					else
						instanciationVariable+="\t\t"+nomVariable+" = new "+type+"();\n";
					instanciationVariable+="\t\t"+nomVariable+".setSize("+posX+","+posY+");\n";
					instanciationVariable+="\t\tgbc.insets = new Insets(5, 10, 0, 5);\n";
					instanciationVariable+="\t\tgbc.gridx = 1;\n";
					instanciationVariable+="\t\tgbc.gridy = "+id+";\n";
					instanciationVariable+="\t\tadd("+nomVariable+",gbc);\n";
					instanciationVariable+="\t\tlisteObjet.add("+nomVariable+");\n";
				}
				break;
				case "JComboBox" :
				{
					String[] labelCheckBox = e.getText().split(":");
					String[] valCheckBox = labelCheckBox[1].split(",");
					int tailleVal = valCheckBox.length;

					String nomVariable = "jcombobox"+ ++compteurCheckbox;
					declarationVariable+="\tprivate "+type+" "+nomVariable+";\n";
					instanciationVariable+="\t\t"+nomVariable+" = new "+type+"();\n";
					instanciationVariable+="\t\t"+nomVariable+".setPreferredSize(new Dimension("+largeur+",25));\n";
					instanciationVariable+="\t\tgbc.insets = new Insets(5, 10, 0, 5);\n";
					instanciationVariable+="\t\tgbc.gridx = 1;\n";
					instanciationVariable+="\t\tgbc.gridy = "+id+";\n";
					instanciationVariable+="\t\tadd (new JLabel(\""+labelCheckBox[0]+"\"),gbc);\n";
					instanciationVariable+="\t\tgbc.gridx = 2;\n";
					// instanciationVariable+="\t\tfor (int i = 0; i < "+tailleVal+"; i++);\n";
					// instanciationVariable+="\t\t\t"+nomVariable+".addItem("+valCheckBox[i]+");\n";
					for (int i = 0; i < tailleVal; i++)
					{
						instanciationVariable+="\t\t"+nomVariable+".addItem(\""+valCheckBox[i]+"\");\n";
					}
					instanciationVariable+="\t\tadd("+nomVariable+",gbc);\n";
					instanciationVariable+="\t\tlisteObjet.add("+nomVariable+");\n\n";
				}
				break;

				default : continue;
			}

		}
		String[] textes = new String[]{declarationVariable,instanciationVariable};
		return textes;
	}

	public static void main(String[] args)
	{
		GenererFormulaire generationFormulaire = new GenererFormulaire("test.xml");
		String[] textes = generationFormulaire.genererVariables();
		System.out.println(generationFormulaire.genererEntete(textes[0]));
		System.out.println(generationFormulaire.genererCorps(textes[1]));
		System.out.println(generationFormulaire.genererPied());

		String s = "";
		s+= generationFormulaire.genererEntete(textes[0]);
		s+= generationFormulaire.genererCorps(textes[1]);
		s+= generationFormulaire.genererPied();

		try {
			PrintWriter writer = new PrintWriter(new File("Formulaire.java"), "UTF-8");
			writer.println(s);
			writer.close();
		}
		catch (Exception e) {}
	}
}