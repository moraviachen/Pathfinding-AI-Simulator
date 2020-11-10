import java.util.ArrayList;
public class NN {

    //create a nn network that takes the infrared sensor and the touching sensor as input
    //return an output as a tuple for the KN

    //the two sensors are only capable to see if there's anything in front
    //so to my understanding only one output is needed

    static double[] hidden_layer= new double[]{0.05,0.01};



    public static Tuple think(Robot pRob){
        ArrayList<Object> ret = new ArrayList<Object>();


        int[] input = {pRob.infSensor(), pRob.touchSensor()};

        double result = input[0]*hidden_layer[0]+input[1]*hidden_layer[1];

        //instead of matching the database, I would just use some if statements to determine
        //the tags for KN

        if(result<0){
            ret.add("empty");
        }else{
            ret.add("facing");
        }
        //return the result
        ret.add(result);

        return new Tuple(ret);
    }

    /*public static void main(String[] args){


    }*/
}
