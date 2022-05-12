
package economics;
import java.util.Random;

/**
 * This class has a name, birthDate and balance.
 * Gives index each object automatically
 */
public class Client {

    private String name;
    private int birthDate;
    private int index;
    private double balance;


    /**
     * @param name client name
     * @param birthDate client birthdate
     * @param balance client balance
     */
    public Client(String name, int birthDate, double balance){
        Random rand = new Random();
        this.name = name;
        this.birthDate = birthDate;
        this.index = (int)(rand.nextDouble() * 1000);
        this.balance = balance;
    }
    public Client(String name, int index,int birthDate, double balance){
        this.name = name;
        this.birthDate = birthDate;
        this.index = index;
        this.balance = balance;
    }

    /**
     * @return Name
     */
    public String getName(){
        return this.name;
    }


    /**
     * @return Birth Date
     */
    public int getBirthDate(){
        return this.birthDate;
    }

    /**
     * @return Index
     */
    public int getIndex(){
        return this.index;
    }

    /**
     * @return Age
     */
    public int getAge(){
        return 2022 - birthDate;
    }

    /**
     * @return Balance
     */
    public double getBalance(){
        return this.balance;
    }

    /**
     * @param name sets name
     */
    public void setName(String name){
        this.name = name;
    }

    /**
     * @param birthDate sets birthdate
     */
    public void setBirthDate(int birthDate){
        this.birthDate = birthDate;
    }

    /**
     * @param balance sets balance
     */
    public void setBalance(double balance){
        this.balance = balance;
    }

    /**
     * @return object variables as a string
     */
    @Override
    public String toString(){
        return "Name: "+name+ ", Age: "+this.getAge()+", Index: "+ index+", Balance: "+balance;
    }
}
