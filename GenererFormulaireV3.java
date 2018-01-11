import javax.swing.*;
import java.awt.*;

import java.util.List;
import java.io.File;

import org.jdom.*;
import org.jdom.input.*;

public class GenererFormulaireV3
{

	private static Document document;
	private static Element racine;

	public GenererFormulaireV3(String fichier)
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

	public JPanel createForm()
	{
		JPanel pan = new JPanel(null);
		List<Element> listeObjet = racine.getChildren();

		for(Element e : listeObjet)
		{
			String type = e.getName();
			int id = Integer.parseInt(e.getAttributeValue("id"));
			int posX = Integer.parseInt(e.getAttributeValue("x"));
			int posY = Integer.parseInt(e.getAttributeValue("y"));
			int largeur = Integer.parseInt(e.getAttributeValue("largeur"));

			Component[] composants = null;

			switch (type)
			{
				case "JTextField" :
				{
					pan.add(new JLabel(id+""));
					composants = pan.getComponents();
					System.out.println(composants.length);
					composants[composants.length-1].setLocation(posX,posY-10);
					composants[composants.length-1].setSize(new Dimension(50,25));
					pan.add(new JLabel(e.getAttributeValue("id")+""));
					composants = pan.getComponents();
					composants[composants.length-1].setLocation(posX,posY-10);
					composants[composants.length-1].setSize(new Dimension(50,25));
					pan.add(new JTextField());
					composants = pan.getComponents();
					composants[composants.length-1].setLocation(posX,posY);
					composants[composants.length-1].setSize(new Dimension(largeur,25));
				}
				break;
			}
		}

		return pan;
	}
}
