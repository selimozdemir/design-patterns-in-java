package com.company.structural.composite;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

abstract class MenuComponent {

    void add(MenuComponent m){
        throw new UnsupportedOperationException();
    }

    void remove(MenuComponent m){
        throw new UnsupportedOperationException();
    }

    MenuComponent getChilds(int i){
        throw new UnsupportedOperationException();
    }

    String getName(){
        throw new UnsupportedOperationException();
    }

    String getDesc(){
        throw new UnsupportedOperationException();
    }

    Float getPrice(){
        throw new UnsupportedOperationException();
    }

    Boolean isVegeterian(){
        throw new UnsupportedOperationException();
    }

    void print(String prefix){
    }

    Iterator createIterator(){
        throw new UnsupportedOperationException();
    }

    Iterator getIterator(){
        return null;
    }

}

class MenuItem extends MenuComponent{

    private String name;
    private String desc;
    private Float price;
    private Boolean isVegeterian;

    public MenuItem(String name, String desc, Float price, Boolean isVegeterian) {
        this.name = name;
        this.desc = desc;
        this.price = price;
        this.isVegeterian = isVegeterian;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public Float getPrice() {
        return price;
    }

    public Boolean isVegeterian() {
        return isVegeterian;
    }

    @Override
    public String toString() {
        return "MenuItem{" +
                "name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", price=" + price +
                ", isVegeterian=" + isVegeterian +
                '}';
    }

    public void print(String prefix) {
        System.out.println(prefix + this);
    }

    @Override
    Iterator createIterator() {
        return new NullIterator();
    }

    private class NullIterator implements Iterator {
        @Override
        public boolean hasNext() {
            return false;
        }

        @Override
        public Object next() {
            return null;
        }
    }
}

class Menu extends MenuComponent{
    Iterator iterator = null;
    List<MenuComponent> childs = new ArrayList<>();
    private String name;
    private String desc;

    public Menu(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public void add(MenuComponent m){
        this.childs.add(m);
    }

    public void remove(MenuComponent m){
        this.childs.remove(m);
    }

    public MenuComponent getChilds(int i){
        return this.childs.get(i);
    }

    @Override
    public String toString() {
        return "Menu{" +
                "name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }

    void print(String prefix){

        System.out.println(prefix + this);

        for (MenuComponent m : this.childs) {
            m.print(prefix + "-");
        }
    }

    @Override
    Iterator createIterator() {
        if (iterator == null) {
            iterator = new CompositeIterator(this.childs.iterator());
        }

        return iterator;
    }

    Iterator getIterator(){
        return iterator;
    }
}

class Waiter {
    MenuComponent allItems;

    public Waiter(MenuComponent allItems) {
        this.allItems = allItems;
    }

    public void print(){
        this.allItems.print("-");
    }
}

class CompositeIterator implements Iterator{
    private Stack<Iterator> stack = new Stack<Iterator>();

    public CompositeIterator(Iterator it) {
        this.stack.push(it);
    }

    @Override
    public boolean hasNext() {
        if(this.stack.isEmpty()){
            return false;
        } else {
            Iterator it = this.stack.peek();
            if(!it.hasNext()){
                this.stack.pop();
                return hasNext();
            } else {
                return true;
            }
        }
    }

    @Override
    public MenuComponent next() {
        if(hasNext()){
            Iterator it = this.stack.peek();
            MenuComponent m = (MenuComponent) it.next();
            if(m instanceof Menu){
                stack.push(m.createIterator());
            }

            return m;
        }

        return null;
    }
}

public class Main {

    static void printExternal(MenuComponent m){
        Iterator iterator = m.getIterator();
        while(iterator.hasNext()) {
            MenuComponent component = (MenuComponent) iterator.next();
            component.print("-");
        }


    }

    public static void main(String[] args) {
        MenuComponent breakfast = new Menu("Van kahvaltı","Bla bla breakfast menu");
        MenuComponent dinner = new Menu("Balık Yemekleri","Bla bla dinner menu");
        MenuComponent dessert = new Menu("Tatlı Menusü","Bla bla tatlı menüsü");

        // Create Breakfast menuü
        MenuComponent recel = new MenuItem("Kayısı","Yoğun kayısı",5f, true);
        MenuComponent tahin = new MenuItem("Tahin","Pekmez ve Tahinli",5f, true);
        MenuComponent peynir = new MenuItem("Peynir","Van otlu peynir",5f, true);
        MenuComponent tulum = new MenuItem("Tulum peynir","Tulum peynir desc",5f, true);

        breakfast.add(recel);
        breakfast.add(tahin);
        breakfast.add(peynir);
        breakfast.add(tulum);

        //Create dinner menüs
        MenuComponent levrek = new MenuItem("Tavada levrek","Leziz bla bla", 10f,false);
        dinner.add(levrek);

        MenuComponent baklava = new MenuItem("Baklava","", 24f, true);
        dessert.add(baklava);

        MenuComponent all = new Menu("All menus","");
        all.add(breakfast);
        all.add(dinner);
        all.add(dessert);
        all.createIterator();

        Waiter w = new Waiter(all);
        //w.print();

        printExternal(all);
    }


}
