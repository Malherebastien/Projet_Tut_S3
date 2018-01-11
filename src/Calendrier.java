import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Calendrier extends JFrame implements ActionListener
{
	private JPanel     panelJour;
	private JComboBox  cbMois;
	private JSpinner   annee;
	private String[][] tabMois = {{"janvier","fevrier","mars","avril","mai","juin","juillet","août","septembre","octobre","novembre","decembre"},
								  {"31","28","31","30","31","30","31","31","30","31","30","31"}};
	private JButton[] tabBouton = new JButton[200];

	public Calendrier()
	{
		this.setTitle("Calendrier");
		this.setLocationRelativeTo(null);
		this.setLayout(new GridBagLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 10, 0, 5);

		/* Choix du mois et de l'année */
		gbc.gridx = 1;
		gbc.gridx = 1;

		cbMois = new JComboBox();
		for (int i = 0; i < tabMois[0].length; i++)
		{
			cbMois.addItem(tabMois[0][i]);
		}
		cbMois.addActionListener(this);
		add(cbMois,gbc);

		gbc.gridx = 2;
		annee = new JSpinner(new SpinnerNumberModel(2018,-1000,5000,1));
		add(annee,gbc);
		
		/* Choix des jours */
		panelJour = new JPanel();
		panelJour.setLayout(new GridLayout(5,7));

		for (int i = 0; i < 12; i++)
		{
			for (int j = 1; j <= Integer.parseInt(tabMois[1][i]); j++)
			{
				tabBouton[j] = new JButton(""+j);
				tabBouton[j].addActionListener(this);
			}
		}

		for (int i = 1; i <= Integer.parseInt(tabMois[1][1]); i++)
		{
			panelJour.add(tabBouton[i]);
		}

		gbc.gridx = 2;
		gbc.gridy = 1;
		add(panelJour);
		
		setVisible(true);
		pack();
	}

	public void actionPerformed(ActionEvent e)
	{
		for (int i = 0; i < tabBouton.length; i++)
		{
			if (e.getSource() == tabBouton[i]) 
				System.out.println("Nous sommes le " + tabBouton[i].getText() + " " + cbMois.getSelectedItem() + " " + annee.getValue());
		}
	}

	public static void main(String[] args) 
	{
		new Calendrier();
	}
}