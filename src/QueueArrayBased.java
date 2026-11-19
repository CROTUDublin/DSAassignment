
public class QueueArrayBased implements QueueInterface {
  //Changed to larger number for the maze
  private final int MAX_QUEUE = 10000; // maximum size of queue
  private Object[] items;
  private int front, back, count;
  
  public QueueArrayBased() {
    items = new Object[MAX_QUEUE];  
    front = 0;
    back = MAX_QUEUE-1; 
    count = 0;
  }  // end default constructor

  // queue operations:
  public boolean isEmpty() {
    return count == 0;
  }  // end isEmpty

  public boolean isFull() {
    return count == MAX_QUEUE;
  }  // end isFull
  
  public void enqueue(Object newItem) {
    if (!isFull()) {
      back = (back+1) % (MAX_QUEUE);
      items[back] = newItem;
      ++count;
    }
    else {
      throw new QueueException("QueueException on enqueue: "
                             + "Queue full");
    }  // end if
  }  // end enqueue

  public Object dequeue() throws QueueException {
    if (!isEmpty()) {
      // queue is not empty; remove front
      Object queueFront = items[front];
      front = (front+1) % (MAX_QUEUE);
      --count;
      return queueFront;
    }
    else {
      throw new QueueException("QueueException on dequeue: "+
                              "Queue empty");
    }   // end if
  }  // end dequeue

  public void dequeueAll() {
    items = new Object[MAX_QUEUE];  
    front = 0;
    back = MAX_QUEUE-1;
    count = 0;
  }  // end dequeueAll

  public Object peek() throws QueueException {
    if (!isEmpty()) {  
      // queue is not empty; retrieve front
      return items[front];
    }
    else {
      throw new QueueException("Queue exception on peek: " + 
                              "Queue empty");
    }  // end if
  }  // end peek

  //displayQueue method
  public void displayQueue(){
    //Need front and back clearly marked
    //Doing front left, back right
//print not println for horizontal
    System.out.print("Front --> " );

    //Making use of this classes front, back, count and items variables and MAX_QUEUE
    //count = jobs in the queue now
    //items = stores the jobs
    for (int i =0; i < count; i++) {
      //modulus required to circle back around
      //Start at front, go forward one
      int index = (front + i) % MAX_QUEUE;
      System.out.print("( " + items[index] + " ) "  );
      //Still need to mark the back, just close and print at the end

    }
    System.out.print(" <-- Back ");
  }
   
} // end QueueArrayBased