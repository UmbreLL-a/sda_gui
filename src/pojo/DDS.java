package pojo;

/**
 * @program: sda_gui
 * @description:
 * @author: LYT
 * @create: 2021-01-30 23:11
 **/

public class DDS {
    private String order;
    private String n;
    private String ftw;
    private String ptd;
    private String symbol_period;
    private String num_filt_taps;

    public DDS(String order, String n, String ftw, String ptd, String symbol_period, String num_filt_taps) {
        this.order = order;
        this.n = n;
        this.ftw = ftw;
        this.ptd = ptd;
        this.symbol_period = symbol_period;
        this.num_filt_taps = num_filt_taps;
    }

    public DDS(){}

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getN() {
        return n;
    }

    public void setN(String n) {
        this.n = n;
    }

    public String getFtw() {
        return ftw;
    }

    public void setFtw(String ftw) {
        this.ftw = ftw;
    }

    public String getPtd() {
        return ptd;
    }

    public void setPtd(String ptd) {
        this.ptd = ptd;
    }

    public String getSymbol_period() {
        return symbol_period;
    }

    public void setSymbol_period(String symbol_period) {
        this.symbol_period = symbol_period;
    }

    public String getNum_filt_taps() {
        return num_filt_taps;
    }

    public void setNum_filt_taps(String num_filt_taps) {
        this.num_filt_taps = num_filt_taps;
    }
}
