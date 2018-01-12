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
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Calendrier extends JFrame implements ActionListener, ItemListener,
		ChangeListener {
	private JPanel panelJour;
	private JComboBox<String> cbMois;
	private JSpinner annee;
	private String[] tabMois = { "janvier", "fevrier", "mars", "avril", "mai",
			"juin", "juillet", "août", "septembre", "octobre", "novembre",
			"decembre" };
	private ArrayList<JButton> tabButton = new ArrayList<JButton>();
	private JButton valider = new JButton("Valider");
	private int dateJ;

	public Calendrier() {
		this.setTitle("Calendrier");
		this.setLocation(100, 100);
		this.setLayout(new GridBagLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 10, 0, 5);

		dateJ = 0;

		/* Choix du mois et de l'année */
		gbc.gridx = 1;
		gbc.gridx = 1;

		cbMois = new JComboBox<String>();
		for (int i = 0; i < tabMois.length; i++) {
			cbMois.addItem(tabMois[i]);
		}

		cbMois.addActionListener(this);
		cbMois.addItemListener(this);
		add(cbMois, gbc);

		gbc.gridx = 2;
		annee = new JSpinner(new SpinnerNumberModel(2018, -1000, 5000, 1));
		annee.addChangeListener(this);
		add(annee, gbc);

		/* Choix des jours */
		panelJour = new JPanel();
		panelJour.setLayout(new GridLayout(5, 7));

		remplirJour();

		gbc.gridx = 2;
		gbc.gridy = 1;
		add(panelJour);

		setVisible(true);
		pack();
	}

	private void remplirJour() {
		viderCache();
		if (((cbMois.getSelectedIndex() < 7) && cbMois.getSelectedIndex() % 2 == 0)
				|| (cbMois.getSelectedIndex() >= 7)
				&& cbMois.getSelectedIndex() % 2 != 0) {
			for (int i = 1; i <= 31; i++) {
				tabButton.add(new JButton("" + i));
				tabButton.get(i - 1).addActionListener(this);
				panelJour.add(tabButton.get(i - 1));
			}
		} else {
			if (cbMois.getSelectedItem().toString().equals("fevrier")) {
				if (Integer.parseInt(annee.getValue().toString()) % 4 != 0)
					for (int i = 1; i <= 28; i++) {
						tabButton.add(new JButton("" + i));
						tabButton.get(i - 1).addActionListener(this);
						panelJour.add(tabButton.get(i - 1));
					}
				else
					for (int i = 1; i <= 29; i++) {
						tabButton.add(new JButton("" + i));
						tabButton.get(i - 1).addActionListener(this);
						panelJour.add(tabButton.get(i - 1));

					}
			} else {
				for (int i = 1; i <= 30; i++) {
					tabButton.add(new JButton("" + i));
					tabButton.get(i - 1).addActionListener(this);
					panelJour.add(tabButton.get(i - 1));
				}
			}
		}
	}

	private void viderCache() {
		tabButton.clear();
		panelJour.removeAll();
	}

	public String toString() {

		String s = (dateJ < 10) ? ("0" + dateJ) : (dateJ + "");
		for (int i = 0; i < tabMois.length; i++)
			if (tabMois[i].equals(cbMois.getSelectedItem()))
				s += "/" + cbMois.getSelectedItem();
		s += "/" + annee.getValue();
		return s;

	}

	public void actionPerformed(ActionEvent e) {
		for (int i = 0; i < tabButton.size(); i++) {
			if (e.getSource() == tabButton.get(i))
				dateJ = Integer.parseInt((tabButton.get(i).getText()));
		}
		// System.out.println(this.toString());
	}

	public void itemStateChanged(ItemEvent e) {
		remplirJour();
		panelJour.repaint();
		pack();
		setLocation(100, 100);
	}

	public void stateChanged(ChangeEvent e) {
		remplirJour();
		panelJour.repaint();
		pack();
		setLocation(100, 100);
	}

	public static void main(String[] args) {
		Calendrier c = new Calendrier();
		System.out.println(c.toString());
	}
}
