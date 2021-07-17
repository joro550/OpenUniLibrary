package openUni;

import openUni.Users.Events.CreateUserEvent;
import openUni.Users.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class FakeEventListenerTests {
    FakeEventListener<CreateUserEvent> _listener;
    
    @Before
    public void beforeTests(){
        _listener = new FakeEventListener<>();
    }
    
    @Test
    public void whenUpdate_EventIsAddedToList(){
        _listener.update(new CreateUserEvent(new User()));
        
        Assert.assertEquals(1,_listener.getEventsCaptured().size());
    }
    
}
