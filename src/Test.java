import java.awt.*;
import javax.swing.*;

public class Test extends JFrame
{
	private String s = "";
	private JScrollPane pan;
	private JLabel label;

	public Test()
	{
		setTitle("cc");
		setLocation(100,110);

		s +=  "Lorem ipsum dolor sit amet, consectetur adipiscing elit.";
		s +=  "Maecenas venenatis sit amet nibh eu euismod. Orci varius natoque penatibus et magnis dis";
		s +=  "parturient montes, nascetur ridiculus mus. In fermentum magna ipsum, at suscipit felis ";
		s +=  "sollicitudin in. Etiam facilisis tincidunt arcu, in volutpat urna aliquam in. Vivamus eget ";
		
		label = new JLabel(s);
		pan = new JScrollPane(label);
		label = new JLabel(s);
		add(label);
		pack();
		setVisible(true);
	}

	public static void main(String[] args) {
		new Test();
	}	
}