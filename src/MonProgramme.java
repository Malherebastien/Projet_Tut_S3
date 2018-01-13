public class MonProgramme
{
	public static void main(String[] args)
	{
		Formulaire form = new Formulaire(IHMFormulaire.createForm("../../../src/Test2.xml"));
		int i = form.getInt(5);

		System.out.println(i+"");
	}
}
