package openUni.Users.Specifications;

import java.util.regex.Pattern;
import openUni.Users.User;

public class ValidUserSpecification extends openUni.Specification {

    private final User _user;
    private final Pattern  _emailPattern;
    
    public ValidUserSpecification(User user){
        this._user = user;
        
        // pattern from - https://emailregex.com/
        _emailPattern = Pattern.compile("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])");
    }

    /**
     * Checks the user information is valid 
     * @return true when user information looks valid, false otherwise
     */
    @Override
    public boolean execute() {
        // Check that the email address looks valid via regex
        String email = _user.getEmail();
        if(!_emailPattern.matcher(email).matches())
            return false;
        
        // Check we have set a first name for the user
        String firstName = _user.getFirstName();
        if(firstName == null || firstName.isEmpty())
            return false;
        
        // Check we have set a last name for the user
        String lastName = _user.getLastName();
        if(lastName == null || lastName.isEmpty())
            return false;
        
        // User information is valid, return true;
        return true;
    }
}
