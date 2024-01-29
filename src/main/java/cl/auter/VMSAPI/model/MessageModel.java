package cl.auter.VMSAPI.model;

import cl.auter.util.Constants;

public class MessageModel {
    Integer id;
    String  name;
    String  message;
    Integer alignmentId;
    String  alignmentName;
    Integer spacing;
    Integer colour;
    Integer signTypeId;
    String  signTypeName;
    Integer signTypeHeight;
    Integer signTypeWidth;
    Integer lineSpacing;
    Integer maxSymbolHeight;
    Integer maxSymbolWidth;
    Integer widthTypeId;
    String  widthTypeName;
    Integer signTypeSpacing;
    Integer grain;
    Integer protocol;
    Integer groupId;
    String  groupName;
    String  description;
    Integer maxHeight;
    Integer groupWidth;
    
    public MessageModel() {
        this.id              = null;
        this.name            = null;
        this.message         = null;
        this.alignmentId     = null;
        this.alignmentName   = null;
        this.spacing         = null;
        this.colour          = null;
        this.signTypeId      = null;
        this.signTypeName    = null;
        this.signTypeHeight  = null;
        this.signTypeWidth   = null;
        this.lineSpacing     = null;
        this.maxSymbolHeight = null;
        this.maxSymbolWidth  = null;
        this.widthTypeId     = null;
        this.widthTypeName   = null;
        this.signTypeSpacing = null;
        this.grain           = null;
        this.protocol        = null;
        this.groupId         = null;
        this.groupName       = null;
        this.description     = null;
        this.groupWidth      = null;
        this.maxHeight       = null;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getAlignmentId() {
        return alignmentId;
    }

    public void setAlignmentId(Integer alignmentId) {
        this.alignmentId = alignmentId;
    }

    public String getAlignmentName() {
        return alignmentName;
    }

    public void setAlignmentName(String alignmentName) {
        this.alignmentName = alignmentName;
    }

    public Integer getSpacing() {
        return spacing;
    }

    public void setSpacing(Integer spacing) {
        this.spacing = spacing;
    }

    public Integer getColour() {
        return colour;
    }

    public void setColour(Integer colour) {
        if ((colour == Constants.COL_WHITE) || (colour == Constants.COL_AMBER) || (colour == Constants.COL_RED)) {
            this.colour = colour;
        } else {
            this.colour = null;
        }
    }

    public Integer getSignTypeId() {
        return signTypeId;
    }

    public void setSignTypeId(Integer signTypeId) {
        this.signTypeId = signTypeId;
    }

    public String getSignTypeName() {
        return signTypeName;
    }

    public void setSignTypeName(String signTypeName) {
        this.signTypeName = signTypeName;
    }

    public Integer getSignTypeHeight() {
        return signTypeHeight;
    }

    public void setSignTypeHeight(Integer signTypeHeight) {
        this.signTypeHeight = signTypeHeight;
    }

    public Integer getSignTypeWidth() {
        return signTypeWidth;
    }

    public void setSignTypeWidth(Integer signTypeWidth) {
        this.signTypeWidth = signTypeWidth;
    }

    public Integer getLineSpacing() {
        return lineSpacing;
    }

    public void setLineSpacing(Integer lineSpacing) {
        this.lineSpacing = lineSpacing;
    }

    public Integer getMaxSymbolHeight() {
        return maxSymbolHeight;
    }

    public void setMaxSymbolHeight(Integer maxSymbolHeight) {
        this.maxSymbolHeight = maxSymbolHeight;
    }

    public Integer getMaxSymbolWidth() {
        return maxSymbolWidth;
    }

    public void setMaxSymbolWidth(Integer maxSymbolWidth) {
        this.maxSymbolWidth = maxSymbolWidth;
    }

    public Integer getWidthTypeId() {
        return widthTypeId;
    }

    public void setWidthTypeId(Integer widthTypeId) {
        this.widthTypeId = widthTypeId;
    }

    public String getWidthTypeName() {
        return widthTypeName;
    }

    public void setWidthTypeName(String widthTypeName) {
        this.widthTypeName = widthTypeName;
    }

    public Integer getSignTypeSpacing() {
        return signTypeSpacing;
    }

    public void setSignTypeSpacing(Integer signTypeSpacing) {
        this.signTypeSpacing = signTypeSpacing;
    }

    public Integer getGrain() {
        return grain;
    }

    public void setGrain(Integer grain) {
        this.grain = grain;
    }

    public Integer getProtocol() {
        return protocol;
    }

    public void setProtocol(Integer protocol) {
        this.protocol = protocol;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getMaxHeight() {
        return maxHeight;
    }

    public void setMaxHeight(Integer maxHeight) {
        this.maxHeight = maxHeight;
    }

    public Integer getGroupWidth() {
        return groupWidth;
    }

    public void setGroupWidth(Integer groupWidth) {
        this.groupWidth = groupWidth;
    }

    @Override
    public String toString() {
        return "Message{" + "id=" + id + ", name=" + name + ", message=" + message + ", alignmentId=" + alignmentId + ", alignmentName=" + alignmentName + ", spacing=" + spacing + ", colour=" + colour + ", signTypeId=" + signTypeId + ", signTypeName=" + signTypeName + ", signTypeHeight=" + signTypeHeight + ", signTypeWidth=" + signTypeWidth + ", lineSpacing=" + lineSpacing + ", maxSymbolHeight=" + maxSymbolHeight + ", maxSymbolWidth=" + maxSymbolWidth + ", widthTypeId=" + widthTypeId + ", widthTypeName=" + widthTypeName + ", signTypeSpacing=" + signTypeSpacing + ", grain=" + grain + ", protocol=" + protocol + ", groupId=" + groupId + ", groupName=" + groupName + ", description=" + description + ", maxHeight=" + maxHeight + ", groupWidth=" + groupWidth + '}';
    }
}