// File: Exercise1_Override.java
class Animal {
    public void makeSound() {
        System.out.println("Animal makes sound");
    }
}

class Dog extends Animal {
    @Override
    public void makeSound() {
        System.out.println("Dog barks");
    }
}

public class Exercise1_Override {
    public static void main(String[] args) {
        Dog dog = new Dog();
        dog.makeSound();
    }
}
