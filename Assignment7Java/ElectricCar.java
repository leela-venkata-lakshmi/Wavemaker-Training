package Assignment7Java;

public class ElectricCar extends ElectricVehicle{
    ElectricCar(String color,String name,String type,String engineType,String bodyStyle,float height,float width){
        super.color = color;
        super.name = name;
        super.type = type;
        super.enginType = engineType;
        super.bodyStyle = bodyStyle;
        super.height = height;
        super.width = width;
    }
    boolean doorOpened=false;
    void openDoor()
    {
        if(!doorOpened)
        {
            doorOpened=true;
            System.out.println("Door opened");
        }else{
            System.out.println("Door is already in open");
        }
    }

    void closeDoor()
    {
        if(doorOpened)
        {
            doorOpened=false;
            System.out.println("Door closed");
        }else{
            System.out.println("Door is already closed");
        }
    }
}
