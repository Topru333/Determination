
public class Point {
	private String _name = "";
	private boolean _end = false;
	public String Name(){
		return _name;
	}
	public boolean isEnd(){
		return _end;
	}
    public Point(String name,boolean end){
		_name = name;
		_end = end;
	}
    public Point(String name){
		_name = name;
	}
}
