package openUni.CreateUser;
import java.util.ArrayList;
import openUni.ui.CreateUsers.CreateUserPresenter;
import openUni.*;
import openUni.Events.EventPublisher;
import openUni.Users.*;
import openUni.Users.Commands.CreateUserCommand;
import openUni.Users.Events.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CreateUserPresenterTest {
    CreateUserCommand _userStore;
    CreateUserPresenter _presenter;
    InMemoryRepository<User> _repository;
    FakeEventListener<CreateUserEvent> _listener;
    
    @Before
    public void setUp() {
        _repository = new InMemoryRepository<>();
        _userStore = new CreateUserCommand();
        _listener = new FakeEventListener<>();
        _presenter = new CreateUserPresenter(_userStore);
        
        EventPublisher.getInstance().subscribe(CreateUserEvent.Name, _listener);
    }

    @Test
    public void testCreateUser() {
        _presenter.createUser("user@email.com", "John", "Doe", new AddressInformation());
        ArrayList<CreateUserEvent> eventsCaptured = _listener.getEventsCaptured();
        Assert.assertNotNull(eventsCaptured.get(0));
    }
    
    @Test
    public void whenEmailIsInvalid_NoEventIsFired() {
        _presenter.createUser("user@.com", "John", "Doe", new AddressInformation());
        Assert.assertTrue(_listener.getEventsCaptured().isEmpty());
    }
    
    @Test
    public void whenNoFirstNameIsSpecified_NoEventIsFired() {
        _presenter.createUser("user@email.com", "", "Doe", new AddressInformation());
        Assert.assertTrue(_listener.getEventsCaptured().isEmpty());
    }
    
    @Test
    public void whenNoLastNameIsSpecified_NoEventIsFired() {
        _presenter.createUser("user@email.com", "John", "", new AddressInformation());
        Assert.assertTrue(_listener.getEventsCaptured().isEmpty());
    }
}


