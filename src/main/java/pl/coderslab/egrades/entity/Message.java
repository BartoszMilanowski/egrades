package pl.coderslab.egrades.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "messages")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    private User sender;

    @NotNull
    @ManyToMany
    @JoinTable(name = "message_addressee", joinColumns = @JoinColumn(name = "message_id"),
    inverseJoinColumns = @JoinColumn(name = "addressee_id"))
    private List<User> addressee;

    @NotBlank
    @Size(min = 5, max = 250)
    private String title;

    @NotBlank
    @Size(min = 5, max = 10000)
    private String content;

    private LocalDateTime sendTime;

    private boolean opened;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public List<User> getAddressee() {
        return addressee;
    }

    public void setAddressee(List<User> addressee) {
        this.addressee = addressee;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getSendTime() {
        return sendTime;
    }

    public void setSendTime(LocalDateTime sendTime) {
        this.sendTime = sendTime;
    }

    public boolean isOpened() {
        return opened;
    }

    public void setOpened(boolean opened) {
        this.opened = opened;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", sender=" + sender +
                ", addressee=" + addressee +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", dateTime=" + sendTime +
                ", read=" + opened +
                '}';
    }
}
