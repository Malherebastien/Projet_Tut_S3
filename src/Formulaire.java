import java.awt.Component;
import java.util.HashMap;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;
import javax.swing.JSpinner;
import javax.swing.JComboBox;

public class Formulaire
{
	private HashMap<Integer,Component> mapIdComposant;

	public Formulaire(HashMap<Integer,Component> mapIdComposant)
	{
		this.mapIdComposant = mapIdComposant;
	}

	public String getString(int id)
	{
		String s = "";
		try
		{
			Component c = mapIdComposant.get(id);
			if(c instanceof JTextField) s += ((JTextField)(c)).getText();
			if(c instanceof JRadioButton) s += ((JRadioButton)(c)).getText();
			if(c instanceof JCheckBox) s += ((JCheckBox)(c)).getText();
			if(c instanceof JSpinner) s += ((JSpinner)(c)).getValue();
			if(c instanceof JComboBox) s += ((JComboBox)(c)).getSelectedItem();
		}
		catch (Exception e)
		{
			if(mapIdComposant.get(id) == null)System.out.println("Cet id n'appartient à aucun champ de saisie.");
		}
		return s;
	}

	public int getInt(int id)
	{
		int i = -1;
		String s = "";
		try
		{
			Component c = mapIdComposant.get(id);
			if(c instanceof JTextField)
			{
				s+= ((JTextField)(c)).getText();
				i = Integer.parseInt(s);
			}
			if(c instanceof JRadioButton)
			{
				s+= ((JRadioButton)(c)).getText();
				i = Integer.parseInt(s);
			}
			if(c instanceof JCheckBox)
			{
				s+= ((JCheckBox)(c)).getText();
				i = Integer.parseInt(s);
			}
			if(c instanceof JSpinner)
			{
				s+= ((JSpinner)(c)).getValue();
				i = Integer.parseInt(s);
			}
			if(c instanceof JComboBox)
			{
				s+= ((JComboBox)(c)).getSelectedItem();
				i = Integer.parseInt(s);
			}
		}
		catch (Exception e)
		{
			if(mapIdComposant.get(id) == null)System.out.println("Cet id n'appartient à aucun champ de saisie.");
		}
		return i;
	}
	public double getDouble(int id)
	{
		Double d = -0.1;
		String s = "";
		try
		{
			Component c = mapIdComposant.get(id);
			if(c instanceof JTextField)
			{
				s+= ((JTextField)(c)).getText();
				d = Double.parseDouble(s);
			}
			if(c instanceof JRadioButton)
			{
				s+= ((JRadioButton)(c)).getText();
				d = Double.parseDouble(s);
			}
			if(c instanceof JCheckBox)
			{
				s+= ((JCheckBox)(c)).getText();
				d = Double.parseDouble(s);
			}
			if(c instanceof JSpinner)
			{
				s+= ((JSpinner)(c)).getValue();
				d = Double.parseDouble(s);
			}
			if(c instanceof JComboBox)
			{
				s+= ((JComboBox)(c)).getSelectedItem();
				d = Double.parseDouble(s);
			}
		}
		catch (Exception e)
		{
			if(mapIdComposant.get(id) == null)System.out.println("Cet id n'appartient à aucun champ de saisie.");
		}
		return d;
	}

	public boolean getBoolean(int id)
	{
		boolean b = false;
		try
		{
			Component c = mapIdComposant.get(id);
			if(c instanceof JRadioButton) b = ((JRadioButton)(c)).isSelected();
			if(c instanceof JCheckBox) b = ((JCheckBox)(c)).isSelected();
		}
		catch (Exception e)
		{
			if(mapIdComposant.get(id) == null)System.out.println("Cet id n'appartient à aucun champ de saisie.\n La valeur de retour par défaut est False, faites attention.");
		}
		return b;
	}

	public char getChar(int id)
	{
		String s = "";
		char car = ' ';
		try
		{
			Component c = mapIdComposant.get(id);
			if(c instanceof JTextField)
			{
				s+= ((JTextField)(c)).getText();
				car = s.charAt(0);
			}
			if(c instanceof JRadioButton)
			{
				s+= ((JRadioButton)(c)).getText();
				car = s.charAt(0);
			}
			if(c instanceof JCheckBox)
			{
				s+= ((JCheckBox)(c)).getText();
				car = s.charAt(0);
			}
			if(c instanceof JSpinner)
			{
				s+= ((JSpinner)(c)).getValue();
				car = s.charAt(0);
			}
			if(c instanceof JComboBox)
			{
				s+= ((JComboBox)(c)).getSelectedItem();
				car = s.charAt(0);
			}
		}
		catch (Exception e)
		{
			if(mapIdComposant.get(id) == null)System.out.println("Cet id n'appartient à aucun champ de saisie.");
		}
		return car;
	}
}
