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

		System.out.println(i+" "+j+" "+k);
	}
}
