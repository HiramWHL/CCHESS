package zwlhw.main.client;

import java.io.File;
import java.io.IOException;
import java.net.*;


public class FileLoader {
	 
	public boolean exists(){
		URL resource = FileLoader.class.getResource("/library/a.txt");
		if(resource==null){
			return false;
		}
		File f = new File(resource.getFile());
		return f.exists();
	}
 
}