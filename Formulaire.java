import java.awt.*;
import javax.swing.*;
import java.util.*;

public class Formulaire extends JFrame
{
	ArrayList<Object> listeObjet;
	ArrayList<JLabel> listeId;
	ArrayList<JLabel> listeType;
	private JTextField jtextfield1;
	private JTextField jtextfield2;
	private JLabel jlabel1;
	private JComboBox jcombobox1;
	private JCheckBox jcheckbox1;
	private JCheckBox jcheckbox2;
	private JCheckBox jcheckbox3;
	private JLabel jlabel2;
	private JRadioButton jradiobutton1;
	private JRadioButton jradiobutton2;
	private JRadioButton jradiobutton3;
	private JRadioButton jradiobutton4;
	private JSpinner jspinner1;

	public Formulaire()
	{
		setLocation(200,200);
		setLayout(new GridBagLayout());
		listeObjet = new ArrayList<Object>();
		listeId = new ArrayList<JLabel>();
		listeType = new ArrayList<JLabel>();
		GridBagConstraints gbc = new GridBagConstraints();

		jtextfield1 = new JTextField();
		jtextfield1.setPreferredSize(new Dimension(150,25));
		gbc.insets = new Insets(5, 10, 0, 5);

		gbc.gridx = 1;
		gbc.gridy = 1;
		JLabel labelId1 = new JLabel("1");
		add(labelId1,gbc);
		listeId.add(labelId1);

		gbc.gridx = 2;
		add (new JLabel("Nom"),gbc);

		gbc.gridx = 3;
		add(jtextfield1,gbc);
		listeObjet.add(jtextfield1);

		gbc.gridx = 4;
		gbc.gridy = 1;
		JLabel labelType1 = new JLabel("String");
		add(labelType1,gbc);
		listeType.add(labelType1);

		jtextfield2 = new JTextField();
		jtextfield2.setPreferredSize(new Dimension(200,25));
		gbc.insets = new Insets(5, 10, 0, 5);

		gbc.gridx = 1;
		gbc.gridy = 2;
		JLabel labelId2 = new JLabel("2");
		add(labelId2,gbc);
		listeId.add(labelId2);

		gbc.gridx = 2;
		add (new JLabel("Prenom"),gbc);

		gbc.gridx = 3;
		add(jtextfield2,gbc);
		listeObjet.add(jtextfield2);

		gbc.gridx = 4;
		gbc.gridy = 2;
		JLabel labelType2 = new JLabel("String");
		add(labelType2,gbc);
		listeType.add(labelType2);

		jlabel1 = new JLabel("Partie 1");
		jlabel1.setSize(150,50);
		gbc.insets = new Insets(5, 10, 0, 5);

		gbc.gridx = 1;
		gbc.gridy = 3;
		JLabel labelId3 = new JLabel("3");
		gbc.anchor = GridBagConstraints.WEST;
		add(labelId3,gbc);
		listeId.add(labelId3);

		gbc.gridx = 3;
		add(jlabel1,gbc);
		listeObjet.add(jlabel1);

		gbc.gridx = 4;
		gbc.gridy = 3;
		JLabel labelType3 = new JLabel("void");
		add(labelType3,gbc);
		listeType.add(labelType3);

		jcombobox1 = new JComboBox();
		jcombobox1.setPreferredSize(new Dimension(200,25));
		gbc.insets = new Insets(5, 10, 0, 5);

		gbc.gridx = 1;
		gbc.gridy = 4;
		JLabel labelId4 = new JLabel("4");
		add(labelId4,gbc);
		listeId.add(labelId4);

		gbc.gridx = 2;
		add (new JLabel("Choix couleur"),gbc);

		gbc.gridx = 3;
		jcombobox1.addItem("rouge");
		jcombobox1.addItem("bleu");
		jcombobox1.addItem("vert");
		add(jcombobox1,gbc);
		listeObjet.add(jcombobox1);

		gbc.gridx = 4;
		gbc.gridy = 4;
		JLabel labelType4 = new JLabel("String");
		add(labelType4,gbc);
		listeType.add(labelType4);

		gbc.insets = new Insets(5, 10, 0, 5);

		gbc.gridx = 1;
		gbc.gridy = 5;
		JLabel labelId5 = new JLabel("5");
		add(labelId5,gbc);
		listeId.add(labelId5);

		gbc.gridx = 2;
		add (new JLabel("Nom de la paire"),gbc);

		gbc.gridx = 4;
		gbc.gridy = 5;
		JLabel labelType5 = new JLabel("String");
		add(labelType5,gbc);
		listeType.add(labelType5);

		gbc.gridx = 3;
		jcheckbox1 = new JCheckBox("rouge");
		add(jcheckbox1,gbc);
		listeObjet.add(jcheckbox1);

		gbc.gridy = 6;
		jcheckbox2 = new JCheckBox("bleu");
		add(jcheckbox2,gbc);
		listeObjet.add(jcheckbox2);

		gbc.gridy = 7;
		jcheckbox3 = new JCheckBox("vert");
		add(jcheckbox3,gbc);
		listeObjet.add(jcheckbox3);

		gbc.gridy = 8;
		jlabel2 = new JLabel("Partie 1");
		jlabel2.setSize(150,50);
		gbc.insets = new Insets(5, 10, 0, 5);

		gbc.gridx = 1;
		gbc.gridy = 8;
		JLabel labelId6 = new JLabel("6");
		gbc.anchor = GridBagConstraints.WEST;
		add(labelId6,gbc);
		listeId.add(labelId6);

		gbc.gridx = 3;
		add(jlabel2,gbc);
		listeObjet.add(jlabel2);

		gbc.gridx = 4;
		gbc.gridy = 8;
		JLabel labelType6 = new JLabel("void");
		add(labelType6,gbc);
		listeType.add(labelType6);

		gbc.insets = new Insets(5, 10, 0, 5);

		gbc.gridx = 1;
		gbc.gridy = 9;
		JLabel labelId7 = new JLabel("7");
		add(labelId7,gbc);
		listeId.add(labelId7);

		gbc.gridx = 2;
		add (new JLabel("Choix unique"),gbc);

		gbc.gridx = 4;
		gbc.gridy = 9;
		JLabel labelType7 = new JLabel("int");
		add(labelType7,gbc);
		listeType.add(labelType7);

		gbc.gridx = 3;
		ButtonGroup bg = new ButtonGroup();
		jradiobutton1 = new JRadioButton("1");
		bg.add(jradiobutton1);
		add(jradiobutton1,gbc);
		listeObjet.add(jradiobutton1);

		gbc.gridy = 10;
		jradiobutton2 = new JRadioButton("2");
		bg.add(jradiobutton2);
		add(jradiobutton2,gbc);
		listeObjet.add(jradiobutton2);

		gbc.gridy = 11;
		jradiobutton3 = new JRadioButton("3");
		bg.add(jradiobutton3);
		add(jradiobutton3,gbc);
		listeObjet.add(jradiobutton3);

		gbc.gridy = 12;
		jradiobutton4 = new JRadioButton("4");
		bg.add(jradiobutton4);
		add(jradiobutton4,gbc);
		listeObjet.add(jradiobutton4);

		gbc.gridy = 13;
		jspinner1 = new JSpinner();
		jspinner1.setPreferredSize(new Dimension(200,25));
		gbc.insets = new Insets(5, 10, 0, 5);

		gbc.gridx = 1;
		gbc.gridy = 13;
		JLabel labelId8 = new JLabel("8");
		add(labelId8,gbc);
		listeId.add(labelId8);

		gbc.gridx = 2;
		add (new JLabel("Spinneeeeeeeer"),gbc);

		gbc.gridx = 3;
		add(jspinner1,gbc);
		listeObjet.add(jspinner1);

		gbc.gridx = 4;
		gbc.gridy = 13;
		JLabel labelType8 = new JLabel("void");
		add(labelType8,gbc);
		listeType.add(labelType8);

		setVisible(true);
		pack();
	}

	public static void main(String[] args)
	{
		new Formulaire();
	}
}
