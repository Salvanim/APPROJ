package chessProject.Pieces;

import chessProject.Runner;

public class Piece extends Runner{
	private String displayName = null;
	private String startColor = null;
	private String pieceColor = null;
	private int x = 0;
	private int y = 0;
	public Piece(String newDisplayName, String newStartColor, String newPieceColor, int newX, int newY) {
		this.displayName = newDisplayName;
		this.startColor = newStartColor;
		this.pieceColor = newPieceColor;
		this.x = newX;
		this.y = newY;
	}
}
