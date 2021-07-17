package openUni.Users.Commands;

import openUni.Persistence.IRepository;
import openUni.Users.User;
import openUni.Users.AddressInformation;
import openUni.Users.Specifications.UserExistsSpecification;
import openUni.Users.Specifications.ValidUserSpecification;

public class CreateUserCommand {

    private final IRepository<User> _userRepository;
    
    public CreateUserCommand(IRepository<User> userRepository){
        _userRepository = userRepository;
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
        UserExistsSpecification userExistsSpecification 
                = new UserExistsSpecification(_userRepository, email);
        
        if(specification.execute() && userExistsSpecification.execute()){
            
            // Save the user
            user.save();
            return true;
        }
        
        return false;
    }
    
}
