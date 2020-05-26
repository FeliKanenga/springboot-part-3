package com.example.demo.Service;

import com.example.demo.Dao.FakeRepoInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;



//annotation to show that this is a repository class
@Service
public class UserServiceImpl implements UserService {
    // FakeRepoInterface
    @Autowired
    private FakeRepoInterface fakeRepoInterface;


    // constructor parameter
    public UserServiceImpl(@Qualifier("fakeDao") FakeRepoInterface fakeRepoInterface) {

        this.fakeRepoInterface = fakeRepoInterface;
    }

    //methods overridden from the UserService Interface
    @Override
    public String addUser(long id, String name, String surname) {
        fakeRepoInterface.insertUser(id,name,surname);
        return name;
    }

    @Override
    public void removeUser(long id) {
        fakeRepoInterface.deleteUser(id);
    }

    @Override
    @Cacheable (value = "Users", key = "name")
    //I firmly believe that @Cacheable should go to the com.example.demo.Controller package but for the sake of the instructions
    public String getUser(long id) {
        try
        {
            System.out.println("Going to sleep for 5 Secs.. to simulate backend call.");
            Thread.sleep(1000*5);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
       return fakeRepoInterface.findUserById(id);
    }
}
