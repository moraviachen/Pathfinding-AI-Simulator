//not necessary but easy to process

import java.util.ArrayList;

public class Tuple {
    ArrayList<Object> elements;

    public Tuple(ArrayList<Object> pEle){
        elements=pEle;
    }

    public Object getElements(int i){
        return elements.get(i);
    }

    public String toString(){
        return elements.toString();
    }
}