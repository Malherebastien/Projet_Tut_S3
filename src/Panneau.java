import javax.swing.*;
import java.awt.*;
/**
 * La classe Panneau permet de créer un panel
 * dans le but d'afficher le tableau
 *
 * @author cofni
 * @version 15/01/18
 */
public class Panneau extends JPanel
{
    /**
     * Constante permettant de définir le nombre de lignes du tableau
     *
     * @see Panneau#paintComponent()
     */
    private final int NB_LIGNES = 5;
    /**
     * Constante permettant de définir le nombre de colonnes du tableau
     *
     * @see Panneau#paintComponent()
     */
    private final int NB_COL = 5;

    /**
     * Position x pour se déplacer dans le tableau
     *
     * @see Panneau#deplacerSelection()
     */
    private int x;
    /**
     * Constante permettant de définir le nombre de lignes
     *
     * @see Panneau#deplacerSelection()
     */
    private int y;

    /**
     *
     */
    private int previousX;

    /**
     *
     */
    private int previousY;

    /**
     * Entier permettant de parcourir le tableau en fonction des lignes
     *
     * @see Panneau#getNbLig()
     */
    private int nbLig;
    /**
     * Entier permettant de parcourir le tableau en fonction des colonnes
     *
     * @see Panneau#getNbCol()
     */
    private int nbCol;

    /**
      * Constructeur permettant l'initialisation le plateau
      * @param nbLig permettant d'initialiser le nombre de lignes au tableau
      * @param nbCol permettant d'initialiser le nombre de colonnes au tableau
      */
    public Panneau(int nbLig, int nbCol)
    {
        this.nbLig = nbLig;
        this.nbCol = nbCol;
		setVisible(true);
    }

    /**
     * Méthode permettant de dessiner les cases du tableau
     */
    public void paintComponent(Graphics g)
    {
        //On efface l'ancien rectangle
        g.setColor(getBackground());
        g.fillRect((this.getWidth()/NB_COL)*previousX, (this.getHeight()/NB_LIGNES)*previousY, this.getWidth()/NB_COL, this.getHeight()/NB_LIGNES);

        //On efface les lignes autour de la case anciennement selectionnéesg.setColor(Color.LIGHT_GRAY);
        for (int i = 0; i < NB_COL; i++)
            g.fillRect((this.getWidth()/NB_COL)*i, (this.getHeight()/NB_LIGNES)*previousY, this.getWidth()/NB_COL, this.getHeight()/NB_LIGNES);
        for (int i = 0; i < NB_LIGNES; i++)
            g.fillRect((this.getWidth()/NB_COL)*previousX, (this.getHeight()/NB_LIGNES)*i, this.getWidth()/NB_COL, this.getHeight()/NB_LIGNES);

        //On dessine des lignes autour de la case selectionnée
        g.setColor(Color.LIGHT_GRAY);
        for (int i = 0; i < NB_COL; i++)
            g.fillRect((this.getWidth()/NB_COL)*i, (this.getHeight()/NB_LIGNES)*y, this.getWidth()/NB_COL, this.getHeight()/NB_LIGNES);
        for (int i = 0; i < NB_LIGNES; i++)
            g.fillRect((this.getWidth()/NB_COL)*x, (this.getHeight()/NB_LIGNES)*i, this.getWidth()/NB_COL, this.getHeight()/NB_LIGNES);

        //On dessine le nouveau
        g.setColor(Color.GRAY);
        g.fillRect((this.getWidth()/NB_COL)*x, (this.getHeight()/NB_LIGNES)*y, this.getWidth()/NB_COL, this.getHeight()/NB_LIGNES);

        //On dessine les lignes du tableau
        g.setColor(Color.BLACK);
        for (int i = 0; i < NB_COL; i++)
            g.drawLine((this.getWidth()/NB_COL)*i, 0, (this.getWidth()/NB_COL)*i, this.getHeight());
        for (int i = 0; i < NB_LIGNES; i++)
            g.drawLine( 0,(this.getHeight()/NB_LIGNES)*i, this.getWidth(), (this.getHeight()/NB_LIGNES)*i);

        g.drawLine(this.getWidth()-1, 0, this.getWidth()-1, this.getHeight()-1);
        g.drawLine(0, this.getHeight()-1, this.getWidth()-1, this.getHeight()-1);
    }

    /**
     * !!!!!!!! chez pas frere  !!
     */
    public void deplacerSelection(int x, int y)
    {
        this.previousX = this.x%5;
        this.previousY = this.y%5;
        this.x = x%5;
        this.y = y%5;
        this.repaint();
    }

    /**
     * Permet d'initialiser une case du tableau avec les indices et la valeur donné
     * @param value est la valeur à saisir aux indices du tableau
     * @param lig est l'indice de la ligne pour insérer la valeur dans le tableau
     * @param col est l'indice de la colonne pour insérer la valeur dans le tableau
     */
    public void caseValue(String value, int lig, int col)
    {
        //Redéfinie dans les classes filles
    }

    /**
     * Permet de retourner la valeur aux indices donné
     * @param lig est l'indice de la ligne du tableau pour retourner la valeur du tableau
     * @param col est l'indice de la colonne du tableau pour retourner la valeur du tableau
     */
    public String getValue(int lig, int col)
    {
        //Redéfinie dans les classes filles
        return null;
    }

    /**
     * Methode permettant de connaitre l'indice de la ligne à un point x
     * @return Le nombre de lignes
     */
    public int getNbLig()
    {
        return nbLig;
    }

    /**
     * Methode permettant de connaitre l'indice de la colonne à un point x
     * @return Le nombre de colonnes
     */
    public int getNbCol()
    {
        return nbCol;
    }
}
