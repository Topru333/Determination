import java.awt.EventQueue;

import java.util.ArrayList;
import javax.swing.JFrame;
import java.awt.Button;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Determination {

	private JFrame frame;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Determination window = new Determination();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public Determination() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Button button = new Button("New button");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PointsAndKeys pak = new PointsAndKeys();
				pak.NewScreen();
			}
		});
		frame.getContentPane().add(button, BorderLayout.SOUTH);
	}

	public static ArrayList<String> _keys = new ArrayList<String>(); // Список возможных переходов
	public static ArrayList<Point> _points = new ArrayList<Point>(); // Список возможных начальных точек

	
	public static void ClearKeys(){
		_keys.clear();
	}
	public static void ClearPoints(){
		_points.clear();
	}
	
	
}
