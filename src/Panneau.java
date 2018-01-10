import javax.swing.*;
import java.awt.*;

public class Panneau extends JPanel
{
    private final int NB_LIGNES = 6;
    private final int NB_COL = 6;

    private int x, y;
    private int previousX, previousY;

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
        for (int i = 1; i < NB_COL; i++)
            g.drawLine((this.getWidth()/NB_COL)*i, 0, (this.getWidth()/NB_COL)*i, this.getHeight());
        for (int i = 1; i < NB_LIGNES; i++)
            g.drawLine( 0,(this.getHeight()/NB_LIGNES)*i, this.getWidth(), (this.getHeight()/NB_LIGNES)*i);
    }

    public void deplacerSelection(int x, int y)
    {
        this.previousX = this.x;
        this.previousY = this.y;
        this.x = x;
        this.y = y;
        this.repaint();
    }
}
