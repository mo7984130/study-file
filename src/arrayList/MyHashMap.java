package arrayList;

import Classes.Hero;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MyHashMap {
    static LinkedList<Entry>[] ll = new LinkedList[2000];

    public static void put(String key,Object value){
        int hashcode = toHashCode(key);
        if (ll[hashcode] == null){
            ll[hashcode] = new LinkedList<>();
        }
        ll[hashcode].addLast(new Entry(key,value));
    }

    public Object get(String key){
        if (ll[toHashCode(key)] == null){
            return null;
        }else if (ll[toHashCode(key)] != null){
            LinkedList entryLL = new LinkedList();
            for (Entry e : ll[toHashCode(key)]){
                if (e.getKey().equals(key))
                    entryLL.add(e.value);
            }
            return entryLL;
        }
        return null;
    }

    static class Entry{
        public Object key;
        public Object value;

        public Entry(Object key,Object value){
            this.key = key;
            this.value = value;
        }

        public String toString(){
            return "[key=" + key + ",value=" + value + "]";
        }

        public String getKey(){
            return String.valueOf(key);
        }
    }

    public static int toHashCode(String str){
        char[] cs = str.toCharArray();
        int i1 = 0;
        for (int i=0;i<cs.length;i++){
            i1 += cs[i];
        }
        int i2 = i1*23;
        if (i2>1999){
            i2 = i2%2000;
        }else if (i2<0){
            i2 = -1 * i2;
        }
        return i2;
    }

    public static void main(String[] args) {
        List<Hero> h = new ArrayList<>();
        for (int i =0;i<100000;i++){
            Hero hero = new Hero();
            hero.name = "hero-" + (int)(Math.random()*10000);
            h.add(hero);
        }

        MyHashMap mhm = new MyHashMap();
        for (int i =0;i<100000;i++){
            Hero hero = new Hero();
            hero.name = "hero-" + (int)(Math.random()*10000);
            mhm.put(hero.name, hero);
        }

        long start = System.currentTimeMillis();
        System.out.println(mhm.get("hero-5555"));
        long end = System.currentTimeMillis();
        long myHashMapTimeConsuming = end - start;
        System.out.println("MyHashMap耗时："+myHashMapTimeConsuming);


        start = System.currentTimeMillis();
        for (int i=0;i<h.size();i++){
            Hero hero = h.get(i);
            if (hero.name.equals("hero-5555"))
                System.out.println(hero);
        }
        end = System.currentTimeMillis();
        long time = end-start;
        System.out.println("for耗时：" + time);

    }

}
