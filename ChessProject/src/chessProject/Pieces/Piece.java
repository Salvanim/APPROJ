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
		this.icon = setChessIconColor(icon.charAt(0), newPieceColor);
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
		this.icon = icon;
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
	
	private String setChessIconColor(char icon, String color) {
		String swapedIcon = icon+"";
		String unicode = charToUnicode(icon);
		String changingCharacter = unicode.split("265")[1];
		if((isNumber(changingCharacter) && color == "white") || isLetter(changingCharacter) && color == "black") {
			swapedIcon = unicodeEscapeToText("\\u265" + letterOrNumberToOppositeWithShift(changingCharacter.charAt(0),3));
		}
		return swapedIcon;
	}
	
	public String letterOrNumberToOppositeWithShift(char input, int shift) {
	    String result = "";
	    String c = input+"";
	    if(isLetter(c)) {
	    	result += c.compareTo("")+shift;
	    } else if (isNumber(c)) {
	        result += numberToLetter(Integer.parseInt(c)-shift);
	    }
	    return result;
	}
	
	private static char numberToLetter(int number) {
	    int modNumber = (number - 1) % 26; // Convert to 0-indexed and take mod 26
	    char letter = (char) ('A' + modNumber);
	    return letter;
	}
	
	private boolean isNumber(String input) {
	    try {
	        Double.parseDouble(input);
	        return true;
	    } catch (NumberFormatException e) {
	        return false;
	    }
	}
	
	public boolean isLetter(String c) {
		char letter = c.charAt(0);
	    return (letter >= 'a' && letter <= 'z') || (letter >= 'A' && letter <= 'Z');
	}
	
	private String charToUnicode(char c) {
		return "\\u" + Integer.toHexString(c | 0x10000).substring(1);
	}
	
	public static String unicodeEscapeToText(String input) {
	    String result = "";
	    int i = 0;
	    while (i < input.length()) {
	        if (input.charAt(i) == '\\' && i + 5 < input.length() && input.charAt(i+1) == 'u') {
	            int codePoint = Integer.parseInt(input.substring(i+2, i+6), 16);
	            result += (char) codePoint;
	            i += 6;
	        } else {
	            result += input.charAt(i);
	            i++;
	        }
	    }
	    return result;
	}

}
