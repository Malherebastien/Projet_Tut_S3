import java.util.HashMap;
import java.awt.Component;

public class MonProgramme
{
	public static void main(String[] args)
	{
		Formulaire form = new Formulaire(IHMFormulaire.createForm("../../../src/Test2.xml"));
		int i = form.getInt(1);
		int j = form.getInt(2);
		int k = form.getInt(5);
		boolean[] cases = form.getBoolean(6);
		String[][] str = form.getTabString(7);

		System.out.println(i+" "+j+" "+k);
		for (int a = 0;a < str.length ;a++ )
		{
			for (int b = 0;b < str[0].length ;b++ )
			{
				System.out.println(str[a][b]);
			}
		}
	}
}
