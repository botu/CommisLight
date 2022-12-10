package ru.pav.model;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ClientModelList {

    List<ClientModel> clientModelList;

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (ClientModel cl: clientModelList
             ) {
            builder.append("name: ")
                    .append(cl.name)
                    .append(", code: ")
                    .append(cl.clientCode).append("\n");
        }
        return builder.toString();

    }
}
