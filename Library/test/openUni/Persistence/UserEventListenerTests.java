package openUni.Persistence;

import openUni.Events.EventManager;
import openUni.InMemoryRepository;
import openUni.Persistence.Listeners.UserEventListener;
import openUni.Users.Events.CreateUserEvent;
import openUni.Users.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class UserEventListenerTests {
    EventManager _manager;
    UserEventListener _listener;
    InMemoryRepository<User> _persistence;

    @Before
    public void beforeTests(){
        _manager = new EventManager(CreateUserEvent.Name);
        _persistence = new InMemoryRepository<>();
        _listener = new UserEventListener(_persistence);
        
        _manager.subscribe(CreateUserEvent.Name, _listener);
    }
    
    @Test
    public void saveUserTest(){
        User user = new User();
        _manager.notify(new CreateUserEvent(user));
        
        User userFromStore = _persistence.get(1);
        Assert.assertEquals(user.getEmail(), userFromStore.getEmail());
        Assert.assertEquals(user.getFirstName(), userFromStore.getFirstName());
        Assert.assertEquals(user.getLastName(), userFromStore.getLastName());
    }
}
