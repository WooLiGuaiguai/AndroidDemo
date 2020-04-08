package Fruit;

public class Fruit {
    private String name;
    private int imageId;
    public Fruit(String name, int imageId) {//name 表示水果的名字， imageId 表示水果对应图片的资源id
        this.name = name;
        this.imageId = imageId;
    }
    public String getName() {
        return name;
    }
    public int getImageId() {
        return imageId;}
}
