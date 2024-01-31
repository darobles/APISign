package cl.auter.VMSAPI.model;

import java.util.List;

public class Symbol {
    Integer       groupId;
    Integer       code;
    Integer       width;
    Character     symbol;
    String        data_str;
    List<Integer> data;
    


    public Symbol(Integer groupId, Integer code, Integer width, Character symbol, String data) {
        this.groupId = groupId;
        this.code    = code;
        this.width   = width;
        this.symbol  = symbol;
        this.data_str    = data;
    }
    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public char getSymbol() {
        return symbol;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }

    public List<Integer> getData() {
        this.data.clear();
        if (! data_str.trim().isEmpty()) {
        	data_str = data_str.trim().replaceAll("\\)\\(", ",");
        	data_str = data_str.substring(1, data_str.length() - 1);
            String [] values = data_str.split(",");
            for (String value : values) {
                this.data.add(Integer.valueOf(value));
            }
        }
        return data;
    }


    @Override
    public String toString() {
        return "Symbol{" + "groupId=" + groupId + ", code=" + code + ", width=" + width + ", symbol=" + symbol + ", data=" + data + '}';
    }

}
