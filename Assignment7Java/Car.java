package Assignment7Java;

public class Car implements Vehicle{

    private String color;
    private String name;
    private String type;
    private String enginType;
    private String bodyStyle;
    private float height;
    private float width;
    private boolean start=true;
    private int speed=0;
    private boolean doorOpened=false;
    private float length=4.5f;
    private int price=600000;

       Car(String color,String name,String type,String engineType,String bodyStyle,float height,float width)
       {
          this.color=color;
          this.name=name;
          this.type=type;
          this.enginType=engineType;
          this.bodyStyle=bodyStyle;
          this.height=height;
          this.width=width;
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

        public float getLength() {
            return length;
        }

        public void setLength(float length) {
            this.length = length;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
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

        @Override
        public void start()
        {
            start=true;
            System.out.println("Car started");
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
                System.out.println("Speed get decreased by "+range+" points");
            }

        }

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
