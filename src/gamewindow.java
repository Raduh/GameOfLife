import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;

class grila extends Canvas implements MouseListener,MouseMotionListener
{ 	
	Image im; 
	BufferedImage imag_buf;
	Graphics2D gr;
	int pozx,pozy,coordx,coordy;
	grila()
	{
		imag_buf=new  BufferedImage(620,410,BufferedImage.TYPE_INT_RGB);
		gr=imag_buf.createGraphics();
		gr.setColor(Color.gray);
		Rectangle2D.Float drept=new Rectangle2D.Float(10,5,600,400);
		gr.fill(drept);
		gr.setColor(Color.black);
		Line2D.Float linie;
		for(float i=10;i<=610;i+=10)
		{
			linie=new Line2D.Float(i,5,i,405);
			gr.draw(linie);			
		}
		for(float i=5;i<=400;i+=10)
		{	
			linie=new Line2D.Float(10,i,610,i);
			gr.draw(linie);
		}
		im=Toolkit.getDefaultToolkit().createImage(imag_buf.getSource());
		setSize(620,410);
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		initializeaza();
	}
	public void paint (Graphics ecr)
	{	
		ecr.drawImage(im,0,0,this);
	}
	
	class celula{
		public int x; public int y; boolean alive;
		celula(){x=0;y=0;alive=false;}
		celula(int a,int b, boolean life){x=a;y=b;alive=life;}
		public void reset(){
		if(alive==true)
		{gr.setColor(Color.gray);gr.fillRect(x+1,y+1,9,9);alive=false;im=Toolkit.getDefaultToolkit().createImage(imag_buf.getSource());
		repaint();}
		}}
		
	public void ClearMap(){	for(int i=0;i<40;i++)for(int j=0;j<60;j++){harta[i][j].reset();neighbors[i][j]=0;}live.ClearList();die.ClearList();}
	
	celula[][] harta=new celula[40][60];
	public void initializeaza(){
		
		live=new clist();
		die=new clist();
		nextlive=new clist();
		nextdie=new clist();
	//gr.translate(100,100);
	for(int k=0;k<40;k++)
		for(int p=0;p<60;p++)
			{harta[k][p]=new celula((10*p+10),(10*k+5),false);
			/*neighbors[k][p]=-1;*/}
			}
	
		/*for(int i=0;i<40;i++)
		for(int j=0;i<60;j++)
		{
			(harta[i][j]).x=(10*j+10);
			(harta[i][j]).y=(10*i+5);
			(harta[i][j]).alive=false;
		}}*/
	
	
/*int test;
	void print(){harta[2][3].x=0;test=harta[2][3].x;
		System.out.println(""+test);}*/
	celula temp=new celula();
	public void update(Graphics ecr){paint(ecr);}
	public void mouseClicked(MouseEvent e){/*System.out.println(e.getX()+" "+e.getY());*/}
	public void mouseEntered(MouseEvent e){}
	public void mouseExited(MouseEvent e){}
	public void mousePressed(MouseEvent e)
	{	//gr.translate(10, 5);
		
		pozx=e.getX();
		pozy=e.getY();
	if(((pozx<=610)&&(pozx>=10))&&((pozy<=405)&&(pozy>=5)))
	{	
		coordx=(pozx-10)/10;
		coordy=(pozy-5)/10;
		System.out.println(coordx+" "+coordy);
		temp=harta[coordy][coordx];
		if((harta[coordy][coordx]).alive)
			{gr.setColor(Color.gray);gr.fillRect((harta[coordy][coordx].x+1),(harta[coordy][coordx].y+1),9,9);harta[coordy][coordx].alive=false;}
		else
			{gr.setColor(Color.green);gr.fillRect((harta[coordy][coordx].x+1),(harta[coordy][coordx].y+1),9,9);harta[coordy][coordx].alive=true;}
	
	ModifyNeighbors(coordy,coordx);
	im=Toolkit.getDefaultToolkit().createImage(imag_buf.getSource());
	repaint();}
	}
	//boolean apasat=true;
	public void mouseReleased (MouseEvent e){}
	public void mouseDragged (MouseEvent e)
	{	//gr.translate(arg0, arg1)
		
		pozx=e.getX();
		pozy=e.getY();
	if(((pozx<=610)&&(pozx>=10))&&((pozy<=405)&&(pozy>=5)))
	{	
		coordx=(pozx-10)/10;
		coordy=(pozy-5)/10;
		if(harta[coordy][coordx]!=temp)
		{if((harta[coordy][coordx]).alive)
		{gr.setColor(Color.gray);gr.fillRect((harta[coordy][coordx].x+1),(harta[coordy][coordx].y+1),9,9);harta[coordy][coordx].alive=false;temp=harta[coordy][coordx];}
	else
		{gr.setColor(Color.green);gr.fillRect((harta[coordy][coordx].x+1),(harta[coordy][coordx].y+1),9,9);harta[coordy][coordx].alive=true;temp=harta[coordy][coordx];}
	ModifyNeighbors(coordy,coordx);}
	im=Toolkit.getDefaultToolkit().createImage(imag_buf.getSource());
	repaint();}
	}
	public void mouseMoved(MouseEvent e){}
	int[][] neighbors=new int[40][60];
	public void ModifyNeighbors(int r,int c)
	{	if((r>0)&&(r<39)&&(c>0)&&(c<59)){
		if (harta[r][c].alive==true)
	{
		neighbors[r-1][c-1]++;
		neighbors[r-1][c]++;
		neighbors[r-1][c+1]++;
		neighbors[r][c+1]++;
		neighbors[r+1][c+1]++;
		neighbors[r+1][c]++;
		neighbors[r+1][c-1]++;
		neighbors[r][c-1]++;
	}
	else
	{	
		neighbors[r-1][c-1]--;
		neighbors[r-1][c]--;
		neighbors[r-1][c+1]--;
		neighbors[r][c+1]--;
		neighbors[r+1][c+1]--;
		neighbors[r+1][c]--;
		neighbors[r+1][c-1]--;
		neighbors[r][c-1]--;
	}}
	else {
		if(harta[r][c].alive)
		{
			try{neighbors[r-1][c-1]++;}catch(Exception ex){}
			try{neighbors[r-1][c]++;}catch(Exception ex){}
			try{neighbors[r-1][c+1]++;}catch(Exception ex){}
			try{neighbors[r][c+1]++;}catch(Exception ex){}
			try{neighbors[r+1][c+1]++;}catch(Exception ex){}
			try{neighbors[r+1][c]++;}catch(Exception ex){}
			try{neighbors[r+1][c-1]++;}catch(Exception ex){}
			try{neighbors[r][c-1]++;}catch(Exception ex){}
		}
		else{
			try{neighbors[r-1][c-1]--;}catch(Exception ex){}
			try{neighbors[r-1][c]--;}catch(Exception ex){}
			try{neighbors[r-1][c+1]--;}catch(Exception ex){}
			try{neighbors[r][c+1]--;}catch(Exception ex){}
			try{neighbors[r+1][c+1]--;}catch(Exception ex){}
			try{neighbors[r+1][c]--;}catch(Exception ex){}
			try{neighbors[r+1][c-1]--;}catch(Exception ex){}
			try{neighbors[r][c-1]--;}catch(Exception ex){}
		}
	}}
	int generations=0;
	clist live, die, nextlive, nextdie;
	public void UpdateGens(){
		int tempx=0,tempy=0;
		live.ClearList();die.ClearList();
		//live.MakeNewNode(1,3);
		//live.MakeNewNode(19,30);
		for(int i=0;i<40;i++)
			for(int j=0;j<60;j++)
				switch(neighbors[i][j])
				{
				//case -1: break; 
				case 0: if(harta[i][j].alive) die.MakeNewNode(j, i);break;
				case 1: die.MakeNewNode(j, i);break;
				case 2: if(harta[i][j].alive) live.MakeNewNode(j, i);break;
				case 3: live.MakeNewNode(j, i);break;
				default: die.MakeNewNode(j, i);break;
				}
		
		//nextlive.TransferList(live);nextdie.TransferList(die);
		while(live.HasNodes())
		{	
			if(live.list_h!=null){live.temp=live.list_h;tempx=live.temp.x;tempy=live.temp.y;
			//System.out.println(tempx + " " +tempy);
			if(!harta[tempy][tempx].alive){gr.setColor(Color.green);gr.fillRect((harta[tempy][tempx].x+1),(harta[tempy][tempx].y+1),9,9);harta[tempy][tempx].alive=true;ModifyNeighbors(tempy,tempx);}
			live.list_h=live.list_h.next;
			if(live.list_h==null)
				live.list_t=null;}
			im=Toolkit.getDefaultToolkit().createImage(imag_buf.getSource());
			repaint();
		}
		while(die.HasNodes())
		{
			if(die.list_h!=null){die.temp=die.list_h;tempx=die.temp.x;tempy=die.temp.y;
			if(harta[tempy][tempx].alive){gr.setColor(Color.gray);gr.fillRect((harta[tempy][tempx].x+1),(harta[tempy][tempx].y+1),9,9);harta[tempy][tempx].alive=false;ModifyNeighbors(tempy,tempx);}
			die.list_h=die.list_h.next;
			if(die.list_h==null)
				die.list_t=null;}
			im=Toolkit.getDefaultToolkit().createImage(imag_buf.getSource());
			repaint();
		}
		generations++;
	
	}
	public void placeCell(int c, int r)
	{	harta[r][c].alive=true;
	gr.setColor(Color.green);
	gr.fillRect((harta[r][c].x+1),(harta[r][c].y+1),9,9);
		ModifyNeighbors(r,c);
	}
	
	public void placeGlider(){
		placeCell(17,26);
		placeCell(18,27);
		placeCell(19,27);
		placeCell(19,26);
		placeCell(19,25);
		im=Toolkit.getDefaultToolkit().createImage(imag_buf.getSource());
		repaint();
	}
	public void placeGrenade(){
		placeCell(24,19);
		placeCell(24,18);
		placeCell(25,18);
		placeCell(26,18);
		placeCell(26,19);
		placeCell(25,20);
		placeCell(25,17);
		im=Toolkit.getDefaultToolkit().createImage(imag_buf.getSource());
		repaint();
	}
	public void placeBomb(){
		placeCell(24,18);
		placeCell(26,18);
		placeCell(26,17);
		placeCell(26,16);
		placeCell(26,15);
		placeCell(26,14);
		placeCell(24,14);
		placeCell(22,14);
		placeCell(22,18);
		placeCell(22,17);
		placeCell(22,16);
		placeCell(22,15);
		im=Toolkit.getDefaultToolkit().createImage(imag_buf.getSource());
		repaint();
		
	}
	public void placeSpaceship(){
		placeCell(22,19);
		placeCell(25,19);
		placeCell(22,17);
		placeCell(26,18);
		placeCell(26,17);
		placeCell(26,16);
		placeCell(25,16);
		placeCell(24,16);
		placeCell(23,16);
		im=Toolkit.getDefaultToolkit().createImage(imag_buf.getSource());
		repaint();
	}
	public void placeGosperGliderGun()
	{	
		placeCell(9,8);
		placeCell(10,8);
		placeCell(10,9);
		placeCell(9,9);
		placeCell(18,8);
		placeCell(19,8);
		placeCell(19,9);
		placeCell(18,10);
		placeCell(17,10);
		placeCell(17,9);
		placeCell(25,12);
		placeCell(25,11);
		placeCell(27,11);
		placeCell(26,10);
		placeCell(25,10);
		placeCell(33,7);
		placeCell(33,6);
		placeCell(32,6);
		placeCell(31,7);
		placeCell(31,8);
		placeCell(43,6);
		placeCell(44,7);
		placeCell(43,7);
		placeCell(44,6);
		placeCell(44,15);
		placeCell(44,14);
		placeCell(46,14);
		placeCell(45,13);
		placeCell(44,13);
		placeCell(33,18);
		placeCell(34,18);
		placeCell(35,18);
		placeCell(33,19);
		placeCell(34,20);
		placeCell(32,8);
		im=Toolkit.getDefaultToolkit().createImage(imag_buf.getSource());
		repaint();
	}
	public void placeTumbler()
	{
	placeCell(28,17);
	placeCell(28,18);
	placeCell(28,19);
	placeCell(27,19);
	placeCell(26,18);
	placeCell(26,17);
	placeCell(26,16);
	placeCell(27,15);
	placeCell(27,14);
	placeCell(26,14);
	placeCell(22,17);
	placeCell(22,18);
	placeCell(22,19);
	placeCell(23,19);
	placeCell(24,18);
	placeCell(24,17);
	placeCell(24,16);
	placeCell(24,15);
	placeCell(23,15);
	placeCell(23,14);
	placeCell(24,14);
	placeCell(26,15);
	im=Toolkit.getDefaultToolkit().createImage(imag_buf.getSource());
	repaint();
	}
}

public class gamewindow extends JFrame implements ActionListener
{	
	grila careu=new grila(); JButton start;JLabel generations;
	JComboBox speeds,models;
	public gamewindow(String titlu)
	{	
		super(titlu);
		setSize(700,500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container x=getContentPane();
		x.setLayout(new FlowLayout());
		JMenuBar bara=new JMenuBar();
		JMenu about=new JMenu("About");
		JMenuItem item11=new JMenuItem("About");item11.addActionListener(this);
		about.add(item11);bara.add(about);x.add(bara);
		x.add(careu);	
		String[] model_list={"None","Glider","Grenade","Bomb","Spaceship","Tumbler","Gosper Glider Gun"};
		models=new JComboBox(model_list);models.setName("models");models.addActionListener(this);
		JButton next=new JButton("Next");next.addActionListener(this);
		start=new JButton("Start");start.addActionListener(this);
		JButton clear=new JButton("Clear");clear.addActionListener(this);
		String[] speed_list={"Very Slow","Slow","Fast","Very fast", "Crazy","Maximum"};
		speeds=new JComboBox(speed_list);speeds.addActionListener(this);
		speeds.setName("speeds"); speeds.setSelectedIndex(1);
		JButton quit=new JButton("Quit");quit.addActionListener(this);
		generations=new JLabel("Generations: 0");
		x.add(models);x.add(next);x.add(start);x.add(clear);x.add(quit);x.add(speeds);x.add(generations);
		setResizable(false);
		setVisible(true);
	}
	boolean continuare=true;
	int speed=50;
	public class thread_extins extends Thread{
	//int viteza;
	//thread_extins(int viteza){this.viteza=viteza;}
	public void run(){while(continuare){careu.UpdateGens();generations.setText("Generations: "+careu.generations);try{sleep(speed*10,1);}catch(Exception random){}}
	}
	}
	
	public void actionPerformed(ActionEvent e)
	{ 	//JComboBox item_apasat=(JComboBox)e.getSource();
		String optiune=e.getActionCommand();
		String mesaj=new String("This amazing version of the Conway's Game of Life was designed, implemented and debugged by raduh on the\n 12th of March 2013.");
		if (optiune.compareTo("About")==0) JOptionPane.showMessageDialog(this, mesaj);
		if (optiune.compareTo("Clear")==0) {careu.ClearMap();careu.generations=0;generations.setText("Generations: "+careu.generations);}
		if (optiune.compareTo("Quit")==0) System.exit(0);
		if (optiune.compareTo("Start")==0)
		{	continuare=true;
			start.setText("Stop");
			thread_extins fir_de_exec=new thread_extins();
			fir_de_exec.start();
		}
		if (optiune.compareTo("Stop")==0) {start.setText("Start");continuare=false;}
		if (optiune.compareTo("Next")==0) {careu.UpdateGens();generations.setText("Generations: "+careu.generations);}
		
		if((speeds)==(e.getSource())){
			if(speeds.getSelectedIndex()==0) speed=100;
			if(speeds.getSelectedIndex()==1) speed=50;
			if(speeds.getSelectedIndex()==2) speed=10;
			if(speeds.getSelectedIndex()==3) speed=5;
			if(speeds.getSelectedIndex()==4) speed=1;
			if(speeds.getSelectedIndex()==5) speed=0;}
		
		if((models)==(e.getSource())){
			if(models.getSelectedIndex()==1) careu.placeGlider();
			if(models.getSelectedIndex()==2) careu.placeGrenade();
			if(models.getSelectedIndex()==3) careu.placeBomb();
			if(models.getSelectedIndex()==4) careu.placeSpaceship();
			if(models.getSelectedIndex()==5) careu.placeTumbler();
			if(models.getSelectedIndex()==6) careu.placeGosperGliderGun();
			
		}
		
}
}
	



