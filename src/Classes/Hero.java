package Classes;

/**
 * @author mo7984130
 */
public class Hero {
    public int id;
    public String name;
    public volatile int hp;
    public int damage;

    public Hero(){}

    public Hero(String name,int hp){
        this.name = name;
        this.hp = hp;
    }

    public Hero(String name,int hp,int damage){
        this.name = name;
        this.hp = hp;
        this.damage = damage;
    }

    public Hero(int id,String name,int hp,int damage){
        this.id = id;
        this.name = name;
        this.hp = hp;
        this.damage = damage;
    }

    @Override
    public String toString(){
        return "name=" + name;
    }

    public synchronized void recover(){
        if (hp >= 1000){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        hp += 1;
        System.out.format("%s 已经回血 %d ，现在的血量为 %d %n",name,1,hp);
    }

    public synchronized  void hurt(){
        hp -= 1;
        System.out.format("%s 扣血 %d 点 ，现在的血量为 %d %n",name,1,hp);
        this.notify();
    }

    public synchronized void attackHero(Hero anotherHero){
        anotherHero.hp -= damage;
        if (anotherHero.isDead()){
            System.out.format("%s 已经死亡 %n",anotherHero.name);
        }
        System.out.format("%s 正在攻击 %s ， %s的血量变为了 %d %n",name,anotherHero.name,anotherHero.name,anotherHero.hp);
    }

    public boolean isDead(){
        return 0>=hp;
    }
}
