package services;

public class HelloWorldService {

    public static void main(String[] args) {

        new HelloWorldService().getGreeting();
    }

    public void getGreeting(){
        System.out.println("Hello, world!");
    }
}
