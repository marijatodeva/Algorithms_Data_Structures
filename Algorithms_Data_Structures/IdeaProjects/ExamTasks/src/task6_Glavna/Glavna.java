package task6_Glavna;
import java.util.Scanner;

public class Glavna
{
    public static void work(SLL<Task> toDo, SLL<Task> inProgress)
    {
        SLLNode<Task> dvizi=toDo.getFirst();
        SLLNode<Task> tmp=null;
        int max_importance=Integer.MIN_VALUE;
        while(dvizi!=null){
            int importance=2*dvizi.element.getHours()*dvizi.element.getPriority();
            if(importance>max_importance){
                max_importance=importance;
                tmp=dvizi;
            }
            dvizi=dvizi.succ;
        }
        toDo.delete(tmp);
        inProgress.insertFirst(tmp.element);

        dvizi=inProgress.getFirst();
        tmp=null;
        int min_importance=Integer.MAX_VALUE;
        while(dvizi!=null){
            int importance=2*dvizi.element.getHours()*dvizi.element.getPriority();
            if(importance<min_importance){
                min_importance=importance;
                tmp=dvizi;
            }
            dvizi=dvizi.succ;
        }
        inProgress.delete(tmp);
        toDo.insertLast(tmp.element);
    }

    public static void main(String[] args)
    {
        SLL<Task> toDoList = new SLL<>();
        SLL<Task> InProgress = new SLL<>();

        Scanner input = new Scanner(System.in);
        int n=input.nextInt();
        int m=input.nextInt();
        for(int i=0;i<n;i++)
        {
            int id = input.nextInt();
            int hours = input.nextInt();
            int priority = input.nextInt();

            Task t1 = new Task(id,hours,priority);
            toDoList.insertLast(t1);
        }
        for(int j=0;j<m;j++)
        {
            int id = input.nextInt();
            int hours = input.nextInt();
            int priority = input.nextInt();

            Task t2 = new Task(id,hours,priority);
            InProgress.insertLast(t2);
        }
        work(toDoList,InProgress);
        System.out.println(toDoList.toString());
        System.out.println(InProgress.toString());
    }
}

class Task
{
    private int id;
    private int hours;
    private int priority;

    public Task(int id, int hours, int priority)
    {
        this.id = id;
        this.hours = hours;
        this.priority = priority;
    }

    public int getId() {
        return id;
    }

    public int getHours() {
        return hours;
    }

    public int getPriority() {
        return priority;
    }

    @Override
    public String toString() {
        return String.valueOf(id);
    }
}

class SLLNode<E>
{
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