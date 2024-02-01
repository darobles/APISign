package cl.auter.VMSAPI.model;

import java.util.ArrayList;
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
        this.data_str = data;
        this.data = new ArrayList<Integer>();
        setData();
    }
    public Symbol(SymbolModel symbolModel) {
        this.setGroupId(symbolModel.getId_grupo());
        this.setCode(symbolModel.getCodigo());
        this.setWidth(symbolModel.getAncho());
        this.setSymbol(symbolModel.getCaracter());
        this.data_str = symbolModel.getData();
        this.data = new ArrayList<Integer>();
        setData();
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

	public void setData() {
        if ((this.data_str != null) && (! data_str.trim().isEmpty())) {
        	this.data_str = this.data_str.trim().replaceAll("\\)\\(", ",");
        	this.data_str = this.data_str.substring(1, data_str.length() - 1);
            String [] values = this.data_str.split(",");
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
