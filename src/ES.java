import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ES
{
    private static ArrayList<KN> facts = new ArrayList<KN>();
    private static ArrayList<KN> fired = new ArrayList<KN>();
    private static ArrayList<KN> database = ES.loadFromFile();

    public static ArrayList<KN> loadFromFile() {
        ArrayList<KN> ret = new ArrayList<KN>();
        try {
            File db = new File("src/ES_database.txt");
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



    public static KN matchTriggers(String keyword) {
        for (KN t : database) {

            if (t.getType().equals(keyword)) {
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


        for(int i=0; i<n.elements.size(); i++) {
            String keyword = (String) n.getElements(i);



            facts.add(ES.matchTriggers(keyword));
            ret.add(ES.matchTriggers(keyword).getPredicate());

            fired.add(ES.matchTriggers(keyword));


            while (fired.size() != 0) {

                for (int j = 0; j < fired.size(); j++) {
                    if (ES.matchTriggers(fired.get(j).getPredicate()) != null) {
                        String temp = fired.get(j).getPredicate();
                        facts.add(ES.matchTriggers(temp));
                        ret.add(ES.matchTriggers(temp).getPredicate());
                        fired.remove(fired.get(j));
                        fired.add(ES.matchTriggers(temp));
                    } else {
                        fired.remove(fired.get(j));
                    }
                }

            }
        }



        return new Tuple(ret);

    }
}
