class Board{
	int[][] board = new int[8][8];
	int row = 0;

	void checkDeadSpace(int checkRow){
		for(int i=0; i<checkRow; i++){
			for(int j=0; j<8; j++){
				if(board[i][j] == 9){
					//縦をdeadSpaceに
					board[checkRow][j] += 1;

					if(j - checkRow + i >= 0){
						//左斜め下をdeadSpaceに
						board[checkRow][j-checkRow+i] += 1;
					}

					if(j + checkRow -i <= 7){
						//右斜め下をdeadSpaceに
						board[checkRow][j+checkRow-i] += 1;
					}
				}
			}
		}

	}

	void clearDeadSpace(int row){
		for(int i=0; i<8; i++){
			this.board[row][i] = 0;
		}
	}


	void printBoard(){
		System.out.println("+---+---+---+---+---+---+---+---+");
		for(int i=0; i<8; i++){
			for(int j=0; j<8; j++){
				System.out.print("| "+board[i][j]+" ");
			}
			System.out.println("|");
			System.out.println("+---+---+---+---+---+---+---+---+");
		}
		System.out.println("");
	}

	int putQueen(){
		for(int i=0; i<8; i++){
			if(this.board[this.row][i] == 0){
				this.board[this.row][i] = 9;
				this.row ++;
				return 0;
			}
		}
		return -1;
	}


	int reputQueen(){
		this.clearDeadSpace(this.row);
		//一つ前の行に戻って
		this.row --;
		//Queenを探して
		for(int i=0; i<8; i++){
			//見つけたら
			if(this.board[this.row][i] == 9){
				//ここには置けない判定。
				this.board[this.row][i] = -1;
				break;
			}
		}

		//改めて置き直す
		int result = this.putQueen();

		//もう置くところがなかったら
		if(result == -1){
			//さらに1つ戻って置き直す
			this.reputQueen();
		}
		return 0;


	}

}


class EightQueen{

	public static void main(String[] args){
		Board board = new Board();
		while(board.row != 8){
			board.checkDeadSpace(board.row);
			int result = board.putQueen();
			board.printBoard();
			if(result == -1){
				System.out.println("failed...");
				board.reputQueen();
			}else{
				System.out.println("success to put Row "+board.row);
			}
		}

		/*
		board.board[1][5] = 9;
		board.checkDeadSpace(2);
		board.checkDeadSpace(3);
		board.checkDeadSpace(4);
		board.checkDeadSpace(5);
		board.checkDeadSpace(6);
		board.checkDeadSpace(7);
		board.printBoard();
		*/
	}

}
