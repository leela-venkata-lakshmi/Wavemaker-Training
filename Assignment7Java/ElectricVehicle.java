package Assignment7Java;

public abstract class ElectricVehicle implements Vehicle{
     String color;
     String name;
     String type;
     String enginType;
    String bodyStyle;
    float height;
    float width;
    boolean start=false;
    int speed=0;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEnginType() {
        return enginType;
    }

    public void setEnginType(String enginType) {
        this.enginType = enginType;
    }

    public String getBodyStyle() {
        return bodyStyle;
    }

    public void setBodyStyle(String bodyStyle) {
        this.bodyStyle = bodyStyle;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public boolean isStart() {
        return start;
    }

    public void setStart(boolean start) {
        this.start = start;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }



    @Override
    public void start()
    {
        start=true;
        System.out.println("Vehicle started");
    }

    @Override
    public void stop()
    {
        start=false;
        System.out.println("Car Stopped");
    }

    @Override
    public void increaseSpeed(int range)
    {
        speed+=range;
        if(speed>100) {
            System.out.println("Speed cannot be increased by " + range + " points");
        }else {
            System.out.println("Speed get increased by " + range + " points");
        }
    }

    @Override
    public void decreaseSpeed(int range)
    {
        speed-=range;
        if(speed<0)
        {
            System.out.println("Speed cannot be decreased by "+range+" points");
        }else{
            System.out.println("Speed get decreased by"+range+"points");
        }

    }
}
