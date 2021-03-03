import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Transform2D extends JPanel {

	private class Display extends JPanel {
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D)g;
			g2.translate(300,300);  // Moves (0,0) to the center of the display.
			int whichTransform = transformSelect.getSelectedIndex();

			// TODO Apply transforms here, depending on the value of whichTransform!

			//g2.drawImage(pic, -200, -150, null); // Draw image with center at (0,0).
			if(whichTransform==0)
			{
				g2.drawPolygon(polygon);
			}
			if(whichTransform==1)
			{
				g2.scale(0.5,0.5);
				g2.drawPolygon(polygon);
			}
			if(whichTransform==2)
			{
				g2.rotate(Math.toRadians(45));
				g2.drawPolygon(polygon);
			}
			if(whichTransform==3)
			{
				g2.rotate(Math.toRadians(180));
				g2.scale(-0.5,1);
				g2.drawPolygon(polygon);
			}
			if(whichTransform==4)
			{
				g2.shear(0.5,0);
				g2.drawPolygon(polygon);
			}
			if(whichTransform==5)
			{
				g2.scale(1, 0.5);
				g2.translate(0, -450);
				g2.drawPolygon(polygon);
			}
			if(whichTransform==6)
			{
				g2.rotate(Math.toRadians(90));
				g2.shear(0.5,0);
				g2.drawPolygon(polygon);
			}
			if(whichTransform==7)
			{
				g2.scale(0.5, 1);
				g2.rotate(Math.toRadians(180));
				g2.drawPolygon(polygon);
			}
			if(whichTransform==8)
			{
				g2.rotate(Math.toRadians(30));
				g2.translate(0, 120);
				g2.scale(1,0.5);
				g2.drawPolygon(polygon);
			}
			if(whichTransform==9)
			{
				g2.translate(150, 0);
				g2.rotate(Math.toRadians(210));
				g2.shear(0.5,0);
				g2.drawPolygon(polygon);
			}
			
		}
	}

	private Display display;
	private BufferedImage pic;
	private JComboBox<String> transformSelect;
	
	
	private Polygon polygon= new Polygon();
	private int n=9;
	//polygon nowy wielokat, i int n oznaczajacy dziewieciokat

	public Transform2D() throws IOException {
		pic = ImageIO.read(getClass().getClassLoader().getResource("shuttle.jpg"));
		display = new Display();
		display.setBackground(Color.YELLOW);
		display.setPreferredSize(new Dimension(600,600));
		transformSelect = new JComboBox<String>();
		transformSelect.addItem("None");
		//tworzenie dziewieciokata
		// 150 to promien, ilosc pikseli
		for (int i=0;i<n;i++){
            polygon.addPoint((int)(150*Math.cos(i*2*Math.PI/n)),
                    (int)(150*Math.sin(i*2*Math.PI/n)));
        }
		
		for (int i = 1; i < 10; i++) {
			transformSelect.addItem("No. " + i);
		}
		transformSelect.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				display.repaint();
			}
		});
		setLayout(new BorderLayout(3,3));
		setBackground(Color.GRAY);
		setBorder(BorderFactory.createLineBorder(Color.GRAY,10));
		JPanel top = new JPanel();
		top.setLayout(new FlowLayout(FlowLayout.CENTER));
		top.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
		top.add(new JLabel("Transform: "));
		top.add(transformSelect);
		add(display,BorderLayout.CENTER);
		add(top,BorderLayout.NORTH);
	}


	public static void main(String[] args) throws IOException {
		JFrame window = new JFrame("2D Transforms");
		window.setContentPane(new Transform2D());
		window.pack();
		window.setResizable(false);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		window.setLocation( (screen.width - window.getWidth())/2, (screen.height - window.getHeight())/2 );
		window.setVisible(true);
	}

}
