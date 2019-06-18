package com.objects.ships;

/**
 * Created by Sergey on 28.01.2016.
 */
public class ShipPrices {
    private int warShipPrice;
    private int regenShipPrice;
    private int stealthShipPrice;
    private int radarShipPrice;
    private int transportShipPrice;


    /**
     * @param warShipPrice
     * @param regenShipPrice
     * @param stealthShipPrice
     * @param radarShipPrice
     * @param transportShipPrice
     */
    public ShipPrices(int warShipPrice, int regenShipPrice, int stealthShipPrice, int radarShipPrice, int transportShipPrice) {
        this.warShipPrice = warShipPrice;
        this.regenShipPrice = regenShipPrice;
        this.stealthShipPrice = stealthShipPrice;
        this.radarShipPrice = radarShipPrice;
        this.transportShipPrice = transportShipPrice;
    }

    public int getWarShipPrice() {
        return warShipPrice;
    }

    public void setWarShipPrice(int warShipPrice) {
        this.warShipPrice = warShipPrice;
    }

    public int getRegenShipPrice() {
        return regenShipPrice;
    }

    public void setRegenShipPrice(int regenShipPrice) {
        this.regenShipPrice = regenShipPrice;
    }

    public int getStealthShipPrice() {
        return stealthShipPrice;
    }

    public void setStealthShipPrice(int stealthShipPrice) {
        this.stealthShipPrice = stealthShipPrice;
    }

    public int getRadarShipPrice() {
        return radarShipPrice;
    }

    public void setRadarShipPrice(int radarShipPrice) {
        this.radarShipPrice = radarShipPrice;
    }

    public int getTransportShipPrice() {
        return transportShipPrice;
    }

    public void setTransportShipPrice(int transportShipPrice) {
        this.transportShipPrice = transportShipPrice;
    }
}
