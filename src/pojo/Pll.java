package pojo;

/**
 * @program: sda_gui
 * @description:
 * @author: LYT
 * @create: 2021-01-26 20:22
 **/

public class Pll {
    private String order;
    private String div_val;
    private String fc;
    private String kv;
    private String fp;
    private String fz;
    private String gain;

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

    public String getFc() {
        return fc;
    }

    public void setFc(String fc) {
        this.fc = fc;
    }

    public String getKv() {
        return kv;
    }

    public void setKv(String kv) {
        this.kv = kv;
    }

    public String getFp() {
        return fp;
    }

    public void setFp(String fp) {
        this.fp = fp;
    }

    public String getFz() {
        return fz;
    }

    public void setFz(String fz) {
        this.fz = fz;
    }

    public String getGain() {
        return gain;
    }

    public void setGain(String gain) {
        this.gain = gain;
    }

    public Pll(String order, String div_val, String fc, String kv, String fp, String fz, String gain) {
        this.order = order;
        this.div_val = div_val;
        this.fc = fc;
        this.kv = kv;
        this.fp = fp;
        this.fz = fz;
        this.gain = gain;
    }
    public Pll(){}

}
