package chessProject.Pieces;

import java.lang.reflect.Field;

public class Piece{
	private String displayName = "pawn";
	private int value = 0;
	private String pieceColor = "white";
	private int x = 0;
	private int y = 0;
	private String icon;
	private int currentDirection = 1;
	public Piece() {}
	
	public Piece(String newDisplayName, int value, String newPieceColor, int newX, int newY, String icon) {
		this.displayName = newDisplayName;
		this.pieceColor = newPieceColor;
		this.x = newX;
		this.y = newY;
		this.value = value;
		this.icon = setChessIconColor(icon.charAt(0), newPieceColor);
	}
	
	
	public Piece(String newDisplayName, int value, String newPieceColor, int newX, int newY, String icon, int Direction) {
		this.displayName = newDisplayName;
		this.pieceColor = newPieceColor;
		this.x = newX;
		this.y = newY;
		this.value = value;
		this.currentDirection = Direction;
		this.icon = setChessIconColor(icon.charAt(0), newPieceColor);
	}
	
	public String toString() {
		return icon + ":(" + x + "," + y + "):" + value;
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
	
	public int getValue() {
		return value;
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
	
	public void setstartColor(int value) {
		this.value = value;
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
	
	
	public int getCurrentDireciton() {
		return currentDirection;
	}
	
	public void swapCurrentDireciton() {
		currentDirection *= -1;
	}
	
}
