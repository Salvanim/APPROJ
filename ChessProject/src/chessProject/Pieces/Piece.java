package chessProject.Pieces;

import chessProject.Board;
import java.lang.reflect.Field;

public class Piece extends Board{
	private String displayName = null;
	private String startColor = null;
	private String pieceColor = null;
	private int x = 0;
	private int y = 0;
	private String icon;
	
	public Piece(String newDisplayName, String newStartColor, String newPieceColor, int newX, int newY, String icon) {
		this.displayName = newDisplayName;
		this.startColor = newStartColor;
		this.pieceColor = newPieceColor;
		this.x = newX;
		this.y = newY;
		this.icon = getColor(icon, newPieceColor);
	}
	
	public String toString() {
		int color = -1;
		if(pieceColor.equals("white")) {
			color  = 0;
		} else if(pieceColor.equals("black")) {
			color  = 1;
		}
		return color+displayName.substring(0, 2);
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public String getIcon() {
		return icon;
	}
	
	public String getDisplayName() {
		return displayName;
	}
	
	public String getstartColor() {
		return startColor;
	}
	
	public String getPieceColor() {
		return pieceColor;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public void setIcon(String icon) {
		this.icon = getColor(icon, getPieceColor());
	}
	
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	
	public void setstartColor(String startColor) {
		this.startColor = startColor;
	}
	
	public void setPieceColor(String pieceColor) {
		this.pieceColor = pieceColor;
	}
	
	public void moveX(int amount) {
		setX(getX() + amount);
	}
	
	public void moveY(int amount) {
		setY(getY() + amount);
	}
	
	public void moveDiagonal(int amount, boolean moveUp) {
		moveX(amount);
		moveY(amount*(moveUp ? -1 : 1));
	}
	
	public void moveLShape(boolean moveRight, boolean moveUp) {
		int xMovement = (moveRight ? 1 : -1);
		moveX(xMovement);
		moveDiagonal(xMovement, moveUp);
	}
	
	public static String getColor(String piece, String targetColor) {
		char type = Character.toLowerCase(piece.charAt(0));
		char oppositeColor = targetColor.equalsIgnoreCase("white") ? 'w' : 'b';
		char oppositePiece = (char) (oppositeColor + type - 'p');
		return oppositePiece+"";
	}
	
	public boolean contains(Object checking) {
		Field[] fields = getClass().getDeclaredFields();
        for(Field field : fields) {
            field.setAccessible(true);
            if(field.toString() == checking || field.toString().contains(checking.toString())) {
                return true;
            }
        }
        return false;
	}
}
