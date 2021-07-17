package openUni.Persistence;

import java.util.HashMap;
import java.util.List;
import openUni.InMemoryRepository;
import openUni.Users.User;
import org.junit.Assert;
import org.junit.Test;

public class InMemoryRepositoryTests {
    @Test
    public void savingEntity_AddsRecordToList(){
        InMemoryRepository<User> repository = new InMemoryRepository<>();
        repository.save(new User());
        
        HashMap<Integer, User> users = repository.getRecords();
        Assert.assertTrue(users.size() == 1);
    }
    
    @Test
    public void savingEntity_GivesEntityIdentifier(){
        InMemoryRepository<User> repository = new InMemoryRepository<>();
        
        User user = new User();
        int identifier = repository.save(user);
        Assert.assertEquals(identifier, user.getId());
    }
    
    @Test
    public void savingEntity_IncreasesIdentifier(){
        InMemoryRepository<User> repository = new InMemoryRepository<>();
        repository.save(new User());
        
        int identifier = repository.getIdentifier();
        Assert.assertEquals(1, identifier);
        
        repository.save(new User());
        
        identifier = repository.getIdentifier();
        Assert.assertEquals(2, identifier);
    }
    
    @Test
    public void givenFilter_ThenReturnsValidRecords(){
        User user1 = createUserWithName("John", "Doe");
        User user2 = createUserWithName("John", "Doe");
        User user3 = createUserWithName("John", "Doe");
        User user4 = createUserWithName("Jane", "Doe");
        User user5 = createUserWithName("Jane", "Doe");
        
        InMemoryRepository<User> repository = new InMemoryRepository<>();
        repository.save(user1);
        repository.save(user2);
        repository.save(user3);
        repository.save(user4);
        repository.save(user5);
        
        List<User> johns = repository.filter((user) -> "John".equals(user.getFirstName()));
        List<User> janes = repository.filter((user) -> "Jane".equals(user.getFirstName()));
        
        Assert.assertEquals(3, johns.size()); 
        Assert.assertEquals(2, janes.size());
    }
    
    @Test
    public void givenFilter_WhenNoRecordsExist_ThenEmptyResultSetIsReturned(){
        InMemoryRepository<User> repository = new InMemoryRepository<>();
        
        List<User> johns = repository.filter((user) -> "John".equals(user.getFirstName()));
        Assert.assertTrue(johns.isEmpty());
    }
    
    @Test
    public void whenUpdatingRecord_RecordIsUpdated(){
        InMemoryRepository<User> repository = new InMemoryRepository<>();
        
        User user = createUserWithName("John", "Doe");
        repository.save(user);
        
        user.setFirstName("Jane");
        repository.update(user);
        
        User userFromStore =repository.get(user.getId());
        
        Assert.assertEquals("Jane", userFromStore.getFirstName()); 
    }
    
    private User createUserWithName(String name, String lastName){
        User user=  new User();
        user.setFirstName(name);
        user.setLastName(lastName);
        return user;
    }
}
