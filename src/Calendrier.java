import javax.swing.*;
import java.awt.*;
import java.util.*;

public class Calendrier extends JFrame
{
	private JPanel panelFinal, panelMois, panelJour;
	private JButton moisPrc, moisSvt;
	private JComboBox cbMois;
	private JSpinner annee;
	private String[] tabMois = {"janvier","fevrier","mars","avril","mai","juin","juillet","août","septembre","octobre","novembre","decembre"};

	public Calendrier()
	{
		this.setTitle("Calendier");
		this.setLocationRelativeTo(null);

		panelFinal = new JPanel();
		panelFinal.setLayout(new GridLayout(2,1));

		/* Choix du mois et de l'année */
		panelMois = new JPanel();
		moisPrc = new JButton("<<");
		cbMois = new JComboBox();
		for (int i = 0; i < tabMois.length; i++)
			cbMois.addItem(tabMois[i]);
		annee = new JSpinner();

		moisSvt = new JButton(">>");

		panelMois.add(moisPrc);
		panelMois.add(cbMois);
		panelMois.add(annee);
		panelMois.add(moisSvt);

		panelFinal.add(panelMois);

		/* Choix des jours */
		panelJour = new JPanel();
		panelJour.setLayout(new GridLayout(5,7));

		for(int i = 1; i < 32; i++)
		{
			panelJour.add(new JButton(""+i));
		}
		panelFinal.add(panelJour);

		add(panelFinal);

		setVisible(true);
		pack();
	}

	public static void main(String[] args) 
	{
		new Calendrier();
	}
}