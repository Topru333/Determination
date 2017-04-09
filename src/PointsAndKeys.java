import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Toolkit;
import java.awt.Font;
import javax.swing.UIManager;
import java.awt.Window.Type;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;

public class PointsAndKeys {

	private JFrame frame;
	private JTable table;
	private JTextField text;
	private DefaultTableModel model;
	private JRadioButton rbk,rbp;
	private JCheckBox StartCheck;
	private JCheckBox EndCheck;
	
	/**
	 * Launch the application.
	 */
	public void NewScreen() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PointsAndKeys window = new PointsAndKeys();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public PointsAndKeys() {
		initialize();
	}

	
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setType(Type.UTILITY);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setBackground(new Color(240, 248, 255));
		frame.setBackground(UIManager.getColor("FormattedTextField.inactiveForeground"));
		frame.setForeground(Color.BLACK);
		frame.setFont(new Font("Action Man Extended", Font.PLAIN, 12));
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(PointsAndKeys.class.getResource("/javax/swing/plaf/metal/icons/ocean/computer.gif")));
		frame.setBounds(100, 100, 315, 300);
		
		table = new JTable();
		table.setBorder(null);
		table.setBackground(new Color(169, 169, 169));
		
		text = new JTextField();
		text.setFont(new Font("Tahoma", Font.BOLD, 12));
		text.setBackground(new Color(169, 169, 169));
		text.setColumns(10);
		
		JButton btnNewButton = new JButton("Add");
		
		model = (DefaultTableModel) table.getModel(); 
		model.addColumn("PointKeys");
		table.setEnabled(false);
		
		frame.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		        if (JOptionPane.showConfirmDialog(frame, 
		            "Do you want to save settings?", "Save settings?", 
		            JOptionPane.YES_NO_OPTION,
		            JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
		        	Determination.ComboBoxUpdate();
		        }
		    }
		});
		// Function of Add button ( will add point or key in our List p.s. list in Determination class and he is static)
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!(text.getText().equals("") || text.getText().equals(" "))){
					model.addRow(new Object[]{text.getText().toUpperCase()});
					if(rbp.isSelected()){
						Determination.AddPoint(text.getText().toUpperCase(),getStartCheck().isSelected(),getEndCheck().isSelected());
					}
					else if(rbk.isSelected()){
						Determination.AddKey(text.getText().toUpperCase());
					}
				}
				text.setText("");
			}
		});
		
		
		
		JButton btnNewButton_1 = new JButton("Delete");
		btnNewButton_1.addActionListener(new ActionListener() {
		    
			public void actionPerformed(ActionEvent arg0) {
				
				if(rbp.isSelected()){
					Determination.DeletePoint(text.getText().toUpperCase());
				}
				else if(rbk.isSelected()){
					Determination.DeleteKey(text.getText().toUpperCase());
				}
				JTableUpdate();
			}
		});
		
		JButton btnNewButton_2 = new JButton("Clear");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(rbp.isSelected()){
					Determination.ClearPoints();
				}
				else if(rbk.isSelected()){
					Determination.ClearKeys();
				}
				JTableUpdate();
			}
		});
		
		JRadioButton RbPoints = new JRadioButton("Points");
		JRadioButton RbKeys = new JRadioButton("Keys");
		rbp = RbPoints;
		rbk = RbKeys;
		RbPoints.setSelected(true);
		
		RbPoints.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				RbKeys.setSelected(false);
				JTableUpdate();
			}
		});
		RbKeys.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				RbPoints.setSelected(false);
				JTableUpdate();
			}
		});
		
		StartCheck = new JCheckBox("Start");
		
		EndCheck = new JCheckBox("End");
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(table, GroupLayout.PREFERRED_SIZE, 153, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnNewButton_2, GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE)
						.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE)
						.addComponent(btnNewButton_1, GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE)
						.addComponent(text)
						.addComponent(StartCheck)
						.addComponent(EndCheck)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(RbPoints)
							.addPreferredGap(ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
							.addComponent(RbKeys)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(text, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnNewButton)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnNewButton_1)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnNewButton_2)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(StartCheck)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(EndCheck)
							.addPreferredGap(ComponentPlacement.RELATED, 60, Short.MAX_VALUE)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(RbPoints)
								.addComponent(RbKeys)))
						.addComponent(table, GroupLayout.DEFAULT_SIZE, 239, Short.MAX_VALUE))
					.addContainerGap())
		);
		frame.getContentPane().setLayout(groupLayout);

	}
	
	// Update for JTable after switch of radiocheck buttons
	private void JTableUpdate(){
		model.setRowCount(0);
		if(rbp.isSelected()){ // If Points selected
			for(Point p:Determination.GetPoints()){
				model.addRow(new Object[] {p.GetName()});
			}
			getStartCheck().setVisible(true);
			getEndCheck().setVisible(true);
		}
		else if(rbk.isSelected()){ // If Keys selected
			for(String s:Determination.GetKeys()){
				model.addRow(new Object[] {s});
			}
			getStartCheck().setVisible(false);
			getEndCheck().setVisible(false);
		}
		else { // If nothing selected
			model.addRow(new Object[] { "Error!" });
			model.addRow(new Object[] { "Choice you'r mode please." });
		}
	}
	
	public JCheckBox getStartCheck() {
		return StartCheck;
	}
	public JCheckBox getEndCheck() {
		return EndCheck;
	}
}
