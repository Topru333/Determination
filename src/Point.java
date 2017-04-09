
import java.util.ArrayList;

// Class for each point of graphs
public class Point {
	private String _name; // Name of point
	private ArrayList<Point> _points;
	public String GetName(){
		if(_points.isEmpty()){
			return _name;
		}
		else {
			String name = "";
			for(Point p: _points){
				name += p.GetName();
			}
			return name;
		}
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
	private int GetLinkCount(){
		return _links.size();
	}
	public ArrayList<Point> GetListOfPoints(String key){
		ArrayList<Point> points = new ArrayList<Point>();
		for(Link l : _links){
			if(l.GetKey().equals(key)){
				points.add(l.GetPoint());
			}
		}
		return points;
	}
	@Override
	public String toString() {
	    return _name;
	}
	
	public Point(String name, boolean end, boolean start){
		_name = name;
		_isEnd = end;
		_isStart = start;
	}
	public Point(ArrayList<Point> p, boolean end){
		_points = p;
		_isEnd = end;
	}
	
	public boolean AddLink(String key, Point point){
		for(int i = 0; i< GetLinkCount()-1;i++){
			if(_links.get(i).GetKey()== key && _links.get(i).GetPoint() == point){
				return false;
			}
		}
		_links.add(new Link(key,point));
		return true;
	}
	public void ClearLinks(){
		_links.clear();
	}
}

