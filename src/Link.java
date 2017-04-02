
public class Link{
	private String _key;
	public String GetKey(){
    	return _key;
    }
	
    private Point _point;
    public Point GetPoint(){
    	return _point;
    }
    
	 public Link (String key,Point point) {
         _key = key;
         _point = point;
     }
}
