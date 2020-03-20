/* MainProgram.java
 * Author: Gordon Duan
 * Date: Dec 9, 2018
 * Description: Move the animals around on the board
 */
//List of Added Features
//1. Spawning of sheep will be close to the parents 
//2. Sheep have gender and only opposite genders can mate
//3. If wolves are "hungry" they will go after the nearest sheep
//4. If wolves are not "hungry" they will move randomly
//4. If sheeps fulfill requirements for reproduction they will find the nearest mate
//5. If sheeps do not fulfill requirements for reproduction they will go after nearest grass
//6. Every 50 turns there is a new season and different seasons change the interactions
//7. During winter all livings things lose 2 health per turn
//8. During winter the plant rate of reproduction is decreased
//9. Game Over Screen

import java.util.Scanner;
class GridTest{
  public static void main(String[]args){
    Scanner input = new Scanner(System.in);
    int gridWidth,gridHeight,numberOfSheep,numberOfWolf,plantNutrition,plantInit,plantRate,plantHealth,sheepHealth,wolfHealth,answer;
    int spaceFilled=0;
    System.out.println("Would you like to run the recommended settings or custom?");
    System.out.println("Enter '1' for recommended or '2' for custom");
    answer = input.nextInt();
    //Set up grid with ideal numbers
    if(answer==1){
      gridWidth = 30;
      gridHeight = 30;
      numberOfSheep = 600;
      sheepHealth = 24;
      numberOfWolf = 45;
      wolfHealth = 20;
      plantInit = 200;
      plantRate = 20;
      plantHealth = 35;
      plantNutrition = 20;
    }else{
    //Ask the user for input in order to set up the grid 
      System.out.println("What is the width of the grid?");
      gridWidth = input.nextInt();
      System.out.println("What is the height of the grid?");
      gridHeight = input.nextInt();
      int spaceTotal = gridHeight*gridWidth;
      System.out.println("How many sheep are there:");
      numberOfSheep = input.nextInt();
      if(spaceFilled+numberOfSheep > spaceTotal){
        System.out.println("That is too many Sheep");
        System.out.println("I will enter as many as the grid can fit");
        numberOfSheep = spaceTotal-spaceFilled;
      }
      spaceFilled += numberOfSheep;
      System.out.println("Number of Sheep: "+numberOfSheep);
      System.out.println("Spaces Left: "+(spaceTotal-spaceFilled));
      System.out.println("How much health does each sheep have?");
      sheepHealth = input.nextInt();
      System.out.println("How many wolves are there:");
      numberOfWolf = input.nextInt();
      if(spaceFilled+numberOfWolf > spaceTotal){
        System.out.println("That is too many Wolves");
        System.out.println("I will enter as many as the grid can fit");
        numberOfWolf = spaceTotal-spaceFilled;
      }
      spaceFilled += numberOfWolf;
      System.out.println("Number of Wolf: "+numberOfWolf);
      System.out.println("Spaces Left: "+(spaceTotal-spaceFilled));
      System.out.println("How much health do wolves have?");
      wolfHealth = input.nextInt();
      System.out.println("How many initial plants are there");
      plantInit = input.nextInt();
      if(spaceFilled+plantInit > spaceTotal){
        System.out.println("That is too many Plants");
        System.out.println("I will enter as many as the grid can fit");
        plantInit = spaceTotal-spaceFilled;
      }
      spaceFilled += plantInit;
      System.out.println("Number of Plants: "+plantInit);
      System.out.println("Spaces Left: "+(spaceTotal-spaceFilled));
      System.out.println("What is the plant spawn rate(Percentage):");
      plantRate = input.nextInt();
      System.out.println("What is the nutritional value of the plants");
      plantNutrition = input.nextInt();
      System.out.println("How much health do plants have?");
      plantHealth = input.nextInt();
    }
    input.close();
    //Create a grid of living things
    LivingThing[][] map = new LivingThing[gridHeight][gridWidth];
    //Create the same size grid to check if spaces have been moved
    boolean[][] moved = new boolean[gridHeight][gridWidth];
    // Initialize Map with initial user values
    gridSetup(map,numberOfSheep,numberOfWolf,plantNutrition,plantInit,plantHealth,sheepHealth,wolfHealth);
    //Set up Grid Panel
    DisplayGrid grid = new DisplayGrid(map);
    //Display the grid on a Panel
    grid.refresh();
    boolean inPlay = true;
    int turns = 1;
    while(inPlay){
      //Small delay
      try{ Thread.sleep(100);}catch(Exception e){};
      // Initialize Map (Making changes to map)
      //Reset the moved 2D Array
      moved = new boolean[gridHeight][gridWidth];
      turns++;
      //Method to make changes to the map
      moveItemsOnGrid(map,moved,plantNutrition,plantRate,plantHealth,sheepHealth,wolfHealth,turns);
      //Method to check if any of the species are extinct
      boolean extinct = checkExtinct(map);
      if(extinct){
        inPlay = false;
      }
      //Display the grid on a Panel
      grid.refresh();
    }  
  } 
  //Make methods-------------------------------------------------------------------------------------------------------
  public static void gridSetup(LivingThing[][]map,int numberOfSheep,int numberOfWolf,int plantNutrition,int plantInit,int plantHealth,int sheepHealth,int wolfHealth){
    int randomX,randomY;
    int placed = 0;
    //Continue finding a random coordinate to place the initial Sheep
    while(placed < numberOfSheep){
      randomX =(int)(Math.random()*(map[0].length));
      randomY =(int)(Math.random()*(map.length));
      if(map[randomY][randomX] == null){
        map[randomY][randomX]= new Sheep(sheepHealth,randomX,randomY);
        placed++;
      }
    }
    placed = 0;
    //Continue finding a random coordinate to place the initial Wolf
    while(placed < numberOfWolf){
      randomX =(int)(Math.random()*(map[0].length));
      randomY =(int)(Math.random()*(map.length));
      if(map[randomY][randomX]==null){
        map[randomY][randomX]= new Wolf(wolfHealth,randomX,randomY);
        placed++;
      }
    }
    placed = 0;
    //Continue finding a random coordinate to place the initial Plant
    while(placed < plantInit){
      randomX =(int)(Math.random()*(map[0].length));
      randomY =(int)(Math.random()*(map.length));      
      if(map[randomY][randomX]==null){
        map[randomY][randomX]= new Plant(plantHealth,plantNutrition,randomX,randomY);
        placed++;
      }
    }
  }
  // Method to simulate grid movement
  public static void moveItemsOnGrid(LivingThing[][]map,boolean[][]moved,int plantNutrition,int plantRate,int plantHealth,int sheepHealth,int wolfHealth,int turns){
    int move = 0;
    int season;
    //Find out what season it is(Spring,Summer,Fall,Winter)
    if(turns%200<50){
      season = 1;
    }else if((turns%200>=50)&&(turns%200<100)){
      season = 2;
    }else if((turns%200>=100)&&(turns%200<150)){
      season = 3;
    }else{
      season = 4;
    }
    //Loop through each spot on the array
    for(int i = 0; i<map.length;i++){
      for(int j = 0; j<map[0].length;j++){
        if((map[i][j]!=null)&&(moved[i][j]==false)){
          if(season == 4){
            map[i][j].setHealth(map[i][j].getHealth()-1);
          }
          //Age all the items on the board(Change age and health)
          map[i][j].grow();
          if(((map[i][j].getAge()>=50)&&(map[i][j]instanceof Sheep))||(map[i][j].getHealth()<=0)){
            map[i][j]=null;
          }
          //Check For Sheep---------------------------------------------------------
          if(map[i][j] instanceof Sheep){
            int closestX = 0;
            int closestY = 0;
            double smallD = Math.sqrt(Math.pow(map.length,2)+Math.pow(map[0].length,2));
            //Find the closest plant to go towards
            if((map[i][j].getHealth()<20)||(map[i][j].getAge()<13)){
              for(int y=0;y<map.length;y++){
                for(int x=0;x<map[0].length;x++){
                  if((map[y][x]instanceof Plant)&&(((Sheep)map[i][j]).getDistance(x,y)<smallD)){
                    smallD =((Sheep)map[i][j]).getDistance(x,y);
                    closestY = y;
                    closestX = x;
                  }
                }
              }
              //Find the closest sheep to go reproduce with
            }else if((map[i][j].getHealth()>=20)&&(map[i][j].getAge()>=13)){
              for(int y=0;y<map.length;y++){
                for(int x=0;x<map[0].length;x++){
                  if((map[y][x]instanceof Sheep)&&(map[y][x].getAge()>=13)&&
                     (((Sheep)map[i][j]).getDistance(x,y)<smallD)&&
                     (map[y][x]!=map[i][j])&&(!((Sheep)map[y][x]).getGender().equals(((Sheep)map[i][j]).getGender()))){
                    smallD =((Sheep)map[i][j]).getDistance(x,y);
                    closestY = y;
                    closestX = x;
                  }
                }
              }
              //If there no sheep to reproduce with just go look for a plant
              if((closestY==0)&&(closestX==0)&&(smallD==Math.sqrt(Math.pow(map.length,2)+Math.pow(map[0].length,2)))){
                for(int y=0;y<map.length;y++){
                  for(int x=0;x<map[0].length;x++){
                    if((map[y][x]instanceof Plant)&&(((Sheep)map[i][j]).getDistance(x,y)<smallD)){
                      smallD =((Sheep)map[i][j]).getDistance(x,y);
                      closestY = y;
                      closestX = x;
                    }
                  }
                }
              }
            }
            //Decide which direction to move in
            if(closestY>i){
              move = 4;
            }else if(closestY<i){
              move = 3;
            }else{
              if(closestX>j){
                move = 1;
              }else if(closestX<j){
                move = 2;
              }
            }
            //Find out what happens if the sheep wants to move right(Eat grass,Reproduce)
            if((move==1)&&(j<map[0].length-1)){
              if(map[i][j+1]==null){
                ((Sheep)map[i][j]).moveRight();
                map[map[i][j].getY()][map[i][j].getX()] = map[i][j];
                map[i][j] = null;
                moved[i][j+1]=true;
              }else if(map[i][j+1] instanceof Sheep){
                if((map[i][j].getHealth()>20)&&(map[i][j+1].getHealth()>20)&&
                   (map[i][j].getAge()>13)&&(map[i][j+1].getAge()>13)&&
                   (!((Sheep)map[i][j]).getGender().equals(((Sheep)map[i][j+1]).getGender()))){
                  if(findSpace(map)){
                    map[i][j].setHealth((map[i][j].getHealth()/100)*70);
                    map[i][j+1].setHealth((map[i][j+1].getHealth()/100)*70);                    
                    int smallestY = 0;
                    int smallestX = 0;
                    double smallestDistance = Math.sqrt(Math.pow(map.length,2)+Math.pow(map[0].length,2));
                    for(int f=0;f<map.length;f++){
                      for(int k=0;k<map[0].length;k++){
                        if((map[f][k]==null)&&(((Sheep)map[i][j]).getDistance(k,f)<=smallestDistance)){
                          smallestDistance =((Sheep)map[i][j]).getDistance(k,f);
                          smallestY = f;
                          smallestX = k;
                        }
                      }
                    }
                    map[smallestY][smallestX]=((Sheep)map[i][j]).makeBaby(sheepHealth,smallestX,smallestY);
                  }
                }
              }else if(map[i][j+1] instanceof Plant){
                map[i][j].setHealth(map[i][j].getHealth()+((Plant)map[i][j+1]).getNutritionalValue());
                ((Sheep)map[i][j]).moveRight();
                map[map[i][j].getY()][map[i][j].getX()] = map[i][j];
                map[i][j] = null;
                moved[i][j+1]=true;
              }
              //Find out what happens if the sheep wants to move left(Eat grass,Reproduce)
            }else if((move == 2)&&(j>0)){
              if(map[i][j-1]==null){
                ((Sheep)map[i][j]).moveLeft();
                map[map[i][j].getY()][map[i][j].getX()] = map[i][j];
                map[i][j] = null;
                moved[i][j-1]=true;
              }else if(map[i][j-1] instanceof Sheep){
                if((map[i][j].getHealth()>20)&&(map[i][j-1].getHealth()>20)&&
                   (map[i][j].getAge()>13)&&(map[i][j-1].getAge()>13)&&
                   (!((Sheep)map[i][j]).getGender().equals(((Sheep)map[i][j-1]).getGender()))){
                  if(findSpace(map)){
                    map[i][j].setHealth((map[i][j].getHealth()/100)*70);
                    map[i][j-1].setHealth((map[i][j-1].getHealth()/100)*70);                    
                    int smallestY = 0;
                    int smallestX = 0;
                    double smallestDistance = Math.sqrt(Math.pow(map.length,2)+Math.pow(map[0].length,2));
                    for(int f=0;f<map.length;f++){
                      for(int k=0;k<map[0].length;k++){
                        if((map[f][k]==null)&&(((Sheep)map[i][j]).getDistance(k,f)<smallestDistance)){
                          smallestDistance = ((Sheep)map[i][j]).getDistance(k,f);
                          smallestY = f;
                          smallestX = k;
                        }
                      }
                    }
                    map[smallestY][smallestX]=((Sheep)map[i][j]).makeBaby(sheepHealth,smallestX,smallestY);
                  }
                }
              }else if(map[i][j-1] instanceof Plant){
                map[i][j].setHealth(map[i][j].getHealth()+((Plant)map[i][j-1]).getNutritionalValue());
                ((Sheep)map[i][j]).moveLeft();
                map[map[i][j].getY()][map[i][j].getX()] = map[i][j];
                map[i][j] = null;
                moved[i][j-1]=true;
              }           
              //Find out what happens if the sheep wants to move up(Eat grass,Reproduce)
            }else if((move == 3)&&(i>0)){
              if(map[i-1][j]==null){
                ((Sheep)map[i][j]).moveUp();
                map[map[i][j].getY()][map[i][j].getX()] = map[i][j];
                map[i][j] = null;
                moved[i-1][j] = true;
              }else if(map[i-1][j] instanceof Sheep){
                if((map[i][j].getHealth()>20)&&(map[i-1][j].getHealth()>20)&&
                   (map[i][j].getAge()>13)&&(map[i-1][j].getAge()>13)&&
                   (!((Sheep)map[i][j]).getGender().equals(((Sheep)map[i-1][j]).getGender()))){
                  if(findSpace(map)){
                    map[i][j].setHealth((map[i][j].getHealth()/100)*70);
                    map[i-1][j].setHealth((map[i-1][j].getHealth()/100)*70);                    
                    int smallestY = 0;
                    int smallestX = 0;
                    double smallestDistance = Math.sqrt(Math.pow(map.length,2)+Math.pow(map[0].length,2));
                    for(int f=0;f<map.length;f++){
                      for(int k=0;k<map[0].length;k++){
                        if((map[f][k]==null)&&(((Sheep)map[i][j]).getDistance(k,f)<smallestDistance)){
                          smallestDistance = ((Sheep)map[i][j]).getDistance(k,f);
                          smallestY = f;
                          smallestX = k;
                        }
                      }
                    }
                    map[smallestY][smallestX]=((Sheep)map[i][j]).makeBaby(sheepHealth,smallestX,smallestY);
                  }
                }
              }else if(map[i-1][j] instanceof Plant){
                map[i][j].setHealth(map[i][j].getHealth()+((Plant)map[i-1][j]).getNutritionalValue());
                ((Sheep)map[i][j]).moveUp();
                map[map[i][j].getY()][map[i][j].getX()] = map[i][j];
                map[i][j] = null;
                moved[i-1][j]=true;
              }
              //Find out what happens if the sheep wants to move down(Eat grass,Reproduce)
            }else if((move == 4)&&(i<map.length-1)){
              if(map[i+1][j]==null){
                ((Sheep)map[i][j]).moveDown();
                map[map[i][j].getY()][map[i][j].getX()] = map[i][j];
                map[i][j] = null;
                moved[i+1][j] = true;
              }else if(map[i+1][j] instanceof Sheep){
                if((map[i][j].getHealth()>20)&&(map[i+1][j].getHealth()>20)&&
                   (map[i][j].getAge()>13)&&(map[i+1][j].getAge()>13)&&
                   (!((Sheep)map[i][j]).getGender().equals(((Sheep)map[i+1][j]).getGender()))){
                  if(findSpace(map)){
                    map[i][j].setHealth((map[i][j].getHealth()/100)*70);
                    map[i+1][j].setHealth((map[i+1][j].getHealth()/100)*70);
                    int smallestY = 0;
                    int smallestX = 0;
                    double smallestDistance = Math.sqrt(Math.pow(map.length,2)+Math.pow(map[0].length,2));
                    for(int f=0;f<map.length;f++){
                      for(int k=0;k<map[0].length;k++){
                        if((map[f][k]==null)&&(((Sheep)map[i][j]).getDistance(k,f)<=smallestDistance)){
                          smallestDistance = ((Sheep)map[i][j]).getDistance(k,f);
                          smallestY = f;
                          smallestX = k;
                        }
                      }
                    }
                    map[smallestY][smallestX]=((Sheep)map[i][j]).makeBaby(sheepHealth,smallestX,smallestY);
                  }
                }
              }else if(map[i+1][j] instanceof Plant){
                map[i][j].setHealth(map[i][j].getHealth()+((Plant)map[i+1][j]).getNutritionalValue());
                ((Sheep)map[i][j]).moveDown();
                map[map[i][j].getY()][map[i][j].getX()] = map[i][j];
                map[i][j] = null;
                moved[i+1][j]=true;
              }
            }
            //Check For Wolf------------------------------------------------------------------
          }else if(map[i][j] instanceof Wolf){
            //Use casting to determine if wolf is hungry or not
            if(((Wolf)map[i][j]).getHealth()>=40){
              ((Wolf)map[i][j]).setHunger(false);
            }else if(((Wolf)map[i][j]).getHealth()<40){
              ((Wolf)map[i][j]).setHunger(true);
            }
            //If hungry, find the nearest sheep to go eat
            if(((Wolf)map[i][j]).getHunger()==true){
              int closestX = 0;
              int closestY = 0;
              double smallD = Math.sqrt(Math.pow(map.length,2)+Math.pow(map[0].length,2));
              for(int y=0;y<map.length;y++){
                for(int x=0;x<map[0].length;x++){
                  if((map[y][x]instanceof Sheep)&&(((Wolf)map[i][j]).getDistance(x,y)<smallD)){
                    smallD =((Wolf)map[i][j]).getDistance(x,y);
                    closestY = y;
                    closestX = x;
                  }
                }
              }
              if(closestY>i){
                move = 4;
              }else if(closestY<i){
                move = 3;
              }else{
                if(closestX>j){
                  move = 1;
                }else if(closestX<j){
                  move = 2;
                }
              }
              //If not hungry then move randomly
            }else{
              move =(int)((Math.random()*4)+1);
            }
            //Determine what happens if wolf wants to move right(Eat sheep,trample grass,fight wolf)
            if((move==1)&&(j<map[0].length-1)){
              if((map[i][j+1]==null)||(map[i][j+1]instanceof Plant)){
                ((Wolf)map[i][j]).moveRight();
                map[map[i][j].getY()][map[i][j].getX()] = map[i][j];
                map[i][j] = null;
                moved[i][j+1]=true;
              }else if(map[i][j+1]instanceof Sheep){
                map[i][j].setHealth(map[i][j].getHealth()+((map[i][j+1]).getHealth()/10)*6);
                ((Wolf)map[i][j]).moveRight();
                map[map[i][j].getY()][map[i][j].getX()] = map[i][j];
                map[i][j] = null;
                moved[i][j+1]=true;
              }else if(map[i][j+1] instanceof Wolf){
                if(((Wolf)map[i][j]).compareTo(((Wolf)map[i][j+1]))==1){
                  map[i][j+1].setHealth((map[i][j+1].getHealth()/100)*65);
                }else if(((Wolf)map[i][j]).compareTo(((Wolf)map[i][j+1]))==0){
                  map[i][j].setHealth((map[i][j].getHealth()/100)*65);
                  map[i][j+1].setHealth((map[i][j+1].getHealth()/100)*65);
                }else{
                  map[i][j].setHealth((map[i][j].getHealth()/100)*65);
                }
              }
              //Determine what happens if wolf wants to move left(Eat sheep,trample grass,fight wolf)
            }else if((move == 2)&&(j>0)){
              if((map[i][j-1]==null)||(map[i][j-1]instanceof Plant)){
                ((Wolf)map[i][j]).moveLeft();
                map[map[i][j].getY()][map[i][j].getX()] = map[i][j];
                map[i][j] = null;
                moved[i][j-1]=true;
              }else if(map[i][j-1]instanceof Sheep){
                map[i][j].setHealth(map[i][j].getHealth()+((map[i][j-1]).getHealth()/10)*6);
                ((Wolf)map[i][j]).moveLeft();
                map[map[i][j].getY()][map[i][j].getX()] = map[i][j];
                map[i][j] = null;
                moved[i][j-1]=true;
              }else if(map[i][j-1] instanceof Wolf){
                if(((Wolf)map[i][j]).compareTo(((Wolf)map[i][j-1]))==1){
                  map[i][j-1].setHealth((map[i][j-1].getHealth()/100)*65);
                }else if(((Wolf)map[i][j]).compareTo(((Wolf)map[i][j-1]))==0){
                  map[i][j].setHealth((map[i][j].getHealth()/100)*65);
                  map[i][j-1].setHealth((map[i][j-1].getHealth()/100)*65);
                }else{
                  map[i][j].setHealth((map[i][j].getHealth()/100)*65);
                }
              }
              //Determine what happens if wolf wants to move up(Eat sheep,trample grass,fight wolf)
            }else if((move == 3)&&(i>0)){
              if((map[i-1][j]==null)||(map[i-1][j]instanceof Plant)){
                ((Wolf)map[i][j]).moveUp();
                map[map[i][j].getY()][map[i][j].getX()] = map[i][j];
                map[i][j] = null;
                moved[i-1][j]=true;
              }else if(map[i-1][j]instanceof Sheep){
                map[i][j].setHealth(map[i][j].getHealth()+((map[i-1][j]).getHealth()/10)*6);
                ((Wolf)map[i][j]).moveUp();
                map[map[i][j].getY()][map[i][j].getX()] = map[i][j];
                map[i][j] = null;
                moved[i-1][j]=true;
              }else if(map[i-1][j] instanceof Wolf){
                if(((Wolf)map[i][j]).compareTo(((Wolf)map[i-1][j]))==1){
                  map[i-1][j].setHealth((map[i-1][j].getHealth()/100)*65);
                }else if(((Wolf)map[i][j]).compareTo(((Wolf)map[i-1][j]))==0){
                  map[i][j].setHealth((map[i][j].getHealth()/100)*65);
                  map[i-1][j].setHealth((map[i-1][j].getHealth()/100)*65);
                }else{
                  map[i][j].setHealth((map[i][j].getHealth()/100)*65);
                }
              }
              //Determine what happens if wolf wants to move down(Eat sheep,trample grass,fight wolf)
            }else if((move == 4)&&(i<map.length-1)){
              if((map[i+1][j]==null)||(map[i+1][j]instanceof Plant)){
                ((Wolf)map[i][j]).moveDown();
                map[map[i][j].getY()][map[i][j].getX()] = map[i][j];
                map[i][j] = null;
                moved[i+1][j]=true;
              }else if(map[i+1][j]instanceof Sheep){
                map[i][j].setHealth(map[i][j].getHealth()+((map[i+1][j]).getHealth()/10)*6);
                ((Wolf)map[i][j]).moveDown();
                map[map[i][j].getY()][map[i][j].getX()] = map[i][j];
                map[i][j] = null;
                moved[i+1][j]=true;
              }else if(map[i+1][j] instanceof Wolf){
                if(((Wolf)map[i][j]).compareTo(((Wolf)map[i+1][j]))==1){
                  map[i+1][j].setHealth((map[i+1][j].getHealth()/100)*65);
                }else if(((Wolf)map[i][j]).compareTo(((Wolf)map[i+1][j]))==0){
                  map[i][j].setHealth((map[i][j].getHealth()/100)*65);
                  map[i+1][j].setHealth((map[i+1][j].getHealth()/100)*65);
                }else{
                  map[i][j].setHealth((map[i][j].getHealth()/100)*65);
                }
              }
            }
          }//End of Sheep Wolf Moving
          moved[i][j]=true;
          //Randomly spawn plants on empty spaces based on user inputted plant rate
        }else if(map[i][j]==null){
          int randomSpawn;
          if(season == 4){
            randomSpawn =(int)(Math.random()*(150-1)+1);
          }else{
            randomSpawn =(int)(Math.random()*(100-1)+1);
          }
          if(randomSpawn<=plantRate){
            map[i][j] = new Plant(plantHealth,plantNutrition,j,i);
          }
        }//End of Spawn Plant or Move Animal
      }//End of For Loop J
    }//End of For Loop I
  }//End of Method
  //method to check for empty spaces
  public static boolean findSpace(LivingThing[][]map){
    int empty = 0;
    for(int i=0;i<map.length;i++){
      for(int j =0;j<map[0].length;j++){
        if(map[i][j] == null){
          empty++;
        }
      }
    }
    if(empty == 0){
      return false;
    }else{
      return true;
    }
  }
  //Method to check if any of the species have gone extince
  public static boolean checkExtinct(LivingThing[][]map){
    int sheepMale,sheepFemale,plant,wolf;
    sheepMale = 0;
    sheepFemale = 0;
    plant = 0;
    wolf = 0;
    for(int i = 0; i<map.length;i++){
      for(int j = 0; j<map[0].length;j++){
        if((map[i][j]instanceof Sheep)&&((Sheep)map[i][j]).getGender().equals("male")){
          sheepMale++;
        }else if((map[i][j]instanceof Sheep)&&((Sheep)map[i][j]).getGender().equals("female")){
          sheepFemale++;
        }else if(map[i][j]instanceof Plant){
          plant++;
        }else if(map[i][j]instanceof Wolf){
          wolf++;
        }
      }
    }
    if((sheepMale==0)||(sheepFemale==0)||(plant==0)||(wolf==0)){
      return true;
    }else{
      return false;
    }
  }
}