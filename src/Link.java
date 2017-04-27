
public class Link {
	private Point FirstPoint,SecondPoint;
	private String Key;
	public Point GetFirstPoint(){
		return FirstPoint;
	}
	public String GetKey(){
		return Key;
	}
	public Point GetSecondPoint(){
		return SecondPoint;
	}
	public Link(Point first,String key,Point second){
		FirstPoint = first;
		Key = key;
		SecondPoint = second;
	}
	public boolean equals(Link l){
		if(l.GetFirstPoint().equals(FirstPoint) && l.GetKey().equals(Key) && l.GetSecondPoint().equals(SecondPoint)){
			return true;
		}
		return false;
	}
}
