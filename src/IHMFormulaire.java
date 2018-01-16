import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import java.util.HashMap;
import javax.swing.UIManager;

/**
 * @author : cofni
 * Classe IHM permettant la gestion du formulaire créé par le générateur
 */
public class IHMFormulaire extends JFrame implements KeyListener, ActionListener
{
	/**
	 * Attribut de type boolean permettant de savoir si la Frame est fermé ou non
	 * @see IHMFormulaire#IHMFormulaire
	 * @see IHMFormulaire#actionPerformed
	 * @see IHMFormulaire#createForm
	 */
	private static boolean termine;

	/**
	 * Attribut de type boolean permettant de savoir si l'id est affiché ou non
	 * @see IHMFormulaire#IHMFormulaire
	 * @see IHMFormulaire#affichageId
	 */
	private boolean showId;

	/**
	 * Attribut de type boolean permettant de savoir si le type est affiché ou non
	 * @see IHMFormulaire#IHMFormulaire
	 * @see IHMFormulaire#affichageType
	 */
	private boolean showType;

	/**
	 * Attribut de type Generateur permettant de créer le générateur et de l'utiliser dans la classe
	 * @see IHMFormulaire#IHMFormulaire
	 * @see IHMFormulaire#affichageId
	 * @see IHMFormulaire#affichageType
	 */
	private Generateur gen;

	/**
	 * Attribut de type JPanel permettant de créer la fenêtre de saisie
	 * @see IHMFormulaire#IHMFormulaire
	 * @see IHMFormulaire#resetComponent
	 * @see IHMFormulaire#actionPerformed
	 * @see IHMFormulaire#addKeyListener
	 * @see IHMFormulaire#getPan
	 */
	private JPanel pan;

	/**
	 * Attribut de type ArrayList de Integer permettant la gestion de deux touches en même temps
	 * @see IHMFormulaire#IHMFormulaire
	 * @see IHMFormulaire#keyPressed
	 * @see IHMFormulaire#keyReleased
	 */
	private ArrayList<Integer> keyEvent;

	/**
	 * Attribut de type JPanel permettant de créer une fenêtre pour ajouter les boutons reset et confirm
	 * @see IHMFormulaire#IHMFormulaire
	 */
	private JPanel flowLayout;

	/**
	 * Attribut de type JButton permettant de créer et d'utiliser le bouton pour réinitialiser le formulaire
	 * @see IHMFormulaire#IHMFormulaire
	 * @see IHMFormulaire#actionPerformed
	 */
	private JButton reset;

	/**
	 * Attribut de type JButton permettant de créer et d'utiliser le bouton pour valider le formulaire
	 * @see IHMFormulaire#IHMFormulaire
	 * @see IHMFormulaire#actionPerformed
	 */
	private JButton confirm;

	/**
	 * Constructeur permettant de créer la Frame du formulaire
	 * @param fichier est le nom du fichier pour extraire le fichier XML
	 */
	public IHMFormulaire(String fichier)
	{
		termine = false;
		setLayout(new BorderLayout());
		setTitle("Formulaire");
		setSize(1000,1000);
		setExtendedState(JFrame.MAXIMIZED_BOTH);

		gen = new Generateur(fichier);
		pan = gen.createForm();
		JScrollPane jsp = new JScrollPane(pan);
		add(jsp, BorderLayout.CENTER);

		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		flowLayout = new JPanel(new FlowLayout(2));

		keyEvent = new ArrayList<Integer>();
		showId = showType = true;
		reset = new JButton("Effacer");
		reset.addActionListener(this);
		confirm = new JButton("Confirmer");
		confirm.addActionListener(this);
		addKeyListener(this);
		addKeyListener(pan);

		flowLayout.add(reset);
		flowLayout.add(confirm);
		add(flowLayout,BorderLayout.SOUTH);

		this.affichageId();
		this.affichageType();
	}

	/**
	 *
	 */
	public void keyPressed(KeyEvent e)
	{
		keyEvent.add(e.getKeyCode());
		if(keyEvent.contains(KeyEvent.VK_I) && keyEvent.contains(KeyEvent.VK_CONTROL))
		{
			this.affichageId();
		}
		if(keyEvent.contains(KeyEvent.VK_T) && keyEvent.contains(KeyEvent.VK_CONTROL))
		{
			this.affichageType();
		}
	}

	/**
	 * Méthode permettant de retirer les code KeyEvent de l’ArrayList
	 */
	public void keyReleased(KeyEvent e)
	{
		for (int i = 0;i < keyEvent.size() ;i++ )
		{
			if(e.getKeyCode() == keyEvent.get(i)) keyEvent.remove(i);
		}
	}

	public void keyTyped(KeyEvent e)
	{
		//Non utilisé
	}

	/**
	 * Méthode permettant de gérer les clics sur les boutons reset et confirm
	 * @param e variable ActionEvent contenant la source du clic
	 */
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == reset)
		{
			resetComponent(pan);
		}
		if(e.getSource() == confirm)
		{
			System.out.println("Terminé");
			IHMFormulaire.termine = true;
			dispose();
		}
	}

	/**
	 * Méthode permettant l'affichage ou non des id
	 */
	private void affichageId()
	{
		ArrayList<JLabel> listeId = (ArrayList)this.gen.getListlabelId();
		if(showId)
		for(JLabel jl : listeId)jl.setVisible(false);
		else
			for(JLabel jl : listeId)jl.setVisible(true);
		revalidate();
		showId = !showId;
	}

	/**
	 * Méthode permettant l'affichage ou non des types
	 */
	private void affichageType()
	{
		ArrayList<JLabel> listeType = (ArrayList)this.gen.getListlabelType();
		if(showType)
		for(JLabel jl : listeType)jl.setVisible(false);
		else
			for(JLabel jl : listeType)jl.setVisible(true);
		revalidate();
		showType = !showType;
	}

	/**
	 * Cette méthode retourne l'attribut pan sous forme de JPanel.
	 *
	 * @see IHMFormulaire#pan
	 */
	public JPanel getPan()
	{
		return this.pan;
	}

	/**
	 * Cette méthode ajoute des KeyListener sur chacuns des composants du JPanel pan
	 *
	 * @see IHMFormulaire#pan
	 */
	private void addKeyListener(JPanel pan)
	{
		Component[] composants = pan.getComponents();
		for (Component c : composants )
		{
			c.addKeyListener(this);
		}
	}

	/**
	 * Cette méthode prends en paramètre un JPanel et permet de réinitialiser les composants
	 * du JPanel passé en paramètres.
	 *
	 * @see IHMFormulaire#pan
	 */
	private void resetComponent(JPanel pan)
	{
		Component[] composants = pan.getComponents();
		for (int i = 0;i < composants.length ;i++ )
		{
			Component c = composants[i];
			if(!(c instanceof JLabel))
			{
				if(c instanceof JTextField) ((JTextField)(c)).setText("");
				if(c instanceof JCheckBox)((JCheckBox)(c)).setSelected(false);
				if(c instanceof JRadioButton) ((JRadioButton)(c)).setSelected(false);
				if(c instanceof JSpinner)((JSpinner)(c)).setValue(0);
				if(c instanceof JComboBox)((JComboBox)(c)).setSelectedIndex(0);
			}
		}
	}

	/**
	 * La méthode createForm du Generateur décompose le fichier XML pour remplir la hashmap
	 * et prends en paramètre une String qui est le nom du fichier XML.
	 *
	 */
	public static HashMap<String,Component> createForm(String fichier)
	{
		IHMFormulaire ihm = new IHMFormulaire(fichier);
		int cpt = 1;
		HashMap<String,Component> hashmap = new HashMap<String,Component>();
		while(!IHMFormulaire.termine)
		{
			try {
				Thread.sleep(100);
			}
			catch (Exception e) {}
		}
		Component[] composants = ihm.getPan().getComponents();

		int i = 0,idActuel = 0;
		char car = 'a';
		String id ="";
		while(i < composants.length)
		{
			Component c = composants[i];
			if(!(c instanceof JLabel))
			{
				if(c instanceof JCheckBox || c instanceof JRadioButton)
				{
					while(c instanceof JCheckBox || c instanceof JRadioButton)
					{
						hashmap.put(cpt+""+car,c);
						car++;
						i++;
						if(i == composants.length)break;
						c = composants[i];
					}
					car = 'a';
					cpt++;
				}
				else
				{
					hashmap.put(cpt++ +"",c);
				}
			}
			i++;
		}
		return hashmap;
	}
}
