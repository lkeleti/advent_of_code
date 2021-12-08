package day08;

import java.util.ArrayList;
import java.util.List;

public class Segment {
    List<String> digits = new ArrayList<>();
    List<String> datas = new ArrayList<>();

    public Segment(List<String> digits, List<String> datas) {
        this.digits = digits;
        this.datas = datas;
    }

    public List<String> getDigits() {
        return digits;
    }

    public List<String> getDatas() {
        return datas;
    }
}
