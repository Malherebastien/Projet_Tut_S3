import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * La classe PanelText permet de gérer les indices affichés du tableau
 *
 * @author cofni
 * @version 15/01/18
 */
public class PanelText extends JPanel implements KeyListener
{
    /**
     * Attribut pour avoir accès au tableau dans lequel le PanelText est inséré
     * @see PanelText#PanelText
     */
    private Tableau parent;
    /**
     * JTextField de saisie
     * @see PanelText#keyPressed
     */
    private JTextField text;

    /**
     * Constructeur 
     * @param parent instance père de Tableau, présente uniquement 
     *        pour avoir accès aux éléments du tableau
     */
    public PanelText(Tableau parent)
    {
        this.parent = parent;
        this.setLayout(new BorderLayout());

        text = new JTextField();
        text.setPreferredSize(new Dimension(150,25));
        text.addKeyListener(this);
        add(text, BorderLayout.CENTER);
    }

    /**
     * Permet de gérer le focus du tableau
     */
    public void getFocus()
    {
        this.requestFocus();
    }

    /**
     * Permet d'initialiser le TextField
     * @param newText est le texte à mettre dans le TextField
     */
    public void setText(String newText)
    {
        text.setText(newText);
    }

    @Override
    public void keyTyped(KeyEvent e)
    {
        //Non utilisé
    }

    /**
     * Permet de gérer les déplacements dans le tableau à l'aide des touches directionnelles
     */
    @Override
    public void keyPressed(KeyEvent e)
    {
        if(e.getKeyCode() == 37 || e.getKeyCode() == 38 ||
        e.getKeyCode() == 39 || e.getKeyCode() == 40)
            parent.getFocus();

        if(e.getKeyCode() == KeyEvent.VK_ENTER )
            parent.textFieldValue(text.getText());
    }

    @Override
    public void keyReleased(KeyEvent e)
    {
        //Non utilisé
    }

    /**
     * Méthode permettant de savoir si la chaine passé
     * en paramètre est un entier
     *
     * @param Chaine de caractère devant être converti en entier
     * @return Booléen pour savoir si la chaine en paramètre est un entier ou non
     */
    public static boolean isInteger(String s)
    {
        try {
            Integer.parseInt(s);
        } catch(NumberFormatException e) {
            return false;
        } catch(NullPointerException e) {
            return false;
        }
        return true;
    }
}
