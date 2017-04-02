
import java.util.ArrayList;

// Class for each point of graphs
public class Point {
	private String _name; // Name of point
	public String GetName(){
		return _name;
	}
	
	private boolean _isEnd = false; // Is it last point?
	public boolean IsEnd(){
		return _isEnd;
	}
	
	private boolean _isStart = false; // Is it first point?
	public boolean IsStart(){
		return _isStart;
	}
	
	private ArrayList<Link> _links = new ArrayList<Link>();
	public ArrayList<Link> GetLinks(){
		return _links;
	}
	private int GetLinkSize(){
		return _links.size();
	}
	
	
	public Point(String name, boolean end, boolean start){
		_name = name;
		_isEnd = end;
		_isStart = start;
	}

	
	public boolean AddLink(String key, Point point){
		for(int i = 0; i< GetLinkSize()-1;i++){
			if(_links.get(i).GetKey()== key && _links.get(i).GetPoint() == point){
				return false;
			}
		}
		_links.add(new Link(key,point));
		return true;
	}
}

