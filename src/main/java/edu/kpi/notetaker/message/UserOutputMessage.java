package edu.kpi.notetaker.message;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import edu.kpi.notetaker.model.Notebook;
import edu.kpi.notetaker.model.User;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

@Data
public class UserOutputMessage {
    @JsonProperty
    private Integer id;
    @JsonProperty
    private String username;
    @JsonProperty("creation_timestamp")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd@HH:mm:ss")
    private LocalDateTime creationTimestamp;
    @JsonProperty("notebook_ids")
    private Collection<Integer> notebookIds;

    public static UserOutputMessage fromUser(User user){
        UserOutputMessage message = new UserOutputMessage();
        message.setId(user.getId());
        message.setUsername(user.getUsername());
        message.setCreationTimestamp(user.getCreationTimestamp());
        message.setNotebookIds(user.getNotebooks()
                .stream()
                .map(Notebook::getId)
                .collect(
                        Collectors.toCollection(ArrayList::new)
                )
        );
        return message;
    }
}
