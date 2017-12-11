package monpkg.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity()
@Table(name = "BB_COUNTER")
public class Counter implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id()
    private String name;

    @Column(name = "value")
    private Integer value;

    public Counter() {
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

}