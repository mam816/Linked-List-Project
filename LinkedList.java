import java.io.*;
import java.util.*;

public class LinkedList<T>
{
	private Node<T> head;  // pointer to the front (first) element of the list

	public LinkedList()
	{
		head = null; // compiler does this anyway. just for emphasis
	}

	// LOAD LINKED LIST FROM INCOMING FILE
	@SuppressWarnings("unchecked")
	public LinkedList( String fileName, boolean orderedFlag )
	{	head = null;
		try
		{
			BufferedReader infile = new BufferedReader( new FileReader( fileName ) );
			while ( infile.ready() )
			{
				if (orderedFlag)
					insertInOrder( (T)infile.readLine() );  // WILL INSERT EACH ELEM INTO IT'S SORTED POSITION
				else
					insertAtTail( (T)infile.readLine() );  // TACK EVERY NEWELEM ONTO END OF LIST. ORIGINAL ORDER PRESERVED
			}
			infile.close();
		}
		catch( Exception e )
		{
			System.out.println( "FATAL ERROR CAUGHT IN C'TOR: " + e );
			System.exit(0);
		}
	}

	//-------------------------------------------------------------

	// inserts new elem at front of list - pushing old elements back one place

	public void insertAtFront(T data)
	{
		head = new Node<T>(data,head);
	}

	// we use toString as our print

	public String toString()
	{
		String toString = "";

		for (Node curr = head; curr != null; curr = curr.next)
		{
			toString += curr.data;		// WE ASSUME OUR T TYPE HAS toString() DEFINED
			if (curr.next != null)
				toString += " ";
		}

		return toString;
	}

	// ########################## Y O U   W R I T E    T H E S E    M E T H O D S ########################


  @SuppressWarnings("unchecked")
	public int size() // OF COURSE MORE EFFICIENT to KEEP COUNTER BUT YOU  MUST WRITE LOOP
	{
		//return 0; // YOUR CODE HERE
		int count = 0;
		Node <T> curr = head;
	    while(curr != null)
		{
			curr = curr.next;
			count++;
		}
		return count;
	}

	public boolean empty()
	{
		//return false;  // YOUR CODE HERE
		return (size()==0);
  	}

	public boolean contains( T key )
	{
		//return false;  // YOUR CODE HERE
		return search(key) !=null;
	}

   @SuppressWarnings("unchecked")
	public Node<T> search( T key )
	{
		//return null;  // YOUR CODE HERE
		Node<T> curr = head;
		while (curr != null)
		{
			 if(curr.data.equals(key))
			 {
				 return curr;
			 }
			 else
			 {
				 curr = curr.next;
			 }	 
	}
return null;
	}

	// TACK A NEW NODE (CABOOSE) ONTO THE END OF THE LIST
   @SuppressWarnings("unchecked")
	public void insertAtTail(T data)
	{
		 if(size() == 0)
		{
			insertAtFront(data);
			return;
		}
		  Node <T> curr = head;
		while(curr !=null && curr.next != null)
		{
		curr = curr.next;
		}
		 curr.next = new Node<T>(data,null);
		// YOUR CODE HERE
	}

	@SuppressWarnings("unchecked")  //CAST TO COMPARABLE THROWS WARNING
	public void insertInOrder(T  data)
	{
  		// YOUR CODE HERE
     // Node <T> curr = this.head;    
      //Comparable cData = (Comparable)data; 
      //int result = cData.compareTo(curr.data);
     
      if(size() == 0 )//|| result < 0)
        {
         insertAtFront(data);
         return;
        }
        
      Node <T> curr = this.head;
      Comparable cData = (Comparable)data; 
      int result = cData.compareTo(curr.data);
      if(result > 0)
         {
            while(result > 1 && curr.next != null)
                 {
                  result = cData.compareTo(curr.next.data);
                  if(result < 0)
                    {
                     curr.next = new Node<T>(data, curr.next);
                     return;
                    }
                  curr = curr.next;
                  result = cData.compareTo(curr.data);
                 }
                 if(curr.next == null)
                   {
                    curr.next = new Node<T>(data,null);
                    return;
                   }
                 if(result == 1)
                   {
                    result = cData.compareTo(curr.next.data);
                    while(result >= 1)
                         {
                          curr = curr.next;
                          result = cData.compareTo(curr.next.data);
                         }
                   }
                 curr.next = new Node<T>(data, curr.next);
                 return;
                   }
      if(result < 0)
         {
           insertAtFront(data);
           return;
         }
         } 

  @SuppressWarnings("unchecked")
	public boolean remove(T key)
	{
      Node<T> curr = head;
      if(size() == 0)
        {
         return false;
        }
      if(curr.data.equals(key))
        {
          head = curr.next;
          curr = head;
          return true;
        }
      while(curr != null && curr.next != null)
             {
              if(curr.next.data.equals(key))
                {
                curr.next = curr.next.next;
                return true;
                } 
              curr = curr.next;
	          }
          return false;
   }
   
   @SuppressWarnings("unchecked")
	public boolean removeAtTail()	// RETURNS TRUE IF THERE WAS NODE TO REMOVE
	{
      Node <T> curr = head;
      if(size() == 0)
      {
		return false; 
      }
      if(size() == 1)
      {
       removeAtFront();
       return true;
      }
      while(curr.next.next != null)
           {
            curr = curr.next;
           }
           curr.next = null;
           return true;
	}

   @SuppressWarnings("unchecked")
	public boolean removeAtFront() // RETURNS TRUE IF THERE WAS NODE TO REMOVE
	{
      Node <T> curr = head;
      if(size() == 0)
        {
         return false;
        }
      if(size() == 1)
        {
         head.next = null;
         head = null;
         return true;
        }
      head = curr.next;
      return true;
		//return false; // YOUR CODE HERE
	}
   
   @SuppressWarnings("unchecked")
	public LinkedList<T> union( LinkedList<T> other )
	{
	  LinkedList<T> union = new LinkedList<T>();
     Node<T> curr = other.head;
     Node<T> curr2 = this.head;
     Node<T> curr3 = union.head;
     while(curr != null)
    // for(int x = 0; x < this.size(); x++)
		     {
             //Node<T> curr = this.head;
				 //union.add();
             union.insertAtTail(curr.data);
             curr = curr.next;
  			  }
     while(curr2 != null)
          {
            if(union.search(curr2.data) == null)
              {
               union.insertInOrder(curr2.data);
              }
            curr2 = curr2.next;
  		    }		
		return union;
	}
   
   @SuppressWarnings("unchecked")
	public LinkedList<T> inter( LinkedList<T> other )
	{
		LinkedList<T> inter = new LinkedList<T>();

		Node<T> curr = other.head;
      Node<T> curr2 = this.head;
      Node<T> curr3 = inter.head;
      while(curr != null && curr.next != null)
          {
            if(this.contains(curr.data))
              {
              inter.head = new Node<T>(curr.data,null);
               
              }
            curr = curr.next;
          }			
		return inter;
	}
   
   @SuppressWarnings("unchecked")
	public LinkedList<T> diff( LinkedList<T> other )
	{
		LinkedList<T> diff = new LinkedList<T>();
      
      Node<T> curr = other.head;
      Node<T> curr2 = this.head;	
      while(curr2 != null && curr.data != null)
           {
            if(other.search(curr2.data) == null && curr2.data != null)
              {
               diff.insertAtTail(curr2.data);
               
              }
              curr2 = curr2.next;
              
           }  
		return diff;
	}
   
   
	public LinkedList<T> xor( LinkedList<T> other )
	{
		return this.union(other).diff(this.inter(other)); 

	}

} //END LINKEDLIST CLASS


class Node<T>
{
   T data;
   Node next;

   Node()
  {
    this( null, null );
  }

   Node(T data)
  {
    this( data, null );
  }

   Node(T data, Node next)
  {
    this.data = data;
	 this.next = next;
  }

  public String toString()
  {
	  return ""+this.data;
  } 
	 
} //EOF
// A D D   N O D E   C L A S S  D O W N   H E R E 
// R E M O V E  A L L  P U B L I C  &  P R I V A T E (except toString)
// R E M O V E  S E T T E R S  &  G E T T E R S 
// M A K E  T O  S T R I N G  P U B L I C