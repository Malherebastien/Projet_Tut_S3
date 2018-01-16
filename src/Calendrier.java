import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.BorderFactory;
import java.awt.Color;

/**
 * La classe Calendrier permet de selectioner une date via l'année, le mois, et le jour.
 * Le nombre de jours change en fonction du mois et de l'année sélectionné.
 *
 * @author cofni
 * @version 15/01/18
 */
public class Calendrier extends JPanel implements ActionListener, ItemListener, ChangeListener
{
	/**
	 * Le numéro du jour sélectionné.
	 * Il peut être modifié en en cliquant sur les boutons du panelJour.
	 *
	 * @see Calendrier#panelJour
	 * @see Calendrier#tabButton
	 */
	private int 				numJ;

	/**
	 * Le panel qui contient tout les jours du mois sélectionné.
	 * Il se remet à jour automatiquement grâce aux méthodes viderCache() et remplirJour()
	 *
	 * @see Calendrier#viderCache()
	 * @see Calendrier#remplirJour()
	 * @see Calendrier#numJ
	 */
	private JPanel 				panelJour;

	/**
	 * La JComboBox sert à choisir un mois dans la liste proposée.
	 * Le changement de la valeur du mois sélectionné dans la ComboBox entraine si besoins est, le rafraîchissement du panel jour.
	 * Notement pour le mois de février à 28 jours et 29 lors des années bisextiles et les mois qui ont 30 ou 31 jours.
	 *
	 * @see Calendrier#panelJour
	 * @see Calendrier#annee
	 * @see Calendrier#numJ
	 * @see Calendrier#cbMois
	 */
	private JComboBox<String> 	cbMois;

	/**
	 * Le JSpinner sert à choisir l'année.
	 * Quand sa valeur change, le panelJour se remet à jour si le mois est février et que l'année modulo 4 est égal à 0
	 *
	 * @see Calendrier#annee
	 * @see Calendrier#panelJour
	 */

	private JSpinner 			annee;
	/**
	 * Cette arraylist qui contient tout les boutons qui représentent le nombre de jours dans le mois sélectionné.
	 * Quand un bouton est sélectionné, la valeur de la variable numJ prends la valeur du bouton qui a été sélectionné.
	 *
	 * @see Calendrier#panelJour
	 * @see Calendrier#numJ
	 */
	private ArrayList<JButton> 	tabButton = new ArrayList<JButton>();

	/**
	 * Ce tableau de String contient les douzes mois de l'année et est parcourue afin de remplir la comboBox cbMois.
	 *
	 * @see Calendrier#cbMois
	 */
	private String[] tabMois = { "janvier", "fevrier", "mars", "avril", "mai", "juin", "juillet", "août", "septembre", "octobre", "novembre", "decembre" };


	/** javadoc à faire, c'est la variable pour get une date */
	private String date;



	/**
	 * Constructeur qui initialise la JFrame du calendrier
	 *
	 * @see Calendrier#cbMois
	 * @see Calendrier#numJ
	 * @see Calendrier#annee
	 * @see Calendrier#panelJour
	 *
	 * @see Calendrier#remplirJour()
	 */
	public Calendrier(int longueur,int largeur) {
		// Initialisation de la frame
		this.setSize(longueur,largeur);
		this.setLocation(100, 100);
		this.setLayout(new GridBagLayout());

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 10, 0, 5);

		this.cbMois = new JComboBox<String>();
		this.numJ   = 0;

		gbc.gridx = 1;
		gbc.gridx = 1;

		// On remplit la checkbox des mois avec le tableau qui contient les String
		for (int i = 0; i < this.tabMois.length; i++)
			this.cbMois.addItem(this.tabMois[i]);

		this.cbMois.addItemListener(this);
		add(this.cbMois, gbc);

		// On remplis initialise le spinner des annees, il va de -1000 à 5000, son pas est de 1 annee et il est par default en 2018
		gbc.gridx  = 2;
		this.annee = new JSpinner(new SpinnerNumberModel(2018, -1000, 5000, 1));
		this.annee.addChangeListener(this);
		add(this.annee, gbc);

		// On initialise le panel des jours et on le rempli avec la methode remplirJour()
		this.panelJour = new JPanel();
		this.panelJour.setLayout(new GridLayout(6, 6));

		remplirJour();

		gbc.gridx = 2;
		gbc.gridy = 1;
		add(this.panelJour);

		setVisible(true);
		setBorder(BorderFactory.createTitledBorder("Calendrier"));
	}

	/**
	 * Méthode private qui sert à la fois à créer les boutons des jours du calendrier et qui sert à les ajouter au panel principal.
	 *
	 * @see Calendrier#panelJour
	 * @see Calendrier#cbMois
	 * @see Calendrier#tabButton
	 *
	 * @see Calendrier#viderCache()
	 */
	private void remplirJour()
	{
		viderCache();

		for (int i = 0; i < 7; i++)
			this.panelJour.add(new JLabel());

		/* Remplir les cases avec le numeros des jours */
		if (((this.cbMois.getSelectedIndex() < 7) && this.cbMois.getSelectedIndex() % 2 == 0) || (this.cbMois.getSelectedIndex() >= 7) && this.cbMois.getSelectedIndex() % 2 != 0)
			for (int i = 1; i <= 31; i++) {											// Si le mois est avant Aout que son index modulo 2 est egal a 0, ou qu'il est egal ou apres Aout et son index modulo deux est different de 0, on remplit la grille avec 31 jours
				this.tabButton.add(new JButton("" + i));
				this.tabButton.get(i - 1).addActionListener(this);
				this.panelJour.add(this.tabButton.get(i - 1));
			}
		else  																		// Les cas pour traiter fevrier (soit 28 soit 29)
			if (this.cbMois.getSelectedItem().toString().equals("fevrier"))
			{
				if (Integer.parseInt(this.annee.getValue().toString()) % 4 != 0)
				{																	// Si c'est une année bisextile on remplpis le panel avec 28 jours
					for (int i = 1; i <= 28; i++)
					{
						this.tabButton.add(new JButton("" + i));
						this.tabButton.get(i - 1).addActionListener(this);
						this.panelJour.add(this.tabButton.get(i - 1));
					}
					this.panelJour.add(new JLabel(""));
				}
				else																// Sinon on le remplis avec 29 jours
					for (int i = 1; i <= 29; i++)
					{
						this.tabButton.add(new JButton("" + i));
						this.tabButton.get(i-1).addActionListener(this);
						this.panelJour.add(this.tabButton.get(i - 1));

					}
				this.panelJour.add(new JLabel(""));
			}
			else																	// Sinon on remplis le panel avec 30 jours
				for (int i = 1; i <= 30; i++) {
					this.tabButton.add(new JButton("" + i));
					this.tabButton.get(i - 1).addActionListener(this);
					this.panelJour.add(this.tabButton.get(i - 1));
				}
	}

	private void setDate(String s){this.date = s;}

	public String getDate(){return this.date;}

	/**
	 * La méthode viderCache() efface tout les boutons contenus dans le panelJour.
	 *
	 * @see Calendrier#panelJour
	 */
	private void viderCache() {
		this.panelJour.removeAll();
	}

	@Override
	/**
	 * La méthode retourne la date dans une String, au format JJ/MM/AAAA.
	 * @return String
	 */
	public String toString() {
		String s = (this.numJ < 10) ? ("0" + this.numJ) : (this.numJ + "");		// On ajoute 0 devant le nombre du jour selectionné si celui-ci est un chiffre

		// On remplis la chaîne au format JJ/MM/AAAA
		for (int i = 0; i < this.tabMois.length; i++)
			if (this.tabMois[i].equals(this.cbMois.getSelectedItem()))
				s += "/" + this.cbMois.getSelectedItem();

		s += "/" + this.annee.getValue();
		return s;

	}

	/**
	 * Lorsque que l'un des boutons du jour est selectioné, la variable numJ change et prends la valeur du bouton sélectionné.
	 */
	public void actionPerformed(ActionEvent e) {
		for (int i = 0; i < this.tabButton.size(); i++)
			if (e.getSource() == this.tabButton.get(i))
			{
				this.numJ = Integer.parseInt((this.tabButton.get(i).getText()));
				setDate(this.numJ+"/"+this.cbMois.getSelectedItem()+"/"+annee.getValue());
			}
	}

	/**
	 * Lorsque la valeur de la JComboBox change les valeurs du panel et on le remet à jour.
	 */
	public void itemStateChanged(ItemEvent e) {

		// On remet le panel à jour quand on change le mois
		remplirJour();
		this.panelJour.repaint();
	}

	/**
	 * Lorsque la valeur du JSpinner change les valeurs du panel et on le remet à jour.
	 */
	public void stateChanged(ChangeEvent e) {
		// On remet le panel à jour quand on change l'annee
		remplirJour();
		this.panelJour.repaint();
	}

	/**
	 * !!!! A 	ENLEVER !!!!! NE SERT QUE POUR LE TEST !!!!!!
	 * @param args
	 */
	public static void main(String[] args) {
		Calendrier c = new Calendrier(500,500);
		System.out.println(c.toString());
	}
}
