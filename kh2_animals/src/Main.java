import java.time.LocalDate;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        AnimalShop animalShop = new myAnimalShop();
        animalShop.setCash(1500.0);
        Dog dog1 = new Dog("AA", 1, false);
        dog1.setVaccineInjected(true);
        Dog dog2 = new Dog("BB", 2, true);
        dog2.setVaccineInjected(false);
        Cat cat1 = new Cat("CC", 1, false, 120);
        try {
            animalShop.buyAnimal(dog1);
            animalShop.buyAnimal(dog2);
            animalShop.buyAnimal(cat1);
        } catch (InsufficientBalanceException e) {
            e.printStackTrace();
        }


        Customer customer = new Customer("Wang");
        animalShop.treatCustomer(customer);
        animalShop.treatCustomer(customer);
        Customer customer2 = new Customer("Li");
        animalShop.treatCustomer(customer2);
        //System.out.println(customer.toString());

        animalShop.setClosed(true);
    }

}

class Dog extends Animal {
    private boolean isVaccineInjected;

    public Dog(String name, int age, boolean sex) {
        super(name, age, sex, 100);
    }

    public boolean isVaccineInjected() {
        return isVaccineInjected;
    }

    public void setVaccineInjected(boolean vaccineInjected) {
        isVaccineInjected = vaccineInjected;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "isVaccineInjected=" + isVaccineInjected +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                ", price=" + price +
                '}';
    }
}

class Cat extends Animal {

    public Cat(String name, int age, boolean sex, double price) {
        super(name, age, sex, 200);
    }

    @Override
    public String toString() {
        return "Cat{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                ", price=" + price +
                '}';
    }
}

class myAnimalShop implements AnimalShop {
    private double cash;
    private List<Animal> lsAnimal = new ArrayList<>();
    private List<Customer> lsCustomer = new ArrayList<>();
    private boolean isClosed;

    public void buyAnimal(Animal animal) {
        if (this.cash >= animal.price) {
            this.lsAnimal.add(animal);
            this.cash -= animal.price;
        } else throw new InsufficientBalanceException(animal.toString());
    }

    public void treatCustomer(Customer customer) {
        if (!lsCustomer.contains(customer)) lsCustomer.add(customer);
        customer.setLast(LocalDate.now());
        customer.setTimes();

        for (Animal i : lsAnimal) {
            System.out.println(lsAnimal.indexOf(i) + "  " + i.toString());
        }
        System.out.println("Welcome " + customer.getName() + ", Pls choose the one you want: ");
        Scanner scanner = new Scanner(System.in);
        int in = scanner.nextInt();
        try {
            double cur = lsAnimal.get(in).price;
            this.cash += cur;
            customer.addPay(cur);
            lsAnimal.remove(in);
        } catch (IndexOutOfBoundsException e) {
            throw new AnimalNotFoundException(e.toString());
        }
        System.out.println("\nNow animals in the shop:");
        for (Animal i : lsAnimal) {
            System.out.println(lsAnimal.indexOf(i) + "  " + i.toString());
        }
        System.out.println("\nYour info:");
        System.out.println(customer.toString());
    }

    public void setClosed(boolean closed) {
        this.isClosed = closed;
        if (closed) {
            LocalDate now = LocalDate.now();
            double income = 0.;
            System.out.println("\nToday:");
            for (Customer i : lsCustomer) {
                if (i.getLast().equals(now)) {
                    System.out.println(i.toString());
                    income += i.getPay();
                }
            }
            System.out.println("Income today: " + income);
        } else {
            lsCustomer.clear();
        }
    }

    public double getCash() {
        return cash;
    }

    public void setCash(double cash) {
        this.cash = cash;
    }

    public boolean isClosed() {
        return isClosed;
    }
}