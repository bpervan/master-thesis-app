package hr.bpervan.mt.model;

import hr.bpervan.mt.functions.Function;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Branimir on 17.6.2015..
 */
public class UserBuilder {
    private UserBuilder(){
        this.userId = -1;
        this.firstName = null;
        this.lastName = null;
        this.timeMap = new HashMap<>();
    }

    private int userId;
    private String firstName;
    private String lastName;

    /** itemId -> function*/
    private Map<Integer, Function> timeMap;

    /**
     * @return New UserBuilder instance
     */
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

    public UserBuilder addEntryToTimeMap(int itemId, Function function){
        this.timeMap.put(itemId, function);
        return this;
    }

    public User build(){
        return new User(userId, firstName, lastName, timeMap);
    }
}
