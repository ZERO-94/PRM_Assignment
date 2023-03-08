package Models;

public class CartItem {
    String code;
    String name;
    String imageUrl;
    int price;
    int num;

    public CartItem(String code, String name, String imageUrl, int price, int num) {
        this.code = code;
        this.name = name;
        this.imageUrl = imageUrl;
        this.price = price;
        this.num = num;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
