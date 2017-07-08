
public class Queue<QueueItem> 
{
	
	private class QueueNode
	{
		QueueItem Content;
		QueueNode Link;
	}
	//end class QueueNode
	
	private QueueNode Front, Rear;
	
	public Queue()
	{
		Front = new QueueNode();
		Rear = Front;
		Front.Link = null;
	}
	//end Queue constructor
	
	public void Enqueue(QueueItem QI)
	{
		QueueNode Temp;
		
		Temp = Rear;
		Rear = new QueueNode();
		Rear.Content = QI;
		Rear.Link = null;
		Temp.Link = Rear;
		
	}
	//end public method Enqueue
	
	public QueueItem Dequeue()
	{
		QueueItem QI;
		
		if(Front == Rear)
		{
			System.out.print("Queue Underflow!");
			System.exit(0);
		}
		//end if
		
		Front = Front.Link;
		QI = Front.Content;
		return QI;
		
	}
	//end public method Dequeue
	
	public void MakeEmpty()
	{
		
		Front.Link = null;
		Rear = Front;
		return;
		
	}
	//end public method MakeEmpty
	
	public boolean IsEmpty()
	{
		
		if (Front == Rear)
		{
			return true;
		}
		else
		{
			return false;
		}
		//end if
		
	}
	//end public method IsEmpty
	
	public int LengthQueue()
	{
		QueueNode Temp;
		int Count;
		
		Count = 0;
		Temp = Front;
		
		while(Temp.Link != null)
		{
			
			Count++;
			Temp = Temp.Link;
		
		}
		//end while
		
		return Count;
		
	}
	//end public method LengthQueue
	
	public QueueItem GetLastItem()
	{
		
		QueueItem QI;
		QI = Rear.Content;
		return QI;
		
	}
	
	public QueueItem GetFirstItem()
	{
		
		
	
		QueueItem QI;
		QI = Front.Link.Content;
		return QI;
		
	}
	
	

}
//end class Queue
