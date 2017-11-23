package machinelearning;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class Serializer<T>{
    
    private String objName;
    
    public void SerializeObject(T obj, String objName){
        
        this.objName = objName;
        
        try{
            FileOutputStream out = new FileOutputStream(this.objName);
            ObjectOutputStream object = new ObjectOutputStream(out);
            
            object.writeObject(obj);
            object.close();
            out.close();
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
}
