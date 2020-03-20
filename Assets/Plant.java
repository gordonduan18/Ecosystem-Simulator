class Plant extends LivingThing{
  private int nutritionalValue;
  Plant(int health,int nutritionalValue,int x,int y){
    super(health,x,y);
    this.nutritionalValue = nutritionalValue;
  }
  public int getNutritionalValue(){
    return this.nutritionalValue;
  }
  //Override the method of growing from living things to incluse the nutritional value decrrease
  public void grow(){
    this.setAge(this.getAge()+1);
    this.setHealth(this.getHealth()-1);
    if(this.nutritionalValue>5){
      this.nutritionalValue--;
    }
  }
}
