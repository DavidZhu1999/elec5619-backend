package com.freshshare.request;

import lombok.Data;

import java.util.List;

@Data
public class AddOrderRequest {

    private String businessId;

    private String customerId;

    private String orderPrice;

    private List<CartItem> cartItems;

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public static class CartItem {
        private String commodityId;
        private String detailNumBuy;
        private String detailPrice;

        // Getters, Setters, and default and parameterized constructors

        public String getCommodityId() {
            return commodityId;
        }

        public void setCommodityId(String commodityId) {
            this.commodityId = commodityId;
        }

        public String getDetailNumBuy() {
            return detailNumBuy;
        }

        public void setDetailNumBuy(String detailNumBuy) {
            this.detailNumBuy = detailNumBuy;
        }

        public String getDetailPrice() {
            return detailPrice;
        }

        public void setDetailPrice(String detailPrice) {
            this.detailPrice = detailPrice;
        }
    }

}
