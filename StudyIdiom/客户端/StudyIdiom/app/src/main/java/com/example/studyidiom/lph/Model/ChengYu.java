package com.example.studyidiom.lph.Model;

public class ChengYu {
    private int id;
    private  String name;
    private  String head;
    private  String rear;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getRear() {
        return rear;
    }

    public void setRear(String rear) {
        this.rear = rear;
    }

    @Override
    public String toString() {
        return "ChengYu{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", head='" + head + '\'' +
                ", rear='" + rear + '\'' +
                '}';
    }
}
