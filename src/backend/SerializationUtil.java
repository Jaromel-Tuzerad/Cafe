/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

 package backend;
/**
 * @version 2018-10-08
 * @author lenka.hruskova
 */
 
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * A simple class with generic serialize and deserialize method implementations
 * @version 2018-10-08
 * @author lenka.hruskova
 * 
 */
public class SerializationUtil {

	// serialize the given object and save it to file
	public static void serialize(ArrayList<Cafe> obj, String fileName) throws IOException {
		FileOutputStream fos = new FileOutputStream(fileName);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
      for (Object o : obj) {
        oos.writeObject(o);
      }
		fos.close();
	}
        
  // deserialize to Object from given file
	public static ArrayList<Product> deserialize(String fileName) throws IOException, ClassNotFoundException {
    ArrayList<Product> obj = new ArrayList<Product>();
		FileInputStream fis = new FileInputStream(fileName);
    ObjectInputStream ois = new ObjectInputStream(fis);
    Object o = null;
      try {
        for (int i=0; i<10; i++) {
          o = ois.readObject();
          obj.add((Product)o);
        } 
      } catch(EOFException e) { 
         fis.close();
      }
      return obj;
	}

}
