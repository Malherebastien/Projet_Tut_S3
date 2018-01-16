import java.util.HashMap;
import java.awt.Component;

public class MonProgramme
{
	public static void main(String[] args)
	{
		Formulaire form = new Formulaire(IHMFormulaire.createForm("../../../src/Test2.xml"));
		String date = form.getDate(8);

		System.out.println(date);


	}
}
