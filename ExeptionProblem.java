
class Parent {
    Parent() {}
    static int child1=0, child2=0;
    public static void check1() throws Exception {
        if(++child1 > 1){
            throw new Exception("Invalid object creation");
        }
    }
    public static void check2() throws Exception {
        if(++child2 > 1){
            throw new Exception("Invalid object creation");
        }
    }
}

class ChildOne extends Parent {
    ChildOne() {
        try{
            super.check1();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}

class ChildTwo extends Parent {
    ChildTwo() {
        try{
            super.check2();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}

public class ExeptionProblem {
    public static void main(String[] args) {
        try{
            ChildOne c1 = new ChildOne();
            ChildTwo c2 = new ChildTwo();
            // ChildTwo c3 = new ChildTwo();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}