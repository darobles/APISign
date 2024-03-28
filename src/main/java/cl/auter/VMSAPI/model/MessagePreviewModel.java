package cl.auter.VMSAPI.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MessagePreviewModel {
	@JsonProperty("message")
	String  message;
	@JsonProperty("alignmentId")
	Integer alignmentId;
	@JsonProperty("groupId")
	Integer groupId;
	@JsonProperty("colour")
	Integer colour;
	@JsonProperty("spacing")
	Integer spacing;
	@JsonProperty("vAlignLeft")
	Integer verticalAlign_left;
	@JsonProperty("imgLeft")
	String  imageB64_left;
	@JsonProperty("vAlignRight")
	Integer verticalAlign_right;
	@JsonProperty("imgRight")
	String  imageB64_right;
	@JsonProperty("sign_id")
	Integer sign_id;
	
	public MessagePreviewModel() {
		super();
		this.message = null;
		this.alignmentId = null;
		this.groupId = null;
		this.colour = null;
		this.spacing = null;
		this.verticalAlign_left = null;
		this.imageB64_left = null;
		this.verticalAlign_right = null;
		this.imageB64_right = null;
		this.message = null;
	}

	public MessagePreviewModel(String message, Integer alignmentId, Integer groupId, Integer colour, Integer spacing,
			Integer verticalAlign_left, String imageB64_left, Integer verticalAlign_right, String imageB64_right) {
		super();
		this.message = message;
		this.alignmentId = alignmentId;
		this.groupId = groupId;
		this.colour = colour;
		this.spacing = spacing;
		this.verticalAlign_left = verticalAlign_left;
		this.imageB64_left = imageB64_left;
		this.verticalAlign_right = verticalAlign_right;
		this.imageB64_right = imageB64_right;
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

	public Integer getColour() {
		return colour;
	}

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public void setColour(Integer colour) {
		this.colour = colour;
	}

	public Integer getSpacing() {
		return spacing;
	}

	public void setSpacing(Integer spacing) {
		this.spacing = spacing;
	}

	public Integer getVerticalAlign_left() {
		return verticalAlign_left;
	}

	public void setVerticalAlign_left(Integer verticalAlign_left) {
		this.verticalAlign_left = verticalAlign_left;
	}

	public String getImageB64_left() {
		return imageB64_left;
	}

	public void setImageB64_left(String imageB64_left) {
		this.imageB64_left = imageB64_left;
	}

	public Integer getVerticalAlign_right() {
		return verticalAlign_right;
	}

	public void setVerticalAlign_right(Integer verticalAlign_right) {
		this.verticalAlign_right = verticalAlign_right;
	}

	public String getImageB64_right() {
		return imageB64_right;
	}

	public void setImageB64_right(String imageB64_right) {
		this.imageB64_right = imageB64_right;
	}

	public Integer getSign_id() {
		return sign_id;
	}

	public void setSign_id(Integer sign_id) {
		this.sign_id = sign_id;
	}

	@Override
	public String toString() {
		return "MessagePreviewModel [message=" + message + ", alignmentId=" + alignmentId + ", groupId=" + groupId
				+ ", colour=" + colour + ", spacing=" + spacing + ", verticalAlign_left=" + verticalAlign_left
				+ ", imageB64_left=" + imageB64_left + ", verticalAlign_right=" + verticalAlign_right
				+ ", imageB64_right=" + imageB64_right + ", sign_id=" + sign_id + "]";
	}

}
