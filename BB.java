class node {
	private static int[][] goal={{1,2,3},{8,-1,4},{7,6,5}};


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


	public void print_node(){
		this.print_grid();
		System.out.println(this.cost+" "+this.child_type+" "+this.parent+" "+this.blank[0]+","+this.blank[1]);
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
			int dest[][]=new int[3][3];
			for(int i=0;i<3;i++){
				for(int j=0;j<3;j++){
					dest[i][j]=this.grid[i][j];
				}
			}			
			node temp=new node(dest,this.child_type,this.parent);
			return temp;

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
		int count=0;

		for(int i=0;i<3;i++){
			for(int j=0;j<3;j++){
				if(this.grid[i][j]!=-1)
					if(this.grid[i][j]!=goal[i][j])
						count++;
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
			
			int init_grid[][]={{6,2,3},{1,8,4},{7,-1,5}};

			node current=new node(init_grid,'x',null);

			

			int row[]={0,0,-1,1};

			int col[]={-1,1,0,0};

			char child_type[]={'l','r','u','d'};

			node children[]=new node[4];
			while(current.cost!=0){
				//current.print_grid();
				//System.out.println((current.cost));
				for (int i=0;i<4;i++){

					int shift_r=current.blank[0]+row[i];
					int shift_col=current.blank[1]+col[i];
					//System.out.println(current.blank[0]+" "+current.blank[1]+" "+shift_r+" "+shift_col);
					if(node.is_valid_pos(shift_r,shift_col) && node.is_valid_child(child_type[i],current.child_type)){
						//System.out.println("Entered!");
						node temp=current.clone();
						//System.out.print(temp);
						//System.out.print(current);
						//temp.print_node();
						temp.swap(temp.blank,new int[]{shift_r,shift_col});
						//.temp.print_node();
						children[i]=temp.clone();
						//current.print_node();
						children[i].parent=current;
						children[i].child_type=child_type[i];
						//children[i].print_node();
						temp=null;
						
					}
					else children[i]=null;

					//find best child
				}
				int min=current.cost;int index=-1;
				for (int j=0;j<children.length;j++){
					if(children[j]!=null &&  children[j].cost<min ){
						index=j;
						 //children[j].print_node();

					}

				}


				if(index==-1){
					for (int j=0;j<children.length;j++){
					if(children[j]!=null &&  children[j].cost<=min ){
						index=j;
						 //children[j].print_node();

					}

				}
				}
				current.print_node();	
				if(index==-1)
				{
					System.out.println("Solution Not Found!");
					return;
					
				}
				else{


					current=children[index].clone();
				}


				

			}
			System.out.println("Solution Found!");
			//current.print_node();


			while(current.child_type!='x'){
				System.out.println(current.child_type);
				current=current.parent;
			}

	}
}