package cl.auter.VMSAPI.model;

public class ImageEntity {
	String right_image;
	String left_image;
	
	public ImageEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getRight_image() {
		return right_image;
	}
	public void setRight_image(String right_image) {
		this.right_image = right_image;
	}
	public String getLeft_image() {
		return left_image;
	}
	public void setLeft_image(String left_image) {
		this.left_image = left_image;
	}
	@Override
	public String toString() {
		return "ImageEntity [right_image=" + right_image + ", left_image=" + left_image + "]";
	}
	
	
}
