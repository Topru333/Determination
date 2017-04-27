import java.awt.EventQueue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

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
    public static void TracePoints(ArrayList<Point> array){
    	if(logger.isTraceEnabled()){
    		String text = "";
    		for(Point p:array){
    			text += p.Name() +" ";
    		}
    		logger.trace(text);
    	}
    }
    public static void TraceLinks(ArrayList<Link> array){
    	if(logger.isTraceEnabled()){
    		String text = "";
    		for(Link l:array){
    			text += l.GetFirstPoint().Name() + "-" + l.GetKey() + "-" + l.GetSecondPoint().Name() +" ";
    		}
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
				Link l = new Link(GetPoint(PointBox1.getSelectedItem().toString()),(String)KeyBox.getSelectedItem(),GetPoint(PointBox2.getSelectedItem().toString()));
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
		
		JButton btnTest = new JButton("Test");
		btnTest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AddPoint("A",false);
				AddPoint("B",true);
				AddPoint("C",true);
				AddPoint("D",true);
				AddKey("0");
				AddKey("1");
				Link l = new Link(GetPoint("A"),"0",GetPoint("B"));
				model.addRow(new Object[]{"A","0","B"});
				_links.add(l);
				 l = new Link(GetPoint("A"),"0",GetPoint("C"));
				model.addRow(new Object[]{"A","0","C"});
				_links.add(l);
				 l = new Link(GetPoint("B"),"1",GetPoint("A"));
				model.addRow(new Object[]{"B","1","A"});
				_links.add(l);
				 l = new Link(GetPoint("B"),"1",GetPoint("D"));
				model.addRow(new Object[]{"B","1","D"});
				_links.add(l);
				 l = new Link(GetPoint("C"),"0",GetPoint("B"));
				model.addRow(new Object[]{"C","0","B"});
				_links.add(l);
				 l = new Link(GetPoint("C"),"1",GetPoint("A"));
				model.addRow(new Object[]{"C","1","A"});
				_links.add(l);
				 l = new Link(GetPoint("C"),"0",GetPoint("D"));
				model.addRow(new Object[]{"C","0","D"});
				_links.add(l);
				 l = new Link(GetPoint("D"),"1",GetPoint("C"));
				model.addRow(new Object[]{"D","1","C"});
				_links.add(l);
				ComboBoxUpdate();
			}
		});
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(table, GroupLayout.DEFAULT_SIZE, 269, Short.MAX_VALUE)
					.addGap(10)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(DetButton, GroupLayout.PREFERRED_SIZE, 185, GroupLayout.PREFERRED_SIZE)
						.addComponent(PointBox2, GroupLayout.PREFERRED_SIZE, 185, GroupLayout.PREFERRED_SIZE)
						.addComponent(PointBox1, GroupLayout.PREFERRED_SIZE, 185, GroupLayout.PREFERRED_SIZE)
						.addComponent(KeyBox, GroupLayout.PREFERRED_SIZE, 185, GroupLayout.PREFERRED_SIZE)
						.addComponent(AddButton, GroupLayout.PREFERRED_SIZE, 185, GroupLayout.PREFERRED_SIZE)
						.addComponent(ClearButton, GroupLayout.PREFERRED_SIZE, 185, GroupLayout.PREFERRED_SIZE)
						.addComponent(SettingsButton, GroupLayout.PREFERRED_SIZE, 185, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnTest))
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
							.addPreferredGap(ComponentPlacement.RELATED, 228, Short.MAX_VALUE)
							.addComponent(btnTest)
							.addPreferredGap(ComponentPlacement.RELATED)
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
	private static ArrayList<Point> _detpoints = new ArrayList<Point>(); // First List of points
	private static Set<String> endLinks;
	public static Set<String> GetSet(){
		return endLinks;
	}
	
	private static ArrayList<Link> _links = new ArrayList<Link>();
	private static ArrayList<Link> _newlinks = new ArrayList<Link>();
	private JTable table;
	private JButton SettingsButton;
	private JButton DetButton;
	private static boolean newPoints = true;
	public static ArrayList<String> GetKeys(){
		return _keys;
	}
	public static Point GetPoint(String name){
		for(Point p:_points){
			if(p.Name().equals(name)) return p;
		}
		for(Point p:_detpoints){
			if(p.Name().equals(name)) return p;
		}
		return null;
	}
	public static ArrayList<Point> GetPoints(){
		return _points;
	}
	public static ArrayList<Link> GetLinks(){
		return _links;
	}
	public static ArrayList<Link> GetNewLinks(){
		return _newlinks;
	}
	public static boolean containsPoint(ArrayList<Point> points,Point point){
		for(Point p : points){
			if(p.Name().equals(point.Name())){
				return true;
			}
		}
		return false;
	}
	public static void SetNewLinks(ArrayList<Link> links){
		newPoints = false;
		for(Link link : links){
			if(!containsPoint(_detpoints,link.GetSecondPoint())){
				newPoints = true;
				_detpoints.add(link.GetSecondPoint());
			}
		}
		for(Link link : links){
			boolean add = true;
			for(Link l : _newlinks){
				if(l.equals(link)){
					add = false;
				}
			}
			if(add){
				_newlinks.add(link);
			}
			
		}
		
	}
	public static void ClearKeys(){
		_keys.clear();
	}
	public static void ClearPoints(){
		_points.clear();
	}
	
	private Point GetNewPoint(Point point,String Key,ArrayList<Link> Links){
		ArrayList<Character> newOne = new ArrayList<Character>();
		boolean isEnd = false;
		for(char c : point.Name().toCharArray()){
				for(Link l : Links){ // Для каждой связки

					if(l.GetFirstPoint().Name().equals(""+c)){ // Если первая точка в связке та же
						if(l.GetKey().equals(Key)){ // Если ключ тот же то добавляем к имени
							if(!newOne.contains(l.GetSecondPoint().Name())){
								newOne.add(l.GetSecondPoint().Name().charAt(0));
							}
						}
						if(l.GetFirstPoint().isEnd()){
							isEnd = true;
						}
					}
				}
			
		}
		Collections.sort(newOne);
		String p = "";
		for(char c:newOne){
			if(!p.contains(""+c)){
				p += c;
			}
		}
		return new Point(p,isEnd);
	}

    
	private void Determinate(){
		Collections.sort(_keys);
		
		logger.info("Started determination");
		ArrayList<Link> Links = new ArrayList<Link>(); 
		ArrayList<Point> firstPoints = GetEnds(_points);
		firstPoints.add(GetStart(_points));
		Trace("First points for determinate: ");
		TracePoints(firstPoints);
		Trace("--------------");
		for(Point point : firstPoints){
			for(String key : _keys){
				Point newPoint = GetNewPoint(point, key, _links);
				if(!newPoint.Name().equals("")){
					Links.add(new Link(point,key,newPoint));
				}
			}
		}
		Trace("New Links: ");
		TraceLinks(Links);
		SetNewLinks(Links);
		
		
		while(newPoints){
			Links.clear();
			for(Link l : _newlinks){
				for(String key : _keys){
					Point newPoint = GetNewPoint(l.GetSecondPoint(), key, _links);
					if(!newPoint.Name().equals("")){
						Links.add(new Link(l.GetSecondPoint(),key,newPoint));
					}
				}
				Trace("Created a new points? : "+newPoints);
			}
			SetNewLinks(Links);
			Trace("New links:");
			
			
		}
		TraceLinks(GetNewLinks());
		endLinks = deleteCopies(GetNewLinks());
		
		
		logger.debug("Determination - End");
	}
	private Set<String> deleteCopies(ArrayList<Link> links){
		Set<String> set = new HashSet<String>(); 
		for(Link link : links){
			set.add(link.GetFirstPoint().Name() + "-" + link.GetKey() + "-" + link.GetSecondPoint().Name());
		}
		return set;
	}
	public boolean LinkExist(Link l){
		for(Link link:_links){
			if(link.GetFirstPoint().equals(l.GetFirstPoint())&&link.GetSecondPoint().equals(l.GetSecondPoint())&&link.GetKey().equals(l.GetKey())){
				return true;
			}
		}
		return false;
	}
	private ArrayList<Point> GetEnds(ArrayList<Point> points){
		ArrayList<Point> ends = new ArrayList<Point>();
		for(Point point:points){
			if(point.isEnd()){
				ends.add(point);
			}
		}
		return ends;
	}
	private Point GetStart(ArrayList<Point> points){
		for(Point point:points){
			if(point.Name().equals("A")){
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
	public static boolean AddPoint (String name,boolean end) {
		for(Point point : _points) {
            if(point.Name().equals(name)) { return false; }
        }
		_points.add(new Point(name,end));
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
		for(Point p:_points){
			if(p.Name().equals(Name)){
				try {
		            _points.remove(Name);
		        }
		        catch (Exception e) { }
			}
		}
    }
	
	public static void ComboBoxUpdate(){
		PointBox1.removeAllItems();
		PointBox2.removeAllItems();
		KeyBox.removeAllItems();
			for(Point p:Determination.GetPoints()){
				PointBox1.addItem(p.Name());
				PointBox2.addItem(p.Name());
			}
			for(String s:Determination.GetKeys()){
				KeyBox.addItem(s);
			}
	}
}
