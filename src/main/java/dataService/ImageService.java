/**
 * 
 */
package dataService;

import java.util.HashMap;

/**
 * @author yanliang
 *
 */
public class ImageService  {
	
	private HashMap<String, String> table;
	
	public ImageService(){
		this.table = new HashMap<String, String>();
		table.put("iphone5s16g", "http://i.ebayimg.com/00/s/OTkxWDEyMDA=/z/NZQAAOSwBLlVeb0c/$_57.JPG");
		table.put("gopro3white","http://i.ebayimg.com/00/s/NzUwWDc1MA==/z/56kAAOSwymxVQT8Q/$_12.JPG");
	    table.put("amazonfire","http://i.ebayimg.com/00/s/ODEzWDgxMQ==/z/G5gAAOSwgQ9VmtUN/$_57.JPG");
	}
	
	public String getItemImage(String item){
		
		return this.table.get(item);
	}
	
}
