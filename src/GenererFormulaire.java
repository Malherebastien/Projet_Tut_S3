/**
 * Classe GenererFormulaire
 * Lecture d'un fichier XML pour générer une IHM
 * @author : Louis D. ,Brice M. ,Winona M. ,Bastien M. ,Thibaut L.
 */
import org.jdom.*;
import org.jdom.input.*;


import java.util.List;
import java.io.File;
import java.io.PrintWriter;
import java.util.Set;
import java.util.HashSet;

public class GenererFormulaire
{
	private static Document document;
	private static Element racine;

	/**
	 * Constructeur de la classe, permettant d'ouvrir le document XML afin de le parcourir par la suite.
	 * @param fichier lien du fichier XML à parcourir sous forme de String
	 */
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

	/**
	 * Méthode permettant de générer la partie haute du formulaire IHM, en prenant en paramètres les variables à déclarer en private
	 * c'est ici qu'on écrit également les import afin que l'IHM fonctionne correctement
	 * @param variables variables à déclarer récupérées depuis le fichier XML de l'utilisateur
	 * @return retourne une String de la partie haute qui sera par la suite écrite dans un fichier
	 */
	public String genererEntete(String variables)
	{
		String s = "";
		s+="import java.awt.*;\n";
		s+="import javax.swing.*;\n";
		s+="import java.awt.event.KeyListener;\n";
		s+="import java.awt.event.KeyEvent;\n";
		s+="import java.util.*;\n\n";
		s+="public class Formulaire extends JFrame implements KeyListener\n{\n";
		s+="\tArrayList<Object> listeObjet;\n";
		s+="\tHashMap<Integer,Object> mapObjetsId;\n";
		s+="\tArrayList<JLabel> listeId;\n";
		s+="\tArrayList<JLabel> listeType;\n";
		s+="\tprivate Set<Integer> keyPressed;\n";
		s+="\tboolean showId = true, showType = true;\n";
		s+= variables+"\n";

		return s;
	}


	/**
	 * Méthode permettant de générer le constructeur du formulaire IHM, en prenant en paramètres les variables à instancier
	 * @param variables les variables à instancier récupérées depuis le fichier XML de l'utilisateur
	 * @return retourne une String du corps qui sera par la suite écrite dans un fichier
	 */
	public String genererCorps(String variables)
	{
		/* Définition des paramètres d'affichage du formulaire et écriture des variables */

		String s = "";
		s+="\tpublic Formulaire()\n\t{\n";
		s+="\t\tsetLocation(200,200);\n";
		s+="\t\tsetDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);\n";
		s+="\t\tsetLayout(new GridBagLayout());\n";
		s+="\t\tkeyPressed = new HashSet<Integer>();\n";
		s+="\t\tmapObjetsId = new HashMap<Integer,Object>();\n";
		s+=variables;
		s+="\t\taddKeyListener(this);\n";
		s+="\t\tsetVisible(true);\n";
		s+="\t\trequestFocus();\n";
		s+="\t\tpack();\n";
		s+="\t}\n\n";

		/* Méthode ajoutée dans le programme Formulaire permettant la gestion des touches */

		s+="\tpublic void keyPressed(KeyEvent e)\n";
		s+="\t{";
		s+="\t\tkeyPressed.add(e.getKeyCode());\n";
		s+="\t\tif(keyPressed.contains(KeyEvent.VK_CONTROL) && keyPressed.contains(KeyEvent.VK_T))\n";
		s+="\t\t{\n";
		s+="\t\t\tthis.affichageTypes();\n";
		s+="\t\t}\n";
		s+="\t\tif(keyPressed.contains(KeyEvent.VK_CONTROL) && keyPressed.contains(KeyEvent.VK_I))\n";
		s+="\t\t{\n";
		s+="\t\t\tthis.affichageId();\n";
		s+="\t\t}\n";
		s+="\t}\n";
		s+="\tpublic void keyReleased(KeyEvent e)";
		s+="\t{\n";
		s+="\t\tkeyPressed.remove(e.getKeyCode());\n";
		s+="\t}\n";
		s+="\tpublic void keyTyped(KeyEvent e){}\n\n";

		/* Méthode ajoutée dans le programme Formulaire permettant la gestion de l'affichage ou non des ID */

		s+="\tprivate void affichageId()\n";
		s+="\t{\n";
		s+="\t\tif(showId)\n";
		s+="\t\t\tfor(JLabel jl : listeId)jl.setVisible(false);\n";
		s+="\t\telse\n";
		s+="\t\t\tfor(JLabel jl : listeId)jl.setVisible(true);\n";
		s+="\t\trevalidate();\n";
		s+="\t\tshowId = !showId;\n";
		s+="\t}\n\n";

		/* Méthode ajoutée dans le programme Formulaire permettant la gestion de l'affichage ou non des types */

		s+="\tprivate void affichageTypes()\n";
		s+="\t{\n";
		s+="\t\tif(showType)\n";
		s+="\t\t\tfor(JLabel jl : listeType)jl.setVisible(false);\n";
		s+="\t\telse\n";
		s+="\t\t\tfor(JLabel jl : listeType)jl.setVisible(true);\n";
		s+="\t\trevalidate();\n";
		s+="\t\tshowType = !showType;\n";
		s+="\t}\n\n";

		return s;
	}

	/**
	 * Méthode permettant de générer la partie main du formulaire IHM, pour lancer le programme.
	 * @return retourne une String du corps qui sera par la suite écrite dans un fichier
	 */
	public String genererPied()
	{
		String s = "";
		s+="\tpublic static void main(String[] args)\n\t{\n";
		s+="\t\tnew Formulaire();\n";
		s+="\t}\n}";

		return s;
	}

	/**
	 * Méthode permettant de générer toutes les variables à déclarer et instancier par la suite.
	 * La méthode parcourt le fichier XML à l'aide de Jdom et
	 * @return retourne une String du corps qui sera par la suite écrite dans un fichier
	 */
	public String[] genererVariables()
	{
		List<Element> listeObjet = racine.getChildren();
		String declarationVariable = "";
		String instanciationVariable = "";

		int gridY = 1;

		int compteurJtf = 0,compteurLabel = 0,compteurCheckbox = 0,
		compteurCombobox = 0,compteurRadiobutton = 0, compteurSpinner = 0;

		/* listes nécessaires pour certaines méthodes telles que affichageId ou affichageTypes */

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

					/* Traitement du cas pour le JTextField */

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
						instanciationVariable+="\t\tadd (new JLabel(\""+e.getAttributeValue("label")+"\"),gbc);\n\n";

						instanciationVariable+="\t\tgbc.gridx = 3;\n";
						instanciationVariable+="\t\tadd("+nomVariable+",gbc);\n";
						instanciationVariable+="\t\tmapObjetsId.put("+id+",(Object)"+nomVariable+");\n\n";

						instanciationVariable+="\t\tgbc.gridx = 4;\n";
						instanciationVariable+="\t\tgbc.gridy = "+gridY+";\n";
						instanciationVariable+="\t\tJLabel labelType"+id+" = new JLabel(\""+e.getAttributeValue("type")+"\");\n";
						instanciationVariable+="\t\tadd(labelType"+id+",gbc);\n";
						instanciationVariable+="\t\t"+nomVariable+".addKeyListener(this);\n";
						instanciationVariable+="\t\tlisteType.add(labelType"+id+");\n\n";
						gridY++;
					}
					break;

					/* Traitement du cas pour le JLabel (celui ci sera peu utilisé) */

					case "JLabel" :
					{
						String nomVariable = "jlabel"+ ++compteurLabel;
						String valeur = e.getAttributeValue("label");
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
						instanciationVariable+="\t\tmapObjetsId.put("+id+",(Object)"+nomVariable+");\n\n";

						instanciationVariable+="\t\tgbc.gridx = 4;\n";
						instanciationVariable+="\t\tgbc.gridy = "+gridY+";\n";
						gridY++;
					}
					break;

					/* Traitement du cas pour le JComboBox */

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
						instanciationVariable+="\t\tadd (new JLabel(\""+e.getAttributeValue("label")+"\"),gbc);\n\n";

						instanciationVariable+="\t\tgbc.gridx = 3;\n";
						for (Element elt : elements)
						{
							instanciationVariable+="\t\t"+nomVariable+".addItem(\""+elt.getText()+"\");\n";
							instanciationVariable+="\t\t"+nomVariable+".addKeyListener(this);\n";
						}
						instanciationVariable+="\t\tadd("+nomVariable+",gbc);\n";
						instanciationVariable+="\t\tmapObjetsId.put("+id+",(Object)"+nomVariable+");\n\n";

						instanciationVariable+="\t\tgbc.gridx = 4;\n";
						instanciationVariable+="\t\tgbc.gridy = "+gridY+";\n";
						instanciationVariable+="\t\tJLabel labelType"+id+" = new JLabel(\""+e.getAttributeValue("type")+"\");\n";
						instanciationVariable+="\t\tadd(labelType"+id+",gbc);\n";
						instanciationVariable+="\t\tlisteType.add(labelType"+id+");\n\n";
						gridY++;
					}
					break;

					/* Traitement du cas pour le JCheckBox */

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
						instanciationVariable+="\t\tadd (new JLabel(\""+e.getAttributeValue("label")+"\"),gbc);\n\n";

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
							instanciationVariable+="\t\t"+nomVariable+".addKeyListener(this);\n";
							instanciationVariable+="\t\tmapObjetsId.put("+id+",(Object)"+nomVariable+");\n\n";
							gridY++;
							instanciationVariable+="\t\tgbc.gridy = "+gridY+";\n";
						}
					}
					break;

					/* Traitement du cas pour le JRadioButton */

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
						instanciationVariable+="\t\tadd (new JLabel(\""+e.getAttributeValue("label")+"\"),gbc);\n\n";

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
							instanciationVariable+="\t\t"+nomVariable+".addKeyListener(this);\n";
							instanciationVariable+="\t\tmapObjetsId.put("+id+",(Object)"+nomVariable+");\n\n";
							gridY++;
							instanciationVariable+="\t\tgbc.gridy = "+gridY+";\n";
						}
					}
					break;

					/* Traitement du cas pour le JSpinner */

					case "JSpinner" :
					{
						List<Element> elements = e.getChildren();

						String nomVariable = "jspinner"+ ++compteurSpinner;
						declarationVariable+="\tprivate "+type+" "+nomVariable+";\n";
						instanciationVariable+="\t\t"+nomVariable+" = new "+type+"();\n";
						instanciationVariable+="\t\t"+nomVariable+".addKeyListener(this);\n";
						instanciationVariable+="\t\t"+nomVariable+".setPreferredSize(new Dimension("+largeur+",25));\n";
						instanciationVariable+="\t\tgbc.insets = new Insets(5, 10, 0, 5);\n\n";

						instanciationVariable+="\t\tgbc.gridx = 1;\n";
						instanciationVariable+="\t\tgbc.gridy = "+gridY+";\n";
						instanciationVariable+="\t\tJLabel labelId"+id+" = new JLabel(\""+id+"\");\n";
						instanciationVariable+="\t\tadd(labelId"+id+",gbc);\n";
						instanciationVariable+="\t\tlisteId.add(labelId"+id+");\n\n";

						instanciationVariable+="\t\tgbc.gridx = 2;\n";
						instanciationVariable+="\t\tadd (new JLabel(\""+e.getAttributeValue("label")+"\"),gbc);\n\n";

						instanciationVariable+="\t\tgbc.gridx = 3;\n";
						instanciationVariable+="\t\tadd("+nomVariable+",gbc);\n";
						instanciationVariable+="\t\t((JSpinner.DefaultEditor)"+nomVariable+".getEditor()).getTextField().addKeyListener(this);\n";
						instanciationVariable+="\t\tmapObjetsId.put("+id+",(Object)"+nomVariable+");\n\n";

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
		String[] formulaire = new String[]{declarationVariable,instanciationVariable};
		return formulaire;
	}

	/**
	 * Main du programme, permettant de faire l'assemblage des différentes partie du Formulaire
	 * Puis écrit le programme dans un fichier en .java à l'aide d'un PrintWriter.
	 */
	public static void main(String[] args)
	{
		GenererFormulaire generationFormulaire = new GenererFormulaire("Test2.xml");
		String[] formulaire = generationFormulaire.genererVariables();
		System.out.println(generationFormulaire.genererEntete(formulaire[0]));
		System.out.println(generationFormulaire.genererCorps(formulaire[1]));
		System.out.println(generationFormulaire.genererPied());

		String s = "";
		s+= generationFormulaire.genererEntete(formulaire[0]);
		s+= generationFormulaire.genererCorps(formulaire[1]);
		s+= generationFormulaire.genererPied();

		try {
			PrintWriter writer = new PrintWriter(new File("Formulaire.java"), "UTF-8");
			writer.println(s);
			writer.close();
		}
		catch (Exception e) {}
	}
}
