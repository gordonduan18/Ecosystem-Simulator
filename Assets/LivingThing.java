//Class of living things that is inherited by Sheep and Wolves but is abstract so it cannot be created
abstract class LivingThing{
  private double health;
  private int age,x,y;
  //private Coordinate coordinate;
  LivingThing(double health,int x,int y){
    //this.coordinate = coordinate;
    this.health = health;
    this.x = x;
    this.y = y;
    this.age = 0;
  }
  public double getHealth(){
    return this.health;
  }
  public int getAge(){
    return this.age;
  }
  public int getX(){
    return this.x;
  }
  public int getY(){
    return this.y;
  }
  public void setX(int x){
    this.x = x;
  }
  public void setY(int y){
    this.y = y;
  }
  public void setAge(int age){
    this.age = age;
  }
  public void setHealth(double health){
    this.health = health;
  }
  public void grow(){
    this.age++;
    this.health-=1;
  }
}