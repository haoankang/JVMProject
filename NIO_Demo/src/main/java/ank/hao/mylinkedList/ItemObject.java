package ank.hao.mylinkedList;

public class ItemObject {

    int intValue;
    ItemObject next;

    public ItemObject(int _intValue){
        intValue=_intValue;
    }
    public ItemObject getNext(){
        return next;
    }
    public void setNext(ItemObject _next){
        next=_next;
    }
    public int getItemValue(){
        return intValue;
    }
    public boolean isEqual(int _intValue){
        return(intValue==_intValue)?true:false;
    }

}
