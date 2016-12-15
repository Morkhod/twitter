package ru.urfu.entitles;

import org.apache.commons.lang3.StringEscapeUtils;
import javax.persistence.*;

@Entity
@Table(name = "TBL_MESSAGE")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "content", nullable = false)
    private String content;
    @Column(name = "author", nullable = false)
    private String author;
    @Column(name = "authorId")
    private int authorId;

    public Message() {
    }

    public Message(String _content, String authorLogin, int _authorId) {
        content = _content;
        author = authorLogin;
        authorId = _authorId;
    }

    public int getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public String getScreenedContent() {
        return StringEscapeUtils.escapeHtml4(content);
    }

    public String getAuthor() {
        return author;
    }

    public int getAuthorId() {
        return authorId;
    }
}
