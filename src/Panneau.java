import javax.swing.*;
import java.awt.*;

public class Panneau extends JPanel
{
    private final int NB_LIGNES = 5;
    private final int NB_COL = 5;

    private int x, y;
    private int previousX, previousY;

    private int nbLig, nbCol;

    public Panneau(int nbLig, int nbCol)
    {
        this.nbLig = nbLig;
        this.nbCol = nbCol;
		setVisible(true);
    }

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

    public void deplacerSelection(int x, int y)
    {
        this.previousX = this.x%5;
        this.previousY = this.y%5;
        this.x = x%5;
        this.y = y%5;
        this.repaint();
    }

    public void caseValue(String value, int lig, int col)
    {
        //Redéfinie dans les classes filles
    }

    public String getValue(int lig, int col)
    {
        //Redéfinie dans les classes filles
        return null;
    }

    public int getNbLig()
    {
        return nbLig;
    }

    public int getNbCol()
    {
        return nbCol;
    }
}
