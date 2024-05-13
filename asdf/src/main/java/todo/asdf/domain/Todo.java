package todo.asdf.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Todo {
    private Long index;
    private String memberId;
    private String content;
    private String lastUpdatedDate;
    private boolean complete;
}
