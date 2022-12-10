package ru.pav.model.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import ru.pav.model.ClientModel;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Objects;

@Data
@Getter
@Setter
@Table(name = "Clients")
public class ClientEntity implements Persistable<String> {

    @Id
    @Column(value = "CLIENT_CODE")
    String clientCode;



    LocalDateTime updateTime = LocalDateTime.now();
    LocalDateTime creationTime;


    String name;
    Boolean margin;


    public ClientEntity() {


    }

    public ClientEntity(ClientModel client) {
        this.creationTime = LocalDateTime.now();
        this.clientCode = client.getClientCode();
        this.margin = client.getMargin();
        this.name = client.getName();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (obj == this)
            return true;

        //if (obj instanceof ClientEntity)
        if (obj.getClass().equals(this.getClass())) {
            ClientEntity temp = (ClientEntity) obj;
            return ((temp.getMargin().equals(this.getMargin()))
                    && (temp.name.equals(this.name))
            );
        }
        return false;

    }


    @Override
    public String getId() {
        return clientCode;
    }

    @Override
    @Transient
    public boolean isNew() {
        return true;
    }
}
