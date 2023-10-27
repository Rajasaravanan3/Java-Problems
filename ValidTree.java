import java.util.*;

class ValidTree{
    public static void main(String[] args) {
        Scanner s=new Scanner(System.in);
        ValidTree obj = new ValidTree();
        String str= s.nextLine();
        str = str.replaceAll("[^0-9,]","");
        String []arr = str.split(",");
        int child[] =new int[arr.length/2];
        int parent[] = new int[arr.length/2];
        int index=0;
        for(int i=0;i<parent.length;i++){
            child[i]=Integer.parseInt(arr[index++]);
            parent[i]=Integer.parseInt(arr[index++]);
        }
        Map<Integer,List<Integer>> map = new TreeMap<>();
        map=obj.initializeMap(map, child, parent);
        System.out.println(obj.checkValidTree(map, child, parent));

        s.close();
    }
    public Map<Integer,List<Integer>> initializeMap(Map<Integer,List<Integer>> map,int child[], int parent[]){
        if(child.length>0){
            if(map.containsKey(parent[0])){
                map.get(parent[0]).add(child[0]);
            }
            else{
                List<Integer> list = new ArrayList<Integer>();
                list.add(child[0]);
                map.put(parent[0],list);
            }
            initializeMap(map, Arrays.copyOfRange(child, 1, child.length), Arrays.copyOfRange(parent, 1, parent.length));
        }
        return map;
    }
    public boolean checkValidTree(Map<Integer,List<Integer>> map, int child[] , int parent[]){
        int root = 0, flag=0;

        for(Map.Entry<Integer, List<Integer>> m : map.entrySet()){
            if(m.getValue().size()>2) return false;
            flag=0;
            for(int i=0 ;i<child.length;i++){
                if(m.getKey()==child[i]) {
                    flag=1;
                    break;
                }
            }
            if(flag==0) root++;
        }

        if(root>1) {
            System.out.println("Tree cannot have more than one root node");
            return false;
        }
        return true;
    }
}

//"(2,4)", "(3,4)", "(5,2)", "(6,3)" , "(8,4)"
//"(2,4)", "(3,4)", "(5,2)", "(6,3)"
//"(2,4)", "(3,4)", "(5,2)", "(6,3)" , "(8,4)" , "(9,7)"