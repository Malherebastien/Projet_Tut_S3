import org.jdom.*;
import org.jdom.input.*;
import java.util.List;
import java.io.File;
import java.io.PrintWriter;

public class genererFormulaire
{
	private static Document document;
	private static Element racine;

	public genererFormulaire(String fichier)
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

		instanciationVariable+="\t\tlisteObjet = new ArrayList<Object>();\n\n";

		for (Element e : listeObjet )
		{
			int id = Integer.parseInt(e.getAttributeValue("id"));
			int posX = Integer.parseInt(e.getAttributeValue("x"));
			int posY = Integer.parseInt(e.getAttributeValue("y"));
			int largeur = Integer.parseInt(e.getAttributeValue("largeur"));
			String type = e.getAttributeValue("type");
			String nomVariable = e.getText();

			switch (type)
			{
				case "JTextField" :
				{
					declarationVariable+="\tprivate "+type+" "+nomVariable+";\n";
					instanciationVariable+="\t\t"+nomVariable+" = new "+type+"();\n";
					instanciationVariable+="\t\t"+nomVariable+".setSize("+posX+","+posY+");\n";
					instanciationVariable+="\t\tadd("+nomVariable+");\n";
					instanciationVariable+="\t\tlisteObjet.add("+nomVariable+");\n\n";
				}
				break;
				case "JLabel" :
				{
					String valeur = e.getAttributeValue("value");
					declarationVariable+="\tprivate "+type+" "+nomVariable+";\n";
					if(!valeur.equals(null))
					{
						instanciationVariable+="\t\t"+nomVariable+" = new "+type+"(\""+valeur+"\");\n";
					}
					else
						instanciationVariable+="\t\t"+nomVariable+" = new "+type+"();\n";
					instanciationVariable+="\t\t"+nomVariable+".setSize("+posX+","+posY+");\n";
					instanciationVariable+="\t\tadd("+nomVariable+");\n";
					instanciationVariable+="\t\tlisteObjet.add("+nomVariable+");\n";
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
		genererFormulaire generationFormulaire = new genererFormulaire("test.xml");
		String[] textes = generationFormulaire.genererVariables();
		System.out.println(generationFormulaire.genererEntete(textes[0]));
		System.out.println(generationFormulaire.genererCorps(textes[1]));
		System.out.println(generationFormulaire.genererPied());

		String s = "";
		s += generationFormulaire.genererEntete(textes[0]);
		s += generationFormulaire.genererCorps(textes[1]);
		s += generationFormulaire.genererPied();

		try {
			PrintWriter writer = new PrintWriter(new File("Formulaire.java"), "UTF-8");
			writer.println(s);
			writer.close();
		}
		catch (Exception e) {}
	}
}
