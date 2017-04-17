
public class Link {
	private String FirstPoint,Key,SecondPoint;
	public String GetFirstPoint(){
		return FirstPoint;
	}
	public String GetKey(){
		return Key;
	}
	public String GetSecondPoint(){
		return SecondPoint;
	}
	public Link(String first,String key,String second){
		FirstPoint = first;
		Key = key;
		SecondPoint = second;
	}
}
