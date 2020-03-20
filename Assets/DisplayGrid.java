/* DisplayGrid.java
 * Author: Gordon Duan
 * Date: Dec 9, 2018
 * Description: Program displaying the 2D array of living things
 */
// Graphics Imports
import javax.swing.*;
import java.awt.*;

class DisplayGrid{ 

  private JFrame frame;
  private int maxX,maxY, GridToScreenRatio;
  private LivingThing[][] world;
  private int turns;
  DisplayGrid(LivingThing[][]w){ 
    this.world = w;
    this.turns = 0;
    maxX = Toolkit.getDefaultToolkit().getScreenSize().width-60;
    maxY = Toolkit.getDefaultToolkit().getScreenSize().height-60;
    GridToScreenRatio = maxY /(world.length+1);  //ratio to fit in screen as square map
    
    System.out.println("Map size: "+world.length+" by "+world[0].length + "\nScreen size: "+ maxX +"x"+maxY+ " Ratio: " + GridToScreenRatio);
    
    this.frame = new JFrame("Map of World");
    
    GridAreaPanel worldPanel = new GridAreaPanel();
    
    frame.getContentPane().add(BorderLayout.CENTER, worldPanel);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
    frame.setVisible(true);
  }
  
  
  public void refresh(){ 
    frame.repaint();
    turns++;
  }
  
  
  
  class GridAreaPanel extends JPanel{
    private static final long serialVersionUID = 1L;
    public void paintComponent(Graphics g){
      setDoubleBuffered(true);
      g.setColor(Color.BLACK);
      //Get the images from the folder
      Image babySheepBoy = Toolkit.getDefaultToolkit().getImage("babySheepBoy.png");
      Image babySheepGirl = Toolkit.getDefaultToolkit().getImage("babySheepGirl.png");
      Image sheepBoy = Toolkit.getDefaultToolkit().getImage("SheepBoy.png");
      Image sheepGirl = Toolkit.getDefaultToolkit().getImage("SheepGirl.png");
      Image wolf = Toolkit.getDefaultToolkit().getImage("Wolf.png");
      Image plant = Toolkit.getDefaultToolkit().getImage("Plant.png");
      Image backgroundSpring = Toolkit.getDefaultToolkit().getImage("backgroundSpring.png");
      Image backgroundSummer = Toolkit.getDefaultToolkit().getImage("backgroundSummer.png");
      Image backgroundFall = Toolkit.getDefaultToolkit().getImage("backgroundFall.png");
      Image backgroundWinter = Toolkit.getDefaultToolkit().getImage("backgroundWinter.png");
      g.setFont(new Font("Bodoni MT", Font.PLAIN, 40)); 
      
      int sheepMaleCount = 0;
      int sheepFemaleCount = 0;
      int wolfCount = 0;
      int plantCount = 0;
      for(int i = 0; i<world.length;i++){ 
        for(int j = 0; j<world[0].length;j++){
          //Draw different seasons depending on the turns
          if(turns%200<50){
            g.drawImage(backgroundSpring,j*GridToScreenRatio,i*GridToScreenRatio,GridToScreenRatio,GridToScreenRatio,this);
          }else if((turns%200>=50)&&(turns%200<100)){
            g.drawImage(backgroundSummer,j*GridToScreenRatio,i*GridToScreenRatio,GridToScreenRatio,GridToScreenRatio,this);
          }else if((turns%200>=100)&&(turns%200<150)){
            g.drawImage(backgroundFall,j*GridToScreenRatio,i*GridToScreenRatio,GridToScreenRatio,GridToScreenRatio,this);
          }else{
            g.drawImage(backgroundWinter,j*GridToScreenRatio,i*GridToScreenRatio,GridToScreenRatio,GridToScreenRatio,this);
          }
          
          //Draw the different images based off age gender and species
          if(world[i][j] instanceof Plant){
            plantCount++;
            g.drawImage(plant,j*GridToScreenRatio,i*GridToScreenRatio,GridToScreenRatio,GridToScreenRatio,this);
          }else if(world[i][j] instanceof Sheep){
            if(world[i][j].getAge()<=13){
              if(((Sheep)world[i][j]).getGender().equals("male")){
                sheepMaleCount++;
                g.drawImage(babySheepBoy,j*GridToScreenRatio,i*GridToScreenRatio,GridToScreenRatio,GridToScreenRatio,this);
              }else{
                sheepFemaleCount++;
                g.drawImage(babySheepGirl,j*GridToScreenRatio,i*GridToScreenRatio,GridToScreenRatio,GridToScreenRatio,this);
              }
            }else{
              if(((Sheep)world[i][j]).getGender().equals("male")){
                sheepMaleCount++;
                g.drawImage(sheepBoy,j*GridToScreenRatio,i*GridToScreenRatio,GridToScreenRatio,GridToScreenRatio,this);
              }else{
                sheepFemaleCount++;
                g.drawImage(sheepGirl,j*GridToScreenRatio,i*GridToScreenRatio,GridToScreenRatio,GridToScreenRatio,this);
              }
            }
          }else if (world[i][j] instanceof Wolf){
            wolfCount++;
            g.drawImage(wolf,j*GridToScreenRatio,i*GridToScreenRatio,GridToScreenRatio,GridToScreenRatio,this);
          }
          g.drawRect(j*GridToScreenRatio, i*GridToScreenRatio, GridToScreenRatio, GridToScreenRatio);
        }
      }
      //Draw the number of species on the map
      g.drawString("Number of Male Sheep: "+sheepMaleCount,1000,200);
      g.drawString("Number of Female Sheep: "+sheepFemaleCount,1000,300);
      g.drawString("Number of Wolves: "+wolfCount,1000,400);
      g.drawString("Number of Plants: "+plantCount,1000,500);
      g.drawString("Number of Turns: "+turns,1000,600);
      //Display a message when the ecosystem simulator ends
      if((sheepMaleCount==0)||(sheepFemaleCount==0)||(wolfCount==0)||(plantCount==0)){
        try{ Thread.sleep(1500);}catch(Exception e){};
        g.fillRect(0,0,maxX+60,maxY+60);
        g.setColor(Color.WHITE);
        g.setFont(new Font("ROG Fonts", Font.PLAIN, 100)); 
        g.drawString("Ecosystem Simulation",130,200);
        g.drawString("Over",700,350);
        g.drawString("Total Turns Passed: "+turns,100,500);
      }
    }
  }//end of GridAreaPanel
} //end of DisplayGrid
