package pojo;

/**
 * @program: sda_gui
 * @description:
 * @author: LYT
 * @create: 2021-03-03 19:31
 **/

public class DIV {
    private String order;
    private String div_val;

    public DIV(String order, String div_val) {
        this.order = order;
        this.div_val = div_val;
    }
    public DIV(){};

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getDiv_val() {
        return div_val;
    }

    public void setDiv_val(String div_val) {
        this.div_val = div_val;
    }
}
