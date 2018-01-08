import java.awt.*;
import javax.swing.*;
import java.util.*;

public class Formulaire extends JFrame
{
	ArrayList<Object> listeObjet;
	private JTextField jtextfield1;
	private JTextField jtextfield2;
	private JLabel jlabel1;
	private JComboBox jcombobox1;
	public Formulaire()
	{
		setSize(500,500);
		setLocation(200,200);
		setLayout(new GridBagLayout());
		listeObjet = new ArrayList<Object>();
		GridBagConstraints gbc = new GridBagConstraints();

		jtextfield1 = new JTextField();
		jtextfield1.setPreferredSize(new Dimension(150,25));
		gbc.insets = new Insets(5, 10, 0, 5);
		gbc.gridx = 1;
		gbc.gridy = 1;
		add (new JLabel("Nom"),gbc);
		gbc.gridx = 2;
		add(jtextfield1,gbc);
		listeObjet.add(jtextfield1);

		jtextfield2 = new JTextField();
		jtextfield2.setPreferredSize(new Dimension(200,25));
		gbc.insets = new Insets(5, 10, 0, 5);
		gbc.gridx = 1;
		gbc.gridy = 2;
		add (new JLabel("Prenom"),gbc);
		gbc.gridx = 2;
		add(jtextfield2,gbc);
		listeObjet.add(jtextfield2);

		jlabel1 = new JLabel("coucou");
		jlabel1.setSize(150,50);
		gbc.insets = new Insets(5, 10, 0, 5);
		gbc.gridx = 1;
		gbc.gridy = 3;
		add(jlabel1,gbc);
		listeObjet.add(jlabel1);
		jcombobox1 = new JComboBox();
		jcombobox1.setPreferredSize(new Dimension(200,25));
		gbc.insets = new Insets(5, 10, 0, 5);
		gbc.gridx = 1;
		gbc.gridy = 4;
		add (new JLabel("Nom de la paire "),gbc);
		gbc.gridx = 2;
		jcombobox1.addItem(" rouge");
		jcombobox1.addItem(" bleu");
		jcombobox1.addItem(" noire");
		add(jcombobox1,gbc);
		listeObjet.add(jcombobox1);

		setVisible(true);
	}
	public static void main(String[] args)
	{
		new Formulaire();
	}
}
