package perfumeManage.perfumeManagingSystem.domain;

import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
public class Deadline {
    private int year;

    private int month;

    private int date;

    protected Deadline() {

    }

    public Deadline(int year, int month, int date) {
        this.year = year;
        this.month = month;
        this.date = date;
    }
}
