/**
*  Project 4 Fall2017 "Pitcher Stats Database Using Binary Tree"
*
*  Names: Robert Burch-Lau & Samuel Tregea
*
*  Professor: Dennis Venabule
*
*  Due Date: 29 November 2017
**/

import java.io.*;


// The implementation of most methods in this file is left as a student
// exercise from Section 9.5 of "Data Structures and Other Objects Using Java"


/******************************************************************************
 * This class is a homework assignment;
 * An <CODE>TreeBag</CODE> is a collection of int numbers.
 *
 * <dl><dt><b>Limitations:</b> <dd>
 *   Beyond <CODE>Integer.MAX_VALUE</CODE> elements, <CODE>countOccurrences</CODE>,
 *   and <CODE>size</CODE> are wrong.
 *
 * <dt><b>Note:</b><dd>
 *   This file contains only blank implementations ("stubs")
 *   because this is a Programming Project for my students.
 *
 * @version
 *   Jan 24, 2016
 ******************************************************************************/
public class TreeBag<E extends Comparable> implements Cloneable
{
   // The Term E extends Comparable is letting the compiler know that any type
   // used to instantiate E must implement Comparable. i. e. that means that whatever
   // type E is must have a compareTo method so that elements can be compared against one another
   // This is required becuase we are doing comparisons in our methods


   // Invariant of the TreeBag class:
   //   1. The elements in the bag are stored in a binary search tree.
   //   2. The instance variable root is a reference to the root of the
   //      binary search tree (or null for an empty tree).
   private BTNode<E> root;


   /**
    * Insert a new element into this bag.
    * @param <CODE>element</CODE>
    *   the new element that is being inserted
    * <dt><b>Postcondition:</b><dd>
    *   A new copy of the element has been added to this bag.
    * @exception OutOfMemoryError
    *   Indicates insufficient memory a new BTNode.
    **/
   public void add(E element)
   {
      BTNode<E> cursor;
      boolean bool;

      if (root == null)
         root = new BTNode<E>(element, null, null);
      else
      {
         bool = false;
         cursor = root;

         while(!bool)
         {
            if(element.compareTo(cursor.getData()) < 0)
            {
               if(cursor.getLeft() == null)
               {
                  cursor.setLeft(new BTNode<E>(element, null, null));
                  bool = true;
               }
               else
                  cursor = cursor.getLeft();
            }
            else
            {
               if(cursor.getRight() == null)
               {
                  cursor.setRight(new BTNode<E>(element, null, null));
                  bool = true;
               }
               else
                  cursor = cursor.getRight();
            }
         }//End while        
      }//Else
   }//Add()

   /**
    * Retrieve location of a specified element from this bag.
    * @param <CODE>target</CODE>
    *   the element to locate in the bag
    * @return
    *  the return value is a reference to the found element in the tree
    * <dt><b>Postcondition:</b><dd>
    *   If <CODE>target</CODE> was found in the bag, then method returns
    *   a reference to a comparable element. If the target was not found then
    *   the method returns null.
    *   The bag remains unchanged.
    **/
   public E retrieve(E target)
   {
      BTNode<E> cursor = root;

      while(cursor != null)
      {
         if(target.compareTo(cursor.getData()) == 0)
            return cursor.getData();

         else if(target.compareTo(cursor.getData()) < 0)
            cursor = cursor.getLeft();

         else if(target.compareTo(cursor.getData()) > 0)
            cursor = cursor.getRight();
      }

      return null;
   }


   /**
    * Remove one copy of a specified element from this bag.
    * @param <CODE>target</CODE>
    *   the element to remove from the bag
    * <dt><b>Postcondition:</b><dd>
    *   If <CODE>target</CODE> was found in the bag, then one copy of
    *   <CODE>target</CODE> has been removed and the method returns true.
    *   Otherwise the bag remains unchanged and the method returns false.
    **/
   public boolean remove(E target)
   {
      BTNode<E> cursor = root;
      BTNode<E> parentOfCursor = null;

      boolean done = false;

      if (root == null)
         return false;

      while (!done)
      {

         if(target.compareTo(cursor.getData()) == 0)
            done = true;
         else if(target.compareTo(cursor.getData()) < 0)
         {
            parentOfCursor = cursor;
            cursor = cursor.getLeft();
         }
         else if(target.compareTo(cursor.getData()) > 0)
         {
            parentOfCursor = cursor;
            cursor = cursor.getRight();
         }
      }//while

      //case 1
      if (cursor == null)
         return false;

         //case 2
      else if (cursor.getLeft() == null && parentOfCursor == null)
      {
         root = root.getRight();
         cursor = null;
         return true;
      }

      //case3
      else if(parentOfCursor != null && cursor.getLeft() == null)
      {
         if(cursor == parentOfCursor.getLeft())
         {
            parentOfCursor.setLeft(cursor.getRight());
         }
         else
         {
            parentOfCursor.setRight(cursor.getRight());
         }

         cursor = null;
         return true;
      }//Case3 else-if 

      //case4
      else if(cursor.getLeft() != null)
      {
         cursor.setData(cursor.getLeft().getRightmostData());
         cursor.setLeft(cursor.getLeft().removeRightmost());
      }
      return done;
   }

   /**
    * Displays the entire tree of Node elements in a order specified
    * by the elements compareTo method
    *
    * @param
    *   none
    * <dt><b>Postcondition:</b><dd>
    *   Outputs all elements in the tree to Screen.
    *   Does not change the structure
    **/
   public void display()
   {
      if(root == null)
         System.out.println("The bag is empty");
      else
      {
         /*//Prints tree in pre-order traversal
         System.out.println("Pre-order: ");
         root.preorderPrint();*/

         //Prints tree in in-order traversal
         System.out.println("In-order: ");
         root.inorderPrint();
         
         /*//Prints tree in post-order traversal
         System.out.println("Post-order");
         root.postorderPrint();*/
      }
   }

   /**
    * Displays the entire tree of Node elements using the
    * built in print method of BTNode
    * which displays the entire tree in tree format
    *
    * @param
    *   none
    * <dt><b>Postcondition:</b><dd>
    *   Outputs all elements in the tree to Screen.
    *   Does not change the structure
    **/
   public void displayAsTree() {
      root.print(0);
   }



   /**
    * Generate a copy of this bag.
    * @param - none
    * @return
    *   The return value is a copy of this bag. Subsequent changes to the
    *   copy will not affect the original, nor vice versa. Note that the return
    *   value must be type cast to an <CODE>TreeBag</CODE> before it can be used.
    * @exception OutOfMemoryError
    *   Indicates insufficient memory for creating the clone.
    **/
   public TreeBag<E> clone( )
   {
      TreeBag<E> tree = null;

      try
      {
         tree = (TreeBag) super.clone();
      }
      catch(CloneNotSupportedException e)
      {
         throw new OutOfMemoryError("Insufficient memory for creating the clone.");
      }


      tree.root = BTNode.treeCopy(root);

      return tree;
   }

   /**
    * Accessor method to count the number of occurrences of a particular element
    * in this bag.
    * @param <CODE>target</CODE>
    *   the element that needs to be counted
    * @return
    *   the number of times that <CODE>target</CODE> occurs in this bag
    **/
   public int countOccurrences(E target)
   {
      int count = 0;
      BTNode<E> cursor = root;

      if(root == null)
      {
         System.out.println("The tree is empty, countOccurrences() cannot be properly used");
         return 0;
      }

      while(cursor != null)
      {
         if(target.compareTo(cursor.getData()) < 0)
            cursor.getLeft();
         if(target.compareTo(cursor.getData()) > 0)
            cursor.getRight();
         else//target and cursor.getData are equal
            count++;
      }

      return count;
   }


   /**
    * Determine the number of elements in this bag.
    * @param - none
    * @return
    *   the number of elements in this bag
    **/
   public int size( )
   {
      return BTNode.treeSize(root);
   }




   /**
    * Add the contents of another bag to this bag.
    * @param <CODE>addend</CODE>
    *   a bag whose contents will be added to this bag
    * <dt><b>Precondition:</b><dd>
    *   The parameter, <CODE>addend</CODE>, is not null.
    * <dt><b>Postcondition:</b><dd>
    *   The elements from <CODE>addend</CODE> have been added to this bag.
    * @exception IllegalArgumentException
    *   Indicates that <CODE>addend</CODE> is null.
    * @exception OutOfMemoryError
    *   Indicates insufficient memory to increase the size of the bag.
    **/
   public void addAll(TreeBag<E> addend)
   {
      BTNode<E> addNode;

      if(addend == null)
      {
         throw new IllegalArgumentException("The bag is empty");
      }
      if(root == addend.root)
      {
         addNode = BTNode.treeCopy(addend.root);
      }
   }

   /**
    * Create a new bag that contains all the elements from two other bags.
    * @param <CODE>b1</CODE>
    *   the first of two bags
    * @param <CODE>b2</CODE>
    *   the second of two bags
    * <dt><b>Precondition:</b><dd>
    *   Neither b1 nor b2 is null.
    * @return
    *   the union of b1 and b2
    * @exception IllegalArgumentException
    *   Indicates that one of the arguments is null.
    * @exception OutOfMemoryError
    *   Indicates insufficient memory for the new bag.
    **/
   public static TreeBag union(TreeBag b1, TreeBag b2)
   {
      TreeBag b3 = new TreeBag();//New bag

      if((b1 == null) || (b2 == null))
      {
         throw new IllegalArgumentException("Atleast one of the bags is empty");
      }
      else
      {
         b3 = b1.clone();//cloning b1 to b3
      }

      b3.addAll(b2);//adding b2 to b3


      return b3;
   }

   public BTNode<E> getRoot() {
      return root;
   }

}
           
