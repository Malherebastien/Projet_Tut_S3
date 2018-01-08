import java.awt.*;
import javax.swing.*;
import java.util.*;

public class Formulaire extends JFrame
{
	ArrayList<Object> listeObjet;
	private JTextField jtextfield1;
	private JTextField jtextfield2;
	private JLabel jlabel1;
	public Formulaire()
	{
		setSize(500,500);
		setLocation(200,200);
		setLayout(new GridBagLayout());
		listeObjet = new ArrayList<Object>();

		jtextfield1 = new JTextField();
		jtextfield1.setSize(150,100);
		add(jtextfield1);
		listeObjet.add(jtextfield1);

		jtextfield2 = new JTextField();
		jtextfield2.setSize(300,100);
		add(jtextfield2);
		listeObjet.add(jtextfield2);

		jlabel1 = new JLabel("coucou");
		jlabel1.setSize(150,50);
		add(jlabel1);
		listeObjet.add(jlabel1);
		setVisible(true);
	}
	public static void main(String[] args)
	{
		new Formulaire();
	}
}
