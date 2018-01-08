import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;

public class testIHM extends JFrame implements ActionListener //oui c'est pas bien je sais
{
	private JTextField jtfTest;
	private JButton boutonEnvoi;
	private boolean valide;

	private testIHM()
	{
		this.setSize(200,200);
		this.setLayout(new BorderLayout());
		this.jtfTest = new JTextField();
		this.boutonEnvoi = new JButton("envoyer");
		this.boutonEnvoi.addActionListener(this);
		this.add(boutonEnvoi,BorderLayout.CENTER);
		this.add(jtfTest,BorderLayout.SOUTH);
		this.setVisible(true);
		while(!valide)
		{
			System.out.println(valide);
			try {
				Thread.sleep(100);
			}
			catch (Exception e){}
		}

	}

	public static String creerIHM()
	{
		testIHM ihm = new testIHM();
		return ihm.getJtf().getText();

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

	public JTextField getJtf()
	{
		return this.jtfTest;
	}
}
