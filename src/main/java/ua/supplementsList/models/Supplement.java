package ua.supplementsList.models;

public class Supplement {

    private int id;
    private String code;
    private int infoId;
    private SupplementInfo info;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getInfoId() {
        return infoId;
    }

    public void setInfoId(int infoId) {
        this.infoId = infoId;
    }

    public SupplementInfo getInfo() {
        return info;
    }

    public void setInfo(SupplementInfo info) {
        this.info = info;
    }
}
