package com.objects.ships;

/**
 * Created by Sergey on 28.01.2016.
 */
public class ShipsLimit {
    private int WarShipLimit;
    private int StealthShipLimit;
    private int RegenShipLimit;
    private int RadarShipLimit;
    private int TransportShipLimit;

    /**
     * @param warShipLimit
     * @param stealthShipLimit
     * @param regenShipLimit
     * @param radarShipLimit
     * @param transportShipLimit
     */
    public ShipsLimit(int warShipLimit, int stealthShipLimit, int regenShipLimit, int radarShipLimit, int transportShipLimit) {
        WarShipLimit = warShipLimit;
        StealthShipLimit = stealthShipLimit;
        RegenShipLimit = regenShipLimit;
        RadarShipLimit = radarShipLimit;
        TransportShipLimit = transportShipLimit;
    }

    public int getWarShipLimit() {
        return WarShipLimit;
    }

    public void setWarShipLimit(int warShipLimit) {
        WarShipLimit = warShipLimit;
    }

    public int getStealthShipLimit() {
        return StealthShipLimit;
    }

    public void setStealthShipLimit(int stealthShipLimit) {
        StealthShipLimit = stealthShipLimit;
    }

    public int getRegenShipLimit() {
        return RegenShipLimit;
    }

    public void setRegenShipLimit(int regenShipLimit) {
        RegenShipLimit = regenShipLimit;
    }

    public int getRadarShipLimit() {
        return RadarShipLimit;
    }

    public void setRadarShipLimit(int radarShipLimit) {
        RadarShipLimit = radarShipLimit;
    }

    public int getTransportShipLimit() {
        return TransportShipLimit;
    }

    public void setTransportShipLimit(int transportShipLimit) {
        TransportShipLimit = transportShipLimit;
    }
}
