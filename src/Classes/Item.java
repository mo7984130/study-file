package Classes;

public class Item implements Comparable{
    public String name;
    public int price;

    @Override
    public int compareTo(Object o) {
        Item i = (Item)o;
       if (price < i.price)
           return 1;
       else
           return -1;
    }

    public String toString(){
        return "name=" + name + ",price=" + price;
    }
}
