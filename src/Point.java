
import java.util.ArrayList;

// ласс дл€ каждой точки графа
public class Point {
	private String _name; // »м€ точки
	public String GetName(){
		return _name;
	}
	
	private boolean _isEnd = false; // явл€етс€ ли конечной
	public boolean IsEnd(){
		return _isEnd;
	}
	
	private boolean _isStart = false; // явл€етс€ ли первой
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
	public Point(String name, boolean end){
		_name = name;
		_isEnd = end;
	}
	public Point(String name){
		_name = name;
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

