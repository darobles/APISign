package cl.auter.VMSAPI.model;

public class SequenceMessageModel {
    Integer id;
    Integer index;
    Integer time;
    MessageModel message;

    public SequenceMessageModel() {
        this.id      = null;
        this.index   = null;
        this.time    = null;
        this.message = new MessageModel();
    }

    public SequenceMessageModel(Integer idSequence, Integer index, Integer time, MessageModel message) {
        this.id      = idSequence;
        this.index   = index;
        this.time    = time;
        this.message = message;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public MessageModel getMessage() {
        return message;
    }

    public void setMessage(MessageModel message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "SequenceMessage{" + "id=" + id + ", index=" + index + ", time=" + time + ", message=" + message + '}';
    }


}
