package code.domain;
import java.io.Serializable;
import java.util.Objects;

public class Patient<Integer> extends Identifiable<Integer> implements Serializable {

    private String illness;
    private String name;
    private int age;

    public Patient(Integer id, String name, String illness,int age)
    {
        super(id);
        this.name = name;
        this.illness = illness;
        this.age = age;
    }

    public String getName() {return this.name;}
    public String getIllness() {return this.illness;}
    public int getAge() {return this.age;}
    public void setIllness(String newi)
    {
        this.illness = newi;
    }
    public void setName(String newname)
    {
        this.name = newname;
    }
    public void setAge(int newage)
    {
        this.age = newage;
    }
    @Override
    public String toString()
    {
        return "Patient : " +
                "id =" + id +
                ", Name =" + name +
                ", Illness =" + illness +
                ", Age =" + age +
                '}';
    }
    @Override
    public boolean equals(Object o)
    {
        if(this == o){
            return true;
        }
        if (!(o instanceof Patient<?> that)) return false;
        if (!super.equals(o)) return false;
        return Objects.equals(name, that.name) && Objects.equals(illness, that.illness) && Objects.equals(age, that.age);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id, name, illness, age);
    }

}
