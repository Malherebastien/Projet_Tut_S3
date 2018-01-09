import java.awt.*;
import javax.swing.*;
import java.util.*;

public class Formulaire extends JFrame
{
	ArrayList<Object> listeObjet;
	ArrayList<JLabel> listeId;
	private JTextField jtextfield1;
	private JTextField jtextfield2;
	private JLabel jlabel1;
	private JComboBox jcombobox1;
	private JCheckBox jcheckbox1;
	private JCheckBox jcheckbox2;
	private JCheckBox jcheckbox3;
	private JLabel jlabel2;

	public Formulaire()
	{
		setLocation(200,200);
		setLayout(new GridBagLayout());
		listeObjet = new ArrayList<Object>();
		listeId = new ArrayList<JLabel>();
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

		gbc.insets = new Insets(5, 10, 0, 5);

		gbc.gridx = 1;
		gbc.gridy = 5;
		JLabel labelId5 = new JLabel("5");
		add(labelId5,gbc);
		listeId.add(labelId5);

		gbc.gridx = 2;
		add (new JLabel("Nom de la paire"),gbc);

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

		setVisible(true);
		pack();
	}
	public static void main(String[] args)
	{
		new Formulaire();
	}
}
