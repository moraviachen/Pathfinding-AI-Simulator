import java.io.*;
import java.util.*;
public class KNN {
    private static ArrayList<KN> facts = new ArrayList<KN>();
    private static ArrayList<KN> fired = new ArrayList<KN>();
    private static ArrayList<KN> database = KNN.loadFromFile();
    public static double epsilon = 0.15;

    public static ArrayList<KN> loadFromFile() {
        ArrayList<KN> ret = new ArrayList<KN>();
        try {
            File db = new File("src/KNN_database.txt");
            FileReader fr = new FileReader(db);
            BufferedReader br = new BufferedReader(fr);
            String line;

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" ");
                ret.add(new KN(parts[0], parts[2], Double.parseDouble(parts[1])));

            }

            fr.close();


        } catch (IOException e) {
            e.printStackTrace();
        }
        return ret;
    }


    public static KN matchTriggers(String keyword, double a) {
        for (KN t : database) {

            if (t.getType().equals(keyword)&& a<=t.getArgument()+epsilon&& a>= t.getArgument()-epsilon) {
                return t;
            }
        }
        return null;
    }


    //for question 1: the input can be changed to String and it would work fine
    //without the casting
    public static Tuple think(Tuple n) {

        ArrayList<Object> ret = new ArrayList<Object>();
        //Tuple n would have 2 element: keyword and result


        String keyword= (String) n.getElements(0);
        double num = (double) n.getElements(1);

            facts.add(KNN.matchTriggers(keyword,num));
            ret.add(KNN.matchTriggers(keyword,num).getPredicate());

            fired.add(KNN.matchTriggers(keyword,num));


            while (fired.size() != 0) {

                for (int i = 0; i < fired.size(); i++) {
                    if (KNN.matchTriggers(fired.get(i).getPredicate(),num) != null) {
                        String temp = fired.get(i).getPredicate();
                        facts.add(KNN.matchTriggers(temp,num));
                        ret.add(KNN.matchTriggers(temp,num).getPredicate());
                        fired.remove(fired.get(i));
                        fired.add(KNN.matchTriggers(temp,num));
                    } else {
                        fired.remove(fired.get(i));
                    }
                }

            }



        return new Tuple(ret);

    }

    /* public static void main(String[] Args){
        World n = new World(30,30);
        Robot aa = new Robot(3,3,0);
        n.addRobot(aa);
        n.addObject(3,22);


       Tuple a = NN.think(aa);

        a=KNN.think(a);
        a=ES.think(a);
        META meta = new META(aa);
        META.think(a, meta);
        System.out.println("X Cord = "+ aa.XCord + " Y Cord = " + aa.YCord);



    }*/

}

