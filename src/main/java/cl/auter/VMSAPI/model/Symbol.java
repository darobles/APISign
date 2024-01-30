package cl.auter.VMSAPI.model;

import java.util.List;
import java.util.ArrayList;

public class Symbol {
    Integer       groupId;
    Integer       code;
    Integer       width;
    Character     symbol;
    List<Integer> data;

    public Symbol() {
        this.groupId = null;
        this.code    = null;
        this.width   = null;
        this.symbol  = null;
        this.data    = new ArrayList<Integer>();
    }

    public Symbol(Integer groupId, Integer code, Integer width, Character symbol, List<Integer> data) {
        this.groupId = groupId;
        this.code    = code;
        this.width   = width;
        this.symbol  = symbol;
        this.data    = data;
    }

    // JPérez 2024.01.30
    public Symbol(SymbolModel symbol) {
        this.setGroupId(symbol.getId_grupo());
        this.setCode(symbol.getCodigo());
        this.setWidth(symbol.getAncho());
        this.setSymbol(symbol.getCaracter());
        this.setData(symbol.getData());
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
        return data;
    }

    public void setData(List<Integer> data) {
        this.data = data;
    }
    
    public void setData(String dataRaw) {
        this.data.clear();
        if (! dataRaw.trim().isEmpty()) {
            dataRaw = dataRaw.trim().replaceAll("\\)\\(", ",");
            dataRaw = dataRaw.substring(1, dataRaw.length() - 1);
            String [] values = dataRaw.split(",");
            for (String value : values) {
                this.data.add(Integer.valueOf(value));
            }
        }
    }

    @Override
    public String toString() {
        return "Symbol{" + "groupId=" + groupId + ", code=" + code + ", width=" + width + ", symbol=" + symbol + ", data=" + data + '}';
    }

}
