package machinelearning;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Serializer<T>{
    
    public void SerializeObject(T obj, String objName){
        
        try{
            FileOutputStream out = new FileOutputStream(objName);
            ObjectOutputStream object = new ObjectOutputStream(out);
            
            object.writeObject(obj);
            object.close();
            out.close();
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public T DeserializeObject(String objName){
        T obj = null;
        
        try{
        
            FileInputStream out = new FileInputStream(objName);
            ObjectInputStream object = new ObjectInputStream(out);

            obj = (T) object.readObject();
            object.close();
            out.close();

        }catch(Exception e){
            
        }
        
        return obj;
    }
}
