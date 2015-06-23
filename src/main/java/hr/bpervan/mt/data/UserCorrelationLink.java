package hr.bpervan.mt.data;

/**
 * Created by Branimir on 16.6.2015..
 */
@Link
public class UserCorrelationLink implements Comparable<UserCorrelationLink>{
    public int userId;
    public double correlation;

    public UserCorrelationLink(int userId, double correlation){
        this.userId = userId;
        this.correlation = correlation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserCorrelationLink)) return false;

        UserCorrelationLink that = (UserCorrelationLink) o;

        if (userId != that.userId) return false;
        return Double.compare(that.correlation, correlation) == 0;

    }

    @Override
    public String toString() {
        return "UserCorrelationLink{" +
                "userId=" + userId +
                ", correlation=" + correlation +
                '}';
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = userId;
        temp = Double.doubleToLongBits(correlation);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public int compareTo(UserCorrelationLink o) {
        return Double.compare(this.correlation, o.correlation);
    }
}
