package todo.asdf.domain;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class Todo {
    private Long index;
    private String memberId;
    private String content;
    private Timestamp lastUpdatedDate;
    private boolean complete;
}
