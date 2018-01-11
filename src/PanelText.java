import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PanelText extends JPanel implements KeyListener
{
    private Tableau parent;

    public PanelText(Tableau parent)
    {
        this.parent = parent;
        this.setLayout(new BorderLayout());

        JTextField text = new JTextField();
        text.setPreferredSize(new Dimension(150,25));
//        BorderFactory.createMatteBorder(1, 0, 0, 0, Color.RED);
//        BorderFactory.createMatteBorder(0, 1, 0, 0, Color.RED);
//        BorderFactory.createMatteBorder(0, 0, 1, 0, Color.RED);
//        BorderFactory.createMatteBorder(0, 0, 0, 1, Color.RED);
        //this.getRootPane().setBorder(BorderFactory.createMatteBorder(10,10,10,10,Color.RED));
        text.addKeyListener(this);
        add(text, BorderLayout.CENTER);
    }

    public void getFocus()
    {
        this.requestFocus();
    }

    @Override
    public void keyTyped(KeyEvent e)
    {

    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        if(e.getKeyCode() == 37 || e.getKeyCode() == 38 ||
        e.getKeyCode() == 39 || e.getKeyCode() == 40)
            parent.getFocus();
    }

    @Override
    public void keyReleased(KeyEvent e)
    {

    }
}
