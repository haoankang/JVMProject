package ank.hao.mylinkedList;

public class MyLinkedListImpl implements MyLinkedList {

    private int size;

    private ItemObject first;

    @Override
    public ItemObject find(int value) {
        if(size>0){
            ItemObject itemObject = first;
            for(int i=1;i<=size;i++){
                if(i==value){
                    return itemObject;
                }
                itemObject = itemObject.next;
            }
        }
        return null;
    }

    @Override
    public void remove(int value) {

    }

    @Override
    public void add(int value) {

    }

    @Override
    public int size() {
        return size;
    }
}
