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
		s+="\tArrayList<JLabel> listeId;\n";
		s+="\tArrayList<JLabel> listeType;\n";
		s+= variables+"\n";

		return s;
	}

	public String genererCorps(String variables)
	{
		String s = "";
		s+="\tpublic Formulaire()\n\t{\n";
		s+="\t\tsetLocation(200,200);\n";
		s+="\t\tsetLayout(new GridBagLayout());\n";
		s+=variables;
		s+="\t\tsetVisible(true);\n";
		s+="\t\tpack();\n";
		s+="\t}";

		return s;
	}

	public String genererPied()
	{
		String s = "";
		s+="\n\n\tpublic static void main(String[] args)\n\t{\n";
		s+="\t\tnew Formulaire();\n";
		s+="\t}\n}";

		return s;
	}

	public String[] genererVariables()
	{
		List<Element> listeObjet = racine.getChildren();
		String declarationVariable = "";
		String instanciationVariable = "";

		int gridY = 1;

		int compteurJtf = 0,compteurLabel = 0,compteurCheckbox = 0,
		compteurCombobox = 0,compteurRadiobutton = 0, compteurSpinner = 0;

		instanciationVariable+="\t\tlisteObjet = new ArrayList<Object>();\n";
		instanciationVariable+="\t\tlisteId = new ArrayList<JLabel>();\n";
		instanciationVariable+="\t\tlisteType = new ArrayList<JLabel>();\n";
		instanciationVariable+="\t\tGridBagConstraints gbc = new GridBagConstraints();\n\n";

		for (Element e : listeObjet )
		{
			String type = e.getName();

			if(type.equals("Tableau"))
			{

			}
			else
			{
				int id = Integer.parseInt(e.getAttributeValue("id"));
				int posX = Integer.parseInt(e.getAttributeValue("x"));
				int posY = Integer.parseInt(e.getAttributeValue("y"));
				int largeur = Integer.parseInt(e.getAttributeValue("largeur"));

				switch (type)
				{
					case "JTextField" :
					{
						String nomVariable = "jtextfield"+ ++compteurJtf;

						declarationVariable+="\tprivate "+type+" "+nomVariable+";\n";
						instanciationVariable+="\t\t"+nomVariable+" = new "+type+"();\n";
						instanciationVariable+="\t\t"+nomVariable+".setPreferredSize(new Dimension("+largeur+",25));\n";
						instanciationVariable+="\t\tgbc.insets = new Insets(5, 10, 0, 5);\n\n";

						instanciationVariable+="\t\tgbc.gridx = 1;\n";
						instanciationVariable+="\t\tgbc.gridy = "+gridY+";\n";
						instanciationVariable+="\t\tJLabel labelId"+id+" = new JLabel(\""+id+"\");\n";
						instanciationVariable+="\t\tadd(labelId"+id+",gbc);\n";
						instanciationVariable+="\t\tlisteId.add(labelId"+id+");\n\n";

						instanciationVariable+="\t\tgbc.gridx = 2;\n";
						instanciationVariable+="\t\tadd (new JLabel(\""+e.getAttributeValue("value")+"\"),gbc);\n\n";

						instanciationVariable+="\t\tgbc.gridx = 3;\n";
						instanciationVariable+="\t\tadd("+nomVariable+",gbc);\n";
						instanciationVariable+="\t\tlisteObjet.add("+nomVariable+");\n\n";

						instanciationVariable+="\t\tgbc.gridx = 4;\n";
						instanciationVariable+="\t\tgbc.gridy = "+gridY+";\n";
						instanciationVariable+="\t\tJLabel labelType"+id+" = new JLabel(\""+e.getAttributeValue("type")+"\");\n";
						instanciationVariable+="\t\tadd(labelType"+id+",gbc);\n";
						instanciationVariable+="\t\tlisteType.add(labelType"+id+");\n\n";
						gridY++;
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
						instanciationVariable+="\t\tgbc.insets = new Insets(5, 10, 0, 5);\n\n";

						instanciationVariable+="\t\tgbc.gridx = 1;\n";
						instanciationVariable+="\t\tgbc.gridy = "+gridY+";\n";
						instanciationVariable+="\t\tJLabel labelId"+id+" = new JLabel(\""+id+"\");\n";
						instanciationVariable+="\t\tgbc.anchor = GridBagConstraints.WEST;\n";
						instanciationVariable+="\t\tadd(labelId"+id+",gbc);\n";
						instanciationVariable+="\t\tlisteId.add(labelId"+id+");\n\n";

						instanciationVariable+="\t\tgbc.gridx = 3;\n";
						instanciationVariable+="\t\tadd("+nomVariable+",gbc);\n";
						instanciationVariable+="\t\tlisteObjet.add("+nomVariable+");\n\n";

						instanciationVariable+="\t\tgbc.gridx = 4;\n";
						instanciationVariable+="\t\tgbc.gridy = "+gridY+";\n";
						instanciationVariable+="\t\tJLabel labelType"+id+" = new JLabel(\""+e.getAttributeValue("type")+"\");\n";
						instanciationVariable+="\t\tadd(labelType"+id+",gbc);\n";
						instanciationVariable+="\t\tlisteType.add(labelType"+id+");\n\n";
						gridY++;
					}
					break;

					case "JComboBox" :
					{
						List<Element> elements = e.getChildren();

						String nomVariable = "jcombobox"+ ++compteurCombobox;
						declarationVariable+="\tprivate "+type+" "+nomVariable+";\n";
						instanciationVariable+="\t\t"+nomVariable+" = new "+type+"();\n";
						instanciationVariable+="\t\t"+nomVariable+".setPreferredSize(new Dimension("+largeur+",25));\n";
						instanciationVariable+="\t\tgbc.insets = new Insets(5, 10, 0, 5);\n\n";

						instanciationVariable+="\t\tgbc.gridx = 1;\n";
						instanciationVariable+="\t\tgbc.gridy = "+gridY+";\n";
						instanciationVariable+="\t\tJLabel labelId"+id+" = new JLabel(\""+id+"\");\n";
						instanciationVariable+="\t\tadd(labelId"+id+",gbc);\n";
						instanciationVariable+="\t\tlisteId.add(labelId"+id+");\n\n";

						instanciationVariable+="\t\tgbc.gridx = 2;\n";
						instanciationVariable+="\t\tadd (new JLabel(\""+e.getAttributeValue("value")+"\"),gbc);\n\n";

						instanciationVariable+="\t\tgbc.gridx = 3;\n";
						for (Element elt : elements)
						{
							instanciationVariable+="\t\t"+nomVariable+".addItem(\""+elt.getText()+"\");\n";
						}
						instanciationVariable+="\t\tadd("+nomVariable+",gbc);\n";
						instanciationVariable+="\t\tlisteObjet.add("+nomVariable+");\n\n";

						instanciationVariable+="\t\tgbc.gridx = 4;\n";
						instanciationVariable+="\t\tgbc.gridy = "+gridY+";\n";
						instanciationVariable+="\t\tJLabel labelType"+id+" = new JLabel(\""+e.getAttributeValue("type")+"\");\n";
						instanciationVariable+="\t\tadd(labelType"+id+",gbc);\n";
						instanciationVariable+="\t\tlisteType.add(labelType"+id+");\n\n";
						gridY++;
					}
					break;

					case "JCheckBox" :
					{
						List<Element> elements = e.getChildren();

						instanciationVariable+="\t\tgbc.insets = new Insets(5, 10, 0, 5);\n\n";

						instanciationVariable+="\t\tgbc.gridx = 1;\n";
						instanciationVariable+="\t\tgbc.gridy = "+gridY+";\n";
						instanciationVariable+="\t\tJLabel labelId"+id+" = new JLabel(\""+id+"\");\n";
						instanciationVariable+="\t\tadd(labelId"+id+",gbc);\n";
						instanciationVariable+="\t\tlisteId.add(labelId"+id+");\n\n";

						instanciationVariable+="\t\tgbc.gridx = 2;\n";
						instanciationVariable+="\t\tadd (new JLabel(\""+e.getAttributeValue("value")+"\"),gbc);\n\n";

						instanciationVariable+="\t\tgbc.gridx = 4;\n";
						instanciationVariable+="\t\tgbc.gridy = "+gridY+";\n";
						instanciationVariable+="\t\tJLabel labelType"+id+" = new JLabel(\""+e.getAttributeValue("type")+"\");\n";
						instanciationVariable+="\t\tadd(labelType"+id+",gbc);\n";
						instanciationVariable+="\t\tlisteType.add(labelType"+id+");\n\n";

						instanciationVariable+="\t\tgbc.gridx = 3;\n";
						for (Element elt : elements)
						{
							String nomVariable = "jcheckbox"+ ++compteurCheckbox;
							declarationVariable+="\tprivate "+type+" "+nomVariable+";\n";
							instanciationVariable+="\t\t"+nomVariable+" = new "+type+"(\""+elt.getText()+"\");\n";
							instanciationVariable+="\t\tadd("+nomVariable+",gbc);\n";
							instanciationVariable+="\t\tlisteObjet.add("+nomVariable+");\n\n";
							gridY++;
							instanciationVariable+="\t\tgbc.gridy = "+gridY+";\n";
						}
					}
					break;

					case "JRadioButton" :
					{
						List<Element> elements = e.getChildren();

						instanciationVariable+="\t\tgbc.insets = new Insets(5, 10, 0, 5);\n\n";

						instanciationVariable+="\t\tgbc.gridx = 1;\n";
						instanciationVariable+="\t\tgbc.gridy = "+gridY+";\n";
						instanciationVariable+="\t\tJLabel labelId"+id+" = new JLabel(\""+id+"\");\n";
						instanciationVariable+="\t\tadd(labelId"+id+",gbc);\n";
						instanciationVariable+="\t\tlisteId.add(labelId"+id+");\n\n";

						instanciationVariable+="\t\tgbc.gridx = 2;\n";
						instanciationVariable+="\t\tadd (new JLabel(\""+e.getAttributeValue("value")+"\"),gbc);\n\n";

						instanciationVariable+="\t\tgbc.gridx = 4;\n";
						instanciationVariable+="\t\tgbc.gridy = "+gridY+";\n";
						instanciationVariable+="\t\tJLabel labelType"+id+" = new JLabel(\""+e.getAttributeValue("type")+"\");\n";
						instanciationVariable+="\t\tadd(labelType"+id+",gbc);\n";
						instanciationVariable+="\t\tlisteType.add(labelType"+id+");\n\n";

						instanciationVariable+="\t\tgbc.gridx = 3;\n";

						instanciationVariable+="\t\tButtonGroup bg = new ButtonGroup();\n";
						for (Element elt : elements)
						{
							String nomVariable = "jradiobutton"+ ++compteurRadiobutton;
							declarationVariable+="\tprivate "+type+" "+nomVariable+";\n";
							instanciationVariable+="\t\t"+nomVariable+" = new "+type+"(\""+elt.getText()+"\");\n";
							instanciationVariable+="\t\tbg.add("+nomVariable+");\n";
							instanciationVariable+="\t\tadd("+nomVariable+",gbc);\n";
							instanciationVariable+="\t\tlisteObjet.add("+nomVariable+");\n\n";
							gridY++;
							instanciationVariable+="\t\tgbc.gridy = "+gridY+";\n";
						}
					}
					break;

					case "JSpinner" :
					{
						List<Element> elements = e.getChildren();

						String nomVariable = "jspinner"+ ++compteurSpinner;
						declarationVariable+="\tprivate "+type+" "+nomVariable+";\n";
						instanciationVariable+="\t\t"+nomVariable+" = new "+type+"();\n";
						instanciationVariable+="\t\t"+nomVariable+".setPreferredSize(new Dimension("+largeur+",25));\n";
						instanciationVariable+="\t\tgbc.insets = new Insets(5, 10, 0, 5);\n\n";

						instanciationVariable+="\t\tgbc.gridx = 1;\n";
						instanciationVariable+="\t\tgbc.gridy = "+gridY+";\n";
						instanciationVariable+="\t\tJLabel labelId"+id+" = new JLabel(\""+id+"\");\n";
						instanciationVariable+="\t\tadd(labelId"+id+",gbc);\n";
						instanciationVariable+="\t\tlisteId.add(labelId"+id+");\n\n";

						instanciationVariable+="\t\tgbc.gridx = 2;\n";
						instanciationVariable+="\t\tadd (new JLabel(\""+e.getAttributeValue("value")+"\"),gbc);\n\n";

						instanciationVariable+="\t\tgbc.gridx = 3;\n";
						instanciationVariable+="\t\tadd("+nomVariable+",gbc);\n";
						instanciationVariable+="\t\tlisteObjet.add("+nomVariable+");\n\n";

						instanciationVariable+="\t\tgbc.gridx = 4;\n";
						instanciationVariable+="\t\tgbc.gridy = "+gridY+";\n";
						instanciationVariable+="\t\tJLabel labelType"+id+" = new JLabel(\""+e.getAttributeValue("type")+"\");\n";
						instanciationVariable+="\t\tadd(labelType"+id+",gbc);\n";
						instanciationVariable+="\t\tlisteType.add(labelType"+id+");\n\n";
						gridY++;
					}
					break;

					default : continue;
				}
			}
		}
		String[] textes = new String[]{declarationVariable,instanciationVariable};
		return textes;
	}

	public static void main(String[] args)
	{
		GenererFormulaire generationFormulaire = new GenererFormulaire("Test2.xml");
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
