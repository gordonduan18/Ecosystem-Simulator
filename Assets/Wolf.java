//Class of wolves that implements comparable with each other
class Wolf extends LivingThing implements Interactions,Comparable<Wolf>{
  double distance;
  boolean hunger;
  Wolf(int health,int x,int y){
    super(health,x,y);
    this.hunger = true;
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
  public int compareTo(Wolf wolf){
    if(this.getHealth()>wolf.getHealth()){
      return 1;
    }else if(this.getHealth()==wolf.getHealth()){
      return 0;
    }else{
      return -1;
    }
  }
  public double getDistance(int x,int y){
    distance = Math.sqrt(Math.pow(this.getX()-x,2)+Math.pow(this.getY()-y,2));
    return distance;
  }
  public boolean getHunger(){
    return this.hunger;
  }
  public void setHunger(boolean hunger){
    this.hunger = hunger;
  }
}