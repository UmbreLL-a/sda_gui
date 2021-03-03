package pojo;

/**
 * @program: sda_gui
 * @description:
 * @author: LYT
 * @create: 2021-03-03 19:35
 **/

public class MUL {
    private String order;
    private String mut_val;

    public MUL(String order, String mut_val) {
        this.order = order;
        this.mut_val = mut_val;
    }
    public MUL(){};

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getMut_val() {
        return mut_val;
    }

    public void setMut_val(String mut_val) {
        this.mut_val = mut_val;
    }
}
