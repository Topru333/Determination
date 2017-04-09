import java.awt.EventQueue;

import java.util.ArrayList;
import java.util.Map;

import javax.swing.JFrame;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTable;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Window.Type;
import javax.swing.table.DefaultTableModel;

public class Determination {

	private JFrame frame;
	private static JComboBox<Point> PointBox1;
	private static JComboBox<String> KeyBox;
    private static JComboBox<Point> PointBox2;
    private DefaultTableModel model;

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
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setType(Type.UTILITY);
		frame.setBackground(new Color(255, 255, 255));
		frame.getContentPane().setBackground(new Color(240, 255, 255));
		frame.setBounds(100, 100, 500, 500);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"PointFrom", "Key", "PointTo"
			}
		));
		table.setBackground(new Color(169, 169, 169));
		model = (DefaultTableModel) table.getModel();
		
		PointBox1 = new JComboBox<Point>();
		
		KeyBox = new JComboBox<String>();
		
		PointBox2 = new JComboBox<Point>();
		
		JButton AddButton = new JButton("Add");
		AddButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		AddButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!(PointBox1.getSelectedIndex() == -1)){
				((Point)PointBox1.getSelectedItem()).AddLink(KeyBox.getSelectedItem().toString(), ((Point)PointBox2.getSelectedItem()));
				
				model.addRow(new Object[]{((Point)PointBox1.getSelectedItem()).GetName(),KeyBox.getSelectedItem().toString(),((Point)PointBox2.getSelectedItem()).GetName()});
			
				}
			}
		});
		
		JButton ClearButton = new JButton("Clear");
		ClearButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		ClearButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (Point p : Determination._points){
					p.ClearLinks();
				}
				
			}
			
		});
		
		SettingsButton = new JButton("Settings");
		SettingsButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		SettingsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				PointsAndKeys pak = new PointsAndKeys();
				pak.NewScreen();
			}
		});
		
		DetButton = new JButton("Determinate");
		DetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Determinate();
				ResultWidow rw = new ResultWidow();
				rw.NewScreen();
				
			}
		});
		DetButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(table, GroupLayout.DEFAULT_SIZE, 269, Short.MAX_VALUE)
					.addGap(10)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addComponent(DetButton, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 185, GroupLayout.PREFERRED_SIZE)
						.addComponent(PointBox2, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 185, GroupLayout.PREFERRED_SIZE)
						.addComponent(PointBox1, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 185, GroupLayout.PREFERRED_SIZE)
						.addComponent(KeyBox, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 185, GroupLayout.PREFERRED_SIZE)
						.addComponent(AddButton, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 185, GroupLayout.PREFERRED_SIZE)
						.addComponent(ClearButton, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 185, GroupLayout.PREFERRED_SIZE)
						.addComponent(SettingsButton, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 185, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(PointBox1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(KeyBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(PointBox2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(AddButton)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(ClearButton)
							.addPreferredGap(ComponentPlacement.RELATED, 257, Short.MAX_VALUE)
							.addComponent(DetButton)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(SettingsButton))
						.addComponent(table, GroupLayout.DEFAULT_SIZE, 439, Short.MAX_VALUE))
					.addContainerGap())
		);
		frame.getContentPane().setLayout(groupLayout);
	}

	private static ArrayList<String> _keys = new ArrayList<String>(); // List of valible links
	private static ArrayList<Point> _points = new ArrayList<Point>(); // First List of points
	private static ArrayList<Point> _detpoints = new ArrayList<Point>(); // Second List of points (after determinate)
	private JTable table;
	private JButton SettingsButton;
	private JButton DetButton;
	
	public static ArrayList<String> GetKeys(){
		return _keys;
	}
	public static ArrayList<Point> GetPoints(){
		return _points;
	}
	public static ArrayList<Point> GetDetPoints(){
		return _detpoints;
	}
	
	public static void ClearKeys(){
		_keys.clear();
	}
	public static void ClearPoints(){
		_points.clear();
	}
	public static void ClearDetPoints(){
		_detpoints.clear();
	}
	
	
	private void Determinate(){
		ArrayList<Point> newPoints = new ArrayList<Point>();
		Point started = GetStart(_points);
		ArrayList<Point> ends = GetEnds(_points);
		ArrayList<Point> list;
		for(String s:_keys){
			list = started.GetListOfPoints(s);
			boolean end = false;
			for(Point p : list){
				if(p.IsEnd()){
					end = true;
				}
			}
			newPoints.add(new Point(list,end));
		}
		for(Point p : ends){
			
		}
		
	}
	
	private ArrayList<Point> GetEnds(ArrayList<Point> points){
		ArrayList<Point> ends = new ArrayList<Point>();
		for (Point p: points){
			if(p.IsEnd()){
				ends.add(p);
			}
		}
		return ends;
	}
	private Point GetStart(ArrayList<Point> points){
		for (Point p: points){
			if(p.IsEnd()){
				return p;
			}
		}
		return null;
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
	public static boolean AddKey (String Key) {
        Key = Key.toUpperCase();
        // 1 - Repeat check
        for(String key : _keys) {
            if(Key == key) { return false; }
        }
        // 2 - Add
        _keys.add(Key);
        return true;
    }
	
	// Add link for point
	public static boolean AddLink (Point point) {
        return true;
    }
	
	// Add point (return false if we have same name in list of points)
	public static boolean AddPoint (String name, boolean end , boolean start) {
        name = name.toUpperCase();
        for (Point point : _points) {
            if (point.GetName() == name) return false;
        }
        _points.add(new Point(name, end, start));
        return true;
    }
	
	// Delete key from List of keys
	public static void DeleteKey(String Key){
		Key = Key.toUpperCase();
        try {
            _keys.remove(Key);
        }
        catch (Exception e) { }
	}
	
	// Delete point from list of the points
	public static void DeletePoint (String Name) {
        Name = Name.toUpperCase();
        for(Point p : _points) {
            if(p.GetName().equals(Name)) {
                _points.remove(p);
                return;
            }
        }
    }
	public static void ComboBoxUpdate(){
		PointBox1.removeAllItems();
		PointBox2.removeAllItems();
		KeyBox.removeAllItems();
			for(Point p:Determination.GetPoints()){
				PointBox1.addItem(p);
				PointBox2.addItem(p);
			}
			for(String s:Determination.GetKeys()){
				KeyBox.addItem(s);
			}
	}
}
