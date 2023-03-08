package Contexts;

import java.util.ArrayList;
import java.util.List;

import Models.CartItem;

public class CartContext {
    static List<CartItem> cart = new ArrayList<>();

    public static void addItem(CartItem item) {
        CartItem tmp = null;
        for(int i = 0; i < cart.size(); i++) {
            if(cart.get(i).getCode().equals(item.getCode())) {
                tmp = cart.get(i);
                tmp.setNum(tmp.getNum() + 1);
                break;
            }
        }
        if(tmp == null) {
            cart.add(item);
        }
    }

    public static void removeItem(String code) {
        CartItem tmp = null;
        for(int i = 0; i < cart.size(); i++) {
            if(cart.get(i).getCode().equals(code)) {
                cart.remove(i);
                break;
            }
        }
    }

    public static CartItem get(int index) {
        return cart.get(index);
    }

    public static int size() {
        return cart.size();
    }
}
