public class MonProgramme
{
	public static void main(String[] args)
	{
		Formulaire form = new Formulaire(createForm("Test2.xml"));
		String nom = form.getString(1);
		String prenom = form.getString(2);

		System.out.println("Je m'appelle "+nom+" "+prenom);
	}
}
