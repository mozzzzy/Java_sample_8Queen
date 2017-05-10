class Board{
	//チェスのボード (8 * 8)
	int[][] board = new int[8][8];	//Queenが置けるマスは 0
					//Queenが置けないマスは 0より大きく9より小さい数値
					//Queueが置いてあるマスは 9を入れる
					//Queenが置けるけど、後々積んでしまうマスは-1を入れる

	//次にQueenを置くべき行
	int row = 0;

	//checkRow行目のどのマスが既に使えなくなっているのかを調べるmethod
	void checkDeadSpace(int checkRow){
		//checkRowより前の各行について
		for(int i=0; i<checkRow; i++){
			//各列について
			for(int j=0; j<8; j++){
				//もしQueen(9で表現) を見つけたら
				if(board[i][j] == 9){
					//checkRow行目のj列は既にこのQueenの移動範囲に含まれているので、0より大きい値に変更
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

	//row行目の各列を全て0に戻すmethod
	void clearDeadSpace(int row){
		for(int i=0; i<8; i++){
			this.board[row][i] = 0;
		}
	}


	//ただチェス盤を出力するだけのメソッド
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

	//Queenを置くメソッド
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

	//一つ前の行に戻って、Queenを置き直すメソッド
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
	}

}
