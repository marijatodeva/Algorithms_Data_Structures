package task14_AlternatelyDeleteSLLElements;

import java.util.Scanner;

public class AlternatelyDeleteSLLElements {

    public static void brisiZapirki(String string) {
        SLL<Character> list1=new SLL<>();
        SLL<String> list2=new SLL<>();

        SLLNode<String> dvizi=list2.getFirst();
        String word= "";

        for (int i = 0; i < string.length(); i++) {
            char ch=string.charAt(i);
            if(ch >= 'a' || ch <= 'z' || ch == ','){
                list1.insertLast(ch);
            }

            if(ch>= 'a' && ch <= 'z'){
                word+=ch;
            } else if(ch== ','){
                if(!word.isEmpty()){
                    list2.insertLast(word);
                    word="";
                }
            }
        }

        if(!word.isEmpty()){
            list2.insertLast(word);
        }
        System.out.println(list1);
        System.out.println(list2);

        /*SLL<Character> list1 = new SLL<>();
        SLL<String> list2 = new SLL<>();
        String word = "";
        SLLNode<String> current = list2.getFirst();

        for (int i = 0; i < string.length(); i++) {
            char ch = string.charAt(i);
            if (ch >= 'a' && ch <= 'z' || ch == ',') {
                list1.insertLast(ch);
            }

            if (ch >= 'a' && ch <= 'z') {
                word += ch;
            } else if (ch == ',') {
                if (!word.isEmpty()) {
                    list2.insertLast(word);
                    word = "";
                }
            }
        }
        if (!word.isEmpty()) {
            list2.insertLast(word);
        }
        System.out.println(list1);
        System.out.println(list2);*/
    }


    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        String string = "";
        string = sc.nextLine();
        //System.out.println(string);
        brisiZapirki(string);
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

class SLL<E> {
    private SLLNode<E> first;

    public SLL() {
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
            ret += tmp;
            while (tmp.succ != null) {
                tmp = tmp.succ;
                ret += "->" + tmp;
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
            if (first == before) {
                this.insertFirst(o);
                return;
            }
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
            if (first == node) {
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
    public void mirror() {
        if (first != null) {
            SLLNode<E> tmp = first;
            SLLNode<E> newsucc = null;
            SLLNode<E> next;

            while(tmp != null) {
                next = tmp.succ;
                tmp.succ = newsucc;
                newsucc = tmp;
                tmp = next;
            }
            first = newsucc;
        }
    }
}