import javax.swing.*;
import java.awt.*;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeMap;
import java.io.File;

import org.jdom.*;
import org.jdom.input.*;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class Generateur
{

	private static Document document;
	private static Element racine;

	private ArrayList<Integer> keyEvent;
	private ArrayList<JLabel> listLabelId,listLabelType;

	public Generateur(String fichier)
	{
		listLabelId = new ArrayList<JLabel>();
		listLabelType = new ArrayList<JLabel>();
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
		pan.setSize(200,200);

		List<Element> listeObjet = racine.getChildren();


		TreeMap<Integer,Element> mapObjet = new TreeMap<Integer,Element>();
		for(Element e : listeObjet)
		{
			mapObjet.put(Integer.parseInt(e.getAttributeValue("id")),e);
		}
		for(int key : mapObjet.keySet())
		{
			Element e = mapObjet.get(key);

			String type = e.getName();
			int posX = Integer.parseInt(e.getAttributeValue("x"));
			int posY = Integer.parseInt(e.getAttributeValue("y"));
			int largeur = Integer.parseInt(e.getAttributeValue("largeur"));

			Component[] composants = null;

			switch (type)
			{
				case "JTextField" :
				{
					int id = Integer.parseInt(e.getAttributeValue("id"));
					pan.add(new JLabel(id+""));
					composants = pan.getComponents();
					composants[composants.length-1].setLocation(posX-20,posY);
					composants[composants.length-1].setSize(new Dimension(50,25));
					listLabelId.add((JLabel)composants[composants.length-1]);

					pan.add(new JTextField());
					composants = pan.getComponents();
					composants[composants.length-1].setLocation(posX+80,posY);
					composants[composants.length-1].setSize(new Dimension(largeur,35));
					((JTextField)(composants[composants.length-1])).setBorder(BorderFactory.createTitledBorder(e.getAttributeValue("label")));

					pan.add(new JLabel(e.getAttributeValue("type")));
					composants = pan.getComponents();
					composants[composants.length-1].setLocation(posX+90+largeur,posY);
					composants[composants.length-1].setSize(new Dimension(150,25));
					listLabelType.add((JLabel)composants[composants.length-1]);
				}
				break;

				case "JComboBox" :
				{
					int id = Integer.parseInt(e.getAttributeValue("id"));
					pan.add(new JLabel(id+""));
					composants = pan.getComponents();
					composants[composants.length-1].setLocation(posX-20,posY);
					composants[composants.length-1].setSize(new Dimension(50,25));
					listLabelId.add((JLabel)composants[composants.length-1]);

					pan.add(new JComboBox());
					composants = pan.getComponents();
					JComboBox jcb = (JComboBox)composants[composants.length-1];
					jcb.setLocation(posX+100,posY);
					jcb.setSize(largeur,40);
					jcb.setBorder(BorderFactory.createTitledBorder(e.getAttributeValue("label")+""));

					pan.add(new JLabel(e.getAttributeValue("type")));
					composants = pan.getComponents();
					composants[composants.length-1].setLocation(posX+120+largeur,posY);
					composants[composants.length-1].setSize(new Dimension(150,25));
					listLabelType.add((JLabel)composants[composants.length-1]);

					List<Element> elements = e.getChildren();
					for (Element elt : elements)
					{
						jcb.addItem(elt.getText());
					}
				}
				break;

				case "JCheckBox" :
				{
					int id = Integer.parseInt(e.getAttributeValue("id"));
					pan.add(new JLabel(id+""));
					composants = pan.getComponents();
					composants[composants.length-1].setLocation(posX-20,posY);
					composants[composants.length-1].setSize(new Dimension(50,25));
					listLabelId.add((JLabel)composants[composants.length-1]);

					pan.add(new JLabel(e.getAttributeValue("label")+""));
					composants = pan.getComponents();
					composants[composants.length-1].setLocation(posX,posY);
					composants[composants.length-1].setSize(new Dimension(150,25));

					pan.add(new JLabel(e.getAttributeValue("type")));
					composants = pan.getComponents();
					composants[composants.length-1].setLocation(posX+140+largeur,posY);
					composants[composants.length-1].setSize(new Dimension(150,25));
					listLabelType.add((JLabel)composants[composants.length-1]);

					List<Element> elements = e.getChildren();
					for (Element elt : elements)
					{
						pan.add(new JCheckBox(elt.getText()));
						composants = pan.getComponents();
						JCheckBox jcb = (JCheckBox) composants[composants.length-1];
						jcb.setLocation(posX+130,posY);
						jcb.setSize(largeur,25);
						posY+=20;
					}
				}
				break;

				case "JRadioButton" :
				{
					int id = Integer.parseInt(e.getAttributeValue("id"));
					pan.add(new JLabel(id+""));
					composants = pan.getComponents();
					composants[composants.length-1].setLocation(posX-20,posY);
					composants[composants.length-1].setSize(new Dimension(50,25));
					listLabelId.add((JLabel)composants[composants.length-1]);

					pan.add(new JLabel(e.getAttributeValue("label")+""));
					composants = pan.getComponents();
					composants[composants.length-1].setLocation(posX,posY);
					composants[composants.length-1].setSize(new Dimension(150,25));

					pan.add(new JLabel(e.getAttributeValue("type")));
					composants = pan.getComponents();
					composants[composants.length-1].setLocation(posX+largeur,posY);
					composants[composants.length-1].setSize(new Dimension(150,25));
					listLabelType.add((JLabel)composants[composants.length-1]);

					ButtonGroup bg = new ButtonGroup();

					List<Element> elements = e.getChildren();
					for (Element elt : elements)
					{
						pan.add(new JRadioButton(elt.getText()));
						composants = pan.getComponents();
						JRadioButton jcb = (JRadioButton) composants[composants.length-1];
						jcb.setLocation(posX+50,posY+=20);
						jcb.setSize(largeur,25);
						bg.add(jcb);
					}
				}
				break;

				case "JSpinner" :
				{
					int id = Integer.parseInt(e.getAttributeValue("id"));
					pan.add(new JLabel(id+""));
					composants = pan.getComponents();
					composants[composants.length-1].setLocation(posX-20,posY);
					composants[composants.length-1].setSize(new Dimension(50,25));
					listLabelId.add((JLabel)composants[composants.length-1]);

					pan.add(new JSpinner());
					composants = pan.getComponents();
					JSpinner jsp = (JSpinner) composants[composants.length-1];
					jsp.setLocation(posX+50,posY+=20);
					jsp.setSize(largeur,40);
					jsp.setBorder(BorderFactory.createTitledBorder(e.getAttributeValue("label")));

					pan.add(new JLabel(e.getAttributeValue("type")));
					composants = pan.getComponents();
					composants[composants.length-1].setLocation(posX+90+largeur,posY);
					composants[composants.length-1].setSize(new Dimension(150,25));
					listLabelType.add((JLabel)composants[composants.length-1]);
				}
				break;

				case "Tableau" :
				{
					int nbCol = 1;
					int id = Integer.parseInt(e.getAttributeValue("id"));
					int dimension = Integer.parseInt(e.getAttributeValue("dimension"));
					if(dimension == 2)nbCol = Integer.parseInt(e.getAttributeValue("col"));
					int nbLig = Integer.parseInt(e.getAttributeValue("lig"));
					int longueur = Integer.parseInt(e.getAttributeValue("longueur"));
					String typeRetour = e.getAttributeValue("type");
					posX = Integer.parseInt(e.getAttributeValue("x"));
					posY = Integer.parseInt(e.getAttributeValue("y"));
					largeur = Integer.parseInt(e.getAttributeValue("largeur"));

					pan.add(new JLabel(id+""));
					composants = pan.getComponents();
					composants[composants.length-1].setLocation(posX-20,posY);
					composants[composants.length-1].setSize(new Dimension(50,25));
					listLabelId.add((JLabel)composants[composants.length-1]);

					pan.add(new JLabel(e.getAttributeValue("label")+""));
					composants = pan.getComponents();
					composants[composants.length-1].setLocation(posX-60,posY+20);
					composants[composants.length-1].setSize(new Dimension(150,25));

					Tableau tab = new Tableau(typeRetour,nbLig,nbCol,dimension,posX,posY,longueur,largeur);
					pan.add(tab);
					composants = pan.getComponents();
					composants[composants.length-1].setLocation(posX,posY);
					composants[composants.length-1].setSize(longueur,largeur);
					pan.add(new JLabel(e.getAttributeValue("type")));
					composants = pan.getComponents();
					composants[composants.length-1].setLocation(posX+largeur,posY);
					composants[composants.length-1].setSize(new Dimension(150,25));
					listLabelType.add((JLabel)composants[composants.length-1]);
				}
				break;

				case "Calendrier" :
				{
					int id = Integer.parseInt(e.getAttributeValue("id"));
					int longueur = Integer.parseInt(e.getAttributeValue("longueur"));
					String typeRetour = e.getAttributeValue("type");
					posX = Integer.parseInt(e.getAttributeValue("x"));
					posY = Integer.parseInt(e.getAttributeValue("y"));
					largeur = Integer.parseInt(e.getAttributeValue("largeur"));

					pan.add(new JLabel(id+""));
					composants = pan.getComponents();
					composants[composants.length-1].setLocation(posX-10,posY);
					composants[composants.length-1].setSize(new Dimension(50,25));
					listLabelId.add((JLabel)composants[composants.length-1]);

					Calendrier cal = new Calendrier(longueur,largeur);
					pan.add(cal);
					composants = pan.getComponents();
					composants[composants.length-1].setLocation(posX,posY);
					composants[composants.length-1].setSize(575,largeur);
					pan.add(new JLabel(e.getAttributeValue("type")));
					composants = pan.getComponents();
					composants[composants.length-1].setLocation(posX+90+largeur,posY);
					composants[composants.length-1].setSize(new Dimension(150,25));
					listLabelType.add((JLabel)composants[composants.length-1]);
				}
				break;

				default: continue;
			}
		}
		return pan;
	}

	public List<JLabel> getListlabelType(){return this.listLabelType;}
	public List<JLabel> getListlabelId()  {return this.listLabelId  ;}
}
