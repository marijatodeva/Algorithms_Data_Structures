package task12_CakeShop;
import java.util.Scanner;

public class CakeShop {

    public static void removeCakes(SLL cakes) {
        // TODO: implement method
        /*SLLNode dvizi=cakes.first;
        double sum=0;
        int br_torti=0;

        while(dvizi!=null){
            sum+=dvizi.price;
            br_torti++;
            dvizi=dvizi.succ;
        }

        double average=sum/br_torti;
        dvizi=cakes.first;
        while(dvizi!=null){
            if(cakes.first>average){
               dvizi=dvizi.succ;
            }
            dvizi=dvizi.succ;
        }*/
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        SLL cakes = new SLL();

        for (int i = 0; i < n; i++) {
            String line = scanner.nextLine();
            String[] parts = line.split("\\s+");
            cakes.insertLast(parts[0], Integer.parseInt(parts[1]));
        }

        removeCakes(cakes);
        System.out.println(cakes.toString());
    }
}


class SLLNode {
    String name;
    int price;
    SLLNode succ;

    public SLLNode(String name, int price, SLLNode succ) {
        this.name = name;
        this.price = price;
        this.succ = succ;
    }

    @Override
    public String toString() {
        return name;
    }
}

class SLL {
    SLLNode first;

    public SLL() {
        this.first = null;
    }

    public void insertFirst(String name, int price) {
        SLLNode ins = new SLLNode(name, price, first);
        first = ins;
    }

    public void insertLast(String name, int price) {
        if (first != null) {
            SLLNode tmp = first;
            while (tmp.succ != null)
                tmp = tmp.succ;
            SLLNode ins = new SLLNode(name, price, null);
            tmp.succ = ins;
        } else {
            insertFirst(name, price);
        }
    }

    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder();
        if (first != null) {
            SLLNode tmp = first;
            ret.append(tmp).append("\n");
            while (tmp.succ != null) {
                tmp = tmp.succ;
                ret.append(tmp).append("\n");
            }
        } else
            ret = new StringBuilder("NO ELEMENTS");
        return ret.toString();
    }
}

