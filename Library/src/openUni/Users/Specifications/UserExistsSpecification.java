package openUni.Users.Specifications;

import java.util.List;
import openUni.Persistence.IRepository;
import openUni.Users.User;

public class UserExistsSpecification extends openUni.Specification {

    private final IRepository<User> _userRepository;
    private final String _emailAddress;
    
    public UserExistsSpecification(IRepository<User> userRepository, String emailAddress){
        _userRepository = userRepository;
        _emailAddress = emailAddress;
    }
    
    
    @Override
    public boolean execute() {
        List<User> users = _userRepository.filter((u) -> u.getEmail().equals(_emailAddress));
        return users.isEmpty();
    }
    
}
