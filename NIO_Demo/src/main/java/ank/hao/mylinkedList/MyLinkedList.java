package ank.hao.mylinkedList;

public interface MyLinkedList {

    ItemObject find(int value);
    void remove(int value);
    void add(int value);
    int size();
}
