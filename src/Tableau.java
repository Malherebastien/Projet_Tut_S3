import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * La classe Tableau permet de créer un tableau d'une type donné.
 * Possibilité de créer un tableau à une ou deux dimensions
 * 
 * @author cofni
 * @version 15/01/18
 */
public class Tableau extends JFrame implements KeyListener
{
    /**
      * Le nombre de lignes du tableau
      */
    private int nbLignes;

    /**
      * Le nombre de colonnes du tableau
      */
    private int nbColonnes;

    /**
      * Le nombre de lignes défini en constante du tableau
      */
    private final int NB_LIGNES = 5;
    
    /**
      * Le nombre de colonnes défini en constante du tableau
      */
    private final int NB_COL = 5;


    /**
      * Le nombre de lignes défini en constante du tableau
      */
    private PanelText panelText;
    private Panneau panneau;
    private int x;
    private int y;

    private int posX, posY;
    private int hauteur, largeur;

    private int nbLig, nbCol;

    private ArrayList<JLabel> listJLabel;

    private Set<Integer> keyPressed;

    private int dimension;

    public Tableau(String type, int nbLig, int nbCol, int dimension, int posX, int posY, int hauteur, int largeur)
    {
        this.x = 0;
        this.y = 0;

        this.nbLig = nbLig;
        this.nbCol = nbCol;

        this.dimension = dimension;
        if (this.dimension == 2)
        {
            this.nbLignes = 5;
            this.nbColonnes = 5;
        }
        else if (this.dimension == 1)
        {
            this.nbLignes = 1;
            this.nbColonnes = 5;
        }


        this.posX = posX;
        this.posY = posY;
        this.hauteur = hauteur;
        this.largeur = largeur;

        this.keyPressed = new HashSet<>();

        this.setSize(500,500);
        this.setLocationRelativeTo(null);

        this.listJLabel = new ArrayList<>();

        switch (type)
        {
            case "int" :
                panneau = new PanneauInt(nbLig, nbCol);
                break;
            case "String" :
                panneau = new PanneauString(nbLig, nbCol);
                break;
            case "booleen" :
                panneau = new PanneauBooleen(nbLig, nbCol);
                break;
            case "double" :
                panneau = new PanneauDouble(nbLig, nbCol);
                break;
            //TODO case String, boolean, double, long, char
        }

        panelText = new PanelText(this);
        BorderLayout bl = new BorderLayout();
        this.setLayout(null);
        panneau.setSize(largeur, hauteur);
        panneau.setLocation(posX,posY);
        add(panneau);

        this.createNumberPanel();

        panelText.setSize(400, 30);
        panelText.setLocation(20, 375);
        add(panelText);
        this.addKeyListener(this);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.requestFocus();
    }

    private void createNumberPanel()
    {
        for (int i = 0; i < 10; i++)
            this.listJLabel.add(new JLabel());

        listJLabel.get(0).setBounds((int)(panneau.getWidth()/nbLignes * 0.5), 5, 20, 10);
        listJLabel.get(1).setBounds((int)(panneau.getWidth()/nbLignes * 1.5), 5, 20, 10);
        listJLabel.get(2).setBounds((int)(panneau.getWidth()/nbLignes * 2.5), 5, 20, 10);
        listJLabel.get(3).setBounds((int)(panneau.getWidth()/nbLignes * 3.5), 5, 20, 10);
        listJLabel.get(4).setBounds((int)(panneau.getWidth()/nbLignes * 4.5), 5, 20, 10);

        listJLabel.get(5).setBounds(5, (int)(panneau.getHeight()/nbColonnes * 0.5), 20, 10);
        listJLabel.get(6).setBounds(5, (int)(panneau.getHeight()/nbColonnes * 1.5), 20, 10);
        listJLabel.get(7).setBounds(5, (int)(panneau.getHeight()/nbColonnes * 2.5), 20, 10);
        listJLabel.get(8).setBounds(5, (int)(panneau.getHeight()/nbColonnes * 3.5), 20, 10);
        listJLabel.get(9).setBounds(5, (int)(panneau.getHeight()/nbColonnes * 4.5), 20, 10);

        this.setLabelTextX(true);
        this.setLabelTextY(true);

        for(JLabel label : listJLabel)
            this.add(label);
    }

    private void setLabelTextX(boolean avance)
    {
        if(avance)
        {
            listJLabel.get(0).setText("" + (this.x));
            listJLabel.get(1).setText("" + (this.x + 1));
            listJLabel.get(2).setText("" + (this.x + 2));
            listJLabel.get(3).setText("" + (this.x + 3));
            listJLabel.get(4).setText("" + (this.x + 4));
        }
        else
        {
            listJLabel.get(0).setText("" + (this.x - 4));
            listJLabel.get(1).setText("" + (this.x - 3));
            listJLabel.get(2).setText("" + (this.x - 2));
            listJLabel.get(3).setText("" + (this.x - 1));
            listJLabel.get(4).setText("" + (this.x));
        }
    }

    private void setLabelTextY(boolean avance)
    {
        if(avance)
        {
            listJLabel.get(5).setText("" + (this.y));
            listJLabel.get(6).setText("" + (this.y + 1));
            listJLabel.get(7).setText("" + (this.y + 2));
            listJLabel.get(8).setText("" + (this.y + 3));
            listJLabel.get(9).setText("" + (this.y + 4));
        }
        else
        {
            listJLabel.get(5).setText("" + (this.y - 4));
            listJLabel.get(6).setText("" + (this.y - 3));
            listJLabel.get(7).setText("" + (this.y - 2));
            listJLabel.get(8).setText("" + (this.y - 1));
            listJLabel.get(9).setText("" + (this.y));
        }
    }

    @Override
    public void keyTyped(KeyEvent e)
    {
        //Unused
    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        keyPressed.add(e.getKeyCode());
        if(keyPressed.contains(KeyEvent.VK_CONTROL) || keyPressed.contains(KeyEvent.VK_SHIFT))
        {
            if(keyPressed.contains(KeyEvent.VK_UP) && keyPressed.contains(KeyEvent.VK_CONTROL))
                y-=5;
            if(keyPressed.contains(KeyEvent.VK_LEFT) && keyPressed.contains(KeyEvent.VK_CONTROL))
                x-=5;
            if(keyPressed.contains(KeyEvent.VK_DOWN) && keyPressed.contains(KeyEvent.VK_CONTROL))
                y+=5;
            if(keyPressed.contains(KeyEvent.VK_RIGHT) && keyPressed.contains(KeyEvent.VK_CONTROL))
                x+=5;

            if(keyPressed.contains(KeyEvent.VK_UP) && keyPressed.contains(KeyEvent.VK_SHIFT))
                y = 0;
            if(keyPressed.contains(KeyEvent.VK_LEFT) && keyPressed.contains(KeyEvent.VK_SHIFT))
                x = 0;
            if(keyPressed.contains(KeyEvent.VK_DOWN) && keyPressed.contains(KeyEvent.VK_SHIFT))
                y = nbColonnes;
            if(keyPressed.contains(KeyEvent.VK_RIGHT) && keyPressed.contains(KeyEvent.VK_SHIFT))
                x = nbLignes;

            this.createNumberPanel();
        }
        else if (keyPressed.contains(KeyEvent.VK_UP) || keyPressed.contains(KeyEvent.VK_LEFT) ||
                 keyPressed.contains(KeyEvent.VK_DOWN) || keyPressed.contains(KeyEvent.VK_RIGHT))
        {
            if (keyPressed.contains(KeyEvent.VK_UP))
                    y--;
            if (keyPressed.contains(KeyEvent.VK_LEFT))
                    x--;
            if (keyPressed.contains(KeyEvent.VK_DOWN))
                    y++;
            if (keyPressed.contains(KeyEvent.VK_RIGHT))
                    x++;
        }
        else
        {
            panelText.getFocus();
        }

        if(x < 0) x = 0;
        if(y < 0) y = 0;
        if(x > panneau.getNbCol()-1) x = panneau.getNbCol()-1;
        if(y > panneau.getNbLig()-1) y = panneau.getNbLig()-1;

        if (this.x%5 == 0 && this.x != 0)
            this.setLabelTextX(true);
        if (this.x%5 == 4 && this.x != panneau.getNbCol())
            this.setLabelTextX(false);

        if (this.y%5 == 0 && this.y != 0)
            this.setLabelTextY(true);
        if (this.y%5 == 4 && this.y != panneau.getNbLig())
            this.setLabelTextY(false);

        panelText.setText(panneau.getValue(x, y));
        panneau.deplacerSelection(x, y);
    }

    public Panneau getPanneau()
    {
        return this.panneau;
    }

    public void textFieldValue(String value)
    {
        panneau.caseValue(value, this.x, this.y);
    }

    @Override
    public void keyReleased(KeyEvent e)
    {
        keyPressed.remove(e.getKeyCode());
    }

    public static void main (String[] argv)
    {
        new Tableau("String", 10, 10, 2, 20, 20, 350, 350);
    }

    public void getFocus()
    {
        this.requestFocus();
    }
}
