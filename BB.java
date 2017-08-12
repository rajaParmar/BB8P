class node implements Cloneable{
	private static int[][] goal={{1,2,3},{4,-1,5},{6,7,8}};


	public int[][] grid;

	public int cost;

	public char child_type;

	public node parent;
	
	public int blank[];
	public node(int[][] grid,char child_type,node parent){

		this.grid=grid;
		this.cost=outplace_count();
		this.child_type=child_type;
		this.parent=parent;
		//this.blank=blank;
		this.blank=find_blank();

		}

	public int[] find_blank(){
		int i=0,j=0;
		for (i=0;i<grid.length;i++){

			for (j=0;j<grid[i].length;j++){

				if(grid[i][j]==-1)
					break;
					
			}
		}
		return new int[] {i,j};
	}

	public node clone(){
		try{
			return (node)super.clone();
		}
		catch(Exception e){
			return null;
		}
	}
	
	public void print_grid(){
		for (int i=0;i<grid.length;i++){

			for (int j=0;j<grid[i].length;j++){

				System.out.print(this.grid[i][j]+" ");
			}
			System.out.println();
		}	
	}

	public int outplace_count(){
		int count=8;

		for(int i=0;i<3;i++){
			for(int j=0;j<3;j++){
				if(this.grid[i][j]!=-1)
					if(this.grid[i][j]!=goal[i][j])
						count--;
			}
		}return count;

	}

	public boolean is_valid_pos(int r,int col){
		if((r < 0 || r>2) || (col>2 || col<2))
			return false;

		return true;

	}



}


public class BB{




	public static void main(String args[]){
			
			int init_grid[][]={{1,7,3},{5,-1,4},{6,2,8}};

			node current=new node(init_grid,'x',null);



	}
}