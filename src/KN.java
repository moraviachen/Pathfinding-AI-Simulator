public class KN {
    private String type;
    private String predicate;
    private double argument;

    public KN(String pType, String pPredicate, double pArgument){
        type=pType;
        predicate=pPredicate;
        argument=pArgument;
    }

    public String getPredicate() {
        return predicate;
    }

    public String getType(){
        return type;

    }

    public double getArgument(){
        return argument;
    }

    public String toString(){
        return ""+type+argument+predicate;
    }

}
