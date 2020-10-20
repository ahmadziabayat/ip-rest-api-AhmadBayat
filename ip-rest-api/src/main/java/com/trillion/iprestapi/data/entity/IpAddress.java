package com.trillion.iprestapi.data.entity;

import com.trillion.iprestapi.util.StatusType;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="IP_ADDRESS")
public class IpAddress implements Serializable {



    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ip_address_Id;

    @Column(name="IP_ADDRESS")
    private String ip_address;

    @NonNull
    @Column(name="CIDR_BLOCK", nullable = false)
    private String cidr_Block;

    @NonNull
    @Enumerated(EnumType.STRING)
    @Column(name="CURRENT_STATUS", nullable = false)
    private StatusType currentStatus;

    @ManyToOne
    @JoinColumn(name="CREATED_BY_USER", foreignKey = @ForeignKey(name="fk-user_created_ip_address"))
    private User createdByUser;

    @ManyToOne
    @JoinColumn(name="UPDATED_BY_USER", foreignKey = @ForeignKey(name="fk-user_updated_ip_address"))
    private User updatedByUser;

    public IpAddress() {
    }

    public Long getIp_address_Id() {
        return ip_address_Id;
    }

    public void setIp_address_Id(Long ip_address_Id) {
        this.ip_address_Id = ip_address_Id;
    }

    public String getIp_address() {
        return ip_address;
    }

    public void setIp_address(String ip_address) {
        this.ip_address = ip_address;
    }

    public String getCidr_Block() {
        return cidr_Block;
    }

    public void setCidr_Block(String cidr_Block) {
        this.cidr_Block = cidr_Block;
    }

    public StatusType getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(StatusType currentStatus) {
        this.currentStatus = currentStatus;
    }

    public User getCreatedByUser() {
        return createdByUser;
    }

    public void setCreatedByUser(User createdByUser) {
        this.createdByUser = createdByUser;
    }

    public User getUpdatedByUser() {
        return updatedByUser;
    }

    public void setUpdatedByUser(User updatedByUser) {
        this.updatedByUser = updatedByUser;
    }

    @Override
    public String toString() {
        return "IpAddress{" +
                ", ip_address='" + ip_address + '\'' +
                ", cidr_Block='" + cidr_Block + '\'' +
                ", currentStatus=" + currentStatus +
                ", createdByUser=" + createdByUser +
                ", updatedByUser=" + updatedByUser +
                '}';
    }
}
