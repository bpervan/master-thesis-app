package hr.bpervan.mt.model;

import hr.bpervan.mt.functions.Function;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Branimir on 2.6.2015..
 */
public class User {
    private Map<Integer, Function> time;
    private int userId;
    private String firstName;
    private String lastName;

    public User(int userId, String firstName, String lastName) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;

        this.time = new HashMap<>();
    }

    
}
