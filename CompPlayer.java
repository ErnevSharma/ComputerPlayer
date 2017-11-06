import java.util.Arrays;
import java.util.Random;

public class CompPlayer {
	//instance variables
	private TicTacToe tr;
	private String mark;
	private String enemMark;
	private String[][] board;
	
	
	public CompPlayer(TicTacToe t, String m)
	{
		tr = t;
		mark = m;
		if(mark.equals("X"))
			enemMark = "O";
		else
			enemMark = "X";
		
		board = tr.getBoard();
		
		
	}
	
	public void showBoard()
	{
		System.out.println(tr);
	}
	
	public void makeMove()
	{
		
		for(int x = 0; x < 3; x++)
		{
			for(int y = 0; y < 3; y++)
				if(tr.getBoard()[x][y].equals(" ") && checkEverywhere(x,y,mark))
				{
				
					tr.set(x, y, mark);
					return;
				}
		}
		
		for(int x = 0; x < 3; x++)
		{
			for(int y = 0; y < 3; y++)
				if(tr.getBoard()[x][y].equals(" ") && checkEverywhere(x,y,enemMark))
				{
					
					tr.set(x, y, mark);
					return;
				}
		}
		
		
		makeRandomMove();
		
		
	}
	
	//keeps looking for a blank spot to place mark in
	private void makeRandomMove()
	{
		int x = (int)(Math.random() * 3);
		int y = (int)(Math.random() * 3);
		while(board[x][y].equals(" ") == false)
		{
			x = (int)(Math.random() * 3);
			y = (int)(Math.random() * 3);
			
		
		}
		
		tr.set(x, y, mark);
	}
	
	//calls the recursive method that checks the row
	private boolean checkEverywhere(int r, int c, String f)
	{
		for(int x = 1; x < 5; x+=2)
		{
			if(checkDir(r,c,f,x,true))
			{
				
				return true;
			}
		}
		
		
			if(r==c)
			{
				if(checkDir(r,c,f,4,true))
				{
					
					return true;
				}
			}
			
			if((r == 2 && c == 0) || (r == 0 && c == 2) || (r == 1 && c == 1))
			{
				if(checkDir(r,c,f,2,true))
				{
					return true;
				}
			}
		
		return false;
	}
	
	
	//recursively checks the current position
	//the point can only traverse if the point is on f
	//goes both ways utilizing the 8 directions a tic-tac-toe piece can go
	//returns true if both sides can reach the outside
	private boolean checkDir(int r, int c, String f, int d, boolean start)
	{
		if(r == 3 || c == 3 || r == -1 || c == -1)
		{
			return true;
		}
		else if(!board[r][c].equals(f) && !start)
		{
			return false;
		}
		else if(start)
		{
			if(d == 1)
			{
				return checkDir(r-1,c,f,-d,false)&&checkDir(r+1,c,f,d,false);
			}
			if(d == 2)
				return checkDir(r-1,c + 1,f,d,false)&&checkDir(r+1,c-1,f,-d,false);
			if(d == 3)
				return checkDir(r,c - 1,f,-d,false)&&checkDir(r,c + 1,f,d,false);
			else
				return checkDir(r+1,c+1,f,d,false)&&checkDir(r-1,c - 1,f,-d,false);
		}
		else
		{
			if(d < 0)
			{
				if(d == -1)
				{
					return checkDir(r-1,c,f,d,false);
				}
				if(d == -2)
					return checkDir(r+1,c-1,f,d,false);
				if(d == -3)
					return checkDir(r,c - 1,f,d,false);
				else
					
					return checkDir(r-1,c-1,f,d,false);
			}
			else
			{
				if(d == 1)
				{
					return checkDir(r+1,c,f,d,false);
				}
				if(d == 2)
					return checkDir(r-1,c + 1,f,d,false);
				if(d == 3)
					return checkDir(r,c + 1,f,d,false);
				else
					return checkDir(r+1,c+1,f,d,false);
			}
		}
	}
	
	
}
	

