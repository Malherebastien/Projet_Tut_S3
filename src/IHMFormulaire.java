import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Set;
import javax.swing.JScrollPane;
import java.util.HashMap;

public class IHMFormulaire extends JFrame implements KeyListener, ActionListener
{
	private boolean showId,showType;
	private Generateur gen;
	private JPanel pan;
	private ArrayList<Integer> keyEvent;
	JScrollBar jsbHor,jsbVer;
	JPanel flowLayout;

	private JButton reset,confirm;

	public IHMFormulaire(String fichier)
	{
		setLayout(new BorderLayout());
		setTitle("Formulaire");
		setSize(600,600);
		gen = new Generateur(fichier);
		pan = gen.createForm();
		add(pan,BorderLayout.CENTER);

		jsbVer = new JScrollBar(JScrollBar.VERTICAL);
		jsbHor = new JScrollBar(JScrollBar.HORIZONTAL);
		pan.add(jsbVer);
		pan.add(jsbHor);

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

	public void keyReleased(KeyEvent e)
	{
		for (int i = 0;i < keyEvent.size() ;i++ )
		{
			if(e.getKeyCode() == keyEvent.get(i)) keyEvent.remove(i);
		}
	}

	public void keyTyped(KeyEvent e){}

	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == reset)
		{
			resetComponent(pan);
		}
		if(e.getSource() == confirm) dispose();
	}

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

	public JPanel getPan()
	{
		return this.pan;
	}

	private void addKeyListener(JPanel pan)
	{
		Component[] composants = pan.getComponents();
		for (Component c : composants )
		{
			c.addKeyListener(this);
		}
	}

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

	public HashMap<Integer,Component> createForm(String fichier)
	{
		IHMFormulaire ihm = new IHMFormulaire(fichier);
		int cpt = 1;
		HashMap<Integer,Component> hashmap = new HashMap<Integer,Component>();
		Component[] composants = this.getPan().getComponents();

		for(int i = 0; i < composants.length;i++)
		{
			Component c = composants[i];
			if(!(c instanceof JLabel))
			{
				hashmap.put(cpt,c);
				cpt++;
			}
		}
		return hashmap;
	}
}
