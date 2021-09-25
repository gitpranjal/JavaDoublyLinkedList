

class ModifiedLinkedList<AnyType> extends MyLinkedList{

    /*
    public int theSize;
    public int modCount = 0;
    public Node<AnyType> beginMarker;
    public Node<AnyType> endMarker;
    */

    /*
    public static class Node<AnyType>
    {
        public Node( AnyType d, Node<AnyType> p, Node<AnyType> n )
        {
            data = d; prev = p; next = n;
        }
        
        public AnyType data;
        public Node<AnyType>   prev;
        public Node<AnyType>   next;
    }
    */


    public Node<AnyType>removeFromBeginning()
    {
        if(theSize == 0)
        {
            return null;
        }
        Node<AnyType>nodeRemoved = beginMarker.next;

        if(theSize == 1)
        {
            beginMarker.next = endMarker;
            endMarker.prev = null;
            theSize --;
            return nodeRemoved;
        }

        beginMarker.next = beginMarker.next.next;
        beginMarker.next.prev = beginMarker;
        nodeRemoved.next = null;
        nodeRemoved.prev = null;
        theSize--;
        return nodeRemoved;
        
    }

    public Node<AnyType>removeFromEnd(){

        if(theSize == 0)
            return null;

        Node<AnyType>removedNode = endMarker.prev;

        if(theSize == 1)
        {
            beginMarker.next = endMarker;
            endMarker.prev = null;
            theSize--;
            return removedNode;
        }

        endMarker.prev = endMarker.prev.prev;
        endMarker.prev.next = endMarker;
        removedNode.prev = null;
        removedNode.next = null;
        theSize--;
        return removedNode;

    }

    public void insertNodeAtBeginning(Node<AnyType>newNode)
    {
        if(theSize == 0)
        {
            beginMarker.next = newNode;
            newNode.prev = beginMarker;
            endMarker.prev = newNode;
            newNode.next = endMarker;
            theSize++;
            return;
        }

        newNode.next = beginMarker.next;
        beginMarker.next = newNode;
        newNode.prev = beginMarker;
        newNode.next.prev = newNode;
        theSize++;
    }


    public void insertNodeAtEnd(Node<AnyType>newNode)
    {
        if(theSize == 0)
        {
            beginMarker.next = newNode;
            newNode.prev = beginMarker;
            endMarker.prev = newNode;
            newNode.next = endMarker;
            theSize++;
            return;
        }
        endMarker.prev.next = newNode;
        newNode.prev = endMarker.prev;
        endMarker.prev = newNode;
        newNode.next = endMarker;
        theSize++;
        
        
    }

    public void insertAtEnd(AnyType value)
    {
        Node<AnyType>newNode = new Node<>(value, null, null);
        this.insertNodeAtEnd(newNode);
    }

    public void printForwardBackward()
    {
        
        Node<AnyType>temp = beginMarker;
        
        
        System.out.println("Forward printing using next pointer");
        while(temp != null)
        {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
        System.out.println("\nReverse printing using prev pointer");
        temp = endMarker;
        while(temp != null)
        {
            System.out.print(temp.data + " ");
            temp = temp.prev;
        }
        System.out.println("");
    }

//########################################## Project1 Question1 Solutions ###################################
   

    public void swap(int index1, int index2)
    {
         /*
        Question 1a.
        receives two index positions as parameters, and swaps the nodes at
        these positions by changing the links, provided both positions are 
        within the current size
        */
       if(index1 >= theSize || index2 >= theSize || index1 < 0 || index2 < 0)
       {
           System.out.println("Invalid indices");
           throw new IndexOutOfBoundsException( "index1: " + index1 + "; index2: " + index2 );
          
       }
       Node<AnyType>n1 = beginMarker.next;
       Node<AnyType>n2 = beginMarker.next;
       Node<AnyType>temp1 = null;
       Node<AnyType>temp2 = null;

       for(int i = 0; i< index1; i++)  // Reaching the required nodes 
        n1= n1.next;

       for(int i = 0; i< index2; i++)
        n2 = n2.next;

       n1.prev.next = null;
       temp1 = n1.prev;
       n1.prev = null;

       n2.prev.next = null;
       temp2 = n2.prev;
       n2.prev = null;

       temp1.next = n2;
       n2.prev = temp1;

       n2.next.prev = null;
       temp1 = n2.next;
       n2.next = null;

       if(n1.next != null)
       {
        n2.next = n1.next;
        n1.next.prev = n2;
        n1.next = null;
       }
       else
       {
           // Case if the two nodes to be swapped are adjacent
           n2.next = n1;
           n1.prev = n2;
       }
       

       n1.next = temp1;
       temp1.prev = n1;

       if(temp2 != n1)
       {
        // case where two nodes to be swapped are not adjacent
        n1.prev = temp2;
        temp2.next = n1;
       }
          
        
    }

    public void shift(int steps)
    {
        /*
        Question 1b.
          receives an integer (positive or negative) and shifts the list this
        many positions forward (if positive) or backward (if negative).  
           1,2,3,4,5    shifted +2    3,4,5,1,2
           1,2,3,4,5    shifted -1    5,1,2,3,4
        */
       
        if(steps > 0)
        {
            while(steps > 0)
            {
                insertNodeAtEnd(removeFromBeginning());
                steps --;
            }
        }
        else
        {
            while(steps < 0)
            {
                insertNodeAtBeginning(removeFromEnd());
                steps ++;
            }
        }
        
    }
    public void erase(int index, int numElements)
    {
        /*
        Question 1c.
        receives an index position and number of elements as parameters, and
        removes elements beginning at the index position for the number of 
        elements specified, provided the index position is within the size
        and together with the number of elements does not exceed the size
        */
        if(index + numElements > theSize)
        {
            System.out.println("Size limit exceeded. Please enter lesser value for number of elements to be removed.");
            return;
        }
        Node<AnyType>current = beginMarker.next;  // At the head

        for(int i = 0; i<index; i++)
            current = current.next;

        while(numElements > 0)
        {
        
            current.prev.next = current.next;
            current.next.prev = current.prev;
            current.prev = null;
            Node<AnyType>temp = current;
            current = current.next;
            temp.next = null;
            numElements--;
            theSize--;
            modCount--;
        }
    }

public void insertList(ModifiedLinkedList<AnyType>lst, int index){

    theSize += lst.theSize;
    modCount += lst.modCount;

    if(index == 0)
    {
        /*
        Question 1d.
        receives another MyLinkedList and an index position as parameters, and 
        copies the list from the passed list into the list at the specified
        position, provided the index position does not exceed the size.
        */
        
        lst.endMarker.prev.next = beginMarker.next;
        beginMarker.next.prev = lst.endMarker.prev;
        beginMarker = lst.beginMarker;
        return;

    }

    Node<AnyType>n = beginMarker.next;
    while(index > 1)
    {
        n = n.next;
        index--;
    }

    Node<AnyType>temp = n.next;
    n.next = lst.beginMarker.next;
    n.next.prev = n;
    lst.endMarker.prev.next = temp;
    temp.prev = lst.endMarker.prev;

}
}
public class ModifyAndTestLinkedList {
    
    public static void main( String [ ] args )
    {
        ModifiedLinkedList<Integer> lst = new ModifiedLinkedList<>( );

        

        for( int i = 1; i < 6; i++ )
                lst.add( i );

        ModifiedLinkedList<Integer>lst2 = new ModifiedLinkedList<>();
        for(int i = 1; i < 6; i++)
            lst2.add(-1*i);
        
        /*
        for( int i = 20; i < 30; i++ )
                lst.add( 0, i );

        lst.remove( 0 );
        lst.remove( lst.size( ) - 1 );

       

        java.util.Iterator<Integer> itr = lst.iterator( );
        while( itr.hasNext( ) )
        {
                itr.next( );
                itr.remove( );
                System.out.println( lst );
        }
    */
    
    System.out.println("Orignal List");
    System.out.println(lst);

    
    
    System.out.println("Modified List after performed operation: ");
    lst.swap(0, 1);
    

    //lst.shift(-1);
    //lst.erase(1,4);
    //lst.insertList(lst2, 5);
    System.out.println(lst);

    }
}
