package Models;

public class Instrument {
    String code;
    String name;
    String imageUrl;
    String sound;
    int price;
    int amount;

    public Instrument(String code, String name, String imageUrl, int price, int amount, String sound) {
        this.code = code;
        this.name = name;
        this.imageUrl = imageUrl;
        this.price = price;
        this.amount = amount;
        this.sound = sound;
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

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getSound() {
        return sound;
    }

    public void setSound(String sound) {
        this.sound = sound;
    }
}
