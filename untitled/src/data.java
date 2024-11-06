import java.sql.Date;

public class data {
    String id;
    float money;
    float rate;
    Date date;
    String firstName;
    String lastName;
    Date birth_day;
    int age;
    public data(){}
    public data(String id,float money,Date date,String firstName,String lastName,Date birth_day,int age){
        this.id = id;
        this.money = money;
        this.date = date;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birth_day = birth_day;
        this.age = age;
    }
    public void setId(String id){
        this.id = id;
    }
    public void setMoney(float money){
        this.money = money;
    }
    public void setDate(Date date){
        this.date = date;
    }
    public void setFirstName(String firstName){
        this.firstName = firstName;
    }
    public void setLastName(String lastName){
        this.lastName = lastName;
    }
    public void setBirth_day(Date birth_day){
        this.birth_day = birth_day;
    }
    public void setAge(int age){
        this.age = age;
    }
    public void setRate(float rate){
        this.rate = rate;
    }

}
