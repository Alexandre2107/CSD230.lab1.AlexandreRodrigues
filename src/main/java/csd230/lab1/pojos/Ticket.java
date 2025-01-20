package csd230.lab1.pojos;

/**
 * DTO for {@link csd230.lab1.entities.old.Ticket}
 */
public class Ticket extends CartItem {
    private String text;
    public Ticket() {
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public void sellItem() {
        System.out.println(getDescription());
    }


}