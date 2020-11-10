import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.sql.XAConnection;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class World{
    public int width;
    public int height;
    public int[] robot_position;
    public int robotDir;
    public int[][] map;
    private JLabel[][] labelArray;
    private boolean simulationEnd=false;




    //number 1 would be the object we want to reach
    //number 2 would be robot's current location
    //number 3 would be the charging station


    public World(int w, int h){
        height=h;
        width=w;

        map=new int[w][h];
        labelArray=new JLabel[w][h];
        robotDir=-1;
    }

    public void addObject(int w, int h){
        map[w][h]=1;
    }

    public void addRobot(Robot pRob){
        map[pRob.XCord][pRob.YCord]=2;
        pRob.world=this;
        robot_position=new int[]{pRob.XCord, pRob.YCord};
        robotDir=pRob.direction;

        //we know the charging station would be next to the robot
        //for the convenient, I would just put it at the right of the robot(if possible)
        if(pRob.XCord!=width-1){
            map[pRob.XCord+1][pRob.YCord]=3;
            pRob.ChargingStation[0]= pRob.XCord+1;
            pRob.ChargingStation[1]= pRob.YCord;

        }else{
            map[pRob.XCord-1][pRob.YCord]=3;
            pRob.ChargingStation[0]= pRob.XCord-1;
            pRob.ChargingStation[1]= pRob.YCord;
        }
    }

    public void create_world(){
        JFrame mainFrame=new JFrame("World");
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        mainFrame.setSize(1000,1000);
        mainFrame.setLayout(new GridLayout(1,1));


        JPanel grid=new JPanel();
        grid.setLayout(new GridLayout(width, height));
        mainFrame.add(grid);

        for(int i=0;i<width;i++){
            for(int j=0; j<height; j++){
                JLabel current = new JLabel("", SwingConstants.CENTER);

                if(map[i][j]==1){
                    current.setBackground(Color.RED);

                    current.setBorder(BorderFactory.createLineBorder(Color.black, 1));
                    current.setOpaque(true);
                    current.setText("Object");
                }else

                if(map[i][j]==2){


                    current.setBorder(BorderFactory.createLineBorder(Color.black, 1));
                    current.setOpaque(true);
                    if(robotDir==0){
                        current.setIcon(new ImageIcon("src/robW.jpg"));
                    }else if(robotDir==1){
                        current.setIcon(new ImageIcon("src/robE.jpg"));
                    }else if(robotDir==2){
                        current.setIcon(new ImageIcon("src/robS.png"));
                    }else if(robotDir==3){
                        current.setIcon(new ImageIcon("src/robN.jpg"));
                    }
                }

                else if(map[i][j]==3){
                    current.setBackground(Color.GREEN);

                    current.setBorder(BorderFactory.createLineBorder(Color.black, 1));
                    current.setOpaque(true);
                    current.setText("Charger");

                }else if(map[i][j]==4) {
                    current.setBackground(Color.BLUE);

                    current.setBorder(BorderFactory.createLineBorder(Color.black, 1));
                    current.setOpaque(true);
                    current.setText("GOAL!!!");

                }
                else
                {
                    current.setBackground((Color.white));

                    current.setBorder(BorderFactory.createLineBorder(Color.black, 1));
                    current.setOpaque(true);
                }
                labelArray[i][j]=current;
                grid.add(labelArray[i][j]);
            }

        }
        mainFrame.setVisible(true);
    }

    public void update_robot(Robot r){

        map[robot_position[0]][robot_position[1]]=0;

        if(map[r.XCord][r.YCord]==1){
            map[r.XCord][r.YCord]=4;
            simulationEnd=true;
            return;
        }

        map[r.XCord][r.YCord]=2;
        robot_position[0]=r.XCord;
        robot_position[1]=r.YCord;

        if(r.isLowBattery){
            simulationEnd=true;
            System.out.println("Out of battery!");
        }



    }







    public static void main(String[] args) throws InterruptedException {
        World w = new World(30,30);
        Robot rob = new Robot(15,15,0);
        META meta = new META(rob);
        w.addRobot(rob);
        w.addObject(4,21);

        w.create_world();

        while(!w.simulationEnd){
            Thread.sleep(1000);
            Tuple a = NN.think(rob);
            a=KNN.think(a);
            a=ES.think(a);
            META.think(a,meta);


            w.create_world();


        }


    }


}
