class DoublyListNode
    {
        int key;
        int value;
        DoublyListNode prev;
        DoublyListNode next;
    }

    public class LRUCache
    {
        private Map<Integer, DoublyListNode> cache = new HashMap<>();
        private int capacity;
        private DoublyListNode head, tail;

        public LRUCache(int capacity)
        {
            this.capacity = capacity;
            
            head = new DoublyListNode();

            tail = new DoublyListNode();

            head.next = tail;
            tail.prev = head;
        }
    
        /**
        Always add the new node right after head
         */

         private void addNodeToHead(DoublyListNode node)
            {
                DoublyListNode pre = head;
                DoublyListNode nex = head.next;
                pre.next = node;
                node.prev = pre;

                nex.prev = node;
                node.next = nex;
            }

         /**
         Remove an existing node from the linked list.
          */

            private void removeNode(DoublyListNode node)
             {
                DoublyListNode prev = node.prev;
                DoublyListNode next = node.next;

                prev.next = next;
                next.prev = prev;

                node.prev = null;
                node.next = null;
            }
     
        /**
        move certain node in beetwen to the head
         */

         private void moveToHead(DoublyListNode node)
         {
             removeNode(node);
             addNodeToHead(node);
         }

         /**
         pop the current tail    
          */

          private DoublyListNode removeTailNode()
          {
              DoublyListNode res = tail.prev;
              removeNode(res);
              return res;
          }

    public int get(int key)
    {
        DoublyListNode node = cache.get(key);
        if (node == null)
            return -1;

            //move the accessed node to the head;
            moveToHead(node);

            return node.value;
    }
    
    public void put(int key, int value) 
    {
        DoublyListNode node = cache.get(key);

        if (node == null)
        {
            DoublyListNode newNode = new DoublyListNode();
            newNode.key = key;
            newNode.value = value;

            cache.put(key, newNode);
            addNodeToHead(newNode);

            if (cache.size() > capacity)
            {
                //delete the tail
                DoublyListNode tail = removeTailNode();
                cache.remove(tail.key);
            }
        }
        else
        {
            //update the value
            node.value = value;
            moveToHead(node);
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */