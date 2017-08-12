class node implements Cloneable{
	private static int[][] goal={{1,2,3},{4,-1,5},{6,7,8}};


	public int[][] grid;

	public int cost;

	public char child_type;

	public node parent;
	
	public int blank[];
	public node(int[][] grid,char child_type,node parent){

		this.grid=grid;
		outplace_count();
		this.child_type=child_type;
		this.parent=parent;
		this.blank=new int[2];
		//this.blank=blank;
		find_blank();

		}

	public void find_blank(){
		int i=0,j=0;
		for (i=0;i<grid.length;i++){

			for (j=0;j<grid[i].length;j++){

				if(grid[i][j]==-1){
					this.blank=new int[]{i,j};
					return;}
					
			}
		}
		 
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

	public void outplace_count(){
		int count=8;

		for(int i=0;i<3;i++){
			for(int j=0;j<3;j++){
				if(this.grid[i][j]!=-1)
					if(this.grid[i][j]!=goal[i][j])
						count--;
			}
		}this.cost= count;

	}

	public static boolean is_valid_pos(int r,int col){
		if((r < 0 || r>2) || (col>2 || col<0))
			return false;

		return true;

	}

	public void swap(int c1[],int c2[]){
		int temp=this.grid[c1[0]][c1[1]];
		this.grid[c1[0]][c1[1]]=this.grid[c2[0]][c2[1]];
		this.grid[c2[0]][c2[1]]=temp;

		this.find_blank();
		outplace_count();

	}

	public static boolean is_valid_child(char child,char parent){
		if((child=='l' && parent=='r')||(child=='r' && parent=='l'))
			return false;
		if((child=='u' && parent=='d')||(child=='d' && parent=='u'))
			return false;
		return true;
	}

}


public class BB{




	public static void main(String args[]){
			
			int init_grid[][]={{1,7,3},{5,-1,4},{6,2,8}};

			node current=new node(init_grid,'x',null);

			

			int row[]={0,0,-1,-1};

			int col[]={-1,-1,0,0};

			char child_type[]={'l','r','u','d'};

			node children[]=new node[4];
			while(current.cost!=0){
				current.print_grid();
				System.out.println((current.cost));
				for (int i=0;i<4;i++){

					int shift_r=current.blank[0]+row[i];
					int shift_col=current.blank[1]+col[i];
					//System.out.println(current.blank[0]+" "+current.blank[1]+" "+shift_r+" "+shift_col);
					if(node.is_valid_pos(shift_r,shift_col) && node.is_valid_child(child_type[i],current.child_type)){
						//System.out.println("Entered!");
						node temp=current.clone();
						
						temp.swap(temp.blank,new int[]{shift_r,shift_col});

						children[i]=temp.clone();
						children[i].parent=current;
						children[i].child_type=child_type[i];

						temp=null;
						
					}
					else children[i]=null;

					//find best child
				}
				int min=current.cost;int index=-1;
				for (int j=0;j<children.length;j++){
					// if(children[j]!=null &&  children[j].cost<min ){
					// 	index=j;
					// }
				System.out.println(children[j].child_type);
				children[j].print_grid();


				}
					
				if(index==-1)
				{
					System.out.println("Solution Not Found!");
					break;
				}
				else{


					current=children[index].clone();
				}


				

			}


	}
}