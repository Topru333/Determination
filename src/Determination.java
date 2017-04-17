import java.awt.EventQueue;

import java.util.ArrayList;
import org.apache.log4j.Logger;
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
	private static JComboBox<String> PointBox1;
	private static JComboBox<String> KeyBox;
    private static JComboBox<String> PointBox2;
    private DefaultTableModel model;

    final static Logger logger = Logger.getLogger(Determination.class);
    
    public static void Trace(String text){
    	if(logger.isTraceEnabled()){
    		logger.trace(text);
    	}
    }
    
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
		
		PointBox1 = new JComboBox<String>();
		
		KeyBox = new JComboBox<String>();
		
		PointBox2 = new JComboBox<String>();
		
		JButton AddButton = new JButton("Add");
		AddButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		AddButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Link l = new Link((String)PointBox1.getSelectedItem(),(String)KeyBox.getSelectedItem(),(String)PointBox2.getSelectedItem());
				if(!LinkExist(l)){
					model.addRow(new Object[]{(String)PointBox1.getSelectedItem(),(String)KeyBox.getSelectedItem(),(String)PointBox2.getSelectedItem()});
					_links.add(l);
				}
				
			}
		});
		
		JButton ClearButton = new JButton("Clear");
		ClearButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		ClearButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				_links.clear();
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
	private static ArrayList<String> _points = new ArrayList<String>(); // First List of points
	private static ArrayList<String> _detpoints = new ArrayList<String>(); // Second List of points (after determinate)
	private static ArrayList<Link> _links = new ArrayList<Link>();
	private JTable table;
	private JButton SettingsButton;
	private JButton DetButton;
	
	public static ArrayList<String> GetKeys(){
		return _keys;
	}
	public static ArrayList<String> GetPoints(){
		return _points;
	}
	public static ArrayList<String> GetDetPoints(){
		return _detpoints;
	}
	public static ArrayList<Link> GetLinks(){
		return _links;
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
		String startPoint = GetStart(_points);
		int nRow = model.getRowCount();
		
		for(int i = 0; i < nRow; i++){
			
		}
	}
	
	private boolean LinkExist(Link l){
		for(Link link:_links){
			if(link.GetFirstPoint().equals(l.GetFirstPoint())&&link.GetSecondPoint().equals(l.GetSecondPoint())&&link.GetKey().equals(l.GetKey())){
				return true;
			}
		}
		return false;
	}
	private ArrayList<String> GetEnds(ArrayList<String> points){
		ArrayList<String> ends = new ArrayList<String>();
		for(String point:points){
			if(point.contains("*")){
				ends.add(point);
			}
		}
		return ends;
	}
	private String GetStart(ArrayList<String> points){
		for(String point:points){
			if(point.contains("$")){
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
	
	
	// Add point (return false if we have same name in list of points)
	public static boolean AddPoint (String name) {
		for(String point : _points) {
            if(point == name) { return false; }
        }
		_points.add(name);
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
        try {
            _points.remove(Name);
        }
        catch (Exception e) { }
    }
	
	public static void ComboBoxUpdate(){
		PointBox1.removeAllItems();
		PointBox2.removeAllItems();
		KeyBox.removeAllItems();
			for(String p:Determination.GetPoints()){
				PointBox1.addItem(p);
				PointBox2.addItem(p);
			}
			for(String s:Determination.GetKeys()){
				KeyBox.addItem(s);
			}
	}
}
