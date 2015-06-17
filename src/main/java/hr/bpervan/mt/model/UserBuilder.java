package hr.bpervan.mt.model;

import hr.bpervan.mt.functions.Function;

import java.util.Map;

/**
 * Created by Branimir on 17.6.2015..
 */
public class UserBuilder {
    private UserBuilder(){
        this.userId = -1;
        this.firstName = null;
        this.lastName = null;
        this.timeMap = null;
    }

    private int userId;
    private String firstName;
    private String lastName;
    private Map<Integer, Function> timeMap;

    public static UserBuilder getInstance(){
        return new UserBuilder();
    }

    public UserBuilder setUserId(int userId){
        this.userId = userId;
        return this;
    }

    public UserBuilder setFirstName(String firstName){
        this.firstName = firstName;
        return this;
    }

    public UserBuilder setLastName(String lastName){
        this.lastName = lastName;
        return this;
    }

    public UserBuilder setTimeMap(Map<Integer, Function> timeMap){
        this.timeMap = timeMap;
        return this;
    }

    public User build(){
        return new User(userId, firstName, lastName, timeMap);
    }
}
