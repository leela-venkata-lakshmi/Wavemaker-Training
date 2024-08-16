package Assignment7Java;

public class MainVehicle {
    public static void main(String[] args)
    {
        Car obj1=new Car("Green","Porsche 911","Sports Car","Rear-mounted","Targa",1.3f,1.8f);
        Bike obj2=new Bike("Blue","Trek Domane AL 2", "Road Bike","Road Bike", 0.9f,0.5f);
        ElectricBike eBike=new ElectricBike("Black","Rad Power Bikes RadRover 6 Plus","Electric Fat Bike","Rear Hub Motor","Fat Bike",1.2f,0.75f);
        ElectricCar eCar=new ElectricCar("Red","Tesla Model 3","Electric Sedan","Electric Motor","Sedan",1.44f, 1.93f);
        System.out.println("Name: "+obj1.getName());
        System.out.println("Color: " +eBike.getColor());
        System.out.println("Body Style: "+eCar.getBodyStyle());
        obj1.start();
        obj1.openDoor();
        obj1.closeDoor();
        obj1.increaseSpeed(20);
        obj1.decreaseSpeed(10);
        obj1.stop();
    }
}
