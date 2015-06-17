package hr.bpervan.mt.model;

import hr.bpervan.mt.functions.Function;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Branimir on 2.6.2015..
 */
public class User {
    private int userId;
    private String firstName;
    private String lastName;
    private Map<Integer, Function> timeMap;

    public User(int userId, String firstName, String lastName, Map<Integer, Function> timeMap) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.timeMap = timeMap;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Map<Integer, Function> getTimeMap() {
        return timeMap;
    }

    public void setTimeMap(Map<Integer, Function> timeMap) {
        this.timeMap = timeMap;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (getUserId() != user.getUserId()) return false;
        if (getFirstName() != null ? !getFirstName().equals(user.getFirstName()) : user.getFirstName() != null)
            return false;
        if (getLastName() != null ? !getLastName().equals(user.getLastName()) : user.getLastName() != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = getUserId();
        result = 31 * result + (getFirstName() != null ? getFirstName().hashCode() : 0);
        result = 31 * result + (getLastName() != null ? getLastName().hashCode() : 0);
        result = 31 * result + (getTimeMap() != null ? getTimeMap().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", timeMap=" + timeMap +
                '}';
    }
}
