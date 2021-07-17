package openUni.Users;

import openUni.Events.EventPublisher;
import openUni.Persistence.PersistedObject;
import openUni.Users.Events.CreateUserEvent;

public class User extends PersistedObject {
    
    private String _firstName;
    private String _lastName;
    private String _email;
    private AddressInformation _addressInformation;    
            
    public String getFirstName(){
        return _firstName;
    }
    
    public void setFirstName(String firstName){
        _firstName = firstName;
    }
    
    public String getLastName(){
        return _lastName;
    }
    
    public void setLastName(String lastName){
        _lastName = lastName;
    }
    
    public String getEmail(){
        return _email;
    }
    
    public void setEmail(String email){
        _email = email;
    }
    
    public AddressInformation getAddress(){
        return _addressInformation;
    }
    
    public void setAddress(AddressInformation address){
        _addressInformation = address;
    }
    
    public void save(){
        EventPublisher.getInstance().notify(new CreateUserEvent(this));
    }
}
