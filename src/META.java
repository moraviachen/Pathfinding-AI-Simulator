import java.util.ArrayList;
public class META {
    public Robot rob;

    public META(Robot pRob){
        rob = pRob;
    }

    public static void think(Tuple n, META m){
        for(int i=0; i<n.elements.size();i++){
            String com =(String)n.getElements(i);

            String[] parts = com.split(",");

            for(int j=0;j<parts.length;j++){
                if(parts[j].equals("walks")){

                    m.rob.walk(m.rob.steps);

                }else if(parts[j].equals("turn")){

                    m.rob.turn();

                }else if(parts[j].equals("inc")){

                    m.rob.steps++;

                }else if(parts[j].equals("checkBattery")){

                    if(m.rob.battery<=10){
                        m.rob.isLowBattery=true;

                    }


                }else if(parts[j].equals("walk")){

                    m.rob.walk(Integer.parseInt(parts[j+1]));

                }
            }



        }
    }
}
