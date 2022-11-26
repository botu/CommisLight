package ru.pav.model.entity;

import lombok.Generated;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import ru.pav.model.ClientModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name="CLIENTS")
@Getter
@Setter
public class ClientEntity {

    @Id
    @Column(name="CLIENT_CODE",updatable = false)
    String clientCode;


    @UpdateTimestamp
    Date updateTime;

    @CreationTimestamp
    Date creationTime;

    String name;
    Boolean margin;

    public ClientEntity(){}

    public ClientEntity(ClientModel client) {
        this.creationTime = new Date();
        this.clientCode = client.getClientCode();
        this.margin     = client.getMargin();
        this.name       = client.getName();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (obj == this)
            return true;

        //if (obj instanceof ClientEntity)
        if (obj.getClass().equals(this.getClass()))
        {
            ClientEntity temp = (ClientEntity) obj;
            return ((temp.getMargin().equals(this.getMargin()))
                    && (temp.name.equals(this.name))
            );
        }
        return false;

    }
}
