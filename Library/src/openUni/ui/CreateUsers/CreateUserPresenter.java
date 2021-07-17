package openUni.ui.CreateUsers;
import openUni.Users.AddressInformation;
import openUni.Users.Commands.CreateUserCommand;

public class CreateUserPresenter {
    private final CreateUserCommand _createUserCommand;
    
    public CreateUserPresenter(CreateUserCommand command){
        _createUserCommand = command;
    }
    
    public void createUser(String email, String firstName, String lastName, AddressInformation addressInformation){
        _createUserCommand.createUser(email, firstName, lastName, addressInformation);
    }
}
