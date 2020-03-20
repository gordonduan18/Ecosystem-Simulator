//Class of Sheep that can be created and extends the living things class and includes gender and age
class Sheep extends LivingThing implements Interactions{
  private double distance;
  private String gender;
  Sheep(double health,int x,int y){
    super(health,x,y);
    int random =(int)(Math.random()*2);
    if(random == 0){
      this.gender = "male";
    }else{
      this.gender = "female";
    }
  }
  public String getGender(){
    return this.gender;
  }
  public Sheep makeBaby(int health,int x,int y){
    Sheep babySheep = new Sheep(health,x,y);
    return babySheep;
  }
  public void moveRight(){
    this.setX(this.getX()+1);
  }
  public void moveLeft(){
    this.setX(this.getX()-1);
  }
  public void moveUp(){
    this.setY(this.getY()-1);
  }
  public void moveDown(){
    this.setY(this.getY()+1);
  }
  public double getDistance(int x,int y){
    distance = Math.sqrt(Math.pow(this.getX()-x,2)+Math.pow(this.getY()-y,2));
    return distance;
  }
}