import java.awt.Component;
import java.util.HashMap;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;
import javax.swing.JSpinner;
import javax.swing.JComboBox;
import java.util.ArrayList;

public class Formulaire
{
	private HashMap<String,Component> mapIdComposant;

	public Formulaire(HashMap<String,Component> mapIdComposant)
	{
		this.mapIdComposant = mapIdComposant;
	}

	public String getString(int id)
	{
		String s = "";
		try
		{
			Component c = mapIdComposant.get(id+"");
			if(c instanceof JTextField) s += ((JTextField)(c)).getText();
			if(c instanceof JComboBox) s += ((JComboBox)(c)).getSelectedItem();
			if(c instanceof JSpinner) s += ((JSpinner)(c)).getValue()+"";
		}
		catch (Exception e)
		{
			if(mapIdComposant.get(id) == null && s.equals(""))System.out.println("Cet id n'appartient à aucun champ de saisie.");
		}
		return s;
	}

	public int getInt(int id)
	{
		int i = -1;
		String s = "",key = id+"";
		try
		{

			Component c = mapIdComposant.get(key);

			if(c instanceof JTextField)
			{
				s+= ((JTextField)(c)).getText();
				i = Integer.parseInt(s);
			}
			if(c instanceof JComboBox)
			{
				s+= ((JComboBox)(c)).getSelectedItem();
				i = Integer.parseInt(s);
			}
			if(c instanceof JSpinner)
			{
				s = ((JSpinner)(c)).getValue().toString();
				i = Integer.parseInt(s);
			}
		}
		catch (Exception e)
		{
			if(mapIdComposant.get(id) == null)System.out.println("Cet id n'appartient à aucun champ de saisie.");
			else
				System.out.println(s+" : Valeur impossible à caster en int. Valeur de retour : -1");
		}
		return i;
	}
	public double getDouble(int id)
	{
		Double d = -0.1;
		String s = "",key = id+"";
		try
		{
			Component c = mapIdComposant.get(id);
			if(c instanceof JTextField)
			{
				s+= ((JTextField)(c)).getText();
				d = Double.parseDouble(s);
			}
			if(c instanceof JComboBox)
			{
				s+= ((JComboBox)(c)).getSelectedItem();
				d = Double.parseDouble(s);
			}
			if(c instanceof JSpinner)
			{
				s = ((JSpinner)(c)).getValue().toString();
				d = Double.parseDouble(s);
			}
		}
		catch (Exception e)
		{
			if(mapIdComposant.get(id) == null)System.out.println("Cet id n'appartient à aucun champ de saisie.");
			else
				System.out.println(s+" : Valeur impossible à caster en double. Valeur de retour : - 0.1");
		}
		return d;
	}

	public char getChar(int id)
	{
		String s = "",key = id+"";
		char car = ' ';
		try
		{
			Component c = mapIdComposant.get(id);
			if(c instanceof JTextField)
			{
				s+= ((JTextField)(c)).getText();
				car = s.charAt(0);
			}
			if(c instanceof JComboBox)
			{
				s+= ((JComboBox)(c)).getSelectedItem();
				if(s.length() > 1)System.out.println("\""+s+"\" est une chaine. valeur de retour : Premier caractère de la chaine.");
				car = s.charAt(0);
			}
			if(c instanceof JSpinner)
			{
				s = ((JSpinner)(c)).getValue().toString();
				car = s.charAt(0);
			}
		}
		catch (Exception e)
		{
			if(mapIdComposant.get(id) == null)System.out.println("Cet id n'appartient à aucun champ de saisie.");
			else
				System.out.println(s+" : Valeur impossible à caster en char. Valeur de retour : ' '");
		}
		return car;
	}

	public Boolean[] getBoolean(int id)
	{
		Boolean[] b = null;
		ArrayList<Component> cases = new ArrayList<Component>();
		try
		{
			for(String key : mapIdComposant.keySet())
			{
				if(key.startsWith(id+""))cases.add(mapIdComposant.get(key));
			}

			b = new Boolean[cases.size()];

			for (int i = 0; i < cases.size();i++)
			{
				b[i] = ((JCheckBox)(cases.get(i))).isSelected();
			}
		}

		catch (Exception e)
		{
			if(mapIdComposant.get(id) == null)System.out.println("Cet id n'appartient à aucun champ de saisie.");
			else
				System.out.println("Impossible de récupérer un booléen. Valeur de retour : false");
		}
		return b;
	}

	public String[] getTabString(int id)
	{

	}
	public int[] getTabInt(int id)
	{

	}
	public double[] getTabDouble(int id)
	{

	}
	public char[] getTabChar(int id)
	{

	}


}
