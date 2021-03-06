package openUni.Users.Commands;

import openUni.Events.EventManager;
import openUni.Events.EventPublisher;
import openUni.Persistence.IRepository;
import openUni.Users.AddressInformation;
import openUni.Users.Events.CreateUserEvent;
import openUni.Users.Specifications.ValidUserSpecification;
import openUni.Users.User;

public class CreateUserCommand extends EventPublisher {
    public CreateUserCommand(IRepository<User> userRepo){
        super(new EventManager(CreateUserEvent.Name));
    }

    /**
     * Creates a user and verifies that the information is valid
     * @param email Email address of the user
     * @param firstName The users first name
     * @param lastName The users second name - or surname
     * @param addressInformation The Address information of the user
     * @return True when valid user information is passed, else false
     */
    public boolean createUser(String email, String firstName, String lastName, AddressInformation addressInformation) {
        // Create user information
        User user = new User();
        user.setEmail(email);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setAddress(addressInformation);
        
        /*
            Create and run the specification to check that the information is as
            valid as it can be at this point in time
        */
        ValidUserSpecification specification = new ValidUserSpecification(user);
        if(specification.execute()){
            
            // Notify all subscribers that a user has been created
            notify(CreateUserEvent.Name, new CreateUserEvent(user));
            return true;
        }
        
        return false;
    }
    
}
