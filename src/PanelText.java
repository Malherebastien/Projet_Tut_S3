import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PanelText extends JPanel implements KeyListener
{
    private Tableau parent;
    private JTextField text;

    public PanelText(Tableau parent)
    {
        this.parent = parent;
        this.setLayout(new BorderLayout());

        text = new JTextField();
        text.setPreferredSize(new Dimension(150,25));
        text.addKeyListener(this);
        add(text, BorderLayout.CENTER);
    }

    public void getFocus()
    {
        this.requestFocus();
    }

    public void setText(String newText)
    {
        text.setText(newText);
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

        if(e.getKeyCode() == KeyEvent.VK_ENTER )
            parent.textFieldValue(text.getText());

    }

    @Override
    public void keyReleased(KeyEvent e)
    {

    }

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
