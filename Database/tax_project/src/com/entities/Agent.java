package com.entities;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by Sergey on 25.12.2016.
 */
@Entity
public class Agent {
    private int pkagent;
    private int itin;
    private String name;
    private String address;
    private String phone;
    private String chiefname;

    @Id
    @Column (name = "pkagent", nullable = false)
    public int getPkagent() {
        return pkagent;
    }

    public void setPkagent(int pkagent) {
        this.pkagent = pkagent;
    }

    @Basic
    @Column (name = "itin", nullable = false)
    public int getItin() {
        return itin;
    }

    public void setItin(int itin) {
        this.itin = itin;
    }

    @Basic
    @Column (name = "name", nullable = true, length = 200)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column (name = "address", nullable = true, length = 500)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column (name = "phone", nullable = true, length = 20)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Basic
    @Column (name = "chiefname", nullable = true, length = 200)
    public String getChiefname() {
        return chiefname;
    }

    public void setChiefname(String chiefname) {
        this.chiefname = chiefname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Agent agent = (Agent) o;

        if (pkagent != agent.pkagent) return false;
        if (itin != agent.itin) return false;
        if (name != null ? !name.equals(agent.name) : agent.name != null) return false;
        if (address != null ? !address.equals(agent.address) : agent.address != null) return false;
        if (phone != null ? !phone.equals(agent.phone) : agent.phone != null) return false;
        if (chiefname != null ? !chiefname.equals(agent.chiefname) : agent.chiefname != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = pkagent;
        result = 31 * result + itin;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (chiefname != null ? chiefname.hashCode() : 0);
        return result;
    }
}
