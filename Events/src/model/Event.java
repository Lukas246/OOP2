package model;

import app.EventType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Event {
    @JsonProperty()
    private int id;
    private String name;
    private EventType type;
    private LocalDateTime timestamp;
}
