import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.util.Set;


public class TestGen extends JFrame implements KeyListener
{
	private boolean showId,showType;
	private Generateur gen;
	private ArrayList<Integer> keyEvent;

	public TestGen()
	{
		setTitle("Formulaire");
		keyEvent = new ArrayList<Integer>();
		showId = showType = true;
		gen = new Generateur("../../../src/Test2.xml");
		JPanel pan = gen.createForm();
		setContentPane(pan);
		setSize(600,600);
		setVisible(true);
		addKeyListener(this);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addKeyListener(pan);
	}

	public static void main(String[] args)
	{
		new TestGen();
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

	private void addKeyListener(JPanel pan)
	{
		Component[] composants = pan.getComponents();
		for (Component c : composants )
		{
			c.addKeyListener(this);
		}
	}
}
