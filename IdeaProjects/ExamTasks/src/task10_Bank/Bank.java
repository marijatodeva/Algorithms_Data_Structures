package task10_Bank;

import java.util.Scanner;

public class Bank
{
    public static void bank(SLL<Client> normal, SLL<Client> golden)
    {
        SLLNode<Client> dvizi=golden.getFirst();
        SLLNode<Client> tmp=null;
        int min_importance=Integer.MAX_VALUE;
        while(dvizi!=null){
            int importance=dvizi.element.getLoyalty()*10+dvizi.element.getAccounts()*20;
            if(importance<min_importance){
                min_importance=importance;
                tmp=dvizi;
            }
            dvizi=dvizi.succ;
        }
        golden.delete(tmp);
        normal.insertLast(tmp.element);

        dvizi=normal.getFirst();
        tmp=null;
        int max_importance=Integer.MIN_VALUE;
        while(dvizi!=null){
            int importance=dvizi.element.getLoyalty()*10+dvizi.element.getAccounts()*20;
            if(importance>max_importance){
                max_importance=importance;
                tmp=dvizi;
            }
            dvizi=dvizi.succ;
        }
        normal.delete(tmp);
        golden.insertLast(tmp.element);
    }
    public static void main(String[] args)
    {
        SLL<Client> Normal = new SLL<>();
        SLL<Client> Golden = new SLL<>();

        Scanner vnesi = new Scanner(System.in);
        int n = vnesi.nextInt();
        int m = vnesi.nextInt();

        for(int i=0;i<n;i++)
        {
            int id = vnesi.nextInt();
            int loyalty = vnesi.nextInt();
            int accounts = vnesi.nextInt();
            Client c1 = new Client(id,loyalty,accounts);
            Normal.insertLast(c1);
        }
        for(int j=0;j<m;j++)
        {
            int id = vnesi.nextInt();
            int loyalty = vnesi.nextInt();
            int accounts = vnesi.nextInt();
            Client c2 = new Client(id,loyalty,accounts);
            Golden.insertLast(c2);
        }
        bank(Normal,Golden);
        System.out.println(Normal.toString());
        System.out.println(Golden.toString());
    }

}

class Client
{
    private int id;
    private int loyalty;
    private int accounts;

    public Client(int id, int loyalty, int accounts)
    {
        this.id = id;
        this.loyalty = loyalty;
        this.accounts = accounts;
    }

    public int getId() {
        return id;
    }

    public int getLoyalty() {
        return loyalty;
    }

    public int getAccounts() {
        return accounts;
    }

    @Override
    public String toString() {
        return String.valueOf(id);
    }
}

class SLL<E> {
    private SLLNode<E> first;

    public SLL() {
        // Construct an empty SLL
        this.first = null;
    }

    public void deleteList() {
        first = null;
    }

    public int length() {
        int ret;
        if (first != null) {
            SLLNode<E> tmp = first;
            ret = 1;
            while (tmp.succ != null) {
                tmp = tmp.succ;
                ret++;
            }
            return ret;
        } else
            return 0;

    }

    @Override
    public String toString() {
        String ret = new String();
        if (first != null) {
            SLLNode<E> tmp = first;
            ret += tmp + " ";
            while (tmp.succ != null) {
                tmp = tmp.succ;
                ret += tmp + " ";
            }
        } else
            ret = "Prazna lista!!!";
        return ret;
    }

    public void insertFirst(E o) {
        SLLNode<E> ins = new SLLNode<E>(o, first);
        first = ins;
    }

    public void insertAfter(E o, SLLNode<E> node) {
        if (node != null) {
            SLLNode<E> ins = new SLLNode<E>(o, node.succ);
            node.succ = ins;
        } else {
            System.out.println("Dadenot jazol e null");
        }
    }

    public void insertBefore(E o, SLLNode<E> before) {

        if (first != null) {
            SLLNode<E> tmp = first;
            if(first==before){
                this.insertFirst(o);
                return;
            }
            //ako first!=before
            while (tmp.succ != before)
                tmp = tmp.succ;
            if (tmp.succ == before) {
                SLLNode<E> ins = new SLLNode<E>(o, before);
                tmp.succ = ins;
            } else {
                System.out.println("Elementot ne postoi vo listata");
            }
        } else {
            System.out.println("Listata e prazna");
        }
    }

    public void insertLast(E o) {
        if (first != null) {
            SLLNode<E> tmp = first;
            while (tmp.succ != null)
                tmp = tmp.succ;
            SLLNode<E> ins = new SLLNode<E>(o, null);
            tmp.succ = ins;
        } else {
            insertFirst(o);
        }
    }

    public E deleteFirst() {
        if (first != null) {
            SLLNode<E> tmp = first;
            first = first.succ;
            return tmp.element;
        } else {
            System.out.println("Listata e prazna");
            return null;
        }
    }

    public E delete(SLLNode<E> node) {
        if (first != null) {
            SLLNode<E> tmp = first;
            if(first ==node){
                return this.deleteFirst();
            }
            while (tmp.succ != node && tmp.succ.succ != null)
                tmp = tmp.succ;
            if (tmp.succ == node) {
                tmp.succ = tmp.succ.succ;
                return node.element;
            } else {
                System.out.println("Elementot ne postoi vo listata");
                return null;
            }
        } else {
            System.out.println("Listata e prazna");
            return null;
        }

    }

    public SLLNode<E> getFirst() {
        return first;
    }

    public SLLNode<E> find(E o) {
        if (first != null) {
            SLLNode<E> tmp = first;
            while (tmp.element != o && tmp.succ != null)
                tmp = tmp.succ;
            if (tmp.element == o) {
                return tmp;
            } else {
                System.out.println("Elementot ne postoi vo listata");
            }
        } else {
            System.out.println("Listata e prazna");
        }
        return first;
    }

}

class SLLNode<E> {
    protected E element;
    protected SLLNode<E> succ;

    public SLLNode(E elem, SLLNode<E> succ) {
        this.element = elem;
        this.succ = succ;
    }

    @Override
    public String toString() {
        return element.toString();
    }
}
