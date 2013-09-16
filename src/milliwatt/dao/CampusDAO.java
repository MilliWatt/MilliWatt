package milliwatt.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import milliwatt.model.Campus;
import milliwatt.utils.Global;

public  class CampusDAO{

	public  static int toFile(ArrayList<Campus> list) {
		
		ObjectOutput out;
		try {
			out = new ObjectOutputStream(new FileOutputStream(Global.FILE_DIR + "data"));
	        out.writeObject(list);
	        out.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return 1;
	}

	
	@SuppressWarnings("unchecked")
	public static ArrayList<Campus> toObject() {
		ArrayList<Campus> list = null;
		try {
				    FileInputStream inFile = new FileInputStream(Global.FILE_DIR+"data");
				    ObjectInputStream d = new ObjectInputStream(inFile);
				    Object o = d.readObject();
				    list = (ArrayList<Campus>) o;
				    d.close();

		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}

		return list;
	}
	
	
	
	
	
}
