package openUni.Users.Events;

import openUni.Events.IEvent;
import openUni.Users.User;

public class CreateUserEvent implements IEvent 
{
    public static String Name = "CreateUserEvent";
    private User _user;
    
    public CreateUserEvent(User user){
        _user = user;
    }
    
    public User getUser(){
        return _user;
    }

    @Override
    public String getName() {
        return Name;
    }
}
