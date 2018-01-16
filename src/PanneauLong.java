/**
 * La classe PanneauInt permet de gérer un tableau de type long
 * La classe appelle la classe Panneau
 *
 * @author cofni
 * @version 15/01/18
 */
public class PanneauLong extends Panneau
{
    /**
     * Tableau à double dimension de type long
     */
    private long[][] tab;

    /**
     * Constructeur permettant de créer un tableau de long
     *
     * @param nbLig est le nombre de lignes pour l'initialisation du tableau
     * @param nbCol est le nombre de colonne pour l'initialisation du tableau
     */
    public PanneauLong(int nbLig, int nbCol)
    {
        super(nbLig, nbCol);
        tab = new long[nbLig][nbCol];
    }

    /**
     * Permet d'initialiser la case du tableau à l'indice donné avec la valeur souhaité
     *
     * @param value est la valeur à insérer dans le tableau à l'indice donné
     * @param lig est l'indice pour la ligne pour l'insertion dans le tableau
     * @param col est l'indice pour la colonne pour l'insertion dans le tableau
     */
    public void caseValue(String value, int lig, int col)
    {
        this.tab[lig][col] = Long.parseLong(value);
    }

    /**
     * Permet de retourner la valeur du tableau aux indices donné
     *
     * @param lig est l'indice de la ligne pour la zone à retourner dans le tableau
     * @param col est l'indice de la colonne pour la zone à retourner dans le tableau
     * @return la valeur du tableau à l'indice lig et col
     */
    public String getValue(int lig, int col)
    {
        return "" + tab[lig][col];
    }

    /**
     * Permet de retourner le tableau créer ci-dessus
     * @return le tableau dans son intégralité
     */
    public long[][] getTab()
    {
        return tab;
    }
}
