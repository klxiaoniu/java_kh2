import java.time.LocalDate;

interface AnimalShop {
    void buyAnimal(Animal animal);

    void treatCustomer(Customer customer);

    void setClosed(boolean closed);

    double getCash();

    void setCash(double v);
}

abstract class Animal {
    protected String name;
    protected int age;
    protected boolean sex;
    protected double price;

    public Animal(String name, int age, boolean sex, double price) {
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.price = price;
    }

    @Override
    public abstract String toString();
}

class Customer {
    private String name;
    private int times;
    private LocalDate last;
    private double pay;

    Customer(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", times=" + times +
                ", last=" + last +
                ", pay=" + pay +
                '}';
    }

    public void setTimes() {
        this.times++;
    }

    public void setLast(LocalDate last) {
        this.last = last;
    }

    public LocalDate getLast() {
        return this.last;
    }

    public String getName() {
        return this.name;
    }

    public void addPay(double pay) {
        this.pay += pay;
    }

    public double getPay() {
        return this.pay;
    }
}

class InsufficientBalanceException extends RuntimeException {

    public InsufficientBalanceException(String message) {
        super(message);
    }

}

class AnimalNotFoundException extends RuntimeException {

    public AnimalNotFoundException(String message) {
        super(message);
    }

}
