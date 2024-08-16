package Assignment7Java;

public class Bike implements Vehicle{
    private String color;
    private String name;
    private String type;
    private String bodyStyle;

    private float height;
    private float width;
    private boolean start=false;
    private int speed=0;

    Bike(String color,String name,String type,String bodyStyle,float height,float width)
    {
        this.color=color;
        this.name=name;
        this.type=type;
        this.bodyStyle=bodyStyle;
        this.height=height;
        this.width=width;
    }

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
