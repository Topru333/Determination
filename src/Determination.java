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

	public static ArrayList<String> _keys = new ArrayList<String>(); // List of valible links
	public static ArrayList<Point> _points = new ArrayList<Point>(); // First List of points

	
	public static void ClearKeys(){
		_keys.clear();
	}
	public static void ClearPoints(){
		_points.clear();
	}
	
	// Return point with same name
	public Point GetPoint(String name){
		name = name.toUpperCase();
		for(Point point : _points){
			if(point.GetName() == name){
				return point;
			}
		}
		return null;
	}
	
	// Add key if here nothing with same name
	public void AddKey (String Key) {
        Key = Key.toUpperCase();
        // 1 - Repeat check
        for(String key : _keys) {
            if(Key == key) { return; }
        }
        // 2 - Add
        _keys.add(Key);
    }
	
	// Add link for point
	public boolean AddLink (Point point) {
        return true;
    }
	
	// Add point (return false if we have same name in list of points)
	public boolean AddPoint (String name, boolean end , boolean start) {
        name = name.toUpperCase();
        for (Point point : _points) {
            if (point.GetName() == name) return false;
        }
        _points.add(new Point(name, end, start));
        return true;
    }
	
	// Delete key from List of keys
	public void DeleteKey(String Key){
		Key = Key.toUpperCase();
        try {
            _keys.remove(Key);
        }
        catch (Exception e) { }
	}
	
	// Delete point from list of the points
	public void DeletePoint (String Name) {
        Name = Name.toUpperCase();
        for(Point p : _points) {
            if(p.GetName() == Name) {
                _points.remove(p);
                return;
            }
        }
    }
	
}
