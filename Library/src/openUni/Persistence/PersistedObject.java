package openUni.Persistence;

public class PersistedObject {
    private int _identifier;
        
    public int getId(){
        return _identifier;
    }
    
    public void setId(int id){
        _identifier = id;
    }
}
