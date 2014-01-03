
public class clist 
{
	public class Node
	{	
		int x,y;
		Node next;
	}
	
	Node node, list_h, list_t,temp;
	
	public clist(){list_h=list_t=null;}
	public void MakeNewNode(int n1, int n2)
	{	
		node=new Node();
		node.x=n1;
		node.y=n2;
		if(list_h==null)
			list_h=node;
		else
			list_t.next=node;
		list_t=node;
		list_t.next=null;
	}
	public void ClearList()
	{
		while(list_h!=null)
			list_h=list_h.next;
	}
	public void TransferList(clist list2)
	{
		list2.ClearList();
		list2.list_h=list_h;
		list2.list_t=list_t;
		list_h=null;
		list_t=null;
	}
	public boolean HasNodes()
	{	if(list_h!=null) return true;
	else return false;
	}
	public void GetNode(int c, int r)
	{	
		if(list_h!=null)
		{	//??node=list_h;??
			c=list_h.x;
			r=list_h.y;
			list_h=list_h.next;
			if(list_h==null)
				list_t=null;
		}
	}
	public void DisplayList()
	{
		node=list_h;
		while(node!=null)
		{	
			System.out.println("x: "+node.x+",y: "+node.y);
			node=node.next;
		}
	
	}
}
