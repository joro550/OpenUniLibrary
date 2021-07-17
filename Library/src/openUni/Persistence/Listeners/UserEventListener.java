package openUni.Persistence.Listeners;

import openUni.Events.IEventListener;
import openUni.Users.Events.CreateUserEvent;
import openUni.Persistence.IRepository;
import openUni.Users.User;

public class UserEventListener implements IEventListener<CreateUserEvent>{
    
    private final IRepository<User> _userPersistence;
    
    public UserEventListener(IRepository<User> userPersistence){
        _userPersistence = userPersistence;
    }

    @Override
    public void update(CreateUserEvent eventType) {
        _userPersistence.save(eventType.getUser());
    }
}
