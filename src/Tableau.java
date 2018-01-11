import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.Set;

public class Tableau extends JFrame implements KeyListener
{

    private final int NB_LIGNES = 5;
    private final int NB_COL = 5;

    private PanelText panelText;
    private Panneau panneau;
    private int x;
    private int y;

    private Set<Integer> keyPressed;

    public Tableau()
    {
        this.x = 0;
        this.y = 0;

        this.keyPressed = new HashSet<>();

        this.setSize(400,400);
        this.setLocationRelativeTo(null);

        panneau = new Panneau();
        panelText = new PanelText(this);
        BorderLayout bl = new BorderLayout();
        this.setLayout(bl);
        add(panneau, BorderLayout.CENTER);
        add(panelText, BorderLayout.SOUTH);
        this.addKeyListener(this);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.requestFocus();
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
                y = NB_COL;
            if(keyPressed.contains(KeyEvent.VK_RIGHT) && keyPressed.contains(KeyEvent.VK_SHIFT))
                x = NB_LIGNES;
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
        if(x > NB_LIGNES-1) x = NB_LIGNES-1;
        if(y > NB_COL-1) y = NB_COL-1;

        panneau.deplacerSelection(x, y);
    }

    @Override
    public void keyReleased(KeyEvent e)
    {
        keyPressed.remove(e.getKeyCode());
    }

    public static void main (String[] argv)
    {
        new Tableau();
    }

    public void getFocus()
    {
        this.requestFocus();
    }
}