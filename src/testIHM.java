import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;

public class testIHM extends JFrame implements ActionListener //oui c'est pas bien je sais
{
	private JButton boutonEnvoi;
	private boolean valide;

	private testIHM()
	{
		this.setLayout(new BorderLayout());
		this.setContentPane(new Formulaire());
		this.boutonEnvoi = new JButton("envoyer");
		this.boutonEnvoi.addActionListener(this);
		this.add(boutonEnvoi,BorderLayout.SOUTH);
		this.setVisible(true);
		while(!valide)
		{
			System.out.println(valide);
			try {
				Thread.sleep(100);
			}
			catch (Exception e){}
		}
		this.dispose();

	}

	public static String creerIHM()
	{
		testIHM ihm = new testIHM();
		return "coucou";

	}

	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == boutonEnvoi)
		{
			System.out.println("validé");
			this.valide = true;
			System.out.println(this.valide);
		}
	}
}
